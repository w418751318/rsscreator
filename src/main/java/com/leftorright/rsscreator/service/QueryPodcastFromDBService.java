package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface QueryPodcastFromDBService {
    public ServiceResponse queryPodcast();

    public ServiceResponse queryPodcastFeed(String podcastName);

    public ServiceResponse queryPodcastItems(String podcastName);
}
