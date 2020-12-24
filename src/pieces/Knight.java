package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.Board;

public class Knight extends Piece {
	
	public Knight(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.KNIGHT;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;
		
		options.add(new Point(p.x+2, p.y+1));
		options.add(new Point(p.x+1, p.y+2));
		options.add(new Point(p.x-1, p.y+2));
		options.add(new Point(p.x-2, p.y+1));
		options.add(new Point(p.x-2, p.y-1));
		options.add(new Point(p.x-1, p.y-2));
		options.add(new Point(p.x-2, p.y+1));
		options.add(new Point(p.x+1, p.y+2));
		
		return checkPoints(options);
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
