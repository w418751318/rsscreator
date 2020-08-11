package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.dao.RotationMapper;
import com.leftorright.rsscreator.entity.BlogShow;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.entity.Rotation;
import com.leftorright.rsscreator.service.RotationService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RotationServiceImpl implements RotationService {
    private static final Logger logger = LoggerFactory.getLogger(RotationServiceImpl.class);
    @Autowired
    private RotationMapper rotationMapper;

    @Override
    public Result saveRotation(Rotation rotation) {
        Result result = new Result();
        try {
            FileUtils.setFileUrl(rotation);
            if(rotation.getId() == null){
                int i = rotationMapper.saveRotation(rotation);
            }else{
                rotationMapper.updateRotation(rotation);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作轮播图失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteRotation(int id) {
        Result result = new Result();
        try {
            int i = rotationMapper.deleteRotation(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除轮播图失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllRotation(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Rotation> rotation = rotationMapper.getRotation(pageBean);
            int count = rotationMapper.getRotationCount();
            Map<String,Object> map = new HashMap<>();
            map.put("list",rotation);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有轮播图：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

}
