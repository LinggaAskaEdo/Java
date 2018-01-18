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

public class MasterMedia extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMedia frame = new MasterMedia();
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
	public MasterMedia() {
		setClosable(true);
		setTitle("Master Media");
		//setBounds(100, 100, 630, 513);
		setBounds(100, 100, 630, 477);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblBillCommitment = new JLabel("Bill Comitment");
		lblBillCommitment.setBounds(45, 175, 133, 15);
		desktopPane.add(lblBillCommitment);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 205, 133, 15);
		desktopPane.add(lblKeterangan);
		
		JLabel lblBuktiPotongPph = new JLabel("Bukti Potong PPH");
		lblBuktiPotongPph.setBounds(45, 271, 133, 15);
		desktopPane.add(lblBuktiPotongPph);
		
		textField = new JTextField();
		textField.setBounds(196, 26, 233, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 53, 233, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(196, 81, 284, 53);
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(textArea);
		
		textField_2 = new JTextField();
		textField_2.setBounds(196, 146, 233, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(196, 173, 233, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 204, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		textField_4 = new JTextField();
		textField_4.setBounds(196, 269, 114, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
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
				"Nama Perusahaan", "Nama Media", "Alamat", "NPWP", "Bill Commitment", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 298, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton button = new JButton("Simpan");
		button.setBounds(45, 400, 117, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 400, 117, 25);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(462, 400, 117, 25);
		desktopPane.add(button_2);

	}
}
