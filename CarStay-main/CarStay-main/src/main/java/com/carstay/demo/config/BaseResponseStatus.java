package com.carstay.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    INVALID_X_Y(false, 2004, "좌표값을 정확히 입력해주세요."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_EMPTY_ID(false, 2018, "아이디를 입력해주세요."),
    POST_USERS_EXISTS_ID(false,2019,"중복된 아이디입니다."),
    POST_USERS_EMPTY_PASSWORD(false, 2020, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_USERNAME(false, 2021, "이름을 입력해주세요."),
    POST_USERS_EMPTY_PHONENUMBER(false, 2022, "휴대폰 번호를 입력해주세요."),
    POST_USERS_INVALID_PHONE(false, 2024, "휴대폰 인증을 해야합니다."),
    USER_IS_NOT_AVAILABLE(false, 2023, "탈퇴한 유저입니다."),
    AUTH_ERROR(false, 2025, "인증 오류입니다."),
    NO_BOOKMARK(false, 2100, "즐겨찾는 맞집이 없습니다."),
    POST_BOOKMARK_EXIST_STORE(false, 2101, "이미 즐겨찾기 된 맛집입니다."),
    DELETE_BOOKMARK_NOEXIST_STORE(false, 2102, "즐겨찾기가 되어있지 않습니다."),
    POST_USERS_INVALID_CODE(false, 2103, "인증번호가 다릅니다."),

    NO_CART(false, 2200, "장바구니가 비어있습니다."),
    ONLY_ONE_STORE_IN_CART(false, 2201, "같은 가게의 메뉴만 담을 수 있습니다."),
    DELETE_CART_NOEXIST_CARTITEM(false, 2202, "해당 cartItemId가 존재하지 않거나 userId를 확인해주세요."),

    CHECK_STORE_AND_MENU(false, 2300, "가게 id와 메뉴 id를 확인해주세요."),

    NO_PAYMENT(false,2400, "결제수단을 입력해주세요."),

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),
    NO_MATCH_ID_AND_PASSWORD(false,3015, "입력하신 아이디 또는 비밀번호가 일치하지 않습니다."),

    NO_STORE_NEAR_BY(false, 3016, "근처에 가게가 없습니다."),
    NO_RESULT(false, 3017, "검색 결과가 없습니다."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");



    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
