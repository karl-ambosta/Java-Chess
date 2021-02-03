package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.Board;
import game.Move;

public class King extends Piece {
	
	boolean hasMoved;

	public King(Point position, Colour colour, Boolean hasMoved) {
		super(position, colour);
		this.pieceType = Type.KING;
		this.hasMoved = hasMoved;
	}
	
	@Override
	public List<Move> getValidMoves(Board board, boolean checkKing) {
		List<Move> options = new ArrayList<Move>();
		Point p = this.position;
		
		if(board == null) {
			return options;
		}
		
		addMoveIfValid(board, options, new Point(p.x+1, p.y+1));
		addMoveIfValid(board, options, new Point(p.x+1, p.y));
		addMoveIfValid(board, options, new Point(p.x+1, p.y-1));
		addMoveIfValid(board, options, new Point(p.x, p.y+1));
		addMoveIfValid(board, options, new Point(p.x, p.y-1));
		addMoveIfValid(board, options, new Point(p.x-1, p.y+1));
		addMoveIfValid(board, options, new Point(p.x-1, p.y));
		addMoveIfValid(board, options, new Point(p.x-1, p.y-1));
		
		return options;
		
	}

	@Override
	public Type getType() {
		return this.pieceType;
	}
	
	public void setMoved(boolean b) {
		this.hasMoved = b;
	}
	
	public boolean getMoved() {
		return this.hasMoved;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2654";
		}
		return "\u265A";
	}

	@Override
	public Piece clone() {
		return new King(new Point(this.position.x, this.position.y), this.colour, this.hasMoved);
	}

	
	
}
