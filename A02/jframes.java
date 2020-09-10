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
        frame.addMouseMotionListener(panel);
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
    }
   
}

class MyCustomPanel extends JPanel implements MouseMotionListener
{
	int x=0;
	int y=0;
	
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.RED);
		g.drawLine(x,y,200,300);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
	}
	
	public void mouseMoved(MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		repaint();
	}
}
