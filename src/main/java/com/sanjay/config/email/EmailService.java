package com.sanjay.config.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import org.thymeleaf.context.Context;

import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
@EnableAsync
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    public EmailService(JavaMailSender javaMailSender,
                              SpringTemplateEngine thymeleafTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public String getSystemEmailAddress() {
        return "sharma.sanjay2054@gmail.com";
    }


    @Async
    public void send(ECommEmail eCommEmail) {
        List<String> emails = eCommEmail.getRecipients();
        for (String email : emails) {
            MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress(eCommEmail.getFrom()));
                mimeMessage.setSubject(eCommEmail.getSubject());
                mimeMessage.setText(eCommEmail.getText());
            };
            javaMailSender.send(mimeMessagePreparator);
        }
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String personal =" Market";
        helper.setFrom(getSystemEmailAddress(), personal);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
    }

    @Async
    public void sendTemplateEmail(String email, String subject, Map<String, Object> templateModel) throws MessagingException, UnsupportedEncodingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("mail_template.html", thymeleafContext);
        sendHtmlMessage(email, subject, htmlBody);
    }
}
