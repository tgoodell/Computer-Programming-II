import java.io.*;  
import java.net.*;
import java.util.*; 

 
public class Server
{ 
    
    public static ArrayList<String> messages = new ArrayList<String>();
    
    
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
                    System.out.println(s); 
                    Scanner sc=new Scanner(s.getInputStream());  
                    String str=sc.nextLine();
                    Scanner lineSc=new Scanner(str); 
                    String action=lineSc.next();
                    PrintWriter out=new PrintWriter(s.getOutputStream()); 
                    if(action.equals("SEND"))
                    {
                        String author=lineSc.next();
                        String msg=lineSc.nextLine();
                        messages.add(author+": "+msg);
                        System.out.println(author+": "+msg);
                        out.println(messages.size()-1);
                    }
                    else if(action.equals("UPDATE"))
                    {
                        System.out.println("update requested");
                        int lastMsgId=lineSc.nextInt();
                        String output="";
                        for(int i=lastMsgId+1;i<messages.size();i++)
                        {
                            output+=messages.get(i)+"\n";
                        }
                        String encodedString = Base64.getEncoder().encodeToString(output.getBytes());

                        out.println(encodedString);
                    }
                    else
                    {
                        out.println("PARSING FAILURE!");
                    }
                   
                    
                    
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
