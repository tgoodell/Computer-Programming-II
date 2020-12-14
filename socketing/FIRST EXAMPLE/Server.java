import java.io.*;  
import java.net.*;
import java.util.*; 

 
public class Server
{ 
    public static void main(String[] args)
    {
        while(true)
        {
            try
            {
                System.out.println("Server Running");
                ServerSocket ss=new ServerSocket(6666); 
                System.out.println(ss.getInetAddress());
                while(true)
                { 
                    Socket s=ss.accept();  
                    Scanner sc=new Scanner(s.getInputStream());  
                    String str=sc.nextLine();
                    Scanner lineSc=new Scanner(str); 
                    int a=lineSc.nextInt();
                    int b=lineSc.nextInt();
                    String equation=String.format("%d+%d=%d",a,b,a+b); 
                    System.out.println("message="+equation);  
                    PrintWriter out=new PrintWriter(s.getOutputStream()); 
                    out.println( equation );
                    out.flush();
                    s.close();
                }  
            }
            catch(Exception e)
            {
                System.out.println(e);
            }  
            System.out.println("Server Stopped");
            
        }
    }  
} 
