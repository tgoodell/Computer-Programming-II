public class Program
{
	public static void main(String[] args)
	{
		//~ Node a=new Node(5);
		//~ Node b=new Node(7);
		//~ Node c=new Node(11);
		//~ a.next=b;
		//~ b.next=c;
		//System.out.println(a.value);
		//System.out.println(a);
		
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(5);
		
		
		
	}
}

class LinkedList
{
	Node root=new Node(0);//pseudoroot
	int size;
	Node last;
	
	public LinkedList()
	{
		last=root;
		size=0;
	}
	
	public void add(int value)
	{
		Node current=root;
		while(current.next!=null)current=current.next;
		current.next=new Node(value);
		size++;
	}
	
	public int get(int index)
	{
		//if(index<0 || index>=size) throw 
		return getNode(index).value;
	}
	
	public int delete(int index)
	{
		Node prev=getNode(index-1);
		int deletedValue=prev.next.value;
		if(index+1==size)last=prev;
		prev.next=prev.next.next;
		size--;
		return deletedValue;
	}
	
	//make this have all the fancy stuff
	private Node getNode(int index)
	{
		Node current=root;
		for(int i=0;i<=index;i++)
		{
			current=current.next;
		}
		
		return current;
	}
	
	public String toString()
	{
		return root.next.toString();
	}
}

class Node
{
	public int value; //32bit
	public Node next; //64bit Pointer to the next node
	public Node prev;
	
	public Node(int value)
	{
		this.value=value;
	}
	
	public String toString()
	{
		return value+", "+next;
	}
	
	
}
