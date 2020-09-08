import java.util.*;

/**
 * An arraylist with sorts.
 * 
 * @author Tristan Goodell
 */
public class ArrayList implements CP2List
{
    //instance variables
    private int[] list;
    private int size;
    private int swapCount;
    private int compareCount;
    public double time;
    private static Random r=new Random();
    
    // pseudo code
    
	// insertion sort
	
	/*
	 i ← 1
		while i < length(A)
			j ← i
			while j > 0 and A[j-1] > A[j]
				swap A[j] and A[j-1]
				j ← j - 1
			end while
			i ← i + 1
		end while
	*/
	
	public void insertionSort()
	{
		for(int i=1;i<size-1;i++)
		{
			int j=i;
			while(j>0 && list[j-1]>list[j])
			{
				swap(list[j],list[j-1]);
				System.out.println("Step: " + toString());
				j=j-1;
			}
			
		}
	}
	
	public void bubbleSort()
	{
		while(!isSorted())
		{
			for (int i=1;i<size-1;i++) 
			{
				if(list[i-1]>list[i])
				{
					swap(list[i-1],list[i]);
				}
			}
		}
	}
    
    //constructor
    public ArrayList()
    {
        list=new int[10];
        size=0;
        resetStats();
    }
    
    public ArrayList(int capacity)
    {
        list=new int[capacity];
        size=0;
        resetStats();
    }
    
    public void resetStats()
    {
		compareCount=0;
		swapCount=0;
		time=System.nanoTime();
	}
	
	public Stats getStats()
	{
		return new Stats(size,(System.nanoTime()-time)/1e9,compareCount,swapCount);
	}
    
    public static ArrayList orderedList(int n)
    {
		ArrayList m=new ArrayList(n);
		for(int i=0;i<n;i++)m.add(i);
		return m;
	}
    
    public static ArrayList shuffledList(int n)
    {
		ArrayList m=orderedList(n);
		m.shuffle();
		return m;
	}
    
    //methods
    
    
    /**
     * A method that shuffles the values in an array.
     * 
     */
    public void shuffle()
    {
		for (int i=0;i<size;i++)
		{
			// One method for random: int rando=(int)Math.random()*size;
			Random r=new Random();
			int randomIndex=r.nextInt(size);
			swap(i,randomIndex);
		}
	}
	
	public boolean isSorted()
	{
		for (int i=0;i<size-1;i++)
		{
			if(list[i]>list[i+1]) return false;
		}
		return true;
	}
	
	public void bogoSort()
	{
		while(!isSorted())shuffle();
	}
	
	/**
	 * A method that swaps two values in an array given two indexes.
	 * 
	 * @param index1 The first value's index to be swapped.
	 * @param index2 The second value's index to be swapped.
	 */
	private void swap(int index1, int index2)
	{
		int temp=list[index1];
		list[index1]=list[index2];
		list[index2]=temp;
		swapCount++;
	}
    
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
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public void add(int index, int value)
    {
		if(index<0 || index>=size)throw new IndexOutOfBoundsException();
		int i;
		if (list.length==size-1) upsize();
		list[index]=value;
		int j=index+1;
		for (int k=j;k<size;k++) {
			list[k]=list[j];
			j++;
		}
    }
    
    /**
     * A function that adds all the values in a CP2List to the end of another CP2List.
     * 
     * @param list The list of values to be added at the end.
     */
    public void addAll(CP2List list)
    {
		for(int i=0;i<list.size();i++) {
			int v=list.get(i);
			add(v);
		}
	}
	
	/**
	 * A function that determines if two CP2Lists are equal. 
	 * 
	 * @param list The list that the CP2List is being compared to.
	 * @return if the lists are equal.
	 */
	public boolean equals(CP2List list){
		if(list.size()!=size)return false;
		for (int i=0;i<size;i++) {
			if(this.list[i]!=list.get(i))return false;
		}
		return true;
	}
    
    /**
     * A function that doubles the size of an array.
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
	public boolean contains(int value)
	{
		return indexOf(value)!=-1;
	}
	
	/**
	 * Determines if an array contains all the values in another array.
	 * 
	 * @param list The list of values that are being looked for in the array.
	 * @return If the list of values are found in the array.
	 */
	public boolean containsAll(CP2List list)
	{
		for(int i=0;i<list.size();i++) {
			int v=list.get(i);
			if(!this.contains(v))return false;
		}
		return true;
	}
	
	/**
	 * Find the index of the first occurance of the value.
	 * 
	 * @param value The value you are looking for.
	 * @return The index of the first occurance. Returns -1 if nothing is found.
	 */
	public int indexOf(int value){
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
	public boolean isEmpty()
	{
		if(size==0) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	/**
	 * Deletes the value at the given index.
	 * 
	 * @param Index The index that will be deleted.
	 * @return The deleted value.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size) 
	 */
	public int delete(int index)
	{
		if(index<0 || index>=size)throw new IndexOutOfBoundsException();
		for (int i=index;i<size-1;i++) 
		{
			list[i]=list[i+1];
		}
		
		size--;
		
		int deletedValue=list[index];
		return deletedValue;
	}
	
	/**
	 * Removes the first occurance of a given value
	 * 
	 * @param value The value that will be removed.
	 * @return If the value can be removed.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size) 
	 */
	public boolean remove(int value)
	{
		int index=indexOf(value);
		if(index<0 || index>=size)throw new IndexOutOfBoundsException();
		if(index>=0)delete(index);
		return true;
	}
	
    
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
     * Replaces the old value at the index in the given list to the new value.
     * 
     * @param index The index of where the new value should be inserted.
     * @param value The new value that will be inserted at the given index.
     * @return The old value at the given index. 
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size) 
     */
	public int set(int index, int value)
	{
		if(index<0 || index>=size)throw new IndexOutOfBoundsException();
		int old=list[index];
		list[index]=value;
		return old;
	}
	
	// Unsupported Operations
	public void addAll(int index,CP2List list){throw new  UnsupportedOperationException();}
	public int lastIndexOf(int value){throw new  UnsupportedOperationException();}
	public void removeRange(int fromIndex,int toIndex){throw new  UnsupportedOperationException();}
	public CP2List subList(int fromIndex,int toIndex){throw new  UnsupportedOperationException();}
	public int[] toArray(){throw new  UnsupportedOperationException();}
}
