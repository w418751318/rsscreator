package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.PastCases;
import com.leftorright.rsscreator.entity.Result;

public interface PastCasesService {
    public Result savePast(PastCases pastCases);

    public Result deletePast(int  id);

    public Result getAllPast(PageBean pageBean);

}
