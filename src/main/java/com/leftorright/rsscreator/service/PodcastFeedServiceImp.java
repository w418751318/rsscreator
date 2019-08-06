package com.leftorright.rsscreator.service;

import com.rometools.rome.feed.rss.Channel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PodcastFeedServiceImp implements PodcastFeedService{
    @Override
    public String podcastFeed() {
        //直接读取文件
//        String filePath = "/app/file/rss.xml";
        String filePath = "C:\\Users\\unicom\\Desktop\\rss.xml";
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(filePath);
            Element root = document.getRootElement();


        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return document.toString();


//        利用rome包
//        Channel channel = new Channel();
//        channel.setTitle("这是一个播客的标题");
//        channel.setLink("https://www.apple.com/itunes/podcasts/");
//        channel.setLanguage("zh-CN");



    }
}
