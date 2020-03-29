package Tapcode;

import java.lang.String;
import java.util.Scanner;

public class Tapcode {

	private String matrica[][] = { { "A", "B", "C", "D", "E" }, { "F", "G", "H", "I", "J" },
			{ "L", "M", "N", "O", "P" }, { "Q", "R", "S", "T", "U" }, { "V", "W", "X", "Y", "Z" } };

	private String shkronjatEKoduara[][] = { { ". .", ". ..", ". ...", ". ....", ". ....." },
			{ ".. .", ".. ..", ".. ...", ".. ....", ".. ....." },
			{ "... .", "... ..", "... ...", "... ....", "... ....." },
			{ ".... .", ".... ..", ".... ...", ".... ....", ".... ....." },
			{ "..... .", "..... ..", "..... ...", "..... ....", "..... ....." } };
	private String input;

	public Tapcode(String _input) {
		this.input = _input;
	}

	static String indexOf(String[][] ar, int row, int col, String x, String[][] shkronjatEnkriptuara) {
		String stringu = "";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (ar[i][j].equals(x)) {
					stringu += shkronjatEnkriptuara[i][j];
//                    stringu += " ";
				}
			}
		}
		return stringu;
	}

	public void enkripto() {
		char[] inputArray = new char[this.input.length()];
		String stringu = "";
		for (int i = 0; i < this.input.length(); i++) {
			inputArray[i] = this.input.charAt(i);
		}

		for (char ch : inputArray) {
			if (Character.toString(ch) == " ") {
				stringu += " ";
			}
			stringu += indexOf(this.matrica, 5, 5, Character.toString(ch), this.shkronjatEKoduara);
			stringu += " ";
		}
		;

		System.out.println("Encrypting....");

		System.out.println(stringu);
	}

	public void dekripto() {
		String[] stringArray = this.input.split("(?<!\\G\\S+)\\s");
//        System.out.println(stringArray);
		String plainString = "";
		for (String ch : stringArray) {
			if (ch.equals(" ")) {
				plainString += " ";
			}
			plainString += indexOf(this.shkronjatEKoduara, 5, 5, ch, this.matrica);
		}
		;

		System.out.println("Decrypting....");

		System.out.println(plainString);
	}

}
