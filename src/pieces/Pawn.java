package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Board;
public class Pawn extends Piece {
	
	boolean hasMoved;
	boolean isPromoted;
	
	public Pawn(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.PAWN;
		this.hasMoved = false;
		this.isPromoted = false;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;
		
		if(this.colour == Colour.WHITE) {
			// Check if tile in front is empty
			if(chessBoard.getPiece(p.x-1, p.y) == null) {
				options.add(new Point(p.x-1,p.y));
			}
			// If pawn hasn't moved yet, the tile two spaces in front is valid
			if(hasMoved == false && chessBoard.getPiece(p.x-2, p.y) == null) {
				options.add(new Point(p.x-2,p.y));
			}
			
			// Check if enemy occupies diagonal, if so, valid move to kill piece
			if(chessBoard.getPiece(p.x-1, p.y-1) != null && chessBoard.getPiece(p.x-1, p.y-1).getColour() != this.colour) {
				options.add(new Point(p.x-1,p.y-1));
			}
			if(chessBoard.getPiece(p.x-1, p.y+1) != null && chessBoard.getPiece(p.x-1, p.y+1).getColour() != this.colour) {
				options.add(new Point(p.x-1,p.y+1));
			}			
		} else {
			// Check if tile in front is empty
			if(chessBoard.getPiece(p.x+1, p.y) == null) {
				options.add(new Point(p.x+1,p.y));
			}
			
			// If pawn hasn't moved yet, the tile two spaces in front is valid
			if(hasMoved == false && chessBoard.getPiece(p.x+2, p.y) == null) {
				options.add(new Point(p.x+2,p.y));
			}
			
			// Check if enemy occupies diagonal, if so, valid move to kill piece
			if(chessBoard.getPiece(p.x+1, p.y-1) != null && chessBoard.getPiece(p.x+1, p.y-1).getColour() != this.colour) {
				options.add(new Point(p.x+1,p.y-1));
			}
			if(chessBoard.getPiece(p.x+1, p.y+1) != null && chessBoard.getPiece(p.x+1, p.y+1).getColour() != this.colour) {
				options.add(new Point(p.x+1,p.y+1));
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
			return "\u2659";
		}
		return "\u265F";
	}
	
	
}
