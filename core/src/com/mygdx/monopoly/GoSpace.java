package com.mygdx.monopoly;

/**
 * Class for the go space
 */
public class GoSpace implements Space{

	private Board board;
	
	public GoSpace(Board board) {
		this.board = board;
	}
	@Override
	public void enter(Player player) {
		board.getScreen().setState(1);
	}
	
	public void enterResult() {
	}

}
