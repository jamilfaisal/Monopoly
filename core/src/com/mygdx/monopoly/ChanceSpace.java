package com.mygdx.monopoly;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class designed for the chance card space
 */
public class ChanceSpace implements Space {

	private Board board;
	private int numOfHouses;
	private int numOfHotels;
	private Player player;
	private int card;
	private final int GAME_CHANCE = 6;
	
	public ChanceSpace(Board b) {
		this.board = b;
		numOfHouses = 0;
		numOfHotels = 0;
	}

	/* 
	 * Selects and displays a chance card to the player
	 */
	@Override
	public void enter(Player player) {
		card = ThreadLocalRandom.current().nextInt(0, 14 + 1);
		board.getScreen().setCardFocus(card);
		board.getScreen().setSpaceFocus(this);
		// Changes game state to the chance state
		board.getScreen().setState(GAME_CHANCE);
		this.player = player;
		
	}
	
	/* 
	 * Performs the effect of the selected chance card
	 */
	public void enterResult() {
		if (card == 0) {
			//Guaranteed to pass go
			player.addMoney(200);
			player.setPosition(5);
			board.getSpace(5).enter(player);
			
		} else if (card == 1) {	// Advance to nearest railroad
			if (player.getPosition() == 7) {
				player.setPosition(15);
				board.getSpace(15).enter(player);
			} else if  (player.getPosition() == 22) {
				player.setPosition(25);
				board.getSpace(25).enter(player);
			}  else {
				player.addMoney(200);
				player.setPosition(5);
				board.getSpace(5).enter(player);
			}
			
		} else if (card == 2) {	// Advance to boardwalk
			player.setPosition(39);
			board.getSpace(39).enter(player);
		
		} else if (card == 3) {	// Advance to St.Charles Place, collect 200 if passing go
			if (player.getPosition()>11) {
				player.addMoney(200);
			}
			player.setPosition(11);
			board.getSpace(11).enter(player);
			
		} else if (card == 4) {	// Bank pays a dividend of 50
			player.addMoney(50);
			board.getScreen().setState(1);
		
		} else if (card == 5) {	// Advance to go
			player.addMoney(200);
			player.setPosition(0);
			board.getSpace(0).enter(player);
		}
		else if (card == 6) {	// Advance to Illinois Avenue
			if (player.getPosition() > 24) {
				player.addMoney(200);
			}
			player.setPosition(24);
			board.getSpace(24).enter(player);
		}
		else if (card == 7) {	// Collect 150
			player.addMoney(150);
			board.getScreen().setState(1);
		}
		else if (card == 8) {	// Go to jail
			player.setPosition(10);
			player.setJailed(true);
			board.getScreen().setState(1);
		}
		else if (card == 9) {	// Pay each player 50
			for (int i=0; i < board.getNumOfPlayers(); i++) {
				board.getPlayer(i).addMoney(50);
				player.addMoney(-50);
			}
			board.getScreen().setState(1);
		}
		else if (card == 10) {	// Go back 3 spaces
			player.setPosition(player.getPosition() - 3);
			board.getSpace(player.getPosition()).enter(player);
		}
		else if (card == 11) {	// For each house pay 25, for each hotel pay 100
			for (int i =0; i < player.getColoredProperties().size(); i++) {
				numOfHouses += player.getColoredProperties().get(i).getNumOfHouses();
				numOfHotels += player.getColoredProperties().get(i).getHotel();
			}
			player.addMoney(-25 * numOfHouses);
			player.addMoney(-100 * numOfHotels);
			numOfHouses = 0;
			numOfHotels = 0;
			board.getScreen().setState(1);
		}
		else if (card == 12) {	// Advance to the nearest utility station
			if (player.getPosition() == 7 || player.getPosition() == 36) {
				player.setPosition(12);
				board.getSpace(12).enter(player);
			}
			else if (player.getPosition() == 22) {
				player.setPosition(28);
				board.getSpace(28).enter(player);
			}
		}
		else if (card == 13) {	// Get out of jail free card
			player.addJailFreeCard("chance");
			board.getScreen().setState(1);
		}
		else if (card == 14) {	// Pay tax of 15
			player.addMoney(-15);
			board.getScreen().setState(1);
		}
	}

}
