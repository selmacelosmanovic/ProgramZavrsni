package ba.unsa.etf.rpr;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        XMLReader reader = new XMLReader();
        reader.readXML();
        XMLToCSV.transform();
    }
}
