package utilities;
 
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
 
public class XMLUtils {
 
    public static String[] data=new String[4];
	public static DocumentBuilderFactory dbFactory;
	public static DocumentBuilder dBuilder;
	public static Document doc;
	
    public XMLUtils(String fileName) throws Exception {
    	File xmlFile = new File(System.getProperty("user.dir")+ "//src/test/resources/XMLfiles/"+fileName);
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
    }
 
    public String getValue(String tagName) {
        try {
            return doc.getElementsByTagName(tagName).item(0).getTextContent();
        } catch (Exception e) {
            System.err.println("Error reading tag: " + tagName);
            return null;
        }
    }
}
 