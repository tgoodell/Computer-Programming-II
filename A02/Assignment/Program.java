import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

public class Program extends JPanel
{
	public static int winWidth=1200;
	public static int winHeight=800;
	
    public static void main(String[] args)
    {
        JFrame frame= new JFrame("The Best Game Demo");   
        Game panel=new Game(); 
        frame.getContentPane().add(panel);
        panel.addMouseMotionListener(panel);
        frame.addKeyListener(panel);
        frame.addMouseWheelListener(panel);
        frame.setPreferredSize(new Dimension(winWidth, winHeight));
        frame.pack();
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
    }
}

class Game extends JPanel implements MouseMotionListener, KeyListener, MouseWheelListener
{
    double x=100;
    double y=100;
    double sx=x+20;
    double sy=y+20;
    double vx=0;
    double vy=0;
    double mx=0;
    double my=0;
    double gravity=0.8;
    double drag=0.1;
    
    boolean leftPressed=false;
    boolean rightPressed=false;
    boolean upPressed=false;
    boolean mouseExplosion=false;
    
    int winWidth=1200;
    int winHeight=800;
    int timeRecord=0;
    
    double r=0;
    double g=0;
    double b=0;
    Color backgroundColor = new Color((int)r,(int)g,(int)b);
    
    Timer timer;
    
    Game()
    {
		setBackground(backgroundColor);
		refreshScreen();
		System.out.println(winWidth);
	}
    
    public void refreshScreen() 
    {
		
		timer = new Timer(0, new ActionListener() 
		{
		@Override
			public void actionPerformed(ActionEvent e) 
			{	
				setBackground(backgroundColor);
				// Vertical Border Stops
				if(y>winHeight-80)
				{
					y=winHeight-80;
					vy=-0.1*vy;
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
				if(vy>4)
				{
					vy=0;
				} else if(vy<-4)
				{
					vy=0;
				}
				
				// Horizontal Max Speed
				if(vx>10)
				{
					vx=10;
				} else if(vx<-10)
				{
					vx=-10;
				}
				x+=vx;
				
				// Horizontal Drag
				if(vx>0)
				{
					vx-=drag;
				} else if(vx<0)
				{
					vx+=drag;
				}
				
				addMouseListener(new MouseAdapter() 
				{
					public void mouseClicked(MouseEvent e) 
					{
						mouseExplosion=true;
					}
				});
				
				// Countering weird slide
				if(vx<0.05 && vx>-0.05)vx=0;
				
				// Keypresses
				if(leftPressed)vx-=0.5;
				if(rightPressed)vx+=0.5;
				if(upPressed)vy-=2*gravity;
				
				vy+=gravity;
				y+=vy;
				sx=x+20;
				sy=y+20;
				
				timeRecord++;
				
				revalidate();
				repaint();
			}
		});
		
		timer.setRepeats(true);
		timer.setDelay(1000/60);
		timer.start();
	}
    
    public static Color randomColor()
    {
        int r=(int)(Math.random()*256);
        int g=(int)(Math.random()*256);
        int b=(int)(Math.random()*256);
        return new Color(r,g,b);
    }
    
    public Color foregroundColor()
    {
		double nr=255-r;
		double ng=255-g;
		double nb=255-b;
		
		return new Color((int)nr,(int)ng,(int)nb);
	}
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g; 
        g2.setStroke(new BasicStroke(3));
        g2.setColor(backgroundColor);   
        g2.fillRect(0, 0, winWidth, winHeight); 
        
        g2.setColor(foregroundColor());   
        super.paintComponent(g2);  
        g2.drawString("Position: " + (int)sx + ", " + (int)(winHeight-sy), 20,20);
        g2.drawString("Velocity: " + (int)vx + ", " + (int)vy, 20,40);
        g2.drawString("Color: " + (int)r + ", " + (int)r + ", " + (int)b, 20, 60);
        g2.drawString("Moon Time: " + (int)(timeRecord*30/1000), 20, 80);
        
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(0, winHeight-40, winWidth, winHeight); 
    
        g2.setColor(Color.CYAN);
        
        g2.drawRect((int)x,(int)y,40,40);
        
        g2.setColor(Color.RED);
        if(upPressed)g2.drawRect((int)x+10, (int)y+40, 20, 10);
        if(rightPressed)g2.drawRect((int)x-10, (int)y+10, 10, 20);
        if(leftPressed)g2.drawRect((int)x+40, (int)y+10, 10, 20);
        
        g2.setStroke(new BasicStroke(2));
        if(mouseExplosion)
        {
			g2.drawRect((int)mx-10,(int)(my-2.5),20,5);
			g2.drawRect((int)(mx-2.5),(int)my-10,5,20);
			g2.setColor(Color.ORANGE);
			g2.drawRect((int)mx-20,(int)my-5,40,10);
			g2.drawRect((int)mx-5,(int)my-20,10,40);
			g2.setColor(Color.YELLOW);
			g2.drawRect((int)mx-25,(int)my-10,50,20);
			g2.drawRect((int)mx-10,(int)my-25,20,50);
		}
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

    public void mouseMoved(MouseEvent e)
    {
		mx=e.getX();
        my=e.getY();
        mouseExplosion=false;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e)
    {
            if (e.getWheelRotation() < 0)
            {
                r++;
                g++;
                b++;
                
                if(r>255)r=255;
                if(g>255)g=255;
                if(b>255)b=255;
                
                backgroundColor = new Color((int)r,(int)g,(int)b);
            }
            else
            {
                r--;
                g--;
                b--;
                
                if(r<0)r=0;
                if(g<0)g=0;
                if(b<0)b=0;
                
                backgroundColor = new Color((int)r,(int)g,(int)b);
            }

    }
	
	public void keyTyped(KeyEvent e){}
	public void mouseDragged(MouseEvent e){}
}
