package com.carstay.demo.src.user;


import com.carstay.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from User where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from User where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")),
                getUserParams);
    }

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (userId, password, userName, phoneNumber, birth) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserId(), postUserReq.getPassword(), postUserReq.getUserName(), postUserReq.getPhoneNumber(), postUserReq.getBirth()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public void updateAuth(String userPhoneNumber, String key){
        String createUserQuery = "update PhoneAuth Set authNumber = ? where phoneNumber = ?";
        Object[] createUserParams = new Object[]{key, userPhoneNumber};
        this.jdbcTemplate.update(createUserQuery, createUserParams);
    }
    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

/*
    public String certification(PostCertReq postCertReq, String phoneNumber) {
        String certQuery = "select authNumber from PhoneAuth where phoneNumber = ?";
        String params = phoneNumber;
        String code =  this.jdbcTemplate.queryForObject(certQuery, String.class, params);
        if(postCertReq.getCertNum() == code) {
            //성공
            String updateQuery = "update User set certification=\"Y\" where phoneNumber = ?";
            this.jdbcTemplate.update(updateQuery, params);
            return "success";
        }
        else {
            //실패
            return "fail";
        }
    }
*/
    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, email, password, userName, phoneNumber, status from User where email = ?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getString("status")
                ),
                getPwdParams);
    }

    public GetJWTRes logInByJWT(int userIdx) {
        String postJwtQuery = "select userIdx, userName, phoneNumber from User where userIdx = ?";
        int postJwtParams = userIdx;

        return this.jdbcTemplate.queryForObject(postJwtQuery,
                (rs,rowNum)-> new GetJWTRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"))
                ,postJwtParams);
    }

}
