package com.leftorright.rsscreator.service.Imp;

import com.leftorright.rsscreator.dao.BlogShowMapper;

import com.leftorright.rsscreator.entity.BlogShow;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.BlogShowService;

import com.leftorright.rsscreator.dao.ChannelMapper;
import com.leftorright.rsscreator.dao.MembersMapper;
import com.leftorright.rsscreator.entity.*;
import com.leftorright.rsscreator.service.BlogShowService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogShowServiceImpl implements BlogShowService {
    private static final Logger logger = LoggerFactory.getLogger(BlogShowServiceImpl.class);

    @Autowired
    private BlogShowMapper blogShowMapper;


    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private MembersMapper membersMapper;


    @Override
    public Result saveBlogShow(BlogShow blowShow) {
        Result result = new Result();
        try {

            if(blowShow.getId() == null){
                blogShowMapper.saveBlogShow(blowShow);
            }else{
                blogShowMapper.updateBlogShow(blowShow);
            }

            FileUtils.setFileUrl(blowShow);
            String creator = blowShow.getCreator();
            if(blowShow.getId() == null){
                if(blowShow.getPodcastid() == null){
                    PodcastItem  podcastItem = new PodcastItem();
                    podcastItem.setPodcastname(blowShow.getName());
                    int id = blogShowMapper.savePodcast(podcastItem);
                    blowShow.setPodcastid(podcastItem.getId());
                }
                int i = blogShowMapper.saveBlogShow(blowShow);
            }else{
                blogShowMapper.updateBlogShow(blowShow);
                channelMapper.deleteByShowId(blowShow.getId());
                membersMapper.deleteShowMember(blowShow.getId());
            }

            if(StringUtils.isNotBlank(creator)){
                String[] split = creator.split(",");
                List<Integer> list = new ArrayList<>();
                for (String s : split) {
                    list.add(Integer.valueOf(s));
                }
                membersMapper.saveShowMembers(blowShow.getId(),list);
            }
            //收听标记

            List<Channel> channels = blowShow.getChannels();
            if(channels != null && channels.size() > 0 ){
                for (Channel channel : channels) {
                    FileUtils.setFileUrl(channel);
                    channel.setShowid(blowShow.getId());
                }
                channelMapper.saveChannelList(channels,"1");
            }

            //收听渠道
            List<Channel> channelList = blowShow.getChannelList();
            if(channelList != null && channelList.size() > 0 ){
                for (Channel channel : channelList) {
                    FileUtils.setFileUrl(channel);
                    channel.setShowid(blowShow.getId());
                }
                channelMapper.saveChannelList(channelList,"2");
            }




            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作博客失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteBlogShow(int id) {
        Result result = new Result();
        try {
            int i = blogShowMapper.deleteBlogShow(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除博客失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllBlogShow(PageBean pageBean) {
        Result result = new Result();
        try {
            List<BlogShow> allBlowShow = blogShowMapper.getAllBlogShow(pageBean);
            int count = blogShowMapper.getAllCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",allBlowShow);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有博客：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

    @Override
    public Result getAllPodCast(PageBean pageBean) {
        Result result = new Result();
        try {
            List<PodcastInfo> allPodcast = blogShowMapper.getAllPodcast(pageBean);
            int count = blogShowMapper.getAllPodcastCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",allPodcast);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有博客：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

    @Override
    public Result getBlowByName(String name) {
        Result result = new Result();
        try {
            BlogShow blow = blogShowMapper.getBlowShowByName(name);
            result.setCode(0);
            result.setData(blow);
        } catch (Exception e) {
            logger.error("查询单独博客：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }
}
