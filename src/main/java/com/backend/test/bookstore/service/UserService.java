package com.backend.test.bookstore.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.backend.test.bookstore.domain.entity.UserEntity;
import com.backend.test.bookstore.domain.model.UserInfo;
import com.backend.test.bookstore.domain.repository.UserRepository;
import com.backend.test.bookstore.exceptions.UserIsNotAdminException;
import com.backend.test.bookstore.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserInfo getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User ID '" + userId + "' not found"));

        return modelMapper.map(userEntity, UserInfo.class);
    }

    public List<UserInfo> getAllUsers(Long loggedInUserId) {
        UserEntity userEntity = getUserById(loggedInUserId);
        if (userEntity.isAdmin()) {
            return getAllUsers();
        }
        throw new UserIsNotAdminException("You do not have the ADMIN role to view this resource");
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }


    private List<UserInfo> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserInfo.class))
                .collect(Collectors.toList());
    }


}
