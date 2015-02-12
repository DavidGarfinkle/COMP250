public class SlidingSolverDriver
{

	public static void main(String[] args)
	{
		String puzzle = "123405786";
		SlidingSolution solution = new SlidingSolver(puzzle).solvePuzzleAStar();
		System.out.println(solution.getMoves());
	}

}

