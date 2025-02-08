package com.backend.test.bookstore.dao;

import com.backend.test.bookstore.entity.User;

import java.util.List;

public interface UserDao {
    User getUserById(Integer userId);
    User saveUser(User user);
    User getUserByName(String username);
    User getByEmail(String userEmail);

}
