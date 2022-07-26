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
