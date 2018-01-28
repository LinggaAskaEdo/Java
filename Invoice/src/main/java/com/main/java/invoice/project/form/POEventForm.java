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

public class POEventForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Event;
	private JTextField TF_Value;
	private JTable table;
	private JTextField TF_PONomor;
	private JTextField TF_ReffKontrak;
	private JTextField TF_Kegiatan;
	private JTextField TF_Jumlah;
	private JTextField TF_Unggah;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POEventForm frame = new POEventForm();
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
	public POEventForm() {
		setClosable(true);
		setTitle("PO. Event");
		setBounds(100, 100, 630, 693);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 164, 15);
		desktopPane.add(lblPoNomor);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(480, 628, 117, 25);
		desktopPane.add(btnSimpan);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 118, 15);
		desktopPane.add(lblReffKontrak);
		
		JLabel lblKegiatan = new JLabel("Kegiatan");
		lblKegiatan.setBounds(45, 90, 105, 15);
		desktopPane.add(lblKegiatan);
		
		JLabel lblTanggalPelaksanaan = new JLabel("Tanggal Event");
		lblTanggalPelaksanaan.setBounds(45, 119, 117, 15);
		desktopPane.add(lblTanggalPelaksanaan);
		
		JLabel lblBiayaDetailEvent = new JLabel("Biaya Detail Event");
		lblBiayaDetailEvent.setBounds(45, 146, 139, 15);
		desktopPane.add(lblBiayaDetailEvent);
		
		JLabel lblBiayaReimbursement = new JLabel("Biaya Reimbursement");
		lblBiayaReimbursement.setBounds(45, 275, 164, 15);
		desktopPane.add(lblBiayaReimbursement);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(45, 404, 70, 15);
		desktopPane.add(lblJumlah);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 431, 118, 15);
		desktopPane.add(lblKeterangan);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(227, 26, 252, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(227, 57, 252, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(227, 119, 158, 20);
		desktopPane.add(CL_Tanggal);
		
		TF_Kegiatan = new JTextField();
		TF_Kegiatan.setBounds(227, 88, 252, 19);
		desktopPane.add(TF_Kegiatan);
		TF_Kegiatan.setColumns(10);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Uraian", "Detail", "Jumlah 1", "Jumlah 2", "Harga Satuan", "Total"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 173, 495, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btnPlus_1 = new JButton("+");
		btnPlus_1.setBounds(549, 173, 49, 25);
		desktopPane.add(btnPlus_1);
		
		JButton btnMinus_1 = new JButton("-");
		btnMinus_1.setBounds(548, 207, 49, 25);
		desktopPane.add(btnMinus_1);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Uraian", "Detail", "Harga"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(45, 302, 495, 90);
		scrollPane1.setViewportView(table);
		desktopPane.add(scrollPane1);
		
		JButton btnPlus_2 = new JButton("+");
		btnPlus_2.setBounds(548, 302, 49, 25);
		desktopPane.add(btnPlus_2);
		
		JButton btnMinus_2 = new JButton("-");
		btnMinus_2.setBounds(547, 336, 49, 25);
		desktopPane.add(btnMinus_2);
		
		TF_Jumlah = new JTextField();
		TF_Jumlah.setBounds(227, 402, 186, 19);
		desktopPane.add(TF_Jumlah);
		TF_Jumlah.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(227, 430, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		JButton btnUnggah = new JButton("Upload");
		btnUnggah.setBounds(386, 597, 84, 25);
		desktopPane.add(btnUnggah);
		
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
				"Reff PO", "Catatan Reimburse", "Tanggal", "Reff Sumber Dana", "Keterangan"
			}
		));
		table.setBounds(46, 214, 532, 90);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(45, 495, 495, 90);
		scrollPane2.setViewportView(table);
		desktopPane.add(scrollPane2);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(227, 601, 158, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton btnPlus_3 = new JButton("+");
		btnPlus_3.setBounds(549, 495, 49, 25);
		desktopPane.add(btnPlus_3);
		
		JButton btnMinus_3 = new JButton("-");
		btnMinus_3.setBounds(549, 528, 49, 25);
		desktopPane.add(btnMinus_3);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 603, 139, 15);
		desktopPane.add(lblUnggahDokumen);
	}
}
