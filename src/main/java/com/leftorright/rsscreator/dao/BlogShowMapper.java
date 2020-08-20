package com.leftorright.rsscreator.dao;

import com.leftorright.rsscreator.entity.BlogShow;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.PodcastInfo;
import com.leftorright.rsscreator.entity.PodcastItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogShowMapper {
    int saveBlogShow(BlogShow blogShow);


    int savePodcast(PodcastItem podcastItem);


    int updateBlogShow(BlogShow blogShow);

    int deleteBlogShow(@Param("id") int id);

    List<BlogShow>  getAllBlogShow(PageBean pageBean);

    int getAllCount(PageBean pageBean);


    List<PodcastInfo> getAllPodcast(PageBean pageBean);

    int getAllPodcastCount(PageBean pageBean);

    BlogShow getBlowShowByName(@Param("name") String name);


    BlogShow selectByPodcastid(@Param("id") int podcastid);

}
