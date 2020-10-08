public interface CP2List //DO NOT CHANGE THIS FILE!!!!
{
	public void add(int value);
	public void add(int index,int value);
	public void addAll(CP2List list);//*
	public void addAll(int index,CP2List list);//*
	public void clear();
	public boolean contains(int value);
	public boolean containsAll(CP2List list);//*
	public boolean equals(CP2List list);
	public int get(int index);
	public int indexOf(int value);
	public boolean isEmpty();
	public int lastIndexOf(int value);//*
	public int delete(int index);
	public boolean remove(int value);
	public void removeRange(int fromIndex,int toIndex);//*
	public int size();
	public int set(int index, int value);
	public CP2List subList(int fromIndex,int toIndex);//*
	public int[] toArray();//*
}
