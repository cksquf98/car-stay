package com.carstay.demo.src.review;



import com.carstay.demo.src.review.model.GetReviewRes;
import com.carstay.demo.src.review.model.PostReviewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetReviewRes> getReviews(int storeId) {
        List<GetReviewRes> getReviewRes = new ArrayList<>();
        int cnt = this.jdbcTemplate.queryForObject("select count(*) from Review where storeId = 1 and status=\"Y\"", int.class);
        List<Integer> reviewId = this.jdbcTemplate.queryForList("select reviewId from Review where storeId = ? and status=\"Y\"", int.class, storeId);
        System.out.println("cnt" + cnt);
        System.out.println("reviewId" + reviewId);
        for(int i=0; i<cnt; i++)
        {
//            int imageCnt = this.jdbcTemplate.queryForObject("select count(*) from ReviewImage where reviewId = 1", int.class, reviewId.get(i));
            List<Integer> reviewImageId = this.jdbcTemplate.queryForList("select reviewImageId from ReviewImage where reviewId =?", int.class, reviewId.get(i));
            GetReviewRes getReviewRes1 = new GetReviewRes();
            getReviewRes1.setReviewId(reviewId.get(i));
            getReviewRes1.setUserName(this.jdbcTemplate.queryForObject("select userName from Review join User on User.userIdx=Review.userIdx where reviewId=?", String.class, reviewId.get(i)));
            getReviewRes1.setStoreId(this.jdbcTemplate.queryForObject("select storeId from Review where reviewId = ?", int.class, reviewId.get(i)));
            getReviewRes1.setStar(this.jdbcTemplate.queryForObject("select star from Review where reviewId = ?", int.class, reviewId.get(i)));
            getReviewRes1.setReviewContent(this.jdbcTemplate.queryForObject("select reviewContent from Review where reviewId = ?", String.class, reviewId.get(i)));
            getReviewRes1.setHasPhoto(this.jdbcTemplate.queryForObject("select hasPhoto from Review where reviewId = ?", String.class, reviewId.get(i)));
            getReviewRes1.setReviewImage(this.jdbcTemplate.queryForList("select imageUrl from ReviewImage where reviewId =?", String.class, reviewId.get(i)));
            getReviewRes1.setCreatedAt(this.jdbcTemplate.queryForObject("select createdAt from Review where reviewId = ?", String.class, reviewId.get(i)));
            getReviewRes1.setUpdatedAt(this.jdbcTemplate.queryForObject("select updatedAt from Review where reviewId = ?", String.class, reviewId.get(i)));

            if(getReviewRes1.getReviewImage().isEmpty()) {

            }
            getReviewRes.add(getReviewRes1);
        }
        return getReviewRes;
    }

    public int createReview(int userIdx, PostReviewReq postReviewReq){
        String createReviewQuery = "INSERT INTO Review (userIdx, storeId, star, reviewContent, hasPhoto) VALUES (?, ?, ?, ?, ?)";
        Object[] createQueryParams = new Object[]{userIdx, postReviewReq.getStoreId(), postReviewReq.getStar(), postReviewReq.getReviewContent(), postReviewReq.getHasPhoto()};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}



