import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;

public class ArrayDisplay extends JPanel
{
	private static ArrayDisplay display;
	ArrayList coolList;
	int x=100;
	int y=100;
	int stroke=7;
	//int[] array;
	int swap1=-1;
	int swap2=-1;
	int comp1=-1;
	int comp2=-1;
	
	static
	{
		display=new ArrayDisplay();
	}
	
	public ArrayDisplay()
	{
		JFrame frame= new JFrame("The Best Game Demo");   
        frame.add(this);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g; 
		setBackground(Color.BLACK);
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,getWidth(),getHeight());
		g2.setStroke(new BasicStroke(stroke));
		int n=coolList.size();
		g2.setColor(Color.WHITE);
		double columnWidth=getWidth()*1.0/n;
		double unitHeight=getHeight()*1.0/(n+1);
		for(int i=0;i<n;i++)
		{
			if(i==getSwap1() || i==getSwap2())g2.setColor(Color.RED);
			else if(i==getComp1() || i==getComp2())g2.setColor(Color.BLUE);
			else g2.setColor(Color.WHITE);
			int left=(int)(i*columnWidth+7);
			int right=(int)((i+1)*columnWidth-2);
			int tall=(int)(unitHeight*coolList.get(i)+1);
			g2.drawRect(left+1,getHeight()-tall,right-left-2,tall);
		}
	}
	
	public static void setBoxPosition(int x,int y)
	{
		display.x=x;
		display.y=y;
		display.revalidate();
		display.repaint();
		wait(30);
	}
	
	public static void update() // ArrayDisplay.update();
	{
		wait(300);
		display.revalidate();
		display.repaint();
	}
	
	public static void setList(ArrayList list) //ArrayDisplay.setList(list);
	{
		display.coolList=list;
	}
	
	public static void wait(int s)
	{
		try
		{
			Thread.sleep(s);
		}
		catch(Exception e){}
	}
	
	public int getSwap1()
	{
		return coolList.getSwap1();
	}
	
	public int getSwap2()
	{
		return coolList.getSwap2();
	}
	
	public int getComp1()
	{
		return coolList.getComp1();
	}
	
	public int getComp2()
	{
		return coolList.getComp2();
	}
}

