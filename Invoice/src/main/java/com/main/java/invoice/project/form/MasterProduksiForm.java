package com.main.java.invoice.project.form;

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

public class MasterProduksiForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Agent;
	private JTextField TF_DOP;
	private JTextField TF_Npwp;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterProduksiForm frame = new MasterProduksiForm();
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
	public MasterProduksiForm() {
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
		
		TF_Agent = new JTextField();
		TF_Agent.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_Agent);
		TF_Agent.setColumns(10);
		
		TF_DOP = new JTextField();
		TF_DOP.setBounds(196, 53, 254, 19);
		desktopPane.add(TF_DOP);
		TF_DOP.setColumns(10);
		
		JTextArea TA_Alamat = new JTextArea();
		TA_Alamat.setBounds(196, 81, 284, 53);
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Alamat);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 177, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 146, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
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
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 344, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 344, 117, 25);
		desktopPane.add(btnHapus);
	}

}
