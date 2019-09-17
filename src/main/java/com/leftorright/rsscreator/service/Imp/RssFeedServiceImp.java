package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.service.RssFeedService;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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
        //将podcastName转成拼音
        PinyinTool pinyinTool = new PinyinTool();
        try {
            String xmlFileName = pinyinTool.toPinYin(podcastName);
            document = reader.read(filePath + xmlFileName + ".xml");
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;//当输入的rss地址不合法：rss文件不存在时，返回null
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        logger.info("document.toString()---" + document.toString());
        return document.asXML();
    }
}
