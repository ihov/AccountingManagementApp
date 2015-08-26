package cl.ihov.project.view.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class SendMailUtils {
    private static final Logger logger = Logger.getLogger(SendMailUtils.class
            .getName());

    public static boolean send(String to, String from, String subject,
            String body, String password) {
        boolean status = true;

        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.user", to);
            Session session = Session.getDefaultInstance(properties);
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
            message.setText(body, "UTF-8", "html");
            Transport t = session.getTransport("smtp");
            t.connect(from, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException | UnsupportedEncodingException ex) {
            status = false;
            logger.log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
