import java.awt.*; 
import java.awt.geom.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.*;
import javax.sound.midi.*;

public class ArrayDisplay extends JPanel 
{
    private static ArrayDisplay display;
    ArrayList coolList;
    Color tomato = new Color(255,99,71);
    int[] notes=new int[10000];
    int[] durations=new int[10000];
    Thread one=new Thread();
    int dni=0;
    int x=0;
    
    static
    {
        display=new ArrayDisplay();
    }
    
    private ArrayDisplay()
    {
        JFrame frame= new JFrame("The Best Sort Demo");   
        frame.add(this);
        this.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        int n=coolList.size();
        double columnWidth=getWidth()*1.0/n;
        double unitHeight=getHeight()*1.0/(n+1);
        
        for(int i=0;i<n;i++)
        {
            drawRect(g2,i,n,columnWidth,unitHeight);
            drawInfo(g2);
        }
        
        wait(30);
    }
    
    public void drawRect(Graphics2D g2, int i, int n, double columnWidth, double unitHeight)
	{
				
		if(coolList.swap1==i || coolList.swap2==i)
		{
			g2.setColor(tomato);
			makeNoise();
		}
		else if(coolList.compare1==i || coolList.compare2==i)
		{
			g2.setColor(Color.CYAN);
			makeNoise();
		}
		else g2.setColor(Color.WHITE);
		
		g2.setStroke(new BasicStroke(5));
		int left=(int)(i*columnWidth+7);
		int right=(int)((i+1)*columnWidth-2);
		int tall=(int)(unitHeight*coolList.get(i)+1);
		g2.draw(new RoundRectangle2D.Double(left+1,getHeight()-tall,right-left-2,tall+columnWidth, columnWidth/5, columnWidth/5));

	}
	
	public void drawInfo(Graphics g2)
	{
		g2.setColor(Color.CYAN);
		g2.drawString("Compares: " + (int)coolList.compareCount, 20, 20);
		g2.setColor(tomato);
		g2.drawString("Swaps: " + (int)coolList.swapCount, 20, 40);
		g2.setColor(Color.WHITE);
		g2.drawString("Time: " + coolList.getTime()/10000000, 20, 60);
	}
    
    public static void update() //ArrayDisplay.update();
    {
		wait(35);
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
    
    public void makeNoise()
    {
		one=new Thread() 
		{
			public void run()
			{
				try {
					play(62,4);
					wait(30);
				} 
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		};
		one.start();
	}
    
    public static void play(int note, int duration)
    {
        try
        {
            Sequencer sequencer=MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence=new Sequence(Sequence.PPQ,4);
            Track t=sequence.createTrack();
            
            int time=1;
            if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, note, 127),time));
            if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, note+7, 127),time));
            t.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, (int)(Math.random()*81+1),0),time));
            time+=duration;
            if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, note, 127),time));
            if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, note+7, 127),time));
            
            sequencer.setTempoInBPM(130);
            sequencer.setSequence(sequence);
            sequencer.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
    
}

