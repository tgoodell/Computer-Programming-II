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
		LinkedList list2=new LinkedList();
		
		for(int i=1;i<10;i++)list.add(i);
		//for(int i=1;i<10;i=i+2)list2.add(i);
		System.out.println(list);
		list.clear();
		System.out.println(list);
	}
}
