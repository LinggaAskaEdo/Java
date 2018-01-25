package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import de.wannawork.jcalendar.JCalendarComboBox;

import javax.swing.JButton;

public class MasterDataForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterDataForm frame = new MasterDataForm();
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
	public MasterDataForm() {
		setClosable(true);
		setTitle("Master Data");
		setBounds(100, 100, 950, 680);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblALegalitas = new JLabel("A. Legalitas");
		lblALegalitas.setBounds(30, 12, 94, 15);
		desktopPane.add(lblALegalitas);
		
		JLabel lblKodePerusahaan = new JLabel("Kode Perusahaan");
		lblKodePerusahaan.setBounds(49, 39, 133, 15);
		desktopPane.add(lblKodePerusahaan);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan.setBounds(49, 66, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(49, 93, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblPicnoRek = new JLabel("NPWP");
		lblPicnoRek.setBounds(49, 120, 133, 15);
		desktopPane.add(lblPicnoRek);
		
		JLabel lblPicnoRek_1 = new JLabel("PIC/No. Rek");
		lblPicnoRek_1.setBounds(49, 147, 133, 15);
		desktopPane.add(lblPicnoRek_1);
		
		JLabel lblFeeAgency = new JLabel("Fee Agency");
		lblFeeAgency.setBounds(49, 174, 133, 15);
		desktopPane.add(lblFeeAgency);
		
		JLabel lblBKlien = new JLabel("B. Klien");
		lblBKlien.setBounds(30, 201, 70, 15);
		desktopPane.add(lblBKlien);
		
		JLabel lblNamaPerusahaan_1 = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan_1.setBounds(49, 228, 133, 15);
		desktopPane.add(lblNamaPerusahaan_1);
		
		JLabel lblAlamat_1 = new JLabel("Alamat");
		lblAlamat_1.setBounds(49, 255, 133, 15);
		desktopPane.add(lblAlamat_1);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(49, 282, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(49, 309, 133, 15);
		desktopPane.add(lblKeterangan);
		
		JLabel lblCMedia = new JLabel("C. Media");
		lblCMedia.setBounds(30, 363, 70, 15);
		desktopPane.add(lblCMedia);
		
		JLabel lblCCetak = new JLabel("C1. Cetak");
		lblCCetak.setBounds(49, 390, 70, 15);
		desktopPane.add(lblCCetak);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(151, 390, 94, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblNamaPerusahaan_2 = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan_2.setBounds(151, 417, 133, 15);
		desktopPane.add(lblNamaPerusahaan_2);
		
		JLabel lblRekening = new JLabel("Rekening");
		lblRekening.setBounds(151, 444, 83, 15);
		desktopPane.add(lblRekening);
		
		JLabel lblCProduksi = new JLabel("C2. Produksi");
		lblCProduksi.setBounds(49, 471, 88, 15);
		desktopPane.add(lblCProduksi);
		
		JLabel lblNewLabel = new JLabel("Nama Media");
		lblNewLabel.setBounds(152, 471, 93, 15);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNamaPerusahaan_3 = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan_3.setBounds(151, 498, 133, 15);
		desktopPane.add(lblNamaPerusahaan_3);
		
		JLabel lblPeralatan = new JLabel("Peralatan");
		lblPeralatan.setBounds(151, 525, 94, 15);
		desktopPane.add(lblPeralatan);
		
		JLabel lblCEvent = new JLabel("C3. Event");
		lblCEvent.setBounds(49, 552, 70, 15);
		desktopPane.add(lblCEvent);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Media");
		lblNewLabel_1.setBounds(151, 552, 94, 15);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nama Kegiatan");
		lblNewLabel_2.setBounds(151, 579, 133, 15);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rekening");
		lblNewLabel_3.setBounds(151, 606, 70, 15);
		desktopPane.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(200, 34, 161, 24);
		desktopPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(200, 64, 284, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 91, 284, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 118, 284, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(200, 145, 161, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(200, 172, 114, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(200, 253, 284, 19);
		desktopPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(200, 280, 284, 19);
		desktopPane.add(textField_6);
		textField_6.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(200, 308, 284, 53);
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(textArea);
		
		textField_7 = new JTextField();
		textField_7.setBounds(200, 226, 284, 19);
		desktopPane.add(textField_7);
		textField_7.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(302, 385, 152, 24);
		desktopPane.add(comboBox_1);
		
		textField_8 = new JTextField();
		textField_8.setBounds(302, 415, 182, 19);
		desktopPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(302, 442, 182, 19);
		desktopPane.add(textField_9);
		textField_9.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(302, 466, 152, 24);
		desktopPane.add(comboBox_2);
		
		textField_10 = new JTextField();
		textField_10.setBounds(302, 496, 182, 19);
		desktopPane.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(302, 523, 182, 19);
		desktopPane.add(textField_11);
		textField_11.setColumns(10);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(302, 547, 152, 24);
		desktopPane.add(comboBox_3);
		
		textField_12 = new JTextField();
		textField_12.setBounds(302, 577, 182, 19);
		desktopPane.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(302, 604, 182, 19);
		desktopPane.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblDBuktiTayang = new JLabel("D. Bukti Tayang");
		lblDBuktiTayang.setBounds(510, 12, 133, 15);
		desktopPane.add(lblDBuktiTayang);
		
		JLabel lblScanDocument = new JLabel("Scan Document");
		lblScanDocument.setBounds(529, 39, 109, 15);
		desktopPane.add(lblScanDocument);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal tayang");
		lblTanggalTayang.setBounds(529, 66, 114, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblBuktiLaporan = new JLabel("Bukti Laporan");
		lblBuktiLaporan.setBounds(529, 93, 114, 15);
		desktopPane.add(lblBuktiLaporan);
		
		JLabel lblEInvoicing = new JLabel("E. Invoicing");
		lblEInvoicing.setBounds(510, 122, 99, 15);
		desktopPane.add(lblEInvoicing);
		
		JLabel lblScanDokumen = new JLabel("Scan Dokumen");
		lblScanDokumen.setBounds(529, 147, 114, 15);
		desktopPane.add(lblScanDokumen);
		
		JLabel lblTanggalTerima = new JLabel("Tanggal Terima");
		lblTanggalTerima.setBounds(529, 174, 114, 15);
		desktopPane.add(lblTanggalTerima);
		
		JLabel lblNilaiTagihan = new JLabel("Nilai Tagihan");
		lblNilaiTagihan.setBounds(529, 201, 114, 15);
		desktopPane.add(lblNilaiTagihan);
		
		JLabel lblPenagih = new JLabel("Penagih");
		lblPenagih.setBounds(529, 228, 70, 15);
		desktopPane.add(lblPenagih);
		
		JLabel lblTanggalBerbayar = new JLabel("Tanggal Berbayar");
		lblTanggalBerbayar.setBounds(529, 255, 139, 15);
		desktopPane.add(lblTanggalBerbayar);
		
		JLabel lblBuktiSetor = new JLabel("Bukti Setor");
		lblBuktiSetor.setBounds(529, 282, 114, 15);
		desktopPane.add(lblBuktiSetor);
		
		JLabel lblFKontrak = new JLabel("F. Kontrak");
		lblFKontrak.setBounds(510, 309, 97, 15);
		desktopPane.add(lblFKontrak);
		
		JLabel lblScanDokumen_1 = new JLabel("Scan Dokumen");
		lblScanDokumen_1.setBounds(529, 336, 114, 15);
		desktopPane.add(lblScanDokumen_1);
		
		JLabel lblTanggalPencairan = new JLabel("Tanggal Pencairan");
		lblTanggalPencairan.setBounds(529, 363, 139, 15);
		desktopPane.add(lblTanggalPencairan);
		
		JLabel lblFaktur = new JLabel("Faktur");
		lblFaktur.setBounds(529, 390, 70, 15);
		desktopPane.add(lblFaktur);
		
		JLabel lblBuktiTransfer = new JLabel("Bukti Transfer");
		lblBuktiTransfer.setBounds(529, 417, 139, 15);
		desktopPane.add(lblBuktiTransfer);
		
		textField_14 = new JTextField();
		textField_14.setBounds(686, 37, 231, 19);
		desktopPane.add(textField_14);
		textField_14.setColumns(10);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(686, 61, 161, 20);
		desktopPane.add(calendarComboBox);
		
		textField_15 = new JTextField();
		textField_15.setBounds(686, 145, 231, 19);
		desktopPane.add(textField_15);
		textField_15.setColumns(10);
		
		JCalendarComboBox calendarComboBox_1 = new JCalendarComboBox();
		calendarComboBox_1.setBounds(686, 174, 161, 20);
		desktopPane.add(calendarComboBox_1);
		
		textField_16 = new JTextField();
		textField_16.setBounds(686, 199, 231, 19);
		desktopPane.add(textField_16);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(686, 226, 231, 19);
		desktopPane.add(textField_17);
		textField_17.setColumns(10);
		
		JCalendarComboBox calendarComboBox_2 = new JCalendarComboBox();
		calendarComboBox_2.setBounds(686, 255, 161, 20);
		desktopPane.add(calendarComboBox_2);
		
		textField_18 = new JTextField();
		textField_18.setBounds(686, 334, 231, 19);
		desktopPane.add(textField_18);
		textField_18.setColumns(10);
		
		JCalendarComboBox calendarComboBox_3 = new JCalendarComboBox();
		calendarComboBox_3.setBounds(686, 363, 161, 20);
		desktopPane.add(calendarComboBox_3);
		
		textField_19 = new JTextField();
		textField_19.setBounds(686, 388, 231, 19);
		desktopPane.add(textField_19);
		textField_19.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(800, 601, 117, 25);
		desktopPane.add(btnSimpan);
		
		JLabel label = new JLabel("%");
		label.setBounds(318, 174, 70, 15);
		desktopPane.add(label);
		
		textField_20 = new JTextField();
		textField_20.setBounds(686, 91, 152, 19);
		desktopPane.add(textField_20);
		textField_20.setColumns(10);
		
		JButton btnCari = new JButton("Cari");
		btnCari.setBounds(847, 88, 70, 25);
		desktopPane.add(btnCari);
		
		textField_21 = new JTextField();
		textField_21.setBounds(686, 280, 152, 19);
		desktopPane.add(textField_21);
		textField_21.setColumns(10);
		
		JButton btnCari_1 = new JButton("Cari");
		btnCari_1.setBounds(847, 277, 70, 25);
		desktopPane.add(btnCari_1);
		
		textField_22 = new JTextField();
		textField_22.setBounds(686, 415, 152, 19);
		desktopPane.add(textField_22);
		textField_22.setColumns(10);
		
		JButton btnCari_2 = new JButton("Cari");
		btnCari_2.setBounds(847, 412, 70, 25);
		desktopPane.add(btnCari_2);
	}
}
