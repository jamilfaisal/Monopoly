package com.mygdx.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopoly.MonopolyGame;
import com.mygdx.monopoly.utilities.Assets;


/**
 * Options screen allows the user to adjust settings
 */
public class Options extends ScreenAdapter {

	float clock;
	MonopolyGame game;
	ScreenAdapter previousScreen;
	SpriteBatch batch;

	public Options(MonopolyGame game, ScreenAdapter previousScreen) {
		this.game = game;
		this.previousScreen = previousScreen;
	}

	@Override
	public void show() {
		Gdx.graphics.setWindowedMode(1024, 588);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1024, 588);
	}

	@Override
	public void render(float delta) {
		// Updates the timer every time the screen is rendered
		clock += Gdx.graphics.getDeltaTime();
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		batch.begin();
		batch.draw(Assets.optionsMenuRegion, 0, 0);
		batch.draw(Assets.musicAdjusterRegion, Assets.musicAdjusterBounds.getX(), Assets.musicAdjusterBounds.getY());
		batch.draw(Assets.soundAdjusterRegion, Assets.soundAdjusterBounds.getX(), Assets.soundAdjusterBounds.getY());

		if (Assets.closeOptionsBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.closeHoveredButton, 452, 588 - 515);
			if (Gdx.input.justTouched()) {
				// Updates the local settings file
				Assets.prefs.setMusicVolume();
				Assets.prefs.setSoundVolume();
				Assets.buttonSound.play(Assets.soundFXVolume);
				// Returns to previous screen
				game.setScreen(previousScreen);
			}
		}
		// Runs based on mouse pointer's x position only
		else if (Assets.musicAdjusterBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.musicAdjusterHovered, Assets.musicAdjusterBounds.x, Assets.musicAdjusterBounds.y);
			if (Gdx.input.isTouched()) {	// On mouse hold
				Assets.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);	
				Assets.musicAdjusterBounds.x = Assets.touchPos.x - 45 / 2; // Moves adjuster alongside mouse's x position
				Assets.setVolume(Assets.backgroundMusic);	// Changes background music volume
			}
		} 
		else if (Assets.soundAdjusterBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			batch.draw(Assets.soundAdjusterHovered, Assets.soundAdjusterBounds.x, Assets.soundAdjusterBounds.y);
			if (Gdx.input.isTouched()) {
				Assets.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				Assets.soundAdjusterBounds.x = Assets.touchPos.x - 45 / 2;
				Assets.soundFXVolume = (Assets.soundAdjusterBounds.x - 410) / 250;
				// Plays button sound every one second
				if (clock > 1) {
					Assets.buttonSound.play(Assets.soundFXVolume);
					clock = 0;
				}
			}
		}
		// Stops adjusters from moving past the sliders' edges
		if (Assets.musicAdjusterBounds.x < 410)
			Assets.musicAdjusterBounds.x = 410;
		if (Assets.musicAdjusterBounds.x > 660)
			Assets.musicAdjusterBounds.x = 660;
		if (Assets.soundAdjusterBounds.x < 410)
			Assets.soundAdjusterBounds.x = 410;
		if (Assets.soundAdjusterBounds.x > 660)
			Assets.soundAdjusterBounds.x = 660;
		batch.end();
	}
	
	@Override
	public void hide() {
		batch.dispose();
	}
}
