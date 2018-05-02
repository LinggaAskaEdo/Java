package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterClientDAO;
import com.main.java.invoice.project.dao.MasterLegalitasDAO;
import com.main.java.invoice.project.pojo.MasterClient;
import com.main.java.invoice.project.pojo.MasterPerusahaan;
import com.main.java.invoice.project.preference.StaticPreference;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

				if (Objects.equals(CB_Laporan.getSelectedItem(), "PO EVENT REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_EVENT.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_EVENT_2.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
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
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "PO MEDIA REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_MEDIA.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());
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
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_MEDIA_2.jasper";

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
				else if (Objects.equals(CB_Laporan.getSelectedItem(), "PO PRODUKSI REPORT"))
				{
					if (buttonGroup.getSelection().getActionCommand().equals("1"))
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());
							param.put("perusahaan", comboBoxPt.getSelectedItem());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\PO_PRODUKSI_2.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
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
						fileName = "C:\\Program Files\\Invoice\\report\\FUNDING.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\FUNDING_2.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());
							param.put("klien", comboBoxClient.getSelectedItem());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
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

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date", CL_Harian.getDate());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
						}
						catch (ClassNotFoundException | SQLException | JRException e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						fileName = "C:\\Program Files\\Invoice\\report\\COST_OPERATIONAL_2.jasper";

						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

							HashMap param = new HashMap();

							param.put("date1", CL_Bulanan_1.getDate());
							param.put("date2", CL_Bulanan_2.getDate());

							JasperPrint JPrint = JasperFillManager.fillReport(fileName, param, connect);
							JasperViewer.viewReport(JPrint, false);
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
					comboBoxPt.addItem(anAllMasterPerusahaan.getCode());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}