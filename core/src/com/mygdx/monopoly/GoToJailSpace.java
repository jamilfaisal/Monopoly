package com.mygdx.monopoly;

/**
 *	Class for the go to jail space
 */
public class GoToJailSpace implements Space{

private Board board;
	
	public GoToJailSpace(Board board) {
		this.board = board;
	}
	
	/* 
	 * Player is jailed
	 */
	@Override
	public void enter(Player player) {
		player.setPosition(10);
		player.setJailed(true);
		board.getScreen().setState(1);
	}
	
	public void enterResult() {
	}

}
