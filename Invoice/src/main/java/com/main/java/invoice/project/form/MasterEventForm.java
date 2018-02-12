package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MasterEventForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private JTextField TF_Agent;
	private JTextField TF_NamaKoor;
	private JTextField TF_Npwp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterEventForm frame = new MasterEventForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	MasterEventForm()
	{
		setTitle("Master Event");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120});
	}

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaPerusahaan = new JLabel("Agent Event");
		lblNamaPerusahaan.setBounds(45, 28, 133, 15);
		desktopPane.add(lblNamaPerusahaan);
		
		JLabel lblNamaKoordinator = new JLabel("Nama Koordinator");
		lblNamaKoordinator.setBounds(45, 55, 133, 15);
		desktopPane.add(lblNamaKoordinator);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 82, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(45, 148, 70, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 178, 133, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Agent = new JTextField();
		TF_Agent.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_Agent);
		TF_Agent.setColumns(10);
		
		TF_NamaKoor = new JTextField();
		TF_NamaKoor.setBounds(196, 53, 254, 19);
		desktopPane.add(TF_NamaKoor);
		TF_NamaKoor.setColumns(10);
		
		JTextArea TA_Alamat = new JTextArea();
		TA_Alamat.setBounds(196, 81, 284, 53);
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Alamat);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 177, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(196, 146, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 242, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(336, 344, 117, 25);
		desktopPane.add(btnSimpan);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(462, 344, 117, 25);
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
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
				},
				new String [] {"Agent Event", "Nama Koordinator", "Alamat", "NPWP", "Keterangan"}
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
