package com.main.java.invoice.project.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import static com.main.java.invoice.project.form.POEventForm.tabelModel2;

public class DetailReimbursementForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Detail;
	private JFormattedTextField TF_Harga;
	private NumberFormat numformat = NumberFormat.getInstance();
	private NumberFormatter numformatter;
	String data[] = new String[3];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					DetailReimbursementForm frame = new DetailReimbursementForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	DetailReimbursementForm()
	{
		setTitle("Detail Reimbursement");
		initializeForm();
	}

	public void initializeForm()
	{
		setCurrencyNow();

		setClosable(true);
		setBounds(100, 100, 445, 202);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Uraian");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setBounds(45, 57, 70, 15);
		desktopPane.add(lblDetail);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(45, 84, 70, 15);
		desktopPane.add(lblHarga);

		JComboBox CB_Uraian = new JComboBox();
		CB_Uraian.addItem("Perjadin");
		CB_Uraian.addItem("Hotel Penginapan");
		CB_Uraian.addItem("Tiket Pesawat");
		CB_Uraian.setBounds(149, 23, 233, 24);
		desktopPane.add(CB_Uraian);
		
		TF_Detail = new JTextField();
		TF_Detail.setBounds(149, 55, 233, 19);
		desktopPane.add(TF_Detail);
		TF_Detail.setColumns(10);
		
		TF_Harga = new JFormattedTextField(numformatter);
		TF_Harga.setBounds(149, 82, 233, 19);
		desktopPane.add(TF_Harga);
		TF_Harga.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				data[0] = String.valueOf(CB_Uraian.getSelectedItem());
				data[1] = TF_Detail.getText();
				data[2] = TF_Harga.getText();

				try
				{
					tabelModel2.insertRow(0, data);
					dispose();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnTambah.setBounds(265, 113, 117, 25);
		desktopPane.add(btnTambah);
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