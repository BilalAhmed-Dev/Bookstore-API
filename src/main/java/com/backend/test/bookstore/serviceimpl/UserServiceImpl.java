package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.constant.Constant;
import com.backend.test.bookstore.dao.UserDao;
import com.backend.test.bookstore.dto.NewUserDTO;

import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.service.PasswordEncoderService;
import com.backend.test.bookstore.service.UserService;
import com.backend.test.bookstore.utils.msgutils.Msg;
import com.backend.test.bookstore.utils.msgutils.MsgUtil;
import com.backend.test.bookstore.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Override
    public User checkUser(String username, String password){
        User user = userDao.getUserByName(username);

        boolean isMatch = passwordEncoderService.matches(password, user.getPassword());
        if (!isMatch){
            throw new RuntimeException("Username or password is incorrect");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        JSONObject auth = SessionUtil.getAuth();
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin")){
            return userDao.getAllUsers();
        }
        return null;
    }

    @Override
    public Msg banUser(Integer userId) {
        JSONObject auth = SessionUtil.getAuth();
        User user = userDao.getUserById(userId);
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin") && user.getUserType().equals("customer")){
            user.setUserType("ban");
            userDao.saveUser(user);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_BAN);
        }
        else {
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_BAN);
        }
    }

    @Override
    public Msg unBanUser(Integer userId) {
        JSONObject auth = SessionUtil.getAuth();
        User user = userDao.getUserById(userId);
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin") && user.getUserType().equals("ban")){
            user.setUserType("customer");
            userDao.saveUser(user);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_UNBAN);
        }
        else {
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_UNBAN);
        }
    }

    @Override
    public Msg checkUsernameDup(String username) {
        User user=userDao.getUserByName(username);
        if(user!=null){
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_NAMEDUP);
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_NAMEVALID);
    }

    @Override
    public Msg addUser(NewUserDTO newUserDTO) {
        // Check if the username already exists
        User user = userDao.getUserByName(newUserDTO.getUsername());
        if (user != null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, MsgUtil.ERROR_NAMEDUP);
        }

        // Create a new User object
        User newUser = new User();
        newUser.setUserType("customer");
        newUser.setEmail(newUserDTO.getEmail());

        // Hash the password before saving
        String hashedPassword = passwordEncoderService.encode(newUserDTO.getPassword());
        newUser.setPassword(hashedPassword);

        newUser.setUsername(newUserDTO.getUsername());

        // Save the user to the database
        userDao.saveUser(newUser);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_REGISTER);
    }
}
