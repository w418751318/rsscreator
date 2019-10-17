package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface AlterPodcastService {
    public ServiceResponse alterPodcastItem(String id, String podcastname, String newdata, String feedStr);
}
