package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterLegalitasDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.pojo.MasterPerusahaan;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class MasterLegalitasForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_KodePerusahaan;
	private JTextField TF_NamaPerusahaan;
	private JTextField TF_Npwp;
	private JTextField TF_Pic;
	private JTextField TF_FeeAgency;
	private JLabel label;
	private JTextField TF_NoRek;
	private JTable table;
	private JTextField TF_Unggah;
	private JTextArea TA_Alamat;
	MasterLegalitasDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterLegalitasForm frame = new MasterLegalitasForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	MasterLegalitasForm()
	{
		setTitle("Master Legalitas Perusahaan");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120, 120, 120});
		setDefaultTable();
	}

	int row = 0;
	String data[]=new String[7];

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 630, 488);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Kode Perusahaan");
		lblNewLabel.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan.setBounds(45, 59, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 117, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 186, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblPicnoRek = new JLabel("PIC/Kontak");
		lblPicnoRek.setBounds(45, 217, 133, 15);
		desktopPane.add(lblPicnoRek);
		
		JLabel lblFeeAgency = new JLabel("Fee Agency");
		lblFeeAgency.setBounds(45, 279, 133, 15);
		desktopPane.add(lblFeeAgency);
		
		TF_KodePerusahaan = new JTextField();
		TF_KodePerusahaan.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_KodePerusahaan);
		TF_KodePerusahaan.setColumns(10);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(196, 57, 254, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 184, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		TF_Pic = new JTextField();
		TF_Pic.setBounds(196, 215, 254, 19);
		desktopPane.add(TF_Pic);
		TF_Pic.setColumns(10);
		
		TF_FeeAgency = new JTextField();
		TF_FeeAgency.setBounds(196, 277, 76, 19);
		desktopPane.add(TF_FeeAgency);
		TF_FeeAgency.setColumns(10);
		
		label = new JLabel("%");
		label.setBounds(271, 279, 70, 15);
		desktopPane.add(label);
		
		TA_Alamat = new JTextArea();
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Alamat.setBounds(196, 119, 284, 53);
		desktopPane.add(TA_Alamat);
		
		JLabel lblNoRek = new JLabel("No. Rek");
		lblNoRek.setBounds(45, 248, 70, 15);
		desktopPane.add(lblNoRek);
		
		TF_NoRek = new JTextField();
		TF_NoRek.setBounds(196, 246, 254, 19);
		desktopPane.add(TF_NoRek);
		TF_NoRek.setColumns(10);
		
		JLabel lblUnggahLogo = new JLabel("Unggah Logo");
		lblUnggahLogo.setBounds(45, 90, 105, 15);
		desktopPane.add(lblUnggahLogo);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(196, 88, 155, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					TF_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnBrowse.setBounds(351, 85, 87, 25);
		desktopPane.add(btnBrowse);

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterPerusahaan masterPerusahaan = null;
				masterPerusahaan.setCode(TF_KodePerusahaan.getText());
				masterPerusahaan.setName(TF_NamaPerusahaan.getText());
				masterPerusahaan.setImage(TF_Unggah.getText());
				masterPerusahaan.setAddress(TA_Alamat.getText());
				masterPerusahaan.setNoNpwp(TF_Npwp.getText());
				masterPerusahaan.setContactNumber(TF_Pic.getText());
				masterPerusahaan.setNoBankAccount(TF_NoRek.getText());
				masterPerusahaan.setFeeAgency(TF_FeeAgency.getText());

				try {
					dao.DeleteMasterPerusahaanById(masterPerusahaan);
					tabelModel.removeRow(row);
					clearLegalitas();
					TF_KodePerusahaan.setEnabled(true);
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnHapus.setBounds(462, 408, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterPerusahaan masterPerusahaan = null;
				masterPerusahaan.setCode(TF_KodePerusahaan.getText());
				masterPerusahaan.setName(TF_NamaPerusahaan.getText());
				masterPerusahaan.setImage(TF_Unggah.getText());
				masterPerusahaan.setAddress(TA_Alamat.getText());
				masterPerusahaan.setNoNpwp(TF_Npwp.getText());
				masterPerusahaan.setContactNumber(TF_Pic.getText());
				masterPerusahaan.setNoBankAccount(TF_NoRek.getText());
				masterPerusahaan.setFeeAgency(TF_FeeAgency.getText());

				if(btnHapus.isEnabled() == false) {
					try {
						dao.addUpdate(masterPerusahaan, 0);
						data[0] = TF_KodePerusahaan.getText();
						data[1] = TF_NamaPerusahaan.getText();
						data[2] = TA_Alamat.getText();
						data[3] = TF_Npwp.getText();
						data[4] = TF_Pic.getText();
						data[5] = TF_NoRek.getText();
						data[6] = TF_FeeAgency.getText();
						tabelModel.insertRow(0, data);
						clearLegalitas();
						TF_KodePerusahaan.setEnabled(true);
					} catch (Exception e2) {
						System.out.println(e2);
					}
				} else {
					try {
						dao.addUpdate(masterPerusahaan, 1);
						data[0] = TF_KodePerusahaan.getText();
						data[1] = TF_NamaPerusahaan.getText();
						data[2] = TA_Alamat.getText();
						data[3] = TF_Npwp.getText();
						data[4] = TF_Pic.getText();
						data[5] = TF_NoRek.getText();
						data[6] = TF_FeeAgency.getText();
						tabelModel.removeRow(row);
						tabelModel.insertRow(row, data);
						clearLegalitas();
						TF_KodePerusahaan.setEnabled(true);
					} catch (Exception e3) {
						System.out.println(e3);
					}
				}
			}
		});
		btnSimpan.setBounds(336, 408, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==1) {
					showLegalitas();
					btnHapus.setEnabled(true);
					TF_KodePerusahaan.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 306, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
	}

	private DefaultTableModel tabelModel = getDefaultTabelModel();
	private void Tabel(JTable tb, int lebar[]){
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int kolom = tb.getColumnCount();
		for (int i=0; i<kolom; i++){
			TableColumn tbc= tb.getColumnModel().getColumn(i);
			tbc.setPreferredWidth(lebar[i]);
			tb.setRowHeight(18);
		}
	}

	private DefaultTableModel getDefaultTabelModel(){
		return new DefaultTableModel(
				new Object [][] {
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null}
				},
				new String [] {"Kode Perusahaan", "Nama Perusahaan", "Alamat", "NPWP", "PIC/Kontak", "No. Rek", "Fee Agency"}
		){
			boolean [] canEdit = new boolean[]{
					false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	public void setDefaultTable()
	{
		try {
			List<MasterPerusahaan> masterPerusahaanList;
			masterPerusahaanList = dao.GetAllMasterPerusahaan();

			for(int i = 0; i < masterPerusahaanList.size(); i++) {
				data[0] = masterPerusahaanList.get(i).getCode();
				data[1] = masterPerusahaanList.get(i).getName();
				data[2] = masterPerusahaanList.get(i).getAddress();
				data[3] = masterPerusahaanList.get(i).getNoNpwp();
				data[4] = masterPerusahaanList.get(i).getContactNumber();
				data[5] = masterPerusahaanList.get(i).getNoBankAccount();
				data[6] = masterPerusahaanList.get(i).getFeeAgency();

				tabelModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void showLegalitas()
	{
		row = table.getSelectedRow();
		TF_KodePerusahaan.setText(tabelModel.getValueAt(row, 0).toString());
		TF_NamaPerusahaan.setText(tabelModel.getValueAt(row, 1).toString());
		TA_Alamat.setText(tabelModel.getValueAt(row, 2).toString());
		TF_Npwp.setText(tabelModel.getValueAt(row, 3).toString());
		TF_Pic.setText(tabelModel.getValueAt(row, 4).toString());
		TF_NoRek.setText(tabelModel.getValueAt(row, 5).toString());
		TF_FeeAgency.setText(tabelModel.getValueAt(row, 6).toString());
	}

	public void clearLegalitas()
	{
		TF_KodePerusahaan.setText("");
		TF_NamaPerusahaan.setText("");
		TF_Unggah.setText("");
		TA_Alamat.setText("");
		TF_Npwp.setText("");
		TF_Pic.setText("");
		TF_NoRek.setText("");
		TF_FeeAgency.setText("");
	}
}
