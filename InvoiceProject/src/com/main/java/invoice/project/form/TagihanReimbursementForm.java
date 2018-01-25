package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;
import de.wannawork.jcalendar.JCalendarComboBox;

import javax.swing.JButton;

public class TagihanReimbursementForm extends JInternalFrame {
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagihanReimbursementForm frame = new TagihanReimbursementForm();
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
	public TagihanReimbursementForm() {
		setClosable(true);
		setTitle("Tagihan Biaya Reimbursement");
		//setBounds(100, 100, 630, 433);
		setBounds(100, 100, 583, 349);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Event/Produksi");
		lblNamaKantor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblCatatanReimburse = new JLabel("Catatan Reimburse");
		lblCatatanReimburse.setBounds(45, 60, 149, 15);
		desktopPane.add(lblCatatanReimburse);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(45, 124, 70, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 161, 149, 15);
		desktopPane.add(lblReffSumberDana);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 193, 124, 15);
		desktopPane.add(lblKeterangan);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(246, 59, 284, 53);
		desktopPane.add(textArea);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(246, 124, 175, 20);
		desktopPane.add(calendarComboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(246, 156, 202, 24);
		desktopPane.add(comboBox_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea_1.setBounds(246, 192, 284, 53);
		desktopPane.add(textArea_1);
		
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
				"Reff PO. Event/Produksi", "Catatan", "Tanggal", "Reff Sumber Dana", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 255, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);*/
		
		JButton button = new JButton("Simpan");
		button.setBounds(284, 262, 117, 25);
		desktopPane.add(button);
		
		/*JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 262, 117, 25);
		desktopPane.add(button_1);*/
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(413, 262, 117, 25);
		desktopPane.add(button_2);
		
		textField = new JTextField();
		textField.setBounds(246, 26, 202, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("Upload");
		button_1.setBounds(135, 262, 84, 25);
		desktopPane.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(45, 265, 90, 19);
		desktopPane.add(textField_1);

	}
}
