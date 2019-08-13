package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.CreatePodcastService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CreatePodcastServiceImp implements CreatePodcastService {
    private static final Logger logger = LoggerFactory.getLogger(CreatePodcastServiceImp.class);
    @Override
    public ServiceResponse createPodcast(String imageName, String title, String subtitle,
                                         String link, String category, String description,
                                         String keywords, String author, String email) {
         logger.info("createPodcast:"+imageName+"title:"+ title);
         if(imageName == "" || imageName == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","imageName == null",null);
         }
         if(title == "" || title == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","title == null",null);
         }
         if(subtitle == "" || subtitle == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","subtitle == null",null);
         }
         if(link == "" || link == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","link == null",null);
         }
         if(category == "" || category == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","category == null",null);
         }
         if(description == "" || description == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","description == null",null);
         }
         if(keywords == "" || keywords == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","keywords == null",null);
         }
         if(author == "" || author == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","author == null",null);
         }
        if(email == "" || email == null ){
             return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","email == null",null);
        }

         String feed = link+"/feed";
         String imageHref = "http://47.99.46.74/file/"+imageName;//更换新
        /**
         * 创建一个rss文件
         */
        // 创建Document
        Document document = DocumentHelper.createDocument();

        // 添加根节点 rss
        Element rss = document.addElement("rss");
        rss.addAttribute("version","2.0");
        rss.addNamespace("atom","http://www.w3.org/2005/Atom");
        rss.addNamespace("itunes","http://www.itunes.com/dtds/podcast-1.0.dtd");

        // 添加channel节点
        Element channel= rss.addElement("channel");
        channel.addElement("title").addText(title);
        channel.addElement("itunes:subtitle").addText(subtitle);
        channel.addElement("link").addText(link);
        channel.addElement("atom:link").addAttribute("href",feed)
                .addAttribute("rel","self")
                .addAttribute("type","application/rss+xml");
        channel.addElement("description").addText(description);
        channel.addElement("language").addText("zh-CN");
        channel.addElement("itunes:explicit").addText("false");
        channel.addElement("itunes:keywords").addText(keywords);
        channel.addElement("itunes:author").addText(author);
        channel.addElement("itunes:type").addText("episodic");
        channel.addElement("itunes:category").addAttribute("text",category);
        channel.addElement("itunes:image").addAttribute("href",imageHref);

        Element owner = channel.addElement("itunes:owner");
        owner.addElement("itunes:name").addText(title);
        owner.addElement("itunes:email").addText(email);

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter( new FileOutputStream(new File("/app/file/rss.xml")), format);
//            XMLWriter writer = new XMLWriter( new FileOutputStream(new File("C:\\Users\\unicom\\Desktop\\rss.xml")), format);
            writer.write(document);
            logger.info("Create rss.xml success!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "00","",null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "01","",null);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_SYSERROR, ServiceConstant.MSG_SYSERROR, "11","",null);
        }

        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS, "","",null);
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