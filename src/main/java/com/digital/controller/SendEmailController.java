package com.digital.controller;
import com.digital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SendEmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/api/contact", method = RequestMethod.POST)
    public void sendEmail(@RequestBody Map<String, String> contactform) {
        try {
            String email =contactform.get("email");
            String subject =contactform.get("subject");
            String message =contactform.get("message");
            emailService.sendMail(email, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}