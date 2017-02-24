import java.util.*;

//Dr. G wrote this class
public class Four2 {
    public static void main (String [] args) {
	System.out.println("Timing *ArrayList*");
	int N = Integer.parseInt(args[0]);
        ArrayList a = new ArrayList(N);

	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRemovalAtZero(a);

	a.clear();
	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRemovalAtSize(a);

	a.clear();
	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRandomRemoval(a);

	System.out.println("\nTiming *LazyArrayList*");
        a = new LazyArrayList(N);

	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRemovalAtZero(a);

	a.clear();
	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRemovalAtSize(a);

	a.clear();
	// fill with 0-N-1
        for (int i = 0; i < N; i++)
	    a.add(i);

        timeRandomRemoval(a);

    }

    public static void timeRemovalAtZero(ArrayList a) {
	System.out.println("Timing removal at 0");

	int N = a.size();

        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
	    a.remove(0); 
	long time = System.currentTimeMillis() -start;
	System.out.println("Removal Time = " + time);
	System.out.println("a = " + a);
    }

    public static void timeRemovalAtSize(ArrayList a) {
	System.out.println("Timing removal at end");

	int N = a.size();
	int rIndex = N-1;

        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
	    a.remove(rIndex--); 
	long time = System.currentTimeMillis() -start;
	System.out.println("Removal Time = " + time);
	System.out.println("a = " + a);
    }

    public static void timeRandomRemoval(ArrayList a) {
	System.out.println("Timing random removal");

	int N = a.size();

	Random rand = new Random();
	rand.setSeed(13);

        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++)
	    a.remove(rand.nextInt(a.size())); 
	long time = System.currentTimeMillis() -start;
	System.out.println("Removal Time = " + time);
	System.out.println("a = " + a);
    }




}
