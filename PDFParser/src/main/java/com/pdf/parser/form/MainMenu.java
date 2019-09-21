package com.pdf.parser.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.pdf.parser.util.ParserUtil;

public class MainMenu extends JFrame 
{
	private static final long serialVersionUID = 1665124484007642606L;

	JDesktopPane desktopPane = new JDesktopPane();
	
	private final JLabel lblPath = new JLabel("Path :");
	private final JTextField txtPath = new JTextField();
	private final JButton btnLoad = new JButton("...");
	private final JButton btnParse = new JButton("Start Parse");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	MainMenu()
	{
		setResizable(false);
		setTitle("PDF Parser");
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
		setBounds(100, 100, 587, 140);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);
		
		lblPath.setBounds(6, 23, 45, 16);
		desktopPane.add(lblPath);
		txtPath.setEditable(false);
		
		txtPath.setBounds(50, 18, 485, 26);
		txtPath.setColumns(10);
		desktopPane.add(txtPath);
		
		btnLoad.setBounds(542, 24, 29, 16);
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
		        fileChooser.setAcceptAllFileFilterUsed(true);
		        
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileChooser.getSelectedFile();
					txtPath.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		desktopPane.add(btnLoad);
		
		btnParse.setBounds(6, 70, 117, 29);
		btnParse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ParserUtil util = new ParserUtil();
				
				boolean status = util.readPdf(txtPath.getText());
				
				if (status)
				{
					JOptionPane.showMessageDialog(null, "Parsing Berhasil !!!", "", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Parsing Gagal !!!", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		desktopPane.add(btnParse);
	}
}