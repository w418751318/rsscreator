package com.leftorright.rsscreator.repository;

import com.leftorright.rsscreator.entity.PodcastItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PodcastItemRepository extends CrudRepository<PodcastItem, Integer> {
    List<PodcastItem> findAll();

    List<PodcastItem> findPodcastItemByPodcastname(String podcastname);

}
