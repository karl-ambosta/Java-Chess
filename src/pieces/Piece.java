package pieces;

import java.awt.Point;
import java.util.Set;

import game.*;

public abstract class Piece {
	
	public enum Type {
		BISHOP, PAWN, ROOK, QUEEN, KING, KNIGHT, 
	}	
	public enum Colour {WHITE, BLACK};
	
	
	public Point position;
	public final Colour colour;
	public Type pieceType;
	
	public Piece(Point position, Colour colour) {
		this.position = position;
		this.colour = colour;
	}

	public abstract Set<Point> getOptions();

	public abstract void movePiece(Point dest);
	
	public abstract Type getType();
	
	public Colour getColour() {
		return this.colour;
	};
	
	public int getRow() {
		return this.position.x;
	}
	
	public int getColumn() {
		return this.position.y;
	}
	
	public void setRow(int row) {
		this.position.x = row;
	}
	
	public void setColumn(int col) {
		this.position.y = col;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void setPosition(int row, int col) {
		this.position = new Point(row,col);
	}
	
	abstract public String toString();

}
