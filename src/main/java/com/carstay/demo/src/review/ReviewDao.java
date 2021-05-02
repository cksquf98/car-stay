package com.carstay.demo.src.review;



import com.carstay.demo.src.review.model.*;
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
    public List<GetDetailReviewRes> getDetailReview(int reviewNum) {
        String getUsersQuery = "select Review.ReviewNum, Title, Content, ReviewImage, ReviewGrade,WriterId, ReviewTime, Spot, CommentContent, CommentWriter, CommentTime from Review left join Comment on Review.ReviewNum= Comment.ReviewNum where Review.ReviewNum= ?";

        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetDetailReviewRes(
                        rs.getInt("ReviewNum"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("ReviewImage"),
                        rs.getString("ReviewGrade"),
                        rs.getString("WriterId"),
                        rs.getString("ReviewTime"),
                        rs.getString("Spot"),
                        rs.getString("CommentContent"),
                        rs.getString("CommentWriter"),
                        rs.getString("CommentTime")),reviewNum);
    }

    public int createReview(String userId, PostReviewReq postReviewReq){
        String createReviewQuery = "insert into Review (Title, Content, ReviewImage, ReviewGrade, WriterId, Spot) values (?, ?, ?, ?, ?, ?)";
        Object[] createQueryParams = new Object[]{postReviewReq.getTitle(), postReviewReq.getContent(), postReviewReq.getReviewImage(), postReviewReq.getReviewGrade(), userId, postReviewReq.getSpot()};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int deleteReview(int reviewNum, String userId){
        if(checkReview(reviewNum) == 0) {
            return 0;
        }

        String deleteReviewQuery = "delete from Review where reviewNum=? and writerId=?";
        Object[] deleteQueryParams = new Object[]{reviewNum, userId};

        this.jdbcTemplate.update(deleteReviewQuery,deleteQueryParams);
        return reviewNum;
    }

    public int createComment(int reviewNum, String userId, PostCommentReq postCommentReq){
        if(checkReview(reviewNum) == 0) {
            return 0;
        }

        String createCommentQuery = "insert into Comment (reviewNum, commentContent, commentWriter) values (?, ?, ?)";
        Object[] createCommentParams = new Object[]{reviewNum, postCommentReq.getCommentContent(), userId};
        this.jdbcTemplate.update(createCommentQuery, createCommentParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkReview(int reviewNum){
        String checkReviewQuery = "select exists(select reviewNum from Review where reviewNum = ?)";
        int checkReviewParams = reviewNum;
        return this.jdbcTemplate.queryForObject(checkReviewQuery,
                int.class,
                checkReviewParams);

    }

    public int deleteComment(int reviewNum, String userId, int commentNum){
        if(checkComment(commentNum) == 0) {
            return 0;
        }

        String delteCommentQuery = "delete from Comment where reviewNum=? and commentWriter=? and commentNum =?";
        Object[] deleteCommentParams = new Object[]{reviewNum, userId, commentNum};

        this.jdbcTemplate.update(delteCommentQuery,deleteCommentParams);
        return reviewNum;
    }

    public int checkComment(int commentNum){
        String checkCommentQuery = "select exists(select commentNum from Comment where commentNum = ?)";
        int checkCommentParams = commentNum;
        return this.jdbcTemplate.queryForObject(checkCommentQuery,
                int.class,
                checkCommentParams);

    }
}



