package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UpdatePodcastListService {
    public ServiceResponse updatePodcastList(String uploadedPodcastName,String title,String shownotes);
}
