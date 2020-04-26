package ua.nure.popova.SummaryTask4.web.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The type Send mail.
 *
 * @author A.Popova
 */
public class SendMail {

    private String username = "pa03622789@gmail.com";
    private String password = "carbonet261965";
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
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
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
