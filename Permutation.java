import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Permutation {

    private String key;
    private String teksti;

    public Permutation(String key, String teksti)
    {
        this.key = key;
        this.teksti=teksti;
    }

    public void encrypt() {
        int celesi = Integer.parseInt(key);

        int length = String.valueOf(celesi).length();

        int b = celesi;
        ArrayList<Integer> array = new ArrayList<Integer>();
        do{
            array.add(b % 10);
            b /= 10;
        } while  (b > 0);

        String replaceString = teksti.replaceAll(" ", "");             //replaces all occurrences of "a" to "e"
        StringBuilder sb = new StringBuilder();
        sb.append(replaceString);

        //Shto "W" nese blloku eshte i paplotesuar
        while (sb.length() % length != 0) {
            sb.append("w");

        }
        System.out.println(sb);

        List<String>lista = splitToChar(sb, length);

        System.out.print("plaintext: " + " ");
        for(String name:lista) {
            System.out.print(name+" ");
        }

        System.out.println();
        System.out.print("key      : " + " ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(celesi + " ");

        }

        String[] myArray = new String[lista.size()];
        lista.toArray(myArray);


        System.out.println();
        System.out.print("ciphertext: ");
        for (int i = 0 ; i<lista.size();i++)
        {
            StringBuilder sb1= new StringBuilder();

            for (int j =length ; j > 0 ; j--)
            {
                sb1.append(myArray[i].charAt(array.get(j-1)-1));

            }
            System.out.print(sb1+" ");
        }


    }












    public void decrpyt() {

        System.out.println("Qelesi eshte " + key);
        System.out.println("Ciphertexti eshte");

    }



    private static List<String> splitToChar(StringBuilder text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts;
    }


}
