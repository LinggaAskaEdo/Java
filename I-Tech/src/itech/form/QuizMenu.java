package itech.form;

import itech.funct.BukuTamuFunct;
import itech.funct.SeqNumber;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class QuizMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					QuizMenu frame = new QuizMenu();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public QuizMenu() 
	{	
		initializeForm();
		centerForm();			
	}
	
	private void centerForm()
	{			
		this.setLocationRelativeTo(getRootPane());
	}
	
	private void initializeForm()
	{	
		setResizable(false);
		setTitle("Form Menu Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Lanjutkan Quiz");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill registration number");
				}
				else
				{
					//cek id apakah sudah terdaftar di database
					BukuTamuFunct btf = new BukuTamuFunct();
					if(btf.checkBukuTamu(textField.getText()))
					{
						//JOptionPane.showMessageDialog(null, "Valid number pendataran");
						//save no pendaftaran to qq.dat file
						SeqNumber sn = new SeqNumber();
						sn.updateSeqNumber(textField.getText(), "qq.dat");
						
						//go to quiz menu
						QuizQuestion qq = new QuizQuestion();
						qq.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not valid registration number, please register first");
					}
				}
			}
		});
		btnNewButton.setBounds(10, 101, 286, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("NOMOR PERNDAFTARAN");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 11, 208, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("No. Pendaftaran");
		label_1.setBounds(10, 46, 96, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(116, 43, 180, 20);
		contentPane.add(textField);		
	}
}