package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class POMediaForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Media;
	private JTextField TF_Value;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POMediaForm frame = new POMediaForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public POMediaForm() {
		setClosable(true);
		setTitle("PO. Media");
		setBounds(100, 100, 630, 551);
		//setBounds(100, 100, 580, 463);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 176, 15);
		desktopPane.add(lblPoNomor);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(333, 482, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(462, 482, 117, 25);
		desktopPane.add(btnBatal);
		
		/*JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(171, 386, 117, 25);
		desktopPane.add(btnHapus);*/
		
		JLabel lblPekerjaanKementerian = new JLabel("Pekerjaan Kementerian");
		lblPekerjaanKementerian.setBounds(45, 90, 176, 15);
		desktopPane.add(lblPekerjaanKementerian);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(45, 124, 176, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal Tayang");
		lblTanggalTayang.setBounds(45, 155, 176, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblUkuran = new JLabel("Ukuran");
		lblUkuran.setBounds(45, 188, 176, 15);
		desktopPane.add(lblUkuran);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(45, 219, 70, 15);
		desktopPane.add(lblHarga);
		
		JLabel lblPpn = new JLabel("PPN");
		lblPpn.setBounds(45, 250, 70, 15);
		desktopPane.add(lblPpn);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 280, 176, 15);
		desktopPane.add(lblKeterangan);
		
		JLabel lblBuktiPotongPph = new JLabel("Bukti Potong PPH");
		lblBuktiPotongPph.setBounds(45, 346, 176, 15);
		desktopPane.add(lblBuktiPotongPph);
		
		textField = new JTextField();
		textField.setBounds(239, 26, 239, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(239, 88, 239, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(239, 119, 206, 24);
		desktopPane.add(comboBox);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(239, 155, 170, 20);
		desktopPane.add(calendarComboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(239, 186, 114, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(239, 217, 114, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(239, 248, 114, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(239, 279, 284, 53);
		desktopPane.add(textArea);
		
		textField_5 = new JTextField();
		textField_5.setBounds(239, 344, 114, 19);
		desktopPane.add(textField_5);
		textField_5.setColumns(10);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				//"PO. Nomor", "Reff Kontrak", "Pekerjaan Kementerian", "Nama Media", "Tayang", "Ukuran", "Harga", "PPN", "Keterangan"
				"Reff PO", "Invoice Media", "Tanggal", "Nilai Tagihan", "Reff Sumber Dana"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 373, 495, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 176, 15);
		desktopPane.add(lblReffKontrak);
		
		textField_6 = new JTextField();
		textField_6.setBounds(239, 57, 239, 19);
		desktopPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(45, 386, 117, 25);
		desktopPane.add(btnUpload);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(47, 485, 90, 19);
		desktopPane.add(textField_7);
		
		JButton button = new JButton("Upload");
		button.setBounds(137, 482, 84, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("+");
		button_1.setBounds(552, 373, 49, 25);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("-");
		button_2.setBounds(552, 406, 49, 25);
		desktopPane.add(button_2);

	}
}
