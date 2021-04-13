package com.carstay.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetJWTRes {
    private int userIdx;
    private String userName;
    private String phoneNumber;
}
