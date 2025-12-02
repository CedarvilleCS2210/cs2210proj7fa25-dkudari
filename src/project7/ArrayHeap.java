package project7;

import java.util.Random;

/**
 * Title:        Project #7
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ArrayHeap extends ArrayBinaryTree implements Heap {

    Comparator heapComp;

    public ArrayHeap(Comparator newComp) {
        this (newComp, DEFAULT_SIZE);
    }

    public ArrayHeap(Comparator newComp, int newSize) {
        super (newSize);
        heapComp = newComp;
    }

    /*
    * This function swaps two elements to maintain the heap ordering property
    * param: the two ArrayPosition to swap
     */
    private void swapElements (ArrayPosition pos1, ArrayPosition pos2){
        int temp = pos1.getIndex();
        int temp2 = pos2.getIndex();
        pos1.setIndex(temp2);
        pos2.setIndex(temp);
        btArray[temp2] = pos1;
        btArray[temp] = pos2;
    }

    /*
    * A helper function to acess the element part of an item
    * param: the element of the btarray
    * returns: the element part of an item 
     */
    private Object getItemValue (Object element) {
        if (element instanceof ArrayPosition pos) {
            Item item = (Item)pos.element();
            return item.element();
        }
        return null;
    }

    /*
    * A helper function that checks if the position is lesser than its parent and then calls the swap method to fix the ordering
    * param: the element of the btarray that needs to be bubbled up
     */
    private void bubbleUp (ArrayPosition pos) {
        while (!isRoot(pos)) {
            ArrayPosition parent = (ArrayPosition)parent(pos);
            if (parent != null && parent.element() != null && this.heapComp.isLessThan(getItemValue(pos), getItemValue(parent))) {
                swapElements (pos, parent);
            } else {
                break;
            }
        }
    }

    /*
    * This method adds a new element into the array and calls bubble up to make sure that the heap ordering property is followed.
    * The array also resizes itself when it reaches capacity.
    * param: the key and the element of the new ArrayPosition to add into the array
    * throws: InvalidObjectException if objects are not comparable
     */
    public void add(Object newKey, Object newElement) throws InvalidObjectException {
        if (!this.heapComp.isComparable(newKey) || !this.heapComp.isComparable(newElement)) {
            throw new InvalidObjectException("Elements cannot be compared. Please enter a valid element");
        }
        if (size + 1 > btArray.length) {
            ArrayPosition[] newArray = new ArrayPosition[size * 2];
            System.arraycopy(btArray, 0, newArray, 0, size);
            btArray = newArray;
        }
        ArrayPosition pos = new ArrayPosition(size, new Item (newKey, newElement));
        btArray[size] = pos;
        ++ size;
        bubbleUp (pos);
    }

    /* Method for testing
    public void printHeap() {
        for (int i = 0, j = size; i < j; i++) {
            System.out.println(btArray[i]);
        }
    }*/
     
    /*
    * A helper function that checks if the position is greater than its parent and then calls the swap method to fix the ordering
    * param: the element of the btarray that needs to be bubbled down
     */
    private void bubbleDown (ArrayPosition pos) {
        while (pos != null) {
            ArrayPosition lchild = (ArrayPosition)leftChild(pos);
            ArrayPosition rchild = (ArrayPosition)rightChild(pos);

            ArrayPosition smallest = pos;
            if (lchild != null && this.heapComp.isGreaterThan(getItemValue(smallest), getItemValue(lchild))) {
                smallest = lchild;
            }
            if (rchild != null && this.heapComp.isGreaterThan(getItemValue(smallest), getItemValue(rchild))) {
                smallest = rchild;
            }
            if (this.heapComp.isEqual(getItemValue(pos), getItemValue(smallest))) {
                pos = null;
            } else {
                swapElements (pos, smallest);
            }
        }
    }

    /*
    * This function removes the root of the tree and returns that element. It also calles bubble up to repair the heap ordering property.
    * returns: the object that was removed
     */
    public Object removeRoot() throws EmptyHeapException {
        if (size() == 0) {
            throw new EmptyHeapException("Cannot remove from an empty array");
        }
        ArrayPosition root = btArray[ROOT];
        swapElements(root, btArray[size-1]);
        btArray[size-1] = null;
        size--;
        if (size != ROOT)
            bubbleDown(btArray[ROOT]);
        return root.element();

    }

    /*
    * The main method of the class. Runs tests to make sure the heap is working property.
     */
    public static void main (String[] args) {
	    Comparator myComp = new IntegerComparator();
        Heap myHeap = new ArrayHeap (myComp, 8);

        myHeap.add(new Integer(14),new Integer(14));
        myHeap.add(new Integer(17),new Integer(17));
        myHeap.add(new Integer(3),new Integer(3));
        myHeap.add(new Integer(2),new Integer(21));
        myHeap.add(new Integer(8),new Integer(8));
        myHeap.add(new Integer(7),new Integer(18));
        myHeap.add(new Integer(1),new Integer(1));
        myHeap.add(new Integer(19),new Integer(11));
        myHeap.add(new Integer(17),new Integer(17));
        myHeap.add(new Integer(25),new Integer(6));

        
        while (!myHeap.isEmpty()) {
            Item removedItem = (Item) myHeap.removeRoot();
            System.out.print("Key:   " + removedItem.key() + "     ");
            System.out.println("Removed " + removedItem.element());
        }
        System.out.println("All nodes removed");


       // This code adds 10000 elements into the heap and them removes them
       Random ran = new Random(100);
        for (int i = 0; i < 10000; i++) {
            myHeap.add(ran.nextInt(100000),ran.nextInt(10000));
        }

        while (!myHeap.isEmpty()) {
            Item removedItem = (Item) myHeap.removeRoot();
            System.out.print("Key:   " + removedItem.key() + "     ");
            System.out.println("Removed " + removedItem.element());

        }
        System.out.println("All nodes removed");
    }
}
