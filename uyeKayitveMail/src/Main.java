
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        Scanner scanner = new Scanner(System.in);
        while (true) {      //MENÜ EKRANI
            System.out.println("*********************");
            System.out.println("1- Elit Üye Ekleme\n2- Genel Üye Ekleme\n3- Mail Gönderme");
            System.out.println("*********************");
            int choice = scanner.nextInt();
            switch (choice) {       //İLGİLİ SEÇİME YÖNLENDİRİLİR

                case 1:
                    System.out.println("İsim: ");
                    scanner.nextLine();
                    String name=scanner.nextLine();
                    System.out.println("Soyisim: ");
                    String surname=scanner.nextLine();
                    System.out.println("Email: ");
                    String email=scanner.nextLine();
                    String type= "elit";
                    elitüyeler üye1=new elitüyeler(name,surname,email);
                    üye1.userInfo();
                    files.dosyaİşlemleri(name,surname,email,type);
                    break;

                case 2:
                    System.out.println("İsim: ");
                    scanner.nextLine();
                    String geneluyeName=scanner.nextLine();
                    System.out.println("Soyisim: ");
                    String geneluyeSurname=scanner.nextLine();
                    System.out.println("Email: ");
                    String geneluyeEmail=scanner.nextLine();
                    type= "genel";
                    genelüyeler üye2=new genelüyeler(geneluyeName,geneluyeSurname,geneluyeEmail);
                    üye2.userInfo();
                    files.dosyaİşlemleri(geneluyeName,geneluyeSurname,geneluyeEmail,type);
                    break;

                case 3:
                    System.out.println("*********************");
                    System.out.println("1- Elit Üyelere Mail\n2- Genel Üyelere Mail\n3- Tüm Üyelere Mail");
                    System.out.println("*********************");
                    int secenek = scanner.nextInt();
                    if(secenek==1) {        //İLGİLİ MAİL SEÇENEĞİNE YÖNLENDİRİLİR

                        mailGöndermeElit.mailGönderme();        //İLGİLİ FONKSİYONLAR ÇAĞIRILIR

                    }

                    else if(secenek==2) {

                        mailgöndermeGenel.mailGönderme();

                    }

                    else if(secenek==3) {

                        mailgönderme.mailGönderme();

                    }

                    else {System.out.println("Hatalı seçim. Lütfen tekrar deneyin.");}
                    break;

                default:
                    System.out.println("Hatalı seçim. Lütfen tekrar deneyin.");
            }
        }
    }
}
