package com.mygdx.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopoly.Board;
import com.mygdx.monopoly.GameHandler;
import com.mygdx.monopoly.MonopolyGame;
import com.mygdx.monopoly.Player;
import com.mygdx.monopoly.Space;
import com.mygdx.monopoly.utilities.Assets;
import com.mygdx.monopoly.utilities.GameAssets;
import com.mygdx.monopoly.utilities.GameMusic;

/*
 * Screen Class that handles all the drawing of the game
 */
public class GameScreen extends ScreenAdapter {

	/*
	 * 
	 */
	MonopolyGame game;
	MainMenu menu;
	private int state;
	SpriteBatch batch;

	private Board board;
	private int currentPlayerTurn;
	private int p1piece;
	private int p2piece;
	private GameHandler gH;
	private boolean menu_up = false;
	private int numOfPlayer;

	private final int GAME_NORMAL = 0;
	private final int GAME_END = 1;
	private final int GAME_JAIL_OPTIONS = 2;
	private final int GAME_ROLL = 3;
	private final int GAME_TAX = 4;
	private final int GAME_COMMUNITY = 5;
	private final int GAME_CHANCE = 6;
	private final int GAME_PROPERTY_BUY = 7;
	private final int GAME_PAY_RENT = 8;
	private final int GAME_BANKRUPT = 9;
	private final int GAME_FINISHED = 10;
	
	private int propertyFocus;
	private int cardFocus;
	private Space spaceFocus;
	private String taxFocus;
	private int amountPaid;

	public GameScreen(MonopolyGame game, MainMenu menu, int numOfPlayer, int p1piece, int p2piece, int p3piece,
			int p4piece) {
		this.game = game;
		this.menu = menu;
		this.p1piece = p1piece;
		this.p2piece = p2piece;
		this.numOfPlayer = 2;
		board = new Board(numOfPlayer, p1piece, p2piece, p3piece, p4piece, this);
		currentPlayerTurn = 0;
		gH = new GameHandler(board, this);
		Assets.backgroundMusic = GameMusic.changeMusic("gameScreen", Assets.backgroundMusic);
		Assets.backgroundMusic.setVolume(Assets.prefs.getMusicVolume());
		Assets.soundFXVolume = Assets.prefs.getSoundVolume();
	}

	@Override
	public void show() {
		// Sets window size
		Gdx.graphics.setWindowedMode(1024, 768);
		// Plays music
		if (!Assets.backgroundMusic.isPlaying()) {
			Assets.backgroundMusic.play();
		}
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1024, 768);

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		// Receives mouse pointer location
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		//Changes music when music is finsihed
		if (!Assets.backgroundMusic.isPlaying())
			Assets.backgroundMusic = GameMusic.changeMusic("gameScreen", Assets.backgroundMusic);
		batch.begin();
		// Draws main menu
		batch.draw(GameAssets.boardRegion, 0, 0);
		batch.draw(GameAssets.toolbar, 0, 0);
		if (currentPlayerTurn == 0) {
			batch.draw(GameAssets.player1_info_turn, 723, 465);
			batch.draw(GameAssets.player2_info, 723, 175);
		} else {
			batch.draw(GameAssets.player1_info, 723, 465);
			batch.draw(GameAssets.player2_info_turn, 723, 175);
		}
		batch.draw(Assets.gamePieces[p1piece], 743, 705);
		batch.draw(Assets.gamePieces[p2piece], 743, 415);

		drawEverything();
		
		//
		/* State for finishing turn
		 * Checks if the player has another turn from doubles
		 * Else it will just give an option to click the finish turn button
		 */
		if (state == GAME_END) {
			if(board.getPlayer(currentPlayerTurn).getMoney() < 0) {
				state = GAME_BANKRUPT;
			}
			if (gH.getDouble()) {
				gH.toggleDoubles();
				state = GAME_ROLL;
			} else {
				batch.draw(GameAssets.finishTurnButton, 270, 350);
				GameAssets.font12.draw(batch, "Player " + (currentPlayerTurn + 1), 360, 440);
				if (currentPlayerTurn == 0) {
					batch.draw(GameAssets.gamePieces[p1piece], 284, 365);
				} else if (currentPlayerTurn == 1) {
					batch.draw(GameAssets.gamePieces[p2piece], 284, 365);
				}
				if (GameAssets.finishTurnButton_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
					batch.draw(GameAssets.finishTurnButtonHovered, 350, 359);
					if (Gdx.input.justTouched()) {
						gH.endTurn();
					}
				}
			}
		}
		
		/*
		 * This is the state to roll the dice
		 * just has a button to roll the dice
		 */
		else if (state == GAME_ROLL) {

			batch.draw(GameAssets.rollDiceButton, 270, 350);
			GameAssets.font12.draw(batch, "Player " + (currentPlayerTurn + 1), 360, 440);
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.gamePieces[p1piece], 284, 365);
			} else if (currentPlayerTurn == 1) {
				batch.draw(GameAssets.gamePieces[p2piece], 284, 365);
			}
			if (GameAssets.rollDiceButton_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.rollDice_highlighted, 351, 360);
				if (Gdx.input.justTouched()) {
					gH.startRoll();
				}
			}

		}

		/*
		 * Jail options when the current player is jailed
		 * three buttons: post bail for $50, use jail free card and roll for doubles
		 */
		else if (state == GAME_JAIL_OPTIONS) {
			batch.draw(GameAssets.jailPopup,100,200);
			if (GameAssets.rollDoubles_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.rollDoublesHovered, 402, 215);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					gH.startRoll();
				}
			} else if (GameAssets.postBail_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.postBailHovered, 114, 216);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					board.getPlayer(currentPlayerTurn).setJailed(false);
					board.getPlayer(currentPlayerTurn).loseMoney(50);
					state = 3;
				}
			} else if (GameAssets.useCard_bounds.contains(x, Gdx.graphics.getHeight() - y) && (board.getPlayer(currentPlayerTurn).getJailFreeCards("chance") || board.getPlayer(currentPlayerTurn).getJailFreeCards("chest"))) {
				batch.draw(GameAssets.useCardHovered, 258, 215);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					board.getPlayer(currentPlayerTurn).useJailCard();
					gH.startRoll();
				}
			}
		}
		
		/*
		 * State in which tax popup shows up
		 * Tells player how much money they are paying in which kind of tax
		 */
		
		else if (state == GAME_TAX) {
			batch.draw(GameAssets.taxPopup, 158, 300);
			GameAssets.font24.draw(batch, "Player " + (currentPlayerTurn + 1), 308, 500);
			GameAssets.font24.draw(batch, "paid $" + (amountPaid), 308, 465);
			GameAssets.font24.draw(batch, "in " + (taxFocus) + " tax.", 308, 430);
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.gamePieces[p1piece], 210, 385);
			} else if (currentPlayerTurn == 1) {
				batch.draw(GameAssets.gamePieces[p2piece], 210, 385);
			}
			if (GameAssets.okTax_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.okHovered, 389, 319);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					spaceFocus.enterResult();
				}
			} if (GameAssets.rollDoubles_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.rollDoublesHovered, 389, 319);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					spaceFocus.enterResult();
				}
			}
		}

		
		/*
		 * State for community chest
		 * Prints the card that the player has landed on and does the effect after clicking ok
		 */
		else if (state == GAME_COMMUNITY) {
			batch.draw(GameAssets.cardPopup, 158, 220);
			batch.draw(GameAssets.chests[cardFocus], 190, 292);
			GameAssets.font24.draw(batch, "Player " + (currentPlayerTurn + 1), 308, 545);
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.gamePieces[p1piece], 199, 505);
			} else if (currentPlayerTurn == 1) {
				batch.draw(GameAssets.gamePieces[p2piece], 199, 505);
			}
			if (GameAssets.okCard_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.okHovered, 315, 238);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					spaceFocus.enterResult();
				}
			}
		}

		/*
		 * State for community chest
		 * Prints the card that the player has landed on and does the effect after clicking ok
		 */
		else if (state == GAME_CHANCE) {
			batch.draw(GameAssets.cardPopup, 158, 220);
			batch.draw(GameAssets.chances[cardFocus], 190, 292);
			GameAssets.font24.draw(batch, "Player " + (currentPlayerTurn + 1), 308, 545);
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.gamePieces[p1piece], 199, 505);
			} else if (currentPlayerTurn == 1) {
				batch.draw(GameAssets.gamePieces[p2piece], 199, 505);
			}
			if (GameAssets.okCard_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.okHovered, 315, 238);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					spaceFocus.enterResult();
				}
			}
		}

		/* 
		 * state in which the player can buy or auction a property
		 * Auction has no functionality at the moment
		 */
		else if (state == GAME_PROPERTY_BUY) {
			batch.draw(GameAssets.propertyBuyOptions, 128, 173);
			batch.draw(GameAssets.properties[propertyFocus], 314, 248);
			GameAssets.font24.draw(batch, "$" + board.getProperties().get(propertyFocus).getPrice(), 333, 584);
			batch.draw(GameAssets.spaces[board.getPlayer(currentPlayerTurn).getPosition()], 157, 248);
			if (GameAssets.buy_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.buyHovered, 244, 192);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					gH.buyProperty(propertyFocus);
				}
			} else if (GameAssets.auction_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.auctionHovered, 385, 192);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
				}
			}

		/* State for paying rent
		 * This will show users how much money the player is paying
		*/
		} else if (state == GAME_PAY_RENT) {
			batch.draw(GameAssets.rentPopup, 140, 233);
			GameAssets.font24.draw(batch, "$" + amountPaid, 330, 480);
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.gamePieces[p1piece], 215, 325);
				batch.draw(GameAssets.gamePieces[p2piece], 460, 325);
			} else if (currentPlayerTurn == 1) {
				batch.draw(GameAssets.gamePieces[p2piece], 215, 325);
				batch.draw(GameAssets.gamePieces[p1piece], 460, 325);
			}
			if (GameAssets.okRent_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.okHovered, 318, 253);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					spaceFocus.enterResult();
				}
			}

			/*
			 * State to start a players turn
			 */
		} else if (state == GAME_NORMAL) {
			batch.draw(GameAssets.startTurn, 743, 20);
			if (GameAssets.startTurn_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.startTurn_highlighted, 743, 20);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					gH.startTurn(board.getPlayer(currentPlayerTurn));
				}
			}
		} else if (state == GAME_BANKRUPT) {
			batch.draw(GameAssets.bankrupt, 350, 350);
			if (GameAssets.bankrupt_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.bankruptHovered, 350, 350);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					state = GAME_FINISHED;
				}
			}
		} else if (state == GAME_FINISHED) {
			GameAssets.font24.draw(batch, "Player " + (currentPlayerTurn + 1) + " loses!", 308, 500);
		}
		
		/*
		 * Toolbar menu
		 * most of it has no functionality
		 */
		if (GameAssets.toolbar_menu_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(GameAssets.toolbar_menu_highlighted, 0, 0);
			if (Gdx.input.justTouched()) {
				Assets.buttonSound.play(Assets.soundFXVolume);
				menu_up = !menu_up;
			}
		}
		if (GameAssets.toolbar_trade_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(GameAssets.toolbar_trade_highlighted, 0, 0);
		}
		if (menu_up) {
			batch.draw(GameAssets.toolbar_menu, 0, 44);
			if (GameAssets.toolbar_menu_help_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_help_highlighted, 0, 44);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					game.setScreen(new Help(game, this));
				}
			}
			if (GameAssets.toolbar_menu_summary_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_summary_highlighted, 0, 44);
			}
			if (GameAssets.toolbar_menu_quit_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_quit_highlighted, 0, 44);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					Assets.backgroundMusic.dispose();
					Assets.backgroundMusic = GameMusic.changeMusic("mainMenuScreen", Assets.backgroundMusic);
					Assets.backgroundMusic.setVolume(Assets.prefs.getMusicVolume());
					Assets.soundFXVolume = Assets.prefs.getSoundVolume();
					game.setScreen(menu);
				}
			}
			if (GameAssets.toolbar_menu_save_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_save_highlighted, 0, 44);
			}
			if (GameAssets.toolbar_menu_resume_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_resume_highlighted, 0, 44);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					menu_up = false;
				}
			}
			if (GameAssets.toolbar_menu_options_bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(GameAssets.toolbar_menu_options_highlighted, 0, 44);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					game.setScreen(new Options(game, this));
				}
			}
		}

		batch.end();

	}

	@Override
	public void hide() {
		batch.dispose();
		// Removes music (Keep commented to allow music to play in other screens)
		// backgroundMusic.dispose();
	}

	public Board getBoard() {
		return board;
	}

	public void setState(int s) {
		state = s;
	}

	public int getState() {
		return state;
	}
	
	/* Draws all the constant images on the board
	 *  this contains, pieces, money, property owners, jail free cards and the dice rolls
	 */
	private void drawEverything() {
		drawPiece(p1piece, board.getPlayer(0));
		drawPiece(p2piece, board.getPlayer(1));
		GameAssets.font24.draw(batch, "$" + board.getPlayer(0).getMoney(), 890, 740);
		GameAssets.font24.draw(batch, "$" + board.getPlayer(1).getMoney(), 890, 450);
		for (int i = 0; i < board.getProperties().size(); i++) {
			if (board.getProperties().get(i).getOwner() == board.getPlayer(0)) {
				batch.draw(board.getProperties().get(i).getThumbnail(), board.getProperties().get(i).getPlayer1X(),
						board.getProperties().get(i).getPlayer1Y());
				batch.draw(GameAssets.property_grey, board.getProperties().get(i).getPlayer2X() + 1,
						board.getProperties().get(i).getPlayer2Y() + 2);
				 drawMarker(i, p1piece);
			} else if (board.getProperties().get(i).getOwner() == board.getPlayer(1)) {
				batch.draw(board.getProperties().get(i).getThumbnail(), board.getProperties().get(i).getPlayer2X(),
						board.getProperties().get(i).getPlayer2Y());
				batch.draw(GameAssets.property_grey, board.getProperties().get(i).getPlayer1X() + 1,
						board.getProperties().get(i).getPlayer1Y() + 2);
				 drawMarker(i, p2piece);
			}
		}

		if (board.getPlayer(0).getJailFreeCards("chest")) {
			batch.draw(GameAssets.goojfc_chest, 899, 510);
		}
		if (board.getPlayer(0).getJailFreeCards("chance")) {
			batch.draw(GameAssets.goojfc_chance, 940, 510);
		}
		if (board.getPlayer(1).getJailFreeCards("chest")) {
			batch.draw(GameAssets.goojfc_chest, 899, 220);
		}
		if (board.getPlayer(1).getJailFreeCards("chance")) {
			batch.draw(GameAssets.goojfc_chance, 940, 220);
		}
		if (gH.getRollOne() != 0 && gH.getRollTwo() != 0) {
			if (currentPlayerTurn == 0) {
				batch.draw(GameAssets.dices[gH.getRollOne() - 1], 750, 505);
				batch.draw(GameAssets.dices[gH.getRollTwo() - 1], 780, 505);
			} else {
				batch.draw(GameAssets.dices[gH.getRollOne() - 1], 750, 215);
				batch.draw(GameAssets.dices[gH.getRollTwo() - 1], 780, 215);
			}
		}
	}

	/*
	 * changes the current player
	 */
	public void changeCurrentPlayer() {
		currentPlayerTurn++;
		if (currentPlayerTurn > numOfPlayer - 1) {
			currentPlayerTurn = 0;
		}
	}

	/*draws all the pieces
	 * 
	 */
	private void drawPiece(int piece, Player player) {
		if (player.getPosition() >= 30) {
			batch.draw(GameAssets.gamePieces[piece], 651, 667 - 56 * (player.getPosition() - 30));
		} else if (player.getPosition() >= 20) {
			batch.draw(GameAssets.gamePieces[piece], 66 + 56 * (player.getPosition() - 20), 667);
		} else if (player.getPosition() >= 10) {
			batch.draw(GameAssets.gamePieces[piece], 66, 110 + 56 * (player.getPosition() - 10));
		} else {
			batch.draw(GameAssets.gamePieces[piece], 625 - 56 * (player.getPosition()), 80);
		}
	}

	/* draws the properties owner on top of the property on the board
	 * 
	 */
	private void drawMarker(int i, int playerPiece) {
		// batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY,
		// rotation);
		if (board.getProperties().get(i).getStreet() == 1) {
			batch.draw(GameAssets.gamePieceMarkers[playerPiece], board.getProperties().get(i).getPropertyX(),
					board.getProperties().get(i).getPropertyY());
		}
		if (board.getProperties().get(i).getStreet() == 2) {
			batch.draw(GameAssets.gamePieceMarkers[playerPiece], board.getProperties().get(i).getPropertyX(),
					board.getProperties().get(i).getPropertyY(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight(), 1, 1, -90);
		}
		if (board.getProperties().get(i).getStreet() == 3) {
			batch.draw(GameAssets.gamePieceMarkers[playerPiece], board.getProperties().get(i).getPropertyX(),
					board.getProperties().get(i).getPropertyY(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight(), 1, 1, 180);
		}
		if (board.getProperties().get(i).getStreet() == 4) {
			batch.draw(GameAssets.gamePieceMarkers[playerPiece], board.getProperties().get(i).getPropertyX(),
					board.getProperties().get(i).getPropertyY(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight() / 2,
					GameAssets.gamePieceMarkers[playerPiece].getRegionWidth(),
					GameAssets.gamePieceMarkers[playerPiece].getRegionHeight(), 1, 1, 90);
		}
	}

	/**
	 * Used to set which property is being used for the states
	 * @param f the index of the property
	 */
	public void setPropertyFocus(int f) {
		propertyFocus = f;
	}

	/**
	 * Used to set which community chance or chance card is being used for the states
	 * @param f the index of the card in thier respective group
	 */
	public void setCardFocus(int f) {
		cardFocus = f;
	}

	/**
	 * Used to set which space is being used for the states
	 * @param f the space index
	 */
	public void setSpaceFocus(Space f) {
		spaceFocus = f;
	}

	/**
	 * Used to set the amount of money being paid for the certain state, used in money situations like tax or paying rent
	 * @param p
	 */
	public void setAmountPaid(int p) {
		amountPaid = p;
	}

	/**
	 * Used to tell what kind of tax the player has landed on
	 * @param s
	 */
	public void setTaxFocus(String s) {
		taxFocus = s;
	}
}
