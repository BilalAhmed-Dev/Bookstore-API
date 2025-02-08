package com.backend.test.bookstore.controller;
import com.backend.test.bookstore.constant.Constant;
import com.backend.test.bookstore.dto.NewUserDTO;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.service.UserService;
import com.backend.test.bookstore.utils.msgutils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users") // Base path for user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/checkUser")
    public User checkUser(@RequestBody Map<String, String> params){
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        return userService.checkUser(username, password);
    }

    @GetMapping("/getUserList")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }





    @GetMapping("/checkDup")
    public Msg checkDup(@RequestParam("username") String userName){
        System.out.println("checkDup");
        return userService.checkUsernameDup(userName);
    }


}
