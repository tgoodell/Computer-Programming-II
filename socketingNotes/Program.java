import java.io.*;
import java.net.*;
import java.util.*;

public class Program 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Give me two numbers separated by a space: ");
			String nums=sc.nextLine();
			Socket s=new Socket("10.241.76.225",6666);
			PrintWriter out=new PrintWriter(s.getOutputStream());
			out.println(nums);
			out.flush();
			Scanner serverSc=new Scanner(s.getInputStream());
			String answer=serverSc.nextLine();
			System.out.println(answer);
		} catch(Exception e) {System.out.println(e);}
	}
}
