package com.mygdx.monopoly;

import java.util.ArrayList;

/**
 *	Class for creating players
 *	Each player can own properties, get out of jail free cards, money, and a game piece
 */
public class Player {
	private int money;
	private ArrayList<ColorProperty> ownedColoredProperties;
	private ArrayList<UtilityProperty> ownedUtilityProperties;
	private ArrayList<StationProperty> ownedStationProperties;
	private boolean chanceJailFreeCard;
	private boolean chestJailFreeCard;
	private String name;
	private int position;
	private boolean jailed;
	
	/**
	 * Starting player for users
	 * @param pieceIndex Selected game piece
	 * @param name Player's name
	 */
	public Player (int pieceIndex, String name) {
		money = 1500;
		chanceJailFreeCard = false;
		chestJailFreeCard = false;
		ownedColoredProperties = new ArrayList<ColorProperty>();
		ownedUtilityProperties = new ArrayList<UtilityProperty>();
		ownedStationProperties = new ArrayList<StationProperty>();
		this.name = name;
		position = 0;
	} 
	
	/**
	 * Player instance for the bank
	 */
	public Player () {
		money = 999999;
		ownedColoredProperties = new ArrayList<ColorProperty>();
		ownedUtilityProperties = new ArrayList<UtilityProperty>();
		ownedStationProperties = new ArrayList<StationProperty>();
		name = "bank";
	}
	
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int amount) {
		money = amount;
	}
	
	/**
	 * Transfers money to the selected player
	 * @param amount The amount to be transferred
	 * @param player The receiving player
	 */
	public void transferMoney(int amount, Player player) {
		money -= amount;
		player.addMoney(amount);
	}
	
	/**
	 * Adds property to the player's array of owned properties
	 * @param p The property bought by the player
	 */
	public void addProperty (Property p) {
		if (p.getColour() == "station") {
			ownedStationProperties.add((StationProperty) p);
		}
		else if (p.getColour() == "utility") {
			ownedUtilityProperties.add((UtilityProperty) p);
		}
		else {
			ownedColoredProperties.add((ColorProperty) p);
		}
	}
	public ArrayList<ColorProperty> getColoredProperties() {
		return ownedColoredProperties;
	}
	public void setPosition (int p) {
		position = p;
	}
	
	public int getPosition () {
		return position;
	}
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void loseMoney(int amount) {
		money -= amount;
	}
	public boolean getJailFreeCards(String card) {
		if (card == "chance") {
			return chanceJailFreeCard;
		}
		else if (card == "chest") {
			return chestJailFreeCard;
		}
		else {
			return false;
		}
	}

	public void useJailCard() {
		if (chanceJailFreeCard) {
			chanceJailFreeCard = false;
		} else {
			chestJailFreeCard = false;
		}
	}
	
	
	/**
	 * Adds a get out of jail free card depending on the origin of the card
	 * @param card The deck containing the card
	 */
	public void addJailFreeCard(String card) {
		if (card == "chance") {
			chanceJailFreeCard = true;
		}
		else if (card == "chest") {
			chestJailFreeCard = true;
		}
	}
	
	public boolean getJailed() {
		return jailed;
	}
	
	public void setJailed(boolean jail) {
		jailed = jail;
	}
	
	/**
	 * Trades properties between players
	 * @param p	 The property to be traded
	 * @param player The receiving player
	 */
	public void transferProperty(Property p, Player player) {
		if (p.getColour() == "station") {
			ownedStationProperties.remove((StationProperty) p);
		}
		else if (p.getColour() == "utility") {
			ownedUtilityProperties.remove((UtilityProperty) p);
		}
		else {
			ownedColoredProperties.remove((ColorProperty) p);
		}
		player.addProperty(p);
		p.setOwner(player);
	}
	
}
