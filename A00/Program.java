import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        
        ArrayList coolList=new ArrayList();
        for(int i=3;i<30;i+=2) coolList.add(i);
        System.out.println(coolList);
        coolList.delete(7);
        System.out.println(coolList);
        coolList.add(7, 7);
		System.out.println(coolList);
        
        
    }
    

    
   
}


