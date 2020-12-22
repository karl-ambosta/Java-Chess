package pieces;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import game.*;

public class Bishop extends Piece {
	
	Type type;
	
	public Bishop(Board board, Player player, Point position, Colour colour) {
		super(board, player, position, colour);
		type = Type.BISHOP;
	}

	@Override
	public boolean isValidPath(Point dest) {
		
		// if outside of grid -> invalid move
		if(dest.x < 0 || dest.x > 7 || dest.y < 0 || dest.y > 7) {
			return false;
		}
		
		// if grid contain same colour piece -> invalid move
		if(this.board.getPiece(dest.x, dest.y) != null &&
				this.board.getPiece(dest.x, dest.y).getColour() == this.colour) {
			return false;
		}
		
		return true;
	}

	@Override
	public Set<Point> showOptions(Board gameBoard) {
		Set<Point> options = new HashSet<>();
		Point p = this.position;
		
		// Check line of sight diagonal left up
		for(int i = 1; i < 8; i++) {
			if(this.board.getPiece(p.x-i, p.y-i) == null) {
				options.add(new Point(p.x-i, p.y-i));
			} else {
				if(this.board.getPiece(p.x-i, p.y-i).getColour() != this.colour) {
					options.add(new Point(p.x-i, p.y-i));
				}
				break;
			}
		}
		
		// Check line of sight diagonal left down
		for(int i = 1; i < 8; i++) {
			if(this.board.getPiece(p.x+i, p.y-i) == null) {
				options.add(new Point(p.x+i, p.y-i));
			} else {
				if(this.board.getPiece(p.x+i, p.y-i).getColour() != this.colour) {
					options.add(new Point(p.x+i, p.y-i));
				}
				break;
			}
		}
		
		// Check line of sight diagonal right up
		for(int i = 1; i < 8; i++) {
			if(this.board.getPiece(p.x-i, p.y+i) == null) {
				options.add(new Point(p.x-i, p.y+i));
			} else {
				if(this.board.getPiece(p.x-i, p.y+i).getColour() != this.colour) {
					options.add(new Point(p.x-i, p.y+i));
				}
				break;
			}
		}
		
		// Check line of sight diagonal right down
		for(int i = 1; i < 8; i++) {
			if(this.board.getPiece(p.x+i, p.y+i) == null) {
				options.add(new Point(p.x+i, p.y+i));
			} else {
				if(this.board.getPiece(p.x+i, p.y+i).getColour() != this.colour) {
					options.add(new Point(p.x+i, p.y+i));
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
		return Type.BISHOP;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2657";
		}
		return "\u265D";
	}

}
