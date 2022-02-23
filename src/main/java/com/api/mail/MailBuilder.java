package com.api.mail;


import com.util.Encryption.EncryptionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Slf4j
public class MailBuilder {
    @Autowired
    private Mail mail;
    private Session session;
    private MimeMessage message;

    public MailBuilder() {
    }

    public MailBuilder setSession() throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", this.mail.getHost_address());
        props.put("mail.smtp.port", this.mail.getHost_port());
        if (mail.getHost_address().contains("google")) {
            props.put("mail.smtp.starttls.enable", this.mail.getHost_starttls());
        }
        props.put("mail.smtp.auth", this.mail.getHost_auth());

        message = new MimeMessage(Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @SneakyThrows
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail.getHost_email(), new EncryptionService().decryptAES(mail.getHost_password()));
            }
        }));
        message.setFrom(new InternetAddress(this.mail.getHost_email())); /*보내는 사람 메일 주소 API상으론 이메일로 로그인을 한 사람이 보낸다.*/
        return this;
    }

    public MailBuilder setTo(String received_address) throws MessagingException {
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(received_address)); /*받는사람 메일 주소 API상으론 서버에 설정된 이메일로 받는다.*/
        return this;
    }

    public MailBuilder setMailTitle(String title) throws MessagingException {
        message.setSubject(title, "UTF-8");
        return this;
    }

    public MailBuilder setMailContent(String content) throws MessagingException {
        /**
         * message.setText("보낼 메세지 내용");
         * message.setContent("보낼 HTML 내용", "text/html;charset=UTF-8");
         * */
        message.setContent(content, "text/html;charset=UTF-8");
        return this;
    }

    public boolean send() throws MessagingException {
        Transport.send(message);
        return true;
    }
}
