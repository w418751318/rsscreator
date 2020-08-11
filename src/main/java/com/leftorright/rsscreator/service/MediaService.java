package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.Media;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface MediaService {
    public Result saveMedia(Media media);

    public Result deleteMedia(int  id);

    public Result getAllMedia(PageBean pageBean);

}
