package com.pdf.parser.form;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class MenuForm extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
            try
            {
                MenuForm frame = new MenuForm();
				frame.setResizable(false);
				frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
	}

	MenuForm()
	{
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
		this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/*Setting Panel*/
		desktopPane = new JDesktopPane();

		setContentPane(desktopPane);

		/*Setting MenuBar*/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem mntmKeluar = new JMenuItem("Keluar");
		mntmKeluar.addActionListener(this::keluarActionPerformed);
		file.add(mntmKeluar);

		JMenu mnPdfParser = new JMenu("Menu");
		menuBar.add(mnPdfParser);

		JMenuItem uploadForm = new JMenuItem("Upload Document");
		uploadForm.addActionListener(this::uploadFormActionPerformed);
		mnPdfParser.add(uploadForm);

//		JMenuItem mntmPoProduksi = new JMenuItem("View Documents");
//		mntmPoProduksi.addActionListener(this::poProduksiActionPerformed);
//		mnPdfParser.add(mntmPoProduksi);
	}

	private void keluarActionPerformed(ActionEvent actionEvent)
	{
		dispose();
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
	}

	private void uploadFormActionPerformed(ActionEvent e)
	{
		UploadForm pom = new UploadForm();
		desktopPane.add(pom);
		Dimension screen = this.getSize();
		Dimension frame = pom.getSize();
		pom.setLocation((screen.width - frame.width)/2, (screen.height = frame.height)/25);
		pom.setVisible(true);
	}
}