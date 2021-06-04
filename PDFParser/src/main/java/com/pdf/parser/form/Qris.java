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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

public class Qris extends JFrame
{
	private static final long serialVersionUID = 3330971717219231685L;

	private JDesktopPane desktopPane = new JDesktopPane();

	private final JTextField txtSource = new JTextField();
	private final JTextField txtDestination = new JTextField();
	private final JButton btnGenerate = new JButton("Generate");
	private final JButton btnCancel = new JButton("Cancel");
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	

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
		setTitle("QRIS Generator");
		initializeForm();
		centerForm();
	}
	
	private void initializeForm()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(450, 250, 651, 345);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);

		JLabel lblSource = new JLabel("Source");
		lblSource.setBounds(22, 134, 49, 15);
		desktopPane.add(lblSource);

		txtSource.setBounds(113, 131, 459, 19);
		txtSource.setColumns(10);
		desktopPane.add(txtSource);

		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(22, 161, 79, 15);
		desktopPane.add(lblDestination);

		txtDestination.setBounds(113, 158, 459, 19);
		txtDestination.setColumns(10);
		desktopPane.add(txtDestination);
		
		btnGenerate.setBounds(381, 253, 117, 25);
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

		btnCancel.setBounds(497, 253, 117, 25);
		desktopPane.add(btnCancel);
		
		JButton btnSource = new JButton("...");
		btnSource.setBounds(584, 133, 30, 19);
		desktopPane.add(btnSource);
		
		JButton btnDestination = new JButton("...");
		btnDestination.setBounds(584, 160, 30, 19);
		desktopPane.add(btnDestination);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(22, 107, 49, 15);
		desktopPane.add(lblType);
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"WeChat", "CIMB"}));
		comboBoxType.setBounds(113, 102, 138, 27);
		desktopPane.add(comboBoxType);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(22, 188, 49, 15);
		desktopPane.add(lblSize);
		
		JComboBox comboBoxSize = new JComboBox();
		comboBoxSize.setModel(new DefaultComboBoxModel(new String[] {"A4", "A5", "A6"}));
		comboBoxSize.setBounds(113, 183, 138, 27);
		desktopPane.add(comboBoxSize);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 84, 592, 45);
		desktopPane.add(separator);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(22, 29, 79, 15);
		desktopPane.add(lblUsername);
		
		JLabel lblType_1_1 = new JLabel("Password");
		lblType_1_1.setBounds(22, 56, 69, 15);
		desktopPane.add(lblType_1_1);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(113, 23, 181, 26);
		desktopPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(113, 46, 181, 26);
		desktopPane.add(passwordField);
		
		
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