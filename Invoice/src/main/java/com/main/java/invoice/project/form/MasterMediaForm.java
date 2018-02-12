package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

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
	private JTextField TF_Npwp;
	private JTextField TF_BillComitment;
	private JTable table;

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
	}

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
		
		JTextArea TA_Alamat = new JTextArea();
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
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 204, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 269, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 371, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 371, 117, 25);
		desktopPane.add(btnHapus);

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
}
