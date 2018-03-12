package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterMediaDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.pojo.MasterMedia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.table.TableColumn;

public class MasterMediaForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_NamaPerusahaan;
	private JTextField TF_NamaMedia;
	private JTextArea TA_Alamat;
	private JTextField TF_Npwp;
	private JTextField TF_BillComitment;
	private JTextArea TA_Keterangan;
	private JTable table;
	MasterMediaDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMediaForm frame = new MasterMediaForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	MasterMediaForm()
	{
		setTitle("Master Media");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120, 120});
		setDefaultTable();
	}

	int row = 0;
	String data[]=new String[6];

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 630, 450);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Nama Perusahaan");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblBillCommitment = new JLabel("Bill Comitment");
		lblBillCommitment.setBounds(45, 175, 133, 15);
		desktopPane.add(lblBillCommitment);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 205, 133, 15);
		desktopPane.add(lblKeterangan);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(196, 26, 233, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		TF_NamaMedia = new JTextField();
		TF_NamaMedia.setBounds(196, 53, 233, 19);
		desktopPane.add(TF_NamaMedia);
		TF_NamaMedia.setColumns(10);
		
		TA_Alamat = new JTextArea();
		TA_Alamat.setBounds(196, 81, 284, 53);
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Alamat);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 146, 233, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		TF_BillComitment = new JTextField();
		TF_BillComitment.setBounds(196, 173, 233, 19);
		desktopPane.add(TF_BillComitment);
		TF_BillComitment.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 204, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterMedia masterMedia = null;
				masterMedia.setCompanyName(TF_NamaPerusahaan.getText());
				masterMedia.setMediaName(TF_NamaMedia.getText());
				masterMedia.setAddress(TA_Alamat.getText());
				masterMedia.setNoNpwp(TF_Npwp.getText());
				masterMedia.setBillCommitment(TF_BillComitment.getText());
				masterMedia.setInformation(TA_Keterangan.getText());

				dao.DeleteMasterMediaById(masterMedia);
				tabelModel.removeRow(row);
				clearMedia();
				TF_NamaPerusahaan.setEnabled(true);
			}
		});
		btnHapus.setBounds(462, 371, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterMedia masterMedia = null;
				masterMedia.setCompanyName(TF_NamaPerusahaan.getText());
				masterMedia.setMediaName(TF_NamaMedia.getText());
				masterMedia.setAddress(TA_Alamat.getText());
				masterMedia.setNoNpwp(TF_Npwp.getText());
				masterMedia.setBillCommitment(TF_BillComitment.getText());
				masterMedia.setInformation(TA_Keterangan.getText());

				if(btnHapus.isEnabled() == false){
					dao.addUpdate(masterMedia, 0);
					data[0] = TF_NamaPerusahaan.getText();
					data[1] = TF_NamaMedia.getText();
					data[2] = TA_Alamat.getText();
					data[3] = TF_Npwp.getText();
					data[4] = TF_BillComitment.getText();
					data[5] = TA_Keterangan.getText();
					tabelModel.insertRow(0, data);
					clearMedia();
					TF_NamaPerusahaan.setEnabled(true);
				} else {
					dao.addUpdate(masterMedia, 1);
					data[0] = TF_NamaPerusahaan.getText();
					data[1] = TF_NamaMedia.getText();
					data[2] = TA_Alamat.getText();
					data[3] = TF_Npwp.getText();
					data[4] = TF_BillComitment.getText();
					data[5] = TA_Keterangan.getText();
					tabelModel.removeRow(row);
					tabelModel.insertRow(row, data);
					clearMedia();
					TF_NamaPerusahaan.setEnabled(true);
				}
			}
		});
		btnSimpan.setBounds(336, 371, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==1) {
					showMedia();
					btnHapus.setEnabled(true);
					TF_NamaPerusahaan.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 269, 534, 90);
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
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
						{null, null, null, null, null, null}
				},
				new String [] {"Nama Perusahaan", "Nama Media", "Alamat", "NPWP", "Bill Commitment", "Keterangan"}
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
		List<MasterMedia> masterMediaList;
		masterMediaList = dao.GetAllMasterMedia();

		for(int i = 0; i < masterMediaList.size(); i++) {
			data[0] = masterMediaList.get(i).getCompanyName();
			data[1] = masterMediaList.get(i).getMediaName();
			data[2] = masterMediaList.get(i).getAddress();
			data[3] = masterMediaList.get(i).getNoNpwp();
			data[4] = masterMediaList.get(i).getBillCommitment();
			data[5] = masterMediaList.get(i).getInformation();

			tabelModel.addRow(data);
		}
	}

	public void showMedia()
	{
		row = table.getSelectedRow();
		TF_NamaPerusahaan.setText(tabelModel.getValueAt(row, 0).toString());
		TF_NamaMedia.setText(tabelModel.getValueAt(row, 1).toString());
		TA_Alamat.setText(tabelModel.getValueAt(row, 2).toString());
		TF_Npwp.setText(tabelModel.getValueAt(row, 3).toString());
		TF_BillComitment.setText(tabelModel.getValueAt(row, 4).toString());
		TA_Keterangan.setText(tabelModel.getValueAt(row, 5).toString());
	}

	public void clearMedia()
	{
		TF_NamaPerusahaan.setText("");
		TF_NamaMedia.setText("");
		TA_Alamat.setText("");
		TF_Npwp.setText("");
		TF_BillComitment.setText("");
		TA_Keterangan.setText("");
	}
}
