package itech.form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamTakePict extends JFrame implements Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;	
	public JLabel label;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new WebcamTakePict());
	}

	public WebcamTakePict()
	{
		addWindowListener(new WindowAdapter() 
		{			
			public void windowClosing(WindowEvent e)
			{
				//System.out.println("aaa");				
				webcam.close();
			}
		});
	}
	
	public void run()
	{		
		Webcam.addDiscoveryListener(this);
		
		setTitle("Webcam Take Picture");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 510, 340);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		picker = new WebcamPicker();
		picker.addItemListener(this);
		
		webcam = picker.getSelectedWebcam();

		if (webcam == null)
		{
			System.out.println("No webcams found...");
			System.exit(1);
		}
		
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.addWebcamListener(WebcamTakePict.this);			
		
		picker.setBounds(10, 1, 481, 20);
		contentPane.add(picker);
		
		panel = new WebcamPanel(webcam, false);
		panel.setFPSDisplayed(true);
		panel.setBounds(10, 23, 481, 244);
		contentPane.add(panel);		
		
		JButton btnNewButton = new JButton("Take Photo !!!");
		btnNewButton.setBounds(368, 275, 123, 23);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		label = new JLabel("Done !!!");
		label.setBounds(15, 275, 95, 35);
		label.setVisible(false);
		contentPane.add(label);
		
		setVisible(true);
		
		Thread t = new Thread()
		{
			
			public void run() 
			{
				panel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
	}

	public void webcamFound(WebcamDiscoveryEvent event) 
	{
		if (picker != null)
		{
			event.getWebcam();
		}		
	}

	public void webcamGone(WebcamDiscoveryEvent event)
	{
		if (picker != null) 
		{
			picker.removeItem(event.getWebcam());
		}		
	}

	public void itemStateChanged(ItemEvent e) 
	{
		if (e.getItem() != webcam)
		{
			if (webcam != null)
			{
				panel.stop();

				remove(panel);

				webcam.removeWebcamListener(this);
				webcam.close();

				webcam = (Webcam) e.getItem();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
				webcam.addWebcamListener(this);

				System.out.println("selected " + webcam.getName());

				panel = new WebcamPanel(webcam, false);
				panel.setFPSDisplayed(true);

				getContentPane().add(panel, BorderLayout.CENTER);
				pack();

				Thread t = new Thread()
				{
					public void run() 
					{
						panel.start();
					}
				};
				t.setName("example-stoper");
				t.setDaemon(true);
				t.setUncaughtExceptionHandler(this);
				t.start();
			}
		}
	}

	public void uncaughtException(Thread t, Throwable e)
	{
		//System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();		
	}

	public void windowOpened(WindowEvent e)
	{}

	public void windowClosing(WindowEvent e)
	{
		webcam.close();
	}

	public void windowClosed(WindowEvent e)
	{
		webcam.close();				
	}

	public void windowIconified(WindowEvent e)
	{
		System.out.println("webcam viewer paused");
		panel.pause();		
	}

	public void windowDeiconified(WindowEvent e)
	{
		System.out.println("webcam viewer resumed");
		panel.resume();		
	}

	public void windowActivated(WindowEvent e)
	{}

	public void windowDeactivated(WindowEvent e)
	{
		webcam.close();
	}

	public void webcamClosed(WebcamEvent arg0) 
	{
		System.out.println("webcam closed");		
	}

	public void webcamDisposed(WebcamEvent arg0)
	{
		System.out.println("webcam disposed");		
	}

	public void webcamImageObtained(WebcamEvent arg0)
	{}

	public void webcamOpen(WebcamEvent arg0)
	{
		System.out.println("webcam open");		
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("CRIT");		
		BufferedImage image = webcam.getImage();

		// save image to JPG file
		try
		{
			ImageIO.write(image, "PNG", new File("test.jpg"));		
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		System.out.println("CROT");	
		label.setVisible(true);
	}	
}
