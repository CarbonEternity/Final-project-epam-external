package ua.nure.popova.SummaryTask4.web.util;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Send mail.
 *
 * @author A.Popova
 */
public class SendMail {

    private static final Logger LOG = Logger.getLogger(SendMail.class);

    private String login;
    private String password;
    private Properties props;

    /**
     * Instantiates a new Send mail.
     */
    public SendMail() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        readPasswordAndMail();
    }

    /**
     * Instantiates password and login for sender-account
     */
    private void readPasswordAndMail() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("/media/carbon/COMMON/epam/FinalProject-PopovaJTV/src/main/resources/email.properties");
            property.load(fis);

            this.password = property.getProperty("gmail.password");
            this.login = property.getProperty("gmail.account");

        } catch (IOException e) {
            LOG.error(e);
        }
    }

    /**
     * Send message.
     *
     * @param subject the subject
     * @param text    the text
     * @param toEmail the email of the recipient
     * @return boolean if success
     */
    public boolean send(String subject, String text, String toEmail){
       boolean result = false;

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);

            result=true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
