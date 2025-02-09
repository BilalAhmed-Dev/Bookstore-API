package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.UserDao;

import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }




    public  User getUserInfo(String userId ) {
       return userDao.getUserById(userId);
    }



}
