import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.*;

import javax.sound.midi.*;

public class Program
{
    public static void play(String music)
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    Synthesizer synth = MidiSystem.getSynthesizer();
                    synth.open();

                    MidiChannel[] channels = synth.getChannels();
                    MidiChannel channel = channels[0];
                    
                    for(String note: music.split(" "))
                    {        
                        int v=12+"C_D_EF_GA_B".indexOf(note.substring(0,1));
                        if(note.charAt(1)=='#')v+=1;
                        else if(note.charAt(1)=='b')v-=1;
                        v+=12*Integer.parseInt(note.substring(2,3));
                        
                        
                        channel.noteOn(v, 127);
                        try{Thread.sleep(400);}catch(Exception e){}
                        channel.noteOff(v, 127);
                        
                        //~ try{Thread.sleep(10);}catch(Exception e){}
                    }
                    synth.close();
                }
                catch(MidiUnavailableException e)
                {
                    System.out.println("midi unavailable");
                }
            }
        };
        
        thread.start();
    
        
        
    }
    
    public static void main(String[] args)
    {
        
        play("C_4 C_4 D_4 E_4 C_4 E_4 D_4");

        
        
        
        
        JFrame frame= new JFrame("Example");   
        MyCustomPanel panel=new MyCustomPanel(); 
        frame.add(panel);
        panel.addMouseMotionListener(panel);
        frame.addKeyListener(panel);
        panel.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        play("C_4 C_4 D_4 E_4 C_4 E_4 D_4");
        //~ for(int i=0;;i++)System.out.println(i); 
        
        javax.swing.Timer t=new javax.swing.Timer(35,panel);
        t.start();
        
  
    }
}

class MyCustomPanel extends JPanel implements MouseMotionListener, KeyListener, ActionListener
{
    int x=100;
    int y=100;
    int mouseX=0;
    int mouseY=0;
    boolean active=false;
    HashSet<Integer> keyMap=new HashSet<Integer>();
    boolean leftPressed=false;
    boolean rightPressed=false;
    int frameCount=0;
    //~ boolean leftPressed=false;
    //~ boolean leftPressed=false;
    
    public static Color randomColor()
    {
        int r=(int)(Math.random()*256);
        int g=(int)(Math.random()*256);
        int b=(int)(Math.random()*256);
        return new Color(r,g,b);
    }
    public void update()
    {
        if(leftPressed)x-=3;
        //~ if(keyMap.has(leftKeyCode))x-=3;
        if(rightPressed)x+=3;
        repaint();
        frameCount++;
    }
    
    public void paint(Graphics g)
    {
        if(mouseX>=x && mouseX<x+50 && mouseY>=y && mouseY<y+50 )
        {
            if(!active)Program.play("C_4 C_4 D_4 E_4 C_4 E_4 D_4");
            active=true;
        }
        else active=false; 
        
        
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight()); 
        if(active) g2.setColor(Color.RED);
        else g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(10));
        int xp=(int)(x+25*Math.sin(2*Math.PI*frameCount/15));
        int yp=(int)(y+25*Math.sin(2*Math.PI*frameCount/11));
        
        g2.fillRect(xp,yp, 50, 50);            
    }
    
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        mouseX=e.getX();
        mouseY=e.getY();
        
        repaint();
    }
    
    public void keyReleased(KeyEvent e)
    {
        int keyCode=e.getKeyCode();
        //~ keyMap.remove(keyCode);
        if(keyCode==KeyEvent.VK_LEFT)leftPressed=false;
        else if(keyCode==KeyEvent.VK_RIGHT)rightPressed=false;
    }
    public void keyPressed(KeyEvent e)
    {
        int keyCode=e.getKeyCode();
        //~ keyMap.add(keyCode)
        if(keyCode==KeyEvent.VK_LEFT)leftPressed=true;
        else if(keyCode==KeyEvent.VK_RIGHT)rightPressed=true;
        else if(keyCode==KeyEvent.VK_UP)y-=10;
        else if(keyCode==KeyEvent.VK_DOWN)y+=10;
        
        if(keyCode==KeyEvent.VK_R)active=true;
        if(keyCode==KeyEvent.VK_B)active=false;
        repaint();
    }
    public void keyTyped(KeyEvent e){}
    
    public void actionPerformed(ActionEvent e) 
    {
        update();
    }
    
}
