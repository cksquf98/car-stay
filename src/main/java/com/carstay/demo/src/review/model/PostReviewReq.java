package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
    public PostReviewReq() {}
    private String title;
    private String content;
    private String reviewImage;
    private int reviewGrade;
    private String spot;
}
