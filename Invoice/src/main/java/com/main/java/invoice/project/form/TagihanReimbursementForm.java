package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import static com.main.java.invoice.project.form.POEventForm.tabelModel3;

public class TagihanReimbursementForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_ReffPO;
	private JTextField TF_Unggah;
	private JTextArea TA_Catatan;
	private JTextArea TA_Keterangan;
	private JComboBox CB_SumberDana;
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	private POEventForm eventForm = new POEventForm();
	String data[] = new String[6];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TagihanReimbursementForm frame = new TagihanReimbursementForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	TagihanReimbursementForm()
	{
		setTitle("Tagihan Biaya Reimbursement");
		initializeForm();
		ShowComboBoxTagihan();
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 583, 369);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Event/Produksi");
		lblNamaKantor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblCatatanReimburse = new JLabel("Catatan Reimburse");
		lblCatatanReimburse.setBounds(45, 60, 149, 15);
		desktopPane.add(lblCatatanReimburse);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(45, 124, 70, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 161, 149, 15);
		desktopPane.add(lblReffSumberDana);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 193, 124, 15);
		desktopPane.add(lblKeterangan);
		
		TA_Catatan = new JTextArea();
		TA_Catatan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Catatan.setBounds(246, 59, 284, 53);
		desktopPane.add(TA_Catatan);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(246, 124, 175, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		CB_SumberDana = new JComboBox();
		CB_SumberDana.setBounds(246, 156, 202, 24);
		desktopPane.add(CB_SumberDana);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(246, 192, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_ReffPO = new JTextField();
		TF_ReffPO.setBounds(246, 26, 202, 19);
		desktopPane.add(TF_ReffPO);
		TF_ReffPO.setColumns(10);

		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(247, 260, 174, 19);
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
		btnUnggah.setBounds(420, 257, 84, 25);
		desktopPane.add(btnUnggah);
		
		JLabel lblUnggahDokument = new JLabel("Unggah Dokumen");
		lblUnggahDokument.setBounds(45, 262, 149, 15);
		desktopPane.add(lblUnggahDokument);

		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				data[0] = TF_ReffPO.getText();
				data[1] = TA_Catatan.getText();
				data[2] = String.valueOf(CL_Tanggal.getDate());
				data[3] = String.valueOf(CB_SumberDana.getSelectedItem());
				data[4] = TA_Keterangan.getText();
				data[5] = TF_Unggah.getText();

				tabelModel3.insertRow(0, data);
				dispose();
			}
		});
		btnTambah.setBounds(413, 294, 117, 25);
		desktopPane.add(btnTambah);
	}

	private void ShowComboBoxTagihan()
	{
		try
		{
			List<MasterDana> allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

			if (allMasterDana.size() > 0)
			{
				for (MasterDana anAllMasterDana : allMasterDana)
				{
					CB_SumberDana.addItem(anAllMasterDana.getNameBankAccount() + "-" + anAllMasterDana.getNoBankAccount());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}