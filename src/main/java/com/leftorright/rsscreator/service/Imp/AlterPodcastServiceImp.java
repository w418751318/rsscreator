package com.leftorright.rsscreator.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastItem;
import com.leftorright.rsscreator.repository.PodcastItemRepository;
import com.leftorright.rsscreator.service.AlterPodcastService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AlterPodcastServiceImp implements AlterPodcastService {
    private static final Logger logger = LoggerFactory.getLogger(AlterPodcastServiceImp.class);

    @Autowired
    private PodcastItemRepository podcastItemRepository;
    @Value("${management.filePath_rss_prod}")
    private String filePath;

    @Override
    public ServiceResponse alterPodcastItem(String id, String podcastname, String newdata, String feedStr) {

        File file = new File(filePath + feedStr + ".xml");
        SAXReader reader = new SAXReader();
        Document document = null;
        Element rss = null;
        try {
            document = reader.read(file);
            rss = document.getRootElement();

            List<Element> listElement = rss.element("channel").selectNodes("//item");//所有channel子节点item的list
            for (Element e : listElement) {//遍历所有一级子节点
//                logger.info("listElement.e=" + e.asXML());
//                logger.info("season=" + e.elementText("season"));
//                   logger.info("title " + e.elementText("title"));
//                   logger.info("podcastname " + podcastname);

                //根据单集播客的标题进行定位
                if (e.elementText("title").equals(podcastname)) {

                    JSONObject newdataJSONObject = JSON.parseObject(newdata);
                    String newTitle = newdataJSONObject.get("episodename").toString();
                    String newShownotes = newdataJSONObject.get("shownotes").toString();
                    String newPubdate = newdataJSONObject.get("pubdate").toString();
                    String newSeason = newdataJSONObject.get("season").toString();
                    String newEpisode = newdataJSONObject.get("episode").toString();
                    String newType = newdataJSONObject.get("type").toString();

                    //转换时间格式 2019-09-17 17:31:15 => Tue, 17 Sep 2019 09:31:15 GMT
                    SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                    sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newPubdate);
                    String pubDateString = sdf3.format(date);

                    /******************************修改xml开始******************************/
                    //将最新的数据更新到xml文件中
                    e.element("title").setText(newTitle);//设置title

                    e.element("description").setText("");//清空description
                    e.element("description").addCDATA(newShownotes);//设置description

                    e.element("encoded").setText("");//清空contentEncoded
                    e.element("encoded").addCDATA(newShownotes);//设置content:encoded

                    e.element("summary").setText("");//清空itunes:summary
                    e.element("summary").addCDATA(newShownotes);//设置itunes:summary

                    e.element("pubDate").setText(pubDateString);
                    e.element("season").setText(newSeason);
                    e.element("episode").setText(newEpisode);
                    e.element("episodeType").setText(newType);

                    XMLWriter writer = new XMLWriter(new FileWriter(file));
                    writer.write(document);
                    writer.flush();
                    writer.close();
                    logger.info("更新播客-xml更新完毕");
                    /******************************修改xml完成******************************/

                    /******************************修改数据库开始******************************/
                    PodcastItem podcastItem = podcastItemRepository.findById(Integer.parseInt(id)).get();
                    podcastItem.setTitle(newTitle);//更新播客标题
                    podcastItem.setDescription(newShownotes);
                    podcastItem.setPubDate(pubDateString);//数据库中存储的更新时间格式为："EEE, dd MMM yyyy HH:mm:ss z"
                    podcastItem.setSeason(newSeason);
                    podcastItem.setEpisode(newEpisode);
                    podcastItem.setEpisodeType(newType);

                    if (podcastItemRepository.save(podcastItem) instanceof PodcastItem) {
                        logger.info("更新播客-数据库更新完毕");
                        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, "", "", null);
                    } else {
                        return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
                    }

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
        } catch (ParseException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATE_DB, "", "", null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, "", "", null);
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
