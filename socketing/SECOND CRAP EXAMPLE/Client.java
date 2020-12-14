import java.io.*;  
import java.net.*;
import java.util.*; 

 
public class Client
{ 
    public static void sendMsg(String author, String msg)
    {
        try
        {
            Socket s=new Socket("10.241.76.225",6666); 
            PrintWriter out=new PrintWriter(s.getOutputStream()); 
            out.println("SEND "+author+" "+msg);  
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
    
    public static String getMsgs(int id)
    {
        try
        {
            Socket s=new Socket("10.241.76.225",6666); 
            PrintWriter out=new PrintWriter(s.getOutputStream()); 
            out.println("UPDATE "+id);  
            out.flush();
            Scanner serverSc=new Scanner(s.getInputStream());  
            String answer=serverSc.nextLine();
            byte[] decodedBytes = Base64.getDecoder().decode(answer);
            answer = new String(decodedBytes);
            return answer;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            return "";
        } 
        
    }
    
    
    
    public static void main(String[] args)
    {  
         //~ sendMsg("bob","Hello again");
         
         int id=-1;
         Scanner sc=new Scanner(System.in);
         while(true)
         {
             System.out.print("1) Send\n2) Update\n>>>");
             int option=sc.nextInt();
             if(option==1)
             {
                 System.out.print("message: ");
                 sc.nextLine();
                 String msg=sc.nextLine();
                 sendMsg("bob", msg);
             }
             else if (option==2)
             {
                 String msgs=getMsgs(id);
                 id+=msgs.split("\n").length-1;
                 System.out.println(msgs);
             }
             
         }
    }  
} 
