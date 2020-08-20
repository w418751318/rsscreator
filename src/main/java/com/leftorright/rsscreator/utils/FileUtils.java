package com.leftorright.rsscreator.utils;


import com.leftorright.rsscreator.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {

    
    private static String fileUrl;

    @Value("${file.url}")
    public  void setFileUrl(String fileUrl) {
        FileUtils.fileUrl = fileUrl;
    }

    public static String getFileUrl(){
        return fileUrl;
    }

    public static<T> void setFileUrl(T t){
        if(t==null){
            return;
        }
        String name = t.getClass().getName();
        if(name.contains("BlogShow")){
            BlogShow blogShow = (BlogShow) t;
            if(StringUtils.isNotBlank(blogShow.getWebbanner()) && !blogShow.getWebbanner().contains(fileUrl)){
                blogShow.setWebbanner(fileUrl + blogShow.getWebbanner());
            }
            if(StringUtils.isNotBlank(blogShow.getAppbanner()) && !blogShow.getAppbanner().contains(fileUrl)){
                blogShow.setAppbanner(fileUrl + blogShow.getAppbanner());
            }
            if(StringUtils.isNotBlank(blogShow.getBanner()) && !blogShow.getBanner().contains(fileUrl)){
                blogShow.setBanner(fileUrl + blogShow.getBanner());
            }
        }else if (name.contains("Brand")){
            Brand brand = (Brand) t;
            if(StringUtils.isNotBlank(brand.getLogo()) && !brand.getLogo().contains(fileUrl)){
                brand.setLogo(fileUrl + brand.getLogo());
            }
        }else if(name.contains("Media")){
            Media media = (Media) t;
            if(StringUtils.isNotBlank(media.getLogo()) && !media.getLogo().contains(fileUrl)){
                media.setLogo(fileUrl + media.getLogo());
            }
        }
        else if(name.contains("Members")){
            Members members = (Members) t;
            if(StringUtils.isNotBlank(members.getPicture()) && !members.getPicture().contains(fileUrl)){
                members.setPicture(fileUrl + members.getPicture());
            }
        }
        else if(name.contains("PodcastInfo")){
            PodcastInfo podcastInfo = (PodcastInfo) t;
            if(StringUtils.isNotBlank(podcastInfo.getImage()) && !podcastInfo.getImage().contains(fileUrl)){
                podcastInfo.setImage(fileUrl + podcastInfo.getImage());
            }
        }
        else if(name.contains("Channel")){
            Channel channel = (Channel) t;
            if(StringUtils.isNotBlank(channel.getLogo()) && !channel.getLogo().contains(fileUrl)){
                channel.setLogo(fileUrl + channel.getLogo());
            }
        }
        else if(name.contains("Rotation")){
            Rotation rotation = (Rotation) t;
            if(StringUtils.isNotBlank(rotation.getBanner()) && !rotation.getBanner().contains(fileUrl)){
                rotation.setBanner(fileUrl + rotation.getBanner());
            }
        }
        else if(name.contains("PastCases")){
            PastCases pastCases = (PastCases) t;
            if(StringUtils.isNotBlank(pastCases.getLogo()) && !pastCases.getLogo().contains(fileUrl)){
                pastCases.setLogo(fileUrl + pastCases.getLogo());
            }
        }
    }


}
