package com.mygdx.monopoly;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for community chest space
 */
public class CommunitySpace implements Space {
	private Board board;
	private int numOfHouses;
	private int numOfHotels;
	private int card;
	private Player player;
	public CommunitySpace(Board b) {
		this.board = b;
	}

	/* 
	 * Selects and displays a community chest card to the player
	 */
	@Override
	public void enter(Player player) {
		card = ThreadLocalRandom.current().nextInt(0, 15 + 1);
		board.getScreen().setCardFocus(card);
		board.getScreen().setSpaceFocus(this);
		board.getScreen().setState(5);
		this.player = player;
	}
	
	/*
	 * Performs the effect of the selected community chest card
	 */
	public void enterResult() {
		if (card == 0) {	// Collect 100
			player.addMoney(100);
			board.getScreen().setState(1);
		}
		else if (card == 1) {	// For each house pay 40$, for each hotel 115$
			for (int i =0; i < player.getColoredProperties().size(); i++) {
				numOfHouses += player.getColoredProperties().get(i).getNumOfHouses();
				numOfHotels += player.getColoredProperties().get(i).getHotel();
			}
			player.addMoney(-40 * numOfHouses);
			player.addMoney(-115 * numOfHotels);
			numOfHouses = 0;
			numOfHotels = 0;
			board.getScreen().setState(1);
		}
		else if (card == 2) {	// Collect 50 from every player
			for (int i=0; i < board.getNumOfPlayers(); i++) {
				board.getPlayer(i).addMoney(-50);
				player.addMoney(50);
			}
			board.getScreen().setState(1);
		}
		else if (card == 3) {	// Advance to go
			player.addMoney(200);
			player.setPosition(0);
			board.getSpace(0).enter(player);
		}
		else if (card == 4) {	// Pay 50
			player.addMoney(-50);
			board.getScreen().setState(1);
		}
		else if (card == 5) {	// Collect 10
			player.addMoney(10);
			board.getScreen().setState(1);
		}
		else if (card == 6) {	// Collect 200
			player.addMoney(200);
			board.getScreen().setState(1);
		}
		else if (card == 7) {	// Go to jail
			player.setPosition(10);
			player.setJailed(true);
			board.getScreen().setState(1);
		}
		else if (card == 8) {	// Collect 20
			player.addMoney(20);
			board.getScreen().setState(1);
		}
		else if (card == 9) {	// Collect 100
			player.addMoney(100);
			board.getScreen().setState(1);
		}
		else if (card == 10) {	// Collect 100
			player.addMoney(100);
			board.getScreen().setState(1);
		}
		else if (card == 11) {	// Pay 100
			player.addMoney(-100);
			board.getScreen().setState(1);
		}
		else if (card == 12) {	// Pay 150
			player.addMoney(-150);
			board.getScreen().setState(1);
		}
		else if (card == 13) {	// Get out of jail free card
			player.addJailFreeCard("chest");
			board.getScreen().setState(1);
		}
		else if (card == 14) {	// Collect 45
			player.addMoney(45);
			board.getScreen().setState(1);
		}
		else if (card == 15) {	// Collect 25
			player.addMoney(25);
			board.getScreen().setState(1);
		}
	}

}
