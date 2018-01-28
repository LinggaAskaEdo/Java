package com.main.java.invoice.project.form;

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

public class MasterDanaForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Nama;
	private JTextField TF_noRek;
	private JTextField TF_AtasNama;
	private JTextField TF_Tunai;
	private JTable table; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterDanaForm frame = new MasterDanaForm();
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
	public MasterDanaForm() {
		setClosable(true);
		setTitle("Master Dana");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 630, 339);
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
		
		TF_Nama = new JTextField();
		TF_Nama.setBounds(163, 26, 281, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);
		
		TF_noRek = new JTextField();
		TF_noRek.setBounds(163, 55, 281, 19);
		desktopPane.add(TF_noRek);
		TF_noRek.setColumns(10);
		
		TF_AtasNama = new JTextField();
		TF_AtasNama.setBounds(163, 86, 281, 19);
		desktopPane.add(TF_AtasNama);
		TF_AtasNama.setColumns(10);
		
		TF_Tunai = new JTextField();
		TF_Tunai.setBounds(163, 117, 281, 19);
		desktopPane.add(TF_Tunai);
		TF_Tunai.setColumns(10);
		
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
		scrollPane.setBounds(45, 157, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 259, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 259, 117, 25);
		desktopPane.add(btnHapus);
	}

}
