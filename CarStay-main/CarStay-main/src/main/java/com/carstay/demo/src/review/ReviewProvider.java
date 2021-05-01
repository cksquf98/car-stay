package com.carstay.demo.src.review;


import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.review.model.GetDetailReviewRes;
import com.carstay.demo.src.review.model.GetReviewRes;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.carstay.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.carstay.demo.config.BaseResponseStatus.NO_CART;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ReviewProvider {

    private final ReviewDao reviewDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }

    public List<GetReviewRes> getReviews() throws BaseException {
        try {
            System.out.println("provider1");
            List<GetReviewRes> getReviewRes = reviewDao.getReviews();
            System.out.println("provider" + getReviewRes);
            return getReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public List<GetReviewRes> getReviewsBySpot(int spotId) throws BaseException {
        try {
            List<GetReviewRes> getReviewRes = reviewDao.getReviewsBySpot(spotId);
            return getReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public List<GetDetailReviewRes> getDetailReview(int reviewNum) throws BaseException {
        try {
            List<GetDetailReviewRes> getDetailReviewRes = reviewDao.getDetailReview(reviewNum);
            return getDetailReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
