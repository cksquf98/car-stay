package com.carstay.demo.src.review;


import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.review.model.*;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.carstay.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;


    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;

    }

    //POST
   // @Transactional
    public PostReviewRes createReview(String userId, PostReviewReq postReviewReq) throws BaseException {

            try{
                int reviewId = reviewDao.createReview(userId, postReviewReq);
                return new PostReviewRes(reviewId);
            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }

    }

    //Delete
    // @Transactional
    public DeleteReviewRes deleteReview(int reviewNum, String userId) throws BaseException {

        try{
            int reviewId = reviewDao.deleteReview(reviewNum, userId);
            return new DeleteReviewRes(reviewId, userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
