package com.example.cinemareservationver2;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {
    public static void sendEmail(String recipient) throws Exception {
        System.out.println("Preparing to send email...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        final String myAccountEmail="2110054@newuzbekistanuniversity.uz";
        final String password="AD0053551";

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        Message message = prepareMessage(session,myAccountEmail,recipient);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    private static Message prepareMessage(Session session,String myAccountEmail, String recipient) throws Exception {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Ticket confirmation");
            message.setText("You have successfully booked a ticket!");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
