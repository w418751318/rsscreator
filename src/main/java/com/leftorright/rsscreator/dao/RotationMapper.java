package com.leftorright.rsscreator.dao;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Rotation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RotationMapper {

    int saveRotation(Rotation rotation);

    int updateRotation(Rotation rotation);

    int deleteRotation(@Param("id") int id);

    List<Rotation> getRotation(PageBean pageBean);

    int getRotationCount();

}
