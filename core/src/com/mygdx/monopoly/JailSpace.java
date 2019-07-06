package com.mygdx.monopoly;

/**
 *	Class for visiting jail space
 */
public class JailSpace implements Space{

	private Board board;
	
	public JailSpace(Board board) {
		this.board = board;
	}
	
	@Override
	public void enter(Player player) {
		board.getScreen().setState(1);
	}

	public void enterResult() {
	}
}
