package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.BaseResponse;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.UpdatePodcastInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updatepodcastinfo")
public class UpdatePodcastInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePodcastInfoController.class);
    @Autowired
    private UpdatePodcastInfoService updatePodcastInfoService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResponse updatePodcastInfo(@RequestParam("id") String id, @RequestParam("feedName") String feedName, @RequestParam("podcastName") String podcastName,@RequestParam("podcastNameOld") String podcastNameOld, @RequestParam("description") String description, @RequestParam("author") String author,
                                          @RequestParam("firstCategoryCode") String firstCategoryCode, @RequestParam("secondCategoryCode") String secondCategoryCode, @RequestParam("subtitle") String subtitle,
                                          @RequestParam("keywords") String keywords, @RequestParam("link") String link, @RequestParam("email") String email) {
        return updatePodcastInfoService.updatePodcastInfo(id, feedName, podcastName,podcastNameOld, description, author, firstCategoryCode, secondCategoryCode, subtitle, keywords, link, email);
    }
}
