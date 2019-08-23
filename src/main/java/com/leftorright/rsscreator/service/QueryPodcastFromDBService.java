package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface QueryPodcastFromDBService {
    public ServiceResponse queryPodcast();
    public ServiceResponse queryPodcastItems();
}
