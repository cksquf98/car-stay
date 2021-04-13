package com.carstay.demo.src.review;

import com.carstay.demo.config.BaseException;
import com.carstay.demo.config.BaseResponse;
import com.carstay.demo.src.review.model.*;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.carstay.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/app")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;


    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    /**
     * 가게 리뷰 조회 API
     * [GET] /app/main/stores/:storeId/reviews
     * @return BaseResponse<List < GetCartRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/main/stores/{storeId}/reviews") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetReviewRes>> getReviews(@PathVariable("storeId") int storeId) {
        try {
            // Get Review
            List<GetReviewRes> getReviewRes = reviewProvider.getReviews(storeId);
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 리뷰 작성 API
     * [POST] /app/users/:userIdx/review
     * @return BaseResponse<PostReviewRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/users/{userIdx}/review")
    public BaseResponse<PostReviewRes> createReview(@PathVariable int userIdx, @RequestBody PostReviewReq postReviewReq) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            System.out.println(userIdxByJwt + " " + userIdx);
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            // Post Review
            PostReviewRes postCartRes = reviewService.createReview(userIdx, postReviewReq);
            return new BaseResponse<>(postCartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}


