package com.main.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	JDesktopPane desktopPane = new JDesktopPane();
	private JPanel contentPane;
	private final JLabel lblKataSandi = new JLabel("Kata Sandi");
	private final JTextField TF_Pengguna = new JTextField();
	private final JButton btnMasuk = new JButton("Masuk");
	private final JButton btnBatal = new JButton("Batal");
	private final JPasswordField passwordField = new JPasswordField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {		
		setTitle("Login");
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 250, 450, 300);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);
		TF_Pengguna.setBounds(181, 65, 191, 19);
		TF_Pengguna.setColumns(10);
		
		JLabel lblPengguna = new JLabel("Pengguna");
		lblPengguna.setBounds(66, 67, 97, 15);
		

		passwordField.setBounds(181, 92, 191, 19);
		desktopPane.add(passwordField);
		
		desktopPane.add(lblPengguna);
		lblKataSandi.setBounds(66, 94, 97, 15);
		
		desktopPane.add(lblKataSandi);
		
		desktopPane.add(TF_Pengguna);
		
		btnMasuk.setBounds(66, 201, 117, 25);
		desktopPane.add(btnMasuk);
		
		btnBatal.setBounds(255, 201, 117, 25);
		desktopPane.add(btnBatal);
		
	}

}
