package pieces;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import game.Board;
import game.Player;
import game.Type;

public class King extends Piece {
	
	Type type;

	public King(Board board, Player player, Point position, Colour colour) {
		super(board, player, position, colour);
		type = Type.KING;
	}

	@Override
	public boolean isValidPath(Point dest) {
		// If grid is outside of board -> invalid move
		if(dest.x < 0 || dest.x > 7 || dest.y < 0 || dest.y > 7) {
			return false;
		}
		
		// If destination has same colour piece -> invalid move
		if(this.board.getPiece(dest.x, dest.y) != null && this.board.getPiece(dest.x, dest.y).getColour() == this.colour) {
			return false;
		}
		
		// Otherwise -> valid move
		return true;
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
		if(isValidPath(new Point(p.x, p.y+1))) {
			options.add(new Point(p.x, p.y+1));
		}
		if(isValidPath(new Point(p.x+1, p.y+1))) {
			options.add(new Point(p.x+1, p.y+1));
		}
		if(isValidPath(new Point(p.x+1, p.y))) {
			options.add(new Point(p.x+1, p.y));
		}
		if(isValidPath(new Point(p.x+1, p.y-1))) {
			options.add(new Point(p.x+1, p.y-1));
		}
		if(isValidPath(new Point(p.x, p.y-1))) {
			options.add(new Point(p.x, p.y-1));
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
		return Type.KING;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2654";
		}
		return "\u265A";
	}

}
