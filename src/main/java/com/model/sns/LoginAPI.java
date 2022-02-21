package com.model.sns;

import com.model.User;
import com.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
            String access_Token = naverAPI.getAccessToken(req, code, state);
            /** Naver Login Get Token*/
            HashMap<String, Object> userInfo = access_Token != null ? naverAPI.getUser(access_Token) : null;
            if (userInfo == null) {
                /**Login Failed*/
                return;
            }
            log.info("User Information : " + userInfo);
            /** Login Success*/
            User user = new User((String) userInfo.get("email"), (String) userInfo.get("id"), (String) userInfo.get("nickname"));
            /** Naver Logout*/
            /**System.out.println(new NaverAPI().naverLogout(access_Token));*/
        }

        /** Kakao*/
        if (code != null && state == null && scope == null) {
            /** Kakao Login success*/
            /** Kakao Login Get Token*/
            String access_Token = kakaoAPI.getAccessToken(req, code);
            if (access_Token == null) {
                /* Login Failed*/
                return;
            }
            /** Kakao Login Get User*/
            HashMap<String, Object> userInfo = kakaoAPI.getUser(access_Token);
            //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
            if (userInfo == null) {
                /** Login Failed*/
                return;
            }
            /** Login Success*/
            User user = new User((String) userInfo.get("email"), (String) userInfo.get("id"), (String) userInfo.get("nickname"));
            /** Kakao Logout*/
            /**System.out.println(new KakaoAPI().kakaoLogout(access_Token));*/
        }

        /** Google*/
        else if (code != null && state != null && scope != null) {
            /** Google Login Success*/
            code = req.getParameter("code");
            HashMap<String, Object> userInfo = code != null ? googleAPI.getUserInfo(req, code) : null;
            /** Google Token 3
             * first : access_token(logout token)
             * second : refresh token
             * third : id_token(in this is real token to get profile token)*/
            if (userInfo == null) {
                /** Login Failed*/
                return;
            }
            /** Login Success*/
            User user = new User((String) userInfo.get("email"), (String) userInfo.get("id"), (String) userInfo.get("name"));
            /** Google Logout*/
            /**System.out.println(new GoogleAPI().googleLogout(userInfo.get("access_token").toString()));*/
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
