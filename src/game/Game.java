package game;

import java.awt.Point;
import java.util.Scanner;

import pieces.Piece;

public class Game {
	
	Scanner userInput = new Scanner(System.in);
	
	private int currentPlayer;
	private Board chessBoard;
	
	
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
	
	private void commands(String s) {
		String[] input = s.split(" ");
		
		if(s.equals("Restart")) {
			chessBoard.initialize();
		} else if(s.equals("Help")) {
			showCommands();
		} else if(s.matches("([A-H]{1})([0-7]{1})(\\s){1}(\\?)")) {
			chessBoard.showPossibleMoves(gridToPoint(input[0]).x, gridToPoint(input[0]).y);
		} else if(isValidGrid(s)) {
			chessBoard.move(gridToPoint(input[0]), gridToPoint(input[1]));
			System.out.print(chessBoard.toString() + "\n");
		} else {
			System.out.println("Invalid Command");
		}
	}
	
	private void showCommands() {
		System.out.println("Displaying list of commands");
	}

	private static Point gridToPoint(String s) {
		int posX = s.charAt(0) - 'A';
		int posY = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;
		return new Point(posX, posY);	
	}
	
	private static String pointToGrid(Point p) {
		String s = Character.toString((char)(p.x + 65));
		String output = s + p.y;
		return output;	
	}
	
	public void gameLoop(){
		boolean continueGame = true;
		String input, start, dest = "";
		System.out.print(chessBoard.toString() + "\n");
		
		while(continueGame){
			
			/*
			if (isGameOver()){
				break;
			}
			*/
			
			System.out.print("Enter command: " );
			input = userInput.nextLine();
			
			commands(input);
		}
	}

}