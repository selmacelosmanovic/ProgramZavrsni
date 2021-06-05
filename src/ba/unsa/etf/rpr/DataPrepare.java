package ba.unsa.etf.rpr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataPrepare {
    public static String fixOutliers(String csvLine) throws IOException {
        String[] lineDivided = csvLine.split(",");
        String filePath = lineDivided[0];
        filePath = filePath.replace("\\.\\", "\\");
        lineDivided[0] = filePath;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        String returnValue = null;
        returnValue = String.join(",", checkDITOutlier(content, lineDivided));
        String [] returnValueTemp = returnValue.split(",");
        returnValue = String.join(",", checkAHFMHFOutlier(content, returnValueTemp));

        if(content.contains("extends Exception")) return "";
        return returnValue;
    }

    private static String[] checkAHFMHFOutlier(String content, String[] line) {
        if((content.contains("@FXML") || content.contains("@javafx.fxml.FXML")) && content.replace(" ", "").contains("initialize()")) {
            line[7] = "NaN";
            line[8] = "NaN";
        }
        return line;
    }

    private static String[] checkDITOutlier(String content, String[] line) {
        if(content.contains("extends JFrame")) {
            line[4] = "0";
        }
        return line;
    }
}
