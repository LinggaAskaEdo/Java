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

public class QuizQuestion extends JFrame 
{
	private static final long serialVersionUID = 1L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public JTextArea txtrIsItImportant;
	public ButtonGroup group, group2, group3, group4, group5, group6 = null;
	public JRadioButton radioButton, rdbtnTheEffectsOf, rdbtnWatchingTvIs, rdbtnReviewingTheRatings;
	public JRadioButton radioButton_1, radioButton_2, radioButton_3, radioButton_4;
	public JRadioButton radioButton_5, radioButton_6, radioButton_7, radioButton_8;
	public JRadioButton radioButton_9, radioButton_10, radioButton_11, radioButton_12;
	public JRadioButton rdbtnClean, rdbtnCleaned, rdbtnClear, rdbtnCleans;
	public JRadioButton rdbtnIs, rdbtnWas, rdbtnAre, rdbtnHave;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					QuizQuestion frame = new QuizQuestion();					
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public QuizQuestion()
	{
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowActivated(WindowEvent arg0)
			{		
				File file = new File("qm.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qm.dat", 1);				
					if (n1.equals("radioButton"))
						radioButton.setSelected(true);
					else if(n1.equals("rdbtnTheEffectsOf"))
						rdbtnTheEffectsOf.setSelected(true);
					else if(n1.equals("rdbtnWatchingTvIs"))
						rdbtnWatchingTvIs.setSelected(true);
					else 
						rdbtnReviewingTheRatings.setSelected(true);
					
					String n2 = tp.getSpecificFile("qm.dat", 2);				
					if (n2.equals("radioButton_1"))
						radioButton_1.setSelected(true);
					else if(n2.equals("radioButton_2"))
						radioButton_2.setSelected(true);
					else if(n2.equals("radioButton_3"))
						radioButton_3.setSelected(true);
					else 
						radioButton_4.setSelected(true);
					
					String n3 = tp.getSpecificFile("qm.dat", 3);				
					if (n3.equals("radioButton_5"))
						radioButton_5.setSelected(true);
					else if(n3.equals("radioButton_6"))
						radioButton_6.setSelected(true);
					else if(n3.equals("radioButton_7"))
						radioButton_7.setSelected(true);
					else 
						radioButton_8.setSelected(true);
					
					String n4 = tp.getSpecificFile("qm.dat", 4);				
					if (n4.equals("radioButton_9"))
						radioButton_9.setSelected(true);
					else if(n4.equals("radioButton_10"))
						radioButton_10.setSelected(true);
					else if(n4.equals("radioButton_11"))
						radioButton_11.setSelected(true);
					else 
						radioButton_12.setSelected(true);
					
					String n5 = tp.getSpecificFile("qm.dat", 5);				
					if (n5.equals("rdbtnClean"))
						rdbtnClean.setSelected(true);
					else if(n5.equals("rdbtnCleaned"))
						rdbtnCleaned.setSelected(true);
					else if(n5.equals("rdbtnClear"))
						rdbtnClear.setSelected(true);
					else 
						rdbtnCleans.setSelected(true);
					
					String n6 = tp.getSpecificFile("qm.dat", 6);				
					if (n6.equals("rdbtnIs"))
						rdbtnIs.setSelected(true);
					else if(n6.equals("rdbtnWas"))
						rdbtnWas.setSelected(true);
					else if(n6.equals("rdbtnAre"))
						rdbtnAre.setSelected(true);
					else 
						rdbtnHave.setSelected(true);
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
		
		txtrIsItImportant = new JTextArea();
		txtrIsItImportant.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtrIsItImportant.setWrapStyleWord(true);
		txtrIsItImportant.setText("Is it important to know what your kids are watching? Of course yes. Television can expose things you have tried to protect them from, especially violence, pornography, consumerism, etc.\n"
				+ "A study demonstrated that spending too much time on watching TV during the day or at bedtime often causes bedtime disruption, stress, and short off sleep duration.\n"
				+ "Another research found that there is a significant relationship between the amount of time spent for watching television during adolescence an early adulthood, and the possibility of being aggressive."
				+ "Meanwhile, many studies have identified a relationship between kids who watch TV a lot and being inactive and overweight.\n"
				+ "Considering some facts mentioned above, protect your children with the following tips :\n"
				+ "1. Limit television viewing to 1-2 hours each day\n"
				+ "2. Do not allow your children to have a TV set in their bedrooms\n"
				+ "3. Review the rating of TV shows that your children watch\n"
				+ "4. Watch your television with your children and discuss what is happening in the show\n");
		txtrIsItImportant.setLineWrap(true);
		txtrIsItImportant.setEditable(false);
		txtrIsItImportant.setBounds(10, 11, 1291, 263);
		contentPane.add(txtrIsItImportant);
		
		JLabel lblNewLabel = new JLabel("1. What is the text about?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 294, 206, 14);
		contentPane.add(lblNewLabel);
		
		radioButton = new JRadioButton("The program shown on TV");		
		radioButton.setBounds(20, 315, 213, 23);
		radioButton.setActionCommand("radioButton");
		contentPane.add(radioButton);
		
		rdbtnTheEffectsOf = new JRadioButton("The effects of watching television on kids");
		rdbtnTheEffectsOf.setBounds(273, 315, 316, 23);
		rdbtnTheEffectsOf.setActionCommand("rdbtnTheEffectsOf");		
		contentPane.add(rdbtnTheEffectsOf);
		
		rdbtnWatchingTvIs = new JRadioButton("Watching TV is disadvantageous");
		rdbtnWatchingTvIs.setBounds(20, 344, 213, 23);
		rdbtnWatchingTvIs.setActionCommand("rdbtnWatchingTvIs");
		contentPane.add(rdbtnWatchingTvIs);
		
		rdbtnReviewingTheRatings = new JRadioButton("Reviewing the ratings of TV shows is important");
		rdbtnReviewingTheRatings.setBounds(273, 344, 305, 23);
		rdbtnReviewingTheRatings.setActionCommand("rdbtnReviewingTheRatings");
		contentPane.add(rdbtnReviewingTheRatings);
		
		group = new ButtonGroup();
		group.add(radioButton);
		group.add(rdbtnTheEffectsOf);
		group.add(rdbtnWatchingTvIs);
		group.add(rdbtnReviewingTheRatings);		
		
		JLabel label = new JLabel("2. The following are the effects of watching TV a lot EXCEPT :");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(20, 397, 387, 14);
		contentPane.add(label);
		
		radioButton_1 = new JRadioButton("stress");
		radioButton_1.setBounds(20, 418, 213, 23);
		radioButton_1.setActionCommand("radioButton_1");
		contentPane.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("being active");
		radioButton_2.setBounds(20, 447, 213, 23);
		radioButton_2.setActionCommand("radioButton_2");
		contentPane.add(radioButton_2);
		
		radioButton_3 = new JRadioButton("being aggressive");
		radioButton_3.setBounds(273, 418, 316, 23);
		radioButton_3.setActionCommand("radioButton_3");
		contentPane.add(radioButton_3);
		
		radioButton_4 = new JRadioButton("bedtime disruption");
		radioButton_4.setBounds(273, 447, 305, 23);
		radioButton_4.setActionCommand("radioButton_4");
		contentPane.add(radioButton_4);
		
		group2 = new ButtonGroup();
		group2.add(radioButton_1);
		group2.add(radioButton_2);
		group2.add(radioButton_3);
		group2.add(radioButton_4);
		
		JLabel label_1 = new JLabel("3. Which of the following statements is TRUE according to the text?");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(20, 500, 435, 14);
		contentPane.add(label_1);
		
		radioButton_5 = new JRadioButton("All TV programs are good for children");
		radioButton_5.setBounds(20, 521, 240, 23);
		radioButton_5.setActionCommand("radioButton_5");
		contentPane.add(radioButton_5);
		
		radioButton_6 = new JRadioButton("Children know what programs to watch");
		radioButton_6.setBounds(20, 550, 251, 23);
		radioButton_6.setActionCommand("radioButton_6");
		contentPane.add(radioButton_6);
		
		radioButton_7 = new JRadioButton("It is very important for children to have a TV set in their bedrooms");
		radioButton_7.setBounds(273, 521, 397, 23);
		radioButton_7.setActionCommand("radioButton_7");
		contentPane.add(radioButton_7);
		
		radioButton_8 = new JRadioButton("Spending too much time for watching TV may cause kids inactive");
		radioButton_8.setBounds(273, 550, 406, 23);
		radioButton_8.setActionCommand("radioButton_8");
		contentPane.add(radioButton_8);
		
		group3 = new ButtonGroup();
		group3.add(radioButton_5);
		group3.add(radioButton_6);
		group3.add(radioButton_7);
		group3.add(radioButton_8);
		
		JLabel label_2 = new JLabel("4. It is hard for a child to sleep because …");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(742, 294, 206, 14);
		contentPane.add(label_2);
		
		radioButton_9 = new JRadioButton("the parents review TV’s program");
		radioButton_9.setBounds(742, 315, 261, 23);
		radioButton_9.setActionCommand("radioButton_9");
		contentPane.add(radioButton_9);
		
		radioButton_10 = new JRadioButton("the parents limit the time to watch TV");
		radioButton_10.setBounds(742, 344, 261, 23);
		radioButton_10.setActionCommand("radioButton_10");
		contentPane.add(radioButton_10);
		
		radioButton_11 = new JRadioButton("the kid watches TV with his/her parents");
		radioButton_11.setBounds(1005, 315, 316, 23);
		radioButton_11.setActionCommand("radioButton_11");
		contentPane.add(radioButton_11);
		
		radioButton_12 = new JRadioButton("the kid watches too much TV at bedtime");
		radioButton_12.setBounds(1005, 344, 305, 23);
		radioButton_12.setActionCommand("radioButton_12");
		contentPane.add(radioButton_12);
		
		group4 = new ButtonGroup();
		group4.add(radioButton_9);
		group4.add(radioButton_10);
		group4.add(radioButton_11);
		group4.add(radioButton_12);
		
		JLabel lblHeHas = new JLabel("5. He has \u2026 The room");
		lblHeHas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeHas.setBounds(742, 397, 206, 14);
		contentPane.add(lblHeHas);
		
		rdbtnClean = new JRadioButton("Clean");
		rdbtnClean.setBounds(742, 418, 261, 23);
		rdbtnClean.setActionCommand("rdbtnClean");
		contentPane.add(rdbtnClean);
		
		rdbtnCleaned = new JRadioButton("Cleaned");
		rdbtnCleaned.setBounds(742, 447, 261, 23);
		rdbtnCleaned.setActionCommand("rdbtnCleaned");
		contentPane.add(rdbtnCleaned);
		
		rdbtnClear = new JRadioButton("Clear");
		rdbtnClear.setBounds(1005, 418, 316, 23);
		rdbtnClear.setActionCommand("rdbtnClear");
		contentPane.add(rdbtnClear);
		
		rdbtnCleans = new JRadioButton("Cleans");
		rdbtnCleans.setBounds(1005, 447, 305, 23);
		rdbtnCleans.setActionCommand("rdbtnCleans");
		contentPane.add(rdbtnCleans);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnClean);
		group5.add(rdbtnCleaned);
		group5.add(rdbtnClear);
		group5.add(rdbtnCleans);
		
		JLabel lblAllStudents = new JLabel("6. All students of STTI I-Tech semester 6 \u2026 Writing scientific research");
		lblAllStudents.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAllStudents.setBounds(742, 500, 597, 14);
		contentPane.add(lblAllStudents);
		
		rdbtnIs = new JRadioButton("Is");
		rdbtnIs.setBounds(742, 521, 261, 23);
		rdbtnIs.setActionCommand("rdbtnIs");
		contentPane.add(rdbtnIs);
		
		rdbtnWas = new JRadioButton("Was");
		rdbtnWas.setBounds(742, 550, 261, 23);
		rdbtnWas.setActionCommand("rdbtnWas");
		contentPane.add(rdbtnWas);
		
		rdbtnAre = new JRadioButton("Are");
		rdbtnAre.setBounds(1005, 521, 316, 23);
		rdbtnAre.setActionCommand("rdbtnAre");
		contentPane.add(rdbtnAre);
		
		rdbtnHave = new JRadioButton("have");
		rdbtnHave.setBounds(1005, 550, 305, 23);
		rdbtnHave.setActionCommand("rdbtnHave");
		contentPane.add(rdbtnHave);
		
		group6 = new ButtonGroup();
		group6.add(rdbtnIs);
		group6.add(rdbtnWas);
		group6.add(rdbtnAre);
		group6.add(rdbtnHave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{					
				//clear all files				
				tp.emptyFile("qm.dat");	
				tp.emptyFile("qq0.dat");	
				tp.emptyFile("qq1.dat");	
				tp.emptyFile("qq2.dat");	
				tp.emptyFile("qq3.dat");	
				tp.emptyFile("qq4.dat");	
				tp.emptyFile("qq5.dat");	
				tp.emptyFile("qq6.dat");	
				tp.emptyFile("qq7.dat");	
				tp.emptyFile("qq8.dat");				
				
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
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
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 1");
					else if(group2.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 2");
					else if(group3.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 3");
					else if(group4.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 4");
					else if(group5.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 5");
					else if(group6.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 6");
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qm.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();
						tp.appendFile("qm.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();
						tp.appendFile("qm.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qm.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qm.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qm.dat", s5);
						
						ButtonModel b6 = group6.getSelection();        	
						String s6 = b6.getActionCommand();
						tp.appendFile("qm.dat", s6);
						
						setVisible(false);
						quiz qz = new quiz();
						qz.setVisible(true);
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
