package cn.youye.factory.xmltest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by pc on 2016/7/11.
 */
public class ReadXML {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            readXml(builder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readXml(DocumentBuilder builder) throws IOException, SAXException {
        Document doc = builder.parse(new File("/JavaWorkSpace/SSMTest/testproject/demo-spring/src/main/java/cn/youye/factory/xmltest/test.xml"));
        Element root = doc.getDocumentElement();
        NodeList list = root.getElementsByTagName("lan");
        System.out.println("root: " + root);
        for (int i = 0; i < list.getLength(); i++) {
            Element lan = (Element) list.item(i);
            System.out.println("id: " + lan.getAttribute("id"));
//                Element name= (Element) lan.getElementsByTagName("name").item(0);
//                System.out.println("name: "+name);
            NodeList list1 = lan.getChildNodes();
            for (int j = 0; j < list1.getLength(); j++) {
                Node c = list1.item(j);
                System.out.println(c.getNodeName() + ": " + c.getTextContent());
            }
            System.out.println("-------------------------------");
        }

    }
}
