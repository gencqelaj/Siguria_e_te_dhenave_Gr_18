public class ds {
    public static void main(String[] args) {
      
        try {
                        int nrArgs = args.length;
switch (args[0]) {
    case "permutation":
        Permutation permutation = new Permutation(args[2], args[3]);
        if (args[1].equals("encrypt"))
            permutation.encrypt();
        else if (args[1].equals("decrypt"))
            permutation.decrypt();
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt si argument te dyte");

        break;

    case "morse":
        if (args[1].equals("encrypt"))
        {
            MorseEncoder encoder = new MorseEncoder(args[2]);
            encoder.OutputMorse();
        }
        else if (args[1].equals("decrypt"))
        {
            for (int i =0 ; i<args[2].length() ; i++)
            {
                if (args[2].charAt(i)=='-' || args[2].charAt(i)=='.' || args[2].charAt(i)==' ')
                {
                     MorseDecoder decoder = new MorseDecoder(args[2]);
                }
                else
                    System.out.println("Teksti nuk duhet te permbaje karaktere tjere perpos - , . , ose space");
                System.exit(0);
            }

        }
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt si argument te dyte per te ekzekutuar");



        break;
case "export-key":
                    RSAKeyPairGenerator obj2 = new RSAKeyPairGenerator();
                    if (nrArgs == 3) {
                        String celesi = args[2];
                        String celesipub = celesi + ".pub";
                        Boolean existsprivate = obj2.FileExists(celesi, obj2.getPath(), ".xml");
                        Boolean existspublic = obj2.FileExists(celesipub, obj2.getPath(), ".xml");
                        switch (args[1]) {
                            case "private":
                                if (existsprivate) {
                                    System.out.println(obj2.exportAndPrint(celesi));
                                } else {
                                    System.out.println(" Celesi privat '" + celesi + "' nuk ekziston.\n");
                                }
                                break;

                            case "public":
                                if (existspublic) {
                                    System.out.println(obj2.exportAndPrint(celesipub));
                                } else {
                                    System.out.println(" Celesi publik '" + celesi + "' nuk ekziston.\n");
                                }
                                break;
                            default:
                                System.out.println("Duhet te percaktoni nese celesi eshte publik ose privat");
                        }

                    } else if (nrArgs == 4) {
                        if (!args[3].contains("\\")) {
                            switch (args[1]) {
                                case "private":
                                    String celesinekeys = args[2]+".xml";
                                    String filekudoteruhet ="C:\\Desktop\\keys\\"+args[3];
                                    Boolean existkey1 = obj2.FileExists("" + args[2], "" + obj2.getPath(), ".xml");

                                    File tmpDir = new File(filekudoteruhet);
                                    boolean exists21 = tmpDir.exists();
                                    if (existkey1) {
                                        if (exists21) {
                                            obj2.export(celesinekeys, filekudoteruhet);
                                            System.out.println("Celesi privat u ruajt ne fajllin '" + filekudoteruhet + "'. ");
                                        } else {
                                            File file = new File(filekudoteruhet);
                                            file.createNewFile();
                                            obj2.export(celesinekeys, file.getAbsolutePath());
                                            System.out.println("Celesi privat u ruajt ne fajllin '" + file.getName() + "'. ");
                                        }
                                    } else {
                                        System.out.println("Gabim: Celesi private '" + args[2] + "' nuk ekziston.\n");
                                    }
                                    break;

                                case "public":
                                    String file3 = args[2] + ".pub.xml";
                                    String file4 = "C:\\Desktop\\keys\\"+args[3];

                                    File tmpDir2 = new File( file4);
                                    boolean bool2 = tmpDir2.exists();

                                    if (obj2.existKey(file3)) {
                                        if (bool2) {
                                            obj2.export(file3, file4);
                                            System.out.println("Celesi publik u ruajt ne fajllin '" + args[3]);

                                        } else {
                                            File file = new File(file4);
                                            file.createNewFile();
                                            obj2.export(file3, file.getAbsolutePath());
                                            System.out.println("Celesi publik u ruajt ne fajllin '" + file.getName() + "'. ");
                                        }

                                    } else {
                                        System.out.println("Gabim: Celesi publik '" + args[2] + "' nuk ekziston.\n");
                                    }

                                    break;
                                default:
                                    System.out.println("Duhet qe te percaktoni nese celesi eshte publik apo privat");

                            }
                        } else {

                            switch (args[1]) {
                                case "private":
                                    String file1 = args[2] + ".xml";
                                    String file2 = args[3];
                                    File tempFile = new File(file2);
                                    boolean exists2 = tempFile.exists();

                                    if (obj2.existKey(file1)) {


                                        if (exists2) {
                                            obj2.exportToFile(args[2], file2);
                                            System.out.println("Celesi privat u ruajt ne fajllin '" +file2+ "'. ");
                                        }
                                        else {
                                            if (obj2.isValidPath(file2)) {
                                                File file = new File(file2);
                                                file.createNewFile();
                                                obj2.exportToFile(args[2], file.getAbsolutePath());
                                                System.out.println("Celesi privat u ruajt ne fajllin '" + file2 + "'. ");
                                            } else {
                                                System.out.println("Direktoriumi gabim");
                                            }
                                        }

                                    } else {
                                        System.out.println("Gabim: Celesi privat '" + args[2] + "' nuk ekziston.\n");
                                    }
                                    break;
                                case "public":
                                    String f1 = args[2] + ".pub.xml";
                                    String f2 = args[3];
                                    File tempFile1 = new File(f2);
                                    boolean exists_ = tempFile1.exists();

                                    if (obj2.existKey(f1)) {
                                        File tempFile2 = new File(f2);


                                        if (exists_) {
                                            obj2.exportToFile(args[2]+".pub", f2);
                                            System.out.println("Celesi publik u ruajt ne fajllin '" + tempFile2.getName() + "'. ");
                                        } else {
                                            try {

                                                File file = new File(f2);
                                                file.createNewFile();
                                                obj2.exportToFile(args[2]+".pub", file.getAbsolutePath());

                                                System.out.println("Celesi publik u ruajt ne fajllin '" + file.getName() + "'. ");
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Direktoriumi gabim");
                                            }
                                        }
                                    } else {
                                        System.out.println("Gabim: Celesi publik '" + args[2] + "' nuk ekziston.\n");
                                    }
                                    break;
                                default:
                                    System.out.println("Duhet qe te percaktoni nese celesi eshte publik apo privat");
                            }
                        }
                    }
                    break;
        
         case "import-key":
                    RSAKeyPairGenerator obj3 = new RSAKeyPairGenerator();
                    String emricelesit = args[1];
                    String celesiqeimportohet = args[2];
                    File tmpDir = new File(celesiqeimportohet);
                    boolean exists21 = tmpDir.exists();
                    if (celesiqeimportohet.matches("^(http|https)://.*$")){
                        Boolean existpub = obj3.FileExists(emricelesit, obj3.getPath(), ".pub.xml");
                        Boolean existpriv = obj3.FileExists(emricelesit, obj3.getPath(), ".xml");


                        if (!existpub || !existpriv)
                            obj3.Import(celesiqeimportohet, obj3.getPath(), emricelesit);
                        else {
                            System.out.println("Qelesi " + emricelesit + " ekziston paraprakisht.");
                        }

                    }
                    else if (exists21){
                    try {


                    Boolean existpub = obj3.FileExists(emricelesit, obj3.getPath(), ".pub.xml");
                    Boolean existpriv = obj3.FileExists(emricelesit, obj3.getPath(), ".xml");


                    if (!existpub && !existpriv)
                        obj3.Import(celesiqeimportohet, obj3.getPath(), emricelesit);
                    else {
                        System.out.println("Qelesi " + emricelesit + " ekziston paraprakisht.");
                    }}

                    catch (Exception e)
                    {

                    }}
                    else
                    {
                        System.out.println("Keni dhene direktorium te gabuar");
                    }
                    break;

    case "tap-code":

        if (args[1].equals("encrypt")) {
            TapCode tapObj = new TapCode(args[2]);
            tapObj.enkripto();
        }
        else if (args[1].equals("decrypt"))
        {
            TapCode tapObj = new TapCode(args[2]);
            tapObj.dekripto();
        }
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt per te ekzekutuar");

        break;

    default:
        System.out.println("Ju lutem shtypni njeren nga komandat permutation morse ,tap-code, create-user,delete-user, apo export-key si argument te pare");

}


} catch (Exception e )
        {
            System.out.println("Ju nuk keni shtypur gjithe argumentet e nevojshme");
        }
    }
}
