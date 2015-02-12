package Sorting;

public class quickSort {

    public static <T extends Comparable<T>> void sort(T[] a) {
    	quicksort(a, 0, a.length-1);
    }
    
    private static <T extends Comparable<T>> void quicksort (T[] a, int p, int r) {
    	if (p<r) return;
    	int q = partition(a,p,r);
    	quicksort(a, p, q);
    	quicksort(a, q+1, r);
    }
    
   /* private static <T extends Comparable<T>> int median(T[] a, int p, int r) { //to find the median of the first, last, and middle elements of a
    	
    	int mid = (r-p)/2;
    	
    	
    	
    }*/
    
    private static <T extends Comparable<T>> int partition(T[] a, int p, int r) {
    	
    	T x = a[0];
    	int i = p-1; //left-hand index, offset by 1 to account for while loop's i++
    	int j = r+1; //right-hand index, offset by 1 to account for while loop's j--
    	
    	while (i<=r || a[i].compareTo(x)<0) {
    		i++;
    		if (i<j) swap(a, i, j);
    		else return(j);
    	}
    	while (j>=p || a[j].compareTo(x)>0) {
    		j = j-1;
    		if (i<j) swap(a,i,j);
    		else return(j);
    	}
    	return(j);
 	}
    
    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
   
    	T swapee = a[i];
    	a[i] = a[j];
    	a[j] = swapee;
   
    }
    
}
