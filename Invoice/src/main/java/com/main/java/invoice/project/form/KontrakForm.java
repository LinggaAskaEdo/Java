package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.main.java.invoice.project.function.GeneralFunction;

public class KontrakForm extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_NamaPerusahaan;
	
	@SuppressWarnings("unused")
	private JTextField TF_AlamatPerusahaan;
	private JTextField TF_Npwp;
	private JTextField TF_NoKontrak;
	private JTextField TF_NilaiKontrak;
	
	@SuppressWarnings("unused")
	private JTextField TF_BayarMedia;
	
	@SuppressWarnings("unused")
	private JTextField TF_Cashback;
	
	@SuppressWarnings("unused")
	private JTextField TF_Entertain;
	
	@SuppressWarnings("unused")
	private JTextField TF_Margin;
	private JTextField TF_Dpp;
	private JTextField TF_Ppn;
	private JTextField TF_Pph_23;
	private JTextField TF_ResultDpp;
	private JTextField TF_ResultPpn;
	private JTextField TF_ResultPph_23;
	private JTextField TF_ResultSP_2D;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					KontrakForm frame = new KontrakForm();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	KontrakForm()
	{
		addInternalFrameListener(new InternalFrameAdapter()
		{
			@Override
			public void internalFrameOpened(InternalFrameEvent e) 
			{
				GeneralFunction generalFunction = new GeneralFunction();
				String newCode = generalFunction.generateCodeName();
				System.out.println("newCode: " + newCode);
				TF_NoKontrak.setText(newCode);
			}
		});
		setResizable(false);
		setTitle("Kontrak");
		initializeForm();
	}

	private void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 534);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNumber = new JLabel("Nomor Kontrak");
		lblPoNumber.setBounds(46, 59, 141, 15);
		desktopPane.add(lblPoNumber);
		
		JLabel lblAlias = new JLabel("Kode Perusahaan");
		lblAlias.setBounds(46, 93, 141, 15);
		desktopPane.add(lblAlias);
		
		JLabel lblNamaPt = new JLabel("Nama Perusahaan");
		lblNamaPt.setBounds(46, 126, 141, 15);
		desktopPane.add(lblNamaPt);
		
		JLabel lblAlamatPt = new JLabel("Alamat Perusahaan");
		lblAlamatPt.setBounds(46, 156, 141, 15);
		desktopPane.add(lblAlamatPt);
		
		JLabel lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(46, 222, 70, 15);
		desktopPane.add(lblNpwp);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(46, 256, 70, 15);
		desktopPane.add(lblProject);
		
		JLabel lblTglTayang = new JLabel("Tgl. Pelaksanaan");
		lblTglTayang.setBounds(46, 287, 141, 15);
		desktopPane.add(lblTglTayang);
		
		JLabel lblNilaiKontrak = new JLabel("Nilai Kontrak");
		lblNilaiKontrak.setBounds(46, 321, 101, 15);
		desktopPane.add(lblNilaiKontrak);
		
		JLabel lblDp = new JLabel("DPP");
		lblDp.setBounds(76, 350, 70, 15);
		desktopPane.add(lblDp);
		
		JLabel lblPpn = new JLabel("PPN");
		lblPpn.setBounds(76, 377, 70, 15);
		desktopPane.add(lblPpn);
		
		JLabel lblPpn_1 = new JLabel("PPH 23");
		lblPpn_1.setBounds(76, 404, 70, 15);
		desktopPane.add(lblPpn_1);
		
		JLabel lblSpd = new JLabel("SP 2D");
		lblSpd.setBounds(76, 431, 70, 15);
		desktopPane.add(lblSpd);
		
		JLabel label = new JLabel("110 %");
		label.setBounds(158, 350, 52, 15);
		desktopPane.add(label);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(210, 350, 19, 15);
		desktopPane.add(lblX);
		
		JLabel label_1 = new JLabel("10 %");
		label_1.setBounds(158, 377, 52, 15);
		desktopPane.add(label_1);
		
		JLabel lblX_1 = new JLabel("X");
		lblX_1.setBounds(210, 377, 19, 15);
		desktopPane.add(lblX_1);
		
		JLabel label_2 = new JLabel("2 %");
		label_2.setBounds(158, 404, 52, 15);
		desktopPane.add(label_2);
		
		JLabel lblX_2 = new JLabel("X");
		lblX_2.setBounds(210, 404, 19, 15);
		desktopPane.add(lblX_2);
		
		JLabel lbldppPpn = new JLabel("(DPP - PPH 23)");
		lbldppPpn.setBounds(158, 431, 101, 15);
		desktopPane.add(lbldppPpn);
		
		JLabel label_3 = new JLabel("=");
		label_3.setBounds(399, 350, 19, 15);
		desktopPane.add(label_3);
		
		JLabel label_4 = new JLabel("=");
		label_4.setBounds(399, 377, 19, 15);
		desktopPane.add(label_4);
		
		JLabel label_5 = new JLabel("=");
		label_5.setBounds(399, 404, 19, 15);
		desktopPane.add(label_5);
		
		JLabel label_6 = new JLabel("=");
		label_6.setBounds(399, 431, 19, 15);
		desktopPane.add(label_6);
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_KodePerusahaan = new JComboBox();
		CB_KodePerusahaan.setBounds(194, 88, 212, 24);
		desktopPane.add(CB_KodePerusahaan);
		
		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(194, 124, 212, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);
		
		JTextArea TA_AlamatPerusahaan = new JTextArea();
		TA_AlamatPerusahaan.setBounds(194, 155, 284, 53);
		TA_AlamatPerusahaan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_AlamatPerusahaan);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(194, 220, 212, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		TF_NoKontrak = new JTextField();
		TF_NoKontrak.setBounds(194, 57, 212, 19);
		desktopPane.add(TF_NoKontrak);
		TF_NoKontrak.setColumns(10);
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_Project = new JComboBox();
		CB_Project.setBounds(194, 251, 212, 24);
		desktopPane.add(CB_Project);
		
		TF_NilaiKontrak = new JTextField();
		TF_NilaiKontrak.setBounds(194, 319, 158, 19);
		desktopPane.add(TF_NilaiKontrak);
		TF_NilaiKontrak.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(453, 460, 117, 25);
		desktopPane.add(btnSimpan);
		
		JCalendarComboBox CL_tanggal = new JCalendarComboBox();
		CL_tanggal.setBounds(193, 287, 157, 20);
		desktopPane.add(CL_tanggal);
		
		TF_Dpp = new JTextField();
		TF_Dpp.setBounds(228, 348, 153, 19);
		desktopPane.add(TF_Dpp);
		TF_Dpp.setColumns(10);
		
		TF_Ppn = new JTextField();
		TF_Ppn.setBounds(228, 375, 153, 19);
		desktopPane.add(TF_Ppn);
		TF_Ppn.setColumns(10);
		
		TF_Pph_23 = new JTextField();
		TF_Pph_23.setBounds(228, 402, 153, 19);
		desktopPane.add(TF_Pph_23);
		TF_Pph_23.setColumns(10);
		
		TF_ResultDpp = new JTextField();
		TF_ResultDpp.setBounds(422, 348, 148, 19);
		desktopPane.add(TF_ResultDpp);
		TF_ResultDpp.setColumns(10);
		
		TF_ResultPpn = new JTextField();
		TF_ResultPpn.setBounds(422, 375, 148, 19);
		desktopPane.add(TF_ResultPpn);
		TF_ResultPpn.setColumns(10);
		
		TF_ResultPph_23 = new JTextField();
		TF_ResultPph_23.setBounds(422, 402, 148, 19);
		desktopPane.add(TF_ResultPph_23);
		TF_ResultPph_23.setColumns(10);
		
		TF_ResultSP_2D = new JTextField();
		TF_ResultSP_2D.setBounds(422, 429, 148, 19);
		desktopPane.add(TF_ResultSP_2D);
		TF_ResultSP_2D.setColumns(10);
		
		JCheckBox chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.setBounds(469, 22, 101, 23);
		desktopPane.add(chckbxPaid);
		
		JLabel lblListKontrak = new JLabel("List Kontrak");
		lblListKontrak.setBounds(46, 26, 126, 15);
		desktopPane.add(lblListKontrak);
		
		JComboBox CB_ListKontrak = new JComboBox();
		CB_ListKontrak.setBounds(194, 21, 212, 24);
		desktopPane.add(CB_ListKontrak);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(46, 460, 117, 25);
		desktopPane.add(btnEdit);
	}
}