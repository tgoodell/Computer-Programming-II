import java.awt.*; 
import java.awt.geom.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.*;


public class ArrayDisplay extends JPanel 
{
    private static ArrayDisplay display;
    ArrayList list;
    
    static
    {
        display=new ArrayDisplay();
    }
    
    private ArrayDisplay()
    {
        JFrame frame= new JFrame("Example");   
        frame.add(this);
        this.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());
        int n=list.size();
        double columnWidth=getWidth()*1.0/n;
        double unitHeight=getHeight()*1.0/(n+1);
        
        for(int i=0;i<n;i++)
        {
            int left=(int)(i*columnWidth);
            int right=(int)((i+1)*columnWidth);
            int tall=(int)(unitHeight*(list.get(i)+1));
            //~ g.fillRect(l);
            if(list.swap1==i || list.swap2==i)g.setColor(Color.RED);
            else if(list.compare1==i || list.compare2==i)g.setColor(Color.BLUE);
            else g.setColor(Color.BLACK);
            g2.fill(new RoundRectangle2D.Double(left+1,getHeight()-tall,right-left-2,tall+columnWidth, columnWidth/5, columnWidth/5));
        }
        
        
    }
    
    public static void update() //ArrayDisplay.update();
    {
        wait(35);
        display.repaint();
    }
    public static void setList(ArrayList list) //ArrayDisplay.setList(list);
    {
        display.list=list;
    }

    
    public static void wait(int s)
    {
        try
        {
            Thread.sleep(s);
        }
        catch(Exception e){}
    }
    
    

    
    
}
