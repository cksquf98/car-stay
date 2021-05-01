package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteCommentRes {
    public DeleteCommentRes() {}
    private int commentNum;
    private String writerId;
}
