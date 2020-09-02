import java.util.*;

/**
 * An arraylist
 */
public class ArrayList implements CP2List
{
    //instance
    private int[] list;
    private int size;
    
    //constructor
    public ArrayList()
    {
        list=new int[10];
        size=0;
    }
    
    //methods
    
    /** 
     * A function to add a value to the end of an array.
     * 
     * @param value the value to be added
     */
    public void add(int value)
    {
        if(size==list.length)upsize();
        list[size]=value;
        size++;
    }
    
    /**
     * A function to insert a value at a specified index.
     * 
     * @param index the index used for insertion
     * @param value the value to be inserted
     */
    public void add(int index, int value)
    {
		int i;
		int l=size+1;
        int[] newArray = new int[l];
        System.out.println(l);
        for (i=0;i<index;i++) {
			newArray[i]=list[i];
		}
		System.out.println("Size= " + l);
		newArray[index]=value;
		int j=i;
		for (int k=i;k<l;k++) {
			newArray[k]=list[j];
			j++;
		}
		list=newArray;
    }
    
    /**
     * A function that doubles the size of an array.
     * 
     * 
     */
    private void upsize()
    {
        int[] newList=new int[list.length*2];
        for(int i=0;i<list.length;i++) newList[i]=list[i];
        list=newList;
    }
    
    /**
     * Gets a value at the specified index.
     * 
     * @param index the index to get the value at
     * @return the value at the index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public int get(int index)
    {
        if(index<0 || index>=size)throw new IndexOutOfBoundsException();
        return list[index];
    }
    
    public String toString()
    {
        if(size<100)
            return Arrays.toString(Arrays.copyOfRange(list, 0, size));
        else
        {
            String output="[";
            for(int i=0;i<5;i++)output+=list[i]+", ";
            output+="...";
            for(int i=size-5;i<size;i++)output+=", "+list[i];
            output+="]";
            return output;
        }
    }
    


	public void addAll(CP2List list){throw new UnsupportedOperationException();}
	public void addAll(int index,CP2List list){throw new  UnsupportedOperationException();}
	
	
	/**
	 * Clears a given array by setting size to zero.
	 * 
	 */
    public void clear()
    {
        size=0;
    }
    
    
    /**
     * Determines if an array contains a value.
     * 
     * @param value The value that is searched for in the array.
     * @return If the value is found in the array.
     */
	public boolean contains(int value){
		return indexOf(value)!=-1;
	}
	
	/**
	 * Determines if an array contains all the values in another array.
	 * 
	 * @param list The list of values that are being looked for in the array.
	 * @return If the list of values are found in the array.
	 */
	public boolean containsAll(CP2List list){
		for(int i=0;i<list.size();i++) {
			int v=list.get(i);
			if(!this.contains(v))return false;
		}
		return true;
	}
	
	public boolean equals(CP2List list){
		throw new  UnsupportedOperationException();
	}
	
	/**
	 * Find the index of the first occurance of the value.
	 * 
	 * @param value The value you are looking for.
	 * @return The index of the first occurance. Returns -1 if nothing is found.
	 */
	public int indexOf(int value){
		//size!=list.length
		for(int i=0;i<size();i++) {
			if(list[i]==value)return i;
		}
		return -1;
	}
	
	/**
	 * Determines if a list is empty.
	 * 
	 * @return If the list is empty.
	 */
	public boolean isEmpty(){
		if(size==0) {
			return true;
		}
		else {
			return false;
		}
	}
	public int lastIndexOf(int value){throw new  UnsupportedOperationException();}
	
	public int delete(int index){
		// start at deleted index, move value from left to right, rinse and repeat. At end, do size--.
		// loop from index to end
		// {
		// shift the value from the right onto the current index
		// }
		// size--
		return 7;
	}
	
	public boolean remove(int value){
		int index=indexOf(value);
		if(index>=0)delete(index);
		return true;
	}
	
	public void removeRange(int fromIndex,int toIndex){throw new  UnsupportedOperationException();}
	
    
    /**
     * Reports the size of the list.
     * 
     * @return the size of the list.
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Sets the current place at the index in the given list to the new value.
     * 
     * @return The 
     * 
     */
	public int set(int index, int value){
		int old=list[index];
		list[index]=value;
		return old;
	}
	
	public CP2List subList(int fromIndex,int toIndex){throw new  UnsupportedOperationException();}
	public int[] toArray(){throw new  UnsupportedOperationException();}
}
