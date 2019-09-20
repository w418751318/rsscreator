package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.UpdatePodcastListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdatePodcastListController {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePodcastListController.class);
    @Autowired
    private UpdatePodcastListService updatePodcastListService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ServiceResponse updatePodcastList(@RequestParam("podcastName") String podcastName, @RequestParam("uploadedPodcastName") String uploadedPodcastName, @RequestParam("title") String title, @RequestParam("shownotes") String shownotes, @RequestParam("episode") String episode, @RequestParam("season") String season, @RequestParam("duration") String duration, @RequestParam("enclosureType") String enclosureType, @RequestParam("length") String length, @RequestParam("episodeType") String episodeType, @RequestParam("feedStr") String feedStr) {
        logger.info("updatePodcastList:uploadedPodcastName-" + uploadedPodcastName + " title-" + title + " shownotes" + shownotes + " episode" + episode);
        return updatePodcastListService.updatePodcastList(podcastName, uploadedPodcastName, title, shownotes, episode, duration, enclosureType, length, season, episodeType, feedStr);
    }

}
