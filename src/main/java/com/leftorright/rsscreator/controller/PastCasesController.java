package com.leftorright.rsscreator.controller;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.PastCases;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.service.PastCasesService;
import com.leftorright.rsscreator.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("past")
public class PastCasesController {
    @Autowired
    private PastCasesService pastCasesService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result savePastCases(@RequestBody PastCases pastCases){
        return pastCasesService.savePast(pastCases);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Result deletePastCases(@PathVariable("id") int id){
        return pastCasesService.deletePast(id);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Result getAllPastCases(HttpServletRequest request){
        PageBean pageBean = JsonUtil.jsonToPage((request));
        return pastCasesService.getAllPast(pageBean);
    }

}
