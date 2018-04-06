package com.main.java.invoice.project.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.*;

import com.main.java.invoice.project.preference.StaticPreference;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LaporanForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private ButtonGroup buttonGroup;
	private Connection connect = null;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LaporanForm frame = new LaporanForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	LaporanForm()
	{
		setTitle("Laporan");
		initializeForm();
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 577, 280);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Laporan");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);

		JComboBox CB_Laporan = new JComboBox();
		CB_Laporan.addItem("PO EVENT REPORT");
		//CB_Laporan.addItem("PO MEDIA REPORT");
		//CB_Laporan.addItem("PO PRODUKSI REPORT");
		CB_Laporan.setBounds(130, 23, 370, 24);
		desktopPane.add(CB_Laporan);
		
		JDateChooser CL_Harian = new JDateChooser();
		CL_Harian.setBounds(45, 125, 142, 20);
		CL_Harian.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Harian);
		
		JDateChooser CL_Bulanan_1 = new JDateChooser();
		CL_Bulanan_1.setBounds(228, 125, 142, 20);
		CL_Bulanan_1.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_1);

		JDateChooser CL_Bulanan_2 = new JDateChooser();
		CL_Bulanan_2.setBounds(382, 125, 142, 20);
		CL_Bulanan_2.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_2);
		
		JRadioButton rdbtnHarian = new JRadioButton("Harian");
		rdbtnHarian.setActionCommand("2");
		rdbtnHarian.setBounds(74, 78, 77, 23);
		desktopPane.add(rdbtnHarian);
		
		JRadioButton rdbtnBulanan = new JRadioButton("Bulanan");
		rdbtnBulanan.setActionCommand("2");
		rdbtnBulanan.setBounds(325, 78, 91, 23);
		desktopPane.add(rdbtnBulanan);

		buttonGroup=new ButtonGroup();
		buttonGroup.add(rdbtnHarian);
		buttonGroup.add(rdbtnBulanan);

		JButton btnCetak = new JButton("Cetak");
		btnCetak.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String fileName;

				if (CB_Laporan.getSelectedItem().equals("PO EVENT REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\poEventReport.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\poEventReport_2.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date_1", CL_Bulanan_1.getDate());
							param.put("date_2", CL_Bulanan_2.getDate());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnCetak.setBounds(217, 181, 117, 25);
		desktopPane.add(btnCetak);
	}
}