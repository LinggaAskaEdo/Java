package com.main.java.invoice.project.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.*;

import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.toedter.calendar.JDateChooser;
import de.wannawork.jcalendar.JCalendarComboBox;

public class TagihanMediaForm extends JInternalFrame {
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Invoice;
	private JTextField TF_Tagihan;
	private JTable table;
	private JTextField TF_ReffPoMedia;
	private JTextField TF_Unggah;
	private JComboBox CB_SumberDana;
	MasterDanaDAO masterDanaDAO;
	POMediaForm MediaForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagihanMediaForm frame = new TagihanMediaForm();
					frame.setVisible(true);
				} catch (Exception e) {
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

	String data[] = new String[5];

	public void initializeForm() {
		setClosable(true);
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 560, 303);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff PO. Media");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblInvoiceMedia = new JLabel("Invoice Media");
		lblInvoiceMedia.setBounds(45, 61, 153, 15);
		desktopPane.add(lblInvoiceMedia);
		
		JLabel lblTanggalTerima = new JLabel("Tanggal Terima");
		lblTanggalTerima.setBounds(45, 90, 153, 15);
		desktopPane.add(lblTanggalTerima);
		
		JLabel lblNilaiTagihan = new JLabel("Nilai Tagihan");
		lblNilaiTagihan.setBounds(45, 124, 153, 15);
		desktopPane.add(lblNilaiTagihan);
		
		JLabel lblReffSumberDana = new JLabel("Reff Sumber Dana");
		lblReffSumberDana.setBounds(45, 158, 153, 15);
		desktopPane.add(lblReffSumberDana);
		
		TF_Invoice = new JTextField();
		TF_Invoice.setBounds(216, 59, 238, 19);
		desktopPane.add(TF_Invoice);
		TF_Invoice.setColumns(10);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(216, 90, 158, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_Tagihan = new JTextField();
		TF_Tagihan.setBounds(216, 122, 185, 19);
		desktopPane.add(TF_Tagihan);
		TF_Tagihan.setColumns(10);
		
		CB_SumberDana = new JComboBox();
		CB_SumberDana.setBounds(216, 153, 185, 24);
		desktopPane.add(CB_SumberDana);
		
		TF_ReffPoMedia = new JTextField();
		TF_ReffPoMedia.setBounds(216, 26, 238, 19);
		desktopPane.add(TF_ReffPoMedia);
		TF_ReffPoMedia.setColumns(10);


		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(216, 192, 169, 19);
		desktopPane.add(TF_Unggah);

		JButton btnUnggah = new JButton("Browse");
		btnUnggah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					TF_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnUnggah.setBounds(386, 189, 103, 25);
		desktopPane.add(btnUnggah);

		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 194, 153, 15);
		desktopPane.add(lblUnggahDokumen);

		JButton button = new JButton("Tambah");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				data[0] = TF_Invoice.getText();
				data[1] = String.valueOf(CL_Tanggal.getDate());
				data[2] = TF_Tagihan.getText();
				data[3] = String.valueOf(CB_SumberDana.getSelectedItem());
				data[4] = TF_Unggah.getText();

				MediaForm.tabelModel.insertRow(0, data);
				dispose();
			}
		});
		button.setBounds(391, 226, 117, 25);
		desktopPane.add(button);
	}

	public void ShowComboBoxTagihan()
	{
		try {
			List<MasterDana> allMasterDana;
			allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

			for (int i = 0; i < allMasterDana.size(); i++) {

				CB_SumberDana.addItem(allMasterDana.get(i).getNameBankAccount()+"-"+allMasterDana.get(i).getNoBankAccount());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
