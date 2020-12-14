import java.io.*;  
import java.net.*;
import java.util.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

 
public class Client extends JPanel
{
	
    public static void main(String[] args)
    {
        Logic e=new Logic();  
    }
}

class Logic extends JPanel
{ 
	JPanel panel;
	JButton sendMessage;
    JTextField messageBox;
    JTextArea chatBox;
    JFrame newFrame=new JFrame("Chat");
    
	int lastMsgID=0;
	String author="tristan";
	Timer timer;
	
	public Logic()
    {
        JFrame frame= new JFrame("Chat App");   
        panel=new JPanel()
        {
            public void paint(Graphics g)
            {
                draw((Graphics2D)g);
            }
        }; 
        frame.add(panel);
        panel.setPreferredSize(new Dimension(1280, 800));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void refreshScreen() 
    {
		
		timer = new Timer(0, new ActionListener() 
		{
		@Override
			public void actionPerformed(ActionEvent e) 
			{	
				revalidate();
				repaint();
			}
			
		});
	}
    
    public void draw(Graphics2D g)
    {
        //~ Color bgblue=new Color(156,192,231);
        //~ g.setColor(bgblue);
        //~ g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        
        //~ drawMessages(g);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();
        messageBox.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 12));

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        //~ right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1280, 800);
        newFrame.setVisible(true);
        
    }
    
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
			if(messageBox.getText().length()<1)
			{}
			else 
			{
				sendMsg(author,messageBox.getText());
				messageBox.setText("");
			}
            
            messageBox.requestFocusInWindow();
        }
    }
    
    public static void drawMessages(Graphics2D g)
    {
		// fetch messages
		// call function to add each message to array.
		// call the last 100 msgs
		
		g.setColor(Color.WHITE);
		g.drawString(getMsgs(0),20,80);
	}
	
    public static void sendMsg(String author, String msg)
    {
        try
        {
            Socket s=new Socket("127.0.0.1",6666); 
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
            Socket s=new Socket("127.0.0.1",6666); 
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
