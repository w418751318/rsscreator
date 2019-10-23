package com.leftorright.rsscreator.service.Imp;

import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.domain.response.BaseResponse;
import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.entity.PodcastItem;
import com.leftorright.rsscreator.repository.PodcastInfoRepository;
import com.leftorright.rsscreator.repository.PodcastItemRepository;
import com.leftorright.rsscreator.service.UpdatePodcastInfoService;
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
import java.util.List;

@Service
public class UpdatePodcastInfoServiceImp implements UpdatePodcastInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePodcastInfoServiceImp.class);

    @Autowired
    private PodcastInfoRepository podcastInfoRepository;
    @Autowired
    private PodcastItemRepository podcastItemRepository;
    @Value("${management.filePath_rss_prod}")
//    @Value("${management.filePath_rss_dev}")
    private String filePath;

    @Override
    public BaseResponse updatePodcastInfo(String id, String feedName, String podcastName, String podcastNameOld, String description,
                                          String author, String firstCategoryCode, String secondCategoryCode,
                                          String subtitle, String keywords, String link, String email) {
        //更新xml文件
        File file = new File(filePath + feedName + ".xml");
        SAXReader reader = new SAXReader();
        Document document = null;
        Element rss = null;
        try {
            document = reader.read(file);
            rss = document.getRootElement();
            Element channelElement = rss.element("channel");
            //需要修改的数据
            Element titleElement = channelElement.element("title");
            Element subtitleElement = channelElement.element("subtitle");
            Element linkElement = channelElement.element("link");
            Element descriptionElement = channelElement.element("description");
            Element summaryElement = channelElement.element("summary");
            Element keywordsElement = channelElement.element("keywords");
            Element authorElement = channelElement.element("author");
            Element firstCategoryElement = channelElement.element("category");

            Element ownerElement = channelElement.element("owner");
            Element ownerNameElement = ownerElement.element("name");
            Element ownerEmailElement = ownerElement.element("email");

            titleElement.setText(podcastName);
            subtitleElement.setText(subtitle);
            linkElement.setText(link);
            descriptionElement.setText(description);
            summaryElement.setText(description);
            keywordsElement.setText(keywords);
            authorElement.setText(author);
            firstCategoryElement.attribute("text").setData(firstCategoryCode);
            if (!secondCategoryCode.equals("null")) {//当传进来的二级分类不为空时
                logger.info("secondCategoryCode is not null");
                Element secondCategoryElement = firstCategoryElement.element("category");
                //当本身播客存在二级分类节点，更新二级分类节点
                if (secondCategoryElement != null) {
                    secondCategoryElement.attribute("text").setData(secondCategoryCode);//更新二级分类
                } else {//播客本身不存在二级分类节点，创建一个二级分类节点
                    firstCategoryElement.addElement("itunes:category").addAttribute("text", secondCategoryCode);//创建二级分类
                }
            } else {
                logger.info("secondCategoryCode is null");
                Element secondCategoryElement = firstCategoryElement.element("category");
                //当本身播客存在二级分类节点，删除二级分类节点
                if (secondCategoryElement != null) {
                    firstCategoryElement.remove(secondCategoryElement);
                    logger.info("delete secondCategoryCode");
                } else {//播客本身不存在二级分类节点，不做操作
                    logger.info("secondCategoryCode is null by default");
                }
            }
            ownerNameElement.setText(podcastName);
            ownerEmailElement.setText(email);

            XMLWriter writer = new XMLWriter(new FileWriter(file));
            writer.write(document);
            writer.flush();
            writer.close();
            logger.info("更新播客专辑信息-xml更新完毕");
            /*******更新数据库 开始********/
            PodcastInfo podcastInfo = podcastInfoRepository.findById(Integer.parseInt(id)).get();
            podcastInfo.setPodcastname(podcastName);
            podcastInfo.setDescription(description);
            podcastInfo.setAuthor(author);
            podcastInfo.setFirstcategorycode(firstCategoryCode);
            podcastInfo.setSecondategorycode(secondCategoryCode);
            podcastInfo.setSubtitle(subtitle);
            podcastInfo.setKeywords(keywords);
            podcastInfo.setLink(link);
            podcastInfo.setEmail(email);


            if (podcastInfoRepository.save(podcastInfo) instanceof PodcastInfo) {
                logger.info("更新播客专辑信息-数据库更新完毕");
                //当更改了播客名时，需要更新数据库，tb_podcastitem表中的podcastname字段
                if (!podcastNameOld.equals(podcastName)) {
                    List<PodcastItem> podcastItemList = podcastItemRepository.findPodcastItemByPodcastname(podcastNameOld);
                    for (PodcastItem podcastItem : podcastItemList) {
                        podcastItem.setPodcastname(podcastName);
                        podcastItemRepository.save(podcastItem);
                    }
                }
                JSONObject podcastInfoJSONObject = new JSONObject();
                podcastInfoJSONObject.put("podcastName", podcastName);
                podcastInfoJSONObject.put("description", description);
                podcastInfoJSONObject.put("author", author);
                podcastInfoJSONObject.put("firstCategoryCode", firstCategoryCode);
                podcastInfoJSONObject.put("secondCategoryCode", secondCategoryCode);
                podcastInfoJSONObject.put("subtitle", subtitle);
                podcastInfoJSONObject.put("keywords", keywords);
                podcastInfoJSONObject.put("link", link);
                podcastInfoJSONObject.put("email", email);
                return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, podcastInfoJSONObject);
            } else {
                return jsonResult(ServiceConstant.STATUS_FAIL, ServiceConstant.MSG_FAIL_UPDATEINFO_DB, null);
            }
            /*******更新数据库 结束********/
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_UPDATE, null);
    }

    private static BaseResponse<Object, Object> jsonResult(String responseCode, String responseMsg, JSONObject podcastInfoJSONObject) {
        BaseResponse serviceResponse = new BaseResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setRspData(podcastInfoJSONObject);
        return serviceResponse;
    }
}
