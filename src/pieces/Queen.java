package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.Board;
import game.Move;

public class Queen extends Piece {

	public Queen(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.QUEEN;
	}
	
	@Override
	public List<Move> getValidMoves(Board board, boolean checkKing) {
		List<Move> options = new ArrayList<Move>();
		Point p = this.position;

		// add moves in lines up, down, left, right
		addMovesInLine(board, options, -1, 0);
		addMovesInLine(board, options, 1, 0);
		addMovesInLine(board, options, 0, -1);
		addMovesInLine(board, options, 0, 1);
		
		// add moves in lines diagonals
		addMovesInLine(board, options, -1, -1);
		addMovesInLine(board, options, -1, 1);
		addMovesInLine(board, options, 1, 1);
		addMovesInLine(board, options, 1, -1);
		
		return options;
	}

	@Override
	public Type getType() {
		return this.pieceType;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2655";
		}
		return "\u265B";
	}
	
	@Override
	public Piece clone() {
        return new Queen(new Point(this.position.x, this.position.y),
                this.colour);
    }	
}
