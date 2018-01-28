package com.main.java.invoice.project.form;

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

public class MasterLegalitasForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_KodePerusahaan;
	private JTextField TF_NamaPerusahaan;
	private JTextField TF_Npwp;
	private JTextField TF_Pic;
	private JTextField TF_FeeAgency;
	private JLabel label;
	private JTextField TF_NoRek;
	private JTable table;
	private JTextField TF_Unggah;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterLegalitasForm frame = new MasterLegalitasForm();
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
	public MasterLegalitasForm() {
		setClosable(true);
		setTitle("Master Legalitas Perusahaan");
		setBounds(100, 100, 630, 488);
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
		lblAlamat.setBounds(45, 117, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 186, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblPicnoRek = new JLabel("PIC/Kontak");
		lblPicnoRek.setBounds(45, 217, 133, 15);
		desktopPane.add(lblPicnoRek);
		
		JLabel lblFeeAgency = new JLabel("Fee Agency");
		lblFeeAgency.setBounds(45, 279, 133, 15);
		desktopPane.add(lblFeeAgency);
		
		TF_KodePerusahaan = new JTextField();
		TF_KodePerusahaan.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_KodePerusahaan);
		TF_KodePerusahaan.setColumns(10);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(196, 57, 254, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 184, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		TF_Pic = new JTextField();
		TF_Pic.setBounds(196, 215, 254, 19);
		desktopPane.add(TF_Pic);
		TF_Pic.setColumns(10);
		
		TF_FeeAgency = new JTextField();
		TF_FeeAgency.setBounds(196, 277, 76, 19);
		desktopPane.add(TF_FeeAgency);
		TF_FeeAgency.setColumns(10);
		
		label = new JLabel("%");
		label.setBounds(271, 279, 70, 15);
		desktopPane.add(label);
		
		JTextArea TA_Alamat = new JTextArea();
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Alamat.setBounds(196, 119, 284, 53);
		desktopPane.add(TA_Alamat);
		
		JLabel lblNoRek = new JLabel("No. Rek");
		lblNoRek.setBounds(45, 248, 70, 15);
		desktopPane.add(lblNoRek);
		
		TF_NoRek = new JTextField();
		TF_NoRek.setBounds(196, 246, 254, 19);
		desktopPane.add(TF_NoRek);
		TF_NoRek.setColumns(10);
		
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
		scrollPane.setBounds(45, 306, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 408, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 408, 117, 25);
		desktopPane.add(btnHapus);
		
		JLabel lblUnggahLogo = new JLabel("Unggah Logo");
		lblUnggahLogo.setBounds(45, 90, 105, 15);
		desktopPane.add(lblUnggahLogo);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(196, 88, 155, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(351, 85, 87, 25);
		desktopPane.add(btnBrowse);

	}
}
