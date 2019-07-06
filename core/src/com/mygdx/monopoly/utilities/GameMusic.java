package com.mygdx.monopoly.utilities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Class that handles all music-related methods
 */
public class GameMusic {

	static Random rand;
	static int musicChoice = 0;

	/**
	 * Selects the music based on the current screen
	 * @param screen The currently displayed screen
	 * @param backgroundMusic The background music asset
	 * @return The updated background music asset
	 */
	public static Music changeMusic(String screen, Music backgroundMusic) {
		rand = new Random();
		backgroundMusic = null;
		// If current screen is the main menu
		if (screen == "mainMenuScreen") {
			musicChoice = rand.nextInt(3) + 1;
			switch (musicChoice) {
			case 1:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music/MUSIC.PC_12.mp3"));
				break;
			case 2:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music/MUSIC.PC_26.mp3"));
				break;
			case 3:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music/MUSIC.PC_30.mp3"));
				break;
			}
		} 
		// If current screen is the game menu
		else if (screen == "gameScreen") {
			musicChoice = rand.nextInt(4) + 1;
			switch (musicChoice) {
			case 1:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/game_music/M1.ogg"));
				break;
			case 2:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/game_music/M2.ogg"));
				break;
			case 3:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/game_music/M3.ogg"));
				break;
			case 4:
				backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/game_music/M4.mp3"));
				break;
			}
		}
		// Sets the music volume
		backgroundMusic.setVolume(Assets.prefs.getMusicVolume());
		Assets.soundFXVolume = Assets.prefs.getSoundVolume();
		// Plays the music
		if (backgroundMusic.getVolume() >= 0)
			backgroundMusic.play();
		return backgroundMusic;
	}
}
