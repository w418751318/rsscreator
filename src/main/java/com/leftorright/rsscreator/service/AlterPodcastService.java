package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface AlterPodcastService {
    public ServiceResponse alterPodcastItem(String id, String podcastname, String season, String episode, String newdata, String feedStr);
}
