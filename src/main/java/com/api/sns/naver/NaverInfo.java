package com.api.sns.naver;

import lombok.Data;

@Data
public class NaverInfo {
    private String result_code;
    private String message;
    private NaverResponse response;

    @Data
    public class NaverResponse{
        private String id;
        private String nickname;
        private String profile_image;
        private String age;
        private String gender;
        private String email;
        private String mobile;
        private String mobile_e164;
        private String name;
        private String birthday;
        private String birthyear;
    }
}
