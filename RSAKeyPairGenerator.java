import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

class RSAKeyPairGenerator {
    private static String Path = "C:\\\\Desktop\\\\keys\\\\";

    public String getPath(){
        return Path;
    }

    private static Document ConvertStringToDocumentBuilder(String stringBuilder)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
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

    public static void create_user(String user, String path) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGen.genKeyPair();
        RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();

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
//        File file = new File(path);
//        file.mkdir();

        Document doc = ConvertStringToDocumentBuilder(builder.toString());
        Document PublicKeyDoc = ConvertStringToDocumentBuilder(Publicbuilder.toString());
        writeXmlDocumentToXmlFile(doc, ""+path+user+".xml");
        System.out.println("Eshte krijuar celesi private keys/"+user+".xml");
        writeXmlDocumentToXmlFile(PublicKeyDoc, ""+path+user+".pub.xml");
        System.out.println("Eshte krijuar celesi public keys/"+user+".pub.xml");

    }

    private static String GetRequest(String url) throws IOException {
        //String url = " https://pastebin.com/raw/568vxV7i";
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        Integer responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader inputreader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = inputreader.readLine()) != null){
                response.append(inputLine);
            }
            inputreader.close();
        }
        return response.toString();
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

    public static void delete_user(String user, String path){
        Boolean existPrivate = FileExists(user, path, ".xml");
        Boolean existPublic = FileExists(user, path, ".pub.xml");
        if(existPrivate) {
            File file = new File("" + path + user + ".xml");
            file.delete();
            System.out.println("Eshte larguar celesi private keys/"+user+".xml");
        }
        if(existPublic) {
            File PublicKeyfile = new File("" + path + user + ".pub.xml");
            PublicKeyfile.delete();
            System.out.println("Eshte larguar celesi public keys/"+user+".pub.xml");
        }
        if(!existPrivate & !existPublic) {
            System.out.println("Celesi '"+user+"'nuk ekziston.");
        }
    }

    static void Import(String sourceFile, String destPath, String user) throws IOException, ParserConfigurationException, SAXException {
        Boolean getRequest = sourceFile.matches("^(http|https)://.*$");
        Boolean isPrivateKey;

        if(getRequest){
            String response = GetRequest(sourceFile);
            generatePublic(response, destPath, user, Boolean.FALSE);
        }
        else{
            if(sourceFile.contains(".xml")) {
                if (sourceFile.contains(":")) {
                    String[] seperate=sourceFile.split("\\\\");
                    String el_fundit=seperate[seperate.length-1];
                    String[] seperate2=el_fundit.split(".");
                    String tipi=seperate2[seperate2.length-1];
                    String pathi="";
                    String useri=seperate2[0];
                    for(int i=0;i<(seperate.length-1);i++)
                    {
                        pathi+=seperate[i];
                    }
                    Boolean exist=FileExists(""+useri,""+pathi,""+tipi);
                    if(exist) {
                        isPrivateKey = IsPrivateKey(pathi+useri+tipi);
                        if (isPrivateKey == Boolean.TRUE) {
                            generatePublic(pathi+useri+tipi, destPath, user, Boolean.TRUE);
                            Export_xml(""+pathi+useri+tipi, "" + destPath + user + ".xml");
                        } else {
                            Export_xml(""+pathi+useri+tipi, "" + destPath + user + ".pub.xml");
                        }
                    }else
                        System.out.println("Gabim ne dhenie te argumenteve!");
                }else
                    System.out.println("Fajlli i dhene nuk eshte qeles valid.");
                } else
                    System.out.println("Fajlli i dhene nuk eshte qeles valid.");

        }
    }


    public static Boolean IsPrivateKey(String file){
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

    public static void Export_xml(String sourceFile, String destFile) throws IOException {
        Files.move(Paths.get(sourceFile), Paths.get(destFile));
    }

    public static void export(String file1 , String file2){
        FileInputStream instream=null;
        FileOutputStream outstream=null;
        try{
            File infile =new File("C:\\Desktop\\keys\\"+file1);
            File outfile =new File("C:\\Desktop\\keys\\"+file2);
            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = instream.read(buffer)) > 0){
                outstream.write(buffer, 0, length);
            }

            instream.close();
            outstream.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static String exportAndPrint(String path) throws IOException {
        String path1 = "C:\\Desktop\\keys\\"+path+".xml";

        try {
            String content = Files.readString(Paths.get(path1));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path1;
    }

    public static void exportToFile(String path1 , String path2){
        FileInputStream instream = null;
        FileOutputStream outstream = null;
        try{
            File infile =new File("C:\\Desktop\\keys\\"+path1+".xml");
            File outfile =new File(path2);
            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = instream.read(buffer)) > 0){
                outstream.write(buffer, 0, length);
            }

            instream.close();
            outstream.close();

            System.out.println("File copied successfully!!");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static boolean existKey(String path)
    {   File tmpDir = new File("C:\\Desktop\\keys\\" + path);
        boolean bool = tmpDir.exists();

        return bool;
    }

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path +user+type+"");
        boolean exists = tempFile.exists();
        return exists;
}

    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
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
    private static void writeString(StringBuilder builder, String tag, String string) throws UnsupportedEncodingException {
        builder.append("\t<");
        builder.append(tag);
        builder.append(">");
        builder.append(string);
        builder.append("</");
        builder.append(tag);
        builder.append(">\n");
    }

    public static String encode(BigInteger b) {
        byte[] b1 = b.toByteArray();
        String k = Base64.getEncoder().encodeToString(b1);

        return  k;
    }
}
