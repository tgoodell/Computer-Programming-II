import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

public class guigumdrops extends JPanel
{
	public static int winWidth=1920;
	public static int winHeight=1080;
	
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
    
    public int getWinWidth()
    {
        return winWidth;
    }
    
    public int getWinHeight()
    {
        return winWidth;
    }
}

class MyCustomPanel extends JPanel implements MouseMotionListener, KeyListener
{
    double x=100;
    double y=100;
    double vx=0;
    double vy=0;
    double gravity=0.8;
    double drag=0.1;
    
    int winWidth=1920;
    int winHeight=1080;
    
    Timer timer;
    
    MyCustomPanel()
    {
		setBackground(Color.WHITE);
		refreshScreen();
	}
    
    public void refreshScreen() 
    {
		timer = new Timer(0, new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				
				// Vertical Border Stops
				if(y>winHeight-80)
				{
					y=winHeight-80;
					vy=-.3*vy;
				}
				
				if(y<0 && vy>0)
				{
					y=0;
				}
				
				// Horizontal Border Pacman Transition
				if(x>winWidth)
				{
					x=0;
				}
				
				if(x<0)
				{
					x=winWidth;
				}
				
				// Terminal Velocity
				if(vy>5 || vy<-5)
				{
					vy=vy;
				}
				
				// Horizontal Drag
				if(vx>0)
				{
					vx-=drag;
				}
				
				if(vx<0)
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
				
				vy-=gravity;
				y-=vy;
				
				revalidate();
				repaint();
			}
		});
		
		timer.setRepeats(true);
		// Aprox. 60 FPS
		timer.setDelay(17);
		timer.start();
	}
    
    public static Color randomColor()
    {
        int r=(int)(Math.random()*256);
        int g=(int)(Math.random()*256);
        int b=(int)(Math.random()*256);
        return new Color(r,g,b);
    }
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(10));
        g2.fillRect(0, 0, winWidth, winHeight); 
        
        g2.setColor(Color.BLACK);
        super.paintComponent(g2);  
        g2.drawString((int)x + ", " + (int)y, 20,20);
        
        g2.setStroke(new BasicStroke(10));
        g2.fillRect(0, winHeight-40, winWidth, winHeight); 
        
        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(10));
        
        g2.fillRect((int)x, (int)y, 40, 40);

    }
    
    public void act(int keyNum)
    {
		int keyCode=keyNum;
		
		if(keyCode==KeyEvent.VK_LEFT && keyCode==KeyEvent.VK_UP)
		{
			vx-=0.1;
			y-=5*gravity;
		} else if(keyCode==KeyEvent.VK_RIGHT && keyCode==KeyEvent.VK_UP)
		{
			vx+=0.1;
			y-=5*gravity;
		} else if(keyCode==KeyEvent.VK_LEFT)
		{
			vx-=0.1;
		} else if(keyCode==KeyEvent.VK_RIGHT)
		{
			vx+=0.1;
		} else if(keyCode==KeyEvent.VK_UP)
		{
			y-=5*gravity;
		}
	}
    
    public void keyReleased(KeyEvent e){}
    
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		act(keyCode);
	}
	
	public void keyTyped(KeyEvent e){}
	public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    
}
