package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.dao.BrandMapper;
import com.leftorright.rsscreator.service.BrandService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);
    
    @Autowired
    private BrandMapper brandMapper;
    
    
    @Override
    public Result saveBrand(Brand brand) {
        Result result = new Result();
        try {
            FileUtils.setFileUrl(brand);
            if(brand.getId() == null){
              brandMapper.saveBrand(brand);
            }else{
               brandMapper.updateBrand(brand);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作品牌失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteBrand(int id) {
        Result result = new Result();
        try {
            int i = brandMapper.deleteBrand(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除品牌失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllBrand(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Brand> allBrand = brandMapper.getAllBrand(pageBean);
            int count = brandMapper.getAllBrandCount();
            Map<String,Object> map = new HashMap<>();
            map.put("list",allBrand);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有品牌：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

}
