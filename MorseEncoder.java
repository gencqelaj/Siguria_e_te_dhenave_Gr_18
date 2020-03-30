public class MorseEncoder {

    private String FjaliaEShtypur;
    private final char[] Shkronjat = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',',
            '?', ':', '/', '&',';','(',')','=','+'};
    private final String[] ShkronjatMorse = {"/", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-",".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--",
            "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--", "..--..", "---...", "-..-",
            ".", ".-...","-.-.-.","-.--.","-.--.-","-...-",".-.-."};

    private String Fjalia;
    private String[] FjaliaeKoduar;
    private int wordlength;

    public MorseEncoder(String sentence) {
        Fjalia = sentence;

        FjaliaEShtypur = sentence.toUpperCase();
        wordlength = FjaliaEShtypur.length();
        FjaliaeKoduar = new String[wordlength];
        ChangeCode();
    }

    public void ChangeCode () {
        char[] letarray = FjaliaEShtypur.toCharArray();
        for (int i = 0; i < wordlength; i++) {
            for (int j = 0; j < Shkronjat.length; j++) {
                if (letarray[i] == Shkronjat[j]) {
                    FjaliaeKoduar[i] = ShkronjatMorse[j];
                }
            }
        }
    }

    public void OutputMorse () {
        System.out.println("\n" + "\"" + Fjalia + "\"" + " In Morse Code is:");
        for (String s : FjaliaeKoduar) {
            if (s == "@") {
                System.out.print(' ');
            } else {
                System.out.printf("%s ", s);
            }
        }
    }


}