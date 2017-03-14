package itech.form;

import itech.funct.TempPendaftaran;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class quiz2 extends JFrame 
{	
	private static final long serialVersionUID = 1L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public ButtonGroup group, group2, group3, group4, group5, group6 = null;
	public JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3;
	public JRadioButton rdbtnNewRadioButton_4, rdbtnNewRadioButton_5, rdbtnNewRadioButton_6, rdbtnNewRadioButton_7;
	public JRadioButton rdbtnNewRadioButton_8, rdbtnNewRadioButton_9, rdbtnNewRadioButton_10, rdbtnNewRadioButton_11;
	public JRadioButton rdbtnNewRadioButton_12, rdbtnNewRadioButton_13, rdbtnNewRadioButton_14, rdbtnNewRadioButton_15;
	public JRadioButton rdbtnNewRadioButton_16, rdbtnNewRadioButton_17, rdbtnNewRadioButton_18, rdbtnNewRadioButton_19;
	public JRadioButton rdbtnNewRadioButton_20, rdbtnNewRadioButton_21, rdbtnNewRadioButton_22, rdbtnNewRadioButton_23;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try
				{
					quiz2 frame = new quiz2();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public quiz2() 
	{
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowActivated(WindowEvent e)
			{
				File file = new File("qq2.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qq2.dat", 1);				
					if (n1.equals("rdbtnNewRadioButton"))
						rdbtnNewRadioButton.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_1"))
						rdbtnNewRadioButton_1.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_2"))
						rdbtnNewRadioButton_2.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_3"))
						rdbtnNewRadioButton_3.setSelected(true);
					
					String n2 = tp.getSpecificFile("qq2.dat", 2);				
					if (n2.equals("rdbtnNewRadioButton_4"))
						rdbtnNewRadioButton_4.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_5"))
						rdbtnNewRadioButton_5.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_6"))
						rdbtnNewRadioButton_6.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_7"))
						rdbtnNewRadioButton_7.setSelected(true);
					
					String n3 = tp.getSpecificFile("qq2.dat", 3);				
					if (n3.equals("rdbtnNewRadioButton_8"))
						rdbtnNewRadioButton_8.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_9"))
						rdbtnNewRadioButton_9.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_10"))
						rdbtnNewRadioButton_10.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_11"))
						rdbtnNewRadioButton_11.setSelected(true);
					
					String n4 = tp.getSpecificFile("qq2.dat", 4);				
					if (n4.equals("rdbtnNewRadioButton_12"))
						rdbtnNewRadioButton_12.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_13"))
						rdbtnNewRadioButton_13.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_14"))
						rdbtnNewRadioButton_14.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_15"))
						rdbtnNewRadioButton_15.setSelected(true);
					
					String n5 = tp.getSpecificFile("qq2.dat", 5);				
					if (n5.equals("rdbtnNewRadioButton_16"))
						rdbtnNewRadioButton_16.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_17"))
						rdbtnNewRadioButton_17.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_18"))
						rdbtnNewRadioButton_18.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_19"))
						rdbtnNewRadioButton_19.setSelected(true);
					
					String n6 = tp.getSpecificFile("qq2.dat", 6);				
					if (n6.equals("rdbtnNewRadioButton_20"))
						rdbtnNewRadioButton_20.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_21"))
						rdbtnNewRadioButton_21.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_22"))
						rdbtnNewRadioButton_22.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_23"))
						rdbtnNewRadioButton_23.setSelected(true);	
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
		
		JLabel lblNewLabel = new JLabel("21. A number of students ... complaining about the test.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 475, 14);
		contentPane.add(lblNewLabel);
		
		rdbtnNewRadioButton = new JRadioButton("is");
		rdbtnNewRadioButton.setBounds(20, 32, 109, 23);
		rdbtnNewRadioButton.setActionCommand("rdbtnNewRadioButton");
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("are");
		rdbtnNewRadioButton_1.setBounds(20, 58, 109, 23);
		rdbtnNewRadioButton_1.setActionCommand("rdbtnNewRadioButton_1");
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("be");
		rdbtnNewRadioButton_2.setBounds(266, 32, 109, 23);
		rdbtnNewRadioButton_2.setActionCommand("rdbtnNewRadioButton_2");
		contentPane.add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("being");
		rdbtnNewRadioButton_3.setBounds(266, 58, 109, 23);
		rdbtnNewRadioButton_3.setActionCommand("rdbtnNewRadioButton_3");
		contentPane.add(rdbtnNewRadioButton_3);
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);		
		
		JLabel lblNewLabel_1 = new JLabel("22. World Trade Centre building, as well as several offiecs, ... completely damaged by the");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 101, 654, 14);
		contentPane.add(lblNewLabel_1);	
		
		JLabel lblNewLabel_2 = new JLabel("aeroplane crush explosion.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 127, 262, 14);
		contentPane.add(lblNewLabel_2);
		
		rdbtnNewRadioButton_4 = new JRadioButton("was");
		rdbtnNewRadioButton_4.setBounds(20, 153, 109, 23);
		rdbtnNewRadioButton_4.setActionCommand("rdbtnNewRadioButton_4");
		contentPane.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("they were");
		rdbtnNewRadioButton_5.setBounds(20, 179, 109, 23);
		rdbtnNewRadioButton_5.setActionCommand("rdbtnNewRadioButton_5");
		contentPane.add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("It was");
		rdbtnNewRadioButton_6.setBounds(265, 153, 109, 23);
		rdbtnNewRadioButton_6.setActionCommand("rdbtnNewRadioButton_6");
		contentPane.add(rdbtnNewRadioButton_6);
		
		rdbtnNewRadioButton_7 = new JRadioButton("Were");
		rdbtnNewRadioButton_7.setBounds(265, 179, 109, 23);
		rdbtnNewRadioButton_7.setActionCommand("rdbtnNewRadioButton_7");
		contentPane.add(rdbtnNewRadioButton_7);
		
		group2 = new ButtonGroup();
		group2.add(rdbtnNewRadioButton_4);
		group2.add(rdbtnNewRadioButton_5);
		group2.add(rdbtnNewRadioButton_6);
		group2.add(rdbtnNewRadioButton_7);		
		
		JLabel lblNewLabel_3 = new JLabel("23. Simple photographic lenses cannot ... sharp, undistorted images over a wide field.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(19, 222, 640, 14);
		contentPane.add(lblNewLabel_3);
		
		rdbtnNewRadioButton_8 = new JRadioButton("to form");
		rdbtnNewRadioButton_8.setBounds(19, 248, 109, 23);
		rdbtnNewRadioButton_8.setActionCommand("rdbtnNewRadioButton_8");
		contentPane.add(rdbtnNewRadioButton_8);
		
		rdbtnNewRadioButton_9 = new JRadioButton("Are formed");
		rdbtnNewRadioButton_9.setBounds(19, 274, 109, 23);
		rdbtnNewRadioButton_9.setActionCommand("rdbtnNewRadioButton_9");
		contentPane.add(rdbtnNewRadioButton_9);
		
		rdbtnNewRadioButton_10 = new JRadioButton("Forming");
		rdbtnNewRadioButton_10.setBounds(265, 248, 109, 23);
		rdbtnNewRadioButton_10.setActionCommand("rdbtnNewRadioButton_10");
		contentPane.add(rdbtnNewRadioButton_10);
		
		rdbtnNewRadioButton_11 = new JRadioButton("Form");
		rdbtnNewRadioButton_11.setBounds(265, 274, 109, 23);
		rdbtnNewRadioButton_11.setActionCommand("rdbtnNewRadioButton_11");
		contentPane.add(rdbtnNewRadioButton_11);
		
		group3 = new ButtonGroup();
		group3.add(rdbtnNewRadioButton_8);
		group3.add(rdbtnNewRadioButton_9);
		group3.add(rdbtnNewRadioButton_10);
		group3.add(rdbtnNewRadioButton_11);		
		
		JLabel lblNewLabel_4 = new JLabel("24. Technology will play a key role in ... future life-styles");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(682, 11, 475, 14);
		contentPane.add(lblNewLabel_4);
		
		rdbtnNewRadioButton_12 = new JRadioButton("To shape");
		rdbtnNewRadioButton_12.setBounds(682, 32, 109, 23);
		rdbtnNewRadioButton_12.setActionCommand("rdbtnNewRadioButton_12");
		contentPane.add(rdbtnNewRadioButton_12);
		
		rdbtnNewRadioButton_13 = new JRadioButton("Shaping");
		rdbtnNewRadioButton_13.setBounds(682, 58, 109, 23);
		rdbtnNewRadioButton_13.setActionCommand("rdbtnNewRadioButton_13");
		contentPane.add(rdbtnNewRadioButton_13);
		
		rdbtnNewRadioButton_14 = new JRadioButton("Shap");
		rdbtnNewRadioButton_14.setBounds(955, 32, 109, 23);
		rdbtnNewRadioButton_14.setActionCommand("rdbtnNewRadioButton_14");
		contentPane.add(rdbtnNewRadioButton_14);
		
		rdbtnNewRadioButton_15 = new JRadioButton("Shaped");
		rdbtnNewRadioButton_15.setBounds(955, 58, 109, 23);
		rdbtnNewRadioButton_15.setActionCommand("rdbtnNewRadioButton_15");
		contentPane.add(rdbtnNewRadioButton_15);
		
		group4 = new ButtonGroup();
		group4.add(rdbtnNewRadioButton_12);
		group4.add(rdbtnNewRadioButton_13);
		group4.add(rdbtnNewRadioButton_14);
		group4.add(rdbtnNewRadioButton_15);		
		
		JLabel lblNewLabel_5 = new JLabel("25. The computer has dramatically affected ... photographic lenses are constructed.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(682, 101, 587, 14);
		contentPane.add(lblNewLabel_5);
		
		rdbtnNewRadioButton_16 = new JRadioButton("Is the way");
		rdbtnNewRadioButton_16.setBounds(682, 127, 109, 23);
		rdbtnNewRadioButton_16.setActionCommand("rdbtnNewRadioButton_16");
		contentPane.add(rdbtnNewRadioButton_16);
		
		rdbtnNewRadioButton_17 = new JRadioButton("That the way");
		rdbtnNewRadioButton_17.setBounds(682, 153, 109, 23);
		rdbtnNewRadioButton_17.setActionCommand("rdbtnNewRadioButton_17");
		contentPane.add(rdbtnNewRadioButton_17);
		
		rdbtnNewRadioButton_18 = new JRadioButton("Which way do");
		rdbtnNewRadioButton_18.setBounds(955, 127, 109, 23);
		rdbtnNewRadioButton_18.setActionCommand("rdbtnNewRadioButton_18");
		contentPane.add(rdbtnNewRadioButton_18);
		
		rdbtnNewRadioButton_19 = new JRadioButton("the way");
		rdbtnNewRadioButton_19.setBounds(955, 153, 109, 23);
		rdbtnNewRadioButton_19.setActionCommand("rdbtnNewRadioButton_19");
		contentPane.add(rdbtnNewRadioButton_19);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnNewRadioButton_16);
		group5.add(rdbtnNewRadioButton_17);
		group5.add(rdbtnNewRadioButton_18);
		group5.add(rdbtnNewRadioButton_19);	
		
		JLabel lblNewLabel_6 = new JLabel("26. In bacteria and in other organisms, ... is the nucleic acid DNA that provides the generic");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(682, 194, 629, 14);
		contentPane.add(lblNewLabel_6);	
		
		JLabel lblNewLabel_7 = new JLabel("information.");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(682, 220, 629, 14);
		contentPane.add(lblNewLabel_7);	
		
		rdbtnNewRadioButton_20 = new JRadioButton("Both");
		rdbtnNewRadioButton_20.setBounds(682, 246, 109, 23);
		rdbtnNewRadioButton_20.setActionCommand("rdbtnNewRadioButton_20");
		contentPane.add(rdbtnNewRadioButton_20);
		
		rdbtnNewRadioButton_21 = new JRadioButton("Which");
		rdbtnNewRadioButton_21.setBounds(682, 272, 109, 23);
		rdbtnNewRadioButton_21.setActionCommand("rdbtnNewRadioButton_21");
		contentPane.add(rdbtnNewRadioButton_21);
		
		rdbtnNewRadioButton_22 = new JRadioButton("And");
		rdbtnNewRadioButton_22.setBounds(955, 246, 109, 23);
		rdbtnNewRadioButton_22.setActionCommand("rdbtnNewRadioButton_22");
		contentPane.add(rdbtnNewRadioButton_22);
		
		rdbtnNewRadioButton_23 = new JRadioButton("It");
		rdbtnNewRadioButton_23.setBounds(955, 272, 109, 23);
		rdbtnNewRadioButton_23.setActionCommand("rdbtnNewRadioButton_23");
		contentPane.add(rdbtnNewRadioButton_23);
		
		group6 = new ButtonGroup();
		group6.add(rdbtnNewRadioButton_20);
		group6.add(rdbtnNewRadioButton_21);
		group6.add(rdbtnNewRadioButton_22);
		group6.add(rdbtnNewRadioButton_23);	
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{			
				//clear file first					
				tp.emptyFile("qq2.dat");						
				
				ButtonModel b1 = group.getSelection();        	
				String s1;
				if(group.getSelection() == null)
					s1 = "-";			
				else			
					s1 = b1.getActionCommand();			
				tp.appendFile("qq2.dat", s1);
				
				ButtonModel b2 = group2.getSelection();        	
				String s2;
				if(group2.getSelection() == null)
					s2 = "-";			
				else			
					s2 = b2.getActionCommand();			
				tp.appendFile("qq2.dat", s2);
				
				ButtonModel b3 = group3.getSelection();        	
				String s3;
				if(group3.getSelection() == null)
					s3 = "-";			
				else			
					s3 = b3.getActionCommand();			
				tp.appendFile("qq2.dat", s3);
				
				ButtonModel b4 = group4.getSelection();        	
				String s4;
				if(group4.getSelection() == null)
					s4 = "-";			
				else			
					s4 = b4.getActionCommand();			
				tp.appendFile("qq2.dat", s4);
				
				ButtonModel b5 = group5.getSelection();        	
				String s5;
				if(group5.getSelection() == null)
					s5 = "-";			
				else			
					s5 = b5.getActionCommand();			
				tp.appendFile("qq2.dat", s5);	
				
				ButtonModel b6 = group6.getSelection();        	
				String s6;
				if(group6.getSelection() == null)
					s6 = "-";			
				else			
					s6 = b6.getActionCommand();			
				tp.appendFile("qq2.dat", s6);
					
				setVisible(false);
				quiz1 qz1 = new quiz1();
				qz1.setVisible(true);
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
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 21");
					else if(group2.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 22");
					else if(group3.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 23");
					else if(group4.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 24");
					else if(group5.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 25");	
					else if(group6.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 26");						
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qq2.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();
						tp.appendFile("qq2.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();
						tp.appendFile("qq2.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qq2.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qq2.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qq2.dat", s5);	
						
						ButtonModel b6 = group6.getSelection();        	
						String s6 = b6.getActionCommand();
						tp.appendFile("qq2.dat", s6);							
						
						setVisible(false);
						quiz3 qz3 = new quiz3();
						qz3.setVisible(true);
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
	}
}
