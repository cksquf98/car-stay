package com.carstay.demo.src.user;



import com.carstay.demo.config.secret.Secret;
import com.carstay.demo.src.user.model.PostUserReq;
import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.user.model.PostUserRes;
import com.carstay.demo.src.user.model.*;
import com.carstay.demo.utils.AES128;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carstay.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
//        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }

        String pwd;
        try{
            //암호화
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int userIdx = userDao.createUser(postUserReq);
            return new PostUserRes(userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void insertAuthCode(String userPhoneNumber, String key) throws BaseException {
        try{
            userDao.updateAuth(userPhoneNumber, key);
        } catch (Exception exception) {
            throw new BaseException(AUTH_ERROR);
        }
    }

//    public String certification(PostCertReq postCertReq, String phoneNumber) throws BaseException {
//        try {
//            String result = userDao.certification(postCertReq, phoneNumber);
//            return result;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
}
