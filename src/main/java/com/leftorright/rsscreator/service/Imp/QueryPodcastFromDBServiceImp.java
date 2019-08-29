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
            PodcastInfo podcastInfo = podcastList.get(0);//一般情况下，只有一个数据,未来可以扩展为管理多个播客
            podcastInfoJSONObject.put("email", podcastInfo.getEmail());
            podcastInfoJSONObject.put("author", podcastInfo.getAuthor());
            podcastInfoJSONObject.put("description", podcastInfo.getDescription());
            podcastInfoJSONObject.put("image", podcastInfo.getImage());
            podcastInfoJSONObject.put("link", podcastInfo.getLink());
            podcastInfoJSONObject.put("subtitle", podcastInfo.getSubtitle());
            podcastInfoJSONObject.put("title", podcastInfo.getTitle());
            logger.info("podcastInfoJSONObject: " + podcastInfoJSONObject.toJSONString());
        } else {
            logger.info("数据库中没有播客的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null,null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, podcastInfoJSONObject,null);
    }

    @Override
    public ServiceResponse queryPodcastItems() {
        //查询数据库 items
        List<PodcastItem> podcastItemList = podcastItemRepository.findAll();
        JSONArray podcastItemJSONArray = new JSONArray();
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
        } else {
            logger.info("数据库中没有播客item的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL_ITEM, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null,null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, null,podcastItemJSONArray);
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
