package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface CreatePodcastService {
//    imageName, title, subtitle, link, firstCategoryCode, secondCategoryCode, description, keywords, author, email, feedname
    public ServiceResponse createPodcast(String imageName, String podcastName, String subtitle,
                                         String link, String firstCategoryCode, String secondCategoryCode, String description,
                                         String keywords, String author, String email, String feedname);
}
