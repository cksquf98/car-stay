package com.carstay.demo.src.review;



import com.carstay.demo.src.review.model.GetReviewRes;
import com.carstay.demo.src.review.model.PostReviewReq;
import com.carstay.demo.src.user.model.GetUserRes;
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

    public List<GetReviewRes> getReviews() {
        String getUsersQuery = "select ReviewNum, Title, Content, ReviewImage, ReviewGrade, WriterId, ReviewTime, Spot from Review";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetReviewRes(
                        rs.getInt("ReviewNum"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("ReviewImage"),
                        rs.getString("ReviewGrade"),
                        rs.getString("WriterId"),
                        rs.getString("ReviewTime"),
                        rs.getString("Spot"))
        );
    }

    public List<GetReviewRes> getReviewsBySpot(int spotId) {
        String getUsersQuery = "select ReviewNum, Title, Content, ReviewImage, ReviewGrade, WriterId, ReviewTime, Spot from Review where spot = ?";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetReviewRes(
                        rs.getInt("ReviewNum"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("ReviewImage"),
                        rs.getString("ReviewGrade"),
                        rs.getString("WriterId"),
                        rs.getString("ReviewTime"),
                        rs.getString("Spot")),
                spotId);
    }

    public int createReview(String userId, PostReviewReq postReviewReq){
        String createReviewQuery = "insert into Review (Title, Content, ReviewImage, ReviewGrade, WriterId, Spot) values (?, ?, ?, ?, ?, ?)";
        Object[] createQueryParams = new Object[]{postReviewReq.getTitle(), postReviewReq.getContent(), postReviewReq.getReviewImage(), postReviewReq.getReviewGrade(), userId, postReviewReq.getSpot()};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        System.out.println(lastInsertIdQuery);
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}



