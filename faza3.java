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
    private static String url = "jdbc:mysql://localhost:3306/ds_faza3";
    private static String user = "root", pass="";

    faza3() throws ClassNotFoundException, SQLException {
         Connection connect = null;
         Statement statement = null;
        ResultSet resultSet = null;
         String url = "jdbc:mysql://localhost:3306/ds_faza3";
        String user = "root", pass="";

        Class.forName("com.mysql.cj.jdbc.Driver");
        connect= DriverManager.getConnection(url,user,pass);
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
  
  }
