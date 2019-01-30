package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.*;
import com.main.java.invoice.project.pojo.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class POEventForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTextField TF_PONomor;
	private JTextField TF_Kegiatan;
	private JTextField TF_Jumlah;
	private JTextField TF_Unggah;
	private JTextArea TA_Keterangan;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxReff;
	
	private PoEventDAO dao = new PoEventDAO();
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	private DetailEventDAO detailEventDAO = new DetailEventDAO();
	private DetailReimbursementDAO detailReimbursementDAO = new DetailReimbursementDAO();
	private TagihanReimbursementDAO tagihanReimbursementDAO = new TagihanReimbursementDAO();
	private KontrakDAO kontrakDAO = new KontrakDAO();
	int row = 0;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					POEventForm frame = new POEventForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
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
		Tabel(table3, new int[]{120, 120, 120, 120, 120, 120});
		ShowComboBoxKontrak();
	}

	@SuppressWarnings("rawtypes")
	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 693);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 164, 15);
		desktopPane.add(lblPoNomor);
		
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

		comboBoxReff = new JComboBox();
		comboBoxReff.setBounds(227, 54, 252, 24);
		desktopPane.add(comboBoxReff);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(227, 119, 158, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_Kegiatan = new JTextField();
		TF_Kegiatan.setBounds(227, 88, 252, 19);
		desktopPane.add(TF_Kegiatan);
		TF_Kegiatan.setColumns(10);
		
		JButton btnPlus_1 = new JButton("+");
		btnPlus_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				DetailEventForm detailEvent = new DetailEventForm();
				getParent().add(detailEvent);
				detailEvent.setVisible(true);
			}
		});
		btnPlus_1.setBounds(549, 173, 49, 25);
		desktopPane.add(btnPlus_1);
		
		JButton btnMinus_1 = new JButton("-");
		btnMinus_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabelModel1.removeRow(row);
				btnMinus_1.setEnabled(false);
			}
		});
		btnMinus_1.setBounds(548, 207, 49, 25);
		desktopPane.add(btnMinus_1);

		btnMinus_1.setEnabled(false);

		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					btnMinus_1.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 173, 495, 90);
		scrollPane.setViewportView(table1);
		desktopPane.add(scrollPane);
		
		JButton btnPlus_2 = new JButton("+");
		btnPlus_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				DetailReimbursementForm detailReimbursement = new DetailReimbursementForm();
				getParent().add(detailReimbursement);
				detailReimbursement.setVisible(true);
			}
		});
		btnPlus_2.setBounds(548, 302, 49, 25);
		desktopPane.add(btnPlus_2);
		
		JButton btnMinus_2 = new JButton("-");
		btnMinus_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabelModel2.removeRow(row);
				btnMinus_2.setEnabled(false);
			}
		});
		btnMinus_2.setBounds(547, 336, 49, 25);
		desktopPane.add(btnMinus_2);

		btnMinus_2.setEnabled(false);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					btnMinus_2.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(45, 302, 495, 90);
		scrollPane1.setViewportView(table2);
		desktopPane.add(scrollPane1);

		TF_Jumlah = new JTextField();
		TF_Jumlah.setBounds(227, 402, 186, 19);
		desktopPane.add(TF_Jumlah);
		TF_Jumlah.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(227, 430, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setBounds(227, 601, 158, 19);
		desktopPane.add(TF_Unggah);
		TF_Unggah.setColumns(10);

		JButton btnUnggah = new JButton("Browse");
		btnUnggah.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = jfc.getSelectedFile();
					TF_Unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnUnggah.setBounds(386, 597, 84, 25);
		desktopPane.add(btnUnggah);
		
		JButton btnPlus_3 = new JButton("+");
		btnPlus_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				TagihanReimbursementForm tagihanReimbursement = new TagihanReimbursementForm();
				getParent().add(tagihanReimbursement);
				tagihanReimbursement.setVisible(true);
			}
		});
		btnPlus_3.setBounds(549, 495, 49, 25);
		desktopPane.add(btnPlus_3);
		
		JButton btnMinus_3 = new JButton("-");
		btnMinus_3.setBounds(549, 528, 49, 25);
		btnMinus_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabelModel3.removeRow(row);
				btnMinus_3.setEnabled(false);
			}
		});
		desktopPane.add(btnMinus_3);

		btnMinus_3.setEnabled(false);

		table3 = new JTable();
		table3.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					btnMinus_3.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(45, 495, 495, 90);
		scrollPane2.setViewportView(table3);
		desktopPane.add(scrollPane2);

		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 603, 139, 15);
		desktopPane.add(lblUnggahDokumen);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PoEvent poEvent = new PoEvent();
				Kontrak kontrak = new Kontrak();

				kontrak.setNoKontrak(String.valueOf(comboBoxReff.getSelectedItem()));

				try
				{
					kontrak = kontrakDAO.GetKontrakById(kontrak);
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}

				if(!TF_Jumlah.getText().matches("^[0-9]+$"))
				{
					JOptionPane.showMessageDialog(null, "Jumlah hanya bisa disi dengan angka", "Peringatan",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (kontrak.getKontrakId() == null)
				{
					JOptionPane.showMessageDialog(null, "Simpan Gagal, Kontrak tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					poEvent.setPoEventNo(TF_PONomor.getText());
					poEvent.setKontrakId(kontrak.getKontrakId());
					poEvent.setKegiatan(TF_Kegiatan.getText());
					poEvent.setTanggal(CL_Tanggal.getDate());
					poEvent.setJumlah(new BigDecimal(TF_Jumlah.getText()));
					poEvent.setKeterangan(TA_Keterangan.getText());

					if (TF_Unggah.getText().length() > 0)
						poEvent.setImage(TF_Unggah.getText());
					else
						poEvent.setImage(null);

					try
					{
						dao.add(poEvent);
						GetTableList_1();
						RemoveRow_1();
						GetTableList_2();
						RemoveRow_2();
						GetTableList_3();
						RemoveRow_3();
						ClearPoEvent();

						JOptionPane.showMessageDialog(null, "Simpan Berhasil", "", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e3)
					{
						e3.printStackTrace();
					}
				}
			}
		});
		btnSimpan.setBounds(480, 628, 117, 25);
		desktopPane.add(btnSimpan);
	}

	static DefaultTableModel tabelModel1 = getDefaultTabelModel1();
	static DefaultTableModel tabelModel2 = getDefaultTabelModel2();
	static DefaultTableModel tabelModel3 = getDefaultTabelModel3();

	private void Tabel(JTable tb, int lebar[])
	{
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int kolom = tb.getColumnCount();

		for (int i = 0; i < kolom; i++)
		{
			TableColumn tbc= tb.getColumnModel().getColumn(i);
			tbc.setPreferredWidth(lebar[i]);
			tb.setRowHeight(18);
		}
	}

	private static DefaultTableModel getDefaultTabelModel1()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Uraian", "Detail", "Jumlah 1", "Jenis 1", "Jumlah 2", "Jenis 2","Harga Satuan", "Total"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false,false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private static DefaultTableModel getDefaultTabelModel2()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Uraian", "Detail", "Harga"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private static DefaultTableModel getDefaultTabelModel3()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Reff PO", "Catatan Reimburse", "Tanggal", "Reff Sumber Dana", "Keterangan", "File"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private void GetTableList_1()
	{
		DetailEvent detailEvent = new DetailEvent();

		for (int i = 0; i < tabelModel1.getRowCount(); i++)
		{
			detailEvent.setPoEventNo(TF_PONomor.getText());
			detailEvent.setUraian(String.valueOf(tabelModel1.getValueAt(i, 0)));
			detailEvent.setDetail(String.valueOf(tabelModel1.getValueAt(i, 1)));

			String vol1 = String.valueOf(tabelModel1.getValueAt(i, 2));
			detailEvent.setVol1(Integer.parseInt(vol1));

			detailEvent.setJenis1(String.valueOf(tabelModel1.getValueAt(i, 3)));

			String vol2 = String.valueOf(tabelModel1.getValueAt(i, 4));
			detailEvent.setVol2(Integer.parseInt(vol2));

			detailEvent.setJenis2(String.valueOf(tabelModel1.getValueAt(i, 5)));

			//detailEvent.setHargaSatuan((BigDecimal) tabelModel1.getValueAt(i, 6));
			String result = String.valueOf(tabelModel1.getValueAt(i, 6));
			String aResult = result.replace(",","");
			String results = aResult.replace(".00","");
			Long value = Long.valueOf(results);
			detailEvent.setHargaSatuan(BigDecimal.valueOf(value));

			//detailEvent.setTotal((BigDecimal) tabelModel1.getValueAt(i, 7));
			String result1 = String.valueOf(tabelModel1.getValueAt(i, 7));
			String aResult1 = result1.replace(",","");
			String result1s = aResult1.replace(".00","");
			Long value1 = Long.valueOf(result1s);
			detailEvent.setTotal(BigDecimal.valueOf(value1));

			try
			{
				detailEventDAO.add(detailEvent);
			}
			catch (Exception e3)
			{
				e3.printStackTrace();
			}
		}
	}

	private void RemoveRow_1()
	{
		for(int i = 0; i < tabelModel1.getRowCount(); i++)
		{
			tabelModel1.removeRow(i);
		}
	}

	private void GetTableList_2()
	{
		DetailReimburse detailReimburse = new DetailReimburse();

		for (int i = 0; i < tabelModel2.getRowCount(); i++)
		{
			detailReimburse.setPoEventNo(TF_PONomor.getText());
			detailReimburse.setUraian(String.valueOf(tabelModel2.getValueAt(i, 0)));
			detailReimburse.setDetail(String.valueOf(tabelModel2.getValueAt(i, 1)));

			//detailReimburse.setHarga((BigDecimal) tabelModel2.getValueAt(i, 2));
			String result = String.valueOf(tabelModel2.getValueAt(i, 2));
			String aResult = result.replace(",","");
			String results = aResult.replace(".00","");
			Long value = Long.valueOf(results);
			detailReimburse.setHarga(BigDecimal.valueOf(value));

			try
			{
				detailReimbursementDAO.add(detailReimburse);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	private void RemoveRow_2()
	{
		for(int i = 0; i < tabelModel2.getRowCount(); i++)
		{
			tabelModel2.removeRow(i);
		}
	}

	private void GetTableList_3()
	{
		TagihanReimburse tagihanReimburse = new TagihanReimburse();

		for (int i = 0; i < tabelModel3.getRowCount(); i++)
		{
			tagihanReimburse.setPoEventNo(TF_PONomor.getText());
			tagihanReimburse.setPoNomor(String.valueOf(tabelModel3.getValueAt(i, 0)));
			tagihanReimburse.setCatatan(String.valueOf(tabelModel3.getValueAt(i,1)));

			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String current = String.valueOf(tabelModel3.getValueAt(i, 2));

			try
			{
				Date newDate = simpleDateFormat.parse(current);
				tagihanReimburse.setTanggal(newDate);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}

			try
			{
				String splitData = String.valueOf(tabelModel3.getValueAt(i,3));
				MasterDana masterDana = masterDanaDAO.GetMasterDanaById(splitData);

				if (masterDana.getMasterDanaId() == null)
				{
					JOptionPane.showMessageDialog(null, "Tambah Gagal, Master Dana tidak ditemukan", "", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					tagihanReimburse.setMasterDanaId(masterDana.getMasterDanaId());

					tagihanReimburse.setKeterangan(String.valueOf(tabelModel3.getValueAt(i, 4)));

					if (tabelModel3.getValueAt(i, 5).toString().length() > 0)
						tagihanReimburse.setImage(String.valueOf(tabelModel3.getValueAt(i, 5)));
					else
						tagihanReimburse.setImage(null);
				}

				tagihanReimbursementDAO.add(tagihanReimburse);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	private void RemoveRow_3()
	{
		for (int i = 0; i < tabelModel3.getRowCount(); i++)
		{
			tabelModel3.removeRow(i);
		}
	}

	private void ClearPoEvent()
	{
		TF_PONomor.setText("");
		//TF_ReffKontrak.setText("");
		TF_Kegiatan.setText("");
		TF_Jumlah.setText("");
		TA_Keterangan.setText("");
		TF_Unggah.setText("");
	}

	@SuppressWarnings("unchecked")
	private void ShowComboBoxKontrak()
	{
		java.util.List<Kontrak> allKontrak = kontrakDAO.GetAllKontrakComboBox();

		if (allKontrak.size() > 0)
		{
			for (Kontrak anAllKontrak : allKontrak)
			{
				comboBoxReff.addItem(anAllKontrak.getNoKontrak());
			}
		}
	}
}