package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.FundingDAO;
import com.main.java.invoice.project.dao.KontrakDAO;
import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.Funding;
import com.main.java.invoice.project.pojo.Kontrak;
import com.main.java.invoice.project.pojo.MasterDana;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class FundingForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Nama;
	private JTextField TF_Nilai;
	private JTextField TF_Unggah;
	private JTextField reffId;
	private ButtonGroup buttonGroup;
	private JTextArea TA_Keterangan;
	
	@SuppressWarnings("rawtypes")
	private JComboBox CB_Reff;
	
	private JRadioButton rdbtnKontrak;
	private JRadioButton rdbtnMasterDana;
	private FundingDAO dao = new FundingDAO();
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	private KontrakDAO kontrakDAO = new KontrakDAO();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					FundingForm frame = new FundingForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	FundingForm()
	{
		setTitle("FUNDING");
		initializeForm();
		ShowComboBoxKontrakFunding();
	}

	@SuppressWarnings("rawtypes")
	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 530, 353);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama/kontak");
		lblNamaPerusahaan.setBounds(39, 26, 110, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblReff = new JLabel("Reff");
		lblReff.setBounds(39, 53, 110, 15);
		desktopPane.add(lblReff);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(39, 116, 70, 15);
		desktopPane.add(lblTanggal);
		
		JLabel lblNilai = new JLabel("Nilai");
		lblNilai.setBounds(39, 150, 70, 15);
		desktopPane.add(lblNilai);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(39, 177, 110, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Nama = new JTextField();
		TF_Nama.setBounds(173, 26, 294, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(173, 118, 184, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_Nilai = new JTextField();
		TF_Nilai.setBounds(173, 150, 184, 19);
		desktopPane.add(TF_Nilai);
		TF_Nilai.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(173, 178, 294, 53);
		desktopPane.add(TA_Keterangan);
		
		rdbtnKontrak = new JRadioButton("Kontrak");
		rdbtnKontrak.setActionCommand("1");
		rdbtnKontrak.setBounds(173, 51, 93, 23);
		rdbtnKontrak.setSelected(true);
		desktopPane.add(rdbtnKontrak);
		
		rdbtnMasterDana = new JRadioButton("Master Dana");
		rdbtnMasterDana.setActionCommand("2");
		rdbtnMasterDana.setBounds(280, 51, 128, 23);
		desktopPane.add(rdbtnMasterDana);

		buttonGroup=new ButtonGroup();
		buttonGroup.add(rdbtnKontrak);
		buttonGroup.add(rdbtnMasterDana);
		
		CB_Reff = new JComboBox();
		CB_Reff.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				if (buttonGroup.getSelection().getActionCommand().equalsIgnoreCase("1"))
				{
					Kontrak kontrak = new Kontrak();
					kontrak.setNoKontrak(String.valueOf(CB_Reff.getSelectedItem()));
					kontrak = kontrakDAO.GetKontrakById(kontrak);

					reffId.setText(String.valueOf(kontrak.getKontrakId()));
				}
				else
				{
					String splitData = String.valueOf(CB_Reff.getSelectedItem());
					if(splitData.matches("-")){
						MasterDana masterDana = masterDanaDAO.GetMasterDanaById(splitData);
						reffId.setText(String.valueOf(masterDana.getMasterDanaId()));
					}
				}
			}
		});
		CB_Reff.setBounds(173, 82, 235, 24);
		desktopPane.add(CB_Reff);

		reffId = new JTextField();
		reffId.setBounds(420, 85, 42, 19);
		desktopPane.add(reffId);
		reffId.setColumns(10);
		reffId.setVisible(false);

		FundingForm.RadioButtonActionListener actionListener = new FundingForm.RadioButtonActionListener();
		rdbtnKontrak.addActionListener(actionListener);
		rdbtnMasterDana.addActionListener(actionListener);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(39, 246, 128, 15);
		desktopPane.add(lblUnggahDokumen);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(173, 246, 184, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton BT_Unggah = new JButton("Browse");
		BT_Unggah.addActionListener(new ActionListener()
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
		BT_Unggah.setBounds(361, 243, 93, 25);
		desktopPane.add(BT_Unggah);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!TF_Nilai.getText().matches("^[0-9]+$"))
				{
					JOptionPane.showMessageDialog(null, "Nilai hanya bisa disi dengan angka", "Peringatan",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (reffId.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "Simpan Gagal, No. Reff tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Funding funding = new Funding();

					try
					{
						funding.setKontakName(TF_Nama.getText());

						if (buttonGroup.getSelection().getActionCommand().equalsIgnoreCase("1"))
						{
							funding.setCheckReff(1);
						}
						else
						{
							funding.setCheckReff(0);
						}

						funding.setReff(Integer.valueOf(reffId.getText()));
						funding.setTanggal(CL_Tanggal.getDate());
						funding.setNilai(new BigDecimal(TF_Nilai.getText()));
						funding.setKeterangan(TA_Keterangan.getText());

						if (TF_Unggah.getText().length() > 0)
							funding.setImage(TF_Unggah.getText());
						else
							funding.setImage(null);

						dao.addFunding(funding);
						ClearFunding();

						JOptionPane.showMessageDialog(null, "Simpan Berhasil", "", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnSimpan.setBounds(350, 277, 117, 25);
		desktopPane.add(btnSimpan);
	}

	private void ClearFunding()
	{
		TF_Nama.setText("");
		TF_Nilai.setText("");
		TA_Keterangan.setText("");
		TF_Unggah.setText("");
	}

	@SuppressWarnings("unchecked")
	private void ShowComboBoxDanaFunding()
	{
		List<MasterDana> allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

		if (allMasterDana.size() > 0)
		{
			for (MasterDana anAllMasterDana : allMasterDana)
			{
				CB_Reff.addItem(anAllMasterDana.getNameBankAccount() + "-" + anAllMasterDana.getNoBankAccount());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void ShowComboBoxKontrakFunding()
	{
		List<Kontrak> allKontrak = kontrakDAO.GetAllKontrakComboBox();

		if (allKontrak.size() > 0)
		{
			for (Kontrak anAllKontrak : allKontrak)
			{
				CB_Reff.addItem(anAllKontrak.getNoKontrak());
			}
		}
	}

	class RadioButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JRadioButton button = (JRadioButton) event.getSource();

			if (button == rdbtnKontrak)
			{
				CB_Reff.removeAllItems();
				ShowComboBoxKontrakFunding();
			}
			else
			{
				CB_Reff.removeAllItems();
				ShowComboBoxDanaFunding();
			}
		}
	}
}