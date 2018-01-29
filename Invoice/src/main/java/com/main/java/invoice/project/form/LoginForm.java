package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class LoginForm extends JFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JPanel contentPane;
	private final JLabel lblKataSandi = new JLabel("Kata Sandi");
	private final JTextField TF_Pengguna = new JTextField();
	private final JPasswordField PF_KataSandi = new JPasswordField();
	private final JButton btnMasuk = new JButton("Masuk");
	private final JButton btnBatal = new JButton("Batal");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
            try
            {
                LoginForm frame = new LoginForm();
                frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	LoginForm()
	{
		setTitle("Login");
		setResizable(false);	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		
		PF_KataSandi.setBounds(181, 92, 191, 19);
		desktopPane.add(PF_KataSandi);
		
		desktopPane.add(lblPengguna);
		lblKataSandi.setBounds(66, 94, 97, 15);
		
		desktopPane.add(lblKataSandi);
		
		desktopPane.add(TF_Pengguna);
		btnMasuk.addActionListener(e ->
		{
            String user = TF_Pengguna.getText();
            String pass = Arrays.toString(PF_KataSandi.getPassword());

			System.out.println(user + " - " + pass);
		});
		
		btnMasuk.setBounds(66, 201, 117, 25);
		desktopPane.add(btnMasuk);
		
		btnBatal.setBounds(255, 201, 117, 25);
		desktopPane.add(btnBatal);
	}
}