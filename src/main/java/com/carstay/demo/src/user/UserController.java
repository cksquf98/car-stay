package com.carstay.demo.src.user;

import com.carstay.demo.src.user.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.carstay.demo.config.BaseException;
import com.carstay.demo.config.BaseResponse;
import com.carstay.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.carstay.demo.config.BaseResponseStatus.*;
import static com.carstay.demo.utils.ValidationRegex.isRegexEmail;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers() {
        try{

                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userIdx
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetUserRes> getUser(@PathVariable("userIdx") int userIdx) {
        // Get Users
        try{
            // jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            // userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            GetUserRes getUserRes = userProvider.getUser(userIdx);
            return new BaseResponse<>(getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

     /**
     * 휴대폰 인증 API
     * [POST] /sendSMS
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("/sendSMS")
    public String sendSMS(String phoneNumber) throws Exception { // 휴대폰 문자보내기
        String api_key = "NCSR0M9VUOSEVIPJ";
        String api_secret = "ZICXEHKHSNRLFJUZZOQUXA0OHTOTMBYJ";
//        String api_key = "NCSVJBMVYB7TOOQV";
//        String api_secret = "DYGDIZQPLVPHNEXMXQAH2YJFXZOL36NG";
        Message coolsms = new Message(api_key, api_secret); // 메시지보내기 객체 생성
        Random rand  = new Random();
        String userPhoneNumber=phoneNumber;
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        userService.insertAuthCode(userPhoneNumber, numStr); // 휴대폰 인증 관련 서비스
        /*
         * Parameters 관련정보 : http://www.coolsms.co.kr/SDK_Java_API_Reference_ko#toc-0
         */
        HashMap<String, String> set = new HashMap<String, String>();
        set.put("to", userPhoneNumber); // 수신번호
        set.put("from", "01098833753"); // 발신번호
        set.put("text", "인증번호는 [" + numStr + "] 입니다."); // 문자내용
        set.put("type", "sms"); // 문자 타입
    System.out.println(set);
        try {
            JSONObject result = (JSONObject) coolsms.send(set); // 보내기&전송결과받기

            return numStr;
        }
        catch (CoolsmsException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            return "fail";
        }
    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) throws Exception {
       /*
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        //이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        */
        if (postUserReq.getPassword() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }
        if (postUserReq.getUserName() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_USERNAME);
        }
        if (postUserReq.getPhoneNumber() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PHONENUMBER);
        }
        try{
//            String code = sendSMS(postUserReq.getPhoneNumber());
//            if(code != postUserReq.getAuthCode()) {
//                return new BaseResponse<>(POST_USERS_INVALID_CODE);
//            }
//            else {
                PostUserRes postUserRes = userService.createUser(postUserReq);
                return new BaseResponse<>(postUserRes);
           // }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 로그인 API
     * [POST] /users/logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("login")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        try{
         /*   if(postLoginReq.getEmail() == null) {
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }
            //이메일 정규표현
            if(!isRegexEmail(postLoginReq.getEmail())){
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            }
            */

            if(postLoginReq.getPassword() == null) {
                return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
            }

            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 로그인 API
     * [GET] /users/jwt
     * @return BaseResponse<PostJWTRes>
     */
    @ResponseBody
    @GetMapping("/jwt")
    public BaseResponse<GetJWTRes> logInByJWT() {
        try{

            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            GetJWTRes getJWTRes = userProvider.logInByJWT(userIdxByJwt);
            return new BaseResponse<>(getJWTRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

}
