package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    public GetReviewRes() {}
    private int ReviewNum;
    private String Title;
    private String Content;
    private String ReviewImage;
    private String ReviewGrade;
    private String WriterId;
    private String ReviewTime;
    private String Spot;
}
