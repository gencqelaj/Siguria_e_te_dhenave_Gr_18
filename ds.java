import java.io.File;
import java.io.FileNotFoundException;

public class ds {
    private static String Path = "..\\keys\\";
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/siguria3?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "root", pass = "Genci";
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
        case "create-user":
                    faza3 obj = new faza3();

                    String argumenti1 = args[1];
                    Boolean exists = obj.FileExists(argumenti1, obj.getPath(), ".xml");
                    if (argumenti1.matches("[_a-zA-Z0-9]+\\.?")) {

                        if (!exists) {

                            String password = obj.getPassword("Jepni fjalekalimin: ");

                            while (password.length()<6){
                                System.out.println("Fjalekalimi duhet te kete me shume se 6 karaktere");
                                password = obj.getPassword("Jepni fjalekalimin: ");
//                                System.exit(0);
                            }
                            while (!obj.validatePassword(password)){
                                System.out.println("Fjalekalimi juaj duhet te permbaje shkronja,numra " +
                                        "dhe simbole , nuk duhet te jete i zbrazet dhe nuk duhet" +
                                        " te permbaje hapesira");
                                password = obj.getPassword("Jepni fjalekalimin: ");

//                                System.exit(0);
                            }
                            String password2 = obj.getPassword("Perserit fjalekalimin: ");
                            String argumenti2 = password;
                            String argumenti_2 = password2;
                            while (!argumenti2.equals(argumenti_2)) {
                                System.out.println("GABIM : Fjalekalimet nuk jane te njejta");
                                password2 = obj.getPassword("Perserit fjalekalimin: ");
                                argumenti_2 = password2;
//                                System.exit(0);
                            }
                                byte[] salt1 = obj.generateSalt();
                                String s = Base64.getEncoder().encodeToString(salt1);
                                String hashPassword = faza3.toHexString(faza3.getSHA(s + password));
                                obj.create_user(argumenti1, obj.getPath());


                                try {

                                    Statement stmt = obj.getConnect().createStatement();
                                    String sql = "INSERT INTO `users` (`id`, `username`, `salt`, `password`) VALUES (NULL,'" + argumenti1 + "','" + s + "','" + hashPassword + "')";
                                    stmt.executeUpdate(sql);
                                    stmt.close();
                                } catch (Exception e) {
                                    System.err.println("Got an exception! ");
                                    System.err.println(e.getMessage());
                                }



                        } else {
                            System.out.println("Celesi '" + argumenti1 + "' ekziston paraprakisht.");
                        }
                    } else {
                        System.out.println("Emri i celesit duhet te permbaje vetem shkronja , numra ose _");

                    }
                    break;
 case "delete-user":
                    faza3 obj1 = new faza3();
                    String argumenti3 = args[1];
                    Statement stmt=null;
                    stmt = obj1.getConnect().createStatement();;

                    String sql = "SELECT * FROM users where username= '"+argumenti3+"'";

                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {

                        String username = rs.getString("username");
                        String salt = rs.getString("salt");

                        String password = rs.getString("password");

                        String password12 = obj1.getPassword("Jepni fjalekalimin: ");
                        String hashPassword = faza3.toHexString(faza3.getSHA(salt + password12));


                                while (!hashPassword.equals(password)) {
                                    System.out.println("Fjalekalimi gabim!");
                                    password12 = obj1.getPassword("Provoni perseri: ");
                                    hashPassword = faza3.toHexString(faza3.getSHA(salt + password12));
                                }
                                if (hashPassword.equals(password)) {
                                    Statement stmt1 = obj1.getConnect().createStatement();
                                    String sql4 = "DELETE FROM users WHERE username ='" + username + "'";
                                    stmt1.executeUpdate(sql4);
                                    obj1.delete_user(argumenti3, obj1.getPath());

                                } else {
                                    System.out.println("Fjalekalimi gabim");
                                    System.exit(0);
                                }


                    }

                    break;
        case "login":
                    faza3 obj_login = new faza3();
                    String argumenti_login = args[1];
                    try {
                        Statement stm_login = obj_login.getConnect().createStatement();
                        String sql_login1 = "select * from users where username='" + argumenti_login + "'";
                        //  stm_login.executeUpdate(sql_login1);
                        ResultSet rs_login1 = stm_login.executeQuery(sql_login1);
                        if (rs_login1.next()) {
                            String username = rs_login1.getString("username");
                            if (username.equals(argumenti_login)) {
                                int id = rs_login1.getInt("id");
                                String password = obj_login.getPassword("Jepni fjalekalimin: ");
                                String salt = rs_login1.getString("salt");
                                String hashPassword = faza3.toHexString(faza3.getSHA(salt + password));
                                String user_password = rs_login1.getString("password");
                                while(!hashPassword.equals(user_password))
                                {
                                    password = obj_login.getPassword("Jepni fjalekalimin: ");
                                    hashPassword = faza3.toHexString(faza3.getSHA(salt + password));
                                }
                                if (hashPassword.equals(user_password)) {
                                    JWT objWT = new JWT();
                                    String currentPath = System.getProperty("user.dir");
                                    String properPath  = Paths.get(currentPath).getParent().toString();
                                    properPath = properPath + "\\keys\\"+ argumenti_login+".xml";
                                    objWT.setFile(properPath);
                                    String JWT = objWT.createJWT_v2(Integer.toString(id), username, "jwt_Token", TimeUnit.MINUTES.toMillis(20));
                                    System.out.println("Logged in :)");
                                    String query_update = "update users set jwt = '" + JWT + "' where username = '" + username + "'";
                                    stm_login.executeUpdate(query_update);

                                    System.out.println(JWT);

                                } else {
                                    System.out.println("Password is wrong");
                                }

                            } else {
                                System.out.println("This user does not exist");
                            }

                        } else {
                            System.out.println("Useri nuk ekziston");
                        }
                        stm_login.close();
                    } catch (Exception e) {
                        System.err.println("Got an exception! ");
                        System.err.println(e.getMessage());
                    }
                    break;
        
         case "status":

                    String argumenti_jwt = args[1];
                    faza3 obj_status = new faza3();
                    try {
                            Statement stm_login = obj_status.getConnect().createStatement();
                            String sql_login1 = "select * from users where jwt='" + argumenti_jwt + "'";

                            ResultSet rs_login1 = stm_login.executeQuery(sql_login1);
                            if (rs_login1.next()) {

                                String username = rs_login1.getString("username");
                                String JWT = rs_login1.getString("jwt");
                                if (JWT.equals(argumenti_jwt)) {
                                    JWT obj_Jwt = new JWT();
                                    String currentPath = System.getProperty("user.dir");
                                    String properPath  = Paths.get(currentPath).getParent().toString();
                                    properPath = properPath + "\\keys\\"+ username+".pub.xml";
                                    obj_Jwt.setFile(properPath);
                                    JwtClaims jwtClaims = null;
                                    try {
                                        jwtClaims = obj_Jwt.decodeJWT(JWT);
                                    }
                                    catch(Exception e){
                                        System.out.println("Tokeni nuk eshte valid");
                                        System.exit(1);
                                    }

                                    Date date = new Date(Long.valueOf(jwtClaims.getClaimsMap().get("exp").toString()) * 1000);
                                    DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
                                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                                    System.out.println(formatter.format(date));

                                        System.out.println("User: " + jwtClaims.getClaimsMap().get("iss"));
                                        System.out.println("Valid: po");
                                        System.out.println("Expires at: " + df.format(date));

                                    } else {
                                    System.out.println("This JWT does not exist");
                                }

                            } else {
                                System.out.println("JWT nuk ekziston");
                            }
                            stm_login.close();
                        } catch (Exception e) {
                            System.out.println(e);
                            System.err.println("Got an exception! ");
                            System.err.println(e.getMessage());
                        }
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
