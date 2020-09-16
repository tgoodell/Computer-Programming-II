import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
		System.out.println(Stats.getHeaders());
		int i=0;
		/*
		 * Quick Sort
        while(true)
        {
            int n=(int)Math.pow(2,i);
            ArrayList coolList=ArrayList.shuffledList(n);
            coolList.resetStats();
            coolList.quickSort();
            Stats s=coolList.getStats();
            if(s.time>60)break;
            System.out.println(s);
            i++;
        }
        */
        
	
        while(true)
        {
            int n=(int)Math.pow(2,i);
            ArrayList coolList=ArrayList.shuffledList(n);
            coolList.resetStats();
            coolList.quickSort();
            Stats s=coolList.getStats();
            if(s.time>60)break;
            System.out.println(s);
            i++;
        }
    }
    

    
   
}


