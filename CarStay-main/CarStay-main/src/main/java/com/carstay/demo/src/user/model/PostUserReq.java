package com.carstay.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    public PostUserReq() {}
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private int birth;
    private String authCode;
}
