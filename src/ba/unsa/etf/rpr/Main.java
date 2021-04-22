package ba.unsa.etf.rpr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Unesite putanju do foldera sa projektima: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        String[] folders = file.list();

        for (String str : folders) {
            System.out.println(str);
            XMLToCSV var = new XMLToCSV();
            var.transform(path + "\\\\" + str);
        }
       // XMLToCSV var = new XMLToCSV();
       // var.transform();
    }
}
