package com.mygdx.monopoly.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

public class GameAssets {

	public static TextureRegion boardRegion;
	public static TextureRegion[] gamePieces;

	// Toolbar Assets

	public static TextureRegion toolbar;
	public static TextureRegion toolbar_menu_highlighted;
	public static TextureRegion toolbar_trade_highlighted;
	public static TextureRegion toolbar_menu;
	public static TextureRegion toolbar_menu_help_highlighted;
	public static TextureRegion toolbar_menu_options_highlighted;
	public static TextureRegion toolbar_menu_quit_highlighted;
	public static TextureRegion toolbar_menu_resume_highlighted;
	public static TextureRegion toolbar_menu_save_highlighted;
	public static TextureRegion toolbar_menu_summary_highlighted;
	public static TextureRegion startTurn;
	public static TextureRegion startTurn_highlighted;
	public static TextureRegion rollDice;
	public static TextureRegion rollDice_highlighted;

	public static Rectangle toolbar_menu_bounds;
	public static Rectangle toolbar_trade_bounds;
	public static Rectangle toolbar_menu_help_bounds;
	public static Rectangle toolbar_menu_options_bounds;
	public static Rectangle toolbar_menu_quit_bounds;
	public static Rectangle toolbar_menu_resume_bounds;
	public static Rectangle toolbar_menu_save_bounds;
	public static Rectangle toolbar_menu_summary_bounds;
	public static Rectangle startTurn_bounds;
	
	// Information Assets
	public static TextureRegion player1_info;
	public static TextureRegion player2_info;
	public static TextureRegion player1_info_turn;
	public static TextureRegion player2_info_turn;
	public static TextureRegion bank;

	public static TextureRegion property_grey;
	public static TextureRegion goojfc_chance;
	public static TextureRegion goojfc_chest;

	public static TextureRegion[] gamePieceMarkers;

	public static BitmapFont font24;
	public static BitmapFont font12;

	// Button Assets
	public static TextureRegion rollDiceButton;
	public static Rectangle rollDiceButton_bounds;
	public static TextureRegion finishTurnButton;
	public static TextureRegion finishTurnButtonHovered;
	public static Rectangle finishTurnButton_bounds;
	public static TextureRegion[] spaces;
	public static TextureRegion[] properties;
	public static TextureRegion propertyBuyOptions;
	public static TextureRegion buyHovered;
	public static TextureRegion auctionHovered;
	public static TextureRegion[] dices;

	public static Rectangle buy_bounds;
	public static Rectangle auction_bounds;

	public static TextureRegion[] chances;
	public static TextureRegion[] chests;
	public static TextureRegion cardPopup;
	public static TextureRegion taxPopup;
	public static TextureRegion okHovered;

	public static Rectangle okCard_bounds;
	public static Rectangle okTax_bounds;
	public static Rectangle okRent_bounds;

	public static TextureRegion rentPopup;
	public static TextureRegion jailPopup;
	
	public static TextureRegion postBailHovered;
	public static TextureRegion rollDoublesHovered;
	public static TextureRegion useCardHovered;
	
	public static Rectangle postBail_bounds;
	public static Rectangle useCard_bounds;
	public static Rectangle rollDoubles_bounds;
	
	public static TextureRegion bankrupt;
	public static TextureRegion bankruptHovered;
	public static Rectangle bankrupt_bounds;
	
	public static void gameLoad() {
		boardRegion = new TextureRegion(new Texture(Gdx.files.internal("Images/board.png")));
		gamePieces = new TextureRegion[11];
		for (int i = 0; i < gamePieces.length; i++) {
			gamePieces[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/game_pieces/game_piece_" + (i) + ".png")));
		}
		rollDiceButton = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/RollDice.png")));
		rollDiceButton_bounds = new Rectangle(351, 360, 152, 62);

		finishTurnButton = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/FinishTurn.png")));
		finishTurnButtonHovered = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_menus/finishTurnHovered.png")));
		finishTurnButton_bounds = new Rectangle(350, 359, 152, 62);

		spaces = new TextureRegion[40];
		for (int i = 0; i < spaces.length; i++) {
			spaces[i] = new TextureRegion(new Texture(Gdx.files.internal("Images/spaces/space(" + (i) + ").png")));
		}

		properties = new TextureRegion[28];
		for (int i = 0; i < properties.length; i++) {
			properties[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/properties/property(" + (i) + ").png")));
		}

		propertyBuyOptions = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/buyingProperty.png")));
		buyHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/buyHovered.png")));
		auctionHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/auctionHovered.png")));

		buy_bounds = new Rectangle(244, 192, 96, 40);
		auction_bounds = new Rectangle(385, 192, 96, 40);

		dices = new TextureRegion[6];
		for (int i = 0; i < dices.length; i++) {
			dices[i] = new TextureRegion(new Texture(Gdx.files.internal("Images/dices/dice(" + (i + 1) + ").png")));
		}

		chances = new TextureRegion[15];
		for (int i = 0; i < chances.length; i++) {
			chances[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/community_chance_cards/Chance_" + (i) + ".png")));
		}

		chests = new TextureRegion[16];
		for (int i = 0; i < chests.length; i++) {
			chests[i] = new TextureRegion(
					new Texture(Gdx.files.internal("Images/community_chance_cards/CommunityChest_" + (i) + ".png")));
		}

		cardPopup = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/chance_community_popup.png")));
		taxPopup = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/taxPopup.png")));
		okHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/okHovered.png")));
		okCard_bounds = new Rectangle(315, 238, 96, 40);
		okTax_bounds = new Rectangle(389, 319, 96, 40);
		okRent_bounds = new Rectangle(318, 253, 96, 40);

		rentPopup = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/payRent.png")));
		
		jailPopup = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/jailPopupThree.png")));
		//jailThreePopup = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/jailPopupThree.png")));
		
		useCardHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/useCardHovered.png")));
		postBailHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/postBailHovered.png")));
		rollDoublesHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/rollForDoublesHovered.png")));
		
		postBail_bounds = new Rectangle(114, 216, 96, 40);
		useCard_bounds = new Rectangle(258, 216, 96, 40);
		rollDoubles_bounds = new Rectangle(402, 216, 96, 40);
		
		bankrupt = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/bankrupt.png")));
		bankruptHovered = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/bankruptHovered.png")));
		bankrupt_bounds = new Rectangle(350, 350, 196, 40);
	}

	public static void toolbarLoad() {
		toolbar = new TextureRegion(new Texture(Gdx.files.internal("Images/game_toolbar/toolbar.png")));
		toolbar_menu_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/toolbar_menu.png")));
		toolbar_trade_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/toolbar_trade.png")));
		toolbar_menu = new TextureRegion(new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions.png")));
		toolbar_menu_help_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_help.png")));
		toolbar_menu_options_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_options.png")));
		toolbar_menu_quit_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_quit.png")));
		toolbar_menu_resume_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_resume.png")));
		toolbar_menu_save_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_save.png")));
		toolbar_menu_summary_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/menuOptions_summary.png")));

		toolbar_menu_bounds = new Rectangle(11, 12, 93, 32);
		toolbar_trade_bounds = new Rectangle(619, 12, 93, 32);
		toolbar_menu_help_bounds = new Rectangle(9, 57, 93, 32);
		toolbar_menu_summary_bounds = new Rectangle(9, 99, 93, 32);
		toolbar_menu_quit_bounds = new Rectangle(9, 142, 93, 32);
		toolbar_menu_save_bounds = new Rectangle(9, 185, 93, 32);
		toolbar_menu_resume_bounds = new Rectangle(9, 227, 93, 32);
		toolbar_menu_options_bounds = new Rectangle(9, 271, 93, 32);

		rollDice = new TextureRegion(new Texture(Gdx.files.internal("Images/game_toolbar/RollDice.png")));
		rollDice_highlighted = new TextureRegion(
				new Texture(Gdx.files.internal("Images/game_toolbar/RollDice_hovered.png")));
		startTurn = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/startTurn.png")));
		startTurn_highlighted = new TextureRegion(new Texture(Gdx.files.internal("Images/game_menus/startTurnHovered.png")));
		startTurn_bounds = new Rectangle(743, 20, 154, 61);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Container/kabel.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 24;
		parameter.borderWidth = 1;
		parameter.color = Color.WHITE;
		parameter.borderColor = Color.BLACK;
		font24 = generator.generateFont(parameter); // font size 24 pixels
		parameter.size = 12;
		font12 = generator.generateFont(parameter);
		generator.dispose();
	}

	public static void informationLoad() {
		player1_info = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/player1_info.png")));
		player2_info = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/player2_info.png")));
		player1_info_turn = new TextureRegion(
				new Texture(Gdx.files.internal("Images/player_info/player1_info_turn.png")));
		player2_info_turn = new TextureRegion(
				new Texture(Gdx.files.internal("Images/player_info/player2_info_turn.png")));
		bank = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/bank.png")));
		property_grey = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/grey.png")));
		goojfc_chance = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/GOOJFC_chance.png")));
		goojfc_chest = new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/GOOJFC_chest.png")));
		gamePieceMarkers = new TextureRegion[11];
		for (int i = 0; i < gamePieceMarkers.length; i++) {
			gamePieceMarkers[i] = new TextureRegion(new Texture(
					Gdx.files.internal("Images/player_info/game_piece_markers/game_piece_marker_" + i + ".png")));
		}
	}

}
