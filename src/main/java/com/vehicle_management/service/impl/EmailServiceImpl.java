package com.vehicle_management.service.impl;

import com.vehicle_management.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public String sendEmail(String toEmail){
//            String body = "Hello! Thank you for choosing G-One Vehicle Management. Good day!";
//            String subject = "G-One Vehicle Management";
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("groupwt123@gmail.com");
//            message.setTo(toEmail);
//            message.setText(body);
//            message.setSubject(subject);
//
//            mailSender.send(message);
//            System.out.println("Mail message sent successfully ");
//
//            return sendEmail(toEmail);
//    }




}
