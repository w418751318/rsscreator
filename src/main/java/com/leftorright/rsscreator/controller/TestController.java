package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.utils.PinyinTool;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.CDATASection;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testuest(@RequestParam("input") String input) {

//        PinyinTool tool = new PinyinTool();
//        try {
//            System.out.println(tool.toPinYin(input));
//        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
//            badHanyuPinyinOutputFormatCombination.printStackTrace();
//        }
        Element newItem = DocumentHelper.createElement("item");
        Element description = DocumentHelper.createElement("description");
        description.addCDATA("这里是cdata的内容");
        newItem.add(description);

        System.out.println(newItem.asXML());
        return "hello！";
    }
}
