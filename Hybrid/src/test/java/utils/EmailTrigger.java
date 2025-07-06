package utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

import org.openqa.selenium.WebDriver;

public class EmailTrigger {

    private WebDriver driver; // Optional: to use Selenium info if needed

    // Constructor with WebDriver
    public EmailTrigger(WebDriver driver) {
        this.driver = driver;
    }

    // If WebDriver not needed, can use default constructor
    public EmailTrigger() {
    }

    public void sendReportEmail(String toEmail, String subject, String messageBody, String attachmentPath) {
        final String fromEmail = "aneesh.g@kauzway.com"; // your custom domain email
        final String password = "Oksana@12345#";     // use real email login or app password
        final String smtpHost = "mail.kauzway.com";     // replace with your SMTP server
        final String smtpPort = "465";                     // or use "587" for TLS

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // for SSL

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Message text
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(messageBody);

            // File attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("📬 Report email sent successfully!");

        } catch (Exception e) {
            System.err.println("❌ Failed to send report email.");
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
    }
}