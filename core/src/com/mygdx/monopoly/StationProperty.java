package com.mygdx.monopoly;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class for station properties
 * Each station has a rent depending on the number of player owned stations
 */
public class StationProperty extends Property{

	private Player player;
	
	public StationProperty(int mortgageValue, int price, String colour, int street,Player player, int player1X,
			int player1Y, int player2X, int player2Y, TextureRegion thumbnail, int propertyX, int propertyY) {
		super(mortgageValue,price,colour,street,player, player1X, player1Y, player2X, player2Y, thumbnail, propertyX, propertyY);
		this.player = player;
	}
	
	public int getRent() {
		if (getGroup().checkOwned(player) == 1) {
			return 25;
		} else if (getGroup().checkOwned(player) == 2) {
			return 50;
		} else if (getGroup().checkOwned(player) == 3) {
			return 100;
		} else  {
			return 200;
		}
	}

}
