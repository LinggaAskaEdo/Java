package com.main.java;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;

public class MasterLegalitas extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_KodePerusahaan;
	private JTextField TF_NamaPerusahaan;
	private JTextField TF_NPWP;
	private JTextField TF_PIC;
	private JTextField TF_FeeAgency;
	private JLabel label;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterLegalitas frame = new MasterLegalitas();
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
	public MasterLegalitas() {
		setClosable(true);
		setTitle("Master Legalitas Perusahaan");
		setBounds(100, 100, 630, 456);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Kode Perusahaan");
		lblNewLabel.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan.setBounds(45, 59, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 86, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 155, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblPicnoRek = new JLabel("PIC/Kontak");
		lblPicnoRek.setBounds(45, 186, 133, 15);
		desktopPane.add(lblPicnoRek);
		
		JLabel lblFeeAgency = new JLabel("Fee Agency");
		lblFeeAgency.setBounds(45, 248, 133, 15);
		desktopPane.add(lblFeeAgency);
		
		TF_KodePerusahaan = new JTextField();
		TF_KodePerusahaan.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_KodePerusahaan);
		TF_KodePerusahaan.setColumns(10);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(196, 57, 254, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		TF_NPWP = new JTextField();
		TF_NPWP.setBounds(196, 153, 254, 19);
		desktopPane.add(TF_NPWP);
		TF_NPWP.setColumns(10);
		
		TF_PIC = new JTextField();
		TF_PIC.setBounds(196, 184, 254, 19);
		desktopPane.add(TF_PIC);
		TF_PIC.setColumns(10);
		
		TF_FeeAgency = new JTextField();
		TF_FeeAgency.setBounds(196, 246, 76, 19);
		desktopPane.add(TF_FeeAgency);
		TF_FeeAgency.setColumns(10);
		
		label = new JLabel("%");
		label.setBounds(271, 248, 70, 15);
		desktopPane.add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(196, 88, 284, 53);
		desktopPane.add(textArea);
		
		JLabel lblNoRek = new JLabel("No. Rek");
		lblNoRek.setBounds(45, 217, 70, 15);
		desktopPane.add(lblNoRek);
		
		textField = new JTextField();
		textField.setBounds(196, 215, 254, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Kode Perusahaan", "Nama Perusahaan", "Alamat", "NPWP", "PIC/Kontak", "No. Rek", "Fee Agency"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 275, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

		JButton button = new JButton("Simpan");
		button.setBounds(45, 377, 117, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 377, 117, 25);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(462, 377, 117, 25);
		desktopPane.add(button_2);

	}
}
