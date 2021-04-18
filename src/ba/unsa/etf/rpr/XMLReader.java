package ba.unsa.etf.rpr;

import com.github.tsijercic1.xml.common.InvalidXMLException;
import com.github.tsijercic1.xml.common.Node;
import com.github.tsijercic1.xml.parser.XMLParser;
import org.w3c.dom.ProcessingInstruction;

import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {
    private ArrayList<ProjectClass> classes = new ArrayList<>();

    public XMLReader() throws IOException {
        readXML();
    }

    public void readXML() throws IOException {
        XMLParser parser = new XMLParser("C:\\Users\\selma\\jasome\\bla.xml");
        try {
            Node node = parser.getDocumentRootNode();
            System.out.println(node.getName());
            ArrayList<Node> classesNodes = node.getChildNode("Packages").getChildNode("Package").getChildNode("Classes").getChildNodes("Class");
            for (Node n : classesNodes) {
                classes.add(createClassElement(n));
            }
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }
    }

    private ProjectClass createClassElement (Node node) {
        return new ProjectClass(node.getAttributes().get("name"), node.getAttributes().get("sourceFile"), getMetricsForClass(node));
    }

    private ArrayList<Metric> getMetricsForClass(Node node) {
        ArrayList<Node> nodeMetrics = node.getChildNode("Metrics").getChildNodes();
        ArrayList<Metric> metricsClass = new ArrayList<>();

        for (Node n : nodeMetrics) {
            String metricName = n.getAttributes().get("name");
            if(metricName.equals("MHF")
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
}
