package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.Common;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface CommonService {
    public Result saveCommon(Common common);

    public Result deleteCommon(int id);

    public Result getCommonByType(PageBean pageBean);

}
