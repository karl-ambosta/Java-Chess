package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.Board;
import game.Move;

public class Knight extends Piece {
	
	public Knight(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.KNIGHT;
	}
	
	@Override
	public List<Move> getValidMoves(Board board, boolean checkKing) {
		List<Move> options = new ArrayList<Move>();
		Point p = this.position;
		
		if(board == null) {
			return options;
		}
		
		addMoveIfValid(board, options, new Point(p.x+2, p.y+1));
		addMoveIfValid(board, options, new Point(p.x+1, p.y+2));
		addMoveIfValid(board, options, new Point(p.x-1, p.y+2));
		addMoveIfValid(board, options, new Point(p.x-2, p.y+1));
		addMoveIfValid(board, options, new Point(p.x-2, p.y-1));
		addMoveIfValid(board, options, new Point(p.x-1, p.y-2));
		addMoveIfValid(board, options, new Point(p.x-2, p.y+1));
		addMoveIfValid(board, options, new Point(p.x+1, p.y+2));
		
		return options;
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

	

	@Override
	public Piece clone() {
		return new Knight(new Point(this.position.x, this.position.y), this.colour);
	}
}
