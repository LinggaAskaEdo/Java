package itech.sample;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SampleShowImage 
{
	public static void main(String[] args) 
	{
		Image image = null;
		Image scaledImage = null;
		
		try
		{		
		    image = ImageIO.read(new File("test.jpg"));
		    scaledImage = image.getScaledInstance(270, 370, Image.SCALE_DEFAULT); 
		}
		catch (IOException e)
		{}

		// Use a label to display the image
		JFrame frame = new JFrame();

		JLabel lblimage = new JLabel(new ImageIcon(scaledImage));
		frame.getContentPane().add(lblimage, BorderLayout.CENTER);
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}
