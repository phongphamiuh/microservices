package com.hotel.project.service;

import com.hotel.project.entity.NotificationEmail;
import com.hotel.project.exception.SendMailException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("phongpham.dev@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        }catch (MailException mailException){
            throw new SendMailException("Exception occurred when sending mail to " +
                    notificationEmail.getRecipient());
        }
    }
}
