package com.mygdx.monopoly;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class for the utility property 
 * Each utility has a rent depending on the player's dice roll
 */
public class UtilityProperty extends Property {

	private Player player;

	public UtilityProperty(int mortgageValue, int price, String colour, int street, Player player, int player1X,
			int player1Y, int player2X, int player2Y, TextureRegion thumbnail, int propertyX, int propertyY) {
		super(mortgageValue, price, colour, street, player, player1X, player1Y, player2X, player2Y, thumbnail,
				propertyX, propertyY);
	}

	public int getRent() {
		int diceRoll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		// If one utility is owned
		if (getGroup().checkOwned(player) == 1) {
			return diceRoll * 4;
		} else {
			return diceRoll * 10;
		}
	}

}
