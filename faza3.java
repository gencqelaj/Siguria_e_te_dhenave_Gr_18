import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
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
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.*;
import java.util.Random;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.io.File;
import java.io.IOException;
public class faza3 {
  
  private static String Path = "..\\keys\\";
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/siguria3";
    private static String user = "root", pass="";

    faza3() throws ClassNotFoundException, SQLException {
        String currentPath = System.getProperty("user.dir");
        this.Path  = Paths.get(currentPath).getParent().toString();
        this.Path = this.Path + "\\keys\\";

        String url = "jdbc:mysql://localhost:3306/siguria3?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root", pass="Genci";

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connect= DriverManager.getConnection(url,user,pass);
    }

  
  public String getPath() {
        return Path;
    }
    private static Document ConvertStringToDocumentBuilder(String stringBuilder) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(stringBuilder)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
  
    private static void writeXmlDocumentToXmlFile(Document xmlDocument, String fileName) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            FileOutputStream outStream = new FileOutputStream(new File(fileName));
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

        Document doc = ConvertStringToDocumentBuilder(builder.toString());
        Document PublicKeyDoc = ConvertStringToDocumentBuilder(Publicbuilder.toString());
        writeXmlDocumentToXmlFile(doc, "" + path + user + ".xml");
        System.out.println("Eshte krijuar celesi private keys/" + user + ".xml");
        writeXmlDocumentToXmlFile(PublicKeyDoc, "" + path + user + ".pub.xml");
        System.out.println("Eshte krijuar celesi public keys/" + user + ".pub.xml");

    }
  
  public static byte[] generateSalt(){
        Random rand = new Random();
        byte[] salt = new byte[16];

        rand.nextBytes(salt);
        return salt;
    }
  
  public static void delete_user(String user, String path) {
        Boolean existPrivate = FileExists(user, path, ".xml");
        Boolean existPublic = FileExists(user, path, ".pub.xml");
        if (existPrivate) {
            File file = new File("" + path + user + ".xml");
            file.delete();
            System.out.println("Eshte larguar celesi private keys/" + user + ".xml");
        }
        if (existPublic) {
            File PublicKeyfile = new File("" + path + user + ".pub.xml");
            PublicKeyfile.delete();
            System.out.println("Eshte larguar celesi public keys/" + user + ".pub.xml");
        }
        if (!existPrivate & !existPublic) {
            System.out.println("Celesi '" + user + "'nuk ekziston.");
            System.exit(0);
        }
    }
  
   public static Boolean FileExists(String user, String path, String type) {
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
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


    public static String encode(BigInteger b) {
        byte[] b1 = b.toByteArray();
        String k = Base64.getEncoder().encodeToString(b1);

        return k;
    }
  
  public static PublicKey getPublicKey(String name){
        PublicKey publickey =null;
        String modulus = "";
        String exponent = "";
        try{
            File file = new File("C:\\Users\\Admin\\IdeaProjects\\siguria3_2\\keys\\" + name + ".pub.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("RSAKeyValue");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    modulus = eElement.getElementsByTagName("Modulus").item(0).getTextContent();
                    exponent = eElement.getElementsByTagName("Exponent").item(0).getTextContent();

                }
            }

            KeyFactory rsaFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec rsaKeyspec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(exponent));
            PublicKey pubKey = rsaFactory.generatePublic(rsaKeyspec);

            return pubKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return publickey;
    }
  
  public static PublicKey GetPublicKey(String name) throws NoSuchAlgorithmException, InvalidKeySpecException, ParserConfigurationException, IOException, SAXException {
        String modulus = "";
        String exponent = "";
        File file = new File("C:\\Users\\Admin\\IdeaProjects\\siguria3_2\\keys\\" + name + ".pub.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("RSAKeyValue");
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                modulus = eElement.getElementsByTagName("Modulus").item(0).getTextContent();
                exponent = eElement.getElementsByTagName("Exponent").item(0).getTextContent();
            }
        }
        BigInteger modulus1 = new BigInteger(Base64.getDecoder().decode(modulus));
        BigInteger pubExponent = new BigInteger(Base64.getDecoder().decode(exponent));
        RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(modulus1, pubExponent);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey pub = factory.generatePublic(publicSpec);
        return  pub;
    }
  
  static String getPassword(String prompt) {

        String password = "";
        ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        consoleEraser.start();
        try {
            password = in.readLine();
        }
        catch (IOException e){
            System.out.println("Error trying to read your password!");
            System.exit(1);
        }

        consoleEraser.halt();
        System.out.print("\b ");

        return password;
    }
    private static class ConsoleEraser extends Thread {
        private boolean running = true;
        public void run() {
            while (running) {
                System.out.print("\b ");
                try {
                    Thread.currentThread().sleep(1);
                }
                catch(InterruptedException e) {
                    break;
                }
            }
        }
        public synchronized void halt() {
            running = false;
        }
    }
  
  public static Boolean validatePassword(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[.@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
  
    public static String writeMessage(String name, String message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ParserConfigurationException, InvalidKeySpecException, SAXException {

        byte[] IV = new byte[8];
        new Random().nextBytes(IV);
        String iv = new String(IV);


        KeyGenerator kg = KeyGenerator.getInstance("DES");
        SecretKey myDESKey = kg.generateKey();

        String key = myDESKey.toString();


        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE,myDESKey);
        byte[] text = message.getBytes();
        byte[] textEnc = cipher.doFinal(text);
        byte[] pjesa3 = encrypt(key,name);




        String part1 = Base64.getEncoder().encodeToString(name.getBytes("UTF-8"));
        String part2 = Base64.getEncoder().encodeToString(iv.getBytes());
        String part3 = Base64.getEncoder().encodeToString(pjesa3);
        String part4 = Base64.getEncoder().encodeToString(textEnc);
        return part1+"."+part2+"."+part3+"."+part4;
    }
  
  public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
  
  }
