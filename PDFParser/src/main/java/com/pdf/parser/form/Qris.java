package com.pdf.parser.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.pdf.parser.dao.ParserDAO;

public class Qris extends JFrame
{
	private static final long serialVersionUID = 3330971717219231685L;

	private JDesktopPane desktopPane = new JDesktopPane();

	private final JTextField txtSource = new JTextField();
	private final JTextField txtDestination = new JTextField();
	private final JButton btnGenerate = new JButton("Generate");
	private final JButton btnCancel = new JButton("Cancel");
	

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Qris frame = new Qris();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Qris() 
	{
		setResizable(false);
		setTitle("Login");
		initializeForm();
		centerForm();
	}
	
	private void initializeForm()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(450, 250, 651, 300);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);

		JLabel lblSource = new JLabel("Source");
		lblSource.setBounds(22, 68, 49, 15);
		desktopPane.add(lblSource);

		txtSource.setBounds(113, 65, 459, 19);
		txtSource.setColumns(10);
		desktopPane.add(txtSource);

		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(22, 95, 79, 15);
		desktopPane.add(lblDestination);

		txtDestination.setBounds(113, 92, 459, 19);
		txtDestination.setColumns(10);
		desktopPane.add(txtDestination);
		
		btnGenerate.setBounds(183, 186, 117, 25);
		desktopPane.add(btnGenerate);
		btnGenerate.addActionListener(e ->
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

		btnCancel.setBounds(381, 186, 117, 25);
		desktopPane.add(btnCancel);
		
		JButton btnSource = new JButton("...");
		btnSource.setBounds(584, 65, 30, 19);
		desktopPane.add(btnSource);
		
		JButton btnDestination = new JButton("...");
		btnDestination.setBounds(584, 92, 30, 19);
		desktopPane.add(btnDestination);
		
		
		btnCancel.addActionListener(e ->
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