package itech.form;

import itech.funct.GradeFunct;
import itech.funct.QuizFunct;
import itech.funct.SeqNumber;
import itech.funct.TempPendaftaran;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class quiz8 extends JFrame 
{	
	private static final long serialVersionUID = 1L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public ButtonGroup group, group2, group3, group4, group5, group6, group7 = null;
	public JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3;
	public JRadioButton rdbtnNewRadioButton_4, rdbtnNewRadioButton_5, rdbtnNewRadioButton_6, rdbtnNewRadioButton_7;
	public JRadioButton rdbtnNewRadioButton_8, rdbtnNewRadioButton_9, rdbtnNewRadioButton_10, rdbtnNewRadioButton_11;
	public JRadioButton rdbtnNewRadioButton_12, rdbtnNewRadioButton_13, rdbtnNewRadioButton_14, rdbtnNewRadioButton_15;
	public JRadioButton rdbtnNewRadioButton_16, rdbtnNewRadioButton_17, rdbtnNewRadioButton_18, rdbtnNewRadioButton_19;
	public JRadioButton rdbtnNewRadioButton_20, rdbtnNewRadioButton_21, rdbtnNewRadioButton_22, rdbtnNewRadioButton_23;
	public JRadioButton rdbtnNewRadioButton_24, rdbtnNewRadioButton_25, rdbtnNewRadioButton_26, rdbtnNewRadioButton_27;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try
				{
					quiz8 frame = new quiz8();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public quiz8()
	{
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowActivated(WindowEvent e)
			{
				File file = new File("qq8.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qq8.dat", 1);				
					if (n1.equals("rdbtnNewRadioButton"))
						rdbtnNewRadioButton.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_1"))
						rdbtnNewRadioButton_1.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_2"))
						rdbtnNewRadioButton_2.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_3"))
						rdbtnNewRadioButton_3.setSelected(true);
					
					String n2 = tp.getSpecificFile("qq8.dat", 2);				
					if (n2.equals("rdbtnNewRadioButton_4"))
						rdbtnNewRadioButton_4.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_5"))
						rdbtnNewRadioButton_5.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_6"))
						rdbtnNewRadioButton_6.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_7"))
						rdbtnNewRadioButton_7.setSelected(true);
					
					String n3 = tp.getSpecificFile("qq8.dat", 3);				
					if (n3.equals("rdbtnNewRadioButton_8"))
						rdbtnNewRadioButton_8.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_9"))
						rdbtnNewRadioButton_9.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_10"))
						rdbtnNewRadioButton_10.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_11"))
						rdbtnNewRadioButton_11.setSelected(true);
					
					String n4 = tp.getSpecificFile("qq8.dat", 4);				
					if (n4.equals("rdbtnNewRadioButton_12"))
						rdbtnNewRadioButton_12.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_13"))
						rdbtnNewRadioButton_13.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_14"))
						rdbtnNewRadioButton_14.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_15"))
						rdbtnNewRadioButton_15.setSelected(true);
					
					String n5 = tp.getSpecificFile("qq8.dat", 5);				
					if (n5.equals("rdbtnNewRadioButton_16"))
						rdbtnNewRadioButton_16.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_17"))
						rdbtnNewRadioButton_17.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_18"))
						rdbtnNewRadioButton_18.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_19"))
						rdbtnNewRadioButton_19.setSelected(true);
					
					String n6 = tp.getSpecificFile("qq8.dat", 6);				
					if (n6.equals("rdbtnNewRadioButton_20"))
						rdbtnNewRadioButton_20.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_21"))
						rdbtnNewRadioButton_21.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_22"))
						rdbtnNewRadioButton_22.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_23"))
						rdbtnNewRadioButton_23.setSelected(true);
					
					String n7 = tp.getSpecificFile("qq8.dat", 7);				
					if (n7.equals("rdbtnNewRadioButton_24"))
						rdbtnNewRadioButton_24.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_25"))
						rdbtnNewRadioButton_25.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_26"))
						rdbtnNewRadioButton_26.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_27"))
						rdbtnNewRadioButton_27.setSelected(true);					
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
		
		JLabel lblNewLabel = new JLabel("21.\t Untuk menyimpan gambar yang anda peroleh melalui Internet anda harus memilih pilihan :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 600, 14);
		contentPane.add(lblNewLabel);
		
		rdbtnNewRadioButton = new JRadioButton("Save");
		rdbtnNewRadioButton.setBounds(20, 32, 109, 23);
		rdbtnNewRadioButton.setActionCommand("rdbtnNewRadioButton");
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("e-mail picture");
		rdbtnNewRadioButton_1.setBounds(20, 53, 109, 23);
		rdbtnNewRadioButton_1.setActionCommand("rdbtnNewRadioButton_1");
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("download picture");
		rdbtnNewRadioButton_2.setBounds(273, 32, 150, 23);
		rdbtnNewRadioButton_2.setActionCommand("rdbtnNewRadioButton_2");
		contentPane.add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("save picture");
		rdbtnNewRadioButton_3.setBounds(273, 53, 109, 23);
		rdbtnNewRadioButton_3.setActionCommand("rdbtnNewRadioButton_3");
		contentPane.add(rdbtnNewRadioButton_3);
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("22.\t Yang bukan fungsi dari Router adalah\u2026");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 88, 424, 14);
		contentPane.add(lblNewLabel_1);
		
		rdbtnNewRadioButton_4 = new JRadioButton("mencari jalur yang terbaik dalam mentransmisikan data");
		rdbtnNewRadioButton_4.setBounds(20, 113, 500, 23);
		rdbtnNewRadioButton_4.setActionCommand("rdbtnNewRadioButton_4");
		contentPane.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("mengatur pesan antara dua protokol");
		rdbtnNewRadioButton_5.setBounds(20, 132, 500, 23);
		rdbtnNewRadioButton_5.setActionCommand("rdbtnNewRadioButton_5");
		contentPane.add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("mengatur pesan yang melewati kabel");
		rdbtnNewRadioButton_6.setBounds(20, 153, 500, 23);
		rdbtnNewRadioButton_6.setActionCommand("rdbtnNewRadioButton_6");
		contentPane.add(rdbtnNewRadioButton_6);
		
		rdbtnNewRadioButton_7 = new JRadioButton("menghubungkan komputer dengan Hub");
		rdbtnNewRadioButton_7.setBounds(20, 174, 500, 23);
		rdbtnNewRadioButton_7.setActionCommand("rdbtnNewRadioButton_7");
		contentPane.add(rdbtnNewRadioButton_7);
		
		group2 = new ButtonGroup();
		group2.add(rdbtnNewRadioButton_4);
		group2.add(rdbtnNewRadioButton_5);
		group2.add(rdbtnNewRadioButton_6);
		group2.add(rdbtnNewRadioButton_7);
		
		JLabel lblNewLabel_2 = new JLabel("23.\t Untuk memilih bagian buku kerja yang akan dicetak, kita dapat mengeblok bagian yang akan");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 215, 650, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("dicetak kemudian menekan ...");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(20, 236, 200, 14);
		contentPane.add(lblNewLabel_3);
		
		rdbtnNewRadioButton_8 = new JRadioButton("Print");
		rdbtnNewRadioButton_8.setBounds(20, 257, 109, 23);
		rdbtnNewRadioButton_8.setActionCommand("rdbtnNewRadioButton_8");
		contentPane.add(rdbtnNewRadioButton_8);
		
		rdbtnNewRadioButton_9 = new JRadioButton("Print Area");
		rdbtnNewRadioButton_9.setBounds(20, 278, 109, 23);
		rdbtnNewRadioButton_9.setActionCommand("rdbtnNewRadioButton_9");
		contentPane.add(rdbtnNewRadioButton_9);
		
		rdbtnNewRadioButton_10 = new JRadioButton("Print Preview");
		rdbtnNewRadioButton_10.setBounds(273, 257, 109, 23);
		rdbtnNewRadioButton_10.setActionCommand("rdbtnNewRadioButton_10");
		contentPane.add(rdbtnNewRadioButton_10);
		
		rdbtnNewRadioButton_11 = new JRadioButton("Page Setup");
		rdbtnNewRadioButton_11.setBounds(273, 278, 109, 23);
		rdbtnNewRadioButton_11.setActionCommand("rdbtnNewRadioButton_11");
		contentPane.add(rdbtnNewRadioButton_11);
		
		group3 = new ButtonGroup();
		group3.add(rdbtnNewRadioButton_8);
		group3.add(rdbtnNewRadioButton_9);
		group3.add(rdbtnNewRadioButton_10);
		group3.add(rdbtnNewRadioButton_11);
		
		JLabel lblNewLabel_4 = new JLabel("24.\t File, Edit, View, Insert, Format, Tools, Table, Window, dan Help terdapat dalam \u2026");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(20, 319, 600, 14);
		contentPane.add(lblNewLabel_4);
		
		rdbtnNewRadioButton_12 = new JRadioButton("Formula Bar");
		rdbtnNewRadioButton_12.setBounds(20, 340, 109, 23);
		rdbtnNewRadioButton_12.setActionCommand("rdbtnNewRadioButton_12");
		contentPane.add(rdbtnNewRadioButton_12);
		
		rdbtnNewRadioButton_13 = new JRadioButton("Menu Bar");
		rdbtnNewRadioButton_13.setBounds(20, 361, 109, 23);
		rdbtnNewRadioButton_13.setActionCommand("rdbtnNewRadioButton_13");
		contentPane.add(rdbtnNewRadioButton_13);
		
		rdbtnNewRadioButton_14 = new JRadioButton("Title Bar");
		rdbtnNewRadioButton_14.setBounds(273, 340, 109, 23);
		rdbtnNewRadioButton_14.setActionCommand("rdbtnNewRadioButton_14");
		contentPane.add(rdbtnNewRadioButton_14);
		
		rdbtnNewRadioButton_15 = new JRadioButton("Tab Lembar Kerja");
		rdbtnNewRadioButton_15.setBounds(273, 361, 200, 23);
		rdbtnNewRadioButton_15.setActionCommand("rdbtnNewRadioButton_15");
		contentPane.add(rdbtnNewRadioButton_15);
		
		group4 = new ButtonGroup();
		group4.add(rdbtnNewRadioButton_12);
		group4.add(rdbtnNewRadioButton_13);
		group4.add(rdbtnNewRadioButton_14);
		group4.add(rdbtnNewRadioButton_15);
		
		JLabel lblNewLabel_5 = new JLabel("25.\t Apabila salah dalam memasukkan data, maka kita dapat membatalkan perintah tersebut ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(742, 11, 550, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("dengan menekan \u2026 Formula Bar");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(742, 32, 250, 14);
		contentPane.add(lblNewLabel_6);
		
		rdbtnNewRadioButton_16 = new JRadioButton("Undo");
		rdbtnNewRadioButton_16.setBounds(742, 53, 109, 23);
		rdbtnNewRadioButton_16.setActionCommand("rdbtnNewRadioButton_16");
		contentPane.add(rdbtnNewRadioButton_16);
		
		rdbtnNewRadioButton_17 = new JRadioButton("Repeat");
		rdbtnNewRadioButton_17.setBounds(742, 74, 109, 23);
		rdbtnNewRadioButton_17.setActionCommand("rdbtnNewRadioButton_17");
		contentPane.add(rdbtnNewRadioButton_17);
		
		rdbtnNewRadioButton_18 = new JRadioButton("Cut");
		rdbtnNewRadioButton_18.setBounds(1005, 53, 109, 23);
		rdbtnNewRadioButton_18.setActionCommand("rdbtnNewRadioButton_18");
		contentPane.add(rdbtnNewRadioButton_18);
		
		rdbtnNewRadioButton_19 = new JRadioButton("Copy");
		rdbtnNewRadioButton_19.setBounds(1005, 74, 109, 23);
		rdbtnNewRadioButton_19.setActionCommand("rdbtnNewRadioButton_19");
		contentPane.add(rdbtnNewRadioButton_19);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnNewRadioButton_16);
		group5.add(rdbtnNewRadioButton_17);
		group5.add(rdbtnNewRadioButton_18);
		group5.add(rdbtnNewRadioButton_19);
		
		JLabel lblNewLabel_7 = new JLabel("26.\t Jaringan komputer yang terhubung keseluruh dunia disebut dengan\u2026");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(742, 115, 550, 14);
		contentPane.add(lblNewLabel_7);
		
		rdbtnNewRadioButton_20 = new JRadioButton("Internet");
		rdbtnNewRadioButton_20.setBounds(742, 136, 109, 23);
		rdbtnNewRadioButton_20.setActionCommand("rdbtnNewRadioButton_20");
		contentPane.add(rdbtnNewRadioButton_20);
		
		rdbtnNewRadioButton_21 = new JRadioButton("Intranet");
		rdbtnNewRadioButton_21.setBounds(742, 157, 109, 23);
		rdbtnNewRadioButton_21.setActionCommand("rdbtnNewRadioButton_21");
		contentPane.add(rdbtnNewRadioButton_21);
		
		rdbtnNewRadioButton_22 = new JRadioButton("WWW");
		rdbtnNewRadioButton_22.setBounds(1005, 136, 109, 23);
		rdbtnNewRadioButton_22.setActionCommand("rdbtnNewRadioButton_22");
		contentPane.add(rdbtnNewRadioButton_22);
		
		rdbtnNewRadioButton_23 = new JRadioButton("FTP");
		rdbtnNewRadioButton_23.setBounds(1005, 157, 109, 23);
		rdbtnNewRadioButton_23.setActionCommand("rdbtnNewRadioButton_23");
		contentPane.add(rdbtnNewRadioButton_23);
		
		group6 = new ButtonGroup();
		group6.add(rdbtnNewRadioButton_20);
		group6.add(rdbtnNewRadioButton_21);
		group6.add(rdbtnNewRadioButton_22);
		group6.add(rdbtnNewRadioButton_23);
		
		JLabel lblNewLabel_8 = new JLabel("27.\t Identitas yang digunakan untuk membedakan setiap alamat yang terhubung ke");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(742, 198, 550, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("internet disebut ...");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(742, 219, 200, 14);
		contentPane.add(lblNewLabel_9);
		
		rdbtnNewRadioButton_24 = new JRadioButton("Protokol jaringan");
		rdbtnNewRadioButton_24.setBounds(742, 240, 150, 23);
		rdbtnNewRadioButton_24.setActionCommand("rdbtnNewRadioButton_24");
		contentPane.add(rdbtnNewRadioButton_24);
		
		rdbtnNewRadioButton_25 = new JRadioButton("Address bar");
		rdbtnNewRadioButton_25.setBounds(742, 261, 109, 23);
		rdbtnNewRadioButton_25.setActionCommand("rdbtnNewRadioButton_25");
		contentPane.add(rdbtnNewRadioButton_25);
		
		rdbtnNewRadioButton_26 = new JRadioButton("TCP/IP");
		rdbtnNewRadioButton_26.setBounds(1005, 240, 109, 23);
		rdbtnNewRadioButton_26.setActionCommand("rdbtnNewRadioButton_26");
		contentPane.add(rdbtnNewRadioButton_26);
		
		rdbtnNewRadioButton_27 = new JRadioButton("IP address");
		rdbtnNewRadioButton_27.setBounds(1005, 261, 109, 23);
		rdbtnNewRadioButton_27.setActionCommand("rdbtnNewRadioButton_27");
		contentPane.add(rdbtnNewRadioButton_27);
		
		group7 = new ButtonGroup();
		group7.add(rdbtnNewRadioButton_24);
		group7.add(rdbtnNewRadioButton_25);
		group7.add(rdbtnNewRadioButton_26);
		group7.add(rdbtnNewRadioButton_27);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//clear file first					
				tp.emptyFile("qq8.dat");						
				
				ButtonModel b1 = group.getSelection();        	
				String s1;
				if(group.getSelection() == null)
					s1 = "-";			
				else			
					s1 = b1.getActionCommand();		
				tp.appendFile("qq8.dat", s1);
				
				ButtonModel b2 = group2.getSelection();        	
				String s2;
				if(group2.getSelection() == null)
					s2 = "-";			
				else			
					s2 = b2.getActionCommand();		
				tp.appendFile("qq8.dat", s2);
				
				ButtonModel b3 = group3.getSelection();        	
				String s3;
				if(group3.getSelection() == null)
					s3 = "-";			
				else			
					s3 = b3.getActionCommand();		
				tp.appendFile("qq8.dat", s3);
				
				ButtonModel b4 = group4.getSelection();        	
				String s4;
				if(group4.getSelection() == null)
					s4 = "-";			
				else			
					s4 = b4.getActionCommand();		
				tp.appendFile("qq8.dat", s4);
				
				ButtonModel b5 = group5.getSelection();        	
				String s5;
				if(group5.getSelection() == null)
					s5 = "-";			
				else			
					s5 = b5.getActionCommand();		
				tp.appendFile("qq8.dat", s5);	
				
				ButtonModel b6 = group6.getSelection();        	
				String s6;
				if(group6.getSelection() == null)
					s6 = "-";			
				else			
					s6 = b6.getActionCommand();		
				tp.appendFile("qq8.dat", s6);	
				
				ButtonModel b7 = group7.getSelection();        	
				String s7;
				if(group7.getSelection() == null)
					s7 = "-";			
				else			
					s7 = b7.getActionCommand();		
				tp.appendFile("qq8.dat", s7);				
				
				setVisible(false);
				quiz7 qz7 = new quiz7();
				qz7.setVisible(true);
			}
		});
		button.setBounds(10, 568, 89, 23);
		contentPane.add(button);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() 
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
					else if(group7.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 27");						
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qq8.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();
						tp.appendFile("qq8.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();
						tp.appendFile("qq8.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qq8.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qq8.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qq8.dat", s5);	
						
						ButtonModel b6 = group6.getSelection();        	
						String s6 = b6.getActionCommand();
						tp.appendFile("qq8.dat", s6);	
						
						ButtonModel b7 = group7.getSelection();        	
						String s7 = b7.getActionCommand();
						tp.appendFile("qq8.dat", s7);								
						
						//save final score to database
						SeqNumber sn = new SeqNumber();
				        String valSeqNum = sn.readSeqNumber("qq.dat"); //nomor pendaftar
				        
				        QuizFunct qf = new QuizFunct();
				        String nameScore = qf.getNameScore(valSeqNum); //nama 
				        
				        String finalScore = qf.getTotalScore(); //nilai score
				        
				        GradeFunct gf = new GradeFunct();
				        @SuppressWarnings("static-access")
						String finalPrice = gf.getGrade(Double.parseDouble(finalScore)); //nilai grade
				        
				        if(qf.saveScore(valSeqNum, finalScore, finalPrice))
				        {
				        	JOptionPane.showMessageDialog(null, "Hey " + nameScore + ", your score " + finalScore);
				        	
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
				        	
				        	//show main menu
							setVisible(false);
							Menu menu = new Menu();
							menu.setVisible(true);
				        }
				        else
				        {
				        	JOptionPane.showMessageDialog(null, "Saved Failed");
				        }
					}						
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnFinish.setBounds(109, 568, 89, 23);
		contentPane.add(btnFinish);
	}
}
