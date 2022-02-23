package com.api.mail;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Mail {
    @Value("${MAIL.SENDER.HOST.EMAIL}")
    private String host_email;
    @Value("${MAIL.SENDER.HOST.PASSWORD}")
    private String host_password;
    @Value("${MAIL.SENDER.PHONE}")
    private String phone;
    @Value("${MAIL.SENDER.NAME}")
    private String name;
    @Value("${MAIL.SENDER.ADDRESS}")
    private String host_address;
    @Value("${MAIL.SENDER.PORT}")
    private int host_port;
    private String host_auth = "true";
    private String host_starttls = "true";
}
