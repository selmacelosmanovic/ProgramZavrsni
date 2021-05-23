package ba.unsa.etf.rpr;

import com.github.tsijercic1.xml.common.InvalidXMLException;
import com.github.tsijercic1.xml.common.Node;
import com.github.tsijercic1.xml.parser.XMLParser;

import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {
    private ArrayList<ProjectClass> classes = new ArrayList<>();

    public XMLReader(String path) throws IOException {
        readXML(path);
    }

    public void readXML(String path) throws IOException {
        XMLParser parser = new XMLParser(path + "\\\\" + "jasome.xml");
        try {
            Node node = parser.getDocumentRootNode();
            ArrayList<Node> packageNodes = node.getChildNode("Packages").getChildNodes("Package");
            for (Node pack : packageNodes) {
                String packageName = pack.getAttributes().get("name");
                ArrayList<Node> classesNodes = pack.getChildNode("Classes").getChildNodes("Class");
                for (Node n : classesNodes) {
                    classes.add(createClassElement(packageName, n));
                }
            }

        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }
    }

    private ProjectClass createClassElement(String packageName, Node node) {
        return new ProjectClass(packageName, node.getAttributes().get("name"), node.getAttributes().get("sourceFile"), getMetricsForClass(node));
    }

    private ArrayList<Metric> getMetricsForClass(Node node) {
        ArrayList<Node> nodeMetrics = node.getChildNode("Metrics").getChildNodes();
        ArrayList<Metric> metricsClass = new ArrayList<>();

        for (Node n : nodeMetrics) {
            String metricName = n.getAttributes().get("name");
            if(metricName.equals("LCOM*")
                    || metricName.equals("MHF")
                    || metricName.equals("AHF")
                    || metricName.equals("AIF")
                    || metricName.equals("MIF")
                    || metricName.equals("NOCh")
                    || metricName.equals("SIX")
                    || metricName.equals("NMI")) {
                metricsClass.add(new Metric(n.getAttributes().get("name"), Double.parseDouble(n.getAttributes().get("value"))));
            }
        }

        return metricsClass;
    }

    public ArrayList<ProjectClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ProjectClass> classes) {
        this.classes = classes;
    }
}
