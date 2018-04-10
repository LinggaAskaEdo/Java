package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.*;
import com.main.java.invoice.project.pojo.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class POMediaForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private JTextField TF_PONomor;
	private JTextField TF_Klien;
	private JTextField TF_volume;
	private JTextField TF_Harga;
	private JTextField TF_Ppn;
	private JTextField TF_unggah;
	private JTextField TF_ReffKontrak;
	private JTextArea TA_Keterangan;
	private JComboBox CB_NamaMedia;
	private JTextField cbId;
	private PoMediaDAO poMediaDAO = new PoMediaDAO();
	private MasterDanaDAO masterDanaDAO = new MasterDanaDAO();
	private MasterMediaDAO masterMediaDAO = new MasterMediaDAO();
	private TagihanMediaDAO tagihanMediaDAO = new TagihanMediaDAO();
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
					POMediaForm frame = new POMediaForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	POMediaForm()
	{
		setTitle("PO. Media");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120});
		ShowComboBoxPoMedia();
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 551);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 176, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblPekerjaanKementerian = new JLabel("Klien");
		lblPekerjaanKementerian.setBounds(45, 90, 176, 15);
		desktopPane.add(lblPekerjaanKementerian);
		
		JLabel lblNamaMedia = new JLabel("Nama Media");
		lblNamaMedia.setBounds(45, 124, 176, 15);
		desktopPane.add(lblNamaMedia);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal Tayang");
		lblTanggalTayang.setBounds(45, 155, 176, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblUkuran = new JLabel("Volume");
		lblUkuran.setBounds(45, 188, 176, 15);
		desktopPane.add(lblUkuran);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(45, 219, 70, 15);
		desktopPane.add(lblHarga);
		
		JLabel lblPpn = new JLabel("PPN");
		lblPpn.setBounds(45, 250, 70, 15);
		desktopPane.add(lblPpn);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 280, 176, 15);
		desktopPane.add(lblKeterangan);
		
		JLabel lblBuktiPotongPph = new JLabel("Unggah Dokumen");
		lblBuktiPotongPph.setBounds(45, 346, 176, 15);
		desktopPane.add(lblBuktiPotongPph);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(239, 26, 239, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		TF_Klien = new JTextField();
		TF_Klien.setBounds(239, 88, 239, 19);
		desktopPane.add(TF_Klien);
		TF_Klien.setColumns(10);

		CB_NamaMedia = new JComboBox();
		CB_NamaMedia.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				MasterMedia masterMedia = new MasterMedia();
				masterMedia.setCompanyName(String.valueOf(CB_NamaMedia.getSelectedItem()));

				try
				{
					masterMedia = masterMediaDAO.GetMasterMediaById(masterMedia);
					cbId.setText(String.valueOf(masterMedia.getMasterMediaId()));
				}
				catch (Exception e4)
				{
					e4.printStackTrace();
				}
			}
		});
		CB_NamaMedia.setBounds(239, 119, 206, 24);
		desktopPane.add(CB_NamaMedia);

		cbId = new JTextField();
		cbId.setBounds(457, 122, 42, 19);
		desktopPane.add(cbId);
		cbId.setColumns(10);
		cbId.setVisible(false);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(239, 155, 170, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_volume = new JTextField();
		TF_volume.setBounds(239, 186, 114, 19);
		desktopPane.add(TF_volume);
		TF_volume.setColumns(10);
		
		TF_Harga = new JTextField();
		TF_Harga.setBounds(239, 217, 114, 19);
		desktopPane.add(TF_Harga);
		TF_Harga.setColumns(10);
		
		TF_Ppn = new JTextField();
		TF_Ppn.setBounds(239, 248, 114, 19);
		desktopPane.add(TF_Ppn);
		TF_Ppn.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(239, 279, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_unggah = new JTextField();
		TF_unggah.setBounds(239, 344, 170, 19);
		desktopPane.add(TF_unggah);
		TF_unggah.setColumns(10);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 59, 176, 15);
		desktopPane.add(lblReffKontrak);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(239, 57, 239, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				TagihanMediaForm media = new TagihanMediaForm();
				getParent().add(media);
				media.setVisible(true);
			}
		});
		btnPlus.setBounds(552, 373, 49, 25);
		desktopPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabelModel.removeRow(row);
				btnMinus.setEnabled(false);
			}
		});
		btnMinus.setBounds(552, 406, 49, 25);
		desktopPane.add(btnMinus);

		btnMinus.setEnabled(false);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					btnMinus.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 373, 495, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JButton btn_Unggah = new JButton("Browse");
		btn_Unggah.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser jfc = new JFileChooser();

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = jfc.getSelectedFile();
					TF_unggah.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btn_Unggah.setBounds(410, 341, 96, 25);
		desktopPane.add(btn_Unggah);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PoMedia poMedia = new PoMedia();
				Kontrak kontrak = new Kontrak();

				try
				{
					kontrak.setNoKontrak(TF_ReffKontrak.getText());
					kontrak = kontrakDAO.GetKontrakById(kontrak);
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}

				poMedia.setPoMediaNo(TF_PONomor.getText());
				poMedia.setKontrakId(kontrak.getKontrakId());
				poMedia.setPekerjaanKementerian(TF_Klien.getText());
				poMedia.setMasterMediaId(Integer.valueOf(cbId.getText()));
				poMedia.setTanggalTayang(CL_Tanggal.getDate());
				poMedia.setUkuran(TF_volume.getText());
				poMedia.setHarga(new BigDecimal(TF_Harga.getText()));
				poMedia.setPpn(new BigDecimal(TF_Ppn.getText()));
				poMedia.setKeterangan(TA_Keterangan.getText());
				poMedia.setImage(TF_unggah.getText());

				try
				{
					poMediaDAO.add(poMedia);
					GetTableList();
					RemoveRowPoMedia();
					ClearPoMedia();
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
			}
		});
		btnSimpan.setBounds(423, 475, 117, 25);
		desktopPane.add(btnSimpan);
	}

	static DefaultTableModel tabelModel = getDefaultTabelModel();

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

	private static DefaultTableModel getDefaultTabelModel()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Invoice Media", "Tanggal", "Nilai Tagihan", "Reff Sumber Dana", "File"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private void GetTableList()
	{
		TagihanMedia tagihanMedia = new TagihanMedia();

		for (int i = 0; i < tabelModel.getRowCount(); i++)
		{
			try
			{
				tagihanMedia.setPoMediaNo(TF_PONomor.getText());
				tagihanMedia.setInvoiceMedia(String.valueOf(tabelModel.getValueAt(i,0)));
				tagihanMedia.setTanggal((Date) tabelModel.getValueAt(i,1));
				tagihanMedia.setNilaiTagihan((BigDecimal) tabelModel.getValueAt(i, 2));

				String splitData = String.valueOf(tabelModel.getValueAt(i,3));
				MasterDana masterDana = masterDanaDAO.GetMasterDanaById(splitData);
				tagihanMedia.setMasterDanaId(masterDana.getMasterDanaId());

				tagihanMedia.setImage(String.valueOf(tabelModel.getValueAt(i, 4)));

				tagihanMediaDAO.add(tagihanMedia);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	private void RemoveRowPoMedia()
	{
		for (int i = 0; i < tabelModel.getRowCount(); i++)
		{
			tabelModel.removeRow(i);
		}
	}

	private void ClearPoMedia()
	{
		TF_PONomor.setText("");
		TF_ReffKontrak.setText("");
		TF_Klien.setText("");
		TF_volume.setText("");
		TF_Harga.setText("");
		TF_Ppn.setText("");
		TA_Keterangan.setText("");
		TF_unggah.setText("");
	}

	private void ShowComboBoxPoMedia()
	{
		try
		{
			List<MasterMedia> allMasterMedia = masterMediaDAO.GetAllMasterMediaComboBox();

			if (allMasterMedia.size() > 0)
			{
				for (MasterMedia anAllMasterMedia : allMasterMedia)
				{
					CB_NamaMedia.addItem(anAllMasterMedia.getMediaName());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}