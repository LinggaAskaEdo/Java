package com.pdf.parser.form;

import com.pdf.parser.dao.ParserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginForm extends JFrame
{
	private static final long serialVersionUID = 3330971717219231685L;

	private JDesktopPane desktopPane = new JDesktopPane();

	private final JTextField tfPengguna = new JTextField();
	private final JPasswordField pfKatasandi = new JPasswordField();
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

		tfPengguna.setBounds(181, 65, 191, 19);
		tfPengguna.setColumns(10);
		desktopPane.add(tfPengguna);

		JLabel lblKataSandi = new JLabel("Kata Sandi");
		lblKataSandi.setBounds(66, 94, 97, 15);
		desktopPane.add(lblKataSandi);

		pfKatasandi.setBounds(181, 92, 191, 19);
		pfKatasandi.setColumns(10);
		desktopPane.add(pfKatasandi);

		btnMasuk.setBounds(66, 201, 117, 25);
		desktopPane.add(btnMasuk);
		btnMasuk.addActionListener(e ->
		{
			String user = tfPengguna.getText();
			String pass = String.valueOf(pfKatasandi.getPassword());

			System.out.println(user + " - " + pass);

			ParserDAO parserDAO = new ParserDAO();

			if (parserDAO.checkUser(user, pass))
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
			tfPengguna.setText("");
			pfKatasandi.setText("");
			tfPengguna.requestFocusInWindow();
		});
	}

	private void centerForm()
	{
		this.setLocationRelativeTo(getRootPane());
	}
}