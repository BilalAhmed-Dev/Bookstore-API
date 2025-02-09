package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.UserDao;

import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.service.UserService;
import com.backend.test.bookstore.utils.Msg;
import com.backend.test.bookstore.utils.MsgUtil;
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

    @Override
    public Msg checkUsernameDup(String username) {
        User user= userDao.getUserByName(username);
        if(user!=null){
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_NAMEDUP);
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_NAMEVALID);
    }


}
