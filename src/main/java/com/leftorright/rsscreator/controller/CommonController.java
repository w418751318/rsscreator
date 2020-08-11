package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.entity.Common;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.CommonService;
import com.leftorright.rsscreator.ueditor.ActionEnter;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result saveCommon(@RequestBody Common common){
        return commonService.saveCommon(common);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deleteCommon(@PathVariable("id") int id){
        return commonService.deleteCommon(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllCommon(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage(request);
        return commonService.getCommonByType(pageBean);
    }


    @RequestMapping(value = "/ueditor/exec")
    @ResponseBody
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException,JSONException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        return new ActionEnter( request, rootPath ).exec();
    }

}
