package assignment;

public class Pegs {
	/**
	 * 
	 * @param x
	 *            we check to see if the guess is valid
	 * @return
	 */
	public static boolean valid(String x) {
		int length = x.length();
		if (length != GameConfiguration.pegNumber) {
			return false;
		}
		int i = 0;
		int flag = 0;
		while (i < GameConfiguration.pegNumber) {
			int ColorsSize = GameConfiguration.colors.length;
			int index = 0;
			while (index < ColorsSize) {
				if (x.charAt(i) == GameConfiguration.colors[index].charAt(0)) {
					flag++; // checks to see if every character of the guess
							// code is in the colors array

				}
				index++;

			}
			i++;
		}

		if (flag < GameConfiguration.pegNumber) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 
	 * @param guess
	 *            this is the guess code
	 * @param secret
	 *            this is the secret code
	 * @return return the string with 0's where a character in the guess code
	 *         and secret code are correctly
	 */
	public static String BlackPegs(String guess, String secret) {
		int i = 0;
		int length = secret.length();
		String secretCpy = String.format(secret);
		while (i < GameConfiguration.pegNumber) {
			if (guess.charAt(i) == secret.charAt(i)) {
				if (i == 0) {
					secretCpy = '0' + secret.substring(i + 1, length);
				} else if (i != length - 1) {
					secretCpy = secretCpy.substring(0, i) + '0' + secretCpy.substring(i + 1, length);

				} else if (i == length - 1) {
					secretCpy = secretCpy.substring(0, length - 1) + '0';
				} // every time there is a color from the code correctly placed,
					// a 0 replaces the color in the secret code

			}
			i++;
		}
		return secretCpy;
	}

	/**
	 * 
	 * @param guess
	 *            this is the guess code
	 * @param secretCpy
	 *            this is the secret code with 0's where black pegs are
	 * @return the secret code with 1's where the white pegs should be
	 */
	public static StringBuilder WhitePegs(String guess, String secretCpy) {

		StringBuilder string = new StringBuilder(secretCpy);
		StringBuilder remove = new StringBuilder(guess);
		int length = secretCpy.length();
		int j = 0;
		int k = 0;
		while (k < GameConfiguration.pegNumber) {
			if (string.charAt(k) == '0') {
				remove.setCharAt(k, '2'); // if there is a 0 in the secret code,
											// we put a 2 in the guess code at
											// the same index
			}
			k++;
		}
		for (int i = 0; i < GameConfiguration.pegNumber; i++) {
			if (string.charAt(i) == '0') {
				if (i == length - 1) {
					return string;
				}
			}
			for (j = 0; j < GameConfiguration.pegNumber; j++) {
				if (string.charAt(i) == remove.charAt(j)) {
					string.setCharAt(i, '1'); // if there's a peg that is
												// incorrecly placed, we put a 1
					remove.setCharAt(j, '3'); // in the secret code and a 3 in
												// the guess code

				}

			}

		}
		return string;
	}

	/**
	 * 
	 * @param string
	 *            this contains 1 in the string for every white pegs.
	 * @return the number of white pegs
	 */
	public static int numberOfPegs(StringBuilder string) {
		int count = 0; // counts the number of 1's
		for (int i = 0; i < GameConfiguration.pegNumber; i++) {
			if (string.charAt(i) == '1') { // for every '1' in the string, a
											// white peg is generated
				count++;
			}
		}
		return count; // returning the number of white pegs
	}

	/**
	 * 
	 * @param secretCpy
	 *            Has the secret code and is used to get the number of Black
	 *            Pegs
	 * @param WhitePegs
	 *            Has the number of White Pegs
	 * @return the number of black pegs to determine if the player has won or
	 *         not
	 */
	public static int GenerateBlackPegs(StringBuilder secretCpy, int WhitePegs) {
		int count = 0;
		for (int i = 0; i < GameConfiguration.pegNumber; i++) {
			if (secretCpy.charAt(i) == '0') { // the number of blackpegs is the
												// number of 0's in the secret
												// code

				count++; // increment for every '0' in the secret code
			}

		}
		for (int j = 0; j < GameConfiguration.pegNumber; j++) {

		}
		if (count == 0 && WhitePegs == 0) { // prints the number of white pegs
											// and black pegs
			System.out.println("Result: No pegs");
		} else if (count == 1 && WhitePegs == 0) {
			System.out.println("Result:  1 black peg");
		} else if (count == 0 && WhitePegs == 1) {
			System.out.println("Result:  1 white peg");
		} else if (count > 1 && WhitePegs == 0) {
			if (count == GameConfiguration.pegNumber && WhitePegs == 0) {
				System.out.println("Result:  " + count + " black pegs – You win!!");
				return GameConfiguration.pegNumber;
			} else {
				System.out.println("Result:  " + count + " black pegs");
			}
		} else if (count == 0 && WhitePegs > 1) {
			System.out.println("Result:  " + WhitePegs + " white pegs");
		} else if (count == 1 && WhitePegs > 1) {
			System.out.println("Result:  " + count + " black peg, " + WhitePegs + " white pegs");
		} else if (count > 1 && WhitePegs == 0) {
			System.out.println("Result:  " + count + " black pegs, " + WhitePegs + " white peg");
		} else if (count == 1 && WhitePegs == 1) {
			System.out.println("Result:  1 black peg, 1 white peg");
		} else {
			System.out.println("Result:  " + count + " black pegs, " + WhitePegs + " white pegs");
		}
		return count;
	}
}
