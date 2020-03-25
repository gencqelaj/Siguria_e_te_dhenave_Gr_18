package Tapcode;

import java.util.Scanner;
import java.lang.String;

public class Tapcode {

	public static void main(String[] args) {
//       String input = "GENCI";
		String stringu = "";
		String plainString = "";
		String matrica[][] = { { "A", "B", "C", "D", "E" }, { "F", "G", "H", "I", "J" }, { "L", "M", "N", "O", "P" },
				{ "Q", "R", "S", "T", "U" }, { "V", "W", "X", "Y", "Z" } };

		String shkronjatEKoduara[][] = { { ". .", ". ..", ". ...", ". ....", ". ....." },
				{ ".. .", ".. ..", ".. ...", ".. ....", ".. ....." },
				{ "... .", "... ..", "... ...", "... ....", "... ....." },
				{ ".... .", ".... ..", ".... ...", ".... ....", ".... ....." },
				{ "..... .", "..... ..", "..... ...", "..... ....", "..... ....." } };

		System.out.print("Sheno enkriptim ose dekriptim : ");
		Scanner scanner = new Scanner(System.in);
		String tipi = scanner.nextLine();

		System.out.print("Jepe inputin : ");
		Scanner scanner_input = new Scanner(System.in);
		String input = scanner_input.nextLine();

		switch (tipi.toLowerCase()) {
		case "enkriptim":
			enkripto(input.toUpperCase(), matrica, shkronjatEKoduara);
			break;
		case "dekriptim":
			dekripto(input, shkronjatEKoduara, matrica);
			break;
		default:
			System.out.println("Gabim ne tip");
		}

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

	static void enkripto(String input, String matrica[][], String shkronjatEKoduara[][]) {
		char[] inputArray = new char[input.length()];
		String stringu = "";
		for (int i = 0; i < input.length(); i++) {
			inputArray[i] = input.charAt(i);
		}

		for (char ch : inputArray) {
			if (Character.toString(ch) == " ") {
				stringu += " ";
			}
			stringu += indexOf(matrica, 5, 5, Character.toString(ch), shkronjatEKoduara);
			stringu += " ";
		}
		;

		System.out.println("Encrypting....");

		System.out.println(stringu);
	}

	static void dekripto(String stringu, String[][] shkronjatEKoduara, String[][] matrica) {
		String[] stringArray = stringu.split("(?<!\\G\\S+)\\s");
//       System.out.println(stringArray);
		String plainString = "";
		for (String ch : stringArray) {
			if (ch.equals(" ")) {
				plainString += " ";
			}
			plainString += indexOf(shkronjatEKoduara, 5, 5, ch, matrica);
		}
		;

		System.out.println("Decrypting....");

		System.out.println(plainString);
	}
}