/**
 * A LinkedList.
 * 
 * @author Tristan Goodell
 */

public class LinkedList implements CP2List
{
	Node root=new Node(0);
	private int size;
	Node last;
	
	public LinkedList()
	{
		last=root;
		size=0;
	}
	
	private Node getNode(int index)
	{
		Node current=root;
		for(int i=0;i<=index;i++)
		{
			current=current.next;
		}
		
		return current;
	}
	
	private Node getNode(Node startNode, int offset)
	{
		Node current=startNode;
		for(int i=0;i<=offset;i++)
		{
			current=current.next;
		}
		
		return current;
	}
	
	/** 
     * A function that adds a node with a specified value to the LinkedList.
     * 
     * @param value the value to be added
     */
	public void add(int value)
	{
		size++;
		last.next=new Node(value);
        last=last.next;
	}
	
	/**
     * A function that inserts a node with a specified value at the desired index.
     * 
     * @param index the index used for insertion
     * @param value the value to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
	// Need to fix
	public void add(int index,int value)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		Node current=root;
		
		for(int i=0;i<=index;i++)
		{
			if(i==index)
			{
				Node after=current.next;
				current.next=new Node(value);
				current=current.next;
				current.next=after;
			}
			current=current.next;
		}
		size++;
	}
	
	public void addAll(CP2List list){throw new  UnsupportedOperationException();}//*

	public void addAll(int index,CP2List list){throw new  UnsupportedOperationException();}//*
	
	/**
     * A function that adds all the values in a LinkedList to the end of another LinkedList.
     * 
     * @param list The list of values to be added at the end.
     * @throws 
     */
	public void addAll(LinkedList addList)
	{		
		try {
			for(int i=0;i<addList.size();i++)
			{
				int v=addList.get(i);
				add(v);
			}
		}
		catch(NullPointerException e){}
	}
	
	/**
	 * Clears a specified LinkedList. 
	 * 
	 */
	// Done
	public void clear()
	{
		Node current=root;
		current.next=null;
		size=0;
	}
	
	
	/**
     * Determines if the LinkedList contains the specified value.
     * 
     * @param value The value that is searched for in the LinkedList.
     * @return If the value is found in the LinkedList.
     */
	// Done
	public boolean contains(int value)
	{
		Node current=root;
		int i=0;
		while(i<=size)
		{
			if(current.value==value) return true;
			current=current.next;
			i++;
		}
		return false;
	}
	
	public boolean containsAll(CP2List list){throw new  UnsupportedOperationException();}//*
	
	
	/**
	 * A function that determines if two LinkedLists are equal. 
	 * 
	 * @param list The list that the LinkedList is being compared to.
	 * @return if the lists are equal.
	 */
	public boolean equals(CP2List list)
	{
		Node current=root;
		if(size!=list.size())return false;

		for(int i=0;i<size-1;i++)
		{
			if(current.value==list.get(i))
			{
				return false;
			}
			
			current=current.next;
		}
		return true;
	}
	
	
	/**
     * Gets a value of a node at the specified index.
     * 
     * @param index the index to get the value at
     * @return the value at the index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
	public int get(int index)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return getNode(index).value;
	}
	
	/**
	 * Find the index of the first occurance of the value.
	 * 
	 * @param value The value you are looking for.
	 * @return The index of the first occurance. Returns -1 if nothing is found.
	 */
	// Done
	public int indexOf(int value)
	{
		Node current=root;
		
		int i=1;
		while(i<size)
		{
			if(current.value==value)return i;
			current=current.next;
			i++;
		}
		return -1;
	}
	
	/**
	 * Determines if the LinkedList is empty.
	 * 
	 * @return If the LinkedList is empty.
	 */
	// Done
	public boolean isEmpty()
	{
		if(size>0)return false;
		return true;
	}
	
	public int lastIndexOf(int value){throw new  UnsupportedOperationException();}//*
	
	/**
	 * Deletes the value at the given index.
	 * 
	 * @param Index The index that will be deleted.
	 * @return The deleted value.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size) 
	 */
	public int delete(int index)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		Node prev=getNode(index-1);
		int deletedValue=prev.next.value;
		if(index+1==size)last=prev;
		prev.next=prev.next.next;
		size--;
		return deletedValue;
	}
	
	/**
	 * Removes the first occurance of a given value
	 * 
	 * @param value The value that will be removed.
	 * @return If the value can be removed.
	 */
	// Done
	public boolean remove(int value)
	{
		Node current=root;
		
		int i=-1;
		while(i<=size-1)
		{
			if(current.value==value)
			{
				delete(i);
				return true;
			}
			current=current.next;
			i++;
		}
		return false;
	}
	
	/**
	 * Remove nodes from the fromIndex inclusive to the toIndex exclusive.
	 * 
	 * @param fromIndex The starting index inclusive
	 * @param toIndex The ending index exclusive
	 * 
	 * @throws IndexOutOfBoundsException if fromIndex or toIndex are out of range
	 */
	public void removeRange(int fromIndex, int toIndex)
	{
		if(fromIndex<0 || fromIndex>=size() || toIndex>size() || toIndex<fromIndex)
		{
			throw new IndexOutOfBoundsException();
		}
		
		Node a=getNode(fromIndex-1); // One before the range
		Node b=getNode(a,toIndex-fromIndex+1); // One after the range
		a.next=b;
	}
	
	/**
     * Reports the size of the LinkedList.
     * 
     * @return the size of the LinkedList.
     */
	public int size()
	{
		return size;
	}
	
	/**
     * Replaces the old value at the index in the given LinkedList to the new value.
     * 
     * @param index The index of where the new value should be inserted.
     * @param value The new value that will be inserted at the given index.
     * @return The old value at the given index. 
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size) 
     */
	public int set(int index, int value)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		Node current=root;
		
		for(int i=-1;i<size-1;i++)
		{
			if(i==index)
			{
				int x=current.value;
				current.value=value;
				return x;
			}
			current=current.next;
		}
		
		return -1;
	}
	
	public CP2List subList(int fromIndex,int toIndex){throw new  UnsupportedOperationException();}//*
	
	public int[] toArray(){throw new  UnsupportedOperationException();}//*
	
	
	public String toString()
    {
		if(size==0) return "[]";
        return root.next.toString();
    }
	
}

class Node
{
	public int value; //32bit
	public Node next; //64bit Pointer to the next node
	
	public Node(int value)
	{
		this.value=value;
	}
	
	public String toString()
    {
        if(next!=null) return value+", "+next;
        return value+"";
    }
	
	
}
