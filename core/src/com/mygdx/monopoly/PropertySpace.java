package com.mygdx.monopoly;

import java.util.ArrayList;

/**
 * Class for all spaces containing properties
 */
public class PropertySpace implements Space {

	private Property property;
	private Player owner;
	private Board board;
	private Player player;
	
	PropertySpace(Property property, Board board) {
		this.property = property;
		this.board = board;
		}
	
	/* 
	 * Interacts with the player based on the current owner
	 */
	@Override
	public void enter(Player player) {
		owner = property.getOwner();
		this.player = player;
		// Player can purchase this property
		if (owner.getName() == "bank") {
			board.getScreen().setState(7);
			board.getScreen().setPropertyFocus(board.getProperties().indexOf(property));
		}
		// Nothing occurs if the player is the owner
		else if(player == owner) {
			board.getScreen().setState(1);
		} 
		// Must pay rent to the owner of the property
		else {
			board.getScreen().setAmountPaid(property.getRent());
			board.getScreen().setSpaceFocus(this);
			board.getScreen().setState(8);
		}
	}
	
	/* 
	 * Transfers money to the owner
	 */
	public void enterResult() {
		player.transferMoney(property.getRent(), owner);
		board.getScreen().setState(1);
	}
	
	public Player getOwner() {
		return owner;
	}
}
