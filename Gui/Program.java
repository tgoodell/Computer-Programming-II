import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Program
{
	public static void main(String[] args)
	{
		new ButtonClicker();
	}
}

class ButtonClicker implements ActionListener
{
	JLabel label;
	int count;
	
	public ButtonClicker()
	{
		JFrame frame= new JFrame("Example");
		JLabel label=new JLabel("asdf");
		JButton button=new JButton("click");
		JPanel panel=new JPanel();
		panel.add(label);
		panel.add(button);
		frame.add(panel);
		button.addActionListener(this);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		// Game Logic
		count=0;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		count++;
		label.setText("Count: " + count);
	}
}
