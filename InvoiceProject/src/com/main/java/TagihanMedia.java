package com.main.java;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JButton;

public class TagihanMedia extends JInternalFrame {
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagihanMedia frame = new TagihanMedia();
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
	public TagihanMedia() {
		setClosable(true);
		setTitle("Tagihan Media");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 560, 289);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Media");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblInvoiceMedia = new JLabel("Invoice Media");
		lblInvoiceMedia.setBounds(45, 61, 153, 15);
		desktopPane.add(lblInvoiceMedia);
		
		JLabel lblTanggalTerima = new JLabel("Tanggal Terima");
		lblTanggalTerima.setBounds(45, 90, 153, 15);
		desktopPane.add(lblTanggalTerima);
		
		JLabel lblNilaiTagihan = new JLabel("Nilai Tagihan");
		lblNilaiTagihan.setBounds(45, 124, 153, 15);
		desktopPane.add(lblNilaiTagihan);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 158, 153, 15);
		desktopPane.add(lblReffSumberDana);
		
		textField = new JTextField();
		textField.setBounds(216, 59, 238, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(216, 90, 158, 20);
		desktopPane.add(calendarComboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 122, 185, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(216, 153, 185, 24);
		desktopPane.add(comboBox_1);
		
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
				"Reff PO. Media", "Invoice Media", "Tanggal", "Nilai Tagihan", "Reff Sumber Dana"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 185, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);*/
		
		JButton button = new JButton("Simpan");
		button.setBounds(272, 199, 117, 25);
		desktopPane.add(button);
		
		/*JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 199, 117, 25);
		desktopPane.add(button_1);*/
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(401, 199, 117, 25);
		desktopPane.add(button_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(216, 26, 238, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button_1 = new JButton("Upload");
		button_1.setBounds(135, 199, 84, 25);
		desktopPane.add(button_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(45, 202, 90, 19);
		desktopPane.add(textField_3);

	}
}
