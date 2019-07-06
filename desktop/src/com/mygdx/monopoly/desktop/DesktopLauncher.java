package com.mygdx.monopoly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.monopoly.MonopolyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Monopoly";
		config.width = 1024;
		config.height = 588;
		config.resizable = false;
		// TODO Resize assets to enable fullscreen
//		config.fullscreen = true;
		new LwjglApplication(new MonopolyGame(), config);
	}
}
