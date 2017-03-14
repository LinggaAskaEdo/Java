package itech.sample;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class JRadioButtonAction implements ActionListener 
{
	ButtonGroup myGroup = null;
	JLabel myLebal = null;
	
	public static void main(String[] a)
	{
		JRadioButtonAction myTest = new JRadioButtonAction();
		myTest.createFrame();
	}
	   
	public void createFrame()
	{
		JFrame f = new JFrame("My Radio Buttons");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
		myGroup = new ButtonGroup();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,1));
		addOption(p,myGroup,"Red");
		addOption(p,myGroup,"Green");
		addOption(p,myGroup,"Blue");
		c.add(p);
		JButton b = new JButton("Select");
		b.addActionListener(this);
		c.add(b);
		myLebal = new JLabel("Please select",SwingConstants.CENTER);
		c.add(myLebal);
		f.pack();
		f.setVisible(true);
	}
	   
	public void addOption(JPanel p, ButtonGroup g, String t) 
	{
		JRadioButton b = new JRadioButton(t);
		b.setActionCommand(t);
		p.add(b);
		g.add(b);
	}	
	   
	public void actionPerformed(ActionEvent e)
	{
		ButtonModel b = myGroup.getSelection();
		String t = "Not selected";
		if (b!=null) 
			t = b.getActionCommand();
		myLebal.setText(t);
	}	
}	
