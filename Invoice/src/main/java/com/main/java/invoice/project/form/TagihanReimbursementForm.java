package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;
import de.wannawork.jcalendar.JCalendarComboBox;

import javax.swing.JButton;

public class TagihanReimbursementForm extends JInternalFrame {
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private JTextField TF_ReffPO;
	private JTextField TF_Unggah;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagihanReimbursementForm frame = new TagihanReimbursementForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	TagihanReimbursementForm()
	{
		setTitle("Tagihan Biaya Reimbursement");
		initializeForm();
	}

	public void initializeForm() {
		setClosable(true);
		//setBounds(100, 100, 630, 433);
		setBounds(100, 100, 583, 369);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Event/Produksi");
		lblNamaKantor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblCatatanReimburse = new JLabel("Catatan Reimburse");
		lblCatatanReimburse.setBounds(45, 60, 149, 15);
		desktopPane.add(lblCatatanReimburse);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(45, 124, 70, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 161, 149, 15);
		desktopPane.add(lblReffSumberDana);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 193, 124, 15);
		desktopPane.add(lblKeterangan);
		
		JTextArea TA_Catatan = new JTextArea();
		TA_Catatan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Catatan.setBounds(246, 59, 284, 53);
		desktopPane.add(TA_Catatan);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(246, 124, 175, 20);
		desktopPane.add(CL_Tanggal);
		
		JComboBox btnSumberDana = new JComboBox();
		btnSumberDana.setBounds(246, 156, 202, 24);
		desktopPane.add(btnSumberDana);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(246, 192, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(413, 294, 117, 25);
		desktopPane.add(btnSimpan);
		
		TF_ReffPO = new JTextField();
		TF_ReffPO.setBounds(246, 26, 202, 19);
		desktopPane.add(TF_ReffPO);
		TF_ReffPO.setColumns(10);
		
		JButton btnUnggah = new JButton("Upload");
		btnUnggah.setBounds(420, 257, 84, 25);
		desktopPane.add(btnUnggah);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(247, 260, 174, 19);
		desktopPane.add(TF_Unggah);
		
		JLabel lblUnggahDokument = new JLabel("Unggah Dokument");
		lblUnggahDokument.setBounds(45, 262, 149, 15);
		desktopPane.add(lblUnggahDokument);

	}
}
