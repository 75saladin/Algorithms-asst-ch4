import java.util.*;

public class Four1 {
    public static void main (String [] args) {
	// get the number of people and initialize from 1 to N
	int N = Integer.parseInt(args[0]);
	int [] people = new int[N];
        for (int i = 1; i <= N; i++)
	    people[i-1] = i;
	// get the number of times to pass the stone
	int M = Integer.parseInt(args[1]);
	// and pass it around
        josephus(people, M);
    }

    public static void josephus(int [] people, int M) {
        int holder = 0;
	for (int size = people.length; size>1; size--)
            holder = remove(people, M, size, holder);
	System.out.println("Winner: " + people[0]);
    }
    
    // Treats the fixed-size fixed-start array as a circular array of size size
    //starting at start. Removes the item M away and shifts everything to the 
    //right leftward.
    //  people - The array of people still in
    //  M - number of passes to find a loser
    //  size - Only indecies of people up to size will be considered in
    //  start - The removal ritual starts on this index.
    // Returns the new start. New size is to be tracked by the caller.
    public static int remove(int [] people, int M, int size, int start) {
        int outIndex = (M+start)%size;
        System.out.println(people[outIndex] + " is out");
        
        //Shift higher-indexed members left to fill the void in our hearts
        for (int i=outIndex+1; i<size; i++) //i is item to be moved left
            people[i-1] = people[i];
        
        //Stone holder just shifted into outIndex - unless outIndex is out of bounds
        return outIndex%(size-1);
    }
}
