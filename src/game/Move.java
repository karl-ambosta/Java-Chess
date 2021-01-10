package game;

import java.awt.Point;

import pieces.Piece;

public class Move {
	private Piece toMove;
	private Point toPosition;
	private Piece toCapture;
	
	public Move(Piece p, Point pos, Piece p2) {
		this.toMove = p;
		this.toPosition = pos;
		this.toCapture = p2;
	}
	
	public Piece getPiece() {
		return this.toMove;
	}
	
	public Point getPosition() {
		return this.toPosition;
	}
	
	public Piece getCaptured() {
		return this.toCapture;
	}
	
}
