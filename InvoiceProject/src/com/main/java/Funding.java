package com.main.java;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;

public class Funding extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	@SuppressWarnings("unused")
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funding frame = new Funding();
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
	public Funding() {
		setClosable(true);
		setTitle("FUNDING");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 530, 296);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama/kontak");
		lblNamaPerusahaan.setBounds(45, 28, 110, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblTanggal = new JLabel("Reff Kontrak");
		lblTanggal.setBounds(45, 55, 110, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblTanggal_1 = new JLabel("Tanggal");
		lblTanggal_1.setBounds(45, 82, 70, 15);
		desktopPane.add(lblTanggal_1);
		
		JLabel lblNilai = new JLabel("Nilai");
		lblNilai.setBounds(45, 116, 70, 15);
		desktopPane.add(lblNilai);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 143, 110, 15);
		desktopPane.add(lblKeterangan);
		
		textField = new JTextField();
		textField.setBounds(173, 26, 294, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(173, 53, 294, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(173, 82, 184, 20);
		desktopPane.add(calendarComboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 114, 184, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(173, 142, 294, 53);
		desktopPane.add(textArea);
		
		/*table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Nama/kontak", "Reff Kontrak", "Tanggal", "Nilai", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 207, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);*/
		
		JButton button = new JButton("Simpan");
		button.setBounds(221, 217, 117, 25);
		desktopPane.add(button);
		
		/*JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 217, 117, 25);
		desktopPane.add(button_1);*/
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(350, 217, 117, 25);
		desktopPane.add(button_2);

	}
}
