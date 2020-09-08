import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        int i=0;
        while(true)
        {
			int n=(int)Math.pow(2,i);
			ArrayList coolList=ArrayList.shuffledList(n+10);
			coolList.resetStats();
			coolList.bogoSort();
			Stats s=coolList.getStats();
			System.out.println(s);
		}
        
    }
    

    
   
}


