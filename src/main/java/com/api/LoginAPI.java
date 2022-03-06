package com.api;

import com.api.sns.google.GoogleAPI;
import com.api.sns.google.GoogleAccess;
import com.api.sns.google.GoogleInfo;
import com.api.sns.kakao.KakaoAPI;
import com.api.sns.kakao.KakaoAccess;
import com.api.sns.kakao.KakaoInfo;
import com.api.sns.naver.NaverAPI;
import com.api.sns.naver.NaverAccess;
import com.api.sns.naver.NaverInfo;
import com.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class LoginAPI {

    @Autowired
    private KakaoAPI kakaoAPI;

    @Autowired
    private NaverAPI naverAPI;

    @Autowired
    private GoogleAPI googleAPI;

    /**
     * @param : req (request)
     * @return : void (void)
     * Description : Login API를 이용하여 로그인 했을 때 결과를 받는 함수
     * Date : 2020-10-10
     * Updated date : 2020-10-12 Kakao Login 추가
     * Updated date : 2020-10-13 Naver Login 추가
     * Updated date : 2020-11-17 Naver Login 제거, Login Logic 추가
     * Version : 4
     */
    public void apiLoginInit(HttpServletRequest req) {
        /** Kakao Naver Google Login Get Auth Code And Error Code = code in Common*/
        String code = req.getParameter("code");
        /** Kakao Login Get Auth = error*/
        String error = req.getParameter("error");
        /** Naver Google Login Get Auth = state*/
        String state = req.getParameter("state");
        /** Google Login Get Auth = scope*/
        String scope = req.getParameter("scope");

        /** Naver*/
        if (code != null && state != null && scope == null) {
            /** Naver Login Success*/
            System.out.println("Naver Code : " + code);
            NaverAccess naverAccess = naverAPI.getAccessToken(req, code, state);
            /** Naver Login Get Token*/
            NaverInfo naverInfo = naverAccess.getAccess_token() != null ? naverAPI.getUser(naverAccess.getAccess_token()) : null;
            if (naverInfo == null) {
                /**Login Failed*/
                return;
            }
            /** Login Success*/
            User user = new User(naverInfo.getResponse().getEmail(), naverInfo.getResponse().getId(), naverInfo.getResponse().getNickname());
            /** Naver Logout*/
            /*NaverAccess logoutAccess = naverAPI.logout(naverAccess.getAccess_token());
            if (logoutAccess.getResult().equals("success")) {
                log.info("logout");
            } else {
                log.info("logout failed");
            }*/
        }

        /** Kakao*/
        if (code != null && state == null && scope == null) {
            /** Kakao Login success*/
            /** Kakao Login Get Token*/
            KakaoAccess kakaoAccess = kakaoAPI.getAccessToken(req, code);
            if (kakaoAccess == null) {
                /* Login Failed*/
                return;
            }
            /** Kakao Login Get User*/
            KakaoInfo kakaoInfo = kakaoAPI.getUser(kakaoAccess.getAccess_token());
            //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
            if (kakaoInfo == null) {
                /** Login Failed*/
                return;
            }
            /** Login Success*/
            User user = new User(kakaoInfo.getKakao_account().getEmail(),
                    Long.toString(kakaoInfo.getId()),
                    kakaoInfo.getKakao_account().getProfile().getNickname() != null ? kakaoInfo.getKakao_account().getProfile().getNickname() : kakaoInfo.getProperties().getNickname());
            /** Kakao Logout*/
            /*String id = kakaoAPI.logout(kakaoAccess.getAccess_token());
            if (id.equals(user.getId())) {
                log.info("logout");
            } else {
                log.info("logout failed");
            }*/
        }

        /** Google*/
        else if (code != null && state != null && scope != null) {
            /** Google Login Success*/
            code = req.getParameter("code");
            /** Google Token 3 Types
             * first : access_token(logout token)
             * second : refresh token
             * third : id_token(in this is real token to get profile token)*/
            GoogleAccess googleAccess = code != null ? googleAPI.getAccessToken(req, code) : null;
            if (googleAccess == null) {
                /* Login Failed*/
                return;
            }
            GoogleInfo googleInfo = code != null ? googleAPI.getUser(googleAccess.getAccess_token()) : null;
            if (googleInfo == null) {
                /** Login Failed*/
                return;
            }
            /** Login Success*/
            /**Google API는 NAME 못가져옵니다. 다른 방법을 사용해서 얻어야합니다.*/
            User user = new User(googleInfo.getEmail(), googleInfo.getId(), googleInfo.getName() == null ? "Empty User Name" : googleInfo.getName());
            /** Google Logout*/
            /*String result = googleAPI.logout(googleAccess.getAccess_token());
            if (result != null) {
                log.info("logout");
            } else {
                log.info("logout failed");
            }*/
        } else {
            if (error != null) {
                /**Kakao Login Error
                 * User denied access	사용자가 동의 화면에서 동의하고 시작하기를 누르지 않고 로그인을 취소한 경우
                 * Not allowed under age 14	만14세 미만 사용자의 보호자 동의 실패*/
                /**Google Login Error
                 * https://oauth2.example.com/callback#error=access_denied
                 * */
                /** Kakao Login Error*/
                /** Google Login Error*/
                return;
            }
        }
    }
}
