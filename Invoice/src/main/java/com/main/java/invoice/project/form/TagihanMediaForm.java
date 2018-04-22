package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.main.java.invoice.project.form.POMediaForm.tabelModel;

public class TagihanMediaForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Invoice;
	private JFormattedTextField TF_Tagihan;
	private JTextField TF_Unggah;
	private JComboBox CB_SumberDana;
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	private NumberFormat numformat = NumberFormat.getInstance();
	private NumberFormatter numformatter;
	String data[] = new String[5];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TagihanMediaForm frame = new TagihanMediaForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	TagihanMediaForm()
	{
		setTitle("Tagihan Media");
		initializeForm();
		ShowComboBoxTagihan();
	}

	public void initializeForm()
	{
		setCurrencyNow();

		setClosable(true);
		setBounds(100, 100, 560, 267);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);

		JLabel lblInvoiceMedia = new JLabel("Invoice Media");
		lblInvoiceMedia.setBounds(45, 25, 153, 15);
		desktopPane.add(lblInvoiceMedia);
		
		JLabel lblTanggalTerima = new JLabel("Tanggal Terima");
		lblTanggalTerima.setBounds(45, 54, 153, 15);
		desktopPane.add(lblTanggalTerima);
		
		JLabel lblNilaiTagihan = new JLabel("Nilai Tagihan");
		lblNilaiTagihan.setBounds(45, 88, 153, 15);
		desktopPane.add(lblNilaiTagihan);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 122, 153, 15);
		desktopPane.add(lblReffSumberDana);
		
		TF_Invoice = new JTextField();
		TF_Invoice.setBounds(216, 23, 238, 19);
		desktopPane.add(TF_Invoice);
		TF_Invoice.setColumns(10);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(216, 54, 158, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_Tagihan = new JFormattedTextField(numformatter);
		TF_Tagihan.setBounds(216, 86, 185, 19);
		desktopPane.add(TF_Tagihan);
		TF_Tagihan.setColumns(10);
		
		CB_SumberDana = new JComboBox();
		CB_SumberDana.setBounds(216, 117, 185, 24);
		desktopPane.add(CB_SumberDana);

		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(216, 156, 169, 19);
		desktopPane.add(TF_Unggah);

		JButton btnUnggah = new JButton("Browse");
		btnUnggah.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = jfc.getSelectedFile();
					TF_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnUnggah.setBounds(386, 153, 103, 25);
		desktopPane.add(btnUnggah);

		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 158, 153, 15);
		desktopPane.add(lblUnggahDokumen);

		JButton button = new JButton("Tambah");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				data[0] = TF_Invoice.getText();

				DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateNow = simpleDateFormat.format(CL_Tanggal.getDate());
				data[1] = dateNow;
				data[2] = TF_Tagihan.getText();
				data[3] = String.valueOf(CB_SumberDana.getSelectedItem());
				data[4] = TF_Unggah.getText();

				tabelModel.insertRow(0, data);
				dispose();
			}
		});
		button.setBounds(391, 190, 117, 25);
		desktopPane.add(button);
	}

	private void ShowComboBoxTagihan()
	{
		try
		{
			List<MasterDana> allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

			if (allMasterDana.size() > 0)
			{
				for (MasterDana anAllMasterDana : allMasterDana) {
					CB_SumberDana.addItem(anAllMasterDana.getNameBankAccount() + "-" + anAllMasterDana.getNoBankAccount());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setCurrencyNow()
	{
		//  set banyaknya angka akhir bilangan
		numformat.setMaximumFractionDigits(0);

		//  Deklarasikan NumberFormatter
		numformatter = new NumberFormatter(numformat);
		numformatter.setAllowsInvalid(false);
	}
}