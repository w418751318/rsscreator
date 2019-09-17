package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface CreatePodcastService {
    public ServiceResponse createPodcast(String imageName, String podcastName, String subtitle,
                                         String link, String category, String description,
                                         String keywords, String author, String email);
}
