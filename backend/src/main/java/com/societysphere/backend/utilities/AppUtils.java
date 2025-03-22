package com.societysphere.backend.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Log4j2
@Service
public class AppUtils {

    @Autowired
    private JavaMailSender mailSender;

    public Date getCurrentTime() {
        Instant instant = Instant.now();
        return Date.from(instant);
    }

    public Boolean sendEmail(String from, String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("Exception occurred while sending mail", e);
            return Boolean.FALSE;
        }
    }

}
