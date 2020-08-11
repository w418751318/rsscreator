package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.dao.CommonMapper;
import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.Common;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.CommonService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Result saveCommon(Common common) {
        Result result = new Result();
        try {
            if(common.getId() == null){
                commonMapper.saveCommon(common);
            }else{
                commonMapper.updateCommon(common);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作公共" + common.getType() +"失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteCommon(int id) {
        Result result = new Result();
        try {
            int i = commonMapper.deleteById(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除公共失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getCommonByType(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Common> allCommon = commonMapper.getAllCommon(pageBean);
            int count = commonMapper.getAllCommonCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",allCommon);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有公共：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

}
