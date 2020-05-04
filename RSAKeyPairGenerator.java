class RSAKeyPairGenerator{
   private static String Path = "C:\\\\Desktop\\\\keys\\\\";

    public String getPath(){
        return Path;
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

        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        Integer responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader inputreader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = inputreader.readLine()) != null) {
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

   
}
