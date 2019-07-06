package com.mygdx.monopoly.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Settings class used to access local game options
 */
public class GamePreferences {

	private Preferences preferences;
	private final String PREFS_NAME = "gamePreferences";
	private final String MUSIC_VOLUME = "musicVolume";
	private final String SOUND_VOLUME = "soundVolume";

	GamePreferences() {
		// Loads settings based on name
		preferences = Gdx.app.getPreferences(PREFS_NAME);
		// Initializes sound volumes for the first time
		if (!preferences.contains(MUSIC_VOLUME)) {
			preferences.putFloat(MUSIC_VOLUME, 0.3f).flush();
		}
		if (!preferences.contains(SOUND_VOLUME)) {
			preferences.putFloat(SOUND_VOLUME, 0.3f).flush();
		}
	}
	
	/**
	 * Saves options to settings file
	 */
	public void setMusicVolume() {
		preferences.putFloat(MUSIC_VOLUME, Assets.backgroundMusic.getVolume()).flush();
	}
	public void setSoundVolume() {
		preferences.putFloat(SOUND_VOLUME, Assets.soundFXVolume).flush();
	}
	
	/**
	 * Accesses file and returns volume settings
	 */
	public float getMusicVolume() {
		return preferences.getFloat(MUSIC_VOLUME);
	}
	public float getSoundVolume() {
		return preferences.getFloat(SOUND_VOLUME);
	}
	
}
