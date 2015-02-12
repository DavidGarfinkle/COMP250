
public class Recursion {

	//I wrote out the answers for n=1,2,3,4 and saw the fibonacci sequence.
	//Accordingly, countBinaryStrings will recursively generate the fibonacci sequence's nth term
	//Except the base cases are different, as a binary string of length 1 has 2 options for non-consecutive zeroes
	
	public static int countBinaryStrings (int n) {
	
		if (n == 0) {
			return (0); //base case.. string of 0 length has 0 possibilities
		} 
		else {
		if (n == 1) {
			return (2); //strings of length 1 have 2 possibilities: 0 or 1 contain no consecutive 0's
		}
		else {
		if (n == 2)
			return(3); // strings of length 2 have 3 possibilities: 01, 01, 11
		}
			return (countBinaryStrings(n-1) + countBinaryStrings(n-2)); //recursive relation
		}
	}
	
	public static void main (String[] args) {

	int n = Integer.parseInt(args[0]);
	
	n = countBinaryStrings(n);
	System.out.println(n);
	
	}
}

