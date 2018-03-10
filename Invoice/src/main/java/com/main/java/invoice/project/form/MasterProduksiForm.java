package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterProduksiDAO;
import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.pojo.MasterProduksi;

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

public class MasterProduksiForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Agent;
	private JTextField TF_DOP;
	private JTextField TF_Npwp;
	private JTextArea TA_Alamat;
	private JTextArea TA_Keterangan;
	private JTable table;
	MasterProduksiDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterProduksiForm frame = new MasterProduksiForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	MasterProduksiForm()
	{
		setTitle("Master Produksi");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120});
	}

	int row = 0;
	String data[]=new String[5];

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);

		JLabel lblNamaPerusahaan = new JLabel("Agent Produksi");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaDop = new JLabel("Nama DOP");
		lblNamaDop.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaDop);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 133, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 178, 133, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Agent = new JTextField();
		TF_Agent.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_Agent);
		TF_Agent.setColumns(10);
		
		TF_DOP = new JTextField();
		TF_DOP.setBounds(196, 53, 254, 19);
		desktopPane.add(TF_DOP);
		TF_DOP.setColumns(10);
		
		TA_Alamat = new JTextArea();
		TA_Alamat.setBounds(196, 81, 284, 53);
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Alamat);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 177, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 146, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterProduksi masterProduksi = null;
				masterProduksi.setAgentProduksi(TF_Agent.getText());
				masterProduksi.setName(TF_DOP.getText());
				masterProduksi.setAddress(TA_Alamat.getText());
				masterProduksi.setNoNpwp(TF_Npwp.getText());
				masterProduksi.setInformation(TA_Keterangan.getText());

				dao.DeleteMasterProduksiById(masterProduksi);
				tabelModel.removeRow(row);
				clearProduksi();
				TF_Agent.setEnabled(true);
			}
		});
		btnHapus.setBounds(462, 344, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterProduksi masterProduksi = null;
				masterProduksi.setAgentProduksi(TF_Agent.getText());
				masterProduksi.setName(TF_DOP.getText());
				masterProduksi.setAddress(TA_Alamat.getText());
				masterProduksi.setNoNpwp(TF_Npwp.getText());
				masterProduksi.setInformation(TA_Keterangan.getText());

				if(btnHapus.isEnabled() == false) {
					dao.addUpdate(masterProduksi, 0);
					data[0] = TF_Agent.getText();
					data[1] = TF_DOP.getText();
					data[2] = TA_Alamat.getText();
					data[3] = TF_Npwp.getText();
					data[4] = TA_Keterangan.getText();
					tabelModel.insertRow(0, data);
					clearProduksi();
					TF_Agent.setEnabled(true);
				} else {
					dao.addUpdate(masterProduksi, 1);
					data[0] = TF_Agent.getText();
					data[1] = TF_DOP.getText();
					data[2] = TA_Alamat.getText();
					data[3] = TF_Npwp.getText();
					data[4] = TA_Keterangan.getText();
					tabelModel.removeRow(row);
					tabelModel.insertRow(row, data);
					clearProduksi();
					TF_Agent.setEnabled(true);
				}
			}
		});
		btnSimpan.setBounds(336, 344, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==1) {
					showProduksi();
					btnHapus.setEnabled(true);
					TF_Agent.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 242, 534, 90);
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
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null}
				},
				new String [] {"Agent Produksi", "Nama DOP", "Alamat", "NPWP", "Keterangan"}
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
		List<MasterProduksi> masterProduksiList;
		masterProduksiList = dao.GetAllMasterProduksi();

		for(int i = 0; i < masterProduksiList.size(); i++) {
			data[0] = masterProduksiList.get(i).getAgentProduksi();
			data[1] = masterProduksiList.get(i).getName();
			data[2] = masterProduksiList.get(i).getAddress();
			data[3] = masterProduksiList.get(i).getNoNpwp();
			data[4] = masterProduksiList.get(i).getInformation();

			tabelModel.addRow(data);
		}
	}

	public void showProduksi()
	{
		row = table.getSelectedRow();
		TF_Agent.setText(tabelModel.getValueAt(row, 0).toString());
		TF_DOP.setText(tabelModel.getValueAt(row, 1).toString());
		TA_Alamat.setText(tabelModel.getValueAt(row, 2).toString());
		TF_Npwp.setText(tabelModel.getValueAt(row, 3).toString());
		TA_Keterangan.setText(tabelModel.getValueAt(row, 4).toString());
	}

	public void clearProduksi()
	{
		TF_Agent.setText("");
		TF_DOP.setText("");
		TA_Alamat.setText("");
		TF_Npwp.setText("");
		TA_Keterangan.setText("");
	}
}
