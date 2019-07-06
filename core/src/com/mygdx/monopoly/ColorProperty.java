package com.mygdx.monopoly;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class for colored properties
 * Colored properties have a rent based on number of houses or hotel
 */
public class ColorProperty extends Property{
	private ArrayList<Integer> rents = new ArrayList<Integer>();
	private int hotelRent;
	private int numOfHouses;
	private int hotel;
	private Player player;
	
	public ColorProperty(int noHouseRent, int oneHouseRent, int twoHouseRent, int threeHouseRent, int fourHouseRent, int hotelRent, int mortgageValue, int price, String colour, int street, Player player, int player1X,
			int player1Y, int player2X, int player2Y, TextureRegion thumbnail, int propertyX, int propertyY) {
		super(mortgageValue,price,colour,street,player, player1X, player1Y, player2X, player2Y, thumbnail, propertyX, propertyY);
		this.player = player;
		rents.add(noHouseRent);
		rents.add(oneHouseRent);
		rents.add(twoHouseRent);
		rents.add(threeHouseRent);
		rents.add(fourHouseRent);
		this.hotelRent = hotelRent;
		numOfHouses = 0;
		hotel = 0;
	}
	
	public int getHouseCost() {
		return getStreet()*50;
	}
	
	public int getRent() {
		if (hotel > 0) {
			return hotelRent;
		} else {
			if (numOfHouses > 0)
				return rents.get(numOfHouses);
			else {
				if (getGroup().checkAllOwned(player)) {
					return rents.get(0)*2;
				} else {
					return rents.get(0);
				}
			}
		}
	}
	
	public int getNumOfHouses() {
		return numOfHouses;
	}
	
	public int getHotel() {
		return hotel;
	}
	
}
