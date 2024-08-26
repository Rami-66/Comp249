// ------------------------------------------------
// Assignment 1
// Question: Part II
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

/**
 * This program aims to run Ladder and Snakes game. The users will be prompted to select the first choice to start the game or either select 2 to quit the game.
 * In addition, every player must input its name. Then, game instructions will be displayed before starting to play the game.
 * If a player reaches exactly the square 100, he wins the game and a congratulations message will be displayed.
 * Finally, a closing message will be displayed to terminate the game program.
 */

import java.util.Scanner;

/**
 * @author Fouad Meida and Rami Al Najem
 * The LadderAndSnake class implements methods to play Ladder And Snakes game.
 * Due date: February 6th, 2023
 */
public class PlayLadderAndSnake {
	
	/**
	 * @param args
	 * Main method to run PlayLadderAndSnake class that takes an args parameter of type String[]
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Welcome message.
		System.out.println("Welcome to Ladder and Snakes game!");
		System.out.println("\n--------------------------------------------------------------------------------");
		System.out.println("");
		// Display the main menu of the game.
		System.out.println("Please select 1 to start the game or 2 to quit the game:");
		System.out.println("1- Start the game.");
		System.out.println("2- Quit the game.");
		// Prompt the user to input either 1 or 2.
		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();
		
		if (choice == 1)
		{
			// Prompt the user to insert the number of players for this game.
			System.out.print("\nPlease enter the number of players: ");
			int numPlayers = keyboard.nextInt();
			
			// Create an object called game of LadderAndSnake class.
			LadderAndSnake game = new LadderAndSnake(numPlayers);
			
			// Prompt the user to set the name of players in this game.
			game.setNameOfPlayers();
			Players[] players = game.getPlayers();
			
			// Decide which player starts first.
	        game.playersTurn();
	        
	        // Initializing the board and display game instructions to the players.
	        game.initializeBoard();
	        //game.printBoard();
			System.out.println("\n--------------------------------------------------------------------------------");
	        System.out.println("\nGame instructions:");
	        System.out.println("\n- Every player must roll its dice and move their pieces starting from square 0 (which is outside the board) with the value of the dice "
	        		+ "\n  until a player reaches EXACTLY the square 100.");
	        System.out.println("- If a player is close to 100, and the dice value exceeds the maximum possible moves, the player moves backward with the excessive amount. "+
	        		"\n  For instance, if a player is at square 96 and the dice value is 5, then he moves to 99 (that is 4 moves to 100, then 1 move backward to 99).");
	        System.out.println("- If a player reaches a square has a bottom of a ladder, then the player moves up to the square that has the "
	        		+ "top of the ladder. \n  For instance, if a player is at the following squares 1, 4, 9, 21, 28, 36, 51, 71, 80; then he "
	        		+ "moves up to squares 38, 14, 31, 42, 84, 44, 67, 91, 100 respectively.");
	        System.out.println("- If a player reaches a square has a head of a snake, then he moves down the square that has the "
	        		+ "tail of the snake. \n  For instance, if a player is at the following squares 16, 48, 64, 79, 93, 95, 97, 98; then he "
	        		+ "moves down to squares 6, 30, 60, 19, 68, 24, 76, 78 respectively.");
	        System.out.println("- Special Condition: The two players cannot be at the same square at the same time. If this occurs, the player who reaches that square last " + 
	        		"\n  will kick the first one and resets it to go back to square 0 (outside the board; where he/she must restart again from that point). " +
	        		"\n  For example, Player 2 is at cell 24, and Player 1 flips the dice and moves to square 27 then Player 2 flips and gets 3, which moves him/her to" + 
	        		"\n  square 27; this will result in Player 1 being reset to square 0.");
	        System.out.println("\n\nGood Luck!");
			System.out.println("\n--------------------------------------------------------------------------------");
			
			// Prompt the user to start the game.
			game.startGame();
			
			// Starting the game.
	        // If the first player gets a higher dice value than the second player's dice value, execute the following steps.
				if (players[0].getDiceValue()>players[1].getDiceValue())
					while(game.getOn())
					{
						// Use for loop to alternate turns between the first and the second player.
						for (int i=0; i<2; i++)
						{
							game.play(players[i]);
						}
						System.out.println("Game not over; flipping again. ");
						System.out.println("\n--------------------------------------------------------------------------------");
						System.out.println("");
					}
		        // If the second player gets a higher dice value than the first player's dice value, execute the following steps.
				else
					while(game.getOn())
					{
						for (int i=1; i>=0; i--)
						{
							game.play(players[i]);
						}
						System.out.println("Game not over; flipping again. ");
						System.out.println("\n--------------------------------------------------------------------------------");
						System.out.println("");
					}
				
	     }
			
		else
		{
			// Display a closing message.
			System.out.println("You quit the game!");
			System.out.println("Thank you for using Ladder and Snakes game program!");
			keyboard.close();
			System.exit(0);
		}

	}

}