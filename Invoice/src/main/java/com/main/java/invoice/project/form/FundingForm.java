package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;

public class FundingForm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Nama;
	private JTextField TF_Nilai;
	private JTextField TF_Unggah;
	
	@SuppressWarnings("unused")
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FundingForm frame = new FundingForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	FundingForm()
	{
		setTitle("FUNDING");
		initializeForm();
	}

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 530, 353);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama/kontak");
		lblNamaPerusahaan.setBounds(39, 26, 110, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblReff = new JLabel("Reff");
		lblReff.setBounds(39, 53, 110, 15);
		desktopPane.add(lblReff);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(39, 116, 70, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblNilai = new JLabel("Nilai");
		lblNilai.setBounds(39, 150, 70, 15);
		desktopPane.add(lblNilai);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(39, 177, 110, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Nama = new JTextField();
		TF_Nama.setBounds(173, 26, 294, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(173, 118, 184, 20);
		desktopPane.add(CL_Tanggal);
		
		TF_Nilai = new JTextField();
		TF_Nilai.setBounds(173, 150, 184, 19);
		desktopPane.add(TF_Nilai);
		TF_Nilai.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(173, 178, 294, 53);
		desktopPane.add(TA_Keterangan);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(350, 277, 117, 25);
		desktopPane.add(btnSimpan);
		
		JRadioButton rdbtnKontrak = new JRadioButton("Kontrak");
		rdbtnKontrak.setBounds(173, 51, 93, 23);
		desktopPane.add(rdbtnKontrak);
		
		JRadioButton rdbtnMasterDana = new JRadioButton("Master Dana");
		rdbtnMasterDana.setBounds(280, 51, 128, 23);
		desktopPane.add(rdbtnMasterDana);
		
		JComboBox CB_Reff = new JComboBox();
		CB_Reff.setBounds(173, 82, 235, 24);
		desktopPane.add(CB_Reff);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(39, 246, 128, 15);
		desktopPane.add(lblUnggahDokumen);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(173, 246, 184, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton BT_Unggah = new JButton("Browse");
		BT_Unggah.setBounds(361, 243, 93, 25);
		desktopPane.add(BT_Unggah);

	}
}
