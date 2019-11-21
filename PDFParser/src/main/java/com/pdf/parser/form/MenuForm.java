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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class MenuForm extends JFrame implements InternalFrameListener
{
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane = new JDesktopPane();

	private JMenuItem uploadFormMenu = new JMenuItem("Upload Document");
	private JMenuItem generateReportMenu = new JMenuItem("Generate Report");

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

	private void initializeForm()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(900, 500, 900, 500);
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

		uploadFormMenu.addActionListener(this::uploadFormActionPerformed);
		mnPdfParser.add(uploadFormMenu);

		generateReportMenu.addActionListener(this::generateReportActionPerformed);
		mnPdfParser.add(generateReportMenu);
	}

	private void centerForm()
	{
		this.setLocationRelativeTo(getRootPane());
	}

	private void keluarActionPerformed(ActionEvent actionEvent)
	{
		dispose();
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
	}

	private void uploadFormActionPerformed(ActionEvent e)
	{
		UploadForm uploadForm = new UploadForm();
		desktopPane.add(uploadForm);
		Dimension screen = this.getSize();
		Dimension frame = uploadForm.getSize();

		uploadForm.setLocation((screen.width - frame.width) / 2, (frame.height) / 25);
		uploadForm.setVisible(true);
		uploadForm.addInternalFrameListener(this);
		uploadFormMenu.setEnabled(false);
	}

	private void generateReportActionPerformed(ActionEvent actionEvent)
	{
		GenerateForm generateForm = new GenerateForm();
		desktopPane.add(generateForm);

		generateForm.setLocation(25, 250);
		generateForm.setVisible(true);
		generateForm.addInternalFrameListener(this);
		generateReportMenu.setEnabled(false);
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e)
	{
		//Do nothing
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e)
	{
		//Do nothing
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e)
	{
		String title = e.getInternalFrame().getTitle();

		System.out.println("Closing Internal Frame: " + title);

		if (title.equalsIgnoreCase("Upload Document"))
			uploadFormMenu.setEnabled(true);
		else if (title.equalsIgnoreCase("Generate Report"))
			generateReportMenu.setEnabled(true);
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e)
	{
		//Do nothing
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e)
	{
		//Do nothing
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e)
	{
		//Do nothing
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e)
	{
		//Do nothing
	}
}