import java.util.*;

public class SlidingSolver {
	
	//We will only look for solutions at most 20 moves away
	private static final int MAX_DISTANCE = 20;
	
	//Priority Queue stores the puzzle space: boards under consideration. Those with lowest heuristic will be analyzed.
	PriorityQueue<TileBoard> puzzleSpace;
	
	//Priority Queue's comparator. It will use evaluateHeuristic()
	Comparator<TileBoard> comparator;
	
	//to initialize the puzzle space
	TileBoard firstBoard;
	
	/*
	 * Constructs a SlidingSolver with the given input board.
	 */
	public SlidingSolver(String initialBoard) {
		firstBoard = new TileBoard(initialBoard);
		comparator = new TileBoardComparator();
		puzzleSpace = new PriorityQueue<TileBoard>(362880, comparator); //a priority queue of size 9! - the maximum unique board states possible
		puzzleSpace.add(firstBoard);
	}

	/*
	 * Solves the puzzle by performing an A* search over the puzzle space.
	 * Note that the priority queue will, in the case of duplicates, drop and replace boards with lower heuristics 
	 */
	public SlidingSolution solvePuzzleAStar() {
		
		//spaceSize = # of boards added to the priority queue
		int spaceSize = 0;
		
		//distance = # of moves to get to a board from the initial state
		int distance = 0;
		
		//We need a HashMap to keep track of the puzzle space and avoid double-queueing boards
		HashMap<String, TileBoard> queuedBoards = new HashMap<String, TileBoard>(362880); //same size as priority queue
		
		while (!puzzleSpace.isEmpty()) { //until the priority queue is empty
			TileBoard top = puzzleSpace.remove(); //analyze the board with the lowest heuristic
			distance = TileBoard.getmyMoves(top).length(); 
			
			//If the top board IS a solution, and has reasonable distance, return it!
			if (top.myBoard.equals(TileBoard.goalBoard) && distance<=MAX_DISTANCE) { 
				SlidingSolution solution = new SlidingSolution(TileBoard.getmyMoves(top), spaceSize);
				return(solution);
			}
			
			//Otherwise, look at the next possible boards moving from 'top'
			for (TileBoard i : TileBoard.getNextBoards(top)) {
				if (!queuedBoards.containsKey(i.myBoard)) { //if a nextBoard isn't already queued (check hash map for faster run-time)
					queuedBoards.put((i.myBoard), i); //then add it to the hash map
					puzzleSpace.add(i); //and queue it
					spaceSize++; //and increase our space size to count every board now under consideration
				}
				
				//BUT if nextBoard IS already queued, compare their heuristics and take the lowest one
				if (queuedBoards.containsKey(i.myBoard)) {
					if (evaluateHeuristic(i) < evaluateHeuristic((queuedBoards.get(i.myBoard)))) {
						queuedBoards.put(i.myBoard, i); //replace it in the hash map
						puzzleSpace.remove(queuedBoards.get(i.myBoard));
						puzzleSpace.add(i); //replace it in the queue
					}
				}
			}//END forloop
		}//END while
		
		//but if no solution had reasonable distance from the starting board, return no solution.
		if (distance > MAX_DISTANCE) return (SlidingSolution.NO_SOLUTION);
		return SlidingSolution.NO_RESPONSE;
	}

	/*
	 * Evaluates the given board.
	 */
	private int evaluateHeuristic(TileBoard p) {
		return TileBoard.getNumMoves(p) + TileBoard.calcManhattanDistance(p);
	}
	
	/* 
	 * For use by our Priority queue; comparator will be implemented by using evaluateHeuristic()
	 */
	class TileBoardComparator implements Comparator<TileBoard> {
		public int compare(TileBoard a, TileBoard b) {
			if (evaluateHeuristic(a)<evaluateHeuristic(b)) return (-1);
			if (evaluateHeuristic(a)>evaluateHeuristic(b)) return(1);
			return 0;
		}
	}//end class TileBoardComparator
}//end class SlidingSolver