package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.BaseResponse;

public interface UpdatePodcastInfoService {
    public BaseResponse updatePodcastInfo(String id, String feedName, String podcastName, String podcastNameOld, String description,
                                          String author, String firstCategoryCode, String secondCategoryCode,
                                          String subtitle, String keywords, String link, String email);
}