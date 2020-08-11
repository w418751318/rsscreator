package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.entity.BlogShow;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.BlogShowService;
import com.leftorright.rsscreator.utils.FileUtils;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blogshow")
public class BlogShowController {

    @Autowired
    private BlogShowService blogShowService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveBlogShow(@RequestBody BlogShow blogShow){
        return blogShowService.saveBlogShow(blogShow);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteBlogShow(@PathVariable("id") int id){
        return blogShowService.deleteBlogShow(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllBlogShow(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage(request);
        return blogShowService.getAllBlogShow(pageBean);
    }


    @RequestMapping(value = "all/pod",method = RequestMethod.POST)
    public Result getAllPod(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage(request);
        return blogShowService.getAllPodCast(pageBean);
    }

    @RequestMapping(value = "get/name",method = RequestMethod.GET)
    public Result getAllPod(@RequestParam("name") String name){
        return blogShowService.getBlowByName(name);
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return FileUtils.getFileUrl();
    }

}
