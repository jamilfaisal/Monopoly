package com.mygdx.monopoly;

/**
 * Class for the tax spaces 
 * Players must pay a fee depending on the type of tax space
 */
public class TaxSpace implements Space {

	private String type;
	private Board board;
	private Player player;

	public TaxSpace(String type, Board board) {
		this.type = type;
		this.board = board;
	}

	@Override
	public void enter(Player player) {
		this.player = player;
		board.getScreen().setTaxFocus(type);
		board.getScreen().setSpaceFocus(this);
		if (type == "luxury") {
			board.getScreen().setAmountPaid(75);
		} else {
			// Player pays 10%
			if (player.getMoney() < 2000) {
				board.getScreen().setAmountPaid((int) Math.round(player.getMoney() * 0.1));
			}
			// Player pays 200
			else {
				board.getScreen().setAmountPaid(200);
			}
		}
		board.getScreen().setState(4);
	}

	public void enterResult() {
		// Player pays 75
		if (type == "luxury") {
			player.transferMoney(75, board.getBank());
		} else {
			if (player.getMoney() < 2000) {
				player.transferMoney(Math.round(player.getMoney() * 0.1f), board.getBank());
			} else {
				player.transferMoney(200, board.getBank());
			}
		}
		board.getScreen().setState(1);
	}
}
