package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.service.PodcastFeedService;
import com.rometools.rome.feed.rss.Channel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PodcastFeedServiceImp implements PodcastFeedService {
    @Override
    public String podcastFeed() {
        //直接读取文件
        String filePath = "/app/file/rss.xml";
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
    }
}
