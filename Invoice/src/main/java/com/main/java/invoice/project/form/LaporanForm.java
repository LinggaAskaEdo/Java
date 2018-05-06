package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.FundingReportDAO;
import com.main.java.invoice.project.dao.MasterClientDAO;
import com.main.java.invoice.project.dao.MasterLegalitasDAO;
import com.main.java.invoice.project.pojo.FundingReport;
import com.main.java.invoice.project.pojo.MasterClient;
import com.main.java.invoice.project.pojo.MasterPerusahaan;
import com.main.java.invoice.project.preference.StaticPreference;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LaporanForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private ButtonGroup buttonGroup;
	private JComboBox comboBoxClient;
	private JComboBox comboBoxPt;
	private Connection connect = null;
	private JRadioButton rdbtnHarian = new JRadioButton("Harian");
	private JRadioButton rdbtnBulanan = new JRadioButton("Bulanan");
	private JDateChooser CL_Harian = new JDateChooser();
	private JDateChooser CL_Bulanan_1 = new JDateChooser();
	private JDateChooser CL_Bulanan_2 = new JDateChooser();
	private MasterClientDAO masterClientDAO = new MasterClientDAO();
	private MasterLegalitasDAO masterLegalitasDAO = new MasterLegalitasDAO();
	private FundingReportDAO fundingReportDAO = new FundingReportDAO();

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
		ShowComboBoxClient();
		ShowComboBoxPerusahaan();
	}

	public void initializeForm()
	{
		setClosable(true);
		//setBounds(100, 100, 577, 280);
		setBounds(100, 100, 577, 327);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Laporan");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);

		JComboBox CB_Laporan = new JComboBox();
		CB_Laporan.addItem("PO EVENT REPORT");
		CB_Laporan.addItem("PO MEDIA REPORT");
		CB_Laporan.addItem("PO PRODUKSI REPORT");
		CB_Laporan.addItem("FUNDING REPORT");
		CB_Laporan.addItem("COST OPERATIONAL REPORT");
		CB_Laporan.setBounds(130, 23, 370, 24);
		desktopPane.add(CB_Laporan);

		comboBoxClient = new JComboBox();
		comboBoxClient.addItem("");
		comboBoxClient.setBounds(130, 55, 370, 24);
		desktopPane.add(comboBoxClient);

		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(42, 60, 70, 15);
		desktopPane.add(lblClient);

		comboBoxPt = new JComboBox();
		comboBoxPt.addItem("");
		comboBoxPt.setBounds(130, 88, 370, 24);
		desktopPane.add(comboBoxPt);

		JLabel lblPt = new JLabel("PT");
		lblPt.setBounds(42, 93, 70, 15);
		desktopPane.add(lblPt);

		CL_Harian.setBounds(45, 185, 142, 20);
		CL_Harian.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Harian);

		CL_Bulanan_1.setBounds(228, 185, 142, 20);
		CL_Bulanan_1.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_1);

		CL_Bulanan_2.setBounds(382, 185, 142, 20);
		CL_Bulanan_2.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_2);

		rdbtnHarian.setActionCommand("1");
		rdbtnHarian.setBounds(74, 138, 77, 23);
		rdbtnHarian.setSelected(true);
		desktopPane.add(rdbtnHarian);

		rdbtnBulanan.setActionCommand("2");
		rdbtnBulanan.setBounds(325, 138, 91, 23);
		desktopPane.add(rdbtnBulanan);

		buttonGroup=new ButtonGroup();
		buttonGroup.add(rdbtnHarian);
		buttonGroup.add(rdbtnBulanan);

		boolean check = rdbtnHarian.isSelected();

		if (check)
		{
			CL_Bulanan_1.setEnabled(false);
			CL_Bulanan_2.setEnabled(false);
		}
		else
		{
			CL_Harian.setEnabled(false);
		}

		RadioButtonActionListener actionListener = new RadioButtonActionListener();
		rdbtnHarian.addActionListener(actionListener);
		rdbtnBulanan.addActionListener(actionListener);

		JButton btnCetak = new JButton("Cetak");
		btnCetak.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String fileName;
				String fileNameImage_1;
				String fileNameImage_2;

				if (Objects.equals(CB_Laporan.getSelectedItem(), "PO EVENT REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_EVENT.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_EVENT_IMAGES.jasper";
						fileNameImage_2 = "C:\\Program Files\\Invoice\\report\\TAGIHAN_REIMBURESMENT_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Harian.getDate());
							param.put("date2", CL_Harian.getDate());
							param.put("perusahaan", comboBoxPt.getSelectedItem());
							param.put("klien", comboBoxClient.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);
							JasperPrint jPrint3 = JasperFillManager.fillReport(fileNameImage_2, param, connect);

							JasperPrint linked = multipageLinking(jPrint, jPrint2);
							JasperPrint firstsecondlinked = multipageLinking(linked, jPrint3);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_EVENT_2.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_EVENT_IMAGES.jasper";
						fileNameImage_2 = "C:\\Program Files\\Invoice\\report\\TAGIHAN_REIMBURESMENT_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
							param.put("perusahaan", comboBoxPt.getSelectedItem());
							param.put("klien", comboBoxClient.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);
							JasperPrint jPrint3 = JasperFillManager.fillReport(fileNameImage_2, param, connect);

							JasperPrint linked = multipageLinking(jPrint, jPrint2);
							JasperPrint firstsecondlinked = multipageLinking(linked, jPrint3);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "PO MEDIA REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_MEDIA.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_MEDIA_IMAGES.jasper";
						fileNameImage_2 = "C:\\Program Files\\Invoice\\report\\TAGIHAN_MEDIA_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Harian.getDate());
							param.put("date2", CL_Harian.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);
							JasperPrint jPrint3 = JasperFillManager.fillReport(fileNameImage_2, param, connect);

							JasperPrint linked = multipageLinking(jPrint, jPrint2);
							JasperPrint firstsecondlinked = multipageLinking(linked, jPrint3);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_MEDIA_2.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_MEDIA_IMAGES.jasper";
						fileNameImage_2 = "C:\\Program Files\\Invoice\\report\\TAGIHAN_MEDIA_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);
							JasperPrint jPrint3 = JasperFillManager.fillReport(fileNameImage_2, param, connect);

							JasperPrint linked = multipageLinking(jPrint, jPrint2);
							JasperPrint firstsecondlinked = multipageLinking(linked, jPrint3);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "PO PRODUKSI REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Harian.getDate());
							param.put("date2", CL_Harian.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);

							JasperPrint firstsecondlinked = multipageLinking(jPrint, jPrint2);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI_2.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "FUNDING REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						String klien = String.valueOf(comboBoxClient.getSelectedItem());
						Date date = CL_Harian.getDate();

						List<FundingReport> allDataFunding = fundingReportDAO.GetAllFundingReport(klien, date);
						List<FundingReport> allDataImage = fundingReportDAO.GetAllFundingReport(klien, date);

						JRBeanCollectionDataSource beanColDataSource1 = new JRBeanCollectionDataSource(allDataFunding, false);
						JRBeanCollectionDataSource beanColDataSource2 = new JRBeanCollectionDataSource(allDataImage, false);

						try
						{
							InputStream inputStream = new FileInputStream("C:\\Program Files\\Invoice\\report\\FUNDING.jrxml");
							JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
							JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

							InputStream inputStream_1 = new FileInputStream("C:\\Program Files\\Invoice\\report\\FUNDING_IMAGES.jrxml");
							JasperDesign jasperDesign_1 = JRXmlLoader.load(inputStream_1);
							JasperReport jasperReport_1 = JasperCompileManager.compileReport(jasperDesign_1);

							HashMap param = new HashMap();

							//JasperFillManager.fillReportToFile("C:\\Program Files\\Invoice\\report\\FUNDING.jrxml", param, beanColDataSource1);

							JasperPrint jPrint = JasperFillManager.fillReport(jasperReport, null, beanColDataSource1);
							JasperPrint jPrint2 = JasperFillManager.fillReport(jasperReport_1, null, beanColDataSource2);

							JasperPrint firstsecondlinked = multipageLinking(jPrint, jPrint2);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (FileNotFoundException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\FUNDING_2.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\FUNDING_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);

							JasperPrint firstsecondlinked = multipageLinking(jPrint, jPrint2);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "COST OPERATIONAL REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\COST_OPERATIONAL.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\COST_OPERASIONAL_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Harian.getDate());
							param.put("date2", CL_Harian.getDate());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);

							JasperPrint firstsecondlinked = multipageLinking(jPrint, jPrint2);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\COST_OPERATIONAL_2.jasper";
						fileNameImage_1 = "C:\\Program Files\\Invoice\\report\\COST_OPERASIONAL_IMAGES.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());

							JasperPrint jPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperPrint jPrint2 = JasperFillManager.fillReport(fileNameImage_1, param, connect);

							JasperPrint firstsecondlinked = multipageLinking(jPrint, jPrint2);

							JasperViewer.viewReport(firstsecondlinked, false);

							//JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							//JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnCetak.setBounds(217, 241, 117, 25);
		desktopPane.add(btnCetak);
	}

	class RadioButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JRadioButton button = (JRadioButton) event.getSource();

			if (button == rdbtnHarian)
			{
				CL_Bulanan_1.setEnabled(false);
				CL_Bulanan_2.setEnabled(false);
				CL_Harian.setEnabled(true);
			}
			else
			{
				CL_Bulanan_1.setEnabled(true);
				CL_Bulanan_2.setEnabled(true);
				CL_Harian.setEnabled(false);
			}
		}
	}

	private void ShowComboBoxClient()
	{
		try
		{
			List<MasterClient> allMasterClient = masterClientDAO.GetAllMasterClientComboBox();

			if (allMasterClient.size() > 0)
			{
				for (MasterClient anAllMasterClient : allMasterClient)
				{
					comboBoxClient.addItem(anAllMasterClient.getName());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void ShowComboBoxPerusahaan()
	{
		try
		{
			List<MasterPerusahaan> allMasterPerusahaan = masterLegalitasDAO.GetAllMasterPerusahaanComboBox();

			if (allMasterPerusahaan.size() > 0)
			{
				for (MasterPerusahaan anAllMasterPerusahaan : allMasterPerusahaan)
				{
					comboBoxPt.addItem(anAllMasterPerusahaan.getName());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private JasperPrint multipageLinking(JasperPrint page1, JasperPrint page2) {

		List pages = page2.getPages();
		for (int i = 0; i < pages.size(); i++) {
			page1.addPage((JRPrintPage) pages.get(i));
		}
		return page1;
	}
}