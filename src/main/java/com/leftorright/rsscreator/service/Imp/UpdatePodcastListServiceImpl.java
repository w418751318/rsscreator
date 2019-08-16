package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.UpdatePodcastListService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public ServiceResponse updatePodcastList(String uploadedPodcastName,String title,String shownotes,String episode,String duration,String type,String length) {
//        title, shownotes, uploadedPodcastName, episode, duration, type, length
        //直接读取文件
        String filePath = "/app/file/rss.xml";
//      String filePath = "C:\\Users\\unicom\\Desktop\\rss.xml";
        File file = new File(filePath);
        SAXReader reader = new SAXReader();
        Document document = null;
        Element rss = null;
        Element channel = null;
        try {
            document = reader.read(file);
            rss = document.getRootElement();//rss标签
            channel = rss.element("channel");
            String podcastAuthor = channel.elementText("itunes:author");
            String podcastLink = channel.elementText("link");
            //新增加的播客
            Element newItem = DocumentHelper.createElement("item");
            Element itunesEpisode = DocumentHelper.createElement("itunes:episode");
            Element podcastTitle= DocumentHelper.createElement("title");
            Element itunesTitle= DocumentHelper.createElement("itunes:title");
            Element description= DocumentHelper.createElement("description");
            Element link= DocumentHelper.createElement("link");
            Element author= DocumentHelper.createElement("author");
            Element pubDate= DocumentHelper.createElement("pubDate");
            Element guid = DocumentHelper.createElement("guid");
            Element enclosure = DocumentHelper.createElement("enclosure");
            Element itunesDuration = DocumentHelper.createElement("itunes:duration");

            podcastTitle.addText(title);
            itunesEpisode.addText(episode);
            itunesTitle.addText(title);
            description.addText(shownotes);
            //需要一个link的内容
            String itemLink = podcastLink + "/" + episode;
            link.addText(itemLink);
            guid.addAttribute("isPermaLink","true").addText(itemLink);
            //需要一个author的内容
            author.addText(podcastAuthor);
            //上传时间
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.US);
            sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
            String pubDateString = sdf3.format(new Date());
            pubDate.addText(pubDateString);
            //需要一个上传的音频文件类型+音频文件url+音频文件长度
            String itemRadioFileUrl = podcastLink + "/file/"+uploadedPodcastName;
            enclosure.addAttribute("type",type).addAttribute("length",length).addAttribute("url",itemRadioFileUrl);
            //需要一个音频文件长度 单位：秒
            itunesDuration.addText(duration);

            newItem.add(podcastTitle);
            channel.add(newItem);

            XMLWriter writer = new XMLWriter(new FileWriter(file));
            //写入数据
            writer.write(document);
            writer.close();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "","",null);
        }  catch (MalformedURLException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "","",null);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE, "","",null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, "","",null);
    }
    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username,String[] permissions) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setUid(uid);
        serviceResponse.setUsername(username);
        serviceResponse.setPermissions(permissions);
        return serviceResponse;
    }
}
