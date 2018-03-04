package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DetailReimbursementForm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Detail;
	private JTextField TF_Harga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailReimbursementForm frame = new DetailReimbursementForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	DetailReimbursementForm()
	{
		setTitle("Detail Reimbursement");
		initializeForm();
	}

	public void initializeForm() {

		setClosable(true);
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
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_Uraian = new JComboBox();
		CB_Uraian.setBounds(149, 23, 233, 24);
		desktopPane.add(CB_Uraian);
		
		TF_Detail = new JTextField();
		TF_Detail.setBounds(149, 55, 233, 19);
		desktopPane.add(TF_Detail);
		TF_Detail.setColumns(10);
		
		TF_Harga = new JTextField();
		TF_Harga.setBounds(149, 82, 233, 19);
		desktopPane.add(TF_Harga);
		TF_Harga.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(265, 113, 117, 25);
		desktopPane.add(btnTambah);

	}

}
