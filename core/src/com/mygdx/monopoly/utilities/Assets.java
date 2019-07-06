package com.mygdx.monopoly.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Pre-game assets class
 */
public class Assets {

	// Loads local settings
	public static GamePreferences prefs;

	// Music and sound files
	public static Music backgroundMusic;
	public static Sound buttonSound;

	// Used for mouse pointer location
	public static Vector3 touchPos = new Vector3();

	// Sets volume for sound effects
	public static float soundFXVolume;

	// Main menu assets
	public static TextureRegion newGameHoveredButton;
	public static TextureRegion exitHoveredButton;
	public static TextureRegion optionsHoveredButton;
	public static TextureRegion helpHoveredButton;
	public static TextureRegion menuRegion;

	public static Rectangle newGameBounds;
	public static Rectangle helpBounds;
	public static Rectangle loadGameBounds;
	public static Rectangle optionsBounds;
	public static Rectangle exitBounds;

	// Options menu assets
	public static TextureRegion optionsMenuRegion;
	public static TextureRegion closeHoveredButton;
	public static TextureRegion musicAdjusterRegion;
	public static TextureRegion musicAdjusterHovered;
	public static TextureRegion soundAdjusterRegion;
	public static TextureRegion soundAdjusterHovered;

	public static Rectangle closeOptionsBounds;
	public static Rectangle musicAdjusterBounds;
	public static Rectangle soundAdjusterBounds;

	// Help menu assets
	public static TextureRegion[] helpPages;
	public static TextureRegion previousButton;
	public static TextureRegion closeHelpButton;
	public static TextureRegion nextButton;

	public static TextureRegion previousHoveredButton;
	public static TextureRegion closeHelpHoveredButton;
	public static TextureRegion nextHoveredButton;

	public static Rectangle previousBounds;
	public static Rectangle closeHelpBounds;
	public static Rectangle nextBounds;

	// New Game Setup assets
	public static TextureRegion[] newPages;
	public static TextureRegion backHoveredButton;
	public static TextureRegion playHoveredButton;
	public static TextureRegion addPlayerHoveredButton;
	public static TextureRegion removeHoveredButton;
	public static TextureRegion rightArrowHoveredButton;
	public static TextureRegion leftArrowHoveredButton;
	public static TextureRegion[] gamePieces;

	public static Rectangle playBounds;
	public static Rectangle backBounds;
	public static Rectangle add2PlayerBounds;
	public static Rectangle add3PlayerBounds;
	public static Rectangle remove3PlayerBounds;
	public static Rectangle remove4Player3Bounds;
	public static Rectangle remove4Player4Bounds;
	public static Rectangle leftPlayer1Bounds;
	public static Rectangle leftPlayer2Bounds;
	public static Rectangle leftPlayer3Bounds;
	public static Rectangle leftPlayer4Bounds;
	public static Rectangle left3Player1Bounds;
	public static Rectangle left3Player2Bounds;
	public static Rectangle left3Player3Bounds;
	public static Rectangle rightPlayer1Bounds;
	public static Rectangle rightPlayer2Bounds;
	public static Rectangle rightPlayer3Bounds;
	public static Rectangle rightPlayer4Bounds;
	public static Rectangle right3Player1Bounds;
	public static Rectangle right3Player2Bounds;
	public static Rectangle right3Player3Bounds;

	public static void mainMenuLoad() {
		prefs = new GamePreferences();
		soundFXVolume = prefs.getSoundVolume();
		// Initiates main menu sound
		Assets.backgroundMusic = GameMusic.changeMusic("mainMenuScreen", Assets.backgroundMusic);
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/main_menu_sounds/4_button_sound.mp3"));

		menuRegion = new TextureRegion(new Texture(Gdx.files.internal("Images/main_menu/main_menu.png")));

		newGameHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/main_menu/new_game_highlighted.png")));
		exitHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/main_menu/exit_highlighted.png")));
		optionsHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/main_menu/options_highlighted.png")));
		helpHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/main_menu/help_highlighted.png")));

		helpBounds = new Rectangle(176, 588 - 521, 142, 60);
		newGameBounds = new Rectangle(389, 588 - 415, 252, 60);
		optionsBounds = new Rectangle(355, 588 - 521, 142, 60);
		exitBounds = new Rectangle(707, 588 - 521, 142, 60);

	}

	public static void optionsMenuLoad() {
		optionsMenuRegion = new TextureRegion(new Texture(Gdx.files.internal("Images/options/options.png")));
		closeHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/options/options_close_highlighted.png")));
		musicAdjusterRegion = new TextureRegion(new Texture(Gdx.files.internal("Images/options/420.png")));
		musicAdjusterHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/options/424.png")));
		soundAdjusterRegion = new TextureRegion(new Texture(Gdx.files.internal("Images/options/420.png")));
		soundAdjusterHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/options/424.png")));

		closeOptionsBounds = new Rectangle(464, 588 - 506, 93, 33);
		musicAdjusterBounds = new Rectangle(410 + Assets.backgroundMusic.getVolume() * 250, 588 - 178, 45, 60);
		soundAdjusterBounds = new Rectangle(410 + Assets.soundFXVolume * 250, 588 - 250, 45, 60);

	}

	public static void helpMenuLoad() {
		helpPages = new TextureRegion[21];
		for (int i = 0; i < helpPages.length; i++) {
			helpPages[i] = new TextureRegion(new Texture(Gdx.files.internal("Images/help/Page" + (i + 1) + ".png")));
		}
		previousButton = new TextureRegion(new Texture(Gdx.files.internal("Images/help/previousButton.png")));
		closeHelpButton = new TextureRegion(new Texture(Gdx.files.internal("Images/help/closeButton.png")));
		nextButton = new TextureRegion(new Texture(Gdx.files.internal("Images/help/nextButton.png")));

		previousHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/help/previousButtonHighlighted.png")));
		closeHelpHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/help/closeButtonHighlighted.png")));
		nextHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/help/nextButtonHighlighted.png")));

		previousBounds = new Rectangle(298, 20, 115, 36);
		closeHelpBounds = new Rectangle(458, 20, 115, 36);
		nextBounds = new Rectangle(616, 20, 115, 36);
	}

	public static void newGameLoad() {
		newPages = new TextureRegion[3];
		for (int i = 0; i < newPages.length; i++) {
			newPages[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/new_game/new_game_" + (i + 2) + "_player.png")));
		}

		backHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/new_game/back_hovered.png")));
		playHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/new_game/play_hovered.png")));
		addPlayerHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/new_game/add_player_hovered.png")));
		removeHoveredButton = new TextureRegion(new Texture(Gdx.files.internal("Images/new_game/remove_hovered.png")));
		leftArrowHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/new_game/left_arrow_hovered.png")));
		rightArrowHoveredButton = new TextureRegion(
				new Texture(Gdx.files.internal("Images/new_game/right_arrow_hovered.png")));

		playBounds = new Rectangle(891, 18, 91, 52);
		backBounds = new Rectangle(29, 22, 95, 39);
		add2PlayerBounds = new Rectangle(800, 346, 72, 108);
		add3PlayerBounds = new Rectangle(921, 346, 72, 108);
		remove3PlayerBounds = new Rectangle(709, 586, 96, 40);
		remove4Player3Bounds = new Rectangle(586, 586, 96, 40);
		remove4Player4Bounds = new Rectangle(827, 586, 96, 40);
		leftPlayer1Bounds = new Rectangle(51, 177, 33, 37);
		leftPlayer2Bounds = new Rectangle(294, 177, 33, 37);
		leftPlayer3Bounds = new Rectangle(539, 177, 33, 37);
		leftPlayer4Bounds = new Rectangle(779, 177, 33, 37);
		left3Player1Bounds = new Rectangle(172, 177, 33, 37);
		left3Player2Bounds = new Rectangle(416, 177, 33, 37);
		left3Player3Bounds = new Rectangle(657, 177, 33, 37);
		rightPlayer1Bounds = new Rectangle(215, 177, 33, 37);
		rightPlayer2Bounds = new Rectangle(458, 177, 33, 37);
		rightPlayer3Bounds = new Rectangle(701, 177, 33, 37);
		rightPlayer4Bounds = new Rectangle(943, 177, 33, 37);
		right3Player1Bounds = new Rectangle(337, 177, 33, 37);
		right3Player2Bounds = new Rectangle(578, 177, 33, 37);
		right3Player3Bounds = new Rectangle(820, 177, 33, 37);

		gamePieces = new TextureRegion[11];
		for (int i = 0; i < gamePieces.length; i++) {
			gamePieces[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/game_pieces/game_piece_" + (i) + ".png")));
		}
	}

	/**
	 * Sets the background music volume based on adjuster's location
	 * 
	 * @param music
	 *            The music affected by the slider
	 */
	public static void setVolume(Music backgroundMusic) {
		backgroundMusic.setVolume((Assets.musicAdjusterBounds.x - 410) / 250);
		if ((Assets.musicAdjusterBounds.x - 410) / 250 < 0) {
			backgroundMusic.setVolume(0);
			Assets.musicAdjusterBounds.x = 410;
		}
	}

}
