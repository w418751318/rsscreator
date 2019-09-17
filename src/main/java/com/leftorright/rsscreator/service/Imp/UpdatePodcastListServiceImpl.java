package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastItem;
import com.leftorright.rsscreator.repository.PodcastItemRepository;
import com.leftorright.rsscreator.service.UpdatePodcastListService;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Service
public class UpdatePodcastListServiceImpl implements UpdatePodcastListService {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePodcastListServiceImpl.class);
    @Autowired
    private PodcastItemRepository podcastItemRepository;

    //读取配置文件中的配置：输出（读取）rss文件地址
//    @Value("${management.filePath_dev}")
    @Value("${management.filePath_rss_prod}")
    private String filePath;

    @Override
    public ServiceResponse updatePodcastList(String podcastName, String uploadedPodcastName, String title, String shownotes, String episode, String duration, String enclosureType, String length, String season, String episodeType) {
        logger.info("uploadedPodcastName-" + uploadedPodcastName + " title-" + title + " shownotes" + shownotes + " episode" + episode);

        //直接读取rss文件
//        String filePath = "/app/file/rss.xml";
//        String filePath = "/Users/zhuyikun/Desktop/rss.xml";
        //使用汉字转成的拼音，用作xml文件的名字
        PinyinTool tool = new PinyinTool();
        String xmlFileName = null;
        try {
            xmlFileName = tool.toPinYin(podcastName);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

        File file = new File(filePath + xmlFileName + ".xml");
        SAXReader reader = new SAXReader();
        Document document = null;
        Element rss = null;
        Element channel = null;
        try {
            document = reader.read(file);
            rss = document.getRootElement();//rss标签
            channel = rss.element("channel");
            String podcastAuthor = channel.elementText("author");
            String podcastLink = channel.elementText("link");
            //写入数据库
            Object podcastItem = savePodcastItemToDB(podcastName, podcastLink, podcastAuthor, title, shownotes, uploadedPodcastName, episode, duration, enclosureType, length, season, episodeType);
            if (podcastItem instanceof PodcastItem) {
                logger.info("更新播客 " + title + " 成功！");
            } else {
                return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
            }
            //创建xml中的item节点
            Element newItem = createNewItem(podcastName, podcastLink, podcastAuthor, title, shownotes, uploadedPodcastName, episode, duration, enclosureType, length, season, episodeType);
            //向channel节点中插入item节点
            channel.add(newItem);

            XMLWriter writer = new XMLWriter(new FileWriter(file));
            //写入数据
            writer.write(document);
            writer.close();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "", "", null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "", "", null);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "", "", null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, "", "", null);
    }

    /**
     * 保存本集播客相关信息到数据库表：tb_podcastitem
     *
     * @param podcastLink
     * @param podcastAuthor
     * @param title
     * @param shownotes
     * @param uploadedPodcastName
     * @param episode
     * @param duration
     * @param enclosureType
     * @param length
     * @return
     */
    private PodcastItem savePodcastItemToDB(String podcastName, String podcastLink, String podcastAuthor, String title, String shownotes, String uploadedPodcastName, String episode, String duration, String enclosureType, String length, String season, String episodeType) {
        String itemRadioFileUrl = podcastLink + "/audio/" + uploadedPodcastName;//上传音频文件在服务器上的位置
        String itemLink = podcastLink + "/" + podcastName + "/" + episode;//link节点的内容为本集的网址，拼写规则：主页网址(link)+"/"+podcastName+"/"+episode
        //上传时间
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        String pubDateString = sdf3.format(new Date());
        PodcastItem podcastItem = new PodcastItem();
        podcastItem.setAuthor(podcastAuthor);
        podcastItem.setDescription(shownotes);
        podcastItem.setDuration(duration);
        podcastItem.setEnclosure_length(length);
        podcastItem.setEnclosure_type(enclosureType);
        podcastItem.setEnclosure_url(itemRadioFileUrl);
        podcastItem.setEpisode(episode);
        podcastItem.setSeason(season);
        podcastItem.setLink(itemLink);
        podcastItem.setPubDate(pubDateString);
        podcastItem.setTitle(title);
        podcastItem.setPodcastname(podcastName);
        podcastItem.setEpisodeType(episodeType);

        return podcastItemRepository.save(podcastItem);
    }

    private Element createNewItem(String podcastName, String podcastLink, String podcastAuthor, String title, String shownotes, String uploadedPodcastName, String episode, String duration, String enclosureType, String length, String season, String episodeType) {
        //新增加的播客
        Element newItem = DocumentHelper.createElement("item");

        Element itunesEpisode = DocumentHelper.createElement("itunes:episode");
        Element podcastTitle = DocumentHelper.createElement("title");
        Element itunesTitle = DocumentHelper.createElement("itunes:title");
        Element description = DocumentHelper.createElement("description");
        Element contentEncoded = DocumentHelper.createElement("content:encoded");
        Element itunesSummary = DocumentHelper.createElement("itunes:summary");
        Element link = DocumentHelper.createElement("link");
        Element author = DocumentHelper.createElement("author");
        Element pubDate = DocumentHelper.createElement("pubDate");
        Element guid = DocumentHelper.createElement("guid");
        Element enclosure = DocumentHelper.createElement("enclosure");
        Element itunesDuration = DocumentHelper.createElement("itunes:duration");
        Element episodeTypeElement = DocumentHelper.createElement("itunes:episodeType");//full(默认) || trailer || bonus
        Element itunesSeason = DocumentHelper.createElement("itunes:season");

        episodeTypeElement.addText(episodeType);
        itunesSeason.addText(season);
        itunesEpisode.addText(episode);
        podcastTitle.addText(title);
        itunesTitle.addText(title);

        description.addCDATA(shownotes);
        contentEncoded.addCDATA(shownotes);
        itunesSummary.addCDATA(shownotes);

        //需要一个link的内容
        String itemLink = podcastLink + "/" + podcastName + "/" + episode;
        link.addText(itemLink);
        //需要一个author的内容
        author.addText(podcastAuthor);
        //上传时间
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        String pubDateString = sdf3.format(new Date());
        pubDate.addText(pubDateString);
        guid.addAttribute("isPermaLink", "true").addText(itemLink);
        //需要一个上传的音频文件类型+音频文件url+音频文件长度
        String itemRadioFileUrl = podcastLink + "/audio/" + uploadedPodcastName;
        enclosure.addAttribute("type", enclosureType).addAttribute("length", length).addAttribute("url", itemRadioFileUrl);
        //需要一个音频文件长度 单位：秒
        itunesDuration.addText(duration);

        newItem.add(itunesSeason);
        newItem.add(podcastTitle);
        newItem.add(itunesTitle);
        newItem.add(description);
        newItem.add(link);
        newItem.add(author);
        newItem.add(pubDate);
        newItem.add(guid);
        newItem.add(enclosure);
        newItem.add(itunesDuration);
        newItem.add(itunesEpisode);
        newItem.add(episodeTypeElement);

        newItem.add(contentEncoded);
        newItem.add(itunesSummary);


        return newItem;
    }

    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username, String[] permissions) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setUid(uid);
        serviceResponse.setUsername(username);
        serviceResponse.setPermissions(permissions);
        return serviceResponse;
    }
}
