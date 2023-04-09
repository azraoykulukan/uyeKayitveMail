import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class files {            //DOSYA İŞLEMLERİ
    public static int elitindex = 0;
    public static int genelindex = 1;

    Scanner scanner = new Scanner(System.in);

    public static void dosyaİşlemleri(String name, String surname, String email,String type) throws IOException {
        File file = new File("Kullanıcılar.txt");


        if (!file.exists()) {           //DOSYA BİLGİSAYARDA YOKSA YENİ DOSYA KURULUMU
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);        //DOSYAYA YAZMA İŞLEMİNİN OLACAĞI BİLDİRİLİR
            BufferedWriter bWriter = new BufferedWriter(fileWriter);            //DOSYAYA YAZMA İŞLEMİNİ SAĞLAYAN KISIM
            bWriter.write("ELİT ÜYELER\n");            //BAŞLIKLAR
            bWriter.write("GENEL ÜYELER\n");
            bWriter.close();
            elitindex = 0;
            genelindex = 1;

        }
        List<String> lines = Files.readAllLines(file.toPath());         //DOSYAYI OKUYUP YAZILARI LİSTELEME
        elitindex = lines.indexOf("ELİT ÜYELER");
        genelindex = lines.indexOf("GENEL ÜYELER");



        switch (type) {         //ÜYE TİPİNE GÖRE ÜYELERİ HANGİ BAŞLIĞIN ALTINA YAZILACAĞI AYARLANIR
            case "elit":
                String s = name + "\t" + surname + "\t" + email;
                lines.add(++elitindex, s);
                genelindex++;
                Files.write(file.toPath(),lines);

                break;

            case "genel":
                String s1 = name + "\t" + surname + "\t" + email;
                lines.add(++genelindex, s1);
                Files.write(file.toPath(),lines);
                break;

        }



    }
}
