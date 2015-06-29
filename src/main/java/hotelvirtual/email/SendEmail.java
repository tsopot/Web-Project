package hotelvirtual.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private static final String HOST = "smtp.mail.yahoo.com";
    private static final String HOTEL_EMAIL_ID = "...@yahoo.com";
    private static final String HOTEL_EMAIL_PASSWORD = "...";

    private static final String SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_USER = "mail.smtp.user";
    private static final String SMTP_PASSWORD = "mail.smtp.password";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_AUTH = "mail.smtp.auth";

    private static final String MESSAGE_SUBJECT = "Email from Hotel Virtual";

    public void SendingEmail(String Email,String Body) throws AddressException, MessagingException {

        String host = HOST;
        String from = HOTEL_EMAIL_ID;
        String pass = HOTEL_EMAIL_PASSWORD;

        Properties props = System.getProperties();

        props.put(SMTP_STARTTLS, "true");
        props.put(SMTP_HOST, host);
        props.put(SMTP_USER, from);
        props.put(SMTP_PASSWORD, pass);
        props.put(SMTP_PORT, "587");
        props.put(SMTP_AUTH, "true");

        String[] to = {Email};

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        InternetAddress[] toAddress = new InternetAddress[to.length];

        for( int i=0; i < to.length; i++ )
        {
            toAddress[i] = new InternetAddress(to[i]);
        }
        System.out.println(Message.RecipientType.TO);

        for (InternetAddress address : toAddress) {
            message.addRecipient(Message.RecipientType.TO, address);
        }

        message.setSubject(MESSAGE_SUBJECT);
        message.setContent(Body,"text/html");

        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}