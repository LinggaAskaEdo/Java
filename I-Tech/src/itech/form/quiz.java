package itech.form;

import itech.funct.TempPendaftaran;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class quiz extends JFrame
{	
	private static final long serialVersionUID = 4039989026569437968L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public ButtonGroup group, group2, group3, group4, group5 = null;
	public JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3;
	public JRadioButton rdbtnNewRadioButton_4, rdbtnNewRadioButton_5, rdbtnNewRadioButton_6, rdbtnNewRadioButton_7;
	public JRadioButton rdbtnNewRadioButton_8, rdbtnNewRadioButton_9, rdbtnNewRadioButton_10, rdbtnNewRadioButton_11;
	public JRadioButton rdbtnNewRadioButton_12, rdbtnNewRadioButton_13, rdbtnNewRadioButton_14, rdbtnNewRadioButton_15;	
	public JRadioButton rdbtnNewRadioButton_16, rdbtnNewRadioButton_17, rdbtnNewRadioButton_18, rdbtnNewRadioButton_19;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try
				{
					quiz frame = new quiz();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}	
	
	public quiz() 
	{
		addWindowListener(new WindowAdapter() 
		{
			public void windowActivated(WindowEvent e) 
			{			
				File file = new File("qq0.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qq0.dat", 1);				
					if (n1.equals("rdbtnNewRadioButton"))
						rdbtnNewRadioButton.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_1"))
						rdbtnNewRadioButton_1.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_2"))
						rdbtnNewRadioButton_2.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_3"))
						rdbtnNewRadioButton_3.setSelected(true);
					
					String n2 = tp.getSpecificFile("qq0.dat", 2);				
					if (n2.equals("rdbtnNewRadioButton_4"))
						rdbtnNewRadioButton_4.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_5"))
						rdbtnNewRadioButton_5.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_6"))
						rdbtnNewRadioButton_6.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_7"))
						rdbtnNewRadioButton_7.setSelected(true);
					
					String n3 = tp.getSpecificFile("qq0.dat", 3);				
					if (n3.equals("rdbtnNewRadioButton_8"))
						rdbtnNewRadioButton_8.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_9"))
						rdbtnNewRadioButton_9.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_10"))
						rdbtnNewRadioButton_10.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_11"))
						rdbtnNewRadioButton_11.setSelected(true);
					
					String n4 = tp.getSpecificFile("qq0.dat", 4);				
					if (n4.equals("rdbtnNewRadioButton_12"))
						rdbtnNewRadioButton_12.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_13"))
						rdbtnNewRadioButton_13.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_14"))
						rdbtnNewRadioButton_14.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_15"))
						rdbtnNewRadioButton_15.setSelected(true);
					
					String n5 = tp.getSpecificFile("qq0.dat", 5);				
					if (n5.equals("rdbtnNewRadioButton_16"))
						rdbtnNewRadioButton_16.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_17"))
						rdbtnNewRadioButton_17.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_18"))
						rdbtnNewRadioButton_18.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_19"))
						rdbtnNewRadioButton_19.setSelected(true);
				}				
			}
		});
		setResizable(false);
		setTitle("Soal");
		initializeForm();
		centerForm();		
	}
	
	private void centerForm()
	{			
		this.setLocationRelativeTo(getRootPane());
	}
	
	private void initializeForm()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1327, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("7. Mr. postman \u2026 the mail 2 days ago");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 279, 14);
		contentPane.add(lblNewLabel);
		
		rdbtnNewRadioButton = new JRadioButton("Deliver");
		rdbtnNewRadioButton.setBounds(20, 29, 109, 23);
		rdbtnNewRadioButton.setActionCommand("rdbtnNewRadioButton");
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Delivers");
		rdbtnNewRadioButton_1.setBounds(20, 55, 109, 23);
		rdbtnNewRadioButton_1.setActionCommand("rdbtnNewRadioButton_1");
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Delivery");
		rdbtnNewRadioButton_2.setBounds(273, 32, 109, 23);
		rdbtnNewRadioButton_2.setActionCommand("rdbtnNewRadioButton_2");
		contentPane.add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Delivered");
		rdbtnNewRadioButton_3.setBounds(273, 55, 109, 23);
		rdbtnNewRadioButton_3.setActionCommand("rdbtnNewRadioButton_3");
		contentPane.add(rdbtnNewRadioButton_3);
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);	
		
		JLabel lblNewLabel_1 = new JLabel("8. \u201CI said to the waitress, \u201CThis bill is wrong\u201D");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 96, 312, 14);
		contentPane.add(lblNewLabel_1);
		
		rdbtnNewRadioButton_4 = new JRadioButton("I telling the waitress this bill is wrong");
		rdbtnNewRadioButton_4.setBounds(20, 117, 235, 23);
		rdbtnNewRadioButton_4.setActionCommand("rdbtnNewRadioButton_4");
		contentPane.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("I told the waitress this bill is wrong");
		rdbtnNewRadioButton_5.setBounds(20, 143, 243, 23);
		rdbtnNewRadioButton_5.setActionCommand("rdbtnNewRadioButton_5");
		contentPane.add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("I'm telling the waitress bill wrong");
		rdbtnNewRadioButton_6.setBounds(273, 117, 230, 23);
		rdbtnNewRadioButton_6.setActionCommand("rdbtnNewRadioButton_6");
		contentPane.add(rdbtnNewRadioButton_6);
		
		rdbtnNewRadioButton_7 = new JRadioButton("Don't know");
		rdbtnNewRadioButton_7.setBounds(273, 143, 109, 23);
		rdbtnNewRadioButton_7.setActionCommand("rdbtnNewRadioButton_7");
		contentPane.add(rdbtnNewRadioButton_7);
		
		group2 = new ButtonGroup();
		group2.add(rdbtnNewRadioButton_4);
		group2.add(rdbtnNewRadioButton_5);
		group2.add(rdbtnNewRadioButton_6);
		group2.add(rdbtnNewRadioButton_7);	
		
		JLabel lblNewLabel_2 = new JLabel("9. The plane \u2026 landing at the airport in five minutes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 184, 362, 14);
		contentPane.add(lblNewLabel_2);
		
		rdbtnNewRadioButton_8 = new JRadioButton("it is");
		rdbtnNewRadioButton_8.setBounds(20, 205, 109, 23);
		rdbtnNewRadioButton_8.setActionCommand("rdbtnNewRadioButton_8");
		contentPane.add(rdbtnNewRadioButton_8);
		
		rdbtnNewRadioButton_9 = new JRadioButton("it really is");
		rdbtnNewRadioButton_9.setBounds(20, 226, 109, 23);
		rdbtnNewRadioButton_9.setActionCommand("rdbtnNewRadioButton_9");
		contentPane.add(rdbtnNewRadioButton_9);
		
		rdbtnNewRadioButton_10 = new JRadioButton("is descending");
		rdbtnNewRadioButton_10.setBounds(273, 205, 109, 23);
		rdbtnNewRadioButton_10.setActionCommand("rdbtnNewRadioButton_10");
		contentPane.add(rdbtnNewRadioButton_10);
		
		rdbtnNewRadioButton_11 = new JRadioButton("will be");
		rdbtnNewRadioButton_11.setBounds(273, 226, 109, 23);
		rdbtnNewRadioButton_11.setActionCommand("rdbtnNewRadioButton_11");
		contentPane.add(rdbtnNewRadioButton_11);
		
		group3 = new ButtonGroup();
		group3.add(rdbtnNewRadioButton_8);
		group3.add(rdbtnNewRadioButton_9);
		group3.add(rdbtnNewRadioButton_10);
		group3.add(rdbtnNewRadioButton_11);	
		
		JLabel lblNewLabel_3 = new JLabel("10. \u2026 this blog on july 14th 2009");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(742, 11, 266, 14);
		contentPane.add(lblNewLabel_3);
		
		rdbtnNewRadioButton_12 = new JRadioButton("launch");
		rdbtnNewRadioButton_12.setBounds(742, 32, 109, 23);
		rdbtnNewRadioButton_12.setActionCommand("rdbtnNewRadioButton_12");
		contentPane.add(rdbtnNewRadioButton_12);
		
		rdbtnNewRadioButton_13 = new JRadioButton("launching");
		rdbtnNewRadioButton_13.setBounds(742, 55, 109, 23);
		rdbtnNewRadioButton_13.setActionCommand("rdbtnNewRadioButton_13");
		contentPane.add(rdbtnNewRadioButton_13);
		
		rdbtnNewRadioButton_14 = new JRadioButton("launched");
		rdbtnNewRadioButton_14.setBounds(1005, 32, 109, 23);
		rdbtnNewRadioButton_14.setActionCommand("rdbtnNewRadioButton_14");
		contentPane.add(rdbtnNewRadioButton_14);
		
		rdbtnNewRadioButton_15 = new JRadioButton("will launching");
		rdbtnNewRadioButton_15.setBounds(1005, 55, 109, 23);
		rdbtnNewRadioButton_15.setActionCommand("rdbtnNewRadioButton_15");
		contentPane.add(rdbtnNewRadioButton_15);
		
		group4 = new ButtonGroup();
		group4.add(rdbtnNewRadioButton_12);
		group4.add(rdbtnNewRadioButton_13);
		group4.add(rdbtnNewRadioButton_14);
		group4.add(rdbtnNewRadioButton_15);	
		
		JLabel lblNewLabel_4 = new JLabel("11. Newspapers \u2026 every morning and every evening");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(742, 96, 341, 14);
		contentPane.add(lblNewLabel_4);
		
		rdbtnNewRadioButton_16 = new JRadioButton("delivery");
		rdbtnNewRadioButton_16.setBounds(742, 117, 109, 23);
		rdbtnNewRadioButton_16.setActionCommand("rdbtnNewRadioButton_16");
		contentPane.add(rdbtnNewRadioButton_16);
		
		rdbtnNewRadioButton_17 = new JRadioButton("are delivery");
		rdbtnNewRadioButton_17.setBounds(742, 143, 109, 23);
		rdbtnNewRadioButton_17.setActionCommand("rdbtnNewRadioButton_17");
		contentPane.add(rdbtnNewRadioButton_17);
		
		rdbtnNewRadioButton_18 = new JRadioButton("on time");
		rdbtnNewRadioButton_18.setBounds(1005, 117, 109, 23);
		rdbtnNewRadioButton_18.setActionCommand("rdbtnNewRadioButton_18");
		contentPane.add(rdbtnNewRadioButton_18);
		
		rdbtnNewRadioButton_19 = new JRadioButton("regularly");
		rdbtnNewRadioButton_19.setBounds(1005, 143, 109, 23);
		rdbtnNewRadioButton_19.setActionCommand("rdbtnNewRadioButton_19");
		contentPane.add(rdbtnNewRadioButton_19);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnNewRadioButton_16);
		group5.add(rdbtnNewRadioButton_17);
		group5.add(rdbtnNewRadioButton_18);
		group5.add(rdbtnNewRadioButton_19);	
		
		JLabel lblNewLabel_5 = new JLabel("This text is for questions 12 to 14");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(20, 277, 210, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{					
				//clear file first					
				tp.emptyFile("qq0.dat");						
				
				ButtonModel b1 = group.getSelection();
				String s1;
				if(group.getSelection() == null)
					s1 = "-";			
				else			
					s1 = b1.getActionCommand();						
				tp.appendFile("qq0.dat", s1);
				
				ButtonModel b2 = group2.getSelection();     
				String s2; 
				if(group2.getSelection() == null)
					s2 = "-";			
				else			
					s2 = b2.getActionCommand();					
				tp.appendFile("qq0.dat", s2);
				
				ButtonModel b3 = group3.getSelection();        	
				String s3;
				if(group3.getSelection() == null)
					s3 = "-";			
				else			
					s3 = b3.getActionCommand();		
				tp.appendFile("qq0.dat", s3);
				
				ButtonModel b4 = group4.getSelection();        	
				String s4;
				if(group4.getSelection() == null)
					s4 = "-";			
				else			
					s4 = b4.getActionCommand();		
				tp.appendFile("qq0.dat", s4);
				
				ButtonModel b5 = group5.getSelection();        	
				String s5;
				if(group5.getSelection() == null)
					s5 = "-";			
				else			
					s5 = b5.getActionCommand();	
				tp.appendFile("qq0.dat", s5);
					
				setVisible(false);
				QuizQuestion qq = new QuizQuestion();
				qq.setVisible(true);
			}
		});
		btnNewButton.setBounds(20, 577, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				try
				{                   
					if(group.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 7");
					else if(group2.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 8");
					else if(group3.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 9");
					else if(group4.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 10");
					else if(group5.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 11");					
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qq0.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();					
						tp.appendFile("qq0.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();						
						tp.appendFile("qq0.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qq0.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qq0.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qq0.dat", s5);					
						
						setVisible(false);
						quiz1 qz1 = new quiz1();
						qz1.setVisible(true);
					}						
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}						
			}
		});
		btnNewButton_1.setBounds(119, 577, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JTextArea txtrPhilippinesAt = new JTextArea();
		txtrPhilippinesAt.setWrapStyleWord(true);
		txtrPhilippinesAt.setText("PHILIPPINES : At least nine people were killed and dozens were injured when the Philippines security forces clashed with dozens of slum \n"
				+ "dwellers who resisted the tearing down of their homes in the northern province, a police commander said Tuesday. Raul Gonzales, the police \n"
				+ "chief in the northern Cordillera area, said, that the soldiers and police officers traded gunfire with dozens of people who are illegally \n"
				+ "occupying the private land in Kalinga province. \u201COur team was ambushed on their way to the community to be demolished,\u201D said Gonzales. \n"
				+ "He added that the security only defended themselves after the residents dug foxholes and opened fire with automatic rifles. \u201CNine people were \n"
				+ "killed and dozens were wounded, including 10 police officers during almost 10 hours of fighting. We even had to evacuate some of our officers \n"
				+ "who needed surgery to get the bullets from their bodies.\u201D \u2013 Reuters");
		txtrPhilippinesAt.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtrPhilippinesAt.setEditable(false);
		txtrPhilippinesAt.setBounds(20, 307, 1277, 263);
		contentPane.add(txtrPhilippinesAt);
	}
}
