package com.leftorright.rsscreator.dao;


import com.leftorright.rsscreator.entity.Common;
import com.leftorright.rsscreator.entity.PageBean;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

    int saveCommon(Common common);

    int updateCommon(Common common);

    int deleteById(@Param("id") int id);

    List<Common> getAllCommon(PageBean pageBean);

    int getAllCommonCount(PageBean pageBean);

}
