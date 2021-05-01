package com.carstay.demo.src.friend;

import com.carstay.demo.src.friend.model.*;
import com.carstay.demo.config.BaseException;
import com.carstay.demo.config.BaseResponse;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/friend")
public class FriendController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final FriendProvider friendProvider;
    @Autowired
    private final FriendService friendService;
    @Autowired
    private final JwtService jwtService;

    @Autowired
    public FriendController(FriendProvider friendProvider, FriendService friendService, JwtService jwtService) {
        this.friendProvider = friendProvider;
        this.friendService = friendService;
        this.jwtService = jwtService;
    }

    /**
     * 친구목록 불러오기 API
     * [GET] app/friends/
     * @return String
     */
    @ResponseBody
    @GetMapping("/{userId}")
        public BaseResponse<List<GetFriendRes>> getFriends(@PathVariable("userId") String userId) {
            try {
                // Get Review
                List<GetFriendRes> getFriendRes = friendProvider.getFriends();
                return new BaseResponse<>(getFriendRes);
            } catch (BaseException exception) {
                return new BaseResponse<>((exception.getStatus()));
            }
        }

    /**
     * 친구 추가 API
     * [POST] /app/friend/addFriend
     * @return BaseResponse<PostReviewRes>
     */
    @ResponseBody
    @PostMapping("/addFriend")
    public BaseResponse<PostFriendRes> createFriend(@RequestBody PostFriendReq postFriendReq) {
        try{
            PostFriendRes postFriendRes = friendService.createFriend(postFriendReq);
            return new BaseResponse<>(postFriendRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 친구 수락 API
     * [PATCH] /app/friend/acceptFriend
     * @return BaseResponse<PostReviewRes>
     */
    @ResponseBody
    @PatchMapping("/acceptFriend")
    public BaseResponse<PostFriendRes> acceptFriend(@RequestBody PostFriendReq postFriendReq) {
        try{
            PostFriendRes postFriendRes = friendService.acceptFriend(postFriendReq);
            return new BaseResponse<>(postFriendRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
