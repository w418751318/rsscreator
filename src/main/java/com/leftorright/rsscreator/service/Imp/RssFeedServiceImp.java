package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.service.RssFeedService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RssFeedServiceImp implements RssFeedService {
    private static final Logger logger = LoggerFactory.getLogger(RssFeedServiceImp.class);

    //读取配置文件中的配置：输出（读取）rss文件地址
//    @Value("${management.filePath_dev}")
    @Value("${management.filePath_rss_prod}")
    private String filePath;

    @Override
    public String rssFeed(String podcastName) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(filePath+podcastName+".xml");
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;//当输入的rss地址不合法：rss文件不存在时，返回null
        }
        logger.info("document.toString()---"+document.toString());
        return document.asXML();
    }
}
