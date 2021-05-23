package ba.unsa.etf.rpr;

import java.io.*;
import java.util.ArrayList;

public class XMLToCSV {
    public void transform(String path) {     //removes test classes from csv and adds new metrics from xml
        try {
            XMLReader reader = new XMLReader(path);
            File oldFile = new File(path + "\\\\" + "class.csv");

            if (!oldFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            File tempFile = new File(oldFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(oldFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {

                if (!line.trim().contains("\\test\\") && !line.trim().contains("enum")) {
                    if (i == 0 && line.trim().contains(("cbo,dit,rfc,lcom,mhf,mif,ahf,aif,nmi,noch,six"))) {
                        return;
                        //line += ",mhf,mif,ahf,aif,nmi,noch,six";
                    } else if (i == 0) {
                        line += ",lcom,mhf,mif,ahf,aif,nmi,noch,six";
                    }
                    else {
                        line += "," + getMetricForClassCSVFormat(line.split(",")[1], reader);
                    }
                    pw.println(line);
                    pw.flush();
                    i++;
                }
            }
            pw.close();
            br.close();

            if (!oldFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(oldFile)) System.out.println("Could not rename file");

        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    private String getMetricForClassCSVFormat(String className, XMLReader reader) {
        ProjectClass classElement = reader.getClasses().get(reader.getClasses().indexOf(new ProjectClass(className)));
        ArrayList<Double> metricValues = getMetricValuesForClass(classElement);
        return metricValues.get(0) + "," +
                metricValues.get(1) + "," +
                metricValues.get(2) + "," +
                metricValues.get(3) + "," +
                metricValues.get(4) + "," +
                metricValues.get(5) + "," +
                metricValues.get(6) + "," +
                metricValues.get(7);
    }

    private ArrayList<Double> getMetricValuesForClass(ProjectClass classElement) {
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<Metric> metrics = classElement.getMetrics();

        if(metrics.indexOf(new Metric("LCOM*")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("LCOM*"))).getValue());

        if(metrics.indexOf(new Metric("MHF")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("MHF"))).getValue());

        if(metrics.indexOf(new Metric("MIF")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("MIF"))).getValue());

        if(metrics.indexOf(new Metric("AHF")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("AHF"))).getValue());

        if(metrics.indexOf(new Metric("AIF")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("AIF"))).getValue());

        if(metrics.indexOf(new Metric("NMI")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("NMI"))).getValue());

        if(metrics.indexOf(new Metric("NOCh")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("NOCh"))).getValue());

        if(metrics.indexOf(new Metric("SIX")) == -1) list.add(Double.NaN);
        else list.add(metrics.get(metrics.indexOf(new Metric("SIX"))).getValue());
        return list;
    }
}
