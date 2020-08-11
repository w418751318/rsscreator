package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.dao.MembersMapper;
import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.Members;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.MembersService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MembersServiceImpl implements MembersService {
    private static final Logger logger = LoggerFactory.getLogger(MembersServiceImpl.class);

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Result saveMembers(Members members) {
        Result result = new Result();
        try {
            FileUtils.setFileUrl(members);
            if(members.getId() == null){
                membersMapper.saveMembers(members);
            }else{
                membersMapper.updateMembers(members);
            }
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception e) {
            logger.error("操作团队成员失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deleteMembers(int id) {
        Result result = new Result();
        try {
            int i = membersMapper.deleteMembers(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除团队成员失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllMembers(PageBean pageBean) {
        Result result = new Result();
        try {
            List<Members> allMembers = membersMapper.getAllMembers(pageBean);
            int count = membersMapper.getAllMembersCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",allMembers);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询所有团队成员：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

    @Override
    public Result getMembersByShowId(String name) {
        Result result = new Result();
        List<Members> membersByShowId = membersMapper.getMembersByShowId(name);
        result.setCode(0);
        result.setData(membersByShowId);
        return result;
    }

}
