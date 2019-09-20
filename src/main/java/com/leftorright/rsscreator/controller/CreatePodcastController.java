package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.CreatePodcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class CreatePodcastController {
    private static final Logger logger = LoggerFactory.getLogger(CreatePodcastController.class);


    @Autowired
    private CreatePodcastService createPodcastService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ServiceResponse<Object, Object> createPodcast(@RequestParam("imageName") String imageName,
                                                         @RequestParam("title") String title,
                                                         @RequestParam("subtitle") String subtitle,
                                                         @RequestParam("link") String link,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("keywords") String keywords,
                                                         @RequestParam("author") String author,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("feedname") String feedname) {

        return createPodcastService.createPodcast(imageName, title, subtitle, link, category, description, keywords, author, email,feedname);
    }
}
