// Helpfer functionality
// Do not modify this class!

public class SlidingSolution
{
	public static final SlidingSolution NO_RESPONSE = new SlidingSolution("", -123456);
	public static final SlidingSolution NO_SOLUTION = new SlidingSolution("", -789);

	private String myMoves;
	private int myProblemSpaceSize;

	public SlidingSolution(String moves, int spaceSize)
	{
		myMoves = moves;
		myProblemSpaceSize = spaceSize;
	}

	public String getMoves()
	{
		return myMoves;
	}

	public int getProblemSpaceSize()
	{
		return myProblemSpaceSize;
	}

	public String toString()
	{
		String toStr = "Move the empty space in the following directions:\n\n";
		for(char move : myMoves.toCharArray())
		{
			switch(move)
			{
				case 'U' :
					toStr += "UP\n";
					break;
				case 'R' :
					toStr += "RIGHT\n";
				       	break;
				case 'D' :
					toStr += "DOWN\n";
				       	break;
				case 'L' :
					toStr += "LEFT\n";
				       	break;
			}
		}
		return toStr;
	}
}#

