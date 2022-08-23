package com.example.demo.services;

import com.example.demo.entity.EmailSender;
import com.example.demo.exception.ApiRequestException;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;

@Service
public class EmailService implements EmailSender {
    private final JavaMailSender mailSender;
    private final static Logger logger= LoggerFactory
            .getLogger(EmailService.class);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(String to, String email,String check)  {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            if(check.equals("enable")){
                helper.setSubject("enable account");

            }
            else if(check.equals("Reset")){
                helper.setSubject("rest password");

            }
            else
            helper.setSubject("confirm your email");
            helper.setFrom("mohammad.derbas@gmail.com");
            mailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            logger.error("failed to send email",e);
            throw new ApiRequestException("failed to send email");
        }

    }


}
