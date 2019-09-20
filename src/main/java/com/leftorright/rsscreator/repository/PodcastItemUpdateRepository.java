package com.leftorright.rsscreator.repository;

import com.leftorright.rsscreator.entity.PodcastItem;
import org.springframework.data.repository.CrudRepository;

public interface PodcastItemUpdateRepository extends CrudRepository<PodcastItem, Integer> {
}
