# 한지우 변경사항

## 1. MailBuilder (2022.07.26)

1. 추가된 파일 (Model)
    - MailType.java
    - MailUser.java
    - MailLogo.java
    - MailFooter.java
2. 추가 기능
    - 메일에 전송되는 HTML을 Model을 이용해 알맞게 커스텀 할 수 있도록 변경했습니다.

## 2. Format.java (2022.07.27)

1. 휴대폰 번호
    1. 마스킹
    2. 하이픈 On/Off
    3. 휴대폰 번호 유효성 검증

### **_+ 추가할 부분 있으면 추후에 추가_**

## 3. ProtocolBuilder (2022.07.27)

1. 형식 casting 없이 바로 사용할 수 있게 변경
2. Response가 ArrayList로 예상될 때 사용할 수 있는 openArrayReader()함수 추가

## 4. Login Api (2022.07.27)
###(KAKAO, NAVER, GOOGLE) 

1. LoginApi.java 내에서 기본 User model에 프로필 이미지까지 등록
2. 각 SNS 별 javascript basic js 파일 생성 
   1. KAKAO -> /resources/js/loginapi/kakao-login.js
   2. NAVER -> /resources/js/loginapi/naver-login.js
   3. GOOGLE -> /resources/js/loginapi/google-login.js
   - 특이사항 : js 라이브러리 파일을 따로 작성하지 않아도 사용할 수 있게 작성
   - NAVER -> CALL BACK URL의 형태에 (?)로 parameter가 붙는게 아니라 (#)으로 붙어버려서 parameter 정보를 담아올 수 없음 (수정 필요 사항)
     - naver-login.js에 TODO로 원인 규명
   - test.jsp에 예시 규명
   - backend와 연동하는 함수 + front 단에서만 해결할 수 있는 함수로 구성
3. backend : google-login (state != null => state == null) 

## 5. BootPay (2022.07.27 ~ 2022.07.28)

1. BootPay v1, v2 모두 추가
   1. 현재 카카오페이는 v1만 사용 가능
   2. 그 외 모두 v2


## 6. 사업자 인증 API
1. 구공과 동기화