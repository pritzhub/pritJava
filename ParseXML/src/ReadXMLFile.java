
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

  public static void main(String argv[]) {
    try {
    File fXmlFile = new File("measures.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(fXmlFile);
    doc.getDocumentElement().normalize();

    System.out.println("Root element is :" + doc.getDocumentElement().getNodeName());
    NodeList nList = doc.getElementsByTagName("deviceid");
    System.out.println("----------------------------");

    for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            System.out.println("Device id : "
                               + eElement.getAttribute("id"));
            System.out.println("Measurement Name : "
                               + eElement.getElementsByTagName("measurementname")
                                 .item(0).getTextContent());
            System.out.println("Measurement timestamp : "
                               + eElement.getElementsByTagName("measurementtimestamp")
                                 .item(0).getTextContent());
            System.out.println("Measurement Description : "
                               + eElement.getElementsByTagName("measurementdesc")
                                 .item(0).getTextContent());
            System.out.println("Measurement Value : "
                               + eElement.getElementsByTagName("measurementvalue")
                                 .item(0).getTextContent());
            System.out.println("Measurement Quality : "
                    + eElement.getElementsByTagName("measurementquality")
                      .item(0).getTextContent());            
        }
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
  }
}