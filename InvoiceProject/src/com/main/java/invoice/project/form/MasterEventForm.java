package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MasterEventForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterEventForm frame = new MasterEventForm();
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
	public MasterEventForm() {
		setClosable(true);
		setTitle("Master Event");
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Agent Event");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaKoordinator = new JLabel("Nama Koordinator");
		lblNamaKoordinator.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaKoordinator);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 70, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 178, 133, 15);
		desktopPane.add(lblKeterangan);
		
		textField = new JTextField();
		textField.setBounds(196, 26, 254, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 53, 254, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(196, 81, 284, 53);
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(textArea);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 177, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		textField_2 = new JTextField();
		textField_2.setBounds(196, 146, 254, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
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
				"Agent Event", "Nama Koordinator", "Alamat", "NPWP", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 242, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

		JButton button = new JButton("Simpan");
		button.setBounds(45, 344, 117, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 344, 117, 25);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(462, 344, 117, 25);
		desktopPane.add(button_2);
	}

}
