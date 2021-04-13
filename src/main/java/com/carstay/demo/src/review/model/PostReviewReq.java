package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
    public PostReviewReq() {}

    private int storeId;
    private int star;
    private String reviewContent;
    private String hasPhoto;

}
