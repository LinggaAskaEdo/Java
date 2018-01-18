package com.main.java;

import java.awt.Color;
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

public class MasterDana extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterDana frame = new MasterDana();
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
	public MasterDana() {
		setClosable(true);
		setTitle("Master Dana");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 630, 326);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);

		JLabel lblNamaKantor = new JLabel("Nama Bank");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblNoRek = new JLabel("No. Rek");
		lblNoRek.setBounds(45, 57, 70, 15);
		desktopPane.add(lblNoRek);
		
		JLabel lblAtasNama = new JLabel("Atas Nama");
		lblAtasNama.setBounds(45, 88, 103, 15);
		desktopPane.add(lblAtasNama);
		
		JLabel lblCashtunai = new JLabel("Cash/Tunai");
		lblCashtunai.setBounds(45, 119, 103, 15);
		desktopPane.add(lblCashtunai);
		
		textField = new JTextField();
		textField.setBounds(163, 26, 281, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 55, 281, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 86, 281, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(163, 117, 281, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Nama Bank", "No. Rek", "Atas Nama", "Cash/Tunai"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 146, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton button = new JButton("Simpan");
		button.setBounds(45, 248, 117, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 248, 117, 25);
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(462, 248, 117, 25);
		desktopPane.add(button_2);
	}

}
