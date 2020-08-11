package com.leftorright.rsscreator.controller;


import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.BrandService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/brand")
public class BrandInfoController {
    private static final Logger logger = LoggerFactory.getLogger(BrandInfoController.class);

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveBrand(@RequestBody Brand brand){
        return brandService.saveBrand(brand);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteBrand(@PathVariable("id") int id){
        return brandService.deleteBrand(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllBrand(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage((request));
        return brandService.getAllBrand(pageBean);
    }

}
