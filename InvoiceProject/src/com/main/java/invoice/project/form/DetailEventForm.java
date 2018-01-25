package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DetailEventForm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
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
					DetailEventForm frame = new DetailEventForm();
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
	public DetailEventForm() {
		setClosable(true);
		setTitle("Detail Event");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 630, 301);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Uraian");
		lblNamaPerusahaan.setBounds(45, 28, 110, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setBounds(45, 61, 110, 15);
		desktopPane.add(lblDetail);
		
		JLabel lblVol = new JLabel("Vol");
		lblVol.setBounds(45, 93, 110, 15);
		desktopPane.add(lblVol);
		
		JLabel lblHargaSatuan = new JLabel("Harga Satuan");
		lblHargaSatuan.setBounds(45, 155, 110, 15);
		desktopPane.add(lblHargaSatuan);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(45, 186, 110, 15);
		desktopPane.add(lblTotal);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(164, 23, 221, 24);
		desktopPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(164, 59, 221, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 91, 57, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton rdbtnOrg = new JRadioButton("org");
		rdbtnOrg.setBounds(229, 89, 49, 23);
		desktopPane.add(rdbtnOrg);
		
		JRadioButton rdbtnUnit = new JRadioButton("unit");
		rdbtnUnit.setBounds(282, 89, 57, 23);
		desktopPane.add(rdbtnUnit);
		
		JRadioButton rdbtnKl = new JRadioButton("kl");
		rdbtnKl.setBounds(343, 89, 49, 23);
		desktopPane.add(rdbtnKl);
		
		JRadioButton rdbtnKmr = new JRadioButton("kmr");
		rdbtnKmr.setBounds(396, 89, 57, 23);
		desktopPane.add(rdbtnKmr);
		
		JRadioButton rdbtnBh = new JRadioButton("bh");
		rdbtnBh.setBounds(457, 89, 49, 23);
		desktopPane.add(rdbtnBh);
		
		JRadioButton rdbtnPkt = new JRadioButton("pkt");
		rdbtnPkt.setBounds(510, 89, 57, 23);
		desktopPane.add(rdbtnPkt);
		
		textField_2 = new JTextField();
		textField_2.setBounds(164, 122, 57, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton rdbtnOj = new JRadioButton("OJ");
		rdbtnOj.setBounds(229, 120, 49, 23);
		desktopPane.add(rdbtnOj);
		
		JRadioButton rdbtnHr = new JRadioButton("hr");
		rdbtnHr.setBounds(282, 120, 49, 23);
		desktopPane.add(rdbtnHr);
		
		JRadioButton rdbtnKl_1 = new JRadioButton("kl");
		rdbtnKl_1.setBounds(335, 120, 43, 23);
		desktopPane.add(rdbtnKl_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(164, 153, 221, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(164, 184, 221, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(321, 219, 117, 25);
		desktopPane.add(btnTambah);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(450, 219, 117, 25);
		desktopPane.add(btnBatal);

	}
}
