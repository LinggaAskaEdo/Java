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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class POProduksi extends JInternalFrame {

	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Produksi;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
private JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POProduksi frame = new POProduksi();
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
	public POProduksi() {
		setClosable(true);
		setTitle("PO. Produksi");
		//setBounds(100, 100, 630, 573);
		setBounds(100, 100, 630, 462);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblProduksi = new JLabel("Produksi");
		lblProduksi.setBounds(45, 88, 133, 15);
		desktopPane.add(lblProduksi);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal");
		lblTanggalTayang.setBounds(45, 119, 133, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblValue = new JLabel("Biaya Produksi");
		lblValue.setBounds(45, 151, 133, 15);
		desktopPane.add(lblValue);
		
		TF_Produksi = new JTextField();
		TF_Produksi.setBounds(197, 86, 254, 19);
		desktopPane.add(TF_Produksi);
		TF_Produksi.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(334, 386, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(462, 386, 117, 25);
		desktopPane.add(btnBatal);
		
		/*JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(171, 386, 117, 25);
		desktopPane.add(btnHapus);*/
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(197, 117, 157, 20);
		desktopPane.add(calendarComboBox);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Media", "Durasi", "Hari", "Lokasi", "Uraian", "Jenis", "Jumlah", "Barang" ,"Harga Satuan", "Total Harga"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 178, 490, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(197, 26, 254, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 57, 110, 15);
		desktopPane.add(lblReffKontrak);
		
		textField_1 = new JTextField();
		textField_1.setBounds(197, 55, 254, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("+");
		button.setBounds(547, 178, 44, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setBounds(547, 209, 44, 25);
		desktopPane.add(button_1);
		
		JLabel lblNilaiProduksi = new JLabel("Nilai Produksi");
		lblNilaiProduksi.setBounds(45, 280, 118, 15);
		desktopPane.add(lblNilaiProduksi);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 312, 107, 15);
		desktopPane.add(lblKeterangan);
		
		textField_2 = new JTextField();
		textField_2.setBounds(197, 280, 254, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(197, 311, 284, 53);
		desktopPane.add(textArea);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(45, 389, 90, 19);
		desktopPane.add(textField_3);
		
		JButton button_2 = new JButton("Upload");
		button_2.setBounds(135, 386, 84, 25);
		desktopPane.add(button_2);
		
		/*table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"PO. Number", "Reff Kontrak", "Produksi", "Tanggal", "Nilai Produksi", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(45, 376, 534, 90);
		scrollPane1.setViewportView(table);
		desktopPane.add(scrollPane1);*/
	}
}
