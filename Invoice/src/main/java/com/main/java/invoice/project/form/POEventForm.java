package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;

public class POEventForm extends JInternalFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Event;
	private JTextField TF_Value;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTextField TF_PONomor;
	private JTextField TF_ReffKontrak;
	private JTextField TF_Kegiatan;
	private JTextField TF_Jumlah;
	private JTextField TF_Unggah;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POEventForm frame = new POEventForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	POEventForm()
	{
		setTitle("PO. Event");
		initializeForm();
		table1.setModel(tabelModel1);
		Tabel(table1, new int[]{120, 120, 120, 120, 120, 120, 120, 120});
		table2.setModel(tabelModel2);
		Tabel(table2, new int[]{120, 300, 120});
		table3.setModel(tabelModel3);
		Tabel(table3, new int[]{120, 120, 120, 120, 120});
	}

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 630, 693);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 164, 15);
		desktopPane.add(lblPoNomor);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(480, 628, 117, 25);
		desktopPane.add(btnSimpan);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 118, 15);
		desktopPane.add(lblReffKontrak);
		
		JLabel lblKegiatan = new JLabel("Kegiatan");
		lblKegiatan.setBounds(45, 90, 105, 15);
		desktopPane.add(lblKegiatan);
		
		JLabel lblTanggalPelaksanaan = new JLabel("Tanggal Event");
		lblTanggalPelaksanaan.setBounds(45, 119, 117, 15);
		desktopPane.add(lblTanggalPelaksanaan);
		
		JLabel lblBiayaDetailEvent = new JLabel("Biaya Detail Event");
		lblBiayaDetailEvent.setBounds(45, 146, 139, 15);
		desktopPane.add(lblBiayaDetailEvent);
		
		JLabel lblBiayaReimbursement = new JLabel("Biaya Reimbursement");
		lblBiayaReimbursement.setBounds(45, 275, 164, 15);
		desktopPane.add(lblBiayaReimbursement);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(45, 404, 70, 15);
		desktopPane.add(lblJumlah);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 431, 118, 15);
		desktopPane.add(lblKeterangan);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(227, 26, 252, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(227, 57, 252, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(227, 119, 158, 20);
		desktopPane.add(CL_Tanggal);
		
		TF_Kegiatan = new JTextField();
		TF_Kegiatan.setBounds(227, 88, 252, 19);
		desktopPane.add(TF_Kegiatan);
		TF_Kegiatan.setColumns(10);
		
		table1 = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 173, 495, 90);
		scrollPane.setViewportView(table1);
		desktopPane.add(scrollPane);
		
		JButton btnPlus_1 = new JButton("+");
		btnPlus_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DetailEventForm detailEvent = new DetailEventForm();
				desktopPane.add(detailEvent);
				detailEvent.setVisible(true);
			}
		});
		btnPlus_1.setBounds(549, 173, 49, 25);
		desktopPane.add(btnPlus_1);
		
		JButton btnMinus_1 = new JButton("-");
		btnMinus_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnMinus_1.setBounds(548, 207, 49, 25);
		desktopPane.add(btnMinus_1);
		
		table2 = new JTable();

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(45, 302, 495, 90);
		scrollPane1.setViewportView(table2);
		desktopPane.add(scrollPane1);
		
		JButton btnPlus_2 = new JButton("+");
		btnPlus_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DetailReimbursementForm detailReimbursement = new DetailReimbursementForm();
				desktopPane.add(detailReimbursement);
				detailReimbursement.setVisible(true);
			}
		});
		btnPlus_2.setBounds(548, 302, 49, 25);
		desktopPane.add(btnPlus_2);
		
		JButton btnMinus_2 = new JButton("-");
		btnMinus_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnMinus_2.setBounds(547, 336, 49, 25);
		desktopPane.add(btnMinus_2);
		
		TF_Jumlah = new JTextField();
		TF_Jumlah.setBounds(227, 402, 186, 19);
		desktopPane.add(TF_Jumlah);
		TF_Jumlah.setColumns(10);
		
		JTextArea TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(227, 430, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		JButton btnUnggah = new JButton("Upload");
		btnUnggah.setBounds(386, 597, 84, 25);
		desktopPane.add(btnUnggah);
		
		table3 = new JTable();

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(45, 495, 495, 90);
		scrollPane2.setViewportView(table3);
		desktopPane.add(scrollPane2);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(227, 601, 158, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);
		
		JButton btnPlus_3 = new JButton("+");
		btnPlus_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TagihanReimbursementForm tagihanReimbursement = new TagihanReimbursementForm();
				desktopPane.add(tagihanReimbursement);
				tagihanReimbursement.setVisible(true);
			}
		});
		btnPlus_3.setBounds(549, 495, 49, 25);
		desktopPane.add(btnPlus_3);
		
		JButton btnMinus_3 = new JButton("-");
		btnMinus_3.setBounds(549, 528, 49, 25);
		btnMinus_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		desktopPane.add(btnMinus_3);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 603, 139, 15);
		desktopPane.add(lblUnggahDokumen);
	}

	private DefaultTableModel tabelModel1 = getDefaultTabelModel1();
	private DefaultTableModel tabelModel2 = getDefaultTabelModel2();
	private DefaultTableModel tabelModel3 = getDefaultTabelModel3();

	private void Tabel(JTable tb, int lebar[]){
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int kolom = tb.getColumnCount();
		for (int i=0; i<kolom; i++){
			TableColumn tbc= tb.getColumnModel().getColumn(i);
			tbc.setPreferredWidth(lebar[i]);
			tb.setRowHeight(18);
		}
	}

	private DefaultTableModel getDefaultTabelModel1(){
		return new DefaultTableModel(
				new Object [][] {
						{null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null}
				},
				new String [] {"Uraian", "Detail", "Jumlah 1", "Jenis 1", "Jumlah 2", "Jenis 2","Harga Satuan", "Total"}
		){
			boolean [] canEdit = new boolean[]{
					false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private DefaultTableModel getDefaultTabelModel2(){
		return new DefaultTableModel(
				new Object [][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null}
				},
				new String [] {"Uraian", "Detail", "Harga"}
		){
			boolean [] canEdit = new boolean[]{
					false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private DefaultTableModel getDefaultTabelModel3(){
		return new DefaultTableModel(
				new Object [][] {
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null}
				},
				new String [] {"Reff PO", "Catatan Reimburse", "Tanggal", "Reff Sumber Dana", "Keterangan"}
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
