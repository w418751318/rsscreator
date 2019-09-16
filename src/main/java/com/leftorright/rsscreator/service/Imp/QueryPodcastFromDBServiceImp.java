package com.leftorright.rsscreator.service.Imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.controller.QueryPodcastFromDBController;
import com.leftorright.rsscreator.domain.response.ServiceConstant;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.entity.PodcastItem;
import com.leftorright.rsscreator.repository.PodcastInfoRepository;
import com.leftorright.rsscreator.repository.PodcastItemRepository;
import com.leftorright.rsscreator.service.QueryPodcastFromDBService;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QueryPodcastFromDBServiceImp implements QueryPodcastFromDBService {
    private static final Logger logger = LoggerFactory.getLogger(QueryPodcastFromDBServiceImp.class);

    @Autowired
    private PodcastInfoRepository podcastInfoRepository;
    @Autowired
    private PodcastItemRepository podcastItemRepository;

    @Override
    public ServiceResponse queryPodcast() {
        //查询数据库
        List<PodcastInfo> podcastList = podcastInfoRepository.findAll();
        JSONObject podcastInfoJSONObject = new JSONObject();
        if (podcastList.size() > 0) {
            String[] podcastNames = new String[podcastList.size()];//存放播客名字的数组
            //循环取出podcastList中的所有播客的名字
            for (int i = 0;i<podcastList.size();i++){
                podcastNames[i] = podcastList.get(i).getPodcastname();
//                logger.info("podcastList.get(i).getPodcastname() " + podcastList.get(i).getPodcastname());
            }
            podcastInfoJSONObject.put("podcastList", podcastNames);//将存放播客列表的数组放到JSONObject中{"podcastList":["无聊斋"，"ridehome"]}
            logger.info("podcastInfoJSONObject: " + podcastInfoJSONObject.toJSONString());
        } else {
            logger.info("数据库中没有播客的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null,null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, podcastInfoJSONObject,null);
    }

    @Override
    public ServiceResponse queryPodcastItems(String podcastName) {
        //使用汉字转成的拼音，用作xml文件的名字
        PinyinTool tool = new PinyinTool();
        String xmlFileName = null;
        try {
            xmlFileName = tool.toPinYin(podcastName);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

        //查询数据库 items
        List<PodcastItem> podcastItemList = podcastItemRepository.findPodcastItemByPodcastname(podcastName);
        JSONArray podcastItemJSONArray = new JSONArray();
        JSONObject podcastInfoJSONObject = new JSONObject();
        if (podcastItemList.size() > 0) {
            for (PodcastItem podcastItem : podcastItemList) {
                JSONObject podcastItemJSONObject = new JSONObject();
                podcastItemJSONObject.put("key",podcastItem.getEpisode());//此处key用于前端展示
                podcastItemJSONObject.put("episodename",podcastItem.getTitle());//此处episodename用于前端展示
                Date date = new Date(podcastItem.getPubDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                podcastItemJSONObject.put("date",simpleDateFormat.format(date));//此处date用于前端展示
                int totalTime = Integer.parseInt(podcastItem.getDuration());
                String totalTimeStr = totalTime/3600 > 0 ? totalTime/3600+"时" + (totalTime%3600)/60 + "分" + (totalTime%3600)%60 + "秒":(totalTime%3600)/60 + "分" + (totalTime%3600)%60 + "秒";
                podcastItemJSONObject.put("length",totalTimeStr);//此处date用于前端展示
                podcastItemJSONArray.add(podcastItemJSONObject);
            }
            //本播客feed地址
            String feedStr = "https://justpodmedia.com/feed?ep="+xmlFileName;
            podcastInfoJSONObject.put("feed",feedStr);
        } else {
            logger.info("数据库中没有播客item的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL_ITEM, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null,null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, podcastInfoJSONObject,podcastItemJSONArray);
    }

    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username, String[] permissions, JSONObject podcastInfoJSONObject,JSONArray podcastItemJSONArray) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setUid(uid);
        serviceResponse.setUsername(username);
        serviceResponse.setPermissions(permissions);
        serviceResponse.setRspData(podcastInfoJSONObject);
        serviceResponse.setRspDataArray(podcastItemJSONArray);
        return serviceResponse;
    }
}
