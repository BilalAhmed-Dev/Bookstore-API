package com.backend.test.bookstore.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthenticationResponse {
    private String accessToken;

    private String refreshToken;

    private UserInfo userInfo;

    public AuthenticationResponse(String accessToken, String refreshToken, UserInfo userInfo) {
        this.accessToken = accessToken;
        this.userInfo = userInfo;
        this.refreshToken = refreshToken;
    }

}
