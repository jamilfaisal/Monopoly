package com.mygdx.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopoly.MonopolyGame;
import com.mygdx.monopoly.utilities.Assets;

/**
 *	Help screen displays the game's rules using several pages to the user
 */
public class Help extends ScreenAdapter {
	MonopolyGame game;
	ScreenAdapter previousScreen;
	SpriteBatch batch;
	int pageNumber = 0;

	public Help(MonopolyGame game, ScreenAdapter previousScreen) {
		this.game = game;
		this.previousScreen = previousScreen;
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
		if (Assets.nextBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			if (Gdx.input.justTouched()) {	// On mouse click
				pageNumber++;
				Assets.buttonSound.play();
				if (pageNumber == Assets.helpPages.length) // Resets page number
					pageNumber = 0;
			}
		} else if (Assets.previousBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			if (Gdx.input.justTouched()) {
				pageNumber--;
				Assets.buttonSound.play();
				if (pageNumber < 0) // Sets page number to final page
					pageNumber = Assets.helpPages.length-1;
			}
		} else if (Assets.closeHelpBounds.contains(x, Gdx.graphics.getHeight() - y)) {
			if (Gdx.input.isTouched()) {
				Assets.buttonSound.play();
				game.setScreen(previousScreen);	// Returns to main menu
			}
		}
		// Updates help page
		drawPage(x, y);

		batch.end();
	}

	/**
	 * Displays the correct help page based on the page number
	 * @param mousePointerX The mouse pointer's X location
	 * @param mousePointerY	The mouse pointer's Y location
	 */
	public void drawPage(int mousePointerX, int mousePointerY) {
		
		// Updates help page
		batch.draw(Assets.helpPages[pageNumber], 0, 0);
		// Adds unhighlighted buttons
		batch.draw(Assets.previousButton, 298, 20);
		batch.draw(Assets.closeHelpButton, 458, 20);
		batch.draw(Assets.nextButton, 616, 20);
		// Adds highlighted buttons based on mouse pointer location
		if (Assets.nextBounds.contains(mousePointerX, Gdx.graphics.getHeight() - mousePointerY))
			batch.draw(Assets.nextHoveredButton, 616, 20);
		if (Assets.previousBounds.contains(mousePointerX, Gdx.graphics.getHeight() - mousePointerY))
			batch.draw(Assets.previousHoveredButton, 298, 20);
		if (Assets.closeHelpBounds.contains(mousePointerX, Gdx.graphics.getHeight() - mousePointerY))
			batch.draw(Assets.closeHelpHoveredButton, 458, 20);

	}
	
	@Override
	public void hide() {
		batch.dispose();
	}
}
