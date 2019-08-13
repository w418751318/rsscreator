package com.leftorright.rsscreator.service.Imp;

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

@Service
public class UpdatePodcastListServiceImpl implements UpdatePodcastListService {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePodcastListServiceImpl.class);

    @Override
    public ServiceResponse updatePodcastList(String uploadedPodcastName,String title,String shownotes) {

        //直接读取文件
//        String filePath = "/app/file/rss.xml";
        String filePath = "C:\\Users\\unicom\\Desktop\\rss.xml";
        File file = new File(filePath);

        SAXReader reader = new SAXReader();
        Document document = null;
        Element rss = null;
        Element channel = null;
        //新增加的播客
        Element newItem = DocumentHelper.createElement("item");
        Element podcastTitle= DocumentHelper.createElement("title");
        Element itunesEpisode= DocumentHelper.createElement("itunes:episode");

        podcastTitle.setText(title);
        newItem.add(podcastTitle);

        try {
            document = reader.read(file);
            rss = document.getRootElement();//rss标签
            logger.info("root:"+rss);
            channel = rss.element("channel");
            logger.info("channel:"+channel);
            channel.add(newItem);

            XMLWriter writer = new XMLWriter(new FileWriter(file));
            //写入数据
            writer.write(document);
            writer.close();

        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
