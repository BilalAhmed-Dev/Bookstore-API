package com.backend.test.bookstore.controller;
import com.backend.test.bookstore.service.UserService;
import com.backend.test.bookstore.utils.Msg;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") // Base path for user-related endpoints
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/checkDup")
    public Msg checkDup(@RequestParam("username") String userName){
        System.out.println("checkDup");
        return userService.checkUsernameDup(userName);
    }


}
