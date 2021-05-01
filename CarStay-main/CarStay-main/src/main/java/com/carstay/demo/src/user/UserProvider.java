package com.carstay.demo.src.user;


import com.carstay.demo.config.secret.Secret;
import com.carstay.demo.src.user.model.GetUserRes;
import com.carstay.demo.src.user.model.PostLoginReq;
import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.user.model.PostLoginRes;
import com.carstay.demo.src.user.model.*;
import com.carstay.demo.utils.AES128;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.carstay.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    /*public List<GetUserRes> getUsers() throws BaseException{
        try{
            List<GetUserRes> getUserRes = userDao.getUsers();
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }*/

    public GetUserRes getUser(String userIdx) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(userIdx);
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkId(String userId) throws BaseException{
        try{
            return userDao.checkId(userId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        if(checkId(postLoginReq.getUserId()) == 0){
            throw new BaseException(NO_MATCH_ID_AND_PASSWORD);
        }
        else{
            User user = userDao.getPwd(postLoginReq);
            String password;
            try {
                password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
            } catch (Exception ignored) {
                throw new BaseException(PASSWORD_DECRYPTION_ERROR);
            }

            if(postLoginReq.getPassword().equals(password)){

                    int userIdx = userDao.getPwd(postLoginReq).getUserIdx();
                    String userName = userDao.getPwd(postLoginReq).getUserName();
                    String phoneNumber = userDao.getPwd(postLoginReq).getPhoneNumber();
                    String jwt = jwtService.createJwt(userIdx);
                    return new PostLoginRes(userIdx, userName, phoneNumber, jwt);

            }
            else{
                throw new BaseException(FAILED_TO_LOGIN);
            }
        }


    }


    public GetJWTRes logInByJWT(int userIdx) throws BaseException {
        try {
            GetJWTRes getJWTRes = userDao.logInByJWT(userIdx);
            return getJWTRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
