/*
 * 1
 * 11
 * 121
 * 1331
 * 14641
 */


public class Pascal {
	
	public static int pascalTriangle(int m, int n) {
		
		int patrick = 0;
		
		if (m==0 || m==1 || n==0 || n>=m) {
			patrick = 1;
		}
		else {
			patrick = pascalTriangle(m-1, n-1) + pascalTriangle(m-1,n);
		}
		return (patrick);
	}
	
	public static void main(String[] args){ 
		
		int n = 100; //order of the triangle
		int pT[][] = new int[n+1][n+1]; //columns 0,1,...,(m=n)   rows 0,1,...,n  --> an mxn square matrix
		
		for (int i = 0; i<=n; i++) { //loop rows from 0 to n
			for (int j=0; j<=i; j++) { //for every row, print same number of columns (to make a triangle)
				pT[i][j] = pascalTriangle(i,j); //modify each entry from 0 to its entry in pascal's triangle
				System.out.print("i, j: (" + i + ", " + j + ") " + pT[i][j] + " "); //print the ith row
			}
			System.out.println(); //next line, increment i
		}
		
		System.out.println();
	}

}
