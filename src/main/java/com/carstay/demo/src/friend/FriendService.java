package com.carstay.demo.src.friend;

import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.friend.model.*;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carstay.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class FriendService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FriendDao friendDao;
    private final FriendProvider friendProvider;
    private final JwtService jwtService;

    @Autowired
    public FriendService(FriendDao friendDao, FriendProvider friendProvider, JwtService jwtService) {
        this.friendDao = friendDao;
        this.friendProvider = friendProvider;
        this.jwtService = jwtService;
    }

    public PostFriendRes createFriend(PostFriendReq postFriendReq) throws BaseException {
        try {
            int friendId = friendDao.createFriend(postFriendReq);
            return new PostFriendRes(friendId);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostFriendRes acceptFriend(PostFriendReq postFriendReq) throws BaseException{
        try{
            int friendAccept = friendDao.acceptFriend(postFriendReq);
            return new PostFriendRes(friendAccept);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}

