package com.backend.test.bookstore.service;

import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.utils.Msg;

public interface UserService {
    User getUserInfo(Integer userId);

    Msg checkUsernameDup(String username);

}
