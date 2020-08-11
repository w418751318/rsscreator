
package com.leftorright.rsscreator.utils;


import com.leftorright.rsscreator.entity.PageBean;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.mapper.JsonMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;


public class JsonUtil {
	private static JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	// jsonMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
	// true);

	public static String toJson(Object object) {
		String json = jsonMapper.toJson(object);
		return json;
	}

	public static <T> T jsonToObject(Object json, Class<T> clazz) {
		//jsonMapper.getMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		if (json instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) json;
			try {
				String str = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8")).readLine();
				return jsonMapper.fromJson(str, clazz);
			} catch (Exception e) {
				return null;
			}
		}
		return jsonMapper.fromJson(String.valueOf(json), clazz);
	}


	public static JSONObject string2json(String str) {
		try {
			JSONObject ret=null;
			ret=JSONObject.fromObject(str);
			if ("null".equals(ret.toString())) return null;
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

    public static PageBean jsonToPage(HttpServletRequest req) {
        PageBean pageBean = new PageBean();
        try {
            String str = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8")).readLine();
            Map<String, Object> map = jsonToObject(str, Map.class);
            if(map==null){
                return pageBean;
            }
            if (map.get("limit")!=null && map.get("offset")!=null) {
                Integer limit = Integer.parseInt(map.get("limit").toString());
                Integer offset = (Integer.parseInt(map.get("offset").toString()) - 1) * limit;
                pageBean.setOffset(offset);
                pageBean.setLimit(limit);
            }
            Map<String,Object> page = (Map<String, Object>) map.get("page");
            if(page != null){
                int limit = Integer.parseInt(page.get("pageSize").toString());
                int offset = (Integer.parseInt(page.get("pageIndex").toString())-1)*limit;
                page.put("pageSize", limit);
                page.put("pageIndex", offset);
                pageBean.setPage(page);
            }
            if(map.get("type") != null){
                int type = Integer.parseInt(map.get("type").toString());
                pageBean.setType(type);
            }

            if(map.get("isshow") != null){
                String isshow = map.get("isshow").toString();
                pageBean.setIsshow(isshow);
            }
            if(map.get("isbanner") != null){
                String isbanner = map.get("isbanner").toString();
                pageBean.setIsbanner(isbanner);
            }
            if(map.get("key") != null){
                String key = map.get("key").toString();
                pageBean.setKey(key);
            }
            if(map.get("id") != null){
                String id = map.get("id").toString();
                pageBean.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return pageBean;
    }
}
