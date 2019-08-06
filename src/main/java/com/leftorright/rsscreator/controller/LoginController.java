package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.domain.response.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ServiceResponse<Object, Object> login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        String responseCode = "";
        String responseMsg = "";
        String uid = "";
        String username = "";
        String[] permissions = new String[10];

        if ("admin".equals(userName) && "admin".equals(passWord)){
            responseCode = "0000";//成功
            responseMsg = "success";
            uid = "11111";
            username = userName;
            permissions[0] = "Dashboard";
        }
        return jsonResult(responseCode, responseMsg, uid,username,permissions);
    }
    private static ServiceResponse<Object, Object> jsonResult(String responseCode, String responseMsg, String uid, String username,String[] permissions) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(responseCode);
        serviceResponse.setMsg(responseMsg);
        serviceResponse.setUid(uid);
        serviceResponse.setUsername(username);
        serviceResponse.setPermissions(permissions);
        return serviceResponse;
    }
}
