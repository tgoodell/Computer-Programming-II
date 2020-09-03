import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        
        ArrayList coolList=new ArrayList();
        for(int i=3;i<30;i+=2) coolList.add(i);
        System.out.println(coolList);
        coolList.remove(7);
        System.out.println(coolList);
        coolList.add(7, 7);
		System.out.println(coolList);
		
		System.out.println("---");
		ArrayList list=new ArrayList();
		for(int i=3;i<30;i+=2) list.add(i);
		list.add(3);
		ArrayList tlist=new ArrayList();
		for(int i=3;i<30;i+=2) tlist.add(i);
		
		System.out.println(list.equals(tlist));
		
		System.out.println(System.nanoTime());
        
    }
    

    
   
}


