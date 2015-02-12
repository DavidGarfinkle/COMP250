import java.util.*;

public class StacksandQueues {
	
	public static void reverseQueue(Queue<Object> Qoripher) { //Runtime: O(n) where n is the number of elements in our queue
	
		Stack Samantha = new Stack(); //make a stack for easy data transfer. let's make it a boy-girl relationship: Samantha & Qoripher
	
		while(!Qoripher.isEmpty()) //until Qoripher is empty, we're gonna take the first in queue and put it in our stack
			Samantha.push(Qoripher.remove()); //first in queue drops to bottom of stack, second in queue drops to second bottom of stack, etc.
		
		while(!Samantha.isEmpty()) //then we empty Samantha into Qoripher
			Qoripher.add(Samantha.pop()); //effectively reversing the order of the elements in Qoripher
		
	}
	
	public static void copyStack(Stack Peter) { //Runtime: O(n^2)
		
		Queue<Object> Lorry = new LinkedList<Object>(); //queue for data transfer
		Stack Petwo = new Stack(); //our copied Stack!
		Object Roger = new Object(); //for two copies
		
		while (!Peter.isEmpty())  //until our stack to be copied is empty,
			Lorry.add(Peter.pop()); //pop each element into a queue
		
		reverseQueue(Lorry); //first on the stack becomes first on the queue, and first to leave the queue! so we reverse the queue
		
		while (!Lorry.isEmpty()) { //now, until our queue is empty
			Roger = Lorry.remove(); //save each element of queue
			Peter.push(Roger); //put it in the first stack
			Petwo.push(Roger); // and put it in the second! for a copy.
		}
	}
}