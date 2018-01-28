package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import de.wannawork.jcalendar.JCalendarComboBox;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class POProduksiForm extends JInternalFrame {

	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Produksi;
	private JTable table;
	private JTextField TF_PONomor;
	private JTextField TF_ReffKontrak;
	private JTextField TF_NilaiProduksi;
	private JTextField TF_Unggah;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POProduksiForm frame = new POProduksiForm();
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
	public POProduksiForm() {
		setClosable(true);
		setTitle("PO. Produksi");
		//setBounds(100, 100, 630, 573);
		setBounds(100, 100, 630, 487);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblProduksi = new JLabel("Produksi");
		lblProduksi.setBounds(45, 88, 133, 15);
		desktopPane.add(lblProduksi);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal");
		lblTanggalTayang.setBounds(45, 119, 133, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblValue = new JLabel("Biaya Produksi");
		lblValue.setBounds(45, 151, 133, 15);
		desktopPane.add(lblValue);
		
		TF_Produksi = new JTextField();
		TF_Produksi.setBounds(197, 86, 254, 19);
		desktopPane.add(TF_Produksi);
		TF_Produksi.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(418, 413, 117, 25);
		desktopPane.add(btnSimpan);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(197, 117, 157, 20);
		desktopPane.add(CL_Tanggal);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Media", "Durasi", "Hari", "Lokasi", "Uraian", "Jenis", "Jumlah", "Barang" ,"Harga Satuan", "Total Harga"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 178, 490, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(197, 26, 254, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 57, 110, 15);
		desktopPane.add(lblReffKontrak);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(197, 55, 254, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(547, 178, 44, 25);
		desktopPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(547, 209, 44, 25);
		desktopPane.add(btnMinus);
		
		JLabel lblNilaiProduksi = new JLabel("Nilai Produksi");
		lblNilaiProduksi.setBounds(45, 280, 118, 15);
		desktopPane.add(lblNilaiProduksi);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 312, 107, 15);
		desktopPane.add(lblKeterangan);
		
		TF_NilaiProduksi = new JTextField();
		TF_NilaiProduksi.setBounds(197, 280, 254, 19);
		desktopPane.add(TF_NilaiProduksi);
		TF_NilaiProduksi.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(197, 311, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(197, 379, 198, 19);
		desktopPane.add(TF_Unggah);
		
		JButton btnUnggah = new JButton("Browse");
		btnUnggah.setBounds(397, 376, 110, 25);
		desktopPane.add(btnUnggah);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 381, 133, 15);
		desktopPane.add(lblUnggahDokumen);
		
	}
}
