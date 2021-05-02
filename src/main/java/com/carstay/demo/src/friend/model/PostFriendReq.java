package com.carstay.demo.src.friend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostFriendReq {
    public PostFriendReq() {}
    private String userId;
    private String friendId;
}
