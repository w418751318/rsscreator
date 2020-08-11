package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.Channel;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface ChannelService {

    public Result saveChannel(Channel channel);

    public Result deleteChannel(int  id);

    public Result getAllChannel(PageBean pageBean);

    public Result getChannelsByShowId(String name,String type);

}
