import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        Environment e=new Environment();

        for(int i=0;i<10;i++)e.add(new Beaver());
        for(int i=0;i<10;i++)e.add(new Wolverine());
        while(true)
        {
            e.step();
            try{Thread.sleep(33);}
            catch(Exception exp){}
            
        }
    }
    
}

class Environment
{
    private int beaverMaxPop=0;
    private int wolverineMaxPop=0;
    private ArrayList<Integer> historicPopulations=new ArrayList<Integer>();
    private ArrayList<Integer> wolverineHistoricPopulations=new ArrayList<Integer>();
    private ArrayList<Integer> beaverHistoricPopulations=new ArrayList<Integer>();
    private ArrayList<Actor> actors;
    private ArrayList<Wolverine> wolverineActors;
    private ArrayList<Beaver> beaverActors;
    private ArrayList<Actor> deadActors=new ArrayList<Actor>();
    private ArrayList<Actor> birthedActors=new ArrayList<Actor>();
    JPanel panel;
    int frame=0;
    
    public Environment()
    {
        actors=new ArrayList<Actor>();
        JFrame frame= new JFrame("Example");   
        panel=new JPanel()
        {
            public void paint(Graphics g)
            {
                draw((Graphics2D)g);
            }
        }; 
        frame.add(panel);
        panel.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public Actor getClosestNeighbor(Actor subject, Actor targetType)
    {
        Actor nearest=null;
        double distance=1000;
        for(Actor a:actors)
        {
            if(a!=subject && a.getClass().equals(targetType.getClass()))
            {
                double d=a.distance(subject);
                if(d<distance)
                {
                    nearest=a;
                    distance=d;
                }
            }
        }
        return nearest;
    }
    
    public void add(Actor a)
    {
        birthedActors.add(a);
        a.setEnvironmnent(this);
        
    }
    
    public void remove(Actor a)
    {
        deadActors.add(a);
        
    }
    
    public void step()
    {
		int wolverineCount=0;
		int beaverCount=0;
        frame++;
        for(Actor a:actors) 
        {
			a.act();
			if(a instanceof Wolverine)wolverineCount++;
			if(a instanceof Beaver)beaverCount++;
		}
        for(Actor a:deadActors) actors.remove(a);
        for(Actor a:birthedActors) actors.add(a);
        wolverineHistoricPopulations.add(wolverineCount);
        beaverHistoricPopulations.add(beaverCount);
        deadActors.clear();
        birthedActors.clear();
        update();
        if(beaverCount>beaverMaxPop)beaverMaxPop=beaverCount;
        if(wolverineCount>wolverineMaxPop)wolverineMaxPop=wolverineCount;
        historicPopulations.add(actors.size());
        
    }
    
    public void draw(Graphics2D g)
    {
        Color bgblue=new Color(156,192,231);
        g.setColor(bgblue);
        g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        
        drawBeaverChart(g);
        drawWolverineChart(g);
        
    }
    
    public void drawBeaverChart(Graphics2D g)
    {
		g.setColor(Color.WHITE);
        int n=beaverHistoricPopulations.size();
        int i=0;
        if(n-500>0) i=n-500;
        int x=0;
        g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        while(i<n-1)
        {
            int pop1=beaverHistoricPopulations.get(i);
            int pop2=beaverHistoricPopulations.get(i+1);
            x++;
            i++;

            g.drawLine(x,500-pop1*100/beaverMaxPop,x+1,500-pop2*100/beaverMaxPop);
        }
        for(Actor a:actors) a.draw(g);
	}
    
    public void drawWolverineChart(Graphics2D g)
    {
        g.setColor(Color.ORANGE);
        int n=wolverineHistoricPopulations.size();
        int i=0;
        if(n-500>0) i=n-500;
        int x=0;
        g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        while(i<n-1)
        {
            int pop1=wolverineHistoricPopulations.get(i);
            int pop2=wolverineHistoricPopulations.get(i+1);
            x++;
            i++;

            g.drawLine(x,500-pop1*100/wolverineMaxPop,x+1,500-pop2*100/wolverineMaxPop);
        }
        
        for(Actor a:actors) a.draw(g);
	}
    
    public void update()
    {
        panel.repaint();
    }
}

class Actor
{
    //instance variables
    protected double hunger=100;
    protected double x=0;
    protected double y=0;
    protected double direction=30;
    protected Environment env=null;
    
    
    //constructor
    public Actor(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    
    public Actor()
    {
        this.x=Math.random()*500;
        this.y=Math.random()*500;
    }
    
    public void setEnvironmnent(Environment env)
    {
        this.env=env;
    }
    
    protected void forward(double distance)
    {
        x+=distance*Math.cos(Math.PI/180*direction);
        if(x>500 || x<0)
        {
            x-=distance*Math.cos(Math.PI/180*direction);
            direction=180-direction;
        }
        y+=distance*Math.sin(Math.PI/180*direction);
        if(y>500 || y<0)
        {
            y-=distance*Math.sin(Math.PI/180*direction);
            direction=-direction;
        }
    }
    
    //methods
    public void act()
    {
        forward(4);
        direction+=Math.random()*10-5;
        
    }
    
    public void die()
    {
        env.remove(this);
    }
    
    public void draw(Graphics2D g)
    {
        g.setColor(Color.GREEN);
        g.fillRect((int)(x-5),(int)(y-5),10,10);
    }
    
    public double distance(Actor other)
    {
        return Math.hypot(other.x-this.x,other.y-this.y);
    }
    
}

class Wolverine extends Actor
{
    public void act()
    {
        forward(2+4*Math.random());
        
        if(hunger<=70)
        {
			Actor beaver=env.getClosestNeighbor(this,new Beaver());
			if(beaver!=null)
			{
				if(distance(beaver)<3)
				{
					beaver.die();
					hunger+=30;
					if(hunger>100)hunger=100;
					
				}
				else if(distance(beaver)<60)
					direction=Math.atan2(beaver.y-y,beaver.x-x)/Math.PI*180.;
			}
		}

        if(hunger<0)this.die();
        hunger-=0.490;
        direction+=Math.random()*20-10;
        
        if(Math.random()<.0028 && hunger>30)
        {
            Wolverine baby=new Wolverine();
            baby.x=x;
            baby.y=y;
            env.add(baby);
        }
    }
    
    
    public void draw(Graphics2D g)
    {
        Color neorange=new Color(216,117,61);
        g.setColor(neorange);
        int i=(int)(x);
        int j=(int)(y);
        g.fillRect(i,j,2,2);
        g.fillRect(i,j+2,8,3);
        g.fillRect(i,j+5,2,1);
        g.fillRect(i+6,j,1,1);
        g.fillRect(i+6,j+1,2,1);
        
        g.setColor(Color.BLACK);
        g.fillRect(i+2,j+5,5,1);
        g.fillRect(i+5,j+7,1,1);
        
        Color neobrown=new Color(36,6,0);
        g.setColor(neobrown);
        g.fillRect(i+7,j,1,1);
        g.fillRect(i,j+6,8,2);
        g.fillRect(i+2,j+8,4,2);
        g.fillRect(i+5,j+5,1,1);
        g.fillRect(i+5,j+5,1,1);
        g.fillRect(i+3,j+9,1,2);
        g.fillRect(i+5,j+9,1,2);
    }
    
}

class Beaver extends Actor
{
    Color c=new Color((int)(Math.random()*128),(int)(Math.random()*128),(int)(Math.random()*128));
    
    public void act()
    {
        forward(3);
        Actor wolverine=env.getClosestNeighbor(this,new Wolverine());
        if(wolverine!=null)
        {
            if(distance(wolverine)<50)
                direction=Math.atan2(wolverine.y-y,wolverine.x-x)/Math.PI*180+180;
        }
        direction+=Math.random()*20-10;
        
        Actor beaver=env.getClosestNeighbor(this,new Beaver());
        
        if(Math.random()<.05 && (beaver==null || distance(beaver)>20))
        {
            Beaver baby=new Beaver();
            baby.x=x;
            baby.y=y;
            env.add(baby);
        }
    }
    
    public void draw(Graphics2D g)
    {
		Color neobrown=new Color(49,30,16);
        g.setColor(neobrown);
        int i=(int)(x);
        int j=(int)(y);
        g.fillRect(i+1,j,5,5);
        g.fillRect(i,j+1,1,1);
        g.fillRect(i+1,j+5,3,1);
        g.fillRect(i+4,j+5,3,1);
        g.fillRect(i+6,j+1,1,1);
        
        Color neogrey=new Color(93,99,99);
        g.setColor(neogrey);
        g.fillRect(i+2,j+2,3,2);
        
        g.setColor(Color.WHITE);
        g.fillRect(i+1,j+1,1,1);
        g.fillRect(i+5,j+1,1,1);
        g.fillRect(i+3,j+4,1,1);
        
        g.setColor(Color.BLACK);
        g.fillRect(i+2,j+1,1,1);
        g.fillRect(i+4,j+1,1,1);
        g.fillRect(i+1,j+6,2,1);
        g.fillRect(i+4,j+6,2,1);
        
        g.setColor(neobrown);
        g.fillRect(i+3,j+2,1,1);

    }
}
