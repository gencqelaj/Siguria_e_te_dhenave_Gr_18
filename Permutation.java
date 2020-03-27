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

        int celesi = Integer.parseInt(key);

        int length = String.valueOf(celesi).length();

        int b = celesi;
        ArrayList<Integer> array = new ArrayList<Integer>();
        do {
            array.add(b % 10);
            b /= 10;
        } while (b > 0);
        String replaceString = teksti.replaceAll(" ", "");             //replaces all occurrences of "a" to "e"
        StringBuilder sb = new StringBuilder();
        sb.append(replaceString);

        //Shto "W" nese blloku eshte i paplotesuar
        while (sb.length() % length != 0) {
            sb.append("w");


        }

        List<String> lista = splitToChar(sb, length);
        String[] myArray = new String[lista.size()];
        lista.toArray(myArray);


        System.out.println();
        System.out.println("ciphertext: ");

        Integer array1[] = array.toArray(new Integer[0]);
        reverse(array1);

        String[] cifra = new String[lista.size()];
        int i2 = 0;
        int i3;

        for (int i = 0; i < lista.size(); i++) {
            StringBuilder sb1 = new StringBuilder();
            for (int j = 0; j <length; j++) {
                int a= findIndex(array1,j+1);
                    sb1.append(myArray[i].charAt(a));
                    
            }       
			cifra[i]=sb.toString();
                System.out.print(sb1 + " ");
                



        }   System.out.println();
        System.out.println();
		   System.out.println();
		   System.out.println("ciphertext: ");
        for (int i =0 ; i<cifra.length ; i++)
        {
            System.out.print(cifra[i]);
        }


    }
   public static void reverse(Integer a[])
    {
        Collections.reverse(Arrays.asList(a));

    }

    public static int findIndex(Integer arr[], int t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
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
