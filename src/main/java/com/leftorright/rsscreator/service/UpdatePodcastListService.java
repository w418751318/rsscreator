package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UpdatePodcastListService {
    public ServiceResponse updatePodcastList(String podcastName, String uploadedPodcastName, String title, String shownotes, String episode, String duration, String enclosureType, String length, String season, String episodeType, String feedStr, String pubDelayHours);
}
