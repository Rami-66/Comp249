// ------------------------------------------------
r// Assignment 1
// Question: Part I
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

import java.util.Random;
import java.util.Scanner;

/**
 * @author Fouad Meida and Rami Al Najem 
 * The LadderAndSnake class implements methods to play Ladder And Snakes game.
 * Due date: February 6th, 2023
 */

public class LadderAndSnake {
	private int[][] board = new int[11][10];
	private Players[] players;
    private boolean on;
    private Random dice;
    
    // Defining a Scanner object so it will be used in some methods.
    Scanner keyboard = new Scanner(System.in);
    
    /**
     * @return the array of two players into the driver (PlayLadderAndSnake class).
     */
    public Players[] getPlayers()
    {
    	return players;
    }
    
    /**
     *  @return the boolean value of on because it will be used in the while loop
     *  in the driver (PlayLadderAndSnake class).
     */
    public boolean getOn()
    {
    	return on;
    }
    
    /**
     * This method initializes game's board so each square gets a value from 1 to 110.
     */
    public void initializeBoard()
    {
    	int counter = 1;
    	for (int i=0; i<board.length; i++)
    		for (int j=0; j<board[i].length; j++)
    		{
    			board[i][j] = counter;
    			counter++;
    		}
    }
    /**
     * @param numPlayers
     * constructor which takes numPlayers as a parameter and evaluates if the user inputs
     * two users, less or more. Display the correct message for every case, set players array length to 2,
     * initialize the on attribute to true and creates a random object with the dice attribute as a reference.
     */
    public LadderAndSnake(int numPlayers)
    {
    	// If the user inputs less than two player, display an error message and exit the program.
        if (numPlayers < 2)
        {
            System.out.println("Error: Cannot execute the game with less than 2 players! Game will exit. ");
			System.out.println("Thank you for using Ladder and Snakes game program!");
            System.exit(0);
        }
        // If the user inputs more than two players, set automatically the number of players to two.
        else if (numPlayers > 2)
        {
            System.out.println("\nInitialization was attempted for " + numPlayers + " member of players. "+
            "However, this is only expected for an extended version of the game. Value will be set to 2 players.");
            players = new Players[2];
            // insert the name of players in the driver class.
        }
        // If the user inputs exactly two players, set the number of players to two.
        else
        {
            players = new Players[2];
        }
        on = true;
        dice = new Random();
    }
    
    /**
     * This method sets the name of every player in the game.
     */
    public void setNameOfPlayers()
    {
    	System.out.print("\nPlease enter the name of the first player: ");
    	for (int k=0, l=1; k<2; k++, l++)
    	{
    		players[k] = new Players(keyboard.nextLine());
    		if (l<2)
                 System.out.print("Please enter the name of the second player: ");
        }
    	System.out.println("");
    }
    
    /** This method
     * @return an random integer value from 1 to 6. The dice value will be used later to add it
     * to the previous square position of a player to get a new square position.
     */
    public int flipDice()
    {
        return dice.nextInt(1,6);
    }
    
    /**
     * This method decides which player should start first. Every player will roll the dice using flipDice() method
     * and sets the number of attempts to 1. A player has the priority to start the game if he gets a higher dice value against
     * the other player.
     */
    public void playersTurn()
    {
    	players[0].setDiceValue(flipDice());
    	players[1].setDiceValue(flipDice());
    	int attempts = 1;
    	System.out.println("Now deciding which player will start playing: ");
    	
    	// If the two players get the same dice value, display a message to break the tie and to repeat rolling dices until
    	// every player gets a different dice value from the opposite player.
    	while(players[0].getDiceValue()==players[1].getDiceValue())
    	{
        	System.out.println("\nIt's " + players[0].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
        	keyboard.nextLine();
    		System.out.println(players[0].getName()+" got a dice value of " + players[0].getDiceValue()+".");
    		System.out.println("\nNow it's " + players[1].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
    		keyboard.nextLine();
    		System.out.println(players[1].getName()+" got a dice value of " + players[1].getDiceValue()+".");
    		attempts++;
    		System.out.println("\nA tie was achieved between "+ players[0].getName() +" and "+ players[1].getName() +". Attempting to break the tie.");
    		System.out.println("");
    		players[0].setDiceValue(flipDice());
        	players[1].setDiceValue(flipDice());
    	}
    	
    	// If the first player has a dice value higher than the opposite player, display a message to inform that the first player
    	// should start the game then it's the second player's turn.
    	if(players[0].getDiceValue() > players[1].getDiceValue())
    	{
        	System.out.println("\nIt's " + players[0].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
        	keyboard.nextLine();
    		System.out.println(players[0].getName()+" got a dice value of " + players[0].getDiceValue()+".");
    		System.out.println("\nNow it's " + players[1].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
    		keyboard.nextLine();
    		System.out.println(players[1].getName()+" got a dice value of " + players[1].getDiceValue()+".");
    		// Using if else statement to print the correct sentence with the number of attempts and the word "attempt" either in singular or plural before 
    		// reaching the final decision to start the game.
    		// If the rolling dice attempt equals to 1, display a message with "attempt" word in singular.
    		if (attempts==1)
    		{
    			System.out.println("\nReached final decision on order of playing: "+players[0].getName()+" then "+players[1].getName()+". It took " + attempts + " attempt"
    				+ " before a decision could be made. ");
        		System.out.println("");
    		}
    		// If the rolling dice attempt is greater than 1, display a message with "attempt" word in plural.
    		else
    		{
    			System.out.println("\nReached final decision on order of playing: "+players[0].getName()+" then "+players[1].getName()+". It took " + attempts + " attempts"
        				+ " before a decision could be made. ");
                System.out.println("");
    		}
    			
    	}
    	// If the second player has a dice value higher than the first player, display a message to inform that the second player
    	// should start the game then it's the first player's turn.
    	else
    	{
        	System.out.println("\nIt's " + players[0].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
        	keyboard.nextLine();
    		System.out.println(players[0].getName()+" got a dice value of " + players[0].getDiceValue()+".");
    		System.out.println("\nNow it's " + players[1].getName() + "'s turn; roll the dice please by pressing the \"Enter\" key. ");
    		keyboard.nextLine();
    		System.out.println(players[1].getName()+" got a dice value of " + players[1].getDiceValue()+".");
    		if (attempts==1)
    		{
    			System.out.println("\nReached final decision on order of playing: "+players[1].getName()+" then "+players[0].getName()+". It took " + attempts + " attempt"
    				+ " before a decision could be made. ");
        		System.out.println("");
    		}    	
    		else
    		{
    			System.out.println("\nReached final decision on order of playing: "+players[1].getName()+" then "+players[0].getName()+". It took " + attempts + " attempts"
        				+ " before a decision could be made. ");
                System.out.println("");
    		}
    	}
    	System.out.println("Press the \"Enter\" key to display game instructions.");
    	keyboard.nextLine();
    }
    /**
     * A method which allow the user to start the game.
     */
    public void startGame()
    {
    	System.out.println("\nPlease press the \"Enter\" key to start the game.");
		System.out.println("\n--------------------------------------------------------------------------------");
		System.out.println("");
		keyboard.nextLine();
    }
    /**
     * @param p
     * A method which takes a p parameter. The player will flip the dice, then the new position will be set.
     * The new position of the player will be evaluated if it equals to 100, has a higher value than 100, has a value that is the bottom of a ladder,
     * has a value that is the head of a snake, and if its position equals to the opposite player's position.
     */
    public void play(Players p) {
        // Flip the dice.
    	int diceNum = flipDice();
    	int newPosition = p.getPosition() + diceNum;
    	// Calculate the corresponding row and column for the newPosition.
    		int row = (newPosition-1)/10;
    		int col = (newPosition-1)%10;
    		// Set the new position of the player using a 2D array.
    		p.setPosition(board[row][col]);
    		
        // Check if the player wins the game.
        if (p.getPosition() == 100)
            {
                on = false;
                System.out.println(p.getName() + " rolled " + diceNum + ". He reached square 100.");
                System.out.println("Congratulations! "+p.getName()+" is the winner of Ladder and Snakes game.");
                System.out.println("Thank you for playing Ladder and Snakes game.");
                System.exit(0);
            }
        // Check if a player gets a position greater than 100:
        else if (p.getPosition() > 100)
        {
                p.setPosition(200-(p.getPosition()));
                System.out.println(p.getName() + " rolled " + diceNum + ". He would exceed square 100 so he must go back to square " + p.getPosition()+".");
                System.out.println("");
        }
        
    	// Check if a player gets a position which is the bottom of a ladder.
        else if (p.getPosition()==1) {
    		p.setPosition(38);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 1 and now is going up to square 38.");
    		System.out.println("\n");
    	}
    	else if(p.getPosition()==4) {
    		p.setPosition(14);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 4 and now is going up to square 14.");
    		System.out.println("\n");    	
    		}
    	else if(p.getPosition()==9) {
    		p.setPosition(31);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 9 and now is going up to square 31.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==21) {
    		p.setPosition(42);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 21 and now is going up to square 42.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==28) {
    		p.setPosition(84);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 28 and now is going up to square 84.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==36) {
    		p.setPosition(44);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 36 and now is going up to square 44.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==51) {
    		p.setPosition(67);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 51 and now is going up to square 67.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==71) {
    		p.setPosition(91);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 71 and now is going up to square 91.");
    		System.out.println("\n");   
    		}
    	else if(p.getPosition()==80) {
    		p.setPosition(100);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the bottom of the ladder at square 80 and now is going up to square 100.");
    		System.out.println("\n");
        	// The player reached square 100 after climbing the ladder. Display a Congratulations message.
    		on = false;
            System.out.println("Congratulations! "+p.getName()+" is the winner of Ladder and Snakes game.");
            System.out.println("Thank you for playing Ladder and Snakes game.");
            System.exit(0);
    		}
    	    	
    	// Check if a player gets a position which is the head of a snake.
    	else if(p.getPosition()==16) {
    		p.setPosition(6);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 16 and now is going down to square 6.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==48) {
    		p.setPosition(30);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 48 and now is going down to square 30.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==64) {
    		p.setPosition(60);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 64 and now is going down to square 60.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==79) {
    		p.setPosition(19);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 79 and now is going down to square 19.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==93) {
    		p.setPosition(68);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 93 and now is going down to square 68.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==95) {
    		p.setPosition(24);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 95 and now is going down to square 24.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==97) {
    		p.setPosition(76);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 97 and now is going down to square 76.");
    		System.out.println("\n");
    		}
    	else if(p.getPosition()==98) {
    		p.setPosition(78);
    		System.out.print(p.getName() + " rolled " + diceNum + ". ");
    		System.out.print("He is on the head of the snake at square 98 and now is going down to square 78.");
    		System.out.println("\n");
    		}
        // If the player does not meet any case of the mentioned cases, display only a message that indicates the dice value that he got with 
        // its new position.
    	else {
    		System.out.println(p.getName() + " rolled " + diceNum + ". He is now on square " +p.getPosition() + ".");
    		System.out.println("");
    	}
    	
        // Check if the two players get the same position value and the index of the first player equals 0, then he kicks the second player
        // and the second player must get back to square 0.
    	if(p.equals(players[1]) && p.getPlayerIndex()==0)
    	{
    		System.out.println(p.getName() + " has reached the same square as " + players[1].getName() + ".");
    		System.out.println(p.getName() + " has kicked " + players[1].getName() + "; He must get back to square 0.");
    		System.out.println("");
    		players[1].setPosition(0);
    	}
    	
    	// Check if the two players get the same position value and the index of the second player equals 1, then he kicks the first player
        // and the first player must get back to square 0.
    	if(p.equals(players[0]) && p.getPlayerIndex()==1)
    	{
    		System.out.println(p.getName() + " has reached the same square as " + players[0].getName() + ".");
    		System.out.println(p.getName() + " has kicked " + players[0].getName() + "; He must get back to square 0.");
    		System.out.println("");
    		players[0].setPosition(0);
    	}
    	
    }
}