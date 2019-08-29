package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.repository.PodcastInfoRepository;
import com.leftorright.rsscreator.service.CreatePodcastService;
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
//    @Value("${management.filePath_dev}")
    @Value("${management.filePath_prod}")
    private String filePath;

    @Override
    public ServiceResponse createPodcast(String imageName, String title, String subtitle,
                                         String link, String category, String description,
                                         String keywords, String author, String email) {
        logger.info("createPodcast:"+imageName+"title:"+ title);
        String feed = link+"/feed";
        String imageHref = "http://47.103.157.221/file/"+imageName;//更换新

        //存入数据库
        PodcastInfo podcastInfo = new PodcastInfo();
        podcastInfo.setAuthor(author);
        podcastInfo.setDescription(description);
        podcastInfo.setEmail(email);
        podcastInfo.setImage(imageHref);
        podcastInfo.setLink(link);
        podcastInfo.setSubtitle(subtitle);
        podcastInfo.setTitle(title);
        if (podcastInfoRepository.save(podcastInfo) instanceof PodcastInfo){
            logger.info("写入数据库成功！");
        }else {
            logger.info("写入数据库失败！");
        }

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
//            String filePath = "/app/file/rss.xml";
//            String filePath1 = "/Users/zhuyikun/Desktop/rss.xml";
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter( new FileOutputStream(new File(filePath)), format);
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

        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_CREATE, "","",null);
    }


    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username,String[] permissions) {
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
