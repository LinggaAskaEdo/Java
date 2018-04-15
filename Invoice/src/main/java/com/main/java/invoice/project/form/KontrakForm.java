package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.KontrakDAO;
import com.main.java.invoice.project.dao.MasterClientDAO;
import com.main.java.invoice.project.dao.MasterLegalitasDAO;
import com.main.java.invoice.project.function.GeneralFunction;
import com.main.java.invoice.project.pojo.Kontrak;
import com.main.java.invoice.project.pojo.MasterClient;
import com.main.java.invoice.project.pojo.MasterPerusahaan;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class KontrakForm extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_NamaPerusahaan;
	private JTextField codeId;
	private JTextField listId;
	private JTextField projectId;

	private JTextArea TA_AlamatPerusahaan;
	private JTextField TF_Npwp;
	private JTextField TF_NoKontrak;
	private JFormattedTextField TF_NilaiKontrak;
	private JFormattedTextField TF_Dpp;
	private JFormattedTextField TF_Ppn;
	private JFormattedTextField TF_Pph_23;
	private JFormattedTextField TF_ResultDpp;
	private JFormattedTextField TF_ResultPpn;
	private JFormattedTextField TF_ResultPph_23;
	private JFormattedTextField TF_ResultSP_2D;
	private JComboBox CB_KodePerusahaan;
	private JComboBox CB_ListKontrak;
	private JComboBox CB_Project;
	private NumberFormat numformat = NumberFormat.getInstance();
	private NumberFormatter numformatter;
	private JCheckBox checkBoxEdit;
	private JDateChooser CL_tanggal;
	KontrakDAO dao = new KontrakDAO();
	private MasterLegalitasDAO masterLegalitasDAO = new MasterLegalitasDAO();
	private MasterClientDAO masterClientDAO = new MasterClientDAO();
	private String pattern = "-?\\d+";

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
		ShowComboBoxPerusahaan();
		ShowComboBoxClient();
	}

	private void initializeForm()
	{
		setCurrencyNow();

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

		JLabel label = new JLabel("100/110");
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

		checkBoxEdit = new JCheckBox("Edit Kontrak");
		checkBoxEdit.setBounds(469, 55, 117, 23);
		checkBoxEdit.setSelected(false);
		desktopPane.add(checkBoxEdit);

		/*if (checkBoxEdit.isSelected()) {
			CB_ListKontrak.setEnabled(true);
			TF_NoKontrak.setEnabled(false);
			TF_NoKontrak.setText("");
			ShowComboBoxKontrak();
		} else {
			//CB_ListKontrak.setEnabled(false);
			//TF_NoKontrak.setText("");
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
		}*/

		CB_KodePerusahaan = new JComboBox();
		CB_KodePerusahaan.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				MasterPerusahaan masterPerusahaan = new MasterPerusahaan();

				try
				{
					masterPerusahaan.setCode(String.valueOf(CB_KodePerusahaan.getSelectedItem()));
					masterPerusahaan = masterLegalitasDAO.GetMasterPerusahaanById(masterPerusahaan);

					codeId.setText(String.valueOf(masterPerusahaan.getMasterPerusahaanId()));
					TF_NamaPerusahaan.setText(masterPerusahaan.getName());
					TA_AlamatPerusahaan.setText(masterPerusahaan.getAddress());
					TF_Npwp.setText(masterPerusahaan.getNoNpwp());
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		CB_KodePerusahaan.setBounds(194, 88, 212, 24);
		desktopPane.add(CB_KodePerusahaan);

		codeId = new JTextField();
		codeId.setBounds(418, 91, 44, 19);
		desktopPane.add(codeId);
		codeId.setColumns(10);
		codeId.setVisible(false);

		TF_NamaPerusahaan = new JTextField();
		TF_NamaPerusahaan.setBounds(194, 124, 212, 19);
		desktopPane.add(TF_NamaPerusahaan);
		TF_NamaPerusahaan.setColumns(10);

		TA_AlamatPerusahaan = new JTextArea();
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

		CB_Project = new JComboBox();
		CB_Project.setBounds(194, 251, 212, 24);
		desktopPane.add(CB_Project);

		projectId = new JTextField();
		projectId.setBounds(418, 254, 44, 19);
		desktopPane.add(projectId);
		projectId.setColumns(10);
		projectId.setVisible(false);

		TF_NilaiKontrak = new JFormattedTextField(numformatter);
		TF_NilaiKontrak.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				Long a = Long.valueOf(TF_NilaiKontrak.getText().replace(",",""));
				TF_Dpp.setValue(a);
				//	TF_Dpp.setText(TF_NilaiKontrak.getText());
			}
		});
		TF_NilaiKontrak.setBounds(194, 319, 158, 19);
		desktopPane.add(TF_NilaiKontrak);
		TF_NilaiKontrak.setColumns(19);

		setTypingTextField();

		CL_tanggal = new JDateChooser();
		CL_tanggal.setBounds(193, 287, 157, 20);
		CL_tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_tanggal);

		TF_Dpp = new JFormattedTextField(numformatter);
		TF_Dpp.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent arg0)
			{
				String getDpp = TF_Dpp.getText().replace(",","");

				if (getDpp.matches(pattern))
				{

					Long grdpp = Long.parseLong(getDpp);
					Long redpp = (grdpp * 110)/100;

					//TF_ResultDpp.setText(String.valueOf(redpp));
					//TF_Ppn.setText(String.valueOf(redpp));
					//TF_Pph_23.setText(String.valueOf(redpp));
					TF_ResultDpp.setValue(redpp);
					TF_Ppn.setValue(redpp);
					TF_Pph_23.setValue(redpp);
				}
			}
		});
		TF_Dpp.setBounds(228, 348, 153, 19);
		desktopPane.add(TF_Dpp);
		TF_Dpp.setColumns(19);

		TF_ResultDpp = new JFormattedTextField(numformatter);
		TF_ResultDpp.setBounds(422, 348, 148, 19);
		desktopPane.add(TF_ResultDpp);
		TF_ResultDpp.setColumns(19);

		TF_Ppn = new JFormattedTextField(numformatter);
		TF_Ppn.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent arg0)
			{
				String getPpn = TF_Ppn.getText().replace(",","");

				if (getPpn.matches(pattern))
				{
					Long grPpn = Long.parseLong(getPpn);
					Long rePpn = (grPpn * 10)/100;
					/*TF_ResultPpn.setText(String.valueOf(rePpn));*/
					TF_ResultPpn.setValue(rePpn);
				}
			}
		});
		TF_Ppn.setBounds(228, 375, 153, 19);
		desktopPane.add(TF_Ppn);
		TF_Ppn.setColumns(19);

		TF_Pph_23 = new JFormattedTextField(numformatter);
		TF_Pph_23.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent arg0)
			{
				String getPph23 = TF_Pph_23.getText().replace(",","");

				if (getPph23.matches(pattern))
				{
					Long grPph23 = Long.parseLong(getPph23);
					Long rePph23 = (grPph23 * 2)/100;
					/*TF_ResultPph_23.setText(String.valueOf(rePph23));*/
					TF_ResultPph_23.setValue(rePph23);

					String getResultDpp = TF_ResultDpp.getText().replace(",","");
					Long dpp = Long.parseLong(getResultDpp);

					Long resultSp2D = dpp - rePph23;
					/*TF_ResultSP_2D.setText(String.valueOf(resultSp2D));*/
					TF_ResultSP_2D.setValue(resultSp2D);
				}
			}
		});
		TF_Pph_23.setBounds(228, 402, 153, 19);
		desktopPane.add(TF_Pph_23);
		TF_Pph_23.setColumns(19);

		TF_ResultPpn = new JFormattedTextField(numformatter);
		TF_ResultPpn.setBounds(422, 375, 148, 19);
		desktopPane.add(TF_ResultPpn);
		TF_ResultPpn.setColumns(19);

		TF_ResultPph_23 = new JFormattedTextField(numformatter);
		TF_ResultPph_23.setBounds(422, 402, 148, 19);
		desktopPane.add(TF_ResultPph_23);
		TF_ResultPph_23.setColumns(19);

		TF_ResultSP_2D = new JFormattedTextField(numformatter);
		TF_ResultSP_2D.setBounds(422, 429, 148, 19);
		desktopPane.add(TF_ResultSP_2D);
		TF_ResultSP_2D.setColumns(19);

		JCheckBox chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.setBounds(469, 22, 101, 23);
		desktopPane.add(chckbxPaid);

		JLabel lblListKontrak = new JLabel("List Kontrak");
		lblListKontrak.setBounds(46, 26, 126, 15);
		desktopPane.add(lblListKontrak);

		ActionListener actionListener = new ActionHandler();
		checkBoxEdit.addActionListener(actionListener);

		CB_ListKontrak = new JComboBox();
		CB_ListKontrak.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				Kontrak kontrak = new Kontrak();

				try
				{
					kontrak.setNoKontrak(String.valueOf(CB_ListKontrak.getSelectedItem()));
					kontrak = dao.GetKontrakById(kontrak);

					listId.setText(String.valueOf(kontrak.getKontrakId()));

					MasterPerusahaan perusahaan = masterLegalitasDAO.GetMasterPerusahaanById(kontrak.getMasterPerusahaanId());
					CB_KodePerusahaan.setSelectedItem(perusahaan.getCode());

					CB_Project.setActionCommand(kontrak.getProject());
					CL_tanggal.setDate(kontrak.getDate());

					if (kontrak.getNilaiKontrak() != null) {
						String value = kontrak.getNilaiKontrak().toString();
						int dotIndex = value.lastIndexOf(".");
						String name = value.substring(0, dotIndex);
						Long a = Long.valueOf(name);
						TF_NilaiKontrak.setValue(a);
					} else {
						TF_NilaiKontrak.setValue(0);
					}

				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		CB_ListKontrak.setBounds(194, 21, 212, 24);
		desktopPane.add(CB_ListKontrak);
		CB_ListKontrak.setEnabled(false);

		listId = new JTextField();
		listId.setBounds(418, 24, 44, 19);
		desktopPane.add(listId);
		listId.setColumns(10);
		listId.setVisible(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Kontrak kontrak = new Kontrak();

				if (!listId.getText().isEmpty() || !listId.getText().equalsIgnoreCase(""))
				{
					kontrak.setListKontrakId(Integer.valueOf(listId.getText()));
				}
				else
				{
					kontrak.setListKontrakId(null);
				}
				kontrak.setMasterPerusahaanId(Integer.valueOf(codeId.getText()));
				kontrak.setProject(String.valueOf(CB_Project.getSelectedItem()));
				kontrak.setDate(CL_tanggal.getDate());

				if (TF_NilaiKontrak.getText().equals("")){
					kontrak.setNilaiKontrak(BigDecimal.valueOf(0));
				} else {
					kontrak.setNilaiKontrak(new BigDecimal(TF_NilaiKontrak.getText().replace(",","")));
					kontrak.setDpp(new BigDecimal(TF_Dpp.getText().replace(",","")));
					kontrak.setPpn(new BigDecimal(TF_ResultDpp.getText().replace(",","")));
					kontrak.setPph23(new BigDecimal(TF_ResultPph_23.getText().replace(",","")));
					kontrak.setSp2d(new BigDecimal(TF_ResultSP_2D.getText().replace(",","")));
				}

				if (chckbxPaid.isSelected())
				{
					kontrak.setPaid(1);
				}
				else
				{
					kontrak.setPaid(0);
				}

				if (TF_NoKontrak.isEnabled())
				{
					kontrak.setNoKontrak(TF_NoKontrak.getText());
					try
					{
						if (kontrak.getNoKontrak().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Simpan Gagal, No kontrak kosong", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getMasterPerusahaanId().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Simpan Gagal, Kode Perusahaan tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getProject().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Simpan Gagal, Project tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getNilaiKontrak().compareTo(BigDecimal.ZERO) == 0)
						{
							JOptionPane.showMessageDialog(null, "Simpan Gagal, Nilai kontrak kosong", "", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							System.out.println(kontrak);
							dao.addUpdate(kontrak, 0);
							clearkontrak();
							TF_NoKontrak.setEnabled(true);
							JOptionPane.showMessageDialog(null, "Simpan Berhasil", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					catch (Exception e2)
					{
						e2.printStackTrace();
					}
				}
				else
				{
					try
					{
						kontrak.setNoKontrak(CB_ListKontrak.getSelectedItem().toString());
						if (kontrak.getNoKontrak().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Update Gagal, No kontrak kosong", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getMasterPerusahaanId().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Update Gagal, Kode Perusahaan tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getProject().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Update Gagal, Project tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
						}
						else if (kontrak.getNilaiKontrak().compareTo(BigDecimal.ZERO) == 0)
						{
							JOptionPane.showMessageDialog(null, "Update Gagal, Nilai kontrak kosong", "", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							dao.addUpdate(kontrak, 1);
							clearkontrak();
							TF_NoKontrak.setEnabled(true);

							JOptionPane.showMessageDialog(null, "Update Berhasil", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					catch (Exception e3)
					{
						e3.printStackTrace();
					}
				}
			}
		});
		btnSimpan.setBounds(453, 460, 117, 25);
		desktopPane.add(btnSimpan);
	}

	private void clearkontrak()
	{
		listId.setText("");
		TF_NoKontrak.setText("");
		codeId.setText("");
		TF_NamaPerusahaan.setText("");
		TA_AlamatPerusahaan.setText("");
		TF_Npwp.setText("");
		TF_NilaiKontrak.setValue(0);
		TF_Dpp.setValue(0);
		TF_Ppn.setValue(0);
		TF_Pph_23.setValue(0);
		TF_ResultDpp.setValue(0);
		TF_ResultPpn.setValue(0);
		TF_ResultPph_23.setValue(0);
		TF_ResultSP_2D.setValue(0);
	}

	private void ShowComboBoxPerusahaan()
	{
		try
		{
			List<MasterPerusahaan> allMasterPerusahaan = masterLegalitasDAO.GetAllMasterPerusahaanComboBox();

			if (allMasterPerusahaan.size() > 0)
			{
				for (MasterPerusahaan anAllMasterPerusahaan : allMasterPerusahaan)
				{
					CB_KodePerusahaan.addItem(anAllMasterPerusahaan.getCode());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void ShowComboBoxKontrak()
	{
		try
		{
			List<Kontrak> allKontrak = dao.GetAllKontrakComboBox();

			if (allKontrak.size() > 0)
			{
				for (Kontrak anAllKontrak : allKontrak)
				{
					CB_ListKontrak.addItem(anAllKontrak.getNoKontrak());
				}
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

	private void ShowComboBoxClient()
	{
		try
		{
			List<MasterClient> allMasterClient = masterClientDAO.GetAllMasterClientComboBox();

			if (allMasterClient.size() > 0)
			{
				for (MasterClient anAllMasterClient : allMasterClient)
				{
					CB_Project.addItem(anAllMasterClient.getName());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setCurrencyNow()
	{
		//  set banyaknya angka akhir bilangan
		numformat.setMaximumFractionDigits(0);

		//  Deklarasikan NumberFormatter
		numformatter = new NumberFormatter(numformat);
		numformatter.setAllowsInvalid(false);
	}

	private void setTypingTextField()
	{
		//  Key Listener
		TF_NilaiKontrak.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke)
			{
				//  Jika terjadi penekanan tombol BACK_SPACE
				if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					String text = TF_NilaiKontrak.getText();
					if(text.length() == 1)
					{
						TF_NilaiKontrak.setValue(0);
						TF_Dpp.setValue(0);
						TF_Ppn.setValue(0);
						TF_Pph_23.setValue(0);
						TF_ResultDpp.setValue(0);
						TF_ResultPpn.setValue(0);
						TF_ResultPph_23.setValue(0);
						TF_ResultSP_2D.setValue(0);
					}
				}
			}
		});
	}

	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			checkBoxEdit = (JCheckBox) event.getSource();
			if (checkBoxEdit.isSelected()) {
				CB_ListKontrak.setEnabled(true);
				TF_NoKontrak.setEnabled(false);
				TF_NoKontrak.setText("");
				if (CB_ListKontrak.getSelectedItem() == null)
				{
					ShowComboBoxKontrak();
				}
			} else {
				CB_ListKontrak.setEnabled(false);
				CL_tanggal.cleanup();
				TF_NilaiKontrak.setValue(0);
				TF_NoKontrak.setEnabled(true);
				GeneralFunction generalFunction = new GeneralFunction();
				String newCode = generalFunction.generateCodeName();
				System.out.println("newCode: " + newCode);
				TF_NoKontrak.setText(newCode);
			}
		}
	}
}