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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.net.HttpURLConnection;
import java.net.URL;


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
  
  private static Document ParseXMLFile(String file) throws ParserConfigurationException, IOException, SAXException {
        File inputFile = new File(file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        return doc;
    }
  
  public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, ParserConfigurationException, SAXException {
        String argumenti2 = "create_user";
        String argumeti3 = "Visar";
        Boolean exists = FileExists(argumeti3, Path, ".xml");
        if(argumenti2 == "create_user") {
            if (!exists) {
                create_user(argumeti3, Path);
            } else {
                System.out.println("Celesi '" + argumeti3 + "' ekziston paraprakisht.");
            }
        }
        else if(argumenti2 == "delete_user"){

                delete_user(argumeti3, Path);

        }
        
    }
  
  private static void create_user(String user, String path) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGen.genKeyPair();
        RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();//ktu vetem qelsin publik.

        BigInteger n = privKey.getModulus();
        BigInteger e = privKey.getPublicExponent();
        BigInteger d = privKey.getPrivateExponent();
        BigInteger p = privKey.getPrimeP();
        BigInteger q = privKey.getPrimeQ();
        BigInteger dp = privKey.getPrimeExponentP();
        BigInteger dq = privKey.getPrimeExponentQ();
        BigInteger inverseQ = privKey.getCrtCoefficient();

        StringBuilder builder = new StringBuilder();
        builder.append("<RSAKeyValue>\n");
        write(builder, "Modulus", n);
        write(builder, "Exponent", e);
        write(builder, "P", p);
        write(builder, "Q", q);
        write(builder, "DP", dp);
        write(builder, "DQ", dq);
        write(builder, "InverseQ", inverseQ);
        write(builder, "D", d);
        builder.append("</RSAKeyValue>");

        BigInteger modulus = pubKey.getModulus();
        BigInteger publicExponent = privKey.getPublicExponent();
        StringBuilder Publicbuilder = new StringBuilder();
        Publicbuilder.append("<RSAKeyValue>\n");
        write(Publicbuilder, "Modulus", modulus);
        write(Publicbuilder, "Exponent", publicExponent);
        Publicbuilder.append("</RSAKeyValue>");
        File file = new File(path);
        file.mkdir();

        Document doc = ConvertStringToDocumentBuilder(builder.toString());
        Document PublicKeyDoc = ConvertStringToDocumentBuilder(Publicbuilder.toString());
        writeXmlDocumentToXmlFile(doc, ""+path+user+".xml"); 
        System.out.println("Eshte krijuar celesi private keys/"+user+".xml");
        writeXmlDocumentToXmlFile(PublicKeyDoc, ""+path+user+".pub.xml");
        System.out.println("Eshte krijuar celesi public keys/"+user+".pub.xml");

    }
  private static void Export_xml(String sourceFile, String destFile) throws IOException {
        Files.move(Paths.get(sourceFile), Paths.get(destFile));
    }
private static void Import(String sourceFile, String destPath, String user) throws IOException, ParserConfigurationException, SAXException, NoSuchAlgorithmException {
        Boolean getRequest = sourceFile.matches("^(http|https)://.*$");
        Boolean isPrivateKey;

        if(getRequest){
            String response = GetRequest(sourceFile);
                generatePublic(response, destPath, user, Boolean.FALSE);
        }
    else{
            isPrivateKey = IsPrivateKey(sourceFile);
            if(isPrivateKey == Boolean.TRUE){
                generatePublic(sourceFile, destPath, user, Boolean.TRUE);
                Export_xml(sourceFile,""+destPath+user+".xml");
            }
            else{
                Export_xml(sourceFile,""+destPath+user+".pub.xml");
            }

        }

    }
  
  
private static Boolean IsPrivateKey(String file){
        try {
                Document doc = ParseXMLFile(file);
                doc.getDocumentElement().normalize();
                NodeList node = doc.getElementsByTagName("P");
                if(node.item(0) != null)
                {
                    return Boolean.TRUE;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
  private static void generatePublic(String file, String path, String user, Boolean isfile) throws ParserConfigurationException, IOException, SAXException {
        Document PublicKeyDoc;
        if(isfile) {
            Document doc = ParseXMLFile(file);
            doc.getDocumentElement().normalize();

            String Modulus = doc.getElementsByTagName("Modulus").item(0).getTextContent();
            String Exponent = doc.getElementsByTagName("Exponent").item(0).getTextContent();

            StringBuilder Publicbuilder = new StringBuilder();
            Publicbuilder.append("<RSAKeyValue>\n");
            writeString(Publicbuilder, "Modulus", Modulus);
            writeString(Publicbuilder, "Exponent", Exponent);
            Publicbuilder.append("</RSAKeyValue>");
             PublicKeyDoc = ConvertStringToDocumentBuilder(Publicbuilder.toString());
        }
        else{
             PublicKeyDoc = ConvertStringToDocumentBuilder(file);
        }
        writeXmlDocumentToXmlFile(PublicKeyDoc, ""+path+user+".pub.xml");
    }
    private static void write(StringBuilder builder, String tag, BigInteger bigInt) throws UnsupportedEncodingException {
      
        builder.append("\t<");
        builder.append(tag);
        builder.append(">");
        builder.append(encode(bigInt));
        builder.append("</");
        builder.append(tag);
        builder.append(">\n");
    }
private static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path +user+type+"");
        boolean exists = tempFile.exists();
        return exists;
    }    private static String encode(BigInteger bigInt) throws UnsupportedEncodingException {

}
