package game;

import java.awt.Point;
import java.util.List;

import pieces.*;
import pieces.Piece.Colour;

public class Board {
	
	private Piece[][] board;
	private int rows;
	private int cols;
	
	public Board() {
		this.setRows(8);
		this.setCols(8);
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
			addPiece(new Pawn(new Point(6,i), Colour.WHITE));
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
			addPiece(new Pawn(new Point(1,i), Colour.BLACK));
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
	
	public void addPiece(Piece p) {
		Point pos = p.getPosition();
		this.board[pos.x][pos.y] = p;
	}
	
	public void removePiece(int row, int col) {
		this.board[row][col] = null;
	}
	
	public void showPossibleMoves(int row, int col) {
		Piece p = this.getPiece(row, col);
		if(p == null) {
			System.out.println("Tile is empty!");
			return;
		}
		List<Point> options = p.getOptions(this);
		System.out.println("Showing moves for " + p.getType() + " : ");
		if(options.isEmpty()) {
			System.out.println("No Valid Moves for Pawn " + Character.toString((char)(row+65)) + (col+1));
		}
		for(Point pos : options) {
			System.out.println("[" + Character.toString((char)(pos.x+65)) + (pos.y+1) + "]");
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


}
