package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.entity.BlogShow;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface BlogShowService {
    public Result saveBlogShow(BlogShow blowShow);

    public Result deleteBlogShow(int  id);

    public Result getAllBlogShow(PageBean pageBean);

    public Result getAllPodCast(PageBean pageBean);

    public Result getBlowByName(String name);
}
