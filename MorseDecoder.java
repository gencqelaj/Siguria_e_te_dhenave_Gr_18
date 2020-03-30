import java.util.InputMismatchException;
public class MorseDecoder {


    private final String[] ShkronjatMorse = {"/", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-",".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--",
            "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--", "..--..", "---...", "-..-",
             ".-...","-.-.-.","-.--.","-.--.-","-...-",".-.-."};
    private final char[] Shkronjat = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',',
            '?', ':', '/',';','(',')','=','+'};
    private String FjaliaEShtypur;


    private String[] wordamt;
    private int wordlength;

    public MorseDecoder(String p) {
        FjaliaEShtypur = p;
        NdarjaEFjaleve();
    }



    public void NdarjaEShkronjave(String l) {

        String[] letters = l.split("\\s");

        for(int i=0; i<letters.length; i++){
            for(int j=0; j<ShkronjatMorse.length; j++) {
                if (letters[i].equals(ShkronjatMorse[j])) {
                    System.out.print(Shkronjat[j]);
                }
            }
        }
        System.out.print(" ");
    }
    public void NdarjaEFjaleve() {
        String[] words = FjaliaEShtypur.split("\\s\\s\\s");
        wordamt = words;
        for(int i =0; i<wordamt.length; i++) {
            NdarjaEShkronjave(words[i]);
        }
    }


}