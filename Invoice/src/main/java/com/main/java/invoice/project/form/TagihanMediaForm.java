package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JButton;

public class TagihanMediaForm extends JInternalFrame {
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Invoice;
	private JTextField TF_Tagihan;
	private JTable table;
	private JTextField TF_ReffPoMedia;
	private JTextField TF_Unggah;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagihanMediaForm frame = new TagihanMediaForm();
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
	public TagihanMediaForm() {
		setClosable(true);
		setTitle("Tagihan Media");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 560, 303);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Media");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblInvoiceMedia = new JLabel("Invoice Media");
		lblInvoiceMedia.setBounds(45, 61, 153, 15);
		desktopPane.add(lblInvoiceMedia);
		
		JLabel lblTanggalTerima = new JLabel("Tanggal Terima");
		lblTanggalTerima.setBounds(45, 90, 153, 15);
		desktopPane.add(lblTanggalTerima);
		
		JLabel lblNilaiTagihan = new JLabel("Nilai Tagihan");
		lblNilaiTagihan.setBounds(45, 124, 153, 15);
		desktopPane.add(lblNilaiTagihan);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 158, 153, 15);
		desktopPane.add(lblReffSumberDana);
		
		TF_Invoice = new JTextField();
		TF_Invoice.setBounds(216, 59, 238, 19);
		desktopPane.add(TF_Invoice);
		TF_Invoice.setColumns(10);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(216, 90, 158, 20);
		desktopPane.add(CL_Tanggal);
		
		TF_Tagihan = new JTextField();
		TF_Tagihan.setBounds(216, 122, 185, 19);
		desktopPane.add(TF_Tagihan);
		TF_Tagihan.setColumns(10);
		
		JComboBox CB_SumberDana = new JComboBox();
		CB_SumberDana.setBounds(216, 153, 185, 24);
		desktopPane.add(CB_SumberDana);
		
		JButton button = new JButton("Simpan");
		button.setBounds(391, 226, 117, 25);
		desktopPane.add(button);
		
		TF_ReffPoMedia = new JTextField();
		TF_ReffPoMedia.setBounds(216, 26, 238, 19);
		desktopPane.add(TF_ReffPoMedia);
		TF_ReffPoMedia.setColumns(10);
		
		JButton btnUnggah = new JButton("Browse");
		btnUnggah.setBounds(386, 189, 103, 25);
		desktopPane.add(btnUnggah);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(216, 192, 169, 19);
		desktopPane.add(TF_Unggah);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 194, 153, 15);
		desktopPane.add(lblUnggahDokumen);

	}
}
