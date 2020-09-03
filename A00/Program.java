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
		list.add(1);
		list.add(2);
		list.add(3);
		coolList.addAll(list);
        System.out.println(coolList);
        
    }
    

    
   
}


