package com.carstay.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int userIdx;
    private String userId;
    private String userName;
    private String birth;
    private String phoneNumber;
    private float xLocation;
    private float yLocation;

}
