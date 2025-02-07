package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.service.PasswordEncoderService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {
    // Cost factor (4-31, default 10)
    private static final int LOG_ROUNDS = 12;

    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(LOG_ROUNDS));
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
