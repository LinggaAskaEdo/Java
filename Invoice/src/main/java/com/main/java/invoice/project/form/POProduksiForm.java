package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.DetailProduksiDAO;
import com.main.java.invoice.project.dao.KontrakDAO;
import com.main.java.invoice.project.dao.PoProduksiDAO;
import com.main.java.invoice.project.pojo.DetailProduksi;
import com.main.java.invoice.project.pojo.Kontrak;
import com.main.java.invoice.project.pojo.PoProduksi;
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

public class POProduksiForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Produksi;
	private JTable table;
	private JTextField TF_PONomor;
	private JTextField TF_ReffKontrak;
	private JTextField TF_NilaiProduksi;
	private JTextField TF_Unggah;
	private JTextArea TA_Keterangan;
	PoProduksiDAO dao = new PoProduksiDAO();
	private DetailProduksiDAO detailProduksiDAO = new DetailProduksiDAO();
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
					POProduksiForm frame = new POProduksiForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	POProduksiForm()
	{
		setTitle("PO. Produksi");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120});
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 487);
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("PO. Nomor");
		lblPoNomor.setBounds(45, 28, 183, 15);
		desktopPane.add(lblPoNomor);
		
		JLabel lblProduksi = new JLabel("Produksi");
		lblProduksi.setBounds(45, 88, 133, 15);
		desktopPane.add(lblProduksi);
		
		JLabel lblTanggalTayang = new JLabel("Tanggal");
		lblTanggalTayang.setBounds(45, 119, 133, 15);
		desktopPane.add(lblTanggalTayang);
		
		JLabel lblValue = new JLabel("Biaya Produksi");
		lblValue.setBounds(45, 151, 133, 15);
		desktopPane.add(lblValue);
		
		TF_Produksi = new JTextField();
		TF_Produksi.setBounds(197, 86, 254, 19);
		desktopPane.add(TF_Produksi);
		TF_Produksi.setColumns(10);

		JDateChooser CL_Tanggal = new JDateChooser();
		CL_Tanggal.setBounds(197, 117, 157, 20);
		CL_Tanggal.setDateFormatString("yyyy-MM-dd");
		desktopPane.add(CL_Tanggal);
		
		TF_PONomor = new JTextField();
		TF_PONomor.setBounds(197, 26, 254, 19);
		desktopPane.add(TF_PONomor);
		TF_PONomor.setColumns(10);
		
		JLabel lblReffKontrak = new JLabel("Reff Kontrak");
		lblReffKontrak.setBounds(45, 57, 110, 15);
		desktopPane.add(lblReffKontrak);
		
		TF_ReffKontrak = new JTextField();
		TF_ReffKontrak.setBounds(197, 55, 254, 19);
		desktopPane.add(TF_ReffKontrak);
		TF_ReffKontrak.setColumns(10);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				DetailProduksiForm produksi = new DetailProduksiForm();
				getParent().add(produksi);
				produksi.setVisible(true);
			}
		});
		btnPlus.setBounds(547, 178, 44, 25);
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
		btnMinus.setBounds(547, 209, 44, 25);
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
		scrollPane.setBounds(45, 178, 490, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
		
		JLabel lblNilaiProduksi = new JLabel("Nilai Produksi");
		lblNilaiProduksi.setBounds(45, 280, 118, 15);
		desktopPane.add(lblNilaiProduksi);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 312, 107, 15);
		desktopPane.add(lblKeterangan);
		
		TF_NilaiProduksi = new JTextField();
		TF_NilaiProduksi.setBounds(197, 280, 254, 19);
		desktopPane.add(TF_NilaiProduksi);
		TF_NilaiProduksi.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Keterangan.setBounds(197, 311, 284, 53);
		desktopPane.add(TA_Keterangan);
		
		TF_Unggah = new JTextField();
		TF_Unggah.setColumns(10);
		TF_Unggah.setBounds(197, 379, 198, 19);
		desktopPane.add(TF_Unggah);
		
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
		btnUnggah.setBounds(397, 376, 110, 25);
		desktopPane.add(btnUnggah);
		
		JLabel lblUnggahDokumen = new JLabel("Unggah Dokumen");
		lblUnggahDokumen.setBounds(45, 381, 133, 15);
		desktopPane.add(lblUnggahDokumen);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PoProduksi poProduksi = new PoProduksi();
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

				poProduksi.setPoProduksiNo(TF_PONomor.getText());
				poProduksi.setKontrakId(kontrak.getKontrakId());
				poProduksi.setProduksi(TF_Produksi.getText());
				poProduksi.setTanggal(CL_Tanggal.getDate());
				poProduksi.setNilaiProduksi(new BigDecimal(TF_NilaiProduksi.getText()));
				poProduksi.setKeterangan(TA_Keterangan.getText());
				poProduksi.setImage(TF_Unggah.getText());

				try
				{
					dao.add(poProduksi);
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
				GetTableList();
				RemoveRowPoProduksi();
				ClearPoProduksi();
			}
		});
		btnSimpan.setBounds(418, 413, 117, 25);
		desktopPane.add(btnSimpan);
	}

	static DefaultTableModel tabelModel = getDefaultTabelModel();

	private void Tabel(JTable tb, int lebar[]){
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
				new Object [][] {
						{null, null, null, null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null, null, null, null}
				},
				new String [] {"Media", "Durasi", "Hari", "Lokasi", "Pre-Uraian", "Pre-Jenis", "Pro-Jenis", "Pro-Jumlah", "Pro-Barang", "Pro-Harga Satuan", "Pro-Total Harga", "Post-Barang", "Post-Total Harga"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false,false,false,false,false,false,false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private void GetTableList()
	{
		DetailProduksi detailProduksi = new DetailProduksi();

		for (int i = 0; i < tabelModel.getRowCount(); i++)
		{
			detailProduksi.setPoProduksiNo(TF_PONomor.getText());
			detailProduksi.setMedia(String.valueOf(tabelModel.getValueAt(i, 0)));
			detailProduksi.setDurasi(String.valueOf(tabelModel.getValueAt(i,1)));
			detailProduksi.setHari(String.valueOf(tabelModel.getValueAt(i,2)));
			detailProduksi.setLokasi(String.valueOf(tabelModel.getValueAt(i,3)));
			detailProduksi.setPreProduksiUraian(String.valueOf(tabelModel.getValueAt(i, 4)));
			detailProduksi.setPreProduksiJenis(String.valueOf(tabelModel.getValueAt(i,5)));
			detailProduksi.setProduksiJenis(String.valueOf(tabelModel.getValueAt(i,6)));
			detailProduksi.setProduksiJumlah(String.valueOf(tabelModel.getValueAt(i, 7)));
			detailProduksi.setProduksiBarang(String.valueOf(tabelModel.getValueAt(i,8)));
			detailProduksi.setProduksiHargaSatuan((BigDecimal) tabelModel.getValueAt(i, 9));
			detailProduksi.setProduksiTotalHarga((BigDecimal) table.getValueAt(i, 10));
			detailProduksi.setPostProduksiBarang(String.valueOf(tabelModel.getValueAt(i, 11)));
			detailProduksi.setPostProduksiTotalHarga((BigDecimal) tabelModel.getValueAt(i, 12));

			try
			{
				detailProduksiDAO.add(detailProduksi);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	private void RemoveRowPoProduksi()
	{
		for (int i = 0; i < tabelModel.getRowCount(); i++)
		{
			tabelModel.removeRow(i);
		}
	}

	private void ClearPoProduksi()
	{
		TF_PONomor.setText("");
		TF_ReffKontrak.setText("");
		TF_Produksi.setText("");
		TF_NilaiProduksi.setText("");
		TA_Keterangan.setText("");
		TF_Unggah.setText("");
	}
}