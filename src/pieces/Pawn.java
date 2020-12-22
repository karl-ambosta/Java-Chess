package pieces;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import game.Board;
import game.Player;
import game.Type;

public class Pawn extends Piece {
	
	Type type;

	public Pawn(Board board, Player player, Point position, Colour colour) {
		super(board, player, position, colour);
		type = Type.PAWN;
	}

	@Override
	public boolean isValidPath(Point dest) {
		// if outside of grid square -> invalid move
		if(dest.x < 0 || dest.x > 7 || dest.y < 0 || dest.y > 7) {
			return false;
		}
		
		// if empty space in front -> valid move
		if(dest.y == this.position.y) {
			if(this.board.getPiece(this.position.x-1, this.position.y) == null) {
				return true;
			}
		} else {
			// if diagonals have a different colour piece -> valid move
			if(this.position.y > 0) { 
				if(this.board.getPiece(this.position.x-1, this.position.y-1) != null && 
						this.board.getPiece(this.position.x-1, this.position.y-1).getColour() != this.colour) {
					return true;
				}
			}
			
			if(this.board.getPiece(this.position.x-1, this.position.y+1) != null && 
					this.board.getPiece(this.position.x-1, this.position.y-1).getColour() != this.colour) {
				return true;
			}
		}
		
		// Otherwise -> invalid move
		return false;
	}

	@Override
	public Set<Point> showOptions(Board gameBoard) {
		Set<Point> options = new HashSet<>();
		Point p = this.position;

		if(isValidPath(new Point(p.x-1, p.y))) {
			options.add(new Point(p.x-1, p.y));
		}
		if(isValidPath(new Point(p.x-1, p.y+1))) {
			options.add(new Point(p.x-1, p.y+1));
		}
		if(isValidPath(new Point(p.x-1, p.y-1))) {
			options.add(new Point(p.x-1, p.y-1));
		}
		
		return options;
	}

	@Override
	public Point drawPath(Point start, Point dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean movePiece(Point dest) {
		Set<Point> options = showOptions(this.board);
		
		if(options.contains(dest)) {
			setPosition(dest.x, dest.y);
			return true;
		}
		
		return false;
	}

	@Override
	public Type getType() {
		return Type.PAWN;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2659";
		}
		return "\u265F";
	}

}
