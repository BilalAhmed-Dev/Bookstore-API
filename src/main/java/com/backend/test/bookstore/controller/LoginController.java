package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.constant.Constant;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.service.UserService;
import com.backend.test.bookstore.utils.msgutils.Msg;
import com.backend.test.bookstore.utils.msgutils.MsgCode;
import com.backend.test.bookstore.utils.msgutils.MsgUtil;
import com.backend.test.bookstore.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/auth") // Base path for user-related endpoints
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Msg login(@RequestBody Map<String, String> params){
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        User user = userService.checkUser(username, password);
        if(user != null){
            JSONObject obj = new JSONObject();
            obj.put(Constant.USER_ID, user.getUserId());
            obj.put(Constant.USERNAME, user.getUsername());
            obj.put(Constant.USER_TYPE, user.getUserType());
            SessionUtil.setSession(obj);

            JSONObject data = JSONObject.fromObject(user);
            data.remove(Constant.PASSWORD);

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
        }
        else{
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @PostMapping("/logout")
    public Msg logout(){
        System.out.println("logout");
        Boolean status = SessionUtil.removeSession();

        if(status){
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }

    @GetMapping("/checkSession")
    public Msg checkSession(){
        JSONObject auth = SessionUtil.getAuth();

        if(auth == null){
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        }
        else{
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }

}
