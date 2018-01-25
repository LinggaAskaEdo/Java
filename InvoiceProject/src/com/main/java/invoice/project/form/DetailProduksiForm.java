package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DetailProduksiForm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailProduksiForm frame = new DetailProduksiForm();
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
	public DetailProduksiForm() {
		setClosable(true);
		setTitle("Detail Produksi");
		setBounds(100, 100, 630, 630);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Media");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblDurasi = new JLabel("Durasi");
		lblDurasi.setBounds(45, 59, 70, 15);
		desktopPane.add(lblDurasi);
		
		JLabel lblHari = new JLabel("Hari");
		lblHari.setBounds(45, 90, 70, 15);
		desktopPane.add(lblHari);
		
		JLabel lblDurasi_1 = new JLabel("Lokasi");
		lblDurasi_1.setBounds(45, 120, 70, 15);
		desktopPane.add(lblDurasi_1);
		
		JLabel lblPreProduction = new JLabel("Pre Production");
		lblPreProduction.setBounds(45, 182, 117, 15);
		desktopPane.add(lblPreProduction);
		
		JLabel lblIi = new JLabel("Uraian");
		lblIi.setBounds(66, 209, 70, 15);
		desktopPane.add(lblIi);
		
		JLabel lblJenis = new JLabel("Jenis");
		lblJenis.setBounds(66, 240, 70, 15);
		desktopPane.add(lblJenis);
		
		JLabel lblProduction = new JLabel("Production");
		lblProduction.setBounds(45, 267, 117, 15);
		desktopPane.add(lblProduction);
		
		JLabel lblJenis_1 = new JLabel("Jenis");
		lblJenis_1.setBounds(66, 294, 70, 15);
		desktopPane.add(lblJenis_1);
		
		JLabel lblBanyak = new JLabel("Jumlah");
		lblBanyak.setBounds(66, 327, 70, 15);
		desktopPane.add(lblBanyak);
		
		JLabel lblHargaSatuan = new JLabel("Harga Satuan");
		lblHargaSatuan.setBounds(66, 389, 111, 15);
		desktopPane.add(lblHargaSatuan);
		
		JLabel lblTotalHarga = new JLabel("Total Harga");
		lblTotalHarga.setBounds(66, 420, 96, 15);
		desktopPane.add(lblTotalHarga);
		
		JLabel lblNewLabel = new JLabel("Post Production");
		lblNewLabel.setBounds(45, 447, 132, 15);
		desktopPane.add(lblNewLabel);
		
		JLabel lblBarang = new JLabel("Barang");
		lblBarang.setBounds(66, 358, 70, 15);
		desktopPane.add(lblBarang);
		
		JLabel lblBarang_1 = new JLabel("Barang");
		lblBarang_1.setBounds(66, 474, 70, 15);
		desktopPane.add(lblBarang_1);
		
		JLabel lblTotalHarga_1 = new JLabel("Total Harga");
		lblTotalHarga_1.setBounds(66, 505, 96, 15);
		desktopPane.add(lblTotalHarga_1);
		
		textField = new JTextField();
		textField.setBounds(194, 26, 231, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 57, 114, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(194, 88, 114, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(194, 119, 284, 53);
		desktopPane.add(textArea);
		
		textField_3 = new JTextField();
		textField_3.setBounds(194, 207, 162, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(194, 238, 162, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(194, 289, 162, 24);
		desktopPane.add(comboBox);
		
		textField_5 = new JTextField();
		textField_5.setBounds(194, 325, 49, 19);
		desktopPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(194, 356, 162, 19);
		desktopPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(194, 387, 114, 19);
		desktopPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(194, 418, 114, 19);
		desktopPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(194, 472, 162, 19);
		desktopPane.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(194, 503, 162, 19);
		desktopPane.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(335, 548, 117, 25);
		desktopPane.add(btnTambah);
		
		JButton btnNewButton = new JButton("Batal");
		btnNewButton.setBounds(464, 548, 117, 25);
		desktopPane.add(btnNewButton);

	}
}
