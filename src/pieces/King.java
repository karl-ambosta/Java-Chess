package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.Board;
import game.Move;

public class King extends Piece {
	
	boolean hasMoved;

	public King(Point position, Colour colour) {
		super(position, colour);
		this.pieceType = Type.KING;
		this.hasMoved = false;
	}

	@Override
	public List<Point> getOptions(Board chessBoard) {
		List<Point> options = new ArrayList<Point>();
		Point p = this.position;
		
		// Above
		if(chessBoard.getPiece(p.x-1, p.y) == null || chessBoard.getPiece(p.x-1, p.y).getColour() != this.colour) {
			options.add(new Point(p.x-1, p.y));
		}
		
		// Up Right
		if(chessBoard.getPiece(p.x-1, p.y+1) == null || chessBoard.getPiece(p.x-1, p.y+1).getColour() != this.colour) {
			options.add(new Point(p.x-1, p.y+1));
		}
		
		// Right
		if(chessBoard.getPiece(p.x, p.y+1) == null || chessBoard.getPiece(p.x, p.y+1).getColour() != this.colour) {
			options.add(new Point(p.x, p.y+1));
		}
		
		// Below Right
		if(chessBoard.getPiece(p.x+1, p.y+1) == null || chessBoard.getPiece(p.x+1, p.y+1).getColour() != this.colour) {
			options.add(new Point(p.x+1, p.y+1));
		}
		
		// Below
		if(chessBoard.getPiece(p.x+1, p.y) == null || chessBoard.getPiece(p.x+1, p.y).getColour() != this.colour) {
			options.add(new Point(p.x+1, p.y));
		}
		
		// Below Left
		if(chessBoard.getPiece(p.x+1, p.y-1) == null || chessBoard.getPiece(p.x+1, p.y-1).getColour() != this.colour) {
			options.add(new Point(p.x+1, p.y-1));
		}
		
		// Left
		if(chessBoard.getPiece(p.x, p.y-1) == null || chessBoard.getPiece(p.x, p.y-1).getColour() != this.colour) {
			options.add(new Point(p.x, p.y-1));
		}
		// Up Left
		if(chessBoard.getPiece(p.x-1, p.y-1) == null || chessBoard.getPiece(p.x-1, p.y-1).getColour() != this.colour) {
			options.add(new Point(p.x-1, p.y-1));
		}
		
		if(!hasMoved) {
			System.out.println("Can Castle");
		}

		
		return checkPoints(options);
	}
	
	@Override
	public List<Move> getValidMoves(Board board, boolean checkKing) {
		List<Move> options = new ArrayList<Move>();
		Point p = this.position;
		
		if(board == null) {
			return options;
		}
		return options;
		
	}

	@Override
	public Type getType() {
		return this.pieceType;
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
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
