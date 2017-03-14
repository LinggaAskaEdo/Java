package itech.sample;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DetermineWhenAJframeIsOpenOrClose 
{
	JFrame frame;
	
	JFrame a = new JFrame("OPEN A JFRAME WITH TITLE'S MONSTER");
	JButton b = new JButton("CLICK HERE TO OPEN");

	public DetermineWhenAJframeIsOpenOrClose()
	{
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame = new JFrame("MONSTER");
				frame.setSize(300, 500);
				frame.setVisible(true);
				
				frame.addWindowListener(new WindowAdapter()
				{
					public void windowOpened(final WindowEvent evt)
					{
						JOptionPane.showMessageDialog(null, "MONSTER IS OPEN");
						System.out.println("MONSTER IS OPEN");
					}
					
					public void windowClosing(WindowEvent evt)
					{
						Frame temp = (Frame) evt.getSource();
						temp.dispose();
						JOptionPane.showMessageDialog(null, "MONSTER IS CLOSE");
						System.out.println("MONSTER IS CLOSE");
					}
				});
			}
		});
		
		a.add(b);
		
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setSize(500, 100);
		a.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new DetermineWhenAJframeIsOpenOrClose();
	}
}
