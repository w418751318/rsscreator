package com.leftorright.rsscreator.repository;

import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.entity.PodcastItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PodcastInfoRepository extends CrudRepository<PodcastInfo, Integer> {
    List<PodcastInfo> findAll();
    PodcastInfo findPodcastInfoByPodcastname(String podcastName);

    PodcastInfo findPodcastInfoByFeedname(String feedname);
}
