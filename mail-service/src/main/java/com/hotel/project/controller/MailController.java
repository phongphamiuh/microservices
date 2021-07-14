package com.hotel.project.controller;

import com.hotel.project.entity.NotificationEmail;
import com.hotel.project.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class MailController {
    @Autowired
    MailService mailService;

    @GetMapping("/send/login/{email}/{token}")
    public ResponseEntity<?> sendForLogin(@PathVariable("email")String email, @PathVariable("token")String token) {
            mailService.sendMail(new NotificationEmail("Please Activate your account", email,
                    "Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                            + "http://localhost:8080/api/auth/accountVerification" + "/" + token
            ));
        return ResponseEntity.ok("Send Email Success");
    }
}
