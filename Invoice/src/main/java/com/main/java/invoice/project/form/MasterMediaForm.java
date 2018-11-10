package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterMediaDAO;
import com.main.java.invoice.project.pojo.MasterMedia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MasterMediaForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_NamaPerusahaan;
	private JTextField TF_NamaMedia;
	private JTextArea TA_Alamat;
	private JTextField TF_Npwp;
	private JTextField TF_BillComitment;
	private JTextArea TA_Keterangan;
	private JTable table;
	MasterMediaDAO dao = new MasterMediaDAO();
	int row = 0;
	String data[] = new String[6];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MasterMediaForm frame = new MasterMediaForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
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
		setDefaultTable();
	}

	public void initializeForm()
	{
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
		
		TA_Alamat = new JTextArea();
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
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setBounds(196, 204, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MasterMedia masterMedia = new MasterMedia();
				masterMedia.setCompanyName(TF_NamaPerusahaan.getText());
				masterMedia.setMediaName(TF_NamaMedia.getText());
				masterMedia.setAddress(TA_Alamat.getText());
				masterMedia.setNoNpwp(TF_Npwp.getText());
				masterMedia.setBillCommitment(TF_BillComitment.getText());
				masterMedia.setInformation(TA_Keterangan.getText());

				try
				{
					dao.DeleteMasterMediaById(masterMedia);
					tabelModel.removeRow(row);
					clearMedia();
					TF_NamaPerusahaan.setEnabled(true);
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
			}
		});
		btnHapus.setBounds(462, 371, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!TF_BillComitment.getText().matches("^[0-9]+$"))
				{
					JOptionPane.showMessageDialog(null, "Bill Comitment hanya bisa disi dengan angka", "Peringatan",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					MasterMedia masterMedia = new MasterMedia();
					masterMedia.setCompanyName(TF_NamaPerusahaan.getText());
					masterMedia.setMediaName(TF_NamaMedia.getText());
					masterMedia.setAddress(TA_Alamat.getText());
					masterMedia.setNoNpwp(TF_Npwp.getText());
					masterMedia.setBillCommitment(TF_BillComitment.getText());
					masterMedia.setInformation(TA_Keterangan.getText());

					if (!btnHapus.isEnabled())
					{
						try
						{
							dao.addUpdate(masterMedia, 0);
							data[0] = TF_NamaPerusahaan.getText();
							data[1] = TF_NamaMedia.getText();
							data[2] = TA_Alamat.getText();
							data[3] = TF_Npwp.getText();
							data[4] = TF_BillComitment.getText();
							data[5] = TA_Keterangan.getText();
							tabelModel.insertRow(0, data);
							clearMedia();
							TF_NamaPerusahaan.setEnabled(true);
						}
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
					else
					{
						try
						{
							dao.addUpdate(masterMedia, 1);
							data[0] = TF_NamaPerusahaan.getText();
							data[1] = TF_NamaMedia.getText();
							data[2] = TA_Alamat.getText();
							data[3] = TF_Npwp.getText();
							data[4] = TF_BillComitment.getText();
							data[5] = TA_Keterangan.getText();
							tabelModel.removeRow(row);
							tabelModel.insertRow(row, data);
							clearMedia();
							TF_NamaPerusahaan.setEnabled(true);
						}
						catch (Exception e2)
						{
							e2.printStackTrace();
						}
					}
				}
			}
		});
		btnSimpan.setBounds(336, 371, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					showMedia();
					btnHapus.setEnabled(true);
					TF_NamaPerusahaan.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 269, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
	}

	private DefaultTableModel tabelModel = getDefaultTabelModel();
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

	private DefaultTableModel getDefaultTabelModel()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Nama Perusahaan", "Nama Media", "Alamat", "NPWP", "Bill Commitment", "Keterangan"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	private void setDefaultTable()
	{
		try
		{
			List<MasterMedia> masterMediaList = dao.GetAllMasterMedia();

			if (masterMediaList.size() > 0)
			{
				for (MasterMedia aMasterMediaList : masterMediaList)
				{
					data[0] = aMasterMediaList.getCompanyName();
					data[1] = aMasterMediaList.getMediaName();
					data[2] = aMasterMediaList.getAddress();
					data[3] = aMasterMediaList.getNoNpwp();
					data[4] = aMasterMediaList.getBillCommitment();
					data[5] = aMasterMediaList.getInformation();

					tabelModel.addRow(data);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showMedia()
	{
		row = table.getSelectedRow();
		TF_NamaPerusahaan.setText(tabelModel.getValueAt(row, 0).toString());
		TF_NamaMedia.setText(tabelModel.getValueAt(row, 1).toString());
		TA_Alamat.setText(tabelModel.getValueAt(row, 2).toString());
		TF_Npwp.setText(tabelModel.getValueAt(row, 3).toString());
		TF_BillComitment.setText(tabelModel.getValueAt(row, 4).toString());
		TA_Keterangan.setText(tabelModel.getValueAt(row, 5).toString());
	}

	private void clearMedia()
	{
		TF_NamaPerusahaan.setText("");
		TF_NamaMedia.setText("");
		TA_Alamat.setText("");
		TF_Npwp.setText("");
		TF_BillComitment.setText("");
		TA_Keterangan.setText("");
	}
}