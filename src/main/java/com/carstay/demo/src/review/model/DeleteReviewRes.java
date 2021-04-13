package com.carstay.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteReviewRes {
    public DeleteReviewRes() {}

    private int userIdx;
    private int cartItemId;
}
