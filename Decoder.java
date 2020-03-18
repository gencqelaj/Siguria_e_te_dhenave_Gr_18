import java.util.ArrayList;
import java.util.StringTokenizer;
public class Decoder {

    private String userphrase;
    private static final String[] MorseLet = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",
            ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..","-----",".----","..---","...--",
            "....-",".....","-....","--...","---..","----.",".-.-.-","--..--","..--..","/"};
    private static final char[] Letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','.',',','?',' '};


    private String[] wordamt;
    private int wordlength;

    public Decoder(String p) {

        userphrase = p;
        SeperateWords();

    }

    public void SeperateLetters(String l) {

        String[] letters = l.split("\\s");

        for(int i=0; i<letters.length; i++){
            for(int j=0; j<MorseLet.length; j++){
                if(letters[i].equals(MorseLet[j])){
                    System.out.print(Letters[j]);
                }
            }
        }
        System.out.print(" ");
    }
    public void SeperateWords() {

        String[] words = userphrase.split("\\s\\s\\s");
        wordamt = words;
        for(int i =0; i<wordamt.length; i++){
            SeperateLetters(words[i]);
        }

    }
}