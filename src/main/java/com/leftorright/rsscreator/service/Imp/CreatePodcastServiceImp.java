package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.repository.PodcastInfoRepository;
import com.leftorright.rsscreator.service.CreatePodcastService;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class CreatePodcastServiceImp implements CreatePodcastService {
    private static final Logger logger = LoggerFactory.getLogger(CreatePodcastServiceImp.class);
    @Autowired
    private PodcastInfoRepository podcastInfoRepository;

    //读取配置文件中的配置：输出（读取）rss文件地址
//    @Value("${management.filePath_rss_dev}")
    @Value("${management.filePath_rss_prod}")
    private String filePath;

    @Override
    public ServiceResponse createPodcast(String imageName, String podcastName, String subtitle,
                                         String link, String firstCategoryCode, String secondCategoryCode, String description,
                                         String keywords, String author, String email, String feedname) {
        logger.info("createPodcast:" + imageName + "podcastName:" + podcastName);

        //使用汉字转成的拼音，用作xml文件的名字
//        PinyinTool tool = new PinyinTool();
//        String xmlFileName = null;
//        try {
//            xmlFileName = tool.toPinYin(podcastName);
//        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
//            badHanyuPinyinOutputFormatCombination.printStackTrace();
//        }
//        String feed = link + "/feed?ep=" + xmlFileName;

        String feed = link + "/feed?ep=" + feedname;
        String imageHref = "https://justpodmedia.com/pic/" + imageName;//更换新

        //存入数据库
        PodcastInfo podcastInfo = new PodcastInfo();
        podcastInfo.setAuthor(author);
        podcastInfo.setDescription(description);
        podcastInfo.setEmail(email);
        podcastInfo.setImage(imageHref);
        podcastInfo.setLink(link);
        podcastInfo.setSubtitle(subtitle);
        podcastInfo.setPodcastname(podcastName);
        podcastInfo.setFeedname(feedname);
        podcastInfo.setKeywords(keywords);
        podcastInfo.setFirstcategorycode(firstCategoryCode);
        podcastInfo.setSecondategorycode(secondCategoryCode);

        if (podcastInfoRepository.save(podcastInfo) instanceof PodcastInfo) {
            logger.info("写入数据库成功！");
        } else {
            logger.info("写入数据库失败！");
            return jsonResult(ServiceConstant.STATUS_DB_ERROR, ServiceConstant.MSG_DB_ERROR, "01", "", null);
        }

        /**
         * 创建一个rss文件
         */
        // 创建Document
        Document document = DocumentHelper.createDocument();

        // 添加根节点 rss
        Element rss = document.addElement("rss");
        rss.addAttribute("version", "2.0");
        rss.addNamespace("atom", "http://www.w3.org/2005/Atom");
        rss.addNamespace("itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd");
        rss.addNamespace("content", "http://purl.org/rss/1.0/modules/content/");

        // 添加channel节点
        Element channel = rss.addElement("channel");
        channel.addElement("title").addText(podcastName);
        channel.addElement("itunes:subtitle").addText(subtitle);
        channel.addElement("link").addText(link);
        channel.addElement("atom:link").addAttribute("href", feed)
                .addAttribute("rel", "self")
                .addAttribute("type", "application/rss+xml");
        channel.addElement("description").addText(description);
        channel.addElement("itunes:summary").addText(description);

        channel.addElement("language").addText("zh-CN");
        channel.addElement("copyright").addText("&#169; 2019 JustPod");
        channel.addElement("itunes:explicit").addText("false");
        channel.addElement("itunes:keywords").addText(keywords);
        channel.addElement("itunes:author").addText(author);
        channel.addElement("itunes:type").addText("episodic");
        Element categoryElement = channel.addElement("itunes:category");
        categoryElement.addAttribute("text", firstCategoryCode);
        //分类可能没有二级分类
        if (!secondCategoryCode.equals("null")){
            categoryElement.addElement("itunes:category").addAttribute("text", secondCategoryCode);
        }
        channel.addElement("itunes:image").addAttribute("href", imageHref);

        Element owner = channel.addElement("itunes:owner");
        owner.addElement("itunes:name").addText(podcastName);
        owner.addElement("itunes:email").addText(email);

        try {
//            String filePath = "/app/file/rss.xml";
//            String filePath1 = "/Users/zhuyikun/Desktop/rss.xml";

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(filePath + feedname + ".xml")), format);
//            XMLWriter writer = new XMLWriter( new FileOutputStream(new File("C:\\Users\\unicom\\Desktop\\rss.xml")), format);
            writer.write(document);
            logger.info("Create rss.xml success!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "00", "", null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "01", "", null);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11", "", null);
        }

        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_CREATE, "", "", null);
    }


    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username, String[] permissions) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setUid(uid);
        serviceResponse.setUsername(username);
        serviceResponse.setPermissions(permissions);
        serviceResponse.setRspData(null);
        return serviceResponse;
    }
}
