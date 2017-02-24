
import java.util.*;

public class ArrayList {

    protected Integer[] elementData; // list of values
    protected int size;        // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 100;

    // post: constructs an empty list of default capacity
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // pre : capacity >= 0 (throws IllegalArgumentException if not)
    // post: constructs an empty list with the given capacity
    //    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = new Integer[capacity];
        size = 0;
    }

    // returns: the current number of elements in the list
    public int size() {
        return size;
    }

    // param: int index into the arraylist
    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // returns: the value at the given index in the list
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // returns: a comma-separated, bracketed string version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + this.get(0);
            for (int i = 1; i < size; i++) {
                result += ", " + this.get(i);
            }
            result += "]";
            return result;
        }
    }

    // param:   integer value to find in arraylist
    // returns: the position of the first occurrence of the given
    //        value (-1 if not found)
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // returns: true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // param: integer value 
    // returns: true if the given value is contained in the list,
    //       false otherwise
    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    // param: integer value 
    // post: appends the given value to the end of the list
    public void add(int value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // param: int index position in arraylist
    // param: int value 
    // pre : 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    //       values right
    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // param: integer index into arraylist
    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // param: integer index into arraylist
    // param: integer value 
    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the value at the given index with the given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: list is empty
    public void clear() {
        size = 0;
    }

    // param: an arraylist
    // post: appends all values in the parameter list to the end of this list
    //       parameter list is unchanged
    public void addAll(ArrayList other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // param: int an arraylist capacity
    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // param: int an index into the arraylist
    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }


}
