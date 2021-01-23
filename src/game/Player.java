package game;

import pieces.Piece.Colour;

public class Player {
	
	private String playerName;
	private Colour colour;
	private boolean isHuman;
	
	public Player(String name, Colour c, boolean isHuman) {
		this.playerName = name;
		this.colour = c;
		this.isHuman = isHuman;
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public Colour getColour() {
		return this.colour;
	}

}
