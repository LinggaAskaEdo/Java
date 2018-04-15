package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.CostOperasionalDAO;
import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.CostOperasional;
import com.main.java.invoice.project.pojo.MasterDana;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

public class CostOperasionalForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();

	private JTextField TB_Kontrak;
	private JTextField TB_Unggah;
	private JTextField cbId;
	private JTextArea TA_Keperluan;
	private JComboBox CB_ReffSumbDana;
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	CostOperasionalDAO dao = new CostOperasionalDAO();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
            try
			{
                CostOperasionalForm frame = new CostOperasionalForm();
                frame.setVisible(true);
            }
            catch (Exception e)
			{
                e.printStackTrace();
            }
        });
	}

	CostOperasionalForm()
	{
		setTitle("Cost Operasional");
		initializeForm();
		ShowComboBoxCost();
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 552, 300);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff Sumber Dana");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblPickontak = new JLabel("PIC/Kontak");
		lblPickontak.setBounds(45, 59, 153, 15);
		desktopPane.add(lblPickontak);
		
		JLabel lblKeperluan = new JLabel("Keperluan");
		lblKeperluan.setBounds(45, 89, 153, 15);
		desktopPane.add(lblKeperluan);
		
		JLabel lblTanggalPembelian = new JLabel("Tanggal Pembelian");
		lblTanggalPembelian.setBounds(45, 153, 153, 15);
		desktopPane.add(lblTanggalPembelian);
		
		TB_Kontrak = new JTextField();
		TB_Kontrak.setBounds(216, 57, 268, 19);
		desktopPane.add(TB_Kontrak);
		TB_Kontrak.setColumns(10);
		
		TA_Keperluan = new JTextArea();
		TA_Keperluan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keperluan.setBounds(216, 88, 268, 53);
		desktopPane.add(TA_Keperluan);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(216, 153, 165, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		CB_ReffSumbDana = new JComboBox();
		CB_ReffSumbDana.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				String splitData = String.valueOf(CB_ReffSumbDana.getSelectedItem());
				MasterDana masterDana = masterDanaDAO.GetMasterDanaById(splitData);

				cbId.setText(masterDana.getMasterDanaId().toString());
			}
		});
		CB_ReffSumbDana.setBounds(216, 23, 205, 24);
		desktopPane.add(CB_ReffSumbDana);

		cbId = new JTextField();
		cbId.setBounds(433, 26, 42, 19);
		desktopPane.add(cbId);
		cbId.setColumns(10);
		cbId.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Unggah Dokumen");
		lblNewLabel.setBounds(45, 187, 153, 15);
		desktopPane.add(lblNewLabel);
		
		TB_Unggah = new JTextField();
		TB_Unggah.setBounds(216, 185, 165, 19);
		desktopPane.add(TB_Unggah);
		TB_Unggah.setColumns(10);
		
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
					TB_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		BT_Unggah.setBounds(383, 182, 87, 25);
		desktopPane.add(BT_Unggah);

		JButton BT_Simpan = new JButton("Simpan");
		BT_Simpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String splitData = String.valueOf(CB_ReffSumbDana.getSelectedItem());
				MasterDana masterDana = masterDanaDAO.GetMasterDanaById(splitData);

				if (masterDana.getMasterDanaId() == null)
				{
					JOptionPane.showMessageDialog(null, "Simpan Gagal, Master Dana tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					CostOperasional costOperasional = new CostOperasional();

					try
					{
						costOperasional.setMasterDanaId(masterDana.getMasterDanaId());
						costOperasional.setPic(TB_Kontrak.getText());
						costOperasional.setKeperluan(TA_Keperluan.getText());
						costOperasional.setTanggalPemebelian(CL_Tanggal.getDate());

						if (TB_Unggah.getText().length() > 0)
							costOperasional.setImage(TB_Unggah.getText());
						else
							costOperasional.setImage(null);

						dao.addCostOperasional(costOperasional);
						ClearCost();

						JOptionPane.showMessageDialog(null, "Simpan Berhasil", "", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1)
					{
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		BT_Simpan.setBounds(366, 223, 117, 25);
		desktopPane.add(BT_Simpan);
	}

	private void ClearCost()
	{
		TB_Kontrak.setText("");
		TA_Keperluan.setText("");
		TB_Unggah.setText("");
	}

	private void ShowComboBoxCost()
	{
		try
		{
			List<MasterDana> allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

			if (allMasterDana.size() > 0)
			{
				for (MasterDana anAllMasterDana : allMasterDana)
				{
					CB_ReffSumbDana.addItem(anAllMasterDana.getNameBankAccount() + "-" + anAllMasterDana.getNoBankAccount());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
