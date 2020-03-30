import java.util.Scanner;
import java.lang.String;

public class TapCode {

    private String matrica[][] = { { "A", "B", "C", "D", "E" }, { "F", "G", "H", "I", "J" },
            { "L", "M", "N", "O", "P" }, { "Q", "R", "S", "T", "U" }, { "V", "W", "X", "Y", "Z" } };

    private String shkronjatEKoduara[][] = { { ". .", ". ..", ". ...", ". ....", ". ....." },
            { ".. .", ".. ..", ".. ...", ".. ....", ".. ....." },
            { "... .", "... ..", "... ...", "... ....", "... ....." },
            { ".... .", ".... ..", ".... ...", ".... ....", ".... ....." },
            { "..... .", "..... ..", "..... ...", "..... ....", "..... ....." } };
    private String input;

    public TapCode(String _input) {
        super();
        this.input = _input.toUpperCase();
    }

    static String indexOf(String[][] ar, int row, int col, String x, String[][] shkronjatEnkriptuara) {
        String stringu = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (ar[i][j].equals(x)) {
                    stringu += shkronjatEnkriptuara[i][j];
                }
            }
        }
        return stringu;
    }

    public void enkripto() {

        if (!isValidEnkripto(this.input)) {
            System.out.println("inputi duhet te kete vetem shkronja");
            System.exit(1);
        }
        char[] inputArray = new char[this.input.length()];
        String stringu = "";
        for (int i = 0; i < this.input.length(); i++) {
            inputArray[i] = this.input.charAt(i);
        }

        for (char ch : inputArray) {
            if (Character.toString(ch).equals("K")) {
                stringu += indexOf(this.matrica, 5, 5, "C", this.shkronjatEKoduara);
                stringu += " ";
                continue;
            }
            stringu += indexOf(this.matrica, 5, 5, Character.toString(ch), this.shkronjatEKoduara);
            stringu += " ";
        };

        System.out.println("Encrypting....");

        System.out.println(stringu);
    }

    public void dekripto() {

        if (!isValidDekripto(this.input)) {
            System.out.println("inputi duhet te kete vetem pika dhe hapsira");
            System.exit(1);
        }
        System.out.println("Decrypting....");

        String[] inputArray = this.input.split("[ ][ ]+");
        String plainString = "";
        for (int i = 0; i < inputArray.length; i++) {
            String[] stringArray = inputArray[i].split("(?<!\\G\\S+)\\s");

            for (String ch : stringArray) {

                plainString += indexOf(this.shkronjatEKoduara, 5, 5, ch, this.matrica);
            };

            plainString += " ";

        }
        System.out.println(plainString);
    }

    public boolean isValidEnkripto(String input) {
        return input.matches("[ A-Z]+");
    }

    public boolean isValidDekripto(String input) {
        return input.matches("[. ]+");
    }
}
