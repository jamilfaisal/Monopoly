package com.mygdx.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopoly.MonopolyGame;
import com.mygdx.monopoly.utilities.Assets;
import com.mygdx.monopoly.utilities.GameAssets;

/**
 *	New game screen for character selection
 */
public class NewGame extends ScreenAdapter {
	MonopolyGame game;
	MainMenu menu;
	SpriteBatch batch;
	int numOfPlayers = 2; 
	static int p1GamePiece = 0;
	static int p2GamePiece = 1;
	static int p3GamePiece = -1;
	static int p4GamePiece = -1;
	
	public NewGame(MonopolyGame game, MainMenu menu) {
		this.game = game;
		this.menu = menu;
		Gdx.graphics.setWindowedMode(1024, 768);
	}

	@Override
	public void show() {
				
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1024, 768);
	}

	@Override
	public void render(float delta) {
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		
		batch.begin();
		// Draws the menu
		batch.draw(Assets.newPages[numOfPlayers-2], 0, 0);

		if (Assets.backBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.backHoveredButton, 29, 21);
			if (Gdx.input.justTouched()) {	// On mouse click
				Assets.buttonSound.play();	// Plays button sound
				game.setScreen(menu);	// Changes screen
			}
		} else if (Assets.playBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.playHoveredButton, 891, 17);
			if (Gdx.input.justTouched()) {	// On mouse click
				Assets.buttonSound.play();	// Plays button sound
				Assets.backgroundMusic.stop();	// Pauses music
				// Loads game assets
				GameAssets.gameLoad();
				GameAssets.toolbarLoad();
				GameAssets.informationLoad();
				game.setScreen(new GameScreen(game,menu, numOfPlayers, p1GamePiece, p2GamePiece, p3GamePiece, p4GamePiece));
			}
		} 
		
		if (numOfPlayers == 2) {
			
			batch.draw(Assets.gamePieces[p1GamePiece], 355, 168);
			batch.draw(Assets.gamePieces[p2GamePiece], 608, 168);
			
			if (Assets.add2PlayerBounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.addPlayerHoveredButton, 800, 345);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					numOfPlayers++;
					p3GamePiece = getViableGamePiece(2, 3);
				}
			} else if (Assets.leftPlayer2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 294, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePieceDown(p1GamePiece-1,1);
				}
			} else if (Assets.leftPlayer3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 536, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePieceDown(p2GamePiece-1,2);
				}
			} else if (Assets.rightPlayer2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 456, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePiece(p1GamePiece+1,1);
				}
			} else if (Assets.rightPlayer3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 698, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePiece(p2GamePiece+1,2);
				}
			}
		} else if (numOfPlayers == 3) {
			batch.draw(Assets.gamePieces[p1GamePiece], 240, 168);
			batch.draw(Assets.gamePieces[p2GamePiece], 480, 168);
			batch.draw(Assets.gamePieces[p3GamePiece], 720, 168);
			
			if (Assets.add3PlayerBounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.addPlayerHoveredButton, 921, 345);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					numOfPlayers++;
					p4GamePiece = getViableGamePiece(3, 4);
				}
			} else if (Assets.remove3PlayerBounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.removeHoveredButton, 708, 586);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p3GamePiece = -1;
					numOfPlayers--;
				}
			} else if (Assets.left3Player1Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 172, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePieceDown(p1GamePiece-1,1);
				}
			} else if (Assets.left3Player2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 415, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePieceDown(p2GamePiece-1,2);
				}
			} else if (Assets.left3Player3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 657, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p3GamePiece = getViableGamePieceDown(p3GamePiece-1,3);
				}
			} else if (Assets.right3Player1Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 334, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePiece(p1GamePiece+1,1);
				}
			} else if (Assets.right3Player2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 577, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePiece(p2GamePiece+1,2);
				}
			} else if (Assets.right3Player3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 819, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p3GamePiece = getViableGamePiece(p3GamePiece+1,3);
				}
			}
		} else if (numOfPlayers == 4) {
			batch.draw(Assets.gamePieces[p1GamePiece], 120, 168);
			batch.draw(Assets.gamePieces[p2GamePiece], 370, 168);
			batch.draw(Assets.gamePieces[p3GamePiece], 620, 168);
			batch.draw(Assets.gamePieces[p4GamePiece], 850, 168);
			
			if (Assets.remove4Player3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.removeHoveredButton, 586, 586);
				if (Gdx.input.justTouched()) {
					p3GamePiece = p4GamePiece;
					p4GamePiece = -1;
					Assets.buttonSound.play();
					numOfPlayers--;
				}
			} else if (Assets.remove4Player4Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.removeHoveredButton, 827, 586);
				if (Gdx.input.justTouched()) {
					p4GamePiece = -1;
					Assets.buttonSound.play();
					numOfPlayers--;
				}
			} else if (Assets.leftPlayer1Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 52, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePieceDown(p1GamePiece-1,1);
				}
			} else if (Assets.leftPlayer2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 295, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePieceDown(p2GamePiece-1,2);
				}
			} else if (Assets.leftPlayer3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 538, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p3GamePiece = getViableGamePieceDown(p3GamePiece-1,3);
				}
			} else if (Assets.leftPlayer4Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.leftArrowHoveredButton, 780, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p4GamePiece = getViableGamePieceDown(p4GamePiece-1,4);
				}
			} else if (Assets.rightPlayer1Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 214, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p1GamePiece = getViableGamePiece(p1GamePiece+1,1);
				}
			} else if (Assets.rightPlayer2Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 457, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p2GamePiece = getViableGamePiece(p2GamePiece+1,2);
				}
			} else if (Assets.rightPlayer3Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 700, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p3GamePiece = getViableGamePiece(p3GamePiece+1,3);
				}
			} else if (Assets.rightPlayer4Bounds.contains(x, Gdx.graphics.getHeight() - y)) {
				batch.draw(Assets.rightArrowHoveredButton, 942, 177);
				if (Gdx.input.justTouched()) {
					Assets.buttonSound.play();
					p4GamePiece = getViableGamePiece(p4GamePiece+1,4);
				}
			}
		}
		
		
		
		batch.end();
	}
	
	/**
	 * Displays the previous available piece
	 * @param gamePiece The currently selected game piece
	 * @param playerNumber
	 * @return	The previous game piece
	 */
	private static int getViableGamePieceDown(int gamePiece, int playerNumber) {
		if (gamePiece == 11) {
			gamePiece = 0;
		} else if (gamePiece < 0) {
			gamePiece = 10;
		}
		if (playerNumber == 1) {
			while (gamePiece == p2GamePiece || gamePiece == p3GamePiece  || gamePiece == p4GamePiece) {
				gamePiece--;
				if (gamePiece == -1) {
					gamePiece = 10;
				}
			} 
		} else if (playerNumber == 2) {
			while (gamePiece == p1GamePiece || gamePiece == p3GamePiece  || gamePiece == p4GamePiece) {
				gamePiece--;
				if (gamePiece == -1) {
					gamePiece = 10;
				}
			} 
		} else if (playerNumber == 3) {
			while (gamePiece == p1GamePiece || gamePiece == p2GamePiece  || gamePiece == p4GamePiece) {
				gamePiece--;
				if (gamePiece == -1) {
					gamePiece = 10;
				}
			} 
		} else if (playerNumber == 4) {
			while (gamePiece == p1GamePiece || gamePiece == p2GamePiece  || gamePiece == p3GamePiece) {
				gamePiece--;
				if (gamePiece == -1) {
					gamePiece = 10;
				}
			} 
		}
		
		return gamePiece;
	}
	
	/**
	 * Displays the next available game piece
	 * @param gamePiece The current game piece
	 * @param playerNumber
	 * @return The next game piece
	 */
	private static int getViableGamePiece(int gamePiece, int playerNumber) {
		if (gamePiece == 11) {
			gamePiece = 0;
		} else if (gamePiece < 0) {
			gamePiece = 10;
		}
		if (playerNumber == 1) {
			while (gamePiece == p2GamePiece || gamePiece == p3GamePiece  || gamePiece == p4GamePiece) {
				gamePiece++;
				if (gamePiece == 11) {
					gamePiece = 0;
				}
			} 
		} else if (playerNumber == 2) {
			while (gamePiece == p1GamePiece || gamePiece == p3GamePiece  || gamePiece == p4GamePiece) {
				gamePiece++;
				if (gamePiece == 11) {
					gamePiece = 0;
				}
			} 
		} else if (playerNumber == 3) {
			while (gamePiece == p1GamePiece || gamePiece == p2GamePiece  || gamePiece == p4GamePiece) {
				gamePiece++;
				if (gamePiece == 11) {
					gamePiece = 0;
				}
			} 
		} else if (playerNumber == 4) {
			while (gamePiece == p1GamePiece || gamePiece == p2GamePiece  || gamePiece == p3GamePiece) {
				gamePiece++;
				if (gamePiece == 11) {
					gamePiece = 0;
				}
			} 
		}
		
		
		return gamePiece;
	}
	
	@Override
	public void hide() {
		batch.dispose();
	}
}
