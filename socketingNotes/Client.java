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
            DataOutputStream out=new DataOutputStream(s.getOutputStream()); 
            out.writeUTF("SEND "+author+" "+msg);  
            out.flush();
            
            DataInputStream in=new DataInputStream(s.getInputStream());  
            String answer=in.readUTF();
            System.out.println(answer);
            out.close();
            
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
            DataOutputStream out=new DataOutputStream(s.getOutputStream()); 
            DataInputStream in=new DataInputStream(s.getInputStream());
            out.writeUTF("UPDATE "+id);  
            out.flush();
            
            String answer=in.readUTF();
            byte[] decodedBytes = Base64.getDecoder().decode(answer);
            answer = new String(decodedBytes);
            out.close();
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
                 sendMsg("Tristan", msg);
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
