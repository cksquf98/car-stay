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
    private int reviewId;
    private String userName;
    private int storeId;
    private int star;
    private String reviewContent;
    private String hasPhoto;
    private List<String> reviewImage;
    private String createdAt;
    private String updatedAt;
}
