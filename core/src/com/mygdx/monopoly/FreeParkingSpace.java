package com.mygdx.monopoly;

/**
 * Class for the free parking space
 */
public class FreeParkingSpace implements Space{

	private Board board;
	
	public FreeParkingSpace(Board board) {
		this.board = board;
	}
	
	@Override
	public void enter(Player player) {
		board.getScreen().setState(1);
	}
	
	public void enterResult() {
	}

}