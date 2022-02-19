package io.spring.utils;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailUtil {
    public void sendmail(String receive, int id_order) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tanvx308@gmail.com", "");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("tanvx308@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive));
        msg.setSubject("Công Minh Idol thông báo");
        msg.setContent("Mãi yêu <3", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
}
