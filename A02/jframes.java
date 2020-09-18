import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class jframes extends JPanel
{	
    public static void main(String[] args)
    {
        JFrame frame= new JFrame("Welcome to this cool Windows!");
        MyCustomPanel panel=new MyCustomPanel();
        frame.getContentPane().add(new MyCustomPanel());
        panel.addMouseMotionListener(panel);
        frame.addKeyListener(panel);
        frame.setSize(600,400);
        frame.setVisible(true);
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
    }
   
}

class MyCustomPanel extends JPanel implements MouseMotionListener, KeyListener
{
	int x=0;
	int y=0;
	
	public static Color randomColor()
    {
        int r=(int)(Math.random()*256);
        int g=(int)(Math.random()*256);
        int b=(int)(Math.random()*256);
        return new Color(r,g,b);
    }
	
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.RED);
		g.drawLine(x,y,200,300);
	}
	
	public void mouseDragged(MouseEvent e)
	{}
	
	public void mouseMoved(MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		repaint();
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT)x-=10;
		if(keyCode==KeyEvent.VK_RIGHT)x+=10;
		if(keyCode==KeyEvent.VK_UP)y-=10;
		if(keyCode==KeyEvent.VK_DOWN)y+=10;
		repaint();
	}
	public void keyTyped(KeyEvent e){}
}
