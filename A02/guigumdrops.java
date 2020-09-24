import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

public class guigumdrops extends JPanel
{
	public static int winWidth=600;
	public static int winHeight=400;
	
    public static void main(String[] args)
    {
        JFrame frame= new JFrame("Welecome to JavaTutorial.net");   
        MyCustomPanel panel=new MyCustomPanel(); 
        frame.getContentPane().add(panel);
        panel.addMouseMotionListener(panel);
        frame.addKeyListener(panel);
        frame.setSize(winWidth, winHeight);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
    }
}

class MyCustomPanel extends JPanel implements MouseMotionListener, KeyListener
{
    double x=100;
    double y=100;
    double sx=x+20;
    double sy=y+20;
    double vx=0;
    double vy=0;
    double gravity=0.2;
    double drag=0.3;
    boolean leftPressed=false;
    boolean rightPressed=false;
    boolean upPressed=false;
    
    int winWidth=600;
    int winHeight=400;
    
    Timer timer;
    
    MyCustomPanel()
    {
		setBackground(Color.WHITE);
		refreshScreen();
	}
    
    public void refreshScreen() 
    {
		
		timer = new Timer(0, new ActionListener() 
		{
		@Override
			public void actionPerformed(ActionEvent e) 
			{
				// Vertical Border Stops
				if(y>winHeight-80)
				{
					y=winHeight-80;
					//vy=-.3*vy;
					vy=0;
				} else if(y<0 && vy>0)
				{
					y=0;
				}
				
				// Horizontal Border Pacman Transition
				if(x<0)
				{
					x=winWidth;
				} else if(x>winWidth)
				{
					x=0;
				}
				
				// Terminal Velocity
				//~ if(vy>5)
				//~ {
					//~ vy=0;
				//~ } else if(vy<-5)
				//~ {
					//~ vy=0;
				//~ }
				
				// Horizontal Drag
				if(vx>0)
				{
					vx-=drag;
				} else if(vx<0)
				{
					vx+=drag;
				}
				
				// Horizontal Max Speed
				if(vx>5)
				{
					x+=5;
					vx=5;
				} else if(vx<-5)
				{
					x-=5;
					vx=-5;
				}
				else
				{
					x+=vx;
				}
				
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						y-=2*gravity;
					}
				});
				
				if(leftPressed)vx-=1;
				if(rightPressed)vx+=1;
				if(upPressed)vy-=2*gravity;
				
				vy+=gravity;
				y+=vy;
				sx=x+20;
				sy=y+20;
				
				revalidate();
				repaint();
			}
		});
		
		timer.setRepeats(true);
		// Aprox. 60 FPS
		timer.setDelay(1000/60);
		timer.start();
	}
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        //~ g2.setColor(Color.WHITE);
        //~ g2.setStroke(new BasicStroke(10));
        //~ g2.fillRect(0, 0, winWidth, winHeight); 
        
        g2.setColor(Color.BLACK);
        super.paintComponent(g2);  
        g2.drawString((int)sx + ", " + (int)sy, 20,20);
        
        g2.setStroke(new BasicStroke(10));
        g2.fillRect(0, winHeight-40, winWidth, winHeight); 
        
        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(10));
        
        g2.fillRect((int)x, (int)y, 40, 40);
        
        g2.setColor(Color.RED);
        if(upPressed)g2.fillRect((int)x+10, (int)y+40, 20, 10);
        if(rightPressed)g2.fillRect((int)x-10, (int)y+10, 10, 20);
        if(leftPressed)g2.fillRect((int)x+40, (int)y+10, 10, 20);


    }
    
    public void keyReleased(KeyEvent e)
    {
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT || keyCode==KeyEvent.VK_A) leftPressed=false;
		if(keyCode==KeyEvent.VK_RIGHT || keyCode==KeyEvent.VK_D) rightPressed=false;
		if(keyCode==KeyEvent.VK_UP || keyCode==KeyEvent.VK_W) upPressed=false;
	}
    
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT || keyCode==KeyEvent.VK_A) leftPressed=true;
		if(keyCode==KeyEvent.VK_RIGHT || keyCode==KeyEvent.VK_D) rightPressed=true;
		if(keyCode==KeyEvent.VK_UP || keyCode==KeyEvent.VK_W) upPressed=true;
	}
	
	public void keyTyped(KeyEvent e){}
	public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    
}
