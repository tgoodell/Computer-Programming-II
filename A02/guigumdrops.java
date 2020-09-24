import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

public class guigumdrops extends JPanel
{
	public static int winWidth=1920;
	public static int winHeight=1000;
	
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
    double sx=x+20;
    double sy=y+20;
    double vx=0;
    double vy=0;
    double gravity=0.8;
    double drag=0.1;
    boolean leftPressed=false;
    boolean rightPressed=false;
    boolean upPressed=false;
    
    int winWidth=1920;
    int winHeight=1000;
    
    int frameCount=0;
    
    boolean onGround=false;
    
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
				// Stop Jump Velocity
				if(vy==-2*gravity)
				{
					vy=0;
				}
				
				// Vertical Border Stops
				if(y>winHeight-80)
				{
					y=winHeight-80;
					vy=-0.99*vy;
				}
				
				if(y<0)
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
				
				// Horizontal Max Speed
				//~ if(vx>10)
				//~ {
					//~ x+=10;
					//~ vx=10;
				//~ } else if(vx<-10)
				//~ {
					//~ x-=10;
					//~ vx=-10;
				//~ }
				//~ else
				//~ {
					//~ x+=vx;
				//~ }
				x+=vx;
				
				// Horizontal Drag
				if(vx>0)
				{
					vx-=drag;
				}
				
				if(vx<0)
				{
					vx+=drag;
				}
				
				// First platform collison
				
				if(x>(winWidth/4) && x<(winWidth/4)+300 && y==winHeight-280)
				{
					y=winHeight-280;
					vy=0;
				}
				
				if(leftPressed)vx-=0.5;
				if(rightPressed)vx+=0.5;
				if(upPressed)vy-=2*gravity;
				
				vy+=gravity;
				y+=vy;
				sx=x+20;
				sy=y+20;
				
				//~ addMouseListener(new MouseAdapter() {
					//~ @Override
					//~ public void mouseClicked(MouseEvent e) {
						//~ vy-=2*gravity;
					//~ }
				//~ });
				
				//frameCount++;
				
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
        g2.drawString((int)sx + ", " + (int)sy, 20,20);
        g2.drawString((int)vx + ", " + (int)vy, 20,40);
        
        g2.setStroke(new BasicStroke(10));
        g2.fillRect(0, winHeight-40, winWidth, winHeight); 
        
        //mid platform
        g2.fillRect(winWidth/4,winHeight-200,300,40); 
        
        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(10));
        
        //g2.fillRect((int)(x+5*Math.sin(2*Math.PI*frameCount/15)), (int)(y+5*Math.sin(2*Math.PI*frameCount/11)), 40, 40);
        g2.fillRect((int)x,(int)y,40,40);
        
        g2.setColor(randomColor());
        g2.setStroke(new BasicStroke(1));
        g2.fillRect((int)sx,(int)sy,4,4);

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
