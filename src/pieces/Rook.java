package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Rook extends Piece {

	public Rook(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.ROOK;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;
		
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
		
		for(int i = p.y-1; i >= 0; i--) {
			if(chessBoard.getPiece(p.x, i) != null) {
				if(chessBoard.getPiece(p.x, i).getColour() == this.colour) {
					break;
				}
				options.add(new Point(p.x, i));
			} else {
				options.add(new Point(p.x, i));
			}
		}
		
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
}
