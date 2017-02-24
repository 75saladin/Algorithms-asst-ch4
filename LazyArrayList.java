public class LazyArrayList extends ArrayList {
    boolean[] deleted;
    int ghostCount;
    
    //Extends the "ArrayList" class to perform lazy deletion, whereby deletions
    //are only marked until the array is half full of ghosts (marked, but 
    //unexecuted deletions). At that point, the deletions are flushed and the 
    //ArrayList underneath is full of only real values.
    
    public LazyArrayList(int n) {
        super(n);
        deleted = new boolean[n];
        ghostCount = 0;
    }
    
    // param: integer index into arraylist
    // pre : 0 <= index < size()
    // post: marks value for deletion, nless that delete made this list half 
    //   full of deleted items. If so, all deleted items are truly deleted,
    //   shifting survivors left.
    public void remove(int index) {
        //mark it deleted
        deleted[trueIndex(index)] = true;
        ghostCount++;
        
        if (ghostCount>=deleted.length/2) flushDel();
    }
    
    //pre: array has some contents marked deleted (half if called per usual)
    //post: array no longer contains the deleted items
    public void flushDel() {    
        int delCount = 0;
        for (int i=0; delCount<ghostCount; i++) 
            if (deleted[i]) {
                super.remove(i-delCount);
                delCount++;
            }
        
        ghostCount = 0;
        deleted = new boolean[this.size()];
    }
    
    //param: the ArrayList index to get
    // When ghosts are afoot, the caller's desired index is i undeleted items 
    // from the left of the array.
    //Returns: the index into the lazy array corresponding to the given index
    public int trueIndex(int index) {
        if (size()<=index || index<0) 
            throw new IndexOutOfBoundsException("index: " + index);
        
        //Iterate from the beginning, skipping deleted values. Once index==0, 
        //it's looking for the very next undeleted item in the array. 
        int i=0;
        for (; index>-1; i++) {
            if (!deleted[i]) index--;
        }
        
        //When index==-1, i just advanced past the spot it was looking for
        return i-1;
    }
    
    public int size() {
        return super.size()-ghostCount;
    }
    
    //deleted array must be resized on adds. Costly: forces a O(n) call to 
    //flushDel() every time. I could make it duplicate the old deleted with 
    //one extra element, but add() isn't the point of this assignment.
    public void add(int value) {
        flushDel();
        super.add(value);
        //ghostCount is 0, so this.size()==super.size()
        deleted = new boolean[this.size()];
    }
    
    //For testing
    private int trueGhostCount() {
        int count = 0;
        for (int i=0; i<deleted.length; i++) if (deleted[i]) count++;
        return count;
    }
}
