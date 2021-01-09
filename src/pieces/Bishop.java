package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Bishop extends Piece {

	public Bishop(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.BISHOP;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;

		// Check diagonal right down
		for(int i = 1; (p.x+i < 8 && p.y+i < 8); i++) {
			if(chessBoard.getPiece(p.x+i, p.y+i) != null) {
				if(chessBoard.getPiece(p.x+i, p.y+i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x+i,p.y+i));
				break;
			} else {
				options.add(new Point(p.x+i,p.y+i));
			}
		}
		
		// Check diagonal left down
		for(int i = 1; (p.x+i < 8 && p.y-i >= 0); i++) {
			if(chessBoard.getPiece(p.x+i, p.y-i) != null) {
				if(chessBoard.getPiece(p.x+i, p.y-i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x+i,p.y-i));
				break;
			} else {
				options.add(new Point(p.x+i,p.y-i));
			}
		}
		
		// Check diagonal left up
		for(int i = 1; (p.x-i >= 0 && p.y-i >= 0); i++) {
			if(chessBoard.getPiece(p.x-i, p.y-i) != null) {
				if(chessBoard.getPiece(p.x-i, p.y-i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x-i,p.y-i));
				break;
			} else {
				options.add(new Point(p.x-i,p.y-i));
			}	
		}
		
		// Check diagonal right up
		for(int i = 1; (p.x-i >= 0 && p.y+i < 8); i++) {
			if(chessBoard.getPiece(p.x-i, p.y+i) != null) {
				if(chessBoard.getPiece(p.x-i, p.y+i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x-i,p.y+i));
				break;
			} else {
				options.add(new Point(p.x-i,p.y+i));
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
			return "\u2657";
		}
		return "\u265D";
	}
}
