package com.carstay.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCertReq {
    public PostCertReq() {}
    private String certNum;
}
