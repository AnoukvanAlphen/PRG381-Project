package controller.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.ModelMessage;
import model.ModelUser;

import java.util.Properties;

public class ServiceMail {

    private final String FROM_EMAIL = "Nicholas2711kelly@gmail.com";
    private final String APP_PASSWORD = "leeilhhohibjjsxn";

    public ModelMessage sendMain(String toEmail, String code) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TLS port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Auth session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL, "BC Wellness Verification"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + code);

            Transport.send(message);

            return new ModelMessage(true, "Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ModelMessage(false, "Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelMessage(false, "Unexpected error: " + e.getMessage());
        }
    }
}
