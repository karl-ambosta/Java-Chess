package game;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pieces.*;
import pieces.Piece.Colour;
import pieces.Piece.Type;

public class Board implements Serializable, Cloneable {
	
	private Piece[][] board;
	private int rows;
	private int cols;
	private Board previousState = null;
    private Colour turn;
    private List<Piece> pieces = new ArrayList<Piece>();
    private Ai ai;
    private Piece inCheck = null;
    private Piece lastMoved = null;
	
	public Board() {
		this.setRows(8);
		this.setCols(8);
	}
	
	private Board(Colour turn, Board previousState, List<Piece> pieces,
            Piece lastMoved, Piece inCheck, Ai ai) {
        this.turn = turn;
        if (inCheck != null) {
            this.inCheck = inCheck.clone();
        }
        if (lastMoved != null)
            this.lastMoved = lastMoved.clone();
        this.ai = ai;
        this.previousState = previousState;
        for(Piece p : pieces) {
            this.pieces.add(p.clone());
        }
    }
	
	public void initialize() {
		board = new Piece[8][8];
		
		// Add White Pieces
		
		addPiece(new King(new Point(7,4), Colour.WHITE));
		addPiece(new Queen(new Point(7,3), Colour.WHITE));
		addPiece(new Rook(new Point(7,0), Colour.WHITE));
		addPiece(new Rook(new Point(7,7), Colour.WHITE));
		addPiece(new Knight(new Point(7,1), Colour.WHITE));
		addPiece(new Knight(new Point(7,6), Colour.WHITE));
		addPiece(new Bishop(new Point(7,2), Colour.WHITE));
		addPiece(new Bishop(new Point(7,5), Colour.WHITE));
		
		for(int i = 0; i < 8; i++) {
			addPiece(new Pawn(new Point(6,i), Colour.WHITE, false));
		}
		
		// Add Black Pieces		
		addPiece(new King(new Point(0,4), Colour.BLACK));
		addPiece(new Queen(new Point(0,3), Colour.BLACK));
		addPiece(new Rook(new Point(0,0), Colour.BLACK));
		addPiece(new Rook(new Point(0,7), Colour.BLACK));
		addPiece(new Knight(new Point(0,1), Colour.BLACK));
		addPiece(new Knight(new Point(0,6), Colour.BLACK));
		addPiece(new Bishop(new Point(0,2), Colour.BLACK));
		addPiece(new Bishop(new Point(0,5), Colour.BLACK));
		
		for(int i = 0; i < 8; i++) {
			addPiece(new Pawn(new Point(1,i), Colour.BLACK, false));
		}
		
	}
	
	public Piece getPiece(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return this.board[x][y];
	}
	
	public void move(Point start, Point dest) {
		Piece p = getPiece(start.x, start.y);
		List<Point> pieceOptions = p.getOptions(this);
		
		if(p instanceof Pawn) {
			Pawn pw = (Pawn) p;
			pw.setMoved(true);
		}
		for(Point point : pieceOptions) {
			if(!checkPoint(p.getColour(), point)) {
				pieceOptions.remove(point);
			}
		}
		
		if(pieceOptions.contains(dest)) {
			p.movePiece(dest);
			this.board[start.x][start.y] = null;
			this.board[dest.x][dest.y] = p;
			String s = Character.toString((char)(start.x+65)) + (start.y+1);
			String d = Character.toString((char)(dest.x+65)) + (dest.y+1);
			System.out.println("Moved " + p.getType() + ": " + s + " -> " + d);
		} else {
			System.out.println("Invalid Move for " + p.getType());
		}
	}
	
	public boolean checkPoint(Colour c, Point p) {
		if(this.getPiece(p.x, p.y) != null &&
				this.getPiece(p.x, p.y).colour == c) {
			return false;
		}
		
		return true;
	}
	
	public boolean isValidGrid(Point p) {
		if(p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8) {
			return true;
		}
		return false;
	}
	
	public boolean movePiece(Move m) {
		this.previousState = this.clone();
		if(m.getCaptured() != null) {
			this.removePiece(m.getPosition());
		}
		
		if(m.getPiece() instanceof Pawn) {
			Pawn p = (Pawn)m.getPiece();
			p.setMoved(true);
		}
		
		m.getPiece().setPosition(m.getPosition()); 
		/*
		if(pieceOptions.contains(dest)) {
			p.movePiece(dest);
			this.board[start.x][start.y] = null;
			this.board[dest.x][dest.y] = p;
			String s = Character.toString((char)(start.x+65)) + (start.y+1);
			String d = Character.toString((char)(dest.x+65)) + (dest.y+1);
			System.out.println("Moved " + p.getType() + ": " + s + " -> " + d);
		} else {
			System.out.println("Invalid Move for " + p.getType());
		}
		*/
		
		return true;
	}
	
	public boolean ifMovePutsKingInCheck(Move m, Colour c) {
		Board boardCopy = checkMove(m);
		
		for(Piece p : boardCopy.getPieces(c)) {
			System.out.println(p.getColour() + " " + p.getType());
			if(p.getColour() == c) {
				for(Move mv : p.getValidMoves(boardCopy, false))
                    // if a move would result in the capture of a king
                    if (mv.getCaptured() instanceof King) 
                        return true;
			}
		}
		
		return false;	
	}
	
	private Board checkMove(Move m) {
		Board boardCopy = this.clone();
		
		if(m.getCaptured() != null) {
			Point pc = m.getCaptured().getPosition();
			Piece capture = boardCopy.getPiece(pc.x, pc.y);

			Point pm = m.getCaptured().getPosition();
            Piece moving = boardCopy.getPiece(pm.x, pm.y);

            // performs the move on the copied board
            boardCopy.movePiece(new Move(moving, m.getPosition(), capture));
		}
		return boardCopy;
	}

	public List<Piece> getPieces(Colour c) {
		return pieces.stream().filter(p -> p.getColour() == c).collect(Collectors.toList());
	}
	
	public void addPiece(Piece p) {
		Point pos = p.getPosition();
		this.board[pos.x][pos.y] = p;
	}
	
	public void removePiece(Point p) {
		this.board[p.x][p.y] = null;
		// pieces.remove(p);
	}
	
	public void showPossibleMoves(int row, int col) {
		Piece p = this.getPiece(row, col);
		if(p == null) {
			System.out.println("Tile is empty!");
			return;
		}
		List<Move> options = p.getValidMoves(this, false);
		System.out.println("Showing moves for " + p.getType() + " : ");
		if(options.isEmpty()) {
			System.out.println("No Valid Moves for Pawn " + Character.toString((char)(row+65)) + (col+1));
		}
		for(Move m : options) {
			Point pt = m.getPosition();
			System.out.println("[" + Character.toString((char)(pt.x+65)) + (pt.y+1) + "]");
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public String toString(){
	    String chess = "";
	    
	    chess += "\t (1) (2) (3) (4) (5) (6) (7) (8)\n";
	    for (int row = 0; row < 8; row++) {
	        chess += "\t---------------------------------\n";
	        chess += "(" + Character.toString((char)(row+65)) +")\t";

	        for (int column = 0; column < 8; column++) {
	        	
	        	if(getPiece(row,column) != null) {
	        		chess += "| " + getPiece(row,column).toString() + " ";
	        	} else {
	        		chess += "| " + " " + " ";
	        	}
	        }       
	        chess += "|\n";
	    }
	      chess += "\t---------------------------------";
	    
	    return chess;
	}
	
	@Override
	public Board clone() {
		return new Board(turn, previousState, pieces, lastMoved, inCheck, ai);
	}
}
