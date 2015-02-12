import java.util.*;
import java.lang.Math.*;

public class TileBoard {
	
	//String representation of the solution board. Set as public to be used in solvePuzzleA*()
	public static final String goalBoard = "123456780"; 

	//String representation of a puzzle board. Set as public in order to simplify solvePuzzleA*()
	public String myBoard;

	//String representation of the list of moves that generated this board.
	private String myMoves; //Assignment came with getmyMoves() so I wasn't lazy and kept it as private.

	/*
	 * Constructs a board to a desired state s.
	 * Note that L(s) = myMoves = null, as we generated this board without any movement from a previous board.
	 * Initialize myMoves to null in order to edit the value when new boards are created by movement.
	 */
	public TileBoard(String board) {
		myBoard = board; //set the board to desired set-up
		myMoves = ""; //a newly-constructed board was not generated by moves. initialize to an empty string for further edits by getNextBoards()
	}
	
	/*
	 * Returns the number of moves from the initial board
	 */
	public static int getNumMoves(TileBoard b) {
		return b.myMoves.length();
	}
	
	/*
	 * Returns the list of moves from the initial board
	 */
	public static String getmyMoves(TileBoard b) {
		return b.myMoves;
	}

	/*
	 * Returns the state of the board in string representation
	 */
	public static String getmyBoard(TileBoard b) {
		return b.myBoard;
	}
	
	/*
	 * This method prints a nicely formatted puzzle for testing
	 */
	public void printmyBoard() {
		
		int[] intBoard = new int[myBoard.length()];
		for (int i=0; i<intBoard.length; i++)
			intBoard[i] = Character.getNumericValue(myBoard.charAt(i));
		
		System.out.println("Board: " + myMoves);
		for(int i=0; i<3; i++)
			System.out.print(intBoard[i] + " ");
		System.out.println();
		for(int i=3; i<6; i++)
			System.out.print(intBoard[i] + " ");
		System.out.println();
		for(int i=6; i<9; i++)
			System.out.print(intBoard[i] + " ");
		System.out.println();
	}
	
	/*
	 * This method takes in a character (U,D,L, or R) and a current board state, and then returns the new moved board.
	 */
	public static TileBoard moveBoard (char myMove, TileBoard board) {
		
		//To find the zero (empty block) within the tile board
		int zeroIndex=-1;
		
		//Moving elements will happen inside a charArray representation of the tile board
		char[] boardChar = board.myBoard.toCharArray();
		
		//What is to be returned:
		TileBoard movedBoard = new TileBoard(board.myBoard);
	
		//Prepare to add the current move to myMoves string
		char[] movesChar = board.myMoves.toCharArray();
		char[] newMovesChar = new char[movesChar.length+1]; //we need one extra space
		for(int i=0; i<movesChar.length; i++)
			newMovesChar[i] = movesChar[i];
				
		//Find the 0 block in the current board
		for (int i = 0; i<boardChar.length; i++)
			if (boardChar[i] == '0') zeroIndex = i;
		
		if (zeroIndex == -1) return (board); //if zeroIndex hasn't moved, then there is no zero to move! don't make any changes.
		
		switch(myMove) {
			case 'U' : //UP
				if (zeroIndex == 0 || zeroIndex == 1 || zeroIndex == 2) return(board);//if zero is already in the top row, do nothing.
				swap(boardChar, zeroIndex, zeroIndex-3);
				newMovesChar[movesChar.length] = 'U'; //insert at the end of myMoves string
				break;
			case 'D' : //DOWN
				if (zeroIndex == 6 || zeroIndex == 7 || zeroIndex == 8) return(board);//if zero is already in the bottom row, return original.
			    swap(boardChar, zeroIndex, zeroIndex+3);  
			    newMovesChar[movesChar.length] = 'D';
				break;
			case 'R' : //RIGHT
				if (zeroIndex == 2 || zeroIndex == 5 || zeroIndex == 8) return(board);//if zero is already in the right column, return an unmodified board.
			    swap(boardChar, zeroIndex, zeroIndex+1);   
			    newMovesChar[movesChar.length] = 'R';
				break;
			case 'L' : //LEFT
				if (zeroIndex == 0 || zeroIndex == 3 || zeroIndex == 6) return(board); //if zero is already in the left column, return an unmodified board.
			    swap(boardChar, zeroIndex, zeroIndex-1);   
			    newMovesChar[movesChar.length] = 'L';
				break;
		}
		
		String newBoard = new String(boardChar);
		String newMoves = new String(newMovesChar);
		movedBoard.myBoard = newBoard;
		movedBoard.myMoves = newMoves;
		return(movedBoard);
	}
	//This method helps moveBoard() by providing a function for swapping two characters in a char array
	public static void swap (char[] c, int left, int right) {
		char temp = c[left];
		c[left] = c[right];
		c[right] = temp;
	}
	

	/*
	 * Returns a list of boards that are one move away.  This list *DOES NOT* contain the
	 * previous board, as this would undo a moving we just made
	 */
	public static LinkedList<TileBoard> getNextBoards(TileBoard b) {
		
		char[] possibleMoves = {'U', 'D', 'R', 'L'};
		char last = 'a';
		
		//The linked list to hold all of the next possible boards; to be returned
		LinkedList<TileBoard> nextBoards = new LinkedList<TileBoard>();
		
		//If this is not the first move, store the opposite of the last move done in order to avoid backtracking
		if (b.myMoves.length() != 0)
			last = b.myMoves.charAt(b.myMoves.length()-1);
				
		//Add each possible move to the list. We avoid backtracking by only adding movements not opposite to the last
		for(char c : possibleMoves) 
			if (c != opposite(last))
				nextBoards.add(moveBoard(c, b));
		
		return (nextBoards);
	}
	//Helper method for getNextBoards. Returns the opposite move of a given direction
	public static char opposite(char current){
		
		char opposite = current;
		
		switch (current) {
			case 'U' : opposite = 'D'; break;
			case 'D' : opposite = 'U'; break;
			case 'R' : opposite = 'L'; break;
			case 'L' : opposite = 'R'; break;
		}
		return (opposite);
	}


	/*
	 * Evaluates the given board using the Manhattan distance heuristic.
	 */
	public static int calcManhattanDistance(TileBoard b) {
		
		int mD = 0;
		
		//Represent the board as an array of integers to facilitate calculations
		int[] intBoard = new int[b.myBoard.length()];
		for (int i=0; i<intBoard.length; i++)
			intBoard[i] = Character.getNumericValue(b.myBoard.charAt(i));
		
		for (int i=0; i<intBoard.length; i++) {
			if (intBoard[i] == 0) continue; //if we are at the 0 block, move on.
			int goal = intBoard[i]-1; //from the structure of our TileBoard string-representation, it follows that any number n's goal index is n-1
			//Our 3x3 space can be reduced to a 1 dimensional space of columns by using the modulo operator
			//or it can be reduced to a space of rows by using the division operator. We increment mD as needed.
			mD = mD + Math.abs(i/3 - goal/3) + Math.abs(i%3 - goal%3);
		}
		return (mD);
	}
}