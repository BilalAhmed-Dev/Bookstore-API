package com.backend.test.bookstore.daoimpl;
import com.backend.test.bookstore.dao.UserDao;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.exceptions.UserNotFoundException;
import com.backend.test.bookstore.repository.UserRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(String userId ) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not exists by Username or Email"));
    }



    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    };

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getByUsername(username).orElseThrow(() -> new UserNotFoundException("User not exists by Username or Email"));
    }
    @Override
    public User getByEmail(String userEmail) {
        return userRepository.getByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not exists by Username or Email"));
    }
}
