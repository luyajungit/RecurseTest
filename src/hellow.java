import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class hellow {
    public static void main(String[] args ) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("123.xml"));

        // 获取根元素节点
        Element root = doc.getDocumentElement();

        parseElement(root);
    }


    private static void parseElement(Element element)
    {
        String tagName = element.getNodeName();

        System.out.print(tagName+"   ");

        // element元素的所有属性构成的NamedNodeMap对象，需要对其进行判断
        NamedNodeMap map = element.getAttributes();

        // 如果存在属性，则打印属性
        if (null != map)
        {
            for (int i = 0; i < map.getLength(); i++)
            {
                // 获得该元素的每一个属性
                Attr attr = (Attr) map.item(i);

                // 属性名和属性值
                String attrName = attr.getName();
                String attrValue = attr.getValue();

                // 注意属性值需要加上引号，所以需要\转义
               System.out.print(" " + attrName + "=\"" + attrValue + "\"");
            }
        }

        // 关闭标签名
       // System.out.print(">");

        // 至此已经打印出了元素名和其属性
        // 下面开始考虑它的子元素
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {

            // 获取每一个child
            Node node = children.item(i);
            // 获取节点类型
            short nodeType = node.getNodeType();

            if (nodeType == Node.ELEMENT_NODE)
            {
                // 如果是元素类型，则递归输出
                parseElement((Element) node);
            }
            else if (nodeType == Node.TEXT_NODE)
            {
                // 如果是文本类型，则输出节点值，及文本内容
                System.out.print(node.getNodeValue());
            }
            else if (nodeType == Node.COMMENT_NODE)
            {
                // 如果是注释，则输出注释
                System.out.print("<!--");

                Comment comment = (Comment) node;

                // 注释内容
                String data = comment.getData();

                System.out.print(data);

                System.out.print("-->");
            }
        }

        // 所有内容处理完之后，输出，关闭根节点
        //System.out.print( tagName);
    }
}
