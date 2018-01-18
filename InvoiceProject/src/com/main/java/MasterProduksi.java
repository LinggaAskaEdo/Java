package com.main.java;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class MasterProduksi extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterProduksi frame = new MasterProduksi();
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
	public MasterProduksi() {
		setClosable(true);
		setTitle("Master Produksi");
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);

		JLabel lblNamaPerusahaan = new JLabel("Agent Produksi");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaDop = new JLabel("Nama DOP");
		lblNamaDop.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaDop);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 133, 15);
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
				"Agent Produksi", "Nama DOP", "Alamat", "NPWP", "Keterangan"
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
