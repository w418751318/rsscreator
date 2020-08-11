package com.leftorright.rsscreator.controller;


import com.leftorright.rsscreator.entity.Media;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.MediaService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/media")
public class MediaInfoController {

    @Autowired
    private MediaService mediaService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveMedia(@RequestBody Media media){
        return mediaService.saveMedia(media);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteMedia(@PathVariable("id") int id){
        return mediaService.deleteMedia(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllMedia(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage((request));
        return mediaService.getAllMedia(pageBean);
    }

}
