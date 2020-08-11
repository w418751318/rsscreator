package com.leftorright.rsscreator.dao;



import com.leftorright.rsscreator.entity.Members;
import com.leftorright.rsscreator.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MembersMapper {
    int saveMembers(Members members);

    int updateMembers(Members members);

    int deleteMembers(int id);

    List<Members> getAllMembers(PageBean pageBean);

    int  getAllMembersCount(PageBean pageBean);

    int deleteShowMember(@Param("id") int id);

    int saveShowMembers(@Param("id") int id,@Param("list") List<Integer> list);

    List<Members> getMembersByShowId(@Param("name") String name);

}
