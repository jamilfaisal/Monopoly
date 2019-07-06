package com.mygdx.monopoly;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.monopoly.screens.GameScreen;

/**
 * Board class which initializes the interactive board
 * The board is designed with arrays for players, spaces, properties, and property groups
 */
public class Board {

	private int numOfPlayer;
	private ArrayList<Integer> pieceIndex = new ArrayList<Integer>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Space> spaces = new ArrayList<Space>();
	private ArrayList<Property> properties = new ArrayList<Property>();
	private ArrayList<PropertyGroup> propertyGroups = new ArrayList<PropertyGroup>();
	private Player bank = new Player();

	private GameScreen screen;

	public Board(int numOfPlayer, int p1piece, int p2piece, int p3piece, int p4piece, GameScreen screen) {
		this.screen = screen;
		this.numOfPlayer = numOfPlayer;
		if (p1piece > -1) {
			pieceIndex.add(p1piece);
		}
		if (p2piece > -1) {
			pieceIndex.add(p2piece);
		}
		if (p3piece > -1) {
			pieceIndex.add(p3piece);
		}
		if (p4piece > -1) {
			pieceIndex.add(p4piece);
		}
		createPlayers();
		createBoard();
	}

	/**
	 * Creates all properties onto the board
	 */
	private void createBoard() {
		spaces.add(new GoSpace(this));
		initProperty(new ColorProperty(2, 10, 30, 90, 160, 250, 30, 60, "darkpurple", 1, bank, 766, 663, 766, 374,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/darkpurple.png"))), 558, 154)); // 0
		spaces.add(new CommunitySpace(this));
		initProperty(new ColorProperty(4, 20, 60, 180, 320, 450, 30, 60, "darkpurple", 1, bank, 791, 663, 791, 374,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/darkpurple.png"))), 446, 154)); // 1
		spaces.add(new TaxSpace("income", this));
		initProperty(new StationProperty(100, 200, "station", 1, bank, 899, 543, 899, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/station.png"))), 334, 154)); // 2
		initProperty(new ColorProperty(6, 30, 90, 270, 400, 550, 50, 100, "lightblue", 1, bank, 766, 632, 766, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/lightblue.png"))), 278, 154)); // 3
		spaces.add(new ChanceSpace(this));
		initProperty(new ColorProperty(6, 30, 90, 270, 400, 550, 50, 100, "lightblue", 1, bank, 791, 633, 791, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/lightblue.png"))), 167, 154)); // 4
		initProperty(new ColorProperty(8, 40, 100, 300, 450, 600, 60, 120, "lightblue", 1, bank, 816, 633, 816, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/lightblue.png"))), 110, 154)); // 5
		spaces.add(new JailSpace(this));
		initProperty(new ColorProperty(10, 50, 150, 450, 625, 750, 70, 140, "pink", 2, bank, 766, 603, 766, 314,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/pink.png"))), 93, 170)); // 6
		initProperty(new UtilityProperty(150, 75, "utility", 2, bank, 899, 573, 899, 284,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/utility2.png"))), 93, 227)); // 7
		initProperty(new ColorProperty(10, 50, 150, 450, 625, 750, 70, 140, "pink", 2, bank, 791, 603, 791, 314,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/pink.png"))), 93, 283)); // 8
		initProperty(new ColorProperty(12, 60, 180, 500, 700, 900, 80, 160, "pink", 2, bank, 816, 603, 816, 314,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/pink.png"))), 93, 340)); // 9
		initProperty(new StationProperty(100, 200, "station", 2, bank, 924, 543, 924, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/station.png"))), 93, 395)); // 10
		initProperty(new ColorProperty(14, 70, 200, 550, 750, 950, 90, 180, "orange", 2, bank, 766, 573, 766, 284,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/orange.png"))), 93, 452)); // 11
		spaces.add(new CommunitySpace(this));
		initProperty(new ColorProperty(14, 70, 200, 550, 750, 950, 90, 180, "orange", 2, bank, 791, 573, 791, 284,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/orange.png"))), 93, 563)); // 12
		initProperty(new ColorProperty(16, 80, 220, 600, 800, 1000, 100, 200, "orange", 2, bank, 816, 573, 816, 284,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/orange.png"))), 93, 619)); // 13
		spaces.add(new FreeParkingSpace(this));
		initProperty(new ColorProperty(18, 90, 250, 700, 875, 1050, 110, 220, "red", 3, bank, 766, 543, 766, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/red.png"))), 109, 636)); // 14
		spaces.add(new ChanceSpace(this));
		initProperty(new ColorProperty(18, 90, 250, 700, 875, 1050, 110, 220, "red", 3, bank, 791, 543, 791, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/red.png"))), 221, 636)); // 15
		initProperty(new ColorProperty(20, 100, 300, 750, 925, 1100, 120, 240, "red", 3, bank, 816, 543, 816, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/red.png"))), 278, 636)); // 16
		initProperty(new StationProperty(100, 200, "station", 3, bank, 949, 543, 949, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/station.png"))), 334, 636)); // 17
		initProperty(new ColorProperty(22, 110, 330, 800, 975, 1150, 130, 260, "yellow", 3, bank, 899, 663, 899, 374,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/yellow.png"))), 389, 636)); // 18
		initProperty(new ColorProperty(22, 110, 330, 800, 975, 1150, 130, 260, "yellow", 3, bank, 924, 663, 924, 374,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/yellow.png"))), 446, 636)); // 19
		initProperty(new UtilityProperty(150, 75, "utility", 3, bank, 924, 573, 924, 284,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/utility.png"))), 501, 636)); // 20
		initProperty(new ColorProperty(24, 120, 360, 850, 1025, 1200, 140, 280, "yellow", 3, bank, 949, 663, 949, 374,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/yellow.png"))), 558, 636)); // 21
		spaces.add(new GoToJailSpace(this));
		initProperty(new ColorProperty(26, 130, 390, 900, 1105, 1275, 150, 300, "green", 4, bank, 899, 633, 899, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/green.png"))), 573, 619)); // 22
		initProperty(new ColorProperty(26, 130, 390, 900, 1105, 1275, 150, 300, "green", 4, bank, 924, 633, 924, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/green.png"))), 573, 563)); // 23
		spaces.add(new CommunitySpace(this));
		initProperty(new ColorProperty(28, 150, 450, 1000, 1200, 1400, 160, 320, "green", 4, bank, 949, 633, 949, 344,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/green.png"))), 573, 452)); // 24
		initProperty(new StationProperty(100, 200, "station", 4, bank, 974, 543, 974, 254,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/station.png"))), 573, 395)); // 25
		spaces.add(new ChanceSpace(this));
		initProperty(new ColorProperty(35, 175, 500, 1100, 1300, 1500, 175, 350, "blue", 4, bank, 899, 603, 899, 314,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/blue.png"))), 573, 283)); // 26
		spaces.add(new TaxSpace("luxury", this));
		initProperty(new ColorProperty(50, 200, 600, 1400, 1700, 2000, 200, 400, "blue", 4, bank, 924, 603, 924, 314,
				new TextureRegion(new Texture(Gdx.files.internal("Images/player_info/blue.png"))), 573, 170)); // 27

		initGroups();
	}

	/**
	 * Initiates the players based on the number of players selected
	 */
	private void createPlayers() {
		for (int i = 0; i < numOfPlayer; i++) {
			Player p = new Player(pieceIndex.get(i), "Player " + i);
			players.add(p);
		}
	}

	/**
	 * Adds the property to the spaces and property arrays and sets the owner as the
	 * bank
	 * 
	 * @param p
	 *            The newly created property
	 */
	private void initProperty(Property p) {
		spaces.add(new PropertySpace(p, this));
		bank.addProperty(p);
		properties.add(p);
	}

	/**
	 * Sets the properties into colored groups
	 */
	private void initGroups() {
		propertyGroups.add(new PropertyGroup(properties.get(0), properties.get(1), "Brown"));
		properties.get(0).setGroup(propertyGroups.get(0));
		properties.get(1).setGroup(propertyGroups.get(0));
		propertyGroups.add(new PropertyGroup(properties.get(3), properties.get(4), properties.get(5), "LightBlue"));
		properties.get(3).setGroup(propertyGroups.get(1));
		properties.get(4).setGroup(propertyGroups.get(1));
		properties.get(5).setGroup(propertyGroups.get(1));
		propertyGroups.add(new PropertyGroup(properties.get(6), properties.get(8), properties.get(9), "Pink"));
		properties.get(6).setGroup(propertyGroups.get(2));
		properties.get(8).setGroup(propertyGroups.get(2));
		properties.get(9).setGroup(propertyGroups.get(2));
		propertyGroups.add(new PropertyGroup(properties.get(11), properties.get(12), properties.get(13), "Orange"));
		properties.get(11).setGroup(propertyGroups.get(3));
		properties.get(12).setGroup(propertyGroups.get(3));
		properties.get(13).setGroup(propertyGroups.get(3));
		propertyGroups.add(new PropertyGroup(properties.get(14), properties.get(15), properties.get(16), "Red"));
		properties.get(14).setGroup(propertyGroups.get(4));
		properties.get(15).setGroup(propertyGroups.get(4));
		properties.get(16).setGroup(propertyGroups.get(4));
		propertyGroups.add(new PropertyGroup(properties.get(18), properties.get(19), properties.get(21), "Yellow"));
		properties.get(18).setGroup(propertyGroups.get(5));
		properties.get(19).setGroup(propertyGroups.get(5));
		properties.get(21).setGroup(propertyGroups.get(5));
		propertyGroups.add(new PropertyGroup(properties.get(22), properties.get(23), properties.get(24), "Green"));
		properties.get(22).setGroup(propertyGroups.get(6));
		properties.get(23).setGroup(propertyGroups.get(6));
		properties.get(24).setGroup(propertyGroups.get(6));
		propertyGroups.add(new PropertyGroup(properties.get(26), properties.get(27), "Blue"));
		properties.get(26).setGroup(propertyGroups.get(7));
		properties.get(27).setGroup(propertyGroups.get(7));
		propertyGroups.add(new PropertyGroup(properties.get(2), properties.get(10), properties.get(17),
				properties.get(25), "Station"));
		properties.get(2).setGroup(propertyGroups.get(8));
		properties.get(10).setGroup(propertyGroups.get(8));
		properties.get(17).setGroup(propertyGroups.get(8));
		properties.get(25).setGroup(propertyGroups.get(8));
		propertyGroups.add(new PropertyGroup(properties.get(7), properties.get(20), "Utility"));
		properties.get(7).setGroup(propertyGroups.get(9));
		properties.get(20).setGroup(propertyGroups.get(9));
	}

	public Player getBank() {
		return bank;
	}

	public GameScreen getScreen() {
		return screen;
	}

	public Player getPlayer(int index) {
		return players.get(index);
	}

	public Space getSpace(int index) {
		return spaces.get(index);
	}

	public int getNumOfPlayers() {
		return numOfPlayer;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}
}
