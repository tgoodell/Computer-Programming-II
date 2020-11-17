//*DOG CHASE CATS
//*CAT RUN FROM DOGS
//HUNGRY DOGS TO DIE
//CAT CAN BE KILLED BY DOGS
//CATS/DOGS MAKE BABIES

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Example
{
    public static void main(String[] args)
    {
        //~ Animal fido=new Dog();
        //~ Animal whiskers=new Cat();
        //~ fido.talk();
        //~ whiskers.talk();
        
        Environment e=new Environment();
        //~ for(int i=0;i<10;i++)e.add(new Actor());
        for(int i=0;i<10;i++)e.add(new Cat());
        for(int i=0;i<10;i++)e.add(new Dog());
        while(true)
        {
            e.step();
            try{Thread.sleep(33);}
            catch(Exception exp){}
            
        }
    }
    
}
//~ Prey
//~ Pred
//~ *x,y,direction
//~ *act
//~ *draw


class Environment
{
    private int catMaxPop=0;
    private int dogMaxPop=0;
    private ArrayList<Integer> historicPopulations=new ArrayList<Integer>();
    private ArrayList<Integer> dogHistoricPopulations=new ArrayList<Integer>();
    private ArrayList<Integer> catHistoricPopulations=new ArrayList<Integer>();
    private ArrayList<Actor> actors;
    private ArrayList<Dog> dogActors;
    private ArrayList<Cat> catActors;
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
        panel.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public Actor getClosestNeighbor(Actor subject, Actor targetType)
    {
        Actor nearest=null;
        double distance=1400;
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
		int dogCount=0;
		int catCount=0;
        frame++;
        for(Actor a:actors) 
        {
			a.act();
			if(a instanceof Dog)dogCount++;
			if(a instanceof Cat)catCount++;
		}
        for(Actor a:deadActors) actors.remove(a);
        for(Actor a:birthedActors) actors.add(a);
        dogHistoricPopulations.add(dogCount);
        catHistoricPopulations.add(catCount);
        deadActors.clear();
        birthedActors.clear();
        //~ dogActors.clear();
        //~ catActors.clear();
        update();
        if(catCount>catMaxPop)catMaxPop=catCount;
        if(dogCount>dogMaxPop)dogMaxPop=dogCount;
        historicPopulations.add(actors.size());
        
    }
    
    public void draw(Graphics2D g)
    {
        //System.out.println(actors.size());
        g.setColor(Color.BLACK);
        g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        
        drawCatChart(g);
        drawDogChart(g);
        
    }
    
    public void drawCatChart(Graphics2D g)
    {
		g.setColor(Color.WHITE);
        int n=catHistoricPopulations.size();
        int i=0;
        if(n-700>0) i=n-700;
        int x=0;
        g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        while(i<n-1)
        {
            int pop1=catHistoricPopulations.get(i);
            int pop2=catHistoricPopulations.get(i+1);
            x++;
            i++;

            g.drawLine(x,700-pop1*100/catMaxPop,x+1,700-pop2*100/catMaxPop);
        }
        for(Actor a:actors) a.draw(g);
	}
    
    public void drawDogChart(Graphics2D g)
    {
        g.setColor(Color.GRAY);
        int n=dogHistoricPopulations.size();
        int i=0;
        if(n-700>0) i=n-700;
        int x=0;
        g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        while(i<n-1)
        {
            int pop1=dogHistoricPopulations.get(i);
            int pop2=dogHistoricPopulations.get(i+1);
            x++;
            i++;

            g.drawLine(x,700-pop1*100/dogMaxPop,x+1,700-pop2*100/dogMaxPop);
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
        this.x=Math.random()*700;
        this.y=Math.random()*700;
    }
    
    public void setEnvironmnent(Environment env)
    {
        this.env=env;
    }
    
    protected void forward(double distance)
    {
        x+=distance*Math.cos(Math.PI/180*direction);
        if(x>700 || x<0)
        {
            x-=distance*Math.cos(Math.PI/180*direction);
            direction=180-direction;
        }
        y+=distance*Math.sin(Math.PI/180*direction);
        if(y>700 || y<0)
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

class Dog extends Actor
{
    public void act()
    {
        forward(2+4*Math.random());
        Actor cat=env.getClosestNeighbor(this,new Cat());
        if(cat!=null)
        {
            if(distance(cat)<3)
            {
                cat.die();
                hunger+=30;
                if(hunger>100)hunger=100;
                
            }
            else if(distance(cat)<60)
                direction=Math.atan2(cat.y-y,cat.x-x)/Math.PI*180.;
        }

        if(hunger<0)this.die();
        hunger-=0.499;
        direction+=Math.random()*20-10;
        
        if(Math.random()<.003 && hunger>25)
        {
            Dog baby=new Dog();
            baby.x=x;
            baby.y=y;
            env.add(baby);
        }
    }
    
    
    public void draw(Graphics2D g)
    {
        g.setColor(Color.YELLOW);
        int i=(int)(x-5);
        int j=(int)(y-5);
        g.fillRect(i,j,10,10);
        g.fillRect(i-2,j-2,4,2);
        g.fillRect(i+8,j-2,4,2);
        g.setColor(Color.BLACK);
        g.fillRect(i+2,j+2,2,2);
        g.fillRect(i+6,j+2,2,2);
        g.fillRect(i+3,j+6,4,2);
    }
    
}

//~ class Cat extends Actor
//~ {
    //~ Color c=new Color((int)(Math.random()*128),(int)(Math.random()*128),(int)(Math.random()*128));
    
    //~ public void act()
    //~ {
        //~ forward(3);
        //~ Actor dog=env.getClosestNeighbor(this,new Dog());
        //~ if(dog!=null)
        //~ {
            //~ if(distance(dog)<50)
                //~ direction=Math.atan2(dog.y-y,dog.x-x)/Math.PI*180+180;
        //~ }
        //~ direction+=Math.random()*20-10;
        
        //~ Actor cat=env.getClosestNeighbor(this,new Cat());
        
        //~ if(Math.random()<.03 && (cat==null || distance(cat)>30))
        //~ {
            //~ Cat baby=new Cat();
            //~ baby.x=x;
            //~ baby.y=y;
            //~ baby.c=c;
            //~ env.add(baby);
        //~ }
    //~ }
    
    //~ public void draw(Graphics2D g)
    //~ {
        //~ g.setColor(c);
        //~ int i=(int)(x-5);
        //~ int j=(int)(y-5);
        //~ g.fillRect(i,j,10,10);
        //~ g.fillRect(i,j-2,2,2);
        //~ g.fillRect(i+8,j-2,2,2);
        //~ g.fillRect(i,j+10,14,2);
        //~ g.setColor(Color.BLACK);
        //~ g.fillRect(i+2,j+2,2,2);
        //~ g.fillRect(i+6,j+2,2,2);
    //~ }
//~ }

class Cat extends Actor
{
    Color c=new Color((int)(Math.random()*128),(int)(Math.random()*128),(int)(Math.random()*128));
    
    public void act()
    {
        forward(3);
        Actor dog=env.getClosestNeighbor(this,new Dog());
        if(dog!=null)
        {
            if(distance(dog)<50)
                direction=Math.atan2(dog.y-y,dog.x-x)/Math.PI*180+180;
        }
        direction+=Math.random()*20-10;
        
        Actor cat=env.getClosestNeighbor(this,new Cat());
        
        if(Math.random()<.035 && (cat==null || distance(cat)>30))
        {
            Cat baby=new Cat();
            baby.x=x;
            baby.y=y;
            baby.c=c;
            env.add(baby);
        }
    }
    
    public void draw(Graphics2D g)
    {
        g.setColor(c);
        int i=(int)(x-5);
        int j=(int)(y-5);
        g.fillRect(i,j,10,15);
        
        g.fillRect(i-3,j-3,4,4);
        g.fillRect(i+9,j-3,4,4);
        
        g.setColor(Color.RED);
        g.fillRect(i+2,j+4,3,2);
        g.fillRect(i+6,j+4,3,2);
        
        g.setColor(Color.WHITE);
        g.fillRect(i+3,j+8,2,3);
        g.fillRect(i+7,j+8,2,3);
        //g.fillRect(i,j-2,2,2);

    }
}
