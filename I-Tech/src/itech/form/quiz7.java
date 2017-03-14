package itech.form;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class quiz7 extends JFrame
{	
	private static final long serialVersionUID = 1L;
	TempPendaftaran tp = new TempPendaftaran();
	private JPanel contentPane;
	public ButtonGroup group, group2, group3, group4, group5, group6, group7, group8, group9, group10 = null;
	public JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3;
	public JRadioButton rdbtnNewRadioButton_4, rdbtnNewRadioButton_5, rdbtnNewRadioButton_6, rdbtnNewRadioButton_7;
	public JRadioButton rdbtnNewRadioButton_8, rdbtnNewRadioButton_9, rdbtnNewRadioButton_10, rdbtnNewRadioButton_11;
	public JRadioButton rdbtnNewRadioButton_12, rdbtnNewRadioButton_13, rdbtnNewRadioButton_14, rdbtnNewRadioButton_15;
	public JRadioButton rdbtnNewRadioButton_16, rdbtnNewRadioButton_17, rdbtnNewRadioButton_18, rdbtnNewRadioButton_19;
	public JRadioButton rdbtnNewRadioButton_20, rdbtnNewRadioButton_21, rdbtnNewRadioButton_22, rdbtnNewRadioButton_23;
	public JRadioButton rdbtnNewRadioButton_24, rdbtnNewRadioButton_25, rdbtnNewRadioButton_26, rdbtnNewRadioButton_27;
	public JRadioButton rdbtnNewRadioButton_28, rdbtnNewRadioButton_29, rdbtnNewRadioButton_30, rdbtnNewRadioButton_31;
	public JRadioButton rdbtnNewRadioButton_32, rdbtnNewRadioButton_33, rdbtnNewRadioButton_34, rdbtnNewRadioButton_35;
	public JRadioButton rdbtnNewRadioButton_36, rdbtnNewRadioButton_37, rdbtnNewRadioButton_38, rdbtnNewRadioButton_39;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					quiz7 frame = new quiz7();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public quiz7()
	{
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowActivated(WindowEvent e)
			{
				File file = new File("qq7.dat");
				
				if(file.length() != 0)
				{
					String n1 = tp.getSpecificFile("qq7.dat", 1);				
					if (n1.equals("rdbtnNewRadioButton"))
						rdbtnNewRadioButton.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_1"))
						rdbtnNewRadioButton_1.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_2"))
						rdbtnNewRadioButton_2.setSelected(true);
					else if(n1.equals("rdbtnNewRadioButton_3"))
						rdbtnNewRadioButton_3.setSelected(true);
					
					String n2 = tp.getSpecificFile("qq7.dat", 2);				
					if (n2.equals("rdbtnNewRadioButton_4"))
						rdbtnNewRadioButton_4.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_5"))
						rdbtnNewRadioButton_5.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_6"))
						rdbtnNewRadioButton_6.setSelected(true);
					else if(n2.equals("rdbtnNewRadioButton_7"))
						rdbtnNewRadioButton_7.setSelected(true);
					
					String n3 = tp.getSpecificFile("qq7.dat", 3);				
					if (n3.equals("rdbtnNewRadioButton_8"))
						rdbtnNewRadioButton_8.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_9"))
						rdbtnNewRadioButton_9.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_10"))
						rdbtnNewRadioButton_10.setSelected(true);
					else if(n3.equals("rdbtnNewRadioButton_11"))
						rdbtnNewRadioButton_11.setSelected(true);
					
					String n4 = tp.getSpecificFile("qq7.dat", 4);				
					if (n4.equals("rdbtnNewRadioButton_12"))
						rdbtnNewRadioButton_12.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_13"))
						rdbtnNewRadioButton_13.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_14"))
						rdbtnNewRadioButton_14.setSelected(true);
					else if(n4.equals("rdbtnNewRadioButton_15"))
						rdbtnNewRadioButton_15.setSelected(true);
					
					String n5 = tp.getSpecificFile("qq7.dat", 5);				
					if (n5.equals("rdbtnNewRadioButton_16"))
						rdbtnNewRadioButton_16.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_17"))
						rdbtnNewRadioButton_17.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_18"))
						rdbtnNewRadioButton_18.setSelected(true);
					else if(n5.equals("rdbtnNewRadioButton_19"))
						rdbtnNewRadioButton_19.setSelected(true);
					
					String n6 = tp.getSpecificFile("qq7.dat", 6);				
					if (n6.equals("rdbtnNewRadioButton_20"))
						rdbtnNewRadioButton_20.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_21"))
						rdbtnNewRadioButton_21.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_22"))
						rdbtnNewRadioButton_22.setSelected(true);
					else if(n6.equals("rdbtnNewRadioButton_23"))
						rdbtnNewRadioButton_23.setSelected(true);
					
					String n7 = tp.getSpecificFile("qq7.dat", 7);				
					if (n7.equals("rdbtnNewRadioButton_24"))
						rdbtnNewRadioButton_24.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_25"))
						rdbtnNewRadioButton_25.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_26"))
						rdbtnNewRadioButton_26.setSelected(true);
					else if(n7.equals("rdbtnNewRadioButton_27"))
						rdbtnNewRadioButton_27.setSelected(true);
					
					String n8 = tp.getSpecificFile("qq7.dat", 8);				
					if (n8.equals("rdbtnNewRadioButton_28"))
						rdbtnNewRadioButton_28.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_29"))
						rdbtnNewRadioButton_29.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_30"))
						rdbtnNewRadioButton_30.setSelected(true);
					else if(n8.equals("rdbtnNewRadioButton_31"))
						rdbtnNewRadioButton_31.setSelected(true);
					
					String n9 = tp.getSpecificFile("qq7.dat", 9);				
					if (n9.equals("rdbtnNewRadioButton_32"))
						rdbtnNewRadioButton_32.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_33"))
						rdbtnNewRadioButton_33.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_34"))
						rdbtnNewRadioButton_34.setSelected(true);
					else if(n9.equals("rdbtnNewRadioButton_35"))
						rdbtnNewRadioButton_35.setSelected(true);
					
					String n10 = tp.getSpecificFile("qq7.dat", 10);				
					if (n10.equals("rdbtnNewRadioButton_36"))
						rdbtnNewRadioButton_36.setSelected(true);
					else if(n10.equals("rdbtnNewRadioButton_37"))
						rdbtnNewRadioButton_37.setSelected(true);
					else if(n10.equals("rdbtnNewRadioButton_38"))
						rdbtnNewRadioButton_38.setSelected(true);
					else if(n10.equals("rdbtnNewRadioButton_39"))
						rdbtnNewRadioButton_39.setSelected(true);
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
		
		JLabel lblNewLabel = new JLabel("11. Hardware yang fungsinya sama dengan port LAN dan Ethernet Card tetapi digunakan untuk");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 600, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Macintosh adalah \u2026");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 32, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		rdbtnNewRadioButton = new JRadioButton("Ethertalk Card");
		rdbtnNewRadioButton.setBounds(20, 53, 150, 23);
		rdbtnNewRadioButton.setActionCommand("rdbtnNewRadioButton");
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Media Access Control");
		rdbtnNewRadioButton_1.setBounds(20, 74, 150, 23);
		rdbtnNewRadioButton_1.setActionCommand("rdbtnNewRadioButton_1");
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Network Interface Card");
		rdbtnNewRadioButton_2.setBounds(273, 53, 200, 23);
		rdbtnNewRadioButton_2.setActionCommand("rdbtnNewRadioButton_2");
		contentPane.add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Localtalk Connector");
		rdbtnNewRadioButton_3.setBounds(273, 74, 150, 23);
		rdbtnNewRadioButton_3.setActionCommand("rdbtnNewRadioButton_3");
		contentPane.add(rdbtnNewRadioButton_3);
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("12. Software khusus sebagai search engine di bawah ini adalah \u2026");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 115, 460, 14);
		contentPane.add(lblNewLabel_2);
		
		rdbtnNewRadioButton_4 = new JRadioButton("www.plasa.com");
		rdbtnNewRadioButton_4.setBounds(20, 136, 150, 23);
		rdbtnNewRadioButton_4.setActionCommand("rdbtnNewRadioButton_4");
		contentPane.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("www.detik.com");
		rdbtnNewRadioButton_5.setBounds(20, 157, 150, 23);
		rdbtnNewRadioButton_5.setActionCommand("rdbtnNewRadioButton_5");
		contentPane.add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("www.google.com");
		rdbtnNewRadioButton_6.setBounds(273, 136, 150, 23);
		rdbtnNewRadioButton_6.setActionCommand("rdbtnNewRadioButton_6");
		contentPane.add(rdbtnNewRadioButton_6);
		
		rdbtnNewRadioButton_7 = new JRadioButton("www.opera.com");
		rdbtnNewRadioButton_7.setBounds(273, 157, 150, 23);
		rdbtnNewRadioButton_7.setActionCommand("rdbtnNewRadioButton_7");
		contentPane.add(rdbtnNewRadioButton_7);
		
		group2 = new ButtonGroup();
		group2.add(rdbtnNewRadioButton_4);
		group2.add(rdbtnNewRadioButton_5);
		group2.add(rdbtnNewRadioButton_6);
		group2.add(rdbtnNewRadioButton_7);
		
		JLabel lblNewLabel_3 = new JLabel("13. www singkatan dari :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(20, 198, 299, 14);
		contentPane.add(lblNewLabel_3);
		
		rdbtnNewRadioButton_8 = new JRadioButton("wide web world");
		rdbtnNewRadioButton_8.setBounds(20, 219, 150, 23);
		rdbtnNewRadioButton_8.setActionCommand("rdbtnNewRadioButton_8");
		contentPane.add(rdbtnNewRadioButton_8);
		
		rdbtnNewRadioButton_9 = new JRadioButton("web wide world");
		rdbtnNewRadioButton_9.setBounds(20, 240, 150, 23);
		rdbtnNewRadioButton_9.setActionCommand("rdbtnNewRadioButton_9");
		contentPane.add(rdbtnNewRadioButton_9);
		
		rdbtnNewRadioButton_10 = new JRadioButton("world web wide");
		rdbtnNewRadioButton_10.setBounds(273, 219, 150, 23);
		rdbtnNewRadioButton_10.setActionCommand("rdbtnNewRadioButton_10");
		contentPane.add(rdbtnNewRadioButton_10);
		
		rdbtnNewRadioButton_11 = new JRadioButton("world wide web");
		rdbtnNewRadioButton_11.setBounds(273, 240, 150, 23);
		rdbtnNewRadioButton_11.setActionCommand("rdbtnNewRadioButton_11");
		contentPane.add(rdbtnNewRadioButton_11);
		
		group3 = new ButtonGroup();
		group3.add(rdbtnNewRadioButton_8);
		group3.add(rdbtnNewRadioButton_9);
		group3.add(rdbtnNewRadioButton_10);
		group3.add(rdbtnNewRadioButton_11);
		
		JLabel lblNewLabel_4 = new JLabel("14. Untuk tersambung ke jaringan internet, pengguna harus menggunakan layanan khusus yang");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(20, 281, 650, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("disebut dengan \u2026");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(20, 302, 150, 14);
		contentPane.add(lblNewLabel_5);
		
		rdbtnNewRadioButton_12 = new JRadioButton("ISDN");
		rdbtnNewRadioButton_12.setBounds(20, 323, 109, 23);
		rdbtnNewRadioButton_12.setActionCommand("rdbtnNewRadioButton_12");
		contentPane.add(rdbtnNewRadioButton_12);
		
		rdbtnNewRadioButton_13 = new JRadioButton("ISP");
		rdbtnNewRadioButton_13.setBounds(20, 344, 109, 23);
		rdbtnNewRadioButton_13.setActionCommand("rdbtnNewRadioButton_13");
		contentPane.add(rdbtnNewRadioButton_13);
		
		rdbtnNewRadioButton_14 = new JRadioButton("WAP");
		rdbtnNewRadioButton_14.setBounds(273, 323, 109, 23);
		rdbtnNewRadioButton_14.setActionCommand("rdbtnNewRadioButton_14");
		contentPane.add(rdbtnNewRadioButton_14);
		
		rdbtnNewRadioButton_15 = new JRadioButton("NSP");
		rdbtnNewRadioButton_15.setBounds(273, 344, 109, 23);
		rdbtnNewRadioButton_15.setActionCommand("rdbtnNewRadioButton_15");
		contentPane.add(rdbtnNewRadioButton_15);
		
		group4 = new ButtonGroup();
		group4.add(rdbtnNewRadioButton_12);
		group4.add(rdbtnNewRadioButton_13);
		group4.add(rdbtnNewRadioButton_14);
		group4.add(rdbtnNewRadioButton_15);
		
		JLabel lblNewLabel_6 = new JLabel("15. Alat yang berfungsi untuk menampilkan informasi dari komputer disebut \u2026");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(20, 385, 550, 14);
		contentPane.add(lblNewLabel_6);
		
		rdbtnNewRadioButton_16 = new JRadioButton("Mouse");
		rdbtnNewRadioButton_16.setBounds(20, 406, 109, 23);
		rdbtnNewRadioButton_16.setActionCommand("rdbtnNewRadioButton_16");
		contentPane.add(rdbtnNewRadioButton_16);
		
		rdbtnNewRadioButton_17 = new JRadioButton("Speaker");
		rdbtnNewRadioButton_17.setBounds(20, 427, 109, 23);
		rdbtnNewRadioButton_17.setActionCommand("rdbtnNewRadioButton_17");
		contentPane.add(rdbtnNewRadioButton_17);
		
		rdbtnNewRadioButton_18 = new JRadioButton("Monitor");
		rdbtnNewRadioButton_18.setBounds(273, 406, 109, 23);
		rdbtnNewRadioButton_18.setActionCommand("rdbtnNewRadioButton_18");
		contentPane.add(rdbtnNewRadioButton_18);
		
		rdbtnNewRadioButton_19 = new JRadioButton("Modem");
		rdbtnNewRadioButton_19.setBounds(273, 427, 109, 23);
		rdbtnNewRadioButton_19.setActionCommand("rdbtnNewRadioButton_19");
		contentPane.add(rdbtnNewRadioButton_19);
		
		group5 = new ButtonGroup();
		group5.add(rdbtnNewRadioButton_16);
		group5.add(rdbtnNewRadioButton_17);
		group5.add(rdbtnNewRadioButton_18);
		group5.add(rdbtnNewRadioButton_19);
		
		JLabel lblNewLabel_7 = new JLabel("16. Pusat pengolahan data dan pengontrolan kerja komputer adalah \u2026");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(742, 11, 550, 14);
		contentPane.add(lblNewLabel_7);
		
		rdbtnNewRadioButton_20 = new JRadioButton("Monitor");
		rdbtnNewRadioButton_20.setBounds(742, 32, 109, 23);
		rdbtnNewRadioButton_20.setActionCommand("rdbtnNewRadioButton_20");
		contentPane.add(rdbtnNewRadioButton_20);
		
		rdbtnNewRadioButton_21 = new JRadioButton("Keyboard");
		rdbtnNewRadioButton_21.setBounds(742, 53, 109, 23);
		rdbtnNewRadioButton_21.setActionCommand("rdbtnNewRadioButton_21");
		contentPane.add(rdbtnNewRadioButton_21);
		
		rdbtnNewRadioButton_22 = new JRadioButton("CPU");
		rdbtnNewRadioButton_22.setBounds(1005, 32, 109, 23);
		rdbtnNewRadioButton_22.setActionCommand("rdbtnNewRadioButton_22");
		contentPane.add(rdbtnNewRadioButton_22);
		
		rdbtnNewRadioButton_23 = new JRadioButton("Mouse");
		rdbtnNewRadioButton_23.setBounds(1005, 53, 109, 23);
		rdbtnNewRadioButton_23.setActionCommand("rdbtnNewRadioButton_23");
		contentPane.add(rdbtnNewRadioButton_23);
		
		group6 = new ButtonGroup();
		group6.add(rdbtnNewRadioButton_20);
		group6.add(rdbtnNewRadioButton_21);
		group6.add(rdbtnNewRadioButton_22);
		group6.add(rdbtnNewRadioButton_23);
		
		JLabel lblNewLabel_8 = new JLabel("17. Fungsi ikon bold (B) dalam MS. Word adalah \u2026");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(742, 94, 450, 14);
		contentPane.add(lblNewLabel_8);
		
		rdbtnNewRadioButton_24 = new JRadioButton("membuat huruf capital");
		rdbtnNewRadioButton_24.setBounds(742, 115, 155, 23);
		rdbtnNewRadioButton_24.setActionCommand("rdbtnNewRadioButton_24");
		contentPane.add(rdbtnNewRadioButton_24);
		
		rdbtnNewRadioButton_25 = new JRadioButton("memberi efek miring");
		rdbtnNewRadioButton_25.setBounds(742, 136, 166, 23);
		rdbtnNewRadioButton_25.setActionCommand("rdbtnNewRadioButton_25");
		contentPane.add(rdbtnNewRadioButton_25);
		
		rdbtnNewRadioButton_26 = new JRadioButton("memberikan efek tebal");
		rdbtnNewRadioButton_26.setBounds(1005, 115, 156, 23);
		rdbtnNewRadioButton_26.setActionCommand("rdbtnNewRadioButton_26");
		contentPane.add(rdbtnNewRadioButton_26);
		
		rdbtnNewRadioButton_27 = new JRadioButton("menutup file dokumen");
		rdbtnNewRadioButton_27.setBounds(1005, 136, 155, 23);
		rdbtnNewRadioButton_27.setActionCommand("rdbtnNewRadioButton_27");
		contentPane.add(rdbtnNewRadioButton_27);
		
		group7 = new ButtonGroup();
		group7.add(rdbtnNewRadioButton_24);
		group7.add(rdbtnNewRadioButton_25);
		group7.add(rdbtnNewRadioButton_26);
		group7.add(rdbtnNewRadioButton_27);
		
		JLabel lblNewLabel_9 = new JLabel("18. Di bawah ini sub menu yang tidak terdapat dalam menu File adalah ...");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(742, 177, 450, 14);
		contentPane.add(lblNewLabel_9);
		
		rdbtnNewRadioButton_28 = new JRadioButton("New");
		rdbtnNewRadioButton_28.setBounds(742, 198, 109, 23);
		rdbtnNewRadioButton_28.setActionCommand("rdbtnNewRadioButton_28");
		contentPane.add(rdbtnNewRadioButton_28);
		
		rdbtnNewRadioButton_29 = new JRadioButton("Open");
		rdbtnNewRadioButton_29.setBounds(742, 219, 109, 23);
		rdbtnNewRadioButton_29.setActionCommand("rdbtnNewRadioButton_29");
		contentPane.add(rdbtnNewRadioButton_29);
		
		rdbtnNewRadioButton_30 = new JRadioButton("Cut");
		rdbtnNewRadioButton_30.setBounds(1005, 198, 109, 23);
		rdbtnNewRadioButton_30.setActionCommand("rdbtnNewRadioButton_30");
		contentPane.add(rdbtnNewRadioButton_30);
		
		rdbtnNewRadioButton_31 = new JRadioButton("Save As");
		rdbtnNewRadioButton_31.setBounds(1005, 219, 109, 23);
		rdbtnNewRadioButton_31.setActionCommand("rdbtnNewRadioButton_31");
		contentPane.add(rdbtnNewRadioButton_31);
		
		group8 = new ButtonGroup();
		group8.add(rdbtnNewRadioButton_28);
		group8.add(rdbtnNewRadioButton_29);
		group8.add(rdbtnNewRadioButton_30);
		group8.add(rdbtnNewRadioButton_31);
		
		JLabel lblNewLabel_10 = new JLabel("19. Ikon yang digunakan untuk meratakan ketikan rata kanan adalah \u2026");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10.setBounds(742, 260, 550, 14);
		contentPane.add(lblNewLabel_10);
		
		rdbtnNewRadioButton_32 = new JRadioButton("Align Left");
		rdbtnNewRadioButton_32.setBounds(742, 281, 109, 23);
		rdbtnNewRadioButton_32.setActionCommand("rdbtnNewRadioButton_32");
		contentPane.add(rdbtnNewRadioButton_32);
		
		rdbtnNewRadioButton_33 = new JRadioButton("Align Right");
		rdbtnNewRadioButton_33.setBounds(742, 302, 109, 23);
		rdbtnNewRadioButton_33.setActionCommand("rdbtnNewRadioButton_33");
		contentPane.add(rdbtnNewRadioButton_33);
		
		rdbtnNewRadioButton_34 = new JRadioButton("Center");
		rdbtnNewRadioButton_34.setBounds(1005, 281, 109, 23);
		rdbtnNewRadioButton_34.setActionCommand("rdbtnNewRadioButton_34");
		contentPane.add(rdbtnNewRadioButton_34);
		
		rdbtnNewRadioButton_35 = new JRadioButton("Justify");
		rdbtnNewRadioButton_35.setBounds(1005, 302, 109, 23);
		rdbtnNewRadioButton_35.setActionCommand("rdbtnNewRadioButton_35");
		contentPane.add(rdbtnNewRadioButton_35);
		
		group9 = new ButtonGroup();
		group9.add(rdbtnNewRadioButton_32);
		group9.add(rdbtnNewRadioButton_33);
		group9.add(rdbtnNewRadioButton_34);
		group9.add(rdbtnNewRadioButton_35);
		
		JLabel lblNewLabel_11 = new JLabel("20. Berikut ini merupakan hardware yang bisa menghubungkan komputer dengan");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_11.setBounds(742, 343, 550, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("internet kecuali \u2026");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(742, 364, 150, 14);
		contentPane.add(lblNewLabel_12);
		
		rdbtnNewRadioButton_36 = new JRadioButton("Line telepon");
		rdbtnNewRadioButton_36.setBounds(742, 385, 109, 23);
		rdbtnNewRadioButton_36.setActionCommand("rdbtnNewRadioButton_36");
		contentPane.add(rdbtnNewRadioButton_36);
		
		rdbtnNewRadioButton_37 = new JRadioButton("Satelit");
		rdbtnNewRadioButton_37.setBounds(742, 406, 109, 23);
		rdbtnNewRadioButton_37.setActionCommand("rdbtnNewRadioButton_37");
		contentPane.add(rdbtnNewRadioButton_37);
		
		rdbtnNewRadioButton_38 = new JRadioButton("TV Kabel");
		rdbtnNewRadioButton_38.setBounds(1005, 385, 109, 23);
		rdbtnNewRadioButton_38.setActionCommand("rdbtnNewRadioButton_38");
		contentPane.add(rdbtnNewRadioButton_38);
		
		rdbtnNewRadioButton_39 = new JRadioButton("TV tuner");
		rdbtnNewRadioButton_39.setBounds(1005, 406, 109, 23);
		rdbtnNewRadioButton_39.setActionCommand("rdbtnNewRadioButton_39");
		contentPane.add(rdbtnNewRadioButton_39);
		
		group10 = new ButtonGroup();
		group10.add(rdbtnNewRadioButton_36);
		group10.add(rdbtnNewRadioButton_37);
		group10.add(rdbtnNewRadioButton_38);
		group10.add(rdbtnNewRadioButton_39);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				//clear file first					
				tp.emptyFile("qq7.dat");						
					
				ButtonModel b1 = group.getSelection();        	
				String s1;
				if(group.getSelection() == null)
					s1 = "-";			
				else			
					s1 = b1.getActionCommand();		
				tp.appendFile("qq7.dat", s1);
					
				ButtonModel b2 = group2.getSelection();        	
				String s2;
				if(group2.getSelection() == null)
					s2 = "-";			
				else			
					s2 = b2.getActionCommand();		
				tp.appendFile("qq7.dat", s2);
				
				ButtonModel b3 = group3.getSelection();        	
				String s3;
				if(group3.getSelection() == null)
					s3 = "-";			
				else			
					s3 = b3.getActionCommand();		
				tp.appendFile("qq7.dat", s3);
				
				ButtonModel b4 = group4.getSelection();        	
				String s4;
				if(group4.getSelection() == null)
					s4 = "-";			
				else			
					s4 = b4.getActionCommand();		
				tp.appendFile("qq7.dat", s4);
				
				ButtonModel b5 = group5.getSelection();        	
				String s5;
				if(group5.getSelection() == null)
					s5 = "-";			
				else			
					s5 = b5.getActionCommand();		
				tp.appendFile("qq7.dat", s5);	
				
				ButtonModel b6 = group6.getSelection();        	
				String s6;
				if(group6.getSelection() == null)
					s6 = "-";			
				else			
					s6 = b6.getActionCommand();		
				tp.appendFile("qq7.dat", s6);	
				
				ButtonModel b7 = group7.getSelection();        	
				String s7;
				if(group7.getSelection() == null)
					s7 = "-";			
				else			
					s7 = b7.getActionCommand();		
				tp.appendFile("qq7.dat", s7);	
				
				ButtonModel b8 = group8.getSelection();        	
				String s8;
				if(group8.getSelection() == null)
					s8 = "-";			
				else			
					s8 = b8.getActionCommand();		
				tp.appendFile("qq7.dat", s8);	
					
				ButtonModel b9 = group9.getSelection();        	
				String s9;
				if(group9.getSelection() == null)
					s9 = "-";			
				else			
					s9 = b9.getActionCommand();		
				tp.appendFile("qq7.dat", s9);	
				
				ButtonModel b10 = group10.getSelection();        	
				String s10;
				if(group10.getSelection() == null)
					s10 = "-";			
				else			
					s10 = b10.getActionCommand();		
				tp.appendFile("qq7.dat", s10);						
			
				setVisible(false);
				quiz6 qz6 = new quiz6();
				qz6.setVisible(true);
			}
		});
		button.setBounds(20, 577, 89, 23);
		contentPane.add(button);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{                   
					if(group.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 11");
					else if(group2.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 12");
					else if(group3.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 13");
					else if(group4.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 14");
					else if(group5.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 15");	
					else if(group6.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 16");	
					else if(group7.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 17");	
					else if(group8.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 18");	
					else if(group9.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 19");	
					else if(group10.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan isi soal nomor 20");	
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("qq7.dat");						
						
						ButtonModel b1 = group.getSelection();        	
						String s1 = b1.getActionCommand();
						tp.appendFile("qq7.dat", s1);
						
						ButtonModel b2 = group2.getSelection();        	
						String s2 = b2.getActionCommand();
						tp.appendFile("qq7.dat", s2);
						
						ButtonModel b3 = group3.getSelection();        	
						String s3 = b3.getActionCommand();
						tp.appendFile("qq7.dat", s3);
						
						ButtonModel b4 = group4.getSelection();        	
						String s4 = b4.getActionCommand();
						tp.appendFile("qq7.dat", s4);
						
						ButtonModel b5 = group5.getSelection();        	
						String s5 = b5.getActionCommand();
						tp.appendFile("qq7.dat", s5);	
						
						ButtonModel b6 = group6.getSelection();        	
						String s6 = b6.getActionCommand();
						tp.appendFile("qq7.dat", s6);	
						
						ButtonModel b7 = group7.getSelection();        	
						String s7 = b7.getActionCommand();
						tp.appendFile("qq7.dat", s7);	
						
						ButtonModel b8 = group8.getSelection();        	
						String s8 = b8.getActionCommand();
						tp.appendFile("qq7.dat", s8);	
						
						ButtonModel b9 = group9.getSelection();        	
						String s9 = b9.getActionCommand();
						tp.appendFile("qq7.dat", s9);	
						
						ButtonModel b10 = group10.getSelection();        	
						String s10 = b10.getActionCommand();
						tp.appendFile("qq7.dat", s10);	
						
						setVisible(false);
						quiz8 qz8 = new quiz8();
						qz8.setVisible(true);
					}						
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}					
			}
		});
		btnNext.setBounds(119, 577, 89, 23);
		contentPane.add(btnNext);
	}

}
