import java.sql.SQLOutput;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Permutation {

    private String key;
    private String string;


    public Permutation(String key, String string) {
        this.key = key;
        this.string = string;


    }

    public void encrypt() {
        int celesi = Integer.parseInt(key);

        String replaceString = string.replaceAll(" ", "");             //replaces all occurrences of "a" to "e"
        StringBuilder sb = new StringBuilder();
        sb.append(replaceString);

        //Shto "W" nese blloku eshte i paplotesuar
        while (sb.length() % key.length() != 0) {
            sb.append("w");

        }
        System.out.println(sb);


        String[] lista = splitToChar(sb, key.length());                                 //ndaje tekstin ne blloqe me gjatesi ne baze te qelesit


        System.out.print("plaintext: " + " ");
        for (int i = 0; i < lista.length; i++) {

            System.out.print(lista[i] + " ");                                        //Shfaqe tekstin ne blloqe


        }
        System.out.println();
        System.out.print("key      : " + " ");
        for (int i = 0; i < lista.length; i++) {
            System.out.print(key + " ");

        }

        String[] lista2 = lista;


        System.out.println();
        System.out.print("ciphertext: ");
        int s = key.length();
        int counter = key.length()-1;
        for (int i = 0; i < lista.length; i++) {
            for (int j = 0; j < key.length(); j++) {

                while (celesi > 0) {
                    int c = celesi % 10;

                 char[] k = swap(lista[i],s-1,c-1);
                 lista[i]=String.valueOf(k);
                    celesi = celesi / 10;
                    s = s - 1;
                }
            }


            }

            for (int i = 0; i < lista.length; i++) {

                System.out.print(lista[i] + " ");                                        //Shfaqe tekstin ne blloqe


            //Shfaqe tekstin ne blloqe


        }
        System.out.println();



    }

    static String swap(String str, int i, int j, StringBuilder sb) {

        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();

    }
    static char[] swap(String str, int i, int j)
    {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return ch;
    }

    public void decrpyt() {

        System.out.println("Qelesi eshte " + key);
        System.out.println("Ciphertexti eshte" + string);

    }

    private static String[] splitToChar(StringBuilder text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
    static void string_swap(String str, int x, int y)
    {
        char arr[] = str.toCharArray();
        char tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
        String perf = new String(arr);

        System.out.print(perf);
    }


}
