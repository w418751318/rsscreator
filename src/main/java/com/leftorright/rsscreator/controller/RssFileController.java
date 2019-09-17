package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.RssFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rssfeed")
public class RssFileController {
    private static final Logger logger = LoggerFactory.getLogger(QueryPodcastItemFromDBController.class);
    @Autowired
    private RssFeedService rssFeedService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"text/xml;charset=UTF-8"})
    public String rssFeed(@RequestParam("ep") String podcastName) {
        logger.info("rssfeed");
        return rssFeedService.rssFeed(podcastName);
    }
}
