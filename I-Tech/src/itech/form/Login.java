package itech.form;

import itech.funct.LoginFunct;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame
{	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					Login frame = new Login();				
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login() 
	{
		setResizable(false);
		setTitle("Form Login");
		initializeForm();
		centerForm();	
	}
	
	private void centerForm()
	{			
		this.setLocationRelativeTo(getRootPane());
	}
	
	private void initializeForm()
	{		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 244, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 28, 77, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 62, 77, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(90, 25, 122, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 59, 122, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				if(textField.getText().isEmpty() || passwordField.getPassword().length == 0)
				{
					JOptionPane.showMessageDialog(null, "Username & Password tidak boleh kosong !!!", "Error", JOptionPane.ERROR_MESSAGE);		
				}
				else
				{		
					LoginFunct lf = new LoginFunct();
					String userText = new String(textField.getText());
					String passText = new String(passwordField.getPassword());
					boolean var = lf.checkUser(userText, passText);
					
					if (var == true)
					{
						Menu menu = new Menu();						
						menu.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Login salah !!!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		});
		btnLogin.setBounds(10, 110, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{				
				dispose();				
			}
		});
		btnExit.setBounds(123, 110, 89, 23);
		contentPane.add(btnExit);	
	}
}
