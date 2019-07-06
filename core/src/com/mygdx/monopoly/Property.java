package com.mygdx.monopoly;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Property super class 
 * Each property has a price, color, street, thumbnail with
 * locations for specific players, and a location on the board
 */
public class Property {

	private int mortgageValue;
	private int price;
	private String colour;
	private int street;
	private boolean mortgaged;
	private Player player;
	private PropertyGroup group;
	private int player1X;
	private int player1Y;
	private int player2X;
	private int player2Y;
	private TextureRegion thumbnail;
	private int propertyX;
	private int propertyY;

	public Property(int mortgageValue, int price, String colour, int street, Player player, int player1X, int player1Y,
			int player2X, int player2Y, TextureRegion thumbnail, int propertyX, int propertyY) {
		this.street = street;
		this.price = price;
		this.mortgageValue = mortgageValue;
		this.colour = colour;
		this.player = player;
		this.player1X = player1X;
		this.player1Y = player1Y;
		this.player2X = player2X;
		this.player2Y = player2Y;
		this.thumbnail = thumbnail;
		this.propertyX = propertyX;
		this.propertyY = propertyY;
		mortgaged = false;
	}

	public int getStreet() {
		return street;
	}

	public Player getOwner() {
		return player;
	}

	public void setOwner(Player player) {
		this.player = player;
	}

	public void setGroup(PropertyGroup g) {
		group = g;
	}

	public PropertyGroup getGroup() {
		return group;
	}

	public String getColour() {
		return colour;
	}

	public int getRent() {
		return 0;
	}

	public int getPrice() {
		return price;
	}

	public int getPlayer1X() {
		return player1X;
	}

	public int getPlayer1Y() {
		return player1Y;
	}

	public int getPlayer2X() {
		return player2X;
	}

	public int getPlayer2Y() {
		return player2Y;
	}

	public TextureRegion getThumbnail() {
		return thumbnail;
	}

	public int getPropertyX() {
		return propertyX;
	}

	public int getPropertyY() {
		return propertyY;
	}

	public void getMortgageValue() {

	}
}
