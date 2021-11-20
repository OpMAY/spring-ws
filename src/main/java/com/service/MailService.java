package com.service;


import com.model.Mail;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    public boolean NaverMailSender(Mail mail) throws MessagingException {
        // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        final String user = mail.getHost_email();
        // 패스워드
        final String password = mail.getHost_password();
        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        props.put("mail.smtp.host", mail.getHost_address());
        props.put("mail.smtp.port", mail.getHost_port());
        props.put("mail.smtp.auth", mail.getHost_auth());
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user)); /*보내는 사람 메일 주소 API상으론 이메일로 로그인을 한 사람이 보낸다.*/
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceived_email())); /*받는사람 메일 주소 API상으론 서버에 설정된 이메일로 받는다.*/

        message.setSubject(mail.getTitle(), "UTF-8");
        /**
         * message.setText("보낼 메세지 내용");
         * message.setContent("보낼 HTML 내용", "text/html;charset=UTF-8");
         * */
        message.setContent(mail.getContents(), "text/html;charset=UTF-8");
        Transport.send(message);
        return true;
    }
}
