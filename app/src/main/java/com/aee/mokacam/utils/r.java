package com.aee.mokacam.utils;

import java.io.InputStream;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes.dex */
public class r {
    public HashMap<String, String> a(InputStream inputStream) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        NodeList childNodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement().getChildNodes();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= childNodes.getLength()) {
                return map;
            }
            Node nodeItem = childNodes.item(i2);
            if (nodeItem.getNodeType() == 1) {
                Element element = (Element) nodeItem;
                if ("version".equals(element.getNodeName())) {
                    map.put("version", element.getFirstChild().getNodeValue());
                } else if ("name".equals(element.getNodeName())) {
                    map.put("name", element.getFirstChild().getNodeValue());
                } else if ("url".equals(element.getNodeName())) {
                    map.put("url", element.getFirstChild().getNodeValue());
                } else if ("info".equals(element.getNodeName())) {
                    map.put("info", element.getFirstChild().getNodeValue());
                } else if ("hostip".equals(element.getNodeName())) {
                    map.put("hostip", element.getFirstChild().getNodeValue());
                }
            }
            i = i2 + 1;
        }
    }
}
