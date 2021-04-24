package com.carstay.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
}
