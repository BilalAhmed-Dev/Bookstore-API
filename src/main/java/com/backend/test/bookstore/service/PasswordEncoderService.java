package com.backend.test.bookstore.service;


public interface PasswordEncoderService {


    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);

}
