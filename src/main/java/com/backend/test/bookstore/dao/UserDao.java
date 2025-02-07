package com.backend.test.bookstore.dao;

import com.backend.test.bookstore.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username, String password);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserByName(String username);
}
