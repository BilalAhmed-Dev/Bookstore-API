package com.backend.test.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoDTO {
    Long id;
    String username;
    String email;
    String userType;
}
