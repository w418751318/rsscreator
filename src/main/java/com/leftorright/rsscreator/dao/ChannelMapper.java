package com.leftorright.rsscreator.dao;


import com.leftorright.rsscreator.entity.Channel;
import com.leftorright.rsscreator.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelMapper {

    int saveChannel(Channel channel);

    int saveChannelList(@Param("list")List<Channel> list,@Param("type") String type);

    int updateChannel(Channel channel);
    
    int deleteById(@Param("id") int id);

    int deleteByShowId(@Param("id") int showid);

    List<Channel> getAllChannel(PageBean pageBean);

    int  getAllChannelCount(PageBean pageBean);

    int deleteShowChannel(@Param("id") int id);

    int saveShowChannels(@Param("id") int id,@Param("list") List<Integer> list,@Param("type")String type);

    List<Channel> getChannelsByShowId(@Param("name") String name,@Param("type") String type);

    int deleteShowChannelByChannelId(@Param("id") int id);
}
