package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.dao.MediaMapper;
import com.leftorright.rsscreator.entity.Media;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.MediaService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public Result saveMedia(Media media) {
        Result result = new Result();
        try {
            FileUtils.setFileUrl(media);
            if(media.getId() == null){
                mediaMapper.saveMedia(media);
            }else{
                mediaMapper.updateMedia(media);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作媒体失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteMedia(int id) {
        Result result = new Result();
        try {
            int i = mediaMapper.deleteMedia(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除媒体失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllMedia(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Media> allMedia = mediaMapper.getAllMedia(pageBean);
            int count = mediaMapper.getAllMediaCount();
            Map<String,Object> map = new HashMap<>();
            map.put("list",allMedia);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有媒体：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

}
