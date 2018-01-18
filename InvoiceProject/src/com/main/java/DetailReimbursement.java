package com.main.java;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailReimbursement extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailReimbursement frame = new DetailReimbursement();
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
	public DetailReimbursement() {
		setClosable(true);
		setTitle("Detail Reimbursement");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 445, 202);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Uraian");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setBounds(45, 57, 70, 15);
		desktopPane.add(lblDetail);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(45, 84, 70, 15);
		desktopPane.add(lblHarga);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(149, 23, 233, 24);
		desktopPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(149, 55, 233, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 82, 233, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("Tambah");
		button.setBounds(136, 113, 117, 25);
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Batal");
		button_1.setBounds(265, 113, 117, 25);
		desktopPane.add(button_1);

	}

}
