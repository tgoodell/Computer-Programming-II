import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Server Running");
			ServerSocket ss=new ServerSocket(6666);
			while(true)
			{
				Socket s=ss.accept();
				Scanner sc=new Scanner(s.getInputStream());
				String str=sc.nextLine();
				System.out.println("meesage= " + str);
			}
			ss.close();
		} catch(Exception e) 
		{
			System.out.println(e);
		}
	}
}
