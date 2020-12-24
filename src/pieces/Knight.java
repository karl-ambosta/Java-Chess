package pieces;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import game.Board;


public class Knight extends Piece {
	
	public Knight(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.KNIGHT;
	}

	@Override
	public Set<Point> getOptions() {
		Set<Point> options = new HashSet<>();
		Point p = this.position;
		
		options.add(new Point(p.x+2, p.y+1));
		options.add(new Point(p.x+1, p.y+2));
		options.add(new Point(p.x-1, p.y+2));
		options.add(new Point(p.x-2, p.y+1));
		options.add(new Point(p.x-2, p.y-1));
		options.add(new Point(p.x-1, p.y-2));
		options.add(new Point(p.x-2, p.y+1));
		options.add(new Point(p.x+1, p.y+2));
		
		Set<Point> toRemove = new HashSet<>();
		
		for(Point p1 : options) {
			if(p1.x < 0 || p1.x > 7 || p1.y < 0 || p1.y > 7) {
				toRemove.add(p1);
			}
		}
		
		options.removeAll(toRemove);
		
		return options;
	}

	@Override
	public void movePiece(Point dest) {
		this.position = dest;
	}

	@Override
	public Type getType() {
		return this.pieceType;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2658";
		}
		return "\u265E";
	}

}
