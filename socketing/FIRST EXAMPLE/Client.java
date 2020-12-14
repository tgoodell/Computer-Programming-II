import java.io.*;  
import java.net.*;
import java.util.*; 

 
public class Client
{ 
    public static void main(String[] args)
    {  
        try
        {
            Scanner sc=new Scanner(System.in) ;
            System.out.print("Give me two numbers seperated by a space: ");
            String nums=sc.nextLine();      
            //~ Socket s=new Socket("10.241.76.225",6666); 
            //~ Socket s=new Socket("10.241.58.54",6666); 
            //~ Socket s=new Socket("10.241.132.102",6666); 
            Socket s=new Socket("10.241.245.208",6666); 
            //~ Socket s=new Socket("10.241.21.158",6666); 
            PrintWriter out=new PrintWriter(s.getOutputStream()); 
            out.println(nums);  
            out.flush();
            Scanner serverSc=new Scanner(s.getInputStream());  
            String answer=serverSc.nextLine();
            System.out.println(answer);
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }  
} 
