package assignment;

public class Driver {
	public static void main(String[] args) {
		boolean testing;
		if (args.length != 0) {
			if (args[0] == "1") {
				testing = true;
			} else
				testing = false;
		} else {
			testing = false;
		}
		Game Game = new Game(testing);
		Game.runGame();

	}
}
