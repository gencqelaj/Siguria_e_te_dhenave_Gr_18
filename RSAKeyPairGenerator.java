import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



class RSAKeyPairGenerator {
  
   private static String Path = "C:\\\\Users\\\\Admin\\\\Desktop\\\\keys\\\\";

    private static Document ConvertStringToDocumentBuilder(String stringBuilder)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            doc = builder.parse(new InputSource(new StringReader(stringBuilder)));

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
  
   private static void writeXmlDocumentToXmlFile(Document xmlDocument, String fileName)
    {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            FileOutputStream outStream = new FileOutputStream(new File(fileName));

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
