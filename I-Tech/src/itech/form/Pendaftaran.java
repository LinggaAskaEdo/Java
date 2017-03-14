package itech.form;

import itech.funct.BukuTamuFunct;
import itech.funct.SeqNumber;
import itech.funct.TempPendaftaran;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

public class Pendaftaran extends JFrame
{	
	private static final long serialVersionUID = -6831534413115395536L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, textField_9, textField_10;
	private JComboBox<?> comboBox;
	private JTextArea textArea;
	private JLabel lblFoto;		
	private final JDateChooser dateChooser = new JDateChooser();
	private JRadioButton rdbtnSSistemInformasi, rdbtnNewRadioButton_1, rdbtnNewRadioButton;
	private JRadioButton rdbtnLakiLaki, rdbtnPerempuan;
	private JRadioButton rdbtnBelumMenikah, rdbtnMenikah;
	private JRadioButton rdbtnBelumBekerja, rdbtnBekerja;
	private JRadioButton rdbtnRp, rdbtnRp_1, rdbtnRp_2;
	private JRadioButton rdbtnOrangTua, rdbtnPribadi, rdbtnLainnya;	
	public ButtonGroup group, groupjkelamin, groupstatusmenikah, groupstatuskerja, grouppenghasilan, grouppembiaya = null;
	public String programStudi, namaLengkap, namaPanggilan, tempatLahir, tanggalLahir, agama, jenisKelamin, alamatTinggal, noTelp, noHp, email, statusNikah, pekerjaan,
		namaBapak, pekerjaanBapak, namaIbu, pekerjaanIbu, penghasilan, biaya;
	private JTextField textField_11;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Pendaftaran frame = new Pendaftaran();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Pendaftaran() 
	{
		addWindowFocusListener(new WindowFocusListener()
		{
			public void windowGainedFocus(WindowEvent e)
			{
				//System.out.println("gain gain gain");
				File file = new File("test.jpg");
				if(file.exists())
				{
					ShowPhoto();
				}	
			}
			public void windowLostFocus(WindowEvent e)
			{
				//System.out.println("gain gain gain");
				File file = new File("test.jpg");
				if(file.exists())
				{
					ShowPhoto();
				}	
			}
		});		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				System.out.println("aktif");
				
				File file = new File("test.jpg");
				if(file.exists())
				{
					ShowPhoto();
				}		
				
				//rdbtnNewRadioButton.setSelected(true);
			}
		});	
		setTitle("Form Pendaftaran Mahasiswa Baru");
		initializeForm();
		centerForm();			
	}
	
	private void centerForm()
	{			
		this.setLocationRelativeTo(getRootPane());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initializeForm()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setBounds(100, 100, 1318, 782);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		
		JLabel lblProgramStudi = new JLabel("PROGRAM STUDI / KONSENTRASI");
		lblProgramStudi.setBounds(142, 11, 208, 14);
		lblProgramStudi.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPilihProgramStudi = new JLabel("Pilih Program Studi");
		lblPilihProgramStudi.setBounds(27, 44, 109, 14);
		
		final JFileChooser fileChooser_1_1 = new JFileChooser();
		fileChooser_1_1.setFileHidingEnabled(true);
		fileChooser_1_1.setBounds(123, 615, 208, 102);
		contentPane.setLayout(null);
		contentPane.add(lblProgramStudi);
		contentPane.add(lblPilihProgramStudi);
		
		rdbtnSSistemInformasi = new JRadioButton("S1 Sistem Informasi - Konsentrasi Multimedia");
		rdbtnSSistemInformasi.setBounds(142, 92, 395, 23);
		rdbtnSSistemInformasi.setActionCommand("S1 Sistem Informasi - Konsentrasi Multimedia");
		contentPane.add(rdbtnSSistemInformasi);
		
		rdbtnNewRadioButton_1 = new JRadioButton("S1 Sistem Informasi - Konsentrasi EIS (Enterprise Information System)");
		rdbtnNewRadioButton_1.setBounds(142, 66, 444, 23);
		rdbtnNewRadioButton_1.setActionCommand("S1 Sistem Informasi - Konsentrasi EIS (Enterprise Information System)");
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton = new JRadioButton("S1 Teknik Informatika - Konsentrasi Networking (CISCO Networking Academy)");
		rdbtnNewRadioButton.setBounds(142, 40, 471, 23);
		rdbtnNewRadioButton.setActionCommand("S1 Teknik Informatika - Konsentrasi Networking (CISCO Networking Academy)");
		contentPane.add(rdbtnNewRadioButton);
		
		group = new ButtonGroup();
		group.add(rdbtnSSistemInformasi);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(142, 180, 295, 278);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);	
		lblFoto.setBorder(border);
		contentPane.add(lblFoto);			
		
		JLabel lblIdentitasDiri = new JLabel("IDENTITAS DIRI");
		lblIdentitasDiri.setBounds(142, 138, 197, 31);
		lblIdentitasDiri.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblIdentitasDiri);
		    
		JLabel lblUploadFotoTerbaru = new JLabel("Upload Foto");
		lblUploadFotoTerbaru.setBounds(27, 180, 116, 14);
		contentPane.add(lblUploadFotoTerbaru);
		
		JLabel lblNamaLengkap = new JLabel("Nama Lengkap");
		lblNamaLengkap.setBounds(27, 472, 96, 14);
		contentPane.add(lblNamaLengkap);
		    
		JLabel lblNamaPanggilan = new JLabel("Nama Panggilan");
		lblNamaPanggilan.setBounds(27, 503, 109, 14);
		contentPane.add(lblNamaPanggilan);
		    
		JLabel lblTempatLahir = new JLabel("Tempat Lahir");
		lblTempatLahir.setBounds(27, 534, 96, 14);
		contentPane.add(lblTempatLahir);
		
		JLabel lblTanggalLahir = new JLabel("Tanggal Lahir");
		lblTanggalLahir.setBounds(27, 570, 109, 14);
		contentPane.add(lblTanggalLahir);
		
		JLabel lblAgama = new JLabel("Agama");
		lblAgama.setBounds(27, 595, 96, 14);
		contentPane.add(lblAgama);
		
		JLabel lblJenisKelamin = new JLabel("Jenis Kelamin");
		lblJenisKelamin.setBounds(27, 626, 96, 19);
		contentPane.add(lblJenisKelamin);
		
		rdbtnLakiLaki = new JRadioButton("Laki - Laki");
		rdbtnLakiLaki.setBounds(142, 624, 96, 23);
		rdbtnLakiLaki.setActionCommand("Laki - Laki");
		contentPane.add(rdbtnLakiLaki);
		
		rdbtnPerempuan = new JRadioButton("Perempuan");
		rdbtnPerempuan.setBounds(247, 624, 109, 23);
		rdbtnPerempuan.setActionCommand("Perempuan");
		contentPane.add(rdbtnPerempuan);
		
		groupjkelamin = new ButtonGroup();
		groupjkelamin.add(rdbtnLakiLaki);
		groupjkelamin.add(rdbtnPerempuan);		
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{				
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}
			}
		});
		textField.setBounds(142, 469, 212, 20);
		contentPane.add(textField);		
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_1.setBounds(142, 500, 212, 20);
		contentPane.add(textField_1);		
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_2.setBounds(142, 531, 212, 20);
		contentPane.add(textField_2);
			
		comboBox = new JComboBox();		
		comboBox.setBounds(144, 592, 212, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Islam", "Kristen - Katholik", "Kristen - Protestan", "Hindu", "Budha"}));
		contentPane.add(comboBox);
		
		dateChooser.setBounds(144, 562, 212, 23);
		dateChooser.setDateFormatString("d MMM yyyy");
		contentPane.add(dateChooser);
		
		JLabel lblNomorTelepon = new JLabel("Nomor Telepon");
		lblNomorTelepon.setBounds(642, 268, 102, 14);
		contentPane.add(lblNomorTelepon);
		
		JLabel lblNomorHandphone = new JLabel("Nomor Handphone");
		lblNomorHandphone.setBounds(642, 303, 123, 14);
		contentPane.add(lblNomorHandphone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(642, 342, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblStatusPernikahan = new JLabel("Status Pernikahan");
		lblStatusPernikahan.setBounds(642, 378, 123, 14);
		contentPane.add(lblStatusPernikahan);
		
		JLabel lblPekerjaan = new JLabel("Pekerjaan");
		lblPekerjaan.setBounds(642, 414, 102, 14);
		contentPane.add(lblPekerjaan);
		
		JLabel lblNamaOrangTua = new JLabel("Nama Orang Tua");
		lblNamaOrangTua.setBounds(639, 462, 102, 14);
		contentPane.add(lblNamaOrangTua);
		
		JLabel lblBapak = new JLabel("1. Bapak");
		lblBapak.setBounds(813, 462, 66, 14);
		contentPane.add(lblBapak);
		
		JLabel lblIbu = new JLabel("2. Ibu");
		lblIbu.setBounds(813, 494, 46, 17);
		contentPane.add(lblIbu);
		
		JLabel lblPenghasilanPerBulan = new JLabel("Penghasilan Orang Tua");
		lblPenghasilanPerBulan.setBounds(639, 540, 197, 14);
		contentPane.add(lblPenghasilanPerBulan);
		
		JLabel lblSiapaYangMembiayai = new JLabel("Siapa yang membiayai kuliah?");
		lblSiapaYangMembiayai.setBounds(639, 633, 191, 14);
		contentPane.add(lblSiapaYangMembiayai);
		
		rdbtnBelumMenikah = new JRadioButton("Belum Menikah");
		rdbtnBelumMenikah.setBounds(816, 374, 127, 23);
		rdbtnBelumMenikah.setActionCommand("Belum Menikah");
		contentPane.add(rdbtnBelumMenikah);
		
		rdbtnMenikah = new JRadioButton("Menikah");
		rdbtnMenikah.setBounds(954, 374, 109, 23);
		rdbtnMenikah.setActionCommand("Menikah");
		contentPane.add(rdbtnMenikah);
		
		groupstatusmenikah = new ButtonGroup();
		groupstatusmenikah.add(rdbtnBelumMenikah);
		groupstatusmenikah.add(rdbtnMenikah);
		
		rdbtnBelumBekerja = new JRadioButton("Belum Bekerja");
		rdbtnBelumBekerja.setBounds(816, 410, 109, 23);
		rdbtnBelumBekerja.setActionCommand("Belum Bekerja");
		contentPane.add(rdbtnBelumBekerja);
		
		rdbtnBekerja = new JRadioButton("Bekerja");
		rdbtnBekerja.setBounds(954, 410, 109, 23);
		rdbtnBekerja.setActionCommand("Bekerja");
		contentPane.add(rdbtnBekerja);
		
		groupstatuskerja = new ButtonGroup();
		groupstatuskerja.add(rdbtnBelumBekerja);
		groupstatuskerja.add(rdbtnBekerja);
		
		rdbtnRp = new JRadioButton("Rp. 1.000.000 - Rp. 3.000.000");
		rdbtnRp.setBounds(813, 536, 197, 23);
		rdbtnRp.setActionCommand("Rp. 1.000.000 - Rp. 3.000.000");
		contentPane.add(rdbtnRp);
		
		rdbtnRp_1 = new JRadioButton("Rp. 3.000.000 - Rp. 6.000.000");
		rdbtnRp_1.setBounds(813, 562, 197, 23);
		rdbtnRp_1.setActionCommand("Rp. 3.000.000 - Rp. 6.000.000");
		contentPane.add(rdbtnRp_1);
		
		rdbtnRp_2 = new JRadioButton("> Rp. 6.000.000");
		rdbtnRp_2.setBounds(813, 588, 180, 23);
		rdbtnRp_2.setActionCommand("> Rp. 6.000.000");
		contentPane.add(rdbtnRp_2);
		    	
		grouppenghasilan = new ButtonGroup();
		grouppenghasilan.add(rdbtnRp);
		grouppenghasilan.add(rdbtnRp_1);
		grouppenghasilan.add(rdbtnRp_2);
		
		rdbtnOrangTua = new JRadioButton("Orang Tua");
		rdbtnOrangTua.setBounds(813, 629, 109, 23);
		rdbtnOrangTua.setActionCommand("Orang Tua");
		contentPane.add(rdbtnOrangTua);
		    
		rdbtnPribadi = new JRadioButton("Pribadi");
		rdbtnPribadi.setBounds(813, 655, 109, 23);
		rdbtnPribadi.setActionCommand("Pribadi");
		contentPane.add(rdbtnPribadi);
		    
		rdbtnLainnya = new JRadioButton("Lainnya :");		
		rdbtnLainnya.setBounds(813, 681, 86, 23);
		rdbtnLainnya.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e) 
			{				 
				  if(rdbtnLainnya.isSelected())
				  {					 
					  textField_3.setEnabled(true);
				  }
				  else
				  {					
					  //textField_3.setText("");
					  textField_3.setEnabled(false);
				  }
			}
		});
		rdbtnLainnya.setActionCommand("Lainnya");
		contentPane.add(rdbtnLainnya);
		    
		grouppembiaya = new ButtonGroup();
		grouppembiaya.add(rdbtnOrangTua);
		grouppembiaya.add(rdbtnPribadi);
		grouppembiaya.add(rdbtnLainnya);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_3.setBounds(915, 682, 105, 20);
		textField_3.setEnabled(false);
		contentPane.add(textField_3);
				    
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) 
			{				
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					getToolkit().beep();
					e.consume(); 
					JOptionPane.showMessageDialog(null, "Inputan Hanya Angka");						
				}				
			}
		});
		textField_4.setBounds(816, 265, 197, 20);
		contentPane.add(textField_4);
			    
		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) 
			{
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					getToolkit().beep();
					e.consume(); 
					JOptionPane.showMessageDialog(null, "Inputan Hanya Angka");						
				}
			}
		});
		textField_5.setBounds(816, 300, 195, 20);
		contentPane.add(textField_5);
			    
		textField_6 = new JTextField();
		textField_6.setBounds(816, 339, 197, 20);
		contentPane.add(textField_6);
			    
		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_7.setBounds(898, 462, 162, 20);
		contentPane.add(textField_7);
				    
		textField_8 = new JTextField();
		textField_8.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_8.setBounds(898, 492, 162, 20);
		contentPane.add(textField_8);
				
		JLabel lblPekerjaan_1 = new JLabel("Pekerjaan");
		lblPekerjaan_1.setBounds(1080, 462, 96, 14);
		contentPane.add(lblPekerjaan_1);
		
		JLabel lblPekerjaan_2 = new JLabel("Pekerjaan");
		lblPekerjaan_2.setBounds(1080, 495, 96, 14);
		contentPane.add(lblPekerjaan_2);
		
		textField_9 = new JTextField();
		textField_9.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_9.setBounds(1163, 459, 152, 20);
		contentPane.add(textField_9);
			    
		textField_10 = new JTextField();
		textField_10.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				boolean doBeep = true;				
				char c = e.getKeyChar();
				switch (c) 
				{
					case KeyEvent.VK_BACK_SPACE: 
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_SPACE:
						doBeep = false;
						break;
					default:
						doBeep = !Character.isAlphabetic(c);
						// no need to "break;": last branch of switch
				}
				if (doBeep)
				{
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Inputan Hanya Huruf");				
				}		
			}
		});
		textField_10.setBounds(1163, 494, 152, 20);
		contentPane.add(textField_10);
			    
		JLabel lblAlamatTinggal = new JLabel("Alamat Tinggal");
		lblAlamatTinggal.setBounds(642, 181, 109, 14);
		contentPane.add(lblAlamatTinggal);
		
		textArea = new JTextArea();
		textArea.setBounds(813, 180, 331, 74);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(textArea);
		    
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(118, 672, 89, 23);
		btnNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				TempPendaftaran tp = new TempPendaftaran();
				BukuTamuFunct btf = new BukuTamuFunct();
								
				try
				{                   
					if(group.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Program Studi");
					else if(textField_11.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nomor Pendaftaran");
					else if(!btf.checkBukuTamu(textField_11.getText()))
						JOptionPane.showMessageDialog(null, "Nomor Pendaftaran Tidak Ada, Silahkan Isi Buku Tamu Dahulu");
					else if(textField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama Lengkap");
					else if(textField_1.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama Panggilan");
					else if(textField_2.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tempat Lahir");
					else if(dateChooser.getDate() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tanggal Lahir");
					else if(comboBox.getSelectedItem().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Agama");
					else if(groupjkelamin.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Jenis Kelamin");
					else if(textArea.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Alamat");
					/*else if(textField_4.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nomor Telepon");
					else if(textField_5.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nomor Handphone");*/
					else if(textField_6.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Email");
					else if(groupstatusmenikah.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Status Pernikahan");
					else if(groupstatuskerja.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Pekerjaan");
					else if(textField_7.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama Bapak");
					/*else if(textField_9.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Pekerjaan Bapak");*/
					else if(textField_8.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama Ibu");
					/*else if(textField_10.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Pekerjaan Ibu");*/
					else if(grouppenghasilan.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Penghasilan");
					else if(grouppembiaya.getSelection() == null)
						JOptionPane.showMessageDialog(null, "Silahkan Pilih Sumber Biaya");
					
					//cek format data
					else if(!cekEmail(textField_6.getText()))
						JOptionPane.showMessageDialog(null, "Format Email Salah");
					else
					{
						//JOptionPane.showMessageDialog(null, "LENGKAP ");
						
						//clear file first					
						tp.emptyFile("p1.dat");				
						
						//save temp no pendaftaran to pp.dat
						SeqNumber sn = new SeqNumber();
						sn.updateSeqNumber(textField_11.getText(), "pp.dat");
						
						String nomorPendaftaran = textField_11.getText();
						tp.appendFile("p1.dat", nomorPendaftaran);
						
						ButtonModel b1 = group.getSelection();        	
						String ps = b1.getActionCommand();
						tp.appendFile("p1.dat", ps);
						//JOptionPane.showMessageDialog(null, ps);	           	

						String namaLengkap = textField.getText();
						tp.appendFile("p1.dat", namaLengkap);
						//JOptionPane.showMessageDialog(null, namaLengkap);	
						
						String namaPanggilan = textField_1.getText();
						tp.appendFile("p1.dat", namaPanggilan);
						//JOptionPane.showMessageDialog(null, namaPanggilan);	
						
						String tmptLahir = textField_2.getText();
						tp.appendFile("p1.dat", tmptLahir);
						//JOptionPane.showMessageDialog(null, tmptLahir);	

						//SimpleDateFormat fmt = new SimpleDateFormat("d MMM yyyy"); //MM/dd/yy
						SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
						String tglLahir = fmt.format(dateChooser.getDate());						
						tp.appendFile("p1.dat", tglLahir);
						//JOptionPane.showMessageDialog(null, tglLahir);

						String agama = (String) comboBox.getSelectedItem();
						tp.appendFile("p1.dat", agama);
						//JOptionPane.showMessageDialog(null, agama);

						ButtonModel b2 = groupjkelamin.getSelection();
						String jk = b2.getActionCommand();
						tp.appendFile("p1.dat", jk);
						//JOptionPane.showMessageDialog(null, jk);

						String alamat = textArea.getText();
						tp.appendFile("p1.dat", alamat);
						//JOptionPane.showMessageDialog(null, alamat);

						String noTelp = textField_4.getText();
						tp.appendFile("p1.dat", noTelp);
						//JOptionPane.showMessageDialog(null, noTelp);

						String noHp = textField_5.getText();
						tp.appendFile("p1.dat", noHp);
						//JOptionPane.showMessageDialog(null, noHp);

						String mail = textField_6.getText();
						tp.appendFile("p1.dat", mail);
						//JOptionPane.showMessageDialog(null, mail);

						ButtonModel b3 = groupstatusmenikah.getSelection();
						String sp = b3.getActionCommand();
						tp.appendFile("p1.dat", sp);
						//JOptionPane.showMessageDialog(null, sp);

						ButtonModel b4 = groupstatuskerja.getSelection();
						String pk = b4.getActionCommand();
						tp.appendFile("p1.dat", pk);
						//JOptionPane.showMessageDialog(null, pk);

						String namaBapak = textField_7.getText();
						tp.appendFile("p1.dat", namaBapak);
						//JOptionPane.showMessageDialog(null, namaBapak);

						String pekerjaanBapak = textField_9.getText();
						tp.appendFile("p1.dat", pekerjaanBapak);
						//JOptionPane.showMessageDialog(null, pekerjaanBapak);

						String namaIbu = textField_8.getText();
						tp.appendFile("p1.dat", namaIbu);
						//JOptionPane.showMessageDialog(null, namaIbu);

						String pekerjaanIbu = textField_10.getText();
						tp.appendFile("p1.dat", pekerjaanIbu);
						//JOptionPane.showMessageDialog(null, pekerjaanIbu);

						ButtonModel b5 = grouppenghasilan.getSelection();
						String ph = b5.getActionCommand();
						tp.appendFile("p1.dat", ph);
						//JOptionPane.showMessageDialog(null, ph);

						ButtonModel b6 = grouppembiaya.getSelection();
						String bk = b6.getActionCommand();
						if (bk.equals("Lainnya")) 
						{
							//JOptionPane.showMessageDialog(null, bk + textField_3.getText());
							tp.appendFile("p1.dat", bk + " " + textField_3.getText());
						}
						else
						{
							//JOptionPane.showMessageDialog(null, bk);
							tp.appendFile("p1.dat", bk);
						}					

						Pendaftaran2 pd2 = new Pendaftaran2();
						pd2.setExtendedState(MAXIMIZED_BOTH);
						pd2.setVisible(true);
						dispose();						
					}
				} 
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		contentPane.add(btnNext);
		    
		JButton btnNewButton = new JButton("Ambil Foto");
		btnNewButton.setBounds(447, 180, 123, 23);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{						
				//show webcam camera				
				WebcamTakePict wtp = new WebcamTakePict();
				wtp.run();		
				
				wtp.addWindowListener(new WindowAdapter()
				{
					public void windowOpened(final WindowEvent evt)
					{					
						System.out.println("MONSTER IS OPEN");
					}
					
					public void windowClosing(WindowEvent evt)
					{						
						Frame temp = (Frame) evt.getSource();
						temp.dispose();					
						System.out.println("MONSTER IS CLOSE");						
						ShowPhoto();						
					}					
				});
			}
		});
		contentPane.add(btnNewButton);		
		
		JButton button = new JButton("Back");
		button.setBounds(27, 672, 89, 23);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempPendaftaran tp = new TempPendaftaran();
				tp.emptyFile("p1.dat");
				tp.emptyFile("p2.dat");
				
				DeletePhoto();				
				
				Menu frame = new Menu();
				frame.setVisible(true);
				dispose();
			}		
		});
		contentPane.add(button);
		
		JLabel lblPerBulan = new JLabel("Per Bulan");
		lblPerBulan.setBounds(639, 565, 127, 14);
		contentPane.add(lblPerBulan);			
		
		JButton btnNewButton_1 = new JButton("Refresh Picture");
		btnNewButton_1.setBounds(447, 214, 123, 23);
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				File file = new File("test.jpg");
				if(file.exists())
				{
					ShowPhoto();
				}		
			}
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNomorPerndaftaran = new JLabel("NOMOR PERNDAFTARAN");
		lblNomorPerndaftaran.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomorPerndaftaran.setBounds(642, 11, 208, 14);
		contentPane.add(lblNomorPerndaftaran);
		
		JLabel lblNoPendaftaran = new JLabel("No. Pendaftaran");
		lblNoPendaftaran.setBounds(642, 44, 96, 14);
		contentPane.add(lblNoPendaftaran);
		
		textField_11 = new JTextField();
		textField_11.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) 
			{				
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					getToolkit().beep();
					e.consume(); 
					JOptionPane.showMessageDialog(null, "Inputan Hanya Angka");						
				}				
			}
		});
		textField_11.setBounds(813, 41, 212, 20);
		contentPane.add(textField_11);
	}

	protected boolean cekEmail(String data) 
	{
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		boolean result = data.matches(emailreg);			
		return result;
	}

	protected void DeletePhoto() 
	{		
		try
		{
			File file = new File("test.jpg");
 
    		if(file.delete())
    		{
    			System.out.println(file.getName() + " is deleted!");
    		}
    		else
    		{
    			System.out.println("Delete operation is failed.");
    		}
		}
		catch(Exception ezz)
		{
			ezz.printStackTrace();
		}	
	}
	
	public void ShowPhoto()
	{
		ImageIcon tinyPicture = new ImageIcon("test.jpg");
		Image image = tinyPicture.getImage();
		image = image.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_FAST);
		tinyPicture.setImage(image);
		lblFoto.setIcon(tinyPicture);			
	}
}