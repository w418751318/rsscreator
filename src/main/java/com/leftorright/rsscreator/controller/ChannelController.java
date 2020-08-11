package com.leftorright.rsscreator.controller;


import com.leftorright.rsscreator.dao.ChannelMapper;
import com.leftorright.rsscreator.entity.Channel;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.ChannelService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveChannel(@RequestBody Channel channel){
        return channelService.saveChannel(channel);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteChannel(@PathVariable("id") int id){
        return channelService.deleteChannel(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllChannel(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage(request);
        return channelService.getAllChannel(pageBean);
    }

    @RequestMapping(value = "getbyshow",method = RequestMethod.GET)
    public Result getChannelsByShowId(@RequestParam("feedname") String feedname,@RequestParam("type") String type){
        return channelService.getChannelsByShowId(feedname,type);
    }
}
