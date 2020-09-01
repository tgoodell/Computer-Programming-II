public interface CP2List //DO NOT CHANGE THIS FILE!!!!
{
	public void add(int value);//Check
	public void add(int index,int value);//Check
	public void addAll(CP2List list);//*
	public void addAll(int index,CP2List list);//*
	public void clear();//Check
	public boolean contains(int value);//Check
	public boolean containsAll(CP2List list);//*Check
	public boolean equals(CP2List list);
	public int get(int index);//Check
	public int indexOf(int value);//Check
	public boolean isEmpty();//Check
	public int lastIndexOf(int value);//*
	public int delete(int index); //remove the specified index
	public boolean remove(int value); //remove the first occurance
	public void removeRange(int fromIndex,int toIndex);//*
	public int size();//check
	public int set(int index, int value);//Check
	public CP2List subList(int fromIndex,int toIndex);//*
	public int[] toArray();//*
}
