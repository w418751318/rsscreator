package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.service.PodcastFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class PodcastFeedController {
    private static final Logger logger = LoggerFactory.getLogger(PodcastFeedController.class);

    @Autowired
    private PodcastFeedService podcastFeedService;
    @RequestMapping(value = "/",method = RequestMethod.GET,headers="accept=text/html,application/xhtml+xml,application/xml")
    public String getFeed(){
        return podcastFeedService.podcastFeed();
    }
}
