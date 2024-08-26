// ------------------------------------------------
// Assignment 1
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

/**
 * @author Fouad Meida and Rami Al Najem 
 * The LadderAndSnake class implements methods to play Ladder And Snakes game.
 * Due date: February 6th, 2023
 */

public class Players {
	private int position;
	private String name;
	private int diceValue;
	private static int playerCount = 0;
	private final int playerIndex;
	
	/**
	 * @param name1 name of a Player.
	 * Players Constructor
	 */
	public Players (String name1) {
		position=0;
		name = name1;
		diceValue = 0;
		playerIndex = playerCount;
		playerCount++;
	}
	
	/**
	 * @return position
	 * Getter for this player's position.
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @return name
	 * Getter for this player's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param currentPosition
	 * Setter of this player's position.
	 */
	public void setPosition(int currentPosition) {
		this.position = currentPosition;
	}
	
	/**
	 * @param name
	 * Setter of this player's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return diceValue
	 * Getter for this player's dice value (used in LadderAndSnake class
	 * to determine which player should start the game first.
	 */
	public int getDiceValue()
	{
		return diceValue;
	}
	
	/**
	 * @param diceValue
	 * Setter for this player's dice value.
	 */
	public void setDiceValue(int diceValue)
	{
		this.diceValue = diceValue;
	}
	
	/**
	 * @return playerIndex;
     * Getter for this player's index (used in LadderAndSnake class 
	 * to determine which player should kick his opposite.
	 */
	public int getPlayerIndex()
	{
		return playerIndex;
	}
    
	/**
	 * @param player
	 * Equals method to compare the position of this player with the position of the opposite player.
	 * @return (this.getPosition() == player.getPosition())
	 */
	public boolean equals(Players player)
	{
		return (this.getPosition() == player.getPosition());
	}
}