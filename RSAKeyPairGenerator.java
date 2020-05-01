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




}
