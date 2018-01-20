package com.main.java;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Akun extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Akun frame = new Akun();
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
	public Akun() {
		setClosable(true);
		setTitle("Akun");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 630, 360);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(46, 43, 139, 15);
		desktopPane.add(lblNama);
		
		JLabel lblNamaPengguna = new JLabel("Nama Pengguna");
		lblNamaPengguna.setBounds(46, 70, 139, 15);
		desktopPane.add(lblNamaPengguna);
		
		JLabel lblKataSandi = new JLabel("Kata Sandi");
		lblKataSandi.setBounds(46, 97, 139, 15);
		desktopPane.add(lblKataSandi);
		
		JLabel lblUlangiKataSandi = new JLabel("Ulangi Kata Sandi");
		lblUlangiKataSandi.setBounds(46, 124, 126, 15);
		desktopPane.add(lblUlangiKataSandi);
		
		textField = new JTextField();
		textField.setBounds(182, 41, 250, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 68, 250, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 95, 250, 19);
		desktopPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 122, 250, 19);
		desktopPane.add(passwordField_1);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}},
			new String[] {
				"Nama", "Nama Pengguna", "Password", "Role"
			}
		));
		table.setBounds(46, 154, 532, 150);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 151, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(46, 266, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(463, 266, 117, 25);
		desktopPane.add(btnBatal);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(172, 266, 117, 25);
		desktopPane.add(btnHapus);
		
		JCheckBox chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setBounds(451, 39, 129, 23);
		desktopPane.add(chckbxAdmin);

	}
}
