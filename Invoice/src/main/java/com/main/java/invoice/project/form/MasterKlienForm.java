package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class MasterKlienForm extends JInternalFrame {
	private JTextField TF_Nama;
	private JTextField TF_Npwp;
	private JTable table;
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Satker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterKlienForm frame = new MasterKlienForm();
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
	public MasterKlienForm() {
		setClosable(true);
		setTitle("Master Klien");
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Nama Kementerian");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 55, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNPWP = new JLabel("NPWP");
		lblNPWP.setBounds(45, 124, 133, 15);
		desktopPane.add(lblNPWP);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 185, 133, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Nama = new JTextField();
		TF_Nama.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(197, 122, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setLineWrap(true);
		TA_Keterangan.setBounds(196, 184, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		JTextArea TA_Alamat = new JTextArea();
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Alamat.setBounds(197, 57, 284, 53);
		desktopPane.add(TA_Alamat);
		
		TF_Satker = new JTextField();
		TF_Satker.setBounds(196, 153, 254, 19);
		desktopPane.add(TF_Satker);
		TF_Satker.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Satker PPK");
		lblNewLabel.setBounds(45, 155, 133, 15);
		desktopPane.add(lblNewLabel);
		
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
				"Nama Kementerian", "Alamat", "NPWP", "Satker PKK", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 249, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 351, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 351, 117, 25);
		desktopPane.add(btnHapus);

	}
}
