package com.leftorright.rsscreator.controller;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.entity.Rotation;
import com.leftorright.rsscreator.service.RotationService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rotation")
public class RotationController {
    @Autowired
    private RotationService rotationService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveRotation(@RequestBody Rotation rotation){
        return rotationService.saveRotation(rotation);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteRotation(@PathVariable("id") int id){
        return rotationService.deleteRotation(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllRotation(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage((request));
        return rotationService.getAllRotation(pageBean);
    }

}
