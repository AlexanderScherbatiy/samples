package sample.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by alexsch on 4/5/2017.
 */
public class DOMXmlParser {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(DOMXmlParser.class.getResourceAsStream("staff.xml"));

        Element element = document.getDocumentElement();
        element.normalize();

        System.out.printf("root: %s\n\n", element.getNodeName());

        NodeList nodeList = document.getElementsByTagName("staff");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.printf("element: %s\n", node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) node;

                printAttribute("staff id  ", nodeElement, "id");
                printTagText("first name", nodeElement, "firstname");
                printTagText("last  name", nodeElement, "lastname");
                printTagText("nick  name", nodeElement, "nickname");
                printTagText("salary    ", nodeElement, "salary");
                System.out.println();
            }
        }
    }

    private static void printAttribute(String msg, Element element, String name) {
        String text = element.getAttribute(name);
        System.out.printf("%s: %s\n", msg, text);
    }

    private static void printTagText(String msg, Element element, String tag) {
        String text = element.getElementsByTagName(tag).item(0).getTextContent();
        System.out.printf("%s: %s\n", msg, text);
    }
}
