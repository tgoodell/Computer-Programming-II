import java.awt.*; 
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 

public class guigumdrops
{
    
    
    public static void main(String[] args){
        JFrame frame= new JFrame("An Example");   
        MyCustomPanel panel=new MyCustomPanel(); 
        frame.add(panel);
        //frame.getContentPane().add(panel);
        //panel.addMouseMotionListener(panel);
        panel.setPreferredSize(new Dimension(600,400));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }
}

class MyCustomPanel extends JPanel implements MouseMotionListener
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
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(randomColor());
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(x, y, 200, 300);            
    }
    
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        x=e.getX();
        y=e.getY();
        repaint();
    }
    
}
