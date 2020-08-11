package com.leftorright.rsscreator.controller;



import com.leftorright.rsscreator.entity.Members;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.MembersService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveMembers(@RequestBody Members members){
        return membersService.saveMembers(members);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteMembers(@PathVariable("id") int id){
        return membersService.deleteMembers(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllMembers(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage((request));
        return membersService.getAllMembers(pageBean);
    }

    @RequestMapping(value = "getbyshow",method = RequestMethod.GET)
    public Result getMembersByShowId(@RequestParam("feedname") String feedname){
        return membersService.getMembersByShowId(feedname);
    }

}
