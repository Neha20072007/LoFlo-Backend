package com.mahendra.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String username) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("🪴 LoFlo Family Welcomes You, " + username + "!");

            // HTML content with updated styling for the LoFlo vibe
            String htmlContent =  "<html><body style='font-family: \"Merriweather\", serif; background-color: #f7f5df; color: #345734; margin: 0; padding: 0;'>"
            + "<div style='text-align: center; padding: 30px; background-color: #ffffff; border: 2px solid #8bb38d; border-radius: 15px; max-width: 600px; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>"
            + "<img src='cid:logoImage' style='width: 150px; height: auto; margin-bottom: 20px;' alt='LoFlo Logo'>"
            + "<h1 style='font-size: 26px; color: #345734;'>Hey " + username + ", Welcome to the LoFlo Family! 🌱</h1>"
            + "<p style='font-size: 18px; color: #345734;'>We're so excited to have you join our community!</p>"
          
            + "<p style='font-size: 16px; color: #345734;'>Click the button below to flow into your new adventure with us:</p>"
            + "<a href='https://google.com' style='display: inline-block; background-color: #6a994e; color: #ffffff; padding: 15px 25px; text-decoration: none; border-radius: 25px; font-size: 18px; font-weight: bold; margin: 20px 0; transition: background-color 0.3s ease;'>Flow into LoFlo 🪴</a>"
            + "<p style='font-size: 16px; color: #345734;'>Stay connected, stay green, and let's grow together! 🌿</p>"
            + "<p style='font-size: 16px; color: #345734;'>See you inside!<br>The LoFlo Team</p>"
            + "<div style='text-align: center; padding: 20px; border-top: 1px solid #8bb38d; margin-top: 30px;'>"
            + "<p style='font-size: 14px; color: #345734;'>LoFlo Headquarters 🌍<br>"
            + "123 Green Street, Nature City, NY 12345<br>"
            + "Phone: (123) 456-7890<br>"
            + "Email: <a href='mailto:support@loflo.com' style='color: #6a994e;'>support@loflo.com</a></p>"
            + "<p style='font-size: 12px; color: #555;'>© 2024 LoFlo. All rights reserved. Remember to plant a tree! 🌳</p>"
            + "</div>"
            + "</div>"
            + "</body></html>";

            helper.setText(htmlContent, true);

            // Load the image from the classpath
            ClassPathResource resource = new ClassPathResource("static/logo.png"); // Ensure correct path
            helper.addInline("logoImage", resource);

            mailSender.send(message);
            System.out.println("Email sent successfully to: " + toEmail);

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
