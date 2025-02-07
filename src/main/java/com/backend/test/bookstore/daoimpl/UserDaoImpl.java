package com.backend.test.bookstore.daoimpl;
import com.backend.test.bookstore.dao.UserDao;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username,String password){
        return userRepository.checkUser(username,password);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.getById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getByUsername(username);
    }
}
