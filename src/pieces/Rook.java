package pieces;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import game.Board;
import game.Player;
import game.Type;

public class Rook extends Piece {
	
	Type type;

	public Rook(Board board, Player player, Point position, Colour colour) {
		super(board, player, position, colour);
		type = Type.ROOK;
	}

	@Override
	public boolean isValidPath(Point dest) {
		// if grid is outside of board -> invalid move
		if(dest.x < 0 || dest.x > 7 || dest.y < 0 || dest.y > 7) {
			return false;
		}
		
		// if grid contains same colour piece -> invalid move
		if(this.board.getPiece(dest.x, dest.y) != null &&
				this.board.getPiece(dest.x, dest.y).getColour() == this.colour) {
			return false;
		}
		
		// Otherwise -> valid move
		return true;
	}

	@Override
	public Set<Point> showOptions(Board gameBoard) {
		Set<Point> options = new HashSet<>();
		Point p = this.position;
		
		// Check line of sight above
		for(int i = p.x-1; i >= 0; i--) {
			if(this.board.getPiece(i, p.y) == null) {
				options.add(new Point(i, p.y));
			} else {
				if(this.board.getPiece(i, p.y).getColour() != this.colour) {
					options.add(new Point(i, p.y));
				}
				break;
			}
		}
		
		// Check line of sight below
		for(int i = p.x+1; i < 8; i++) {
			if(this.board.getPiece(i, p.y) == null) {
				options.add(new Point(i, p.y));
			} else {
				if(this.board.getPiece(i, p.y).getColour() != this.colour) {
					options.add(new Point(i, p.y));
				}
				break;
			}
		}
		
		// Check line of sight left
		for(int i = p.y-1; i >= 0; i--) {
			if(this.board.getPiece(p.x, i) == null) {
				options.add(new Point(p.x, i));
			} else {
				if(this.board.getPiece(p.x, i).getColour() != this.colour) {
					options.add(new Point(p.x, i));
				}
				break;
			}
		}
		
		// Check line of sight right
		for(int i = p.y+1; i < 8; i++) {
			if(this.board.getPiece(p.x, i) == null) {
				options.add(new Point(p.x, i));
			} else {
				if(this.board.getPiece(p.x, i).getColour() != this.colour) {
					options.add(new Point(p.x, i));
				}
				break;
			}
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
		return Type.ROOK;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2656";
		}
		return "\u265C";
	}

}
