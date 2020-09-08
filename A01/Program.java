import java.awt.*;
import java.util.Arrays;
public class Program
{
    public static void main(String[] args)
    {
        ArrayList coolList=ArrayList.shuffledList(10);
		System.out.println(coolList);
		coolList.insertionSort();
		System.out.println(coolList);
        System.out.println(Stats.getHeaders());
		System.out.println(coolList.getStats());
    }
    

    
   
}


