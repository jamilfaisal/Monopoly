package com.mygdx.monopoly;

import com.badlogic.gdx.Game;
import com.mygdx.monopoly.screens.MainMenu;
import com.mygdx.monopoly.utilities.Assets;

/**
 * Game class that initializes the main menu
 */
public class MonopolyGame extends Game {
	
	private MainMenu mainMenu;
	
	@Override
	public void create () {
		// Loads all pre-game assets
		Assets.mainMenuLoad();
		Assets.helpMenuLoad();
		Assets.optionsMenuLoad();
		Assets.newGameLoad();
		// Switches to main menu screen
		mainMenu = new MainMenu(this);
		setScreen(mainMenu);
	}
	@Override
	public void render() {
		super.render();
	}
}
