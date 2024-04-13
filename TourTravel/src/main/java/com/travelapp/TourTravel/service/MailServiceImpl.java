package com.travelapp.TourTravel.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.travelapp.TourTravel.dto.Mail;
import jakarta.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MailServiceImpl implements MailService{

    private JavaMailSender javaMailSender;


    public void sendEmail(Mail mail)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(String.valueOf(new InternetAddress(mail.getMailFrom())));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        }
        catch (MessagingException | jakarta.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
