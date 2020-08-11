package com.leftorright.rsscreator.dao;


import com.leftorright.rsscreator.entity.Media;
import com.leftorright.rsscreator.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MediaMapper {
    int saveMedia(Media media);
    
    int updateMedia(Media media);

    int deleteMedia(@Param("id") int id);

    List<Media> getAllMedia(PageBean pageBean);

    int getAllMediaCount();

}
