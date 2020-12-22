package pieces;

import java.awt.Point;
import java.util.Set;

import game.*;

public abstract class Piece {
	
	public enum Colour {WHITE, BLACK};
	
	public Board board;
	public Player player;
	public Point position;
	public Colour colour;	
	
	public Piece(Board board, Player player, Point position, Colour colour) {
		this.board = board;
		this.player = player;
		this.position = position;
		this.colour = colour;
	}
	
	public abstract boolean isValidPath(Point dest);
	
	public abstract Set<Point> showOptions(Board gameBoard);
	
	public abstract Point drawPath(Point start, Point dest);
	
	public abstract boolean movePiece(Point dest);
	
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
