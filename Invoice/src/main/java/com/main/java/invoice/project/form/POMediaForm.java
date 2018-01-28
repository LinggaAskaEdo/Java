package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class POMediaForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Media;
	private JTextField TF_Value;
	private JTable table;
	private JTextField TF_PONomor;
	private JTextField TF_Klien;
	private JTextField TF_volume;
	private JTextField TF_Harga;
	private JTextField TF_Ppn;
	private JTextField TF_unggah;
	private JTextField TF_ReffKontrak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POMediaForm frame = new POMediaForm();
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
	public POMediaForm() {
		setClosable(true);
		setTitle("PO. Media");
		setBounds(100, 100, 630, 551);
		//setBounds(100, 100, 580, 463);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 176, 15);
		desktopPane.add(lblPoNomor);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(423, 475, 117, 25);
		desktopPane.add(btnSimpan);
		
		JLabel lblPekerjaanKementerian = new JLabel("Klien");
		lblPekerjaanKementerian.setBounds(45, 90, 176, 15);
		desktopPane.add(lblPekerjaanKementerian);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(45, 124, 176, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal Tayang");
		lblTanggalTayang.setBounds(45, 155, 176, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblUkuran = new JLabel("Volume");
		lblUkuran.setBounds(45, 188, 176, 15);
		desktopPane.add(lblUkuran);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(45, 219, 70, 15);
		desktopPane.add(lblHarga);
		
		JLabel lblPpn = new JLabel("PPN");
		lblPpn.setBounds(45, 250, 70, 15);
		desktopPane.add(lblPpn);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 280, 176, 15);
		desktopPane.add(lblKeterangan);
		
		JLabel lblBuktiPotongPph = new JLabel("Unggah Dokumen");
		lblBuktiPotongPph.setBounds(45, 346, 176, 15);
		desktopPane.add(lblBuktiPotongPph);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(239, 26, 239, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		TF_Klien = new JTextField();
		TF_Klien.setBounds(239, 88, 239, 19);
		desktopPane.add(TF_Klien);
		TF_Klien.setColumns(10);
		
		JComboBox CB_NamaMedia = new JComboBox();
		CB_NamaMedia.setBounds(239, 119, 206, 24);
		desktopPane.add(CB_NamaMedia);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(239, 155, 170, 20);
		desktopPane.add(CL_Tanggal);
		
		TF_volume = new JTextField();
		TF_volume.setBounds(239, 186, 114, 19);
		desktopPane.add(TF_volume);
		TF_volume.setColumns(10);
		
		TF_Harga = new JTextField();
		TF_Harga.setBounds(239, 217, 114, 19);
		desktopPane.add(TF_Harga);
		TF_Harga.setColumns(10);
		
		TF_Ppn = new JTextField();
		TF_Ppn.setBounds(239, 248, 114, 19);
		desktopPane.add(TF_Ppn);
		TF_Ppn.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(239, 279, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_unggah = new JTextField();
		TF_unggah.setBounds(239, 344, 170, 19);
		desktopPane.add(TF_unggah);
		TF_unggah.setColumns(10);
		
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
				"Reff PO", "Invoice Media", "Tanggal", "Nilai Tagihan", "Reff Sumber Dana"
			}
		));
		table.setBounds(46, 214, 532, 90);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 373, 495, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 176, 15);
		desktopPane.add(lblReffKontrak);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(239, 57, 239, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(45, 386, 117, 25);
		desktopPane.add(btnUpload);
		
		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(552, 373, 49, 25);
		desktopPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(552, 406, 49, 25);
		desktopPane.add(btnMinus);
		
		JButton btn_Unggah = new JButton("Browse");
		btn_Unggah.setBounds(410, 341, 96, 25);
		desktopPane.add(btn_Unggah);

	}
}
