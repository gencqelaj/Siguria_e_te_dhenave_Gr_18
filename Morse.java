import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Morse {

    public static void main(String[] args) {

        int choice=0;
        String phrase,code;
        boolean cont = true;

        Scanner input = new Scanner(System.in);
        System.out.println("Jepni tekstin:");
        String teksti=input.nextLine();

        do{
            try{


                System.out.println("Deshironi te kodoni apo te dekodoni mesazhin e sipershenuar ?");
                System.out.println("\n1.Kodoje mesazhin");
                System.out.println("2.Dekodoje mesazhin");

                choice = input.nextInt();
                if(choice < 1 || choice > 2){
                    while(choice < 1 || choice > 2){
                        System.out.println("Invalid Input.");
                        System.out.println("\n1.Encode");
                        System.out.println("2.Decode");
                        choice = input.nextInt();
                    }
                }
                cont=false;
            }
            catch(InputMismatchException in){
                System.out.println("Invalid Input");
                input.nextLine();
            }

        } while(cont);

        if(choice == 1){
            Encoder encoder=new Encoder(teksti);
            encoder.OutputMorse();
        }
        if(choice == 2){
            code = input.nextLine();
            Decoder decode = new Decoder(teksti);
        }
    }

    protected static class Encoder {
        private String FjaliaEShtypur;
        private  final char[] Shkronjat = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
                'R',
                'S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','.',',','?',':','/','&'};
        private  final String[] ShkronjatMorse = {"/",".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                "-.-",
                ".-..","--","-.","---",
                ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..","-----",".----","..---","...--",
                "....-",".....","-....","--...","---..","----.",".-.-.-","--..--","..--..","---...","-..-.",".-..."};


        private String Fjalia;
        private String[] FjaliaeKoduar;
        private int wordlength;

        public Encoder(String sentence) {
            Fjalia = sentence;
            FjaliaEShtypur = sentence.toUpperCase();
            wordlength = FjaliaEShtypur.length();
            FjaliaeKoduar = new String[wordlength];
            ChangeCode();
        }

        public void ChangeCode() {
            char[] letarray = FjaliaEShtypur.toCharArray();
            for(int i=0; i<wordlength; i++){
                for(int j=0; j<Shkronjat.length; j++){
                    if (letarray[i] == Shkronjat[j]){
                        FjaliaeKoduar[i] = ShkronjatMorse[j];
                    }
                }
            }
        }

        public void OutputMorse(){
            System.out.println("\n" + "\"" + Fjalia + "\"" + " In Morse Code is:");
            for(String s : FjaliaeKoduar){
                if(s == "@"){
                    System.out.print(' ');
                }
                else{
                    System.out.printf("%s ",s);
                }
            }
        }
    }
    protected static class Decoder {

        private String FjaliaEShtypur;
        private  final String[] ShkronjatMorse = {"/",".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                "-.-",
                ".-..","--","-.","---",
                ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..","-----",".----","..---","...--",
                "....-",".....","-....","--...","---..","----.",".-.-.-","--..--","..--..","---...","-..-.",".-..."};

        private  final char[] Shkronjat = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
                'R',
                'S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','.',',','?',':','/','&'};


        private String[] wordamt;
        private int wordlength;

        public Decoder(String p) {
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
}


