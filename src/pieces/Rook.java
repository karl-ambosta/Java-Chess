package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Move;

public class Rook extends Piece {

	public Rook(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.ROOK;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;
		
		// Check squares above
		for(int i = p.x-1; i >= 0; i--) {
			if(chessBoard.getPiece(i, p.y) != null) {
				if(chessBoard.getPiece(i, p.y).getColour() == this.colour) {
					break;
				}
				options.add(new Point(i,p.y));
			} else {
				options.add(new Point(i,p.y));
			}
			
		}
		
		// Check squares below
		for(int i = p.x+1; i < 8; i++) {
			if(chessBoard.getPiece(i, p.y) != null) {
				if(chessBoard.getPiece(i, p.y).getColour() == this.colour) {
					break;
				}
				options.add(new Point(i,p.y));
			} else {
				options.add(new Point(i,p.y));
			}
		}
		
		// Check squares left
		for(int i = p.y-1; i >= 0; i--) {
			if(chessBoard.getPiece(p.x, i) != null) {
				if(chessBoard.getPiece(p.x, i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x, i));
				break;
			} else {
				options.add(new Point(p.x, i));
			}
		}
		
		// Check squares right
		for(int i = p.y+1; i < 8; i++) {
			if(chessBoard.getPiece(p.x, i) != null) {
				if(chessBoard.getPiece(p.x, i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x, i));
			} else {
				options.add(new Point(p.x, i));
			}
		}
		
		return checkPoints(options);
	}

	@Override
	public Type getType() {
		return this.pieceType;
	}

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2656";
		}
		return "\u265C";
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
		
		return options;
	}

	@Override
	public Piece clone() {
		return new Rook(new Point(this.position.x, this.position.y),
                this.colour);
	}
}
