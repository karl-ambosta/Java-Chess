package pieces;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.Board;
import game.Move;

public abstract class Piece implements Serializable, Cloneable {
	
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
	
	public abstract List<Move> getValidMoves(Board board, boolean checkKing);

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
		
		options.removeAll(toRemove);
		
		options.sort(Comparator.comparing(Point::getX));
		return options;
		
	}
	
	public void addMovesInLine(Board board, List<Move> moves, int x_offset, int y_offset) {
		Point p = new Point(this.position.x + x_offset, this.position.y + y_offset);
		
		while(board.isValidGrid(p)) {
			Piece pc = board.getPiece(p.x, p.y);
			if(pc == null) {
				moves.add(new Move(this, p, pc));
			} else if(pc.getColour() != this.colour) {
				moves.add(new Move(this, p, pc));
				break;
			} else {
				break;
			}
			p = new Point(p.x + x_offset, p.y + y_offset);
		}
	}
	
	public Colour getColour() {
		return this.colour;
	};
	
	public Point getPosition() {
		return this.position;
	}
	
	public void setPosition(Point p) {
		this.position = new Point(p.x, p.y);
	}
	
	@Override
	public abstract Piece clone();
	
	public abstract String toString();

}
