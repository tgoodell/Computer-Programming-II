import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        
        
        //~ System.out.println(Arrays.toString(nums));
        // ArrayList list=new ArrayList();
        //~ long start=System.nanoTime();
        //~ for(int i=0;i<1000000000;i++) list.add(i);
        //~ long end=System.nanoTime();
        //~ System.out.println((end-start)/1e9);
        //~ try{ System.gc();Thread.sleep(5000);}catch(Exception e){}
        //~ System.out.println(list);
        
        //~ int[] list=new int[1000000000];
        //~ long start=System.nanoTime();
        //~ for(int i=0;i<1000000000;i++) list[i]=i;
        //~ long end=System.nanoTime();
        //~ try{Thread.sleep(5000);}catch(Exception e){}
        //~ System.out.println((end-start)/1e9);
        
        ArrayList list1=new ArrayList();
        for(int i=0;i<10;i++) list1.add(i);
        System.out.println(list1);
        list1.set(3,147);
        System.out.println(list1);
        System.out.println(list1.indexOf(147));
        System.out.println(list1.get(3));
        ArrayList list3=new ArrayList();
        System.out.println(list3.size());
        System.out.println(list3.isEmpty());
        
        ArrayList list2=new ArrayList();
        for(int i=3;i<30;i+=2) list2.add(i);
        System.out.println(list2);
        list2.delete(7);
        System.out.println(list2);
		//System.out.println(list1.containsAll(list2));
        
        
    }
    

    
   
}


