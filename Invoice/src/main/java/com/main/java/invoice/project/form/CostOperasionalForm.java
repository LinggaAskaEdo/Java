package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.*;

import com.main.java.invoice.project.dao.CostOperasionalDAO;
import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.CostOperasional;
import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.pojo.MasterMedia;
import com.toedter.calendar.JDateChooser;
import de.wannawork.jcalendar.JCalendarComboBox;

public class CostOperasionalForm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	
	@SuppressWarnings("unused")
	private JTable table;
	private JTextField TB_Kontrak;
	private JTextField TB_Unggah;
	private JTextField cbId;
	private JTextArea TA_Keperluan;
	private JComboBox CB_ReffSumbDana;
	MasterDanaDAO masterDanaDAO;
	CostOperasionalDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                CostOperasionalForm frame = new CostOperasionalForm();
                frame.setVisible(true);
            } catch (Exception e) {
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

	public void initializeForm() {

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
		CB_ReffSumbDana.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				MasterDana masterDana = null;

				String splitData = String.valueOf(CB_ReffSumbDana.getSelectedItem());
				masterDana = masterDanaDAO.GetMasterDanaById(splitData);

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
		BT_Unggah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					TB_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		BT_Unggah.setBounds(383, 182, 87, 25);
		desktopPane.add(BT_Unggah);

		JButton BT_Simpan = new JButton("Simpan");
		BT_Simpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostOperasional costOperasional = null;
				MasterDana masterDana;

				try {
					String splitData = String.valueOf(CB_ReffSumbDana.getSelectedItem());
					masterDana = masterDanaDAO.GetMasterDanaById(splitData);
					costOperasional.setMasterDanaId(masterDana.getMasterDanaId());

					costOperasional.setPic(TB_Kontrak.getText());
					costOperasional.setKeperluan(TA_Keperluan.getText());
					costOperasional.setTanggalPemebelian(CL_Tanggal.getDate());
					costOperasional.setImage(TB_Unggah.getText());

					dao.addCostOperasional(costOperasional);
					ClearCost();
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		BT_Simpan.setBounds(366, 223, 117, 25);
		desktopPane.add(BT_Simpan);
	}

	public void ClearCost()
	{
		TB_Kontrak.setText("");
		TA_Keperluan.setText("");
		TB_Unggah.setText("");
	}

	public void ShowComboBoxCost()
	{
		try {
			List<MasterDana> allMasterDana;
			allMasterDana = masterDanaDAO.GetAllMasterDanaComboBox();

			for (int i = 0; i < allMasterDana.size(); i++) {

				CB_ReffSumbDana.addItem(allMasterDana.get(i).getNameBankAccount()+"-"+allMasterDana.get(i).getNoBankAccount());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
