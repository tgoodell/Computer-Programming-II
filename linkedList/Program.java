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
		list.add(7);
		
		LinkedList coolList=new LinkedList();
		coolList.add(58);
		coolList.add(32);
		coolList.add(55);
		
		System.out.println(coolList);
		
		System.out.println(list);
		
		
		list.addAll(coolList);
		System.out.println(list);
	}
}
