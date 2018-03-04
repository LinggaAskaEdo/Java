package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		
		JTextArea TA_Keperluan = new JTextArea();
		TA_Keperluan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keperluan.setBounds(216, 88, 268, 53);
		desktopPane.add(TA_Keperluan);
		
		JCalendarComboBox CL_Tanggal = new JCalendarComboBox();
		CL_Tanggal.setBounds(216, 153, 165, 20);
		desktopPane.add(CL_Tanggal);
		
		JButton BT_Simpan = new JButton("Simpan");
		BT_Simpan.setBounds(366, 223, 117, 25);
		desktopPane.add(BT_Simpan);
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_ReffSumbDana = new JComboBox();
		CB_ReffSumbDana.setBounds(216, 23, 205, 24);
		desktopPane.add(CB_ReffSumbDana);
		
		JLabel lblNewLabel = new JLabel("Unggah Dokumen");
		lblNewLabel.setBounds(45, 187, 153, 15);
		desktopPane.add(lblNewLabel);
		
		TB_Unggah = new JTextField();
		TB_Unggah.setBounds(216, 185, 165, 19);
		desktopPane.add(TB_Unggah);
		TB_Unggah.setColumns(10);
		
		JButton BT_Unggah = new JButton("Browse");
		BT_Unggah.setBounds(383, 182, 87, 25);
		desktopPane.add(BT_Unggah);

	}
}
