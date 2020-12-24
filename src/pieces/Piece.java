package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.Board;

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

	public abstract List<Point> getOptions(Board chessBoard);

	public void movePiece(Point dest) {
		this.position = dest;
	}
	
	public abstract Type getType();
	
	public List<Point> checkPoints(List<Point> options) {
		List<Point> toRemove = new ArrayList<Point>();
		
		for(Point p : options) {
			if(p.x < 0 || p.x > 7 || p.y < 0 || p.y > 7) {
				toRemove.add(p);
			}
		}
		
		options.sort(Comparator.comparing(Point::getX));
		return options;
		
	}
	
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
