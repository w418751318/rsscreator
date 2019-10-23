package com.leftorright.rsscreator.service.Imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.domain.response.BaseResponse;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            //存放podcastname和podcastfeed的键值对
            Map<String, String > podcastFeedsMap = new HashMap<>();

            //循环取出podcastList中的所有播客的名字
            for (int i = 0; i < podcastList.size(); i++) {
                String podcastName = podcastList.get(i).getPodcastname();
                String feed = podcastList.get(i).getFeedname();
                podcastNames[i] = podcastName;
                podcastFeedsMap.put(podcastName,feed);
            }
            podcastInfoJSONObject.put("podcastList", podcastNames);//将存放播客列表的数组放到JSONObject中{"podcastList":["无聊斋"，"ridehome"]}
            podcastInfoJSONObject.put("feeds",podcastFeedsMap);
            logger.info("podcastInfoJSONObject: " + podcastInfoJSONObject.toJSONString());
        } else {
            logger.info("数据库中没有播客的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null, null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, podcastInfoJSONObject, null);
    }

    /**
     * 查询播客信息，eg：播客名，播客简介，作者
     * @param podcastName
     * @return
     */
    @Override
    public BaseResponse queryPodcastInfo(String podcastName) {
        PodcastInfo podcastInfo = podcastInfoRepository.findPodcastInfoByPodcastname(podcastName);
        if (podcastInfo != null) {
            JSONObject podcastInfoJSONObject = new JSONObject();
            podcastInfoJSONObject.put("id", podcastInfo.getId());
            podcastInfoJSONObject.put("podcastname", podcastInfo.getPodcastname());
            podcastInfoJSONObject.put("subtitle", podcastInfo.getSubtitle());
            podcastInfoJSONObject.put("link", podcastInfo.getLink());
            podcastInfoJSONObject.put("description", podcastInfo.getDescription());
            podcastInfoJSONObject.put("author", podcastInfo.getAuthor());
            podcastInfoJSONObject.put("image", podcastInfo.getImage());
            podcastInfoJSONObject.put("email", podcastInfo.getEmail());
            podcastInfoJSONObject.put("feedname", podcastInfo.getFeedname());
            podcastInfoJSONObject.put("keywords", podcastInfo.getKeywords());
            podcastInfoJSONObject.put("firstCategoryCode", podcastInfo.getFirstcategorycode());
            podcastInfoJSONObject.put("secondCategoryCode", podcastInfo.getSecondategorycode());
            return podcastInfojsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, podcastInfoJSONObject);
        } else {
            return podcastInfojsonResult(ServiceConstant.STATUS_QUERY_FAIL, ServiceConstant.MSG_FAIL_QUERY,  null);
        }

    }


    @Override
    public ServiceResponse queryPodcastItems(String podcastName) {
        //使用汉字转成的拼音，用作xml文件的名字
//        PinyinTool tool = new PinyinTool();
//        String xmlFileName = null;
//        try {
//            xmlFileName = tool.toPinYin(podcastName);
//        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
//            badHanyuPinyinOutputFormatCombination.printStackTrace();
//        }

        //查询数据库 items
        List<PodcastItem> podcastItemList = podcastItemRepository.findPodcastItemByPodcastname(podcastName);
        JSONArray podcastItemJSONArray = new JSONArray();
        JSONObject podcastInfoJSONObject = new JSONObject();
        if (podcastItemList.size() > 0) {
            for (PodcastItem podcastItem : podcastItemList) {
                JSONObject podcastItemJSONObject = new JSONObject();
                /**
                 * 前端展示内容：eg:
                 * key: '1',
                 * episodename: '王俊煜：迟早大家不会再装APP',
                 * shownotes:"这里是shownotes",
                 * pubdate: '2019-09-16 11:47:56',
                 * season: '9',
                 * episode: '1',
                 * type: 'full',
                 * podcastname:'忽左忽右'
                 */
                podcastItemJSONObject.put("key", podcastItem.getId());
                podcastItemJSONObject.put("episodename", podcastItem.getTitle());
                podcastItemJSONObject.put("shownotes", podcastItem.getDescription());
                Date date = new Date(podcastItem.getPubDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                podcastItemJSONObject.put("pubdate", simpleDateFormat.format(date));//此处date用于前端展示
                podcastItemJSONObject.put("season", podcastItem.getSeason());
                podcastItemJSONObject.put("episode", podcastItem.getEpisode());
                podcastItemJSONObject.put("type", podcastItem.getEpisodeType());
                podcastItemJSONObject.put("podcastname", podcastItem.getPodcastname());
                podcastItemJSONArray.add(podcastItemJSONObject);
            }
            //本播客feed地址
//            String feedStr = "https://justpodmedia.com/feed?ep=" + xmlFileName;
//            podcastInfoJSONObject.put("feed", feedStr);
        } else {
            logger.info("数据库中没有播客item的数据");
            return jsonResult(ServiceConstant.STATUS_QUERY_FAIL_ITEM, ServiceConstant.MSG_FAIL_QUERY, "", "", null, null, null);
        }
        return jsonResult(ServiceConstant.STATUS_SUCCESS, ServiceConstant.MSG_SUCCESS_QUERY, "", "", null, podcastInfoJSONObject, podcastItemJSONArray);
    }

    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username, String[] permissions, JSONObject podcastInfoJSONObject, JSONArray podcastItemJSONArray) {
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
    private static BaseResponse<Object, Object> podcastInfojsonResult(String responseCode, String responseMsg, JSONObject podcastInfoJSONObject) {
        BaseResponse serviceResponse = new BaseResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setRspData(podcastInfoJSONObject);
        return serviceResponse;
    }
}
