package com.backend.test.bookstore.service;

import com.backend.test.bookstore.dto.NewUserDTO;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.utils.msgutils.Msg;

import java.util.List;

public interface UserService {
    User checkUser(String username,String password);
    List<User> getAllUsers();

    Msg banUser(Integer userId);

    Msg unBanUser(Integer userId);

    Msg checkUsernameDup(String username);

    Msg addUser(NewUserDTO newUserDTO);
}
