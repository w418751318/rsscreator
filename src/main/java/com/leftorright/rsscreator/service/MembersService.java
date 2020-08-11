package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.Members;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface MembersService {
    public Result saveMembers(Members members);

    public Result deleteMembers(int  id);

    public Result getAllMembers(PageBean pageBean);

    public Result getMembersByShowId(String name);

}
