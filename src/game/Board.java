package game;

import java.awt.Point;
import java.util.Set;

import pieces.*;
import pieces.Piece.Colour;

public class Board {
	
	private Piece[][] board;
	private int rows;
	private int cols;
	
	public Board() {
		this.rows = 8;
		this.cols = 8;
	}
	
	public void initialize() {
		board = new Piece[8][8];
		
		// Add White Pieces
		addPiece(new King(this, new Player(), new Point(7,4), Colour.WHITE));
		addPiece(new Queen(this, new Player(), new Point(7,3), Colour.WHITE));
		addPiece(new Rook(this, new Player(), new Point(7,0), Colour.WHITE));
		addPiece(new Rook(this, new Player(), new Point(7,7), Colour.WHITE));
		addPiece(new Knight(this, new Player(), new Point(7,1), Colour.WHITE));
		addPiece(new Knight(this, new Player(), new Point(7,6), Colour.WHITE));
		addPiece(new Bishop(this, new Player(), new Point(7,2), Colour.WHITE));
		addPiece(new Bishop(this, new Player(), new Point(7,5), Colour.WHITE));
		
		for(int i = 0; i < 8; i++) {
			addPiece(new Pawn(this, new Player(), new Point(6,i), Colour.WHITE));
		}
		
		// Add Black Pieces		
		addPiece(new King(this, new Player(), new Point(0,4), Colour.BLACK));
		addPiece(new Queen(this, new Player(), new Point(0,3), Colour.BLACK));
		addPiece(new Rook(this, new Player(), new Point(0,0), Colour.BLACK));
		addPiece(new Rook(this, new Player(), new Point(0,7), Colour.BLACK));
		addPiece(new Knight(this, new Player(), new Point(0,1), Colour.BLACK));
		addPiece(new Knight(this, new Player(), new Point(0,6), Colour.BLACK));
		addPiece(new Bishop(this, new Player(), new Point(0,2), Colour.BLACK));
		addPiece(new Bishop(this, new Player(), new Point(0,5), Colour.BLACK));
		
		for(int i = 0; i < 8; i++) {
			addPiece(new Pawn(this, new Player(), new Point(1,i), Colour.BLACK));
		}
	}
	
	public Piece getPiece(int x, int y) {
		return this.board[x][y];
	}
	
	public void move(Point start, Point dest) {
		Piece p = getPiece(start.x, start.y);
		
		
		if(p.movePiece(dest)) {
			this.board[dest.x][dest.y] = p;
			this.board[start.x][start.y] = null;
		} else {
			System.out.println("Invalid Move for " + p.getType());
		}
	}
	
	public void addPiece(Piece p) {
		Point pos = p.getPosition();
		this.board[pos.x][pos.y] = p;
	}
	
	public void removePiece(int row, int col) {
		this.board[row][col] = null;
	}
	
	public void showPossibleMoves(int row, int col) {
		Piece p = this.getPiece(row, col);
		Set<Point> options = p.showOptions(this);
		System.out.println("Showing moves for " + p.getType() + " : ");
		if(options.isEmpty()) {
			System.out.println("No Valid Moves for Pawn " + Character.toString((char)(row+65)) + (col+1));
		}
		for(Point pos : options) {
			System.out.println("[" + Character.toString((char)(pos.x+65)) + (pos.y+1) + "]");
		}
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

}
