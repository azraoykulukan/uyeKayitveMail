
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Scanner;

public class mailGöndermeElit {

    Scanner scanner = new Scanner(System.in);

    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL

    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void mailGönderme() throws AddressException, MessagingException, IOException
    {
        mailGöndermeElit mailElit = new mailGöndermeElit();
        mailElit.setupServerProperties();
        mailElit.draftEmail();
        mailElit.sendEmail();
    }

    private void sendEmail() throws MessagingException {

        System.out.println("Mail hesabınız: ");
        String gönderenMail=scanner.nextLine();
        String fromUser = gönderenMail;           //Enter sender email id


        System.out.println("Şifreniz(Uygulama şifresi): ");
        String mailŞifre=scanner.nextLine();
        String fromUserPassword = mailŞifre;     //Enter sender gmail password , this will be authenticated by gmail smtp server


        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
        List<String> emailReceipients = new ArrayList<String>();
        File file = new File("Kullanıcılar.txt");
        List<String> lines = Files.readAllLines(file.toPath());
        int elitindex = lines.indexOf("ELİT ÜYELER");
        int genelindex = lines.indexOf("GENEL ÜYELER");
        for(int i = 1; i < genelindex; i++){
            List<String> tmp = List.of(lines.get(i).split("\t"));
            emailReceipients.add(tmp.get(2));
        }





        //Enter list of email recepients

        System.out.println("Konu: ");
        String mailKonu = scanner.nextLine();
        String emailSubject = mailKonu;

        System.out.println("Mailiniz: ");
        String mailBodyYaz = scanner.nextLine();
        String emailBody = mailBodyYaz;
        mimeMessage = new MimeMessage(newSession);
        for(String email : emailReceipients){
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        }


        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE
        // CREATE MESSAGE BODY PARTS
        // CREATE MESSAGE MULTIPART
        // ADD MESSAGE BODY PARTS ----> MULTIPART
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object


        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "html/text");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }



    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}

