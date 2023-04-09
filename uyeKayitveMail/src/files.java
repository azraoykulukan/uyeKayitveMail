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
public class files {
    public static int elitindex = 0;
    public static int genelindex = 1;

    Scanner scanner = new Scanner(System.in);

    public static void dosyaİşlemleri(String name, String surname, String email,String type) throws IOException {
        File file = new File("Kullanıcılar.txt");


        if (!file.exists()) {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write("ELİT ÜYELER\n ");
            bWriter.write("GENEL ÜYELER\n ");
            bWriter.close();
            elitindex = 0;
            genelindex = 1;

        }
        List<String> lines = Files.readAllLines(file.toPath());
        elitindex = lines.indexOf("ELİT ÜYELER");
        genelindex = lines.indexOf("GENEL ÜYELER");



        switch (type) {
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
