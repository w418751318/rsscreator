package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.BaseResponse;
import com.leftorright.rsscreator.service.QueryPodcastFromDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/querypodcastinfo")
public class QueryPodcastInfoFromDBController {
    private static final Logger logger = LoggerFactory.getLogger(QueryPodcastInfoFromDBController.class);
    @Autowired
    private QueryPodcastFromDBService queryPodcastFromDBService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BaseResponse queryPodcastFromDB(@RequestParam("podcastName") String podcastName) {
        return queryPodcastFromDBService.queryPodcastInfo(podcastName);
    }

}
