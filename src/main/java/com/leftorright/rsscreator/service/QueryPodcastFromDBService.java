package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.BaseResponse;
import com.leftorright.rsscreator.domain.response.ServiceResponse;

public interface QueryPodcastFromDBService {
    //
    public ServiceResponse queryPodcast();
    //查询播客信息，例如：播客名，播客简介，播客作者等
    public BaseResponse queryPodcastInfo(String podcastName);

    public ServiceResponse queryPodcastItems(String podcastName);
}
