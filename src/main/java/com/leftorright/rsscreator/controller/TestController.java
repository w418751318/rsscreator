package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testuest(@RequestParam("input") String input) {

        PinyinTool tool = new PinyinTool();
        try {
            System.out.println(tool.toPinYin(input));
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return "hello！";
    }
}
