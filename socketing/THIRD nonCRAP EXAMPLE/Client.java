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
	public JPanel panel;
	public JButton sendMessageButton;
    public JTextArea messageTextBox;
    public JTextArea chatBox;
    public JFrame frame=new JFrame("Chat App");
    
	public String author="tristan";
	public String messageText="";
	Timer timer;
	
	Logic()
    { 
        panel=new JPanel()
        {
            public void paint(Graphics g)
            {
                draw((Graphics2D)g);
                
            }
            
            
        }; 
       int delay = 1000; //milliseconds
      ActionListener taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
			  saveMessage();
			  panel.revalidate();
              panel.repaint();
          }
      };
      
      new Timer(delay, taskPerformer).start();
      frame.add(panel);
      panel.setPreferredSize(new Dimension(1280, 800));
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
    public void saveMessage()
    {
		messageText=messageTextBox.getText();
		System.out.println(messageText);
	}
    
    public void draw(Graphics2D g)
    {
        //~ Color bgblue=new Color(156,192,231);
        //~ g.setColor(bgblue);
        //~ g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        
        //~ drawMessages(g);
        
        JPanel mainPanel = new JPanel();
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        
        mainPanel.setLayout(new BorderLayout());

        //~ JPanel southPanel = new JPanel();
        //~ southPanel.setBackground(Color.BLUE);
        //~ southPanel.setLayout(new GridBagLayout());
        
        sendMessageButton=new JButton("Send");
        sendMessageButton.setBounds(50,50,95,30);
        sendMessageButton.addActionListener(new sendMessageButtonListener());
        
        messageTextBox=new JTextArea();
        messageTextBox.setBounds(50,100,150,20);
        System.out.println(messageText);
        
        chatBox = new JTextArea(5,30);
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);
        
        chatBox.append(getMsgs(0));
        
        //~ southPanel.add(messageTextBox);
        //~ southPanel.add(sendMessageButton);
 

        //~ messageBox = new JTextField(30);
        //~ messageBox.requestFocusInWindow();
        //~ messageBox.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 12));

        //~ sendMessage = new JButton("Send Message");
        //~ sendMessage.addActionListener(new sendMessageButtonListener());

        //~ chatBox = new JTextArea();
        //~ chatBox.setEditable(false);
        //~ chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        //~ chatBox.setLineWrap(true);

        //~ mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageTextBox, left);
        southPanel.add(sendMessageButton, right);
        
        mainPanel.add(BorderLayout.SOUTH, southPanel);
        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setVisible(true);
        
    }
    
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
			String content = messageTextBox.getText();
			System.out.println(content);
			revalidate();
			repaint();
			
			sendMsg(author,content);
			//~ System.out.println(messageTextBox.getText());
			
			//~ messageTextBox.setText("");
            
            //~ messageTextBox.requestFocusInWindow();
        }
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
