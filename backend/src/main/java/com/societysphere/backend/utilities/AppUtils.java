package com.societysphere.backend.utilities;

import com.societysphere.backend.model.data.CollegeAdminDetail;
import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class AppUtils {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${verification.email.path}")
    private String templatePath;

    public Date getCurrentTime() {
        Instant instant = Instant.now();
        return Date.from(instant);
    }

    public Boolean sendSimpleEmail(String from, String to, String subject, String body) {
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

    public Boolean sendVerificationEmail(SocietyCreationRequest request, List<CollegeAdminDetail> collegeAdminDetail,
                                         SocietyCreationRequestDetail requestDetail) {
        try {
            String htmlTemplate = Files.readString(Paths.get(templatePath), StandardCharsets.UTF_8);
            String acceptUrl = AppConstants.BASE_URL + "/accept-request?requestId=" + requestDetail.getRequestId();
            String rejectUrl = AppConstants.BASE_URL + "/reject-request?requestId=" + requestDetail.getRequestId();
            htmlTemplate = htmlTemplate.replace("{{societyName}}", request.getSocietyName())
                    .replace("{{acceptUrl}}", acceptUrl)
                    .replace("{{rejectUrl}}", rejectUrl)
                    .replace("{{requestTo}}", collegeAdminDetail.get(0).getAdminName());

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("${spring.mail.username}");
            helper.setTo(collegeAdminDetail.get(0).getAdminEmail());
            helper.setSubject("Society Creation Request");
            helper.setText(htmlTemplate, true);

            mailSender.send(message);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.info("Error sending verification email to {}", collegeAdminDetail.get(0).getAdminEmail());
            return Boolean.FALSE;
        }
    }

}
