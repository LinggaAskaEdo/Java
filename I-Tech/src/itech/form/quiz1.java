package itech.form;

import itech.funct.TempPendaftaran;

import java.awt.EventQueue;
import java.awt.Font;
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class quiz1 extends JFrame
{		
	private static final long serialVersionUID = 1L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public ButtonGroup group, group2, group3, group4, group5, group6, group7, group8, group9 = null;
	public JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3;
	public JRadioButton rdbtnNewRadioButton_4, rdbtnNewRadioButton_5, rdbtnNewRadioButton_6, rdbtnNewRadioButton_7;
	public JRadioButton rdbtnNewRadioButton_8, rdbtnNewRadioButton_9, rdbtnNewRadioButton_10, rdbtnNewRadioButton_11;
	public JRadioButton rdbtnNewRadioButton_12, rdbtnNewRadioButton_13, rdbtnNewRadioButton_14, rdbtnNewRadioButton_15;
	public JRadioButton rdbtnNewRadioButton_16, rdbtnNewRadioButton_17, rdbtnNewRadioButton_18, rdbtnNewRadioButton_19; 
	public JRadioButton rdbtnNewRadioButton_20, rdbtnNewRadioButton_21, rdbtnNewRadioButton_22, rdbtnNewRadioButton_23;
	public JRadioButton rdbtnNewRadioButton_24, rdbtnNewRadioButton_25, rdbtnNewRadioButton_26, rdbtnNewRadioButton_27;
	public JRadioButton rdbtnNewRadioButton_28, rdbtnNewRadioButton_29, rdbtnNewRadioButton_30, rdbtnNewRadioButton_31;
	public JRadioButton rdbtnNewRadioButton_32, rdbtnNewRadioButton_33, rdbtnNewRadioButton_34, rdbtnNewRadioButton_35;	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					quiz1 frame = new quiz1();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public quiz1() 
	{
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowActivated(WindowEvent e)
			{		
				File file = new File("qq1.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qq1.dat", 1);				
					if (n1.equals("rdbtnNewRadioButton"))
						rdbtnNewRadioButton.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_1"))
						rdbtnNewRadioButton_1.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_2"))
						rdbtnNewRadioButton_2.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_3"))
						rdbtnNewRadioButton_3.setSelected(true);
					
					String n2 = tp.getSpecificFile("qq1.dat", 2);				
					if (n2.equals("rdbtnNewRadioButton_4"))
						rdbtnNewRadioButton_4.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_5"))
						rdbtnNewRadioButton_5.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_6"))
						rdbtnNewRadioButton_6.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_7"))
						rdbtnNewRadioButton_7.setSelected(true);
					
					String n3 = tp.getSpecificFile("qq1.dat", 3);				
					if (n3.equals("rdbtnNewRadioButton_8"))
						rdbtnNewRadioButton_8.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_9"))
						rdbtnNewRadioButton_9.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_10"))
						rdbtnNewRadioButton_10.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_11"))
						rdbtnNewRadioButton_11.setSelected(true);
					
					String n4 = tp.getSpecificFile("qq1.dat", 4);				
					if (n4.equals("rdbtnNewRadioButton_12"))
						rdbtnNewRadioButton_12.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_13"))
						rdbtnNewRadioButton_13.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_14"))
						rdbtnNewRadioButton_14.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_15"))
						rdbtnNewRadioButton_15.setSelected(true);
					
					String n5 = tp.getSpecificFile("qq1.dat", 5);				
					if (n5.equals("rdbtnNewRadioButton_16"))
						rdbtnNewRadioButton_16.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_17"))
						rdbtnNewRadioButton_17.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_18"))
						rdbtnNewRadioButton_18.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_19"))
						rdbtnNewRadioButton_19.setSelected(true);
					
					String n6 = tp.getSpecificFile("qq1.dat", 6);				
					if (n6.equals("rdbtnNewRadioButton_20"))
						rdbtnNewRadioButton_20.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_21"))
						rdbtnNewRadioButton_21.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_22"))
						rdbtnNewRadioButton_22.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_23"))
						rdbtnNewRadioButton_23.setSelected(true);
					
					String n7 = tp.getSpecificFile("qq1.dat", 7);				
					if (n7.equals("rdbtnNewRadioButton_24"))
						rdbtnNewRadioButton_24.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_25"))
						rdbtnNewRadioButton_25.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_26"))
						rdbtnNewRadioButton_26.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_27"))
						rdbtnNewRadioButton_27.setSelected(true);
					
					String n8 = tp.getSpecificFile("qq1.dat", 8);				
					if (n8.equals("rdbtnNewRadioButton_28"))
						rdbtnNewRadioButton_28.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_29"))
						rdbtnNewRadioButton_29.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_30"))
						rdbtnNewRadioButton_30.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_31"))
						rdbtnNewRadioButton_31.setSelected(true);
					
					String n9 = tp.getSpecificFile("qq1.dat", 9);				
					if (n9.equals("rdbtnNewRadioButton_32"))
						rdbtnNewRadioButton_32.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_33"))
						rdbtnNewRadioButton_33.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_34"))
						rdbtnNewRadioButton_34.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_35"))
						rdbtnNewRadioButton_35.setSelected(true);
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
		
		JLabel lblNewLabel = new JLabel("12.  What is the passage about?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 277, 14);
		getContentPane().add(lblNewLabel);
		
		rdbtnNewRadioButton = new JRadioButton("An illegal gunfire trade");
		rdbtnNewRadioButton.setBounds(20, 32, 212, 23);
		rdbtnNewRadioButton.setActionCommand("rdbtnNewRadioButton");
		getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("A demolition of illegal slum dwellers");
		rdbtnNewRadioButton_1.setBounds(20, 57, 251, 23);
		rdbtnNewRadioButton_1.setActionCommand("rdbtnNewRadioButton_1");
		getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("A fight between the police officers and the soldiers");
		rdbtnNewRadioButton_2.setBounds(293, 32, 372, 23);
		rdbtnNewRadioButton_2.setActionCommand("rdbtnNewRadioButton_2");
		getContentPane().add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("A clash between the security forces and the slum dwellers");
		rdbtnNewRadioButton_3.setBounds(293, 57, 372, 23);
		rdbtnNewRadioButton_3.setActionCommand("rdbtnNewRadioButton_3");
		getContentPane().add(rdbtnNewRadioButton_3);
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);	
		
		JLabel lblNewLabel_1 = new JLabel("13.  The clash happened because \u2026");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 96, 312, 14);
		getContentPane().add(lblNewLabel_1);
		
		rdbtnNewRadioButton_4 = new JRadioButton("illegal slum dwellers resisted the demolition of their homes");
		rdbtnNewRadioButton_4.setBounds(20, 117, 415, 23);
		rdbtnNewRadioButton_4.setActionCommand("rdbtnNewRadioButton_4");
		getContentPane().add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("the police officers and soldiers shot the dwellers");
		rdbtnNewRadioButton_5.setBounds(20, 142, 382, 23);
		rdbtnNewRadioButton_5.setActionCommand("rdbtnNewRadioButton_5");
		getContentPane().add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("nine people were killed by the security forces");
		rdbtnNewRadioButton_6.setBounds(20, 167, 382, 23);
		rdbtnNewRadioButton_6.setActionCommand("rdbtnNewRadioButton_6");
		getContentPane().add(rdbtnNewRadioButton_6);
		
		rdbtnNewRadioButton_7 = new JRadioButton("the police officers firing the dwellers");
		rdbtnNewRadioButton_7.setBounds(20, 192, 362, 23);
		rdbtnNewRadioButton_7.setActionCommand("rdbtnNewRadioButton_7");
		getContentPane().add(rdbtnNewRadioButton_7);
		
		group2 = new ButtonGroup();
		group2.add(rdbtnNewRadioButton_4);
		group2.add(rdbtnNewRadioButton_5);
		group2.add(rdbtnNewRadioButton_6);
		group2.add(rdbtnNewRadioButton_7);	
		
		JLabel lblNewLabel_2 = new JLabel("14.  Raul Gonzales said that \u2026");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 228, 312, 14);
		getContentPane().add(lblNewLabel_2);
		
		rdbtnNewRadioButton_8 = new JRadioButton("ten police officers had to be evacuated");
		rdbtnNewRadioButton_8.setBounds(20, 249, 251, 23);
		rdbtnNewRadioButton_8.setActionCommand("rdbtnNewRadioButton_8");
		getContentPane().add(rdbtnNewRadioButton_8);
		
		rdbtnNewRadioButton_9 = new JRadioButton("ten police officers were killed in the fighting");
		rdbtnNewRadioButton_9.setBounds(20, 275, 277, 23);
		rdbtnNewRadioButton_9.setActionCommand("rdbtnNewRadioButton_9");
		getContentPane().add(rdbtnNewRadioButton_9);
		
		rdbtnNewRadioButton_10 = new JRadioButton("ten police officers were injured during the fighting");
		rdbtnNewRadioButton_10.setBounds(293, 249, 347, 23);
		rdbtnNewRadioButton_10.setActionCommand("rdbtnNewRadioButton_10");
		getContentPane().add(rdbtnNewRadioButton_10);
		
		rdbtnNewRadioButton_11 = new JRadioButton("some of the wounded people needed surgery");
		rdbtnNewRadioButton_11.setBounds(293, 275, 335, 23);
		rdbtnNewRadioButton_11.setActionCommand("rdbtnNewRadioButton_11");
		getContentPane().add(rdbtnNewRadioButton_11);
		
		group3 = new ButtonGroup();
		group3.add(rdbtnNewRadioButton_8);
		group3.add(rdbtnNewRadioButton_9);
		group3.add(rdbtnNewRadioButton_10);
		group3.add(rdbtnNewRadioButton_11);	
		
		JLabel lblNewLabel_3 = new JLabel("15.  Why didn't Did! wan to go home?'' His mother \u2026 him for causing the car accident'");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(20, 315, 579, 14);
		getContentPane().add(lblNewLabel_3);
		
		rdbtnNewRadioButton_12 = new JRadioButton("would blame");		
		rdbtnNewRadioButton_12.setBounds(20, 337, 109, 23);
		rdbtnNewRadioButton_12.setActionCommand("rdbtnNewRadioButton_12");
		getContentPane().add(rdbtnNewRadioButton_12);
		
		rdbtnNewRadioButton_13 = new JRadioButton("has blamed");
		rdbtnNewRadioButton_13.setBounds(20, 358, 109, 23);
		rdbtnNewRadioButton_13.setActionCommand("rdbtnNewRadioButton_13");
		getContentPane().add(rdbtnNewRadioButton_13);
		
		rdbtnNewRadioButton_14 = new JRadioButton("is blaming");
		rdbtnNewRadioButton_14.setBounds(293, 337, 109, 23);
		rdbtnNewRadioButton_14.setActionCommand("rdbtnNewRadioButton_14");
		getContentPane().add(rdbtnNewRadioButton_14);
		
		rdbtnNewRadioButton_15 = new JRadioButton("had blamed");
		rdbtnNewRadioButton_15.setBounds(293, 358, 109, 23);
		rdbtnNewRadioButton_15.setActionCommand("rdbtnNewRadioButton_15");
		getContentPane().add(rdbtnNewRadioButton_15);
		
		group4 = new ButtonGroup();
		group4.add(rdbtnNewRadioButton_12);
		group4.add(rdbtnNewRadioButton_13);
		group4.add(rdbtnNewRadioButton_14);
		group4.add(rdbtnNewRadioButton_15);	
		
		JLabel lblNewLabel_4 = new JLabel("16. The way professor Mattis teaches English not only keeps the student' interest \u2026");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(20, 395, 559, 14);
		getContentPane().add(lblNewLabel_4);
		
		rdbtnNewRadioButton_16 = new JRadioButton("And also increases their motivation");
		rdbtnNewRadioButton_16.setBounds(20, 417, 245, 23);
		rdbtnNewRadioButton_16.setActionCommand("rdbtnNewRadioButton_16");
		getContentPane().add(rdbtnNewRadioButton_16);
		
		rdbtnNewRadioButton_17 = new JRadioButton("But also increasing their motivation");
		rdbtnNewRadioButton_17.setBounds(20, 443, 226, 23);
		rdbtnNewRadioButton_17.setActionCommand("rdbtnNewRadioButton_17");
		getContentPane().add(rdbtnNewRadioButton_17);
		
		rdbtnNewRadioButton_18 = new JRadioButton("And he also increases their motivation");
		rdbtnNewRadioButton_18.setBounds(293, 417, 262, 23);
		rdbtnNewRadioButton_18.setActionCommand("rdbtnNewRadioButton_18");
		getContentPane().add(rdbtnNewRadioButton_18);
		
		rdbtnNewRadioButton_19 = new JRadioButton("But also increases their motivation");
		rdbtnNewRadioButton_19.setBounds(293, 443, 226, 23);
		rdbtnNewRadioButton_19.setActionCommand("rdbtnNewRadioButton_19");
		getContentPane().add(rdbtnNewRadioButton_19);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnNewRadioButton_16);
		group5.add(rdbtnNewRadioButton_17);
		group5.add(rdbtnNewRadioButton_18);
		group5.add(rdbtnNewRadioButton_19);	
		
		JLabel lblNewLabel_5 = new JLabel("17. The thief \u2026  into the room through this window because there are footprint near the door.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(682, 11, 639, 14);
		getContentPane().add(lblNewLabel_5);
		
		rdbtnNewRadioButton_20 = new JRadioButton("Was to get");
		rdbtnNewRadioButton_20.setBounds(682, 32, 109, 23);
		rdbtnNewRadioButton_20.setActionCommand("rdbtnNewRadioButton_20");
		getContentPane().add(rdbtnNewRadioButton_20);
		
		rdbtnNewRadioButton_21 = new JRadioButton("My get");
		rdbtnNewRadioButton_21.setBounds(682, 57, 109, 23);
		rdbtnNewRadioButton_21.setActionCommand("rdbtnNewRadioButton_21");
		getContentPane().add(rdbtnNewRadioButton_21);
		
		rdbtnNewRadioButton_22 = new JRadioButton("Would rather get");
		rdbtnNewRadioButton_22.setBounds(955, 32, 170, 23);
		rdbtnNewRadioButton_22.setActionCommand("rdbtnNewRadioButton_22");
		getContentPane().add(rdbtnNewRadioButton_22);
		
		rdbtnNewRadioButton_23 = new JRadioButton("Must have got");
		rdbtnNewRadioButton_23.setBounds(955, 57, 109, 23);
		rdbtnNewRadioButton_23.setActionCommand("rdbtnNewRadioButton_23");
		getContentPane().add(rdbtnNewRadioButton_23);
		
		group6 = new ButtonGroup();
		group6.add(rdbtnNewRadioButton_20);
		group6.add(rdbtnNewRadioButton_21);
		group6.add(rdbtnNewRadioButton_22);
		group6.add(rdbtnNewRadioButton_23);	
		
		JLabel lblNewLabel_6 = new JLabel("18. He told me a lot about the Philippines. He \u2026 there for a long time");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(682, 96, 505, 14);
		getContentPane().add(lblNewLabel_6);
		
		rdbtnNewRadioButton_24 = new JRadioButton("Must have lived");
		rdbtnNewRadioButton_24.setBounds(682, 117, 151, 23);
		rdbtnNewRadioButton_24.setActionCommand("rdbtnNewRadioButton_24");
		getContentPane().add(rdbtnNewRadioButton_24);
		
		rdbtnNewRadioButton_25 = new JRadioButton("Might be living");
		rdbtnNewRadioButton_25.setBounds(682, 143, 109, 23);
		rdbtnNewRadioButton_25.setActionCommand("rdbtnNewRadioButton_25");
		getContentPane().add(rdbtnNewRadioButton_25);
		
		rdbtnNewRadioButton_26 = new JRadioButton("Ought to have lived");
		rdbtnNewRadioButton_26.setBounds(955, 117, 143, 23);
		rdbtnNewRadioButton_26.setActionCommand("rdbtnNewRadioButton_26");
		getContentPane().add(rdbtnNewRadioButton_26);
		
		rdbtnNewRadioButton_27 = new JRadioButton("Should be living");
		rdbtnNewRadioButton_27.setBounds(955, 143, 143, 23);
		rdbtnNewRadioButton_27.setActionCommand("rdbtnNewRadioButton_27");
		getContentPane().add(rdbtnNewRadioButton_27);
		
		group7 = new ButtonGroup();
		group7.add(rdbtnNewRadioButton_24);
		group7.add(rdbtnNewRadioButton_25);
		group7.add(rdbtnNewRadioButton_26);
		group7.add(rdbtnNewRadioButton_27);	
		
		JLabel lblNewLabel_7 = new JLabel("19. Your son will be operated on tomorrow morning. He \u2026 have a good rest tonight' the doctor said.");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(682, 186, 639, 14);
		getContentPane().add(lblNewLabel_7);
		
		rdbtnNewRadioButton_28 = new JRadioButton("Might");
		rdbtnNewRadioButton_28.setBounds(682, 207, 109, 23);
		rdbtnNewRadioButton_28.setActionCommand("rdbtnNewRadioButton_28");
		getContentPane().add(rdbtnNewRadioButton_28);
		
		rdbtnNewRadioButton_29 = new JRadioButton("May");
		rdbtnNewRadioButton_29.setBounds(682, 233, 109, 23);
		rdbtnNewRadioButton_29.setActionCommand("rdbtnNewRadioButton_29");
		getContentPane().add(rdbtnNewRadioButton_29);
		
		rdbtnNewRadioButton_30 = new JRadioButton("Can");
		rdbtnNewRadioButton_30.setBounds(955, 207, 109, 23);
		rdbtnNewRadioButton_30.setActionCommand("rdbtnNewRadioButton_30");
		getContentPane().add(rdbtnNewRadioButton_30);
		
		rdbtnNewRadioButton_31 = new JRadioButton("Must");
		rdbtnNewRadioButton_31.setBounds(955, 233, 109, 23);
		rdbtnNewRadioButton_31.setActionCommand("rdbtnNewRadioButton_31");
		getContentPane().add(rdbtnNewRadioButton_31);	
		
		group8 = new ButtonGroup();
		group8.add(rdbtnNewRadioButton_28);
		group8.add(rdbtnNewRadioButton_29);
		group8.add(rdbtnNewRadioButton_30);
		group8.add(rdbtnNewRadioButton_31);	
		
		JLabel lblNewLabel_9 = new JLabel("20. Having given the prescription to the patient \u2026");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(682, 274, 397, 14);
		getContentPane().add(lblNewLabel_9);
		
		rdbtnNewRadioButton_32 = new JRadioButton("The medicine was taken regularly by the patient");
		rdbtnNewRadioButton_32.setBounds(682, 296, 372, 23);
		rdbtnNewRadioButton_32.setActionCommand("rdbtnNewRadioButton_32");
		getContentPane().add(rdbtnNewRadioButton_32);
		
		rdbtnNewRadioButton_33 = new JRadioButton("The doctor told the patient to take the medicine regularly");
		rdbtnNewRadioButton_33.setBounds(682, 322, 382, 23);
		rdbtnNewRadioButton_33.setActionCommand("rdbtnNewRadioButton_33");
		getContentPane().add(rdbtnNewRadioButton_33);
		
		rdbtnNewRadioButton_34 = new JRadioButton("The medicine had to be taken regularly by the patient");
		rdbtnNewRadioButton_34.setBounds(682, 348, 326, 23);
		rdbtnNewRadioButton_34.setActionCommand("rdbtnNewRadioButton_34");
		getContentPane().add(rdbtnNewRadioButton_34);
		
		rdbtnNewRadioButton_35 = new JRadioButton("The patient was told to take the medicine regularly");
		rdbtnNewRadioButton_35.setBounds(682, 374, 326, 23);
		rdbtnNewRadioButton_35.setActionCommand("rdbtnNewRadioButton_35");
		getContentPane().add(rdbtnNewRadioButton_35);
		
		group9 = new ButtonGroup();
		group9.add(rdbtnNewRadioButton_32);
		group9.add(rdbtnNewRadioButton_33);
		group9.add(rdbtnNewRadioButton_34);
		group9.add(rdbtnNewRadioButton_35);	
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{			
				//clear file first					
				tp.emptyFile("qq1.dat");						
				
				ButtonModel b1 = group.getSelection();        	
				String s1;
				if(group.getSelection() == null)
					s1 = "-";			
				else			
					s1 = b1.getActionCommand();		
				tp.appendFile("qq1.dat", s1);
				
				ButtonModel b2 = group2.getSelection();        	
				String s2;
				if(group2.getSelection() == null)
					s2 = "-";			
				else			
					s2 = b2.getActionCommand();		
				tp.appendFile("qq1.dat", s2);
				
				ButtonModel b3 = group3.getSelection();        	
				String s3;
				if(group3.getSelection() == null)
					s3 = "-";			
				else			
					s3 = b3.getActionCommand();		
				tp.appendFile("qq1.dat", s3);
				
				ButtonModel b4 = group4.getSelection();        	
				String s4;
				if(group4.getSelection() == null)
					s4 = "-";			
				else			
					s4 = b4.getActionCommand();		
				tp.appendFile("qq1.dat", s4);
				
				ButtonModel b5 = group5.getSelection();        	
				String s5;
				if(group5.getSelection() == null)
					s5 = "-";			
				else			
					s5 = b5.getActionCommand();		
				tp.appendFile("qq1.dat", s5);	
				
				ButtonModel b6 = group6.getSelection();        	
				String s6;
				if(group6.getSelection() == null)
					s6 = "-";			
				else			
					s6 = b6.getActionCommand();		
				tp.appendFile("qq1.dat", s6);	
				
				ButtonModel b7 = group7.getSelection();        	
				String s7;
				if(group7.getSelection() == null)
					s7 = "-";			
				else			
					s7 = b7.getActionCommand();		
				tp.appendFile("qq1.dat", s7);	
				
				ButtonModel b8 = group8.getSelection();        	
				String s8;
				if(group8.getSelection() == null)
					s8 = "-";			
				else			
					s8 = b8.getActionCommand();		
				tp.appendFile("qq1.dat", s8);	
				
				ButtonModel b9 = group9.getSelection();        	
				String s9;
				if(group9.getSelection() == null)
					s9 = "-";			
				else			
					s9 = b9.getActionCommand();		
				tp.appendFile("qq1.dat", s9);	
				
				setVisible(false);
				quiz qz = new quiz();
				qz.setVisible(true);
			}
		});
		btnNewButton.setBounds(20, 577, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				try
				{                   
					if(group.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 12");
					else if(group2.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 13");
					else if(group3.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 14");
					else if(group4.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 15");
					else if(group5.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 16");	
					else if(group6.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 17");	
					else if(group7.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 18");	
					else if(group8.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 19");	
					else if(group9.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 20");	
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qq1.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();
						tp.appendFile("qq1.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();
						tp.appendFile("qq1.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qq1.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qq1.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qq1.dat", s5);	
						
						ButtonModel b6 = group6.getSelection();        	
						String s6 = b6.getActionCommand();
						tp.appendFile("qq1.dat", s6);	
						
						ButtonModel b7 = group7.getSelection();        	
						String s7 = b7.getActionCommand();
						tp.appendFile("qq1.dat", s7);	
						
						ButtonModel b8 = group8.getSelection();        	
						String s8 = b8.getActionCommand();
						tp.appendFile("qq1.dat", s8);	
						
						ButtonModel b9 = group9.getSelection();        	
						String s9 = b9.getActionCommand();
						tp.appendFile("qq1.dat", s9);	
						
						setVisible(false);
						quiz2 qz2 = new quiz2();
						qz2.setVisible(true);
					}						
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}					
			}
		});
		btnNewButton_1.setBounds(119, 577, 89, 23);
		getContentPane().add(btnNewButton_1);
	}
}
