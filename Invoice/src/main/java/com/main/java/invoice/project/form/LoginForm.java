package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.UserDAO;
import com.main.java.invoice.project.preference.StaticPreference;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginForm extends JFrame
{
	private static final long serialVersionUID = 3330971717219231685L;

	JDesktopPane desktopPane = new JDesktopPane();

	private final JTextField TF_Pengguna = new JTextField();
	private final JPasswordField PF_KataSandi = new JPasswordField();
	private final JButton btnMasuk = new JButton("Masuk");
	private final JButton btnBatal = new JButton("Batal");

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

	LoginForm()
	{
		setResizable(false);
		setTitle("Login");
		initializeForm();
		centerForm();
	}

	private void centerForm()
	{
		this.setLocationRelativeTo(getRootPane());
	}

	private void initializeForm()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(450, 250, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);

		JLabel lblPengguna = new JLabel("Pengguna");
		lblPengguna.setBounds(66, 67, 97, 15);
		desktopPane.add(lblPengguna);

		TF_Pengguna.setBounds(181, 65, 191, 19);
		TF_Pengguna.setColumns(10);
		desktopPane.add(TF_Pengguna);

		JLabel lblKataSandi = new JLabel("Kata Sandi");
		lblKataSandi.setBounds(66, 94, 97, 15);
		desktopPane.add(lblKataSandi);

		PF_KataSandi.setBounds(181, 92, 191, 19);
		PF_KataSandi.setColumns(10);
		desktopPane.add(PF_KataSandi);

		btnMasuk.setBounds(66, 201, 117, 25);
		desktopPane.add(btnMasuk);
		btnMasuk.addActionListener(e ->
		{
			String user = TF_Pengguna.getText();
			String pass = String.valueOf(PF_KataSandi.getPassword());

			System.out.println(user + " - " + pass);
			//System.out.println("URL: " + StaticPreference.URL);
			//System.out.println("USERNAME: " + StaticPreference.USERNAME);
			//System.out.println("PASSWORD: " + StaticPreference.PASSWORD);

			//TODO Login Check
			UserDAO userDAO = new UserDAO();

			if (userDAO.checkUser(user, pass))
			{
				MenuForm menuForm = new MenuForm();
				menuForm.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Login Gagal", "", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnBatal.setBounds(255, 201, 117, 25);
		desktopPane.add(btnBatal);
		btnBatal.addActionListener(e ->
		{
			TF_Pengguna.setText("");
			PF_KataSandi.setText("");
			TF_Pengguna.requestFocusInWindow();
		});
	}
}