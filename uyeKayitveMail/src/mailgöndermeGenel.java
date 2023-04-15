import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class mailgöndermeGenel {
    Scanner scanner = new Scanner(System.in);

    //MAIL SERVER PROPERTIES AYARLAMA
    //YAZILACAK MAILI ALMA
    //EMAIL YOLLAMA

    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void mailGönderme() throws AddressException, MessagingException, IOException          //GEREKLİ FONKSİYONLAR ÇAĞIRILIR
    {
        mailgöndermeGenel mailGenel = new mailgöndermeGenel();
        mailGenel.setupServerProperties();
        mailGenel.draftEmail();
        mailGenel.sendEmail();
    }

    private void sendEmail() throws MessagingException {                //KULLANICININ MAİL BİLGİLERİ ALINIP ÜYELERİN MAİL HESAPLARINA MAİL GÖNDERİLİR

        System.out.println("Mail hesabınız: ");

        String gönderenMail=scanner.nextLine();
        String fromUser = gönderenMail;            //GÖNDERENİN MAILI


        System.out.println("Şifreniz(Uygulama şifresi): ");

        String mailŞifre=scanner.nextLine();
        String fromUserPassword = mailŞifre;      //GÖNDERENİN MAIL ŞİFRESİ, GOOGLE GUVENLİK ÖNLEMLERİNDEN DOLAYI 2FA AÇIP UYGULAMA ŞİFRESİ EKLENMELİ


        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email başarıyla gönderildi!");
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {             //DOSYAYI OKUYUP GEREKLİ ÜYE TİPLERİNİN MAİL HESABI ALINIR
        List<String> emailReceipients = new ArrayList<String>();
        File file = new File("Kullanıcılar.txt");
        List<String> lines = Files.readAllLines(file.toPath());
        int elitindex = lines.indexOf("ELİT ÜYELER");
        int genelindex = lines.indexOf("GENEL ÜYELER");
        for(int i = 1; i < genelindex; i++){
            List<String> tmp = List.of(lines.get(i).split("\t"));
            emailReceipients.add(tmp.get(2));
        }





        

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

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}
