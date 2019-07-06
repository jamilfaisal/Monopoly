package com.mygdx.monopoly;

import java.util.concurrent.ThreadLocalRandom;

import com.mygdx.monopoly.screens.GameScreen;

public class GameHandler {

	private Board board;
	private int doubleCounter;
	private boolean doubles;
	private Player currentPlayer;
	private GameScreen screen;
	private int rollOne;
	private int rollTwo;
	/**
	 * Creating the gameHandler
	 * @param board referencing the board
	 * @param gamescreen referencing the game screen
	 * setting the current rolls to zero so they do not appear
	 */
	public GameHandler(Board board, GameScreen gamescreen) {
		this.board = board;
		screen = gamescreen;
		rollOne =0;
		rollTwo =0;
	}

	/**
	 * Used in the gameNormal state
	 * Starts the turn for the player
	 * 
	 * @param player
	 */
	public void startTurn(Player player) {
		if (currentPlayer != player) {
			doubles = false;
			doubleCounter = 0;
		}
		currentPlayer = player;
		if(currentPlayer.getJailed()) {
			screen.setState(2);
		} else {
			screen.setState(3);
		}
	}
	/** 
	 * Checks if player is jailed, if not or the player got a double, do a normal roll and enter the space the player lands on.
	 * If player is going to pass go, it gives the player $200
	 */
	public void startRoll() {
		rollOne = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		rollTwo = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		if (currentPlayer.getJailed() == false || rollOne == rollTwo) {
			if (currentPlayer.getJailed()) {
				currentPlayer.setJailed(false);
			}
			if (rollOne == rollTwo) {
				doubleCounter++;
				doubles = true;
			}
			if (doubleCounter >= 3) {
				currentPlayer.setJailed(true);
				board.getScreen().setState(1);
			}
			
			int destination=currentPlayer.getPosition()+rollOne+rollTwo;
			
			if (destination > 39) {
				currentPlayer.addMoney(200);
				destination -= 40;
			}
			
			currentPlayer.setPosition(destination);
			board.getSpace(destination).enter(currentPlayer);
		} else {
			board.getScreen().setState(1);
		}
	}
	
	/**
	 * used to buy a property when the player selects buy in the BUY PROPERTY state
	 * @param propertyIndex the property being bought
	 */
	public void buyProperty(int propertyIndex) {
		Property property = board.getProperties().get(propertyIndex);
		board.getBank().transferProperty(property, currentPlayer);
		currentPlayer.transferMoney(property.getPrice(), board.getBank());
		screen.setState(1);
	}

	/**
	 * Ending the current players turn and restarting the turn for the next player
	 */
	public void endTurn() {
		doubleCounter = 0;
		doubles = false;
		rollOne = 0;
		rollTwo = 0;
		screen.changeCurrentPlayer();
		screen.setState(0);
	}
	 
	/**
	 * Used to check if the current player got a double
	 * @return a boolean of whether the player got a double this turn so that the player gets another turn
	 */
	public boolean getDouble() {
		return doubles;
	}
	
	/**
	 * Used to get the players rolls
	 * @return a integer of the rolls
	 */
	public int getRollOne() {
		return rollOne;
	}
	
	public int getRollTwo() {
		return rollTwo;
	}
	
	/**
	 * Used to set if the players doubles from true to false when he has used his turn
	 */
	public void toggleDoubles() {
		doubles  = !doubles;
	}
}
