package com.main.java;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;

public class Kontrak extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_NamaPerusahaan;
	
	@SuppressWarnings("unused")
	private JTextField TF_AlamatPerusahaan;
	private JTextField textField_2;
	private JTextField TF_Project;
	private JTextField TF_NilaiKontrak;
	
	@SuppressWarnings("unused")
	private JTextField TF_BayarMedia;
	
	@SuppressWarnings("unused")
	private JTextField TF_Cashback;
	
	@SuppressWarnings("unused")
	private JTextField TF_Entertain;
	
	@SuppressWarnings("unused")
	private JTextField TF_Margin;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontrak frame = new Kontrak();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Kontrak() {
		setClosable(true);
		setTitle("Kontrak");
		setBounds(100, 100, 630, 510);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNumber = new JLabel("Nomor Kontrak");
		lblPoNumber.setBounds(46, 26, 141, 15);
		desktopPane.add(lblPoNumber);
		
		JLabel lblAlias = new JLabel("Kode Perusahaan");
		lblAlias.setBounds(46, 60, 141, 15);
		desktopPane.add(lblAlias);
		
		JLabel lblNamaPt = new JLabel("Nama Perusahaan");
		lblNamaPt.setBounds(46, 93, 141, 15);
		desktopPane.add(lblNamaPt);
		
		JLabel lblAlamatPt = new JLabel("Alamat Perusahaan");
		lblAlamatPt.setBounds(46, 123, 141, 15);
		desktopPane.add(lblAlamatPt);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(46, 189, 70, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(46, 223, 70, 15);
		desktopPane.add(lblProject);
		
		JLabel lblTglTayang = new JLabel("Tgl. Pelaksanaan");
		lblTglTayang.setBounds(46, 254, 141, 15);
		desktopPane.add(lblTglTayang);
		
		JLabel lblNilaiKontrak = new JLabel("Nilai Kontrak");
		lblNilaiKontrak.setBounds(46, 288, 101, 15);
		desktopPane.add(lblNilaiKontrak);
		
		JLabel lblDp = new JLabel("DPP");
		lblDp.setBounds(92, 325, 70, 15);
		desktopPane.add(lblDp);
		
		JLabel lblPpn = new JLabel("PPN");
		lblPpn.setBounds(92, 352, 70, 15);
		desktopPane.add(lblPpn);
		
		JLabel lblPpn_1 = new JLabel("PPH 23");
		lblPpn_1.setBounds(92, 379, 70, 15);
		desktopPane.add(lblPpn_1);
		
		JLabel lblSpd = new JLabel("SP 2D");
		lblSpd.setBounds(92, 406, 70, 15);
		desktopPane.add(lblSpd);
		
		JLabel label = new JLabel("110 %");
		label.setBounds(174, 325, 52, 15);
		desktopPane.add(label);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(226, 325, 19, 15);
		desktopPane.add(lblX);
		
		JLabel label_1 = new JLabel("10 %");
		label_1.setBounds(174, 352, 52, 15);
		desktopPane.add(label_1);
		
		JLabel lblX_1 = new JLabel("X");
		lblX_1.setBounds(226, 352, 19, 15);
		desktopPane.add(lblX_1);
		
		JLabel label_2 = new JLabel("2 %");
		label_2.setBounds(174, 379, 52, 15);
		desktopPane.add(label_2);
		
		JLabel lblX_2 = new JLabel("X");
		lblX_2.setBounds(226, 379, 19, 15);
		desktopPane.add(lblX_2);
		
		JLabel lbldppPpn = new JLabel("(DPP - PPH 23)");
		lbldppPpn.setBounds(174, 406, 101, 15);
		desktopPane.add(lbldppPpn);
		
		JLabel label_3 = new JLabel("=");
		label_3.setBounds(415, 325, 19, 15);
		desktopPane.add(label_3);
		
		JLabel label_4 = new JLabel("=");
		label_4.setBounds(415, 352, 19, 15);
		desktopPane.add(label_4);
		
		JLabel label_5 = new JLabel("=");
		label_5.setBounds(415, 379, 19, 15);
		desktopPane.add(label_5);
		
		JLabel label_6 = new JLabel("=");
		label_6.setBounds(415, 406, 19, 15);
		desktopPane.add(label_6);
		
		/*JLabel lblFeeBendera = new JLabel("Fee Bendera");
		lblFeeBendera.setBounds(46, 403, 101, 15);
		desktopPane.add(lblFeeBendera);
		
		JLabel lblBayarMedia = new JLabel("Bayar Media");
		lblBayarMedia.setBounds(46, 430, 101, 15);
		desktopPane.add(lblBayarMedia);
		
		JLabel lblCashback = new JLabel("Cashback");
		lblCashback.setBounds(46, 457, 87, 15);
		desktopPane.add(lblCashback);
		
		JLabel lblEntertain = new JLabel("Entertain");
		lblEntertain.setBounds(46, 484, 87, 15);
		desktopPane.add(lblEntertain);
		
		JLabel lblMargin = new JLabel("Margin");
		lblMargin.setBounds(46, 511, 70, 15);
		desktopPane.add(lblMargin);*/
		
		/*JLabel label_7 = new JLabel("2,5 %");
		label_7.setBounds(195, 403, 52, 15);
		desktopPane.add(label_7);
		
		JLabel lblX_3 = new JLabel("X");
		lblX_3.setBounds(244, 403, 19, 15);
		desktopPane.add(lblX_3);
		
		JLabel lblHasilSpd = new JLabel("\"auto SP 2D\"");
		lblHasilSpd.setBounds(264, 403, 101, 15);
		desktopPane.add(lblHasilSpd);
		
		JLabel label_8 = new JLabel("=");
		label_8.setBounds(365, 403, 19, 15);
		desktopPane.add(label_8);*/
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_KodePerusahaan = new JComboBox();
		CB_KodePerusahaan.setBounds(194, 55, 212, 24);
		desktopPane.add(CB_KodePerusahaan);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(194, 91, 212, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		/*TF_AlamatPerusahaan = new JTextField();
		TF_AlamatPerusahaan.setBounds(194, 105, 267, 19);
		desktopPane.add(TF_AlamatPerusahaan);
		TF_AlamatPerusahaan.setColumns(10);*/
		JTextArea TA_AlamatPerusahaan = new JTextArea();
		TA_AlamatPerusahaan.setBounds(194, 122, 284, 53);
		TA_AlamatPerusahaan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_AlamatPerusahaan);
		
		textField_2 = new JTextField();
		textField_2.setBounds(194, 187, 212, 19);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		TF_Project = new JTextField();
		TF_Project.setBounds(194, 24, 212, 19);
		desktopPane.add(TF_Project);
		TF_Project.setColumns(10);
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_Pekerjaan = new JComboBox();
		CB_Pekerjaan.setBounds(194, 218, 212, 24);
		desktopPane.add(CB_Pekerjaan);
		
		TF_NilaiKontrak = new JTextField();
		TF_NilaiKontrak.setBounds(194, 286, 158, 19);
		desktopPane.add(TF_NilaiKontrak);
		TF_NilaiKontrak.setColumns(10);
		
		/*JLabel lblResul = new JLabel("\"Result Fee\"");
		lblResul.setBounds(391, 403, 117, 15);
		desktopPane.add(lblResul);*/
		
		/*TF_BayarMedia = new JTextField();
		TF_BayarMedia.setBounds(194, 428, 158, 19);
		desktopPane.add(TF_BayarMedia);
		TF_BayarMedia.setColumns(10);
		
		TF_Cashback = new JTextField();
		TF_Cashback.setBounds(194, 455, 158, 19);
		desktopPane.add(TF_Cashback);
		TF_Cashback.setColumns(10);
		
		TF_Entertain = new JTextField();
		TF_Entertain.setBounds(194, 482, 158, 19);
		desktopPane.add(TF_Entertain);
		TF_Entertain.setColumns(10);
		
		TF_Margin = new JTextField();
		TF_Margin.setBounds(194, 509, 158, 19);
		desktopPane.add(TF_Margin);
		TF_Margin.setColumns(10);*/
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(469, 433, 117, 25);
		desktopPane.add(btnFinish);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(193, 254, 157, 20);
		desktopPane.add(calendarComboBox);
		
		textField = new JTextField();
		textField.setBounds(244, 323, 153, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(244, 350, 153, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(244, 377, 153, 19);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(438, 323, 148, 19);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(438, 350, 148, 19);
		desktopPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(438, 377, 148, 19);
		desktopPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(438, 404, 148, 19);
		desktopPane.add(textField_7);
		textField_7.setColumns(10);
	}
}
