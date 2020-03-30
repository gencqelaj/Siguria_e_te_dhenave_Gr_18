import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Permutation {

    private String key;
    private String teksti;
    private int celesi ;
    private int length;
    private int b;
    private String replaceString;
    private ArrayList<Integer> array ;
    private StringBuilder sb;
    private List<String>lista;
    public Permutation(String key, String teksti)
    {
        this.key = key;
        this.teksti=teksti;
        for (int i =0 ; i<key.length() ; i++)
        {
            if (Character.isDigit(key.charAt(i))==false)
            {
                System.out.println("Celesi nuk mund te permbaje shkronja");
                System.exit(0);
            }
            if (key.charAt(i)=='0')
            {
                System.out.println("Celesi nuk mund te permbaje numrin 0");
                System.exit(0);
            }
        }
        celesi = Integer.parseInt(key);
        length = String.valueOf(celesi).length();
        b=celesi;
        array = new ArrayList<Integer>();
        do{
            array.add(b % 10);
            b /= 10;
        } while  (b > 0);
        for (int i =0 ; i < array.size() ; i++)
        {

            if (array.get(i)>array.size())
            {
                System.out.println("Celesi nuk mund te permbaje shifra qe jane me te medha se gjatesia e tij");
                System.exit(0);
            }
        }

        sb = new StringBuilder();
        replaceString = teksti.replaceAll(" ", "");
        sb.append(replaceString);

        while (sb.length() % length != 0) {
            sb.append("w");


        }
        lista = splitToChar(sb, length);
    }

    public void encrypt() {
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

        String[] cifra = new String[lista.size()];
        System.out.println();
        System.out.print("ciphertext: ");
        for (int i = 0 ; i<lista.size();i++)
        {
            StringBuilder sb1= new StringBuilder();

            for (int j =length ; j > 0 ; j--)
            {
                sb1.append(myArray[i].charAt(array.get(j-1)-1));
            }
            cifra[i]=sb1.toString();
            System.out.print(sb1+" ");
        }
        System.out.println();
        System.out.print("ciphertext: ");
        for (int i =0 ; i<cifra.length ; i++)
        {
            System.out.print(cifra[i]);
        }
    }

    public void decrypt() {
        String[] myArray = new String[lista.size()];
        lista.toArray(myArray);

        System.out.println("ciphertext: ");

        Integer array1[] = array.toArray(new Integer[0]);
        reverse(array1);

        for (int i = 0; i < lista.size(); i++) {
            StringBuilder sb1 = new StringBuilder();
            for (int j = 0; j <length; j++) {
                int a= findIndex(array1,j+1);
                sb1.append(myArray[i].charAt(a));
            }
            System.out.print(sb1);
        }
        System.out.println();
    }

    public static void reverse(Integer a[])
    {
        Collections.reverse(Arrays.asList(a));
    }

    public static int findIndex(Integer arr[], int t)
    {
        if (arr == null) {
            return -1;
        }

        int len = arr.length;
        int i = 0;

        while (i < len) {

            if (arr[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
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
