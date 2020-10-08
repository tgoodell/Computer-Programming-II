import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.Timer;
import javax.sound.midi.*;
import javax.swing.Timer;

public class ArrayDisplay extends JPanel
{
	
	ArrayList notes;
	ArrayList durations;
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
	Timer coolTimer;
	
	static
	{
		display=new ArrayDisplay();
	}
	
	public ArrayDisplay()
	{
		JFrame frame= new JFrame("The Best Sort Demo");   
        frame.add(this);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setFocusable(true);
        frame.setVisible(true);
        //superCoolTimer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	//~ public void superCoolTimer() 
    //~ {
		//~ coolTimer = new Timer(0, new ActionListener()
		//~ {
			//~ @Override
			//~ public void actionPerformed(ActionEvent e) 
			//~ {	
				//~ setBackground(Color.BLACK);
				
				
				//~ update();
			//~ }
		//~ });
		
		//~ coolTimer.setRepeats(true);
		//~ coolTimer.setDelay(1000/30);
		//~ coolTimer.start();
	//~ }
	
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g; 
		setBackground(Color.BLACK);
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,getWidth(),getHeight());
		g2.setStroke(new BasicStroke(stroke));
		int n=coolList.size();
		double columnWidth=getWidth()*1.0/n;
		double unitHeight=getHeight()*1.0/(n+1);
		
		for(int i=0;i<n;i++)
		{
			drawRect(g2,i,n,columnWidth,unitHeight);
		}
		
		//~ int[] realNotes=(int)notes.toString();
		//~ int[] realDurations=(int)durations.toString();
		//System.out.println(notes.toString());
		//play(realNotes,realDurations);
	}
	
	public void drawRect(Graphics2D g2, int i, int n, double columnWidth, double unitHeight)
	{
		if(i==getSwap1() || i==getSwap2())
		{
			g2.setColor(Color.RED);
		}
		else if(i==getComp1() || i==getComp2())
		{
			g2.setColor(Color.BLUE);
		}
		else g2.setColor(Color.WHITE);
		
		int left=(int)(i*columnWidth+7);
		int right=(int)((i+1)*columnWidth-2);
		int tall=(int)(unitHeight*coolList.get(i)+1);
		g2.drawRect(left+1,getHeight()-tall,right-left-2,tall);
	}
	
	public static void setBoxPosition(int x,int y)
	{
		display.x=x;
		display.y=y;
		update();
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
	
	public static void play(int[] notes, int[] durations)
    {
        try
        {
            Sequencer sequencer=MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence=new Sequence(Sequence.PPQ,4);
            Track t=sequence.createTrack();
            
            int time=1;
            for(int i=0;i<notes.length;i++)
            {
                if(notes[i]>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, notes[i], 127),time));
                if(notes[i]>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, notes[i]+7, 127),time));
                t.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, (int)(Math.random()*81+1),0),time));
                time+=durations[i];
                if(notes[i]>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, notes[i], 127),time));
                if(notes[i]>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, notes[i]+7, 127),time));
            }
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
    
    //~ public static void play(int note, int duration)
    //~ {
        //~ try
        //~ {
            //~ Sequencer sequencer=MidiSystem.getSequencer();
            //~ sequencer.open();
            //~ Sequence sequence=new Sequence(Sequence.PPQ,4);
            //~ Track t=sequence.createTrack();
            
            //~ int time=1;
            //~ if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, note, 127),time));
            //~ if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, note+7, 127),time));
            //~ t.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, (int)(Math.random()*81+1),0),time));
            //~ time=duration;
            //~ if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, note, 127),time));
            //~ if(note>0) t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, note+7, 127),time));
            
            //~ sequencer.setTempoInBPM(130);
            //~ sequencer.setSequence(sequence);
            //~ sequencer.start();
        //~ }
        
        //~ catch(Exception e)
        //~ {
            //~ e.printStackTrace();
            //~ return;
        //~ }
    //~ }
}

