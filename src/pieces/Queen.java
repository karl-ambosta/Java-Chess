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
	public Piece clone() {
        return new Queen(new Point(this.position.x, this.position.y),
                this.colour);
    }

	@Override
	public String toString() {
		if(this.colour == Colour.WHITE) {
			return "\u2655";
		}
		return "\u265B";
	}

	
}
