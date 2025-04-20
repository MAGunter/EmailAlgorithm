package com.sandbox.emailalgorithm.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String sendHtmlEmail(String to, String subject, String description) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            String html = getHtmlTemplate(description);
            helper.setText(html, true); // true = HTML

            mailSender.send(message);
            return "Mail sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

    private String getHtmlTemplate(String description) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                  <body style="margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f5f5f5;">
                    <div style="max-width: 600px; margin: 40px auto; background-color: #ffffff; padding: 20px; border-radius: 12px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
                      <h2 style="text-align: center; color: #4A90E2; margin-top: 0;">We have received your message:</h2>
                      <p style="font-size: 16px; color: #333333; line-height: 1.6; text-align: center;">
                        %s
                      </p>
                      <hr style="border: none; border-top: 1px solid #e0e0e0; margin: 30px 0;" />
                      <p style="font-size: 14px; color: #888888; text-align: center;">
                        With love, <br />
                        Our Team ❤️ <br />
                        Arslan, Yersultan, Abdurakhman, Bekbolsyn
                      </p>
                    </div>
                  </body>
                </html>
                """.formatted(description);
    }
}

