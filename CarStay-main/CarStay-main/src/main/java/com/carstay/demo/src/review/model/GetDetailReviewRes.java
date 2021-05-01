package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDetailReviewRes {
    public GetDetailReviewRes() {}
    private int ReviewNum;
    private String Title;
    private String Content;
    private String ReviewImage;
    private String ReviewGrade;
    private String WriterId;
    private String ReviewTime;
    private String Spot;
    private String CommentContent;
    private String CommentWriter;
    private String CommentTime;

}
