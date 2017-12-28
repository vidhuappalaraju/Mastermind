package assignment;

import java.util.*;
public class History{
	static ArrayList<String> Guesslist = new ArrayList<String>();

	//static int index = 0;
	/**
	 * 
	 * @param x this is the guess. We're checking if they want the History or not
	 * @param white This adds the number of white pegs in the history
	 * @param black This adds the number of black pegs in the history
	 */
	public static void store(String x, int white, int black){
		if(x.equals("HISTORY")){
			if(Guesslist.size() == 0){
				return;
			}
			System.out.println(Guesslist);
			
		}
		else{
			if(Pegs.valid(x)){
				String guess = String.format(x);
				guess = guess.substring(0, GameConfiguration.pegNumber)+ ' ' + black +'B' +'_'+ white +'W';
				Guesslist.add(guess);
			}
		}
		
	}
	/**
	 * this deletes everything in the Guesslist. The Guesslist is deleted every time a game finish
	 */
	public static void deleteHistory(){
		int size = Guesslist.size();
		while(size != 0){
			Guesslist.remove(0);
			size--;
		}
		
		
	}
	
	
}
