package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterClientDAO;
import com.main.java.invoice.project.pojo.MasterClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class MasterKlienForm extends JInternalFrame
{
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Nama;
	private JTextField TF_Npwp;
	private JTextArea TA_Keterangan;
	private JTextArea TA_Alamat;
	private JTable table;
	private JTextField TF_Satker;
	MasterClientDAO dao = new MasterClientDAO();
	int row = 0;
	String data[] = new String[5];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MasterKlienForm frame = new MasterKlienForm();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	MasterKlienForm()
	{
		setTitle("Master Klien");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 120, 120, 120});
		setDefaultTable();
	}

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 428);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Nama Kementerian");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(45, 55, 133, 15);
		desktopPane.add(lblAlamat);
		
		JLabel lblNPWP = new JLabel("NPWP");
		lblNPWP.setBounds(45, 124, 133, 15);
		desktopPane.add(lblNPWP);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(45, 185, 133, 15);
		desktopPane.add(lblKeterangan);
		
		TF_Nama = new JTextField();
		TF_Nama.setBounds(196, 26, 254, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);
		
		TF_Npwp = new JTextField();
		TF_Npwp.setBounds(197, 122, 254, 19);
		desktopPane.add(TF_Npwp);
		TF_Npwp.setColumns(10);
		
		TA_Keterangan = new JTextArea();
		TA_Keterangan.setLineWrap(true);
		TA_Keterangan.setBounds(196, 184, 284, 53);
		TA_Keterangan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		desktopPane.add(TA_Keterangan);
		
		TA_Alamat = new JTextArea();
		TA_Alamat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		TA_Alamat.setBounds(197, 57, 284, 53);
		desktopPane.add(TA_Alamat);
		
		TF_Satker = new JTextField();
		TF_Satker.setBounds(196, 153, 254, 19);
		desktopPane.add(TF_Satker);
		TF_Satker.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Satker PPK");
		lblNewLabel.setBounds(45, 155, 133, 15);
		desktopPane.add(lblNewLabel);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MasterClient masterClient = new MasterClient();
				masterClient.setName(TF_Nama.getText());
				masterClient.setAddress(TA_Alamat.getText());
				masterClient.setNoNpwp(TF_Npwp.getText());
				masterClient.setSatkerPpk(TF_Satker.getText());
				masterClient.setInformation(TA_Keterangan.getText());

				try
				{
					dao.DeleteMasterClientById(masterClient);
					tabelModel.removeRow(row);
					clearKlien();
					TF_Nama.setEnabled(true);
				}
				catch (Exception e3)
				{
					e3.printStackTrace();
				}
			}
		});
		btnHapus.setBounds(462, 351, 117, 25);
		desktopPane.add(btnHapus);
		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!TF_Satker.getText().matches("^[0-9]+$"))
				{
					JOptionPane.showMessageDialog(null, "Satker PKK hanya bisa disi dengan angka", "Peringatan",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					MasterClient masterClient = new MasterClient();
					masterClient.setName(TF_Nama.getText());
					masterClient.setAddress(TA_Alamat.getText());
					masterClient.setNoNpwp(TF_Npwp.getText());
					masterClient.setSatkerPpk(TF_Satker.getText());
					masterClient.setInformation(TA_Keterangan.getText());

					if (!btnHapus.isEnabled())
					{
						try
						{
							dao.addUpdate(masterClient, 0);
							data[0] = TF_Nama.getText();
							data[1] = TA_Alamat.getText();
							data[2] = TF_Npwp.getText();
							data[3] = TF_Satker.getText();
							data[4] = TF_Satker.getText();
							tabelModel.insertRow(0, data);
							clearKlien();
							TF_Nama.setEnabled(true);
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
							dao.addUpdate(masterClient, 1);
							data[0] = TF_Nama.getText();
							data[1] = TA_Alamat.getText();
							data[2] = TF_Npwp.getText();
							data[3] = TF_Satker.getText();
							data[4] = TF_Satker.getText();
							tabelModel.removeRow(row);
							tabelModel.insertRow(row, data);
							clearKlien();
							TF_Nama.setEnabled(true);
						}
						catch (Exception e2)
						{
							e2.printStackTrace();
						}
					}
				}
			}
		});
		btnSimpan.setBounds(336, 351, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					showKlien();
					btnHapus.setEnabled(true);
					TF_Nama.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 249, 534, 90);
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
				new String [] {"Nama Kementerian", "Alamat", "NPWP", "Satker PKK", "Keterangan"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false,false
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
			List<MasterClient> masterClientList = dao.GetAllMasterClient();

			if (masterClientList.size() > 0)
			{
				for (MasterClient aMasterClientList : masterClientList)
				{
					data[0] = aMasterClientList.getName();
					data[1] = aMasterClientList.getAddress();
					data[2] = aMasterClientList.getNoNpwp();
					data[3] = aMasterClientList.getSatkerPpk();
					data[4] = aMasterClientList.getInformation();

					tabelModel.addRow(data);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void showKlien()
	{
		row = table.getSelectedRow();
		TF_Nama.setText(tabelModel.getValueAt(row, 0).toString());
		TA_Alamat.setText(tabelModel.getValueAt(row, 1).toString());
		TF_Npwp.setText(tabelModel.getValueAt(row, 2).toString());
		TF_Satker.setText(tabelModel.getValueAt(row, 3).toString());
		TA_Keterangan.setText(tabelModel.getValueAt(row, 4).toString());
	}

	private void clearKlien()
	{
		TF_Nama.setText("");
		TA_Alamat.setText("");
		TF_Npwp.setText("");
		TF_Satker.setText("");
		TA_Keterangan.setText("");
	}
}