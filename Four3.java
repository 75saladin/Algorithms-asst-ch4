import java.util.*;


public class Four3 {

    //Dr. G method
    public static void main (String [] args) {
	System.out.println(Tr(Integer.parseInt(args[0])));
        System.out.println(Ts(Integer.parseInt(args[0])));
    }
  
    //Recursively solves the recurrance
    public static long Tr(long n) {
	if (n==1) return 1;
	return 2*Tr(n/2)+n;
    }
    
    //Now I simulate the recursion explicitly using a stack. I am limited to 
    //n and s as local variables.
    public static long Ts(long n) {
	Stack<Long> s = new Stack<>();
	//Simulate going deeper into the web of substitutions. (Push +Ns)
	while (n!=1) {
            s.push(n);
            n/=2;
        }
        
        
        //Now that we have gotten to n=1, simulate the cascading plug-in of 
        //T(1)=1. (Multiply the "return" by 2 and add the next pop)
        s.push(n); //or change previous while loop from 1 to 0
        while (s.size()>1) {
            //I am required by the assignment to push here. See Four3Bonus.java
            //for a cleaner version.
            n = 2*s.pop() + s.pop();
            s.push(n);            
        }
        return s.pop();
    }

}


