package itech.form;

import itech.funct.BukuTamuFunct;
import itech.funct.SeqNumber;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class BukuTamu extends JFrame
{
	private static final long serialVersionUID = 1L;
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					BukuTamu frame = new BukuTamu();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public BukuTamu()
	{
		setResizable(false);
		addWindowListener(new WindowAdapter() 
		{
			public void windowActivated(WindowEvent e)
			{
				SeqNumber sn = new SeqNumber();
		        String num = sn.readSeqNumber("seq.dat");
		        textField.setText(num);
			}
		});
		setTitle("Form Buku Tamu");
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
		setBounds(100, 100, 489, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNo = new JLabel("No");
		lblNo.setBounds(21, 11, 22, 14);
		contentPane.add(lblNo);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(21, 43, 46, 14);
		contentPane.add(lblNama);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(21, 78, 86, 14);
		contentPane.add(lblAlamat);
		
		JLabel lblNotelp = new JLabel("Nomor Telepon");
		lblNotelp.setBounds(21, 114, 110, 14);
		contentPane.add(lblNotelp);
		
		JLabel lblAsalSekolah = new JLabel("Asal Sekolah / Asal Konversi");
		lblAsalSekolah.setBounds(21, 150, 187, 14);
		contentPane.add(lblAsalSekolah);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(219, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
		textField_1.setBounds(219, 40, 244, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(219, 75, 244, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter()
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
		textField_3.setBounds(219, 111, 244, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(218, 147, 245, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) 
			{
				if(textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Silahkan Isi Nama");
				}
				else if(textField_2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Silahkan Isi Alamat");
				}
				else if(textField_3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Silahkan Isi Nomor Telepon");
				}
				else if(textField_4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Silahkan Isi Asal Sekolah");
				}
				else
				{
					String no = textField.getText();
			        String nama = textField_1.getText();
			        String alamat = textField_2.getText();
			        String telp = textField_3.getText();
			        String sekolah = textField_4.getText();
			        
			        BukuTamuFunct btf = new BukuTamuFunct();
			        Boolean status = btf.addBukuTamu(no, nama, alamat, telp, sekolah);
			        
			        if (status == true)
			        {
			            JOptionPane.showMessageDialog(null, "Saved Successfully");
			            
			            //update seq number
			            SeqNumber sn = new SeqNumber();
			            String newNum = sn.countSeqNumber(sn.readSeqNumber("seq.dat"));
			            sn.updateSeqNumber(newNum, "seq.dat");
			            //JOptionPane.showMessageDialog(null, sn.readSeqNumber());     			          
			            
			            //generate report
			            JasperPrint jasperPrint = null;
			            System.out.println(no);
			            System.out.println(nama);
			            System.out.println(alamat);
			            System.out.println(telp);
			            System.out.println(sekolah);
			            TableModelData(no, nama, alamat, telp, sekolah);
			            
			            try 
			            {
			                JasperCompileManager.compileReportToFile("reports/BukuTamuReports.jrxml", "reports/BukuTamuReports.jasper");
			                jasperPrint = JasperFillManager.fillReport("reports/BukuTamuReports.jasper", new HashMap(), new JRTableModelDataSource(tableModel));
			                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
			                jasperViewer.setVisible(true);
			            } 
			            catch (JRException ex)
			            {
			                ex.printStackTrace();
			            }	
			            
			            //back to main menu
			            Menu menu = new Menu();						
						menu.setVisible(true);
			            dispose();
			        }
			        else
			        {
			            JOptionPane.showMessageDialog(null, "Saved Failed");
			        }	
				}				
			}

			private void TableModelData(String no, String nama, String alamat, String telp, String sekolah)
			{
				String[] columnNames = {"No.", "Nama", "Alamat", "Telp.", "Sekolah"};
				String[][] data =
				{
		            {no, nama, alamat, telp, sekolah},
				};
		        tableModel = new DefaultTableModel(data, columnNames);	
			}
		});
		btnCreate.setBounds(119, 199, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(21, 199, 89, 23);
		contentPane.add(btnBack);
	}
}