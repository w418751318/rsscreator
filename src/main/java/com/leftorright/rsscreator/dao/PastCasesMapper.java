package com.leftorright.rsscreator.dao;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.PastCases;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.entity.PodcastItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PastCasesMapper {

    int savePastCases(PastCases pastCases);

    int deletePastCases(@Param("id") int id);

    List<PastCases> getAllPastCases(PageBean pageBean);

    int getAllCount(PageBean pageBean);

    PodcastInfo findPodcastInfoByFeedname(@Param("feedname") String feedname);

    List<PodcastItem> findPodcastItemByPodcastname(@Param("podcastname") String podcastname);
}
