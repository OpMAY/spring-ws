package com.api.sns.kakao;

import lombok.Data;

@Data
public class KakaoInfo {
    private long id;
    private String connected_at;
    private String synched_at;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Data
    public class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Data
    public class KakaoAccount {
        private String profile_nickname_needs_agreement;
        private String profile_image_needs_agreement;
        private KakaoProfile profile;
        private Boolean name_needs_agreement;
        private String name;
        private Boolean has_email;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;
        private Boolean has_phone_number;
        private Boolean phone_number_needs_agreement;
        private String phone_number; // +82 10-0000-0000
        private Boolean has_age_range;
        private Boolean age_range_needs_agreement;
        private String age_range;
        private Boolean has_birthday;
        private Boolean birthday_needs_agreement;
        private String birthday; // 생일 : MMDD
        private String birthday_type; // 음력/양력 : SOLAR/LUNAR
        private Boolean has_gender;
        private Boolean gender_needs_agreement;
        private String gender; // male/female

        @Data
        public class KakaoProfile {
            private String nickname;
            private String thumbnail_image_url;
            private String profile_image_url;
            private Boolean is_default_image;
        }
    }
}