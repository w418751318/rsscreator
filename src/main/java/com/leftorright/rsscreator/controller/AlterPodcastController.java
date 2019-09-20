package com.leftorright.rsscreator.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.AlterPodcastService;
import com.leftorright.rsscreator.service.UpdatePodcastListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alter")
public class AlterPodcastController {
    private static final Logger logger = LoggerFactory.getLogger(AlterPodcastController.class);
    @Autowired
    private AlterPodcastService alterPodcastService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ServiceResponse alterPodcastItem(@RequestParam("id") String id, @RequestParam("podcastname") String podcastname, @RequestParam("season") String season, @RequestParam("episode") String episode, @RequestParam("newdata") String newdata, @RequestParam("feedStr") String feedStr) {
        return alterPodcastService.alterPodcastItem(id, podcastname, season, episode, newdata, feedStr);
    }
}
