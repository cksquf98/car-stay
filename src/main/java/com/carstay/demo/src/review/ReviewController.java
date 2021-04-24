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
     * 차박 리뷰 전체 조회 API
     * [GET] /app/reviews
     * @return BaseResponse<List < GetCartRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/reviews") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetReviewRes>> getReviews() {
        try {
            // Get Review
            List<GetReviewRes> getReviewRes = reviewProvider.getReviews();
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 차박 리뷰 장소별 조회 API
     * [GET] /app/reviews/spot/:spotId
     * @return BaseResponse<List < GetCartRes>>
     */
    /**
     * spotId int 형으로 받는게 편해서 db 수정해야될듯 (spot테이블 만들어서 지역별로 코드값 주는 형식,,?)
     * 그래서 아직!! 안돌아감!!!!
     */
    //Query String
    @ResponseBody
    @GetMapping("/reviews/spot/{spotId}") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetReviewRes>> getReviewsBySpot(@PathVariable("spotId") int spotId) {
        try {
            // Get Review
            List<GetReviewRes> getReviewRes = reviewProvider.getReviewsBySpot(spotId);
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 리뷰 작성 API
     * [POST] /app/reviews/users/:userId
     * @return BaseResponse<PostReviewRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/reviews/users/{userId}")
    public BaseResponse<PostReviewRes> createReview(@PathVariable String userId, @RequestBody PostReviewReq postReviewReq) {
        try{
            /**
             * jwt는 나중에 하는게 편하겄다
             */
//            //jwt에서 idx 추출.
//            int userIdxByJwt = jwtService.getUserIdx();
//            System.out.println(userIdxByJwt + " " + userIdx);
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }


            // Post Review
            PostReviewRes postReviewRes = reviewService.createReview(userId, postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}


