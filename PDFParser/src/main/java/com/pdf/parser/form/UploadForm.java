package com.pdf.parser.form;

import com.pdf.parser.util.ParserUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class UploadForm extends JInternalFrame 
{
	private static final long serialVersionUID = 1665124484007642606L;

	private JDesktopPane desktopPane = new JDesktopPane();
	
	private final JLabel lblPath = new JLabel("Path :");
	private final JTextField txtPath = new JTextField();
	private final JButton btnLoad = new JButton("...");
	private final JButton btnParse = new JButton("Parse");
	private final JButton btnPreview = new JButton("Preview");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			try
			{
				UploadForm frame = new UploadForm();
				frame.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

	UploadForm()
	{
		setTitle("Upload Document");
		initializeForm();
	}
	
	private void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 601, 150);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		lblPath.setBounds(6, 23, 45, 16);
		desktopPane.add(lblPath);

		txtPath.setEditable(false);
		txtPath.setBounds(50, 18, 485, 26);
		txtPath.setColumns(10);
		desktopPane.add(txtPath);
		
		btnLoad.setBounds(542, 24, 29, 16);
		btnLoad.addActionListener(e -> {
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
				
				btnParse.setEnabled(true);
				btnPreview.setEnabled(true);
			}
		});
		desktopPane.add(btnLoad);
		
		btnParse.setBounds(6, 70, 117, 29);
		btnParse.setEnabled(false);
		btnParse.addActionListener(e -> {
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
		});
		desktopPane.add(btnParse);
		
		btnPreview.setBounds(133, 70, 117, 29);
		btnPreview.setEnabled(false);
		btnPreview.addActionListener(e -> {
			try 
			{
				//Preview PDF File
	            File file = new File(txtPath.getText());
	            
	            if (file.exists()) 
	            	Desktop.getDesktop().open(file);
			} 
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Buka File Gagal !!!", "", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		desktopPane.add(btnPreview);
	}
}