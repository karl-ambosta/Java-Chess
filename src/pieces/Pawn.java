package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Move;
public class Pawn extends Piece {
	
	boolean hasMoved;
	boolean isPromoted;
	
	public Pawn(Point position, Colour colour, Boolean hasMoved) {
		super(position, colour);
		this.pieceType = Type.PAWN;
		this.hasMoved = hasMoved;
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
	
	public void setMoved(boolean b) {
		this.hasMoved = b;
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

	@Override
	public List<Move> getValidMoves(Board board, boolean checkKing) {
		List<Move> options = new ArrayList<Move>();
		Point p = this.position;
		
		if(this.colour == Colour.WHITE) {
			Piece front_pc = board.getPiece(p.x-1, p.y);
			if(front_pc == null) {
				addMoveIfValid(board, options, new Point(p.x-1, p.y));
			}
			if(!hasMoved) {
				addMoveIfValid(board, options, new Point(p.x-2, p.y));
			}
			
			Piece left_pc = board.getPiece(p.x-1, p.y-1);
			Piece right_pc = board.getPiece(p.x-1, p.y+1);
			
			if(left_pc != null && left_pc.getColour() != this.colour) {
				addMoveIfValid(board, options, new Point(p.x-1, p.y-1));
			}
			
			if(right_pc != null && right_pc.getColour() != this.colour) {
				addMoveIfValid(board, options, new Point(p.x-1, p.y+1));
			}
			
		} else {
			Piece front_pc = board.getPiece(p.x+1, p.y);
			if(front_pc == null) {
				addMoveIfValid(board, options, new Point(p.x+1, p.y));
			}
			if(!hasMoved) {
				addMoveIfValid(board, options, new Point(p.x+2, p.y));
			}
			
			Piece left_pc = board.getPiece(p.x+1, p.y-1);
			Piece right_pc = board.getPiece(p.x+1, p.y+1);
			
			if(left_pc != null && left_pc.getColour() != this.colour) {
				addMoveIfValid(board, options, new Point(p.x+1, p.y-1));
			}
			
			if(right_pc != null && right_pc.getColour() != this.colour) {
				addMoveIfValid(board, options, new Point(p.x+1, p.y+1));
			}
			
		}
		
		
		
		
		return options;
	}

	@Override
	public Piece clone() {
		return new Pawn(new Point(this.position.x, this.position.y), this.colour, this.hasMoved);
	}
	
	
}
