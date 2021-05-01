package com.carstay.demo.src.friend;

import com.carstay.demo.src.friend.FriendDao;
import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.friend.model.GetFriendRes;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.carstay.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class FriendProvider {

    private final FriendDao friendDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FriendProvider(FriendDao reviewDao, JwtService jwtService) {
        this.friendDao = reviewDao;
        this.jwtService = jwtService;
    }

    public List<GetFriendRes> getFriends() throws BaseException {
        try {
            System.out.println("provider1");
            List<GetFriendRes> getReviewRes = friendDao.getFriends();
            System.out.println("provider" + getReviewRes);
            return getReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}