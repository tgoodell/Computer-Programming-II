import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Program
{
	public static void main(String[] args)
	{
		// Polymorphism - Animal Variable can act like any of its subtypes
		//~ Animal fido=new Dog();
		//~ Animal whiskers=new Cat();
		//~ fido.talk();
		//~ whiskers.talk();
		
		Actor bob=new Actor();
		Environment coolEnviro=new Environment();
		for(int i=0;i<10;i++)coolEnviro.add(new Actor());
		while(1<2)
		{
			coolEnviro.step();
			try{Thread.sleep(33);}
			catch(Exception f){}
		}
	}
}

class Environment
{
	private ArrayList<Actor> actors;
	JPanel panel;
	
	public Environment()
	{
		actors=new ArrayList<Actor>();
		JFrame frame=new JFrame("Example");
		JPanel panel =new JPanel() {
			public void paint(Graphics g) {
				draw((Graphics2D)g);
			}
		};
		frame.add(panel);
		panel.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void add(Actor a)
	{
		actors.add(a);
		a.setEnvironment(this);
	}
	
	public void step()
	{
		for(Actor a:actors) a.act();
		update();
	}
	
	public void draw(Graphics2D g)
	{
		for(Actor a:actors) a.draw(g);
	}
	
	public void update()
	{
		panel.repaint();
		panel.revalidate();
	}
	
	public Actor getClosestDifferentNeighbor(Actor subject, Actor targetType)
	{
		Actor nearest=null;
		double distance=1000;
		for(Actor a:actor)
		{
			if(a.getClass.equals(targetType.getClass()))
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
	
}

//~ Prey
//~ Predator
//~ *x,y,direction
//~ *act
//~ *draw

class Actor
{
	//instance variables
	private double x=0;
	private double y=0;
	private double direction=30;
	private Environment env=null;
	
	//constructor
	public Actor(double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double setX()
	{
		this.x=x;
		return this.x;
	}
	
	private void forward(double distance)
	{
		x+=distance*Math.cos(Math.PI*direction/180);
		y+=distance*Math.sin(Math.PI*direction/180);
	}
	
	public Actor()
	{
		this.x=Math.random()*500;
		this.y=Math.random()*500;
	}
	
	public void setEnvironment(Environment env)
	{
		
	}
	
	//methods
	
	public void act()
	{
		forward(0.001);
		direction+=Math.random()*10-5;
	}
	
	public void draw(Graphics2D g)
	{
		g.fillRect((int)(x-5),(int)(y-5),10,10);
	}
	
	public double distance(Actor other)
	{
		return Math.hypot(other.x-this.x,other.y-this.y);
	}
}

abstract class Animal
{
	public abstract void talk();
}

class Dog extends Animal
{
	public void talk()
	{
		System.out.println("Woof");
	}
}

class Cat extends Animal
{
	public void talk()
	{
		System.out.println("Meow");
	}
}

//~ abstract class PartialClass // Abstract = Not everything is filled in
//~ {
	
	
	
	//~ public abstract void func();
//~ }
