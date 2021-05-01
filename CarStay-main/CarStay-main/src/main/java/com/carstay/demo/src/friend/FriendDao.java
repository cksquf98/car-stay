package com.carstay.demo.src.friend;



import com.carstay.demo.src.friend.model.GetFriendRes;
import com.carstay.demo.src.friend.model.PostFriendReq;
import com.carstay.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendDao {

    private JdbcTemplate jdbcTemplate;

    public int createFriend(PostFriendReq postFriendReq) {
        System.out.println(postFriendReq.getUserId());
        String createReviewQuery = "insert into Friend (MyId,FriendId) values (?,?)";
        Object[] createQueryParams = new Object[]{postFriendReq.getUserId(),postFriendReq.getFriendId()};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetFriendRes> getFriends() {
        String getUsersQuery = "select FriendId from Friend where FriendAccept = 1";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetFriendRes(
                        rs.getString("FriendId")
                        )
        );
    }

    public int acceptFriend(PostFriendReq postFriendReq) {
        System.out.println(postFriendReq.getUserId());
        String createReviewQuery = "update Friend set FriendAccept = ? where MyId = ?";
        Object[] createQueryParams = new Object[]{1,postFriendReq.getUserId()};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}