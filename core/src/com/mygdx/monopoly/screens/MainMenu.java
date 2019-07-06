package com.mygdx.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopoly.MonopolyGame;
import com.mygdx.monopoly.utilities.Assets;
import com.mygdx.monopoly.utilities.GameMusic;

public class MainMenu extends ScreenAdapter {

	MonopolyGame game;

	SpriteBatch batch;
	
	public MainMenu(MonopolyGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		// Sets window size
		Gdx.graphics.setWindowedMode(1024, 588);
		// Plays music
		if (!Assets.backgroundMusic.isPlaying()) {
			Assets.backgroundMusic.play();
		}
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1024, 588);

	}

	@Override
	public void render(float delta) {
		// Receives mouse pointer location
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		batch.begin();
		// Draws main menu
		batch.draw(Assets.menuRegion, 0, 0);
		// Changes music after it has stopped playing  
		if (!Assets.backgroundMusic.isPlaying())
			Assets.backgroundMusic = GameMusic.changeMusic("mainMenuScreen", Assets.backgroundMusic);
			
		// Highlights buttons on mouse hover
		if (Assets.newGameBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.newGameHoveredButton, 0, 0);
			if (Gdx.input.justTouched()) {	// On mouse click
				Assets.buttonSound.play(Assets.soundFXVolume);	// Plays button sound
				game.setScreen(new NewGame(game, this));	// Changes screen
			}
		} else if (Assets.exitBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.exitHoveredButton, 0, 0);
			if (Gdx.input.justTouched()) {
				Assets.buttonSound.play(Assets.soundFXVolume);
				Gdx.app.exit();	// Closes application
			}
		} else if (Assets.optionsBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.optionsHoveredButton, 0, 0);
			if (Gdx.input.justTouched()) {
				Assets.buttonSound.play(Assets.soundFXVolume);
				game.setScreen(new Options(game, this));
			}
		} else if (Assets.helpBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.helpHoveredButton, 0, 0);
			if (Gdx.input.justTouched()) {
				Assets.buttonSound.play(Assets.soundFXVolume);
				game.setScreen(new Help(game, this));
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

}
