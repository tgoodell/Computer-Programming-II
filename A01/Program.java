import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        int i=0;
        while(true)
        {
			ArrayList coolList=ArrayList.shuffledList(10);
			System.out.println(coolList);
			coolList.bubbleSort();
			System.out.println(coolList);
		}
        
    }
    

    
   
}


