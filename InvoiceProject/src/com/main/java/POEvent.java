package com.main.java;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class POEvent extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Event;
	private JTextField TF_Value;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POEvent frame = new POEvent();
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
	public POEvent() {
		setClosable(true);
		setTitle("PO. Event");
		setBounds(100, 100, 630, 680);
		//setBounds(100, 100, 630, 588);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 164, 15);
		desktopPane.add(lblPoNomor);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(333, 611, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(462, 611, 117, 25);
		desktopPane.add(btnBatal);
		
		/*JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(171, 506, 117, 25);
		desktopPane.add(btnHapus);*/
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 118, 15);
		desktopPane.add(lblReffKontrak);
		
		JLabel lblKegiatan = new JLabel("Kegiatan");
		lblKegiatan.setBounds(45, 90, 105, 15);
		desktopPane.add(lblKegiatan);
		
		JLabel lblTanggalPelaksanaan = new JLabel("Tanggal Event");
		lblTanggalPelaksanaan.setBounds(45, 119, 117, 15);
		desktopPane.add(lblTanggalPelaksanaan);
		
		JLabel lblBiayaDetailEvent = new JLabel("Biaya Detail Event");
		lblBiayaDetailEvent.setBounds(45, 146, 139, 15);
		desktopPane.add(lblBiayaDetailEvent);
		
		JLabel lblBiayaReimbursement = new JLabel("Biaya Reimbursement");
		lblBiayaReimbursement.setBounds(45, 275, 164, 15);
		desktopPane.add(lblBiayaReimbursement);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(45, 404, 70, 15);
		desktopPane.add(lblJumlah);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 431, 118, 15);
		desktopPane.add(lblKeterangan);
		
		textField = new JTextField();
		textField.setBounds(227, 26, 252, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 57, 252, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(227, 119, 158, 20);
		desktopPane.add(calendarComboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(227, 88, 252, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Uraian", "Detail", "Jumlah 1", "Jumlah 2", "Harga Satuan", "Total"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 173, 495, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton button = new JButton("+");
		button.setBounds(549, 173, 49, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setBounds(548, 207, 49, 25);
		desktopPane.add(button_1);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Uraian", "Detail", "Harga"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(45, 302, 495, 90);
		scrollPane1.setViewportView(table);
		desktopPane.add(scrollPane1);
		
		JButton button_2 = new JButton("+");
		button_2.setBounds(548, 302, 49, 25);
		desktopPane.add(button_2);
		
		JButton button_3 = new JButton("-");
		button_3.setBounds(547, 336, 49, 25);
		desktopPane.add(button_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(227, 402, 186, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(227, 430, 284, 53);
		desktopPane.add(textArea);
		
		JButton button_4 = new JButton("Upload");
		button_4.setBounds(135, 611, 84, 25);
		desktopPane.add(button_4);
		
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
				//"PO. Nomor", "REFF Kontrak", "Kegiatan", "Tanggal", "Jumlah", "Keterangan"
				"Reff PO", "Catatan Reimburse", "Tanggal", "Reff Sumber Dana", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(45, 502, 495, 90);
		scrollPane2.setViewportView(table);
		desktopPane.add(scrollPane2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(45, 614, 90, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(549, 502, 49, 25);
		desktopPane.add(btnNewButton);
		
		JButton button_5 = new JButton("-");
		button_5.setBounds(549, 535, 49, 25);
		desktopPane.add(button_5);
	}
}
