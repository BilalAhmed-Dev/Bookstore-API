package com.backend.test.bookstore.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthenticationResponseDTO {
    private String accessToken;

    private String refreshToken;

    private UserInfoDTO userInfo;

    public AuthenticationResponseDTO(String accessToken, String refreshToken, UserInfoDTO userInfo) {
        this.accessToken = accessToken;
        this.userInfo = userInfo;
        this.refreshToken = refreshToken;
    }

}
