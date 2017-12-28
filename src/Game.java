package assignment;


import java.util.Scanner;

public class Game {

	boolean TestMode;

	/**
	 * This tells you all the rules and how many colors and guesses you have.
	 */
	public static void Greeting() {
		System.out.print("Welcome to Mastermind.  Here are the rules.\n"
				+ "The computer will think of a secret code. The code consists of " + GameConfiguration.pegNumber
				+ "\ncolored pegs. " + "The pegs MUST be one of six colors: blue, green, orange, purple,\n"
				+ "red, or yellow. A color may appear more than once in the code. You\n"
				+ "try to guess what colored pegs are in the code and what order they\n"
				+ "are in. After you make a valid guess the result (feedback) will be\n" + "displayed.\n"
				+ "The result consists of a black peg for each peg you have guessed\n"
				+ "exactly correct (color and position) in your guess.  For each peg in\n"
				+ "the guess that is the correct color, but is out of position, you get\n"
				+ "a white peg.  For each peg that is fully incorrect, you get no\n" + "feedback.\n"
				+ "Only the first letter of the color is displayed. B for Blue, R for\n" + "Red, and so forth.\n"
				+ "When entering guesses you only need to enter the first character of\n"
				+ "each color as a capital letter.\n" + "You have " + GameConfiguration.guessNumber
				+ " guesses to figure out the secret code or you lose the\n" + "game.  Are you ready to play? (Y/N):");
	}

	/**
	 * 
	 * @param reveal
	 *            this tells us if we're running the game in Test
	 */
	public Game(boolean reveal) {
		if (reveal == true) {
			TestMode = true;
		} else {
			TestMode = false;
		}

	}

	/**
	 * this runs the game. Keeps going until the player doesn't want to play
	 */
	public void runGame() {
		Game.Greeting();
		boolean play = true;
		Scanner scanIn = new Scanner(System.in);
		String x = "X";
		while (play) {
			while (!x.equals("Y") && !x.equals("N")) {
				x = scanIn.nextLine();
			}
			if (x.equals("Y")) {

				String secret = SecretCodeGenerator.getInstance().getNewSecretCode();
				System.out.print("Generating secret code .... ");
				if (TestMode == false) {
					System.out.print("(for this example the secret code is ");
					System.out.println(secret + ") ");
				}
				int guesses = GameConfiguration.guessNumber;

				while (play) {
					if (guesses != 1) {
						System.out.println("You have " + guesses + " guesses left.");
					} else {
						System.out.println("You have " + guesses + " guess left.");
					}
					System.out.println("What is your next guess?");
					System.out.println("Type in the characters for your guess and press enter.");
					System.out.print("Enter guess:");
					x = scanIn.nextLine();
					x = x.trim();	//removes spaces
					if (!x.equals("HISTORY")) {
						if (Pegs.valid(x)) {

							String secretCpy = Pegs.BlackPegs(x, secret);
							StringBuilder secretCpy2 = Pegs.WhitePegs(x, secretCpy);
							int WhitePegs = Pegs.numberOfPegs(secretCpy2);
							System.out.print(x + "->");
							int winnerCheck = Pegs.GenerateBlackPegs(secretCpy2, WhitePegs);
							guesses--;
							if (winnerCheck == GameConfiguration.pegNumber) {
								System.out.println("Do you want to play again?");
								History.deleteHistory();
								break;
							}
							if (guesses == 0) {
								System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
								System.out.println("Do you want to play again?");
								History.deleteHistory();
								break;
							}
							History.store(x, WhitePegs, winnerCheck);
						} else {
							System.out.print(x + "->");
							System.out.println("INVALID GUESS");
						}
					} else {
						int rubbish = 0;			//rubbish and rubbish2 are just variables initialized to use the store function
						int rubbish2 = 0;
						History.store(x, rubbish, rubbish2);
					}

				}

			} else if (x.equals("N")) {
				scanIn.close();
				play = false;
			}

		}

	}

}
