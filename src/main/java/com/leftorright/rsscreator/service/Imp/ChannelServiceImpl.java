package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.dao.ChannelMapper;
import com.leftorright.rsscreator.entity.*;
import com.leftorright.rsscreator.service.ChannelService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChannelServiceImpl implements ChannelService{
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public Result saveChannel(Channel channel) {
        Result result = new Result();
        try {
            channel.setType("1");
            FileUtils.setFileUrl(channel);
            if(channel.getId() == null){
                channelMapper.saveChannel(channel);
            }else{
                channelMapper.updateChannel(channel);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作频道失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteChannel(int id) {
        Result result = new Result();
        try {
            int i = channelMapper.deleteById(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除频道失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllChannel(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Channel> list = channelMapper.getAllChannel(pageBean);
            int count = channelMapper.getAllChannelCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",list);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有频道：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

    @Override
    public Result getChannelsByShowId(String name,String type) {
        Result result = new Result();
        List<Channel> channelsByShowId = channelMapper.getChannelsByShowId(name,type);
        result.setCode(0);
        result.setData(channelsByShowId);
        return result;
    }

}
