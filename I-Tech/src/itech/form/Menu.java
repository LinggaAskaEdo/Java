package itech.form;

import itech.form.admin.FormAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					Menu frame = new Menu();					
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Menu()
	{
		setResizable(false);
		setTitle("Form Menu");
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
		setBounds(100, 100, 306, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBukuTamu = new JButton("Buku Tamu");
		btnBukuTamu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				BukuTamu butbukutamu = new BukuTamu();				
				butbukutamu.setVisible(true);
				dispose();
			}
		});
		btnBukuTamu.setBounds(10, 11, 271, 23);
		contentPane.add(btnBukuTamu);
		
		JButton btnPendaftaranMahasiswaBaru = new JButton("Pendaftaran Mahasiswa Baru");
		btnPendaftaranMahasiswaBaru.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				Pendaftaran butdaftar = new Pendaftaran();
				butdaftar.setExtendedState(MAXIMIZED_BOTH);				
				butdaftar.setVisible(true);				
				dispose();
			}
		});
		btnPendaftaranMahasiswaBaru.setBounds(10, 45, 271, 23);
		contentPane.add(btnPendaftaranMahasiswaBaru);
		
		JButton btnUjianMasuk = new JButton("Ujian Masuk / Exam");
		btnUjianMasuk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				QuizQuestion qq = new QuizQuestion();
				qq.setVisible(true);
				dispose();
			}
		});
		btnUjianMasuk.setBounds(10, 82, 271, 23);
		//contentPane.add(btnUjianMasuk);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				Login logoutact = new Login();
				logoutact.setVisible(true);
				dispose();				
			}
		});
		btnLogout.setBounds(10, 150, 271, 23);
		contentPane.add(btnLogout);
		
		JButton btnFormAdmin = new JButton("Form Admin");
		btnFormAdmin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				FormAdmin fa = new FormAdmin();
				fa.setVisible(true);
				dispose();
			}
		});
		btnFormAdmin.setBounds(10, 116, 271, 23);
		contentPane.add(btnFormAdmin);
		
		JButton btnFormQuiz = new JButton("Form Quiz");
		btnFormQuiz.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				QuizMenu qm = new QuizMenu();
				qm.setVisible(true);
				dispose();
			}
		});
		btnFormQuiz.setBounds(10, 79, 271, 23);
		contentPane.add(btnFormQuiz);
	}
}