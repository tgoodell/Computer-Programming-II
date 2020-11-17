public class Test
{
	public static void main(String[] ars)
	{
		add1Test();
		add2Test();
		addIndex1Test();
		addIndex2Test();
		addAll1Test();
		addAll2Test();
		clear1Test();
		clear2Test();
		contains1Test();
		contains2Test();
		equals1Test();
		equals2Test();
		get1Test();
		get2Test();
		indexOf1Test();
		indexOf2Test();
		isEmpty1Test();
		isEmpty2Test();
		delete1Test();
		delete2Test();
		remove1Test();
		remove2Test();
		removeRange1Test();
		removeRange2Test();
		size1Test();
		size2Test();
		set1Test();
		set2Test();
	}
	
	public static void add1Test()
	{
		LinkedList list=new LinkedList();
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		if(list.toString().equals("4, 5, 6, 7"))
		{
			System.out.println("Success: Add 1 Test");
		} else {
			System.out.println("Failed: Add 1 Test");
			System.out.println("List should be [4,5,6,7] instead of " + list.toString());
		}
	}
	
	public static void add2Test()
	{
		LinkedList list=new LinkedList();
		list.add(4);
		if(list.toString().equals("4"))
		{
			System.out.println("Success: Add 2 Test");
		} else {
			System.out.println("Failed: Add 2 Test");
			System.out.println("List should be [4] instead of " + list.toString());
		}
	}
	
	public static void addIndex1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.add(5,47);
		if(list.get(5)==47) System.out.println("Success: Add Index 1 Test");
		else System.out.println("Failed: Add Index 1 Test");
	}
	
	public static void addIndex2Test()
	{
		try
		{
			LinkedList list=new LinkedList();
			for(int i=1;i<10;i++)list.add(i);
			list.add(11,47);
			System.out.println("Failed: Add Index 2 Test");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Success: Add Index 2 Test");
		}
	}
	
	public static void addAll1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		LinkedList list2=new LinkedList();
		for(int i=11;i<21;i++)list2.add(i);
		list.addAll(list2);
		if(list.toString().equals("1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20")) System.out.println("Success: Add All 1 Test");
		else System.out.println("Failed: Add All 1 Test");
	}
	
	public static void addAll2Test()
	{
		LinkedList list=new LinkedList();
		LinkedList list2=new LinkedList();
		list.addAll(list2);
		if(list.toString().equals("[]")) System.out.println("Success: Add All 2 Test");
		else System.out.println("Failed: Add All 2 Test");
	}
	
	public static void clear1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.clear();
		if(list.isEmpty()) System.out.println("Success: Clear 1 Test");
		else System.out.println("Failed: Clear 1 Test");
	}
	
	public static void clear2Test()
	{
		LinkedList list=new LinkedList();
		list.clear();
		if(list.isEmpty()) System.out.println("Success: Clear 2 Test");
		else System.out.println("Failed: Clear 2 Test");
	}
	
	public static void contains1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(list.contains(3)) System.out.println("Success: Contains 1 Test");
		else System.out.println("Failed: Contains 1 Test");
	}
	
	public static void contains2Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(!list.contains(11)) System.out.println("Success: Contains 2 Test");
		else System.out.println("Failed: Contains 2 Test");
	}
	
	public static void equals1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		LinkedList list2=list;
		if(list.equals(list2)) System.out.println("Success: Equals 1 Test");
		else System.out.println("Failed: Equals 1 Test");
	}
	
	public static void equals2Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		LinkedList list2=new LinkedList();
		for(int i=10;i<13;i++)list2.add(i);
		if(!list.equals(list2)) System.out.println("Success: Equals 2 Test");
		else System.out.println("Failed: Equals 2 Test");
	}
	
	public static void get1Test()
	{
		LinkedList list=new LinkedList();
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		if(list.get(1)==5 && list.get(2)==6)
		{
			System.out.println("Success: Get 1 Test");
		} else {
			System.out.println("Failed: Get 1 Test");
		}
	}
	
	public static void get2Test()
	{
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		if(list.get(0)==1 && list.get(3)==4)
		{
			System.out.println("Success: Get 2 Test");
		} else {
			System.out.println("Failed: Get 2 Test");
		}
	}
	
	public static void indexOf1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(list.indexOf(6)==7) System.out.println("Success: Index Of 1 Test");
		else System.out.println("Failed: Index Of 1 Test");
	}
	
	public static void indexOf2Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(list.indexOf(11)==-1) System.out.println("Success: Index Of 2 Test");
		else System.out.println("Failed: Index Of 2 Test");
	}
	
	public static void isEmpty1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(!list.isEmpty()) System.out.println("Success: Is Empty 1 Test");
		else System.out.println("Failed: Is Empty 1 Test");
	}
	
	public static void isEmpty2Test()
	{
		LinkedList list=new LinkedList();
		if(list.isEmpty()) System.out.println("Success: Is Empty 2 Test");
		else System.out.println("Failed: Is Empty 2 Test");
	}
	
	public static void delete1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.delete(6);
		if(list.toString().equals("1, 2, 3, 4, 5, 6, 8, 9")) System.out.println("Success: Delete 1 Test");
		else System.out.println("Failed: Delete 1 Test");
	}
	
	public static void delete2Test()
	{
		try {
			LinkedList list=new LinkedList();
			for(int i=1;i<10;i++)list.add(i);
			list.delete(20);
			System.out.println("Failed: Delete 2 Test");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Success: Delete 2 Test");
		}
		
	}
	
	public static void remove1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.remove(7);
		if(list.toString().equals("1, 2, 3, 4, 5, 6, 8, 9")) System.out.println("Success: Remove 1 Test");
		else System.out.println("Failed: Remove 1 Test");
	}
	
	public static void remove2Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(!list.remove(11)) System.out.println("Success: Remove 2 Test");
		else System.out.println("Failed: Remove 2 Test");
	}
	
	public static void removeRange1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.removeRange(5,7);
		if(list.toString().equals("1, 2, 3, 4, 5, 9")) System.out.println("Success: Remove Range 1 Test");
		else System.out.println("Failed: Remove Range 1 Test");
	}
	
	public static void removeRange2Test()
	{
		try 
		{
			LinkedList list=new LinkedList();
			for(int i=1;i<10;i++)list.add(i);
			list.removeRange(11,15);
			System.out.println("Failed: Remove Range 2 Test");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Success: Remove Range 2 Test");
		}
	}
	
	public static void size1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		if(list.size()==9) System.out.println("Success: Size 1 Test");
		else System.out.println("Failed: Size 1 Test");
	}
	
	public static void size2Test()
	{
		LinkedList list=new LinkedList();
		if(list.size()==0) System.out.println("Success: Size 2 Test");
		else System.out.println("Failed: Size 2 Test");
	}
	
	public static void set1Test()
	{
		LinkedList list=new LinkedList();
		for(int i=1;i<10;i++)list.add(i);
		list.set(7,47);
		if(list.get(7)==47) System.out.println("Success: Set 1 Test");
		else System.out.println("Failed: Set 1 Test");
	}
	
	public static void set2Test()
	{
		try
		{
			LinkedList list=new LinkedList();
			for(int i=1;i<10;i++)list.add(i);
			list.set(11,47);
			System.out.println("Failed: Set 2 Test");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Success: Set 2 Test");
		}
	}
	
	
	
	//Make tests that fail with try catches
	//Fail/Success Test for each function
}
