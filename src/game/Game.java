package game;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;

import pieces.Piece;
import pieces.Piece.Colour;

public class Game {
	
	Scanner userInput = new Scanner(System.in);
	
	//private int currentPlayer;
	private Board chessBoard;
	private enum gameResult {WhiteWin, BlackWin, Draw};
	private List<Move> gameMoves;
	private Colour turnColour;
	private Player player_white;
	private Player player_black;
	
	
	public Game() {
		chessBoard = new Board();
		chessBoard.initialize();
	}
	
	private static boolean isValidGrid(String s) {
		if(s.length() != 5) {
			return false;
		}
		String[] input = s.split(" ");
		
		if(input[0].length() != 2 && input[1].length() != 2) {
			return false;
		}
		
	    if((input[0].charAt(0) >= 'A' && input[0].charAt(0) <= 'H') 
	    		&& (input[0].charAt(1) >= '0' && input[0].charAt(1) <= '8')) {
	    	if((input[1].charAt(0) >= 'A' && input[1].charAt(0) <= 'H') 
		    		&& (input[1].charAt(1) >= '0' && input[1].charAt(1) <= '8')) {
	    		return true;
	    	}
	    }
	    return false;
	}
	
	private boolean commands(Player player, String s) {
		String[] input = s.split(" ");
		
		if(s.equals("restart")) {
			System.out.println("Resetting board");
			chessBoard.initialize();
			System.out.print(chessBoard.toString() + "\n");
			turnColour = Colour.BLACK;
			return true;
		} else if(s.equals("help")) {
			showCommands();
		} else if(s.matches("([A-H]{1})([0-7]{1})(\\s){1}(\\?)")) {
			int x_coord = gridToPoint(input[0]).x;
			int y_coord = gridToPoint(input[0]).y;
			
			if(x_coord >= 0 || x_coord < 8 || y_coord >= 0 || y_coord < 8) {
				Point p = new Point(x_coord, y_coord);
				if(chessBoard.getPieceAt(p).getColour() == player.getColour()) { 
					chessBoard.showPossibleMoves(gridToPoint(input[0]).x, gridToPoint(input[0]).y);
				} else {
					System.out.println("Piece at " + pointToGrid(new Point(x_coord, y_coord)) + 
							" is not a " + player.getColour() + " piece");
				}
			} else {
				System.out.println("Invalid Tile!");
			}
			
		} else if(isValidGrid(s)) {
			Point start = gridToPoint(input[0]);
			Point dest = gridToPoint(input[1]);
			Piece moving = chessBoard.getPieceAt(start);
			Piece capture = chessBoard.getPieceAt(dest);
			
			Move m = new Move(moving, dest, capture);
			chessBoard.movePiece(m);
			System.out.print(chessBoard.toString() + "\n");
			return true;
		} else {
			System.out.println("Invalid Command");
		}
		
		return false;
	}
	
	private void showCommands() {
		System.out.println("Displaying list of commands");
		System.out.println("'restart' -> reset the chess game");
		System.out.println("'help' -> display these commands");
		System.out.println("'[GridSquare] ?' -> Get all valid moves for the Piece at specified grid square");
		System.out.println("'[GridSquare1] [GridSquare2]' -> Move Piece from Square 1 to Square 2");
	}

	private static Point gridToPoint(String s) {
		int posX = s.charAt(0) - 'A';
		int posY = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;
		return new Point(posX, posY);	
	}
	
	private static String pointToGrid(Point p) {
		String s = Character.toString((char)(p.x + 65));
		String output = s + (p.y+1);
		return output;	
	}
	
	private void addMove(Move move) {
		this.gameMoves.add(move);
	}
	
	private void changeTurn() {
		if(turnColour == null || turnColour == Colour.BLACK) {
			turnColour = Colour.WHITE;
			System.out.println(player_white.getName() + "'s turn (WHITE)");
		} else {
			turnColour = Colour.BLACK;
			System.out.println(player_black.getName() + "'s turn (BLACK)");
		}
	}
	
	private Player getActivePlayer() {
		if(turnColour == Colour.WHITE) {
			return player_white;
		} else {
			return player_black;
		}
	}
	
	public void gameLoop(){
		boolean continueGame = true;
		String input = "";
		
		System.out.println("Enter Player 1 Name (White) (if computer player, leave blank): ");
		input = userInput.nextLine();
		
		if(input.equals("")) {
			player_white = new Player("Computer",Colour.WHITE, false);
		} else {
			player_white = new Player(input, Colour.WHITE, true);
		}
		
		System.out.println("Enter Player 2 Name (Black) (if computer player, leave blank): ");
		input = userInput.nextLine();
		
		if(input.equals("")) {
			player_black = new Player("Computer", Colour.BLACK, false);
		} else {
			player_black = new Player(input, Colour.BLACK, true);
		}
		
		
		
		
		System.out.print(chessBoard.toString() + "\n");
		changeTurn();
		
		while(continueGame){
			
			System.out.print("Enter command: " );
			input = userInput.nextLine();
			/*
			Point pp = gridToPoint(input);
			System.out.println("Point = " + pp);
			Piece piece = chessBoard.getPieceAt(pp);
			List<Piece> ppp = chessBoard.getPieces(Colour.BLACK);
			
			System.out.println("Piece: " + piece);

			
			System.out.println(ppp);
			*/
			boolean result = commands(getActivePlayer(), input);
			
			if(result) {
				changeTurn();
				//System.out.println("changing player turn");
			}
			
		}
	}

}
