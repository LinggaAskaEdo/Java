package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.MasterDanaDAO;
import com.main.java.invoice.project.pojo.MasterDana;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class MasterDanaForm extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 528257075179999134L;
	
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField TF_Nama;
	private JTextField TF_noRek;
	private JTextField TF_AtasNama;
	private JFormattedTextField TF_Tunai;
	private JTable table;
	private NumberFormat numformat = NumberFormat.getInstance();
	private NumberFormatter numformatter;
	MasterDanaDAO dao = new MasterDanaDAO();
	int row = 0;
	String data[] = new String[4];

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			try
			{
				MasterDanaForm frame = new MasterDanaForm();
				frame.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

	MasterDanaForm()
	{
		setTitle("Master Dana");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 200, 120});
		setDefaultTable();
	}

	public void initializeForm()
	{
		setCurrencyNow();

		setClosable(true);
		setBounds(100, 100, 630, 339);
		getContentPane().setLayout(null);

		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);

		JLabel lblNamaKantor = new JLabel("Nama Bank");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);

		JLabel lblNoRek = new JLabel("No. Rek");
		lblNoRek.setBounds(45, 57, 70, 15);
		desktopPane.add(lblNoRek);

		JLabel lblAtasNama = new JLabel("Atas Nama");
		lblAtasNama.setBounds(45, 88, 103, 15);
		desktopPane.add(lblAtasNama);

		JLabel lblCashtunai = new JLabel("Cash/Tunai");
		lblCashtunai.setBounds(45, 119, 103, 15);
		desktopPane.add(lblCashtunai);

		TF_Nama = new JTextField();
		TF_Nama.setBounds(163, 26, 281, 19);
		desktopPane.add(TF_Nama);
		TF_Nama.setColumns(10);

		TF_noRek = new JTextField();
		TF_noRek.setBounds(163, 55, 281, 19);
		desktopPane.add(TF_noRek);
		TF_noRek.setColumns(10);

		TF_AtasNama = new JTextField();
		TF_AtasNama.setBounds(163, 86, 281, 19);
		desktopPane.add(TF_AtasNama);
		TF_AtasNama.setColumns(10);

		TF_Tunai = new JFormattedTextField(numformatter);
		TF_Tunai.setBounds(163, 117, 281, 19);
		desktopPane.add(TF_Tunai);
		TF_Tunai.setColumns(10);

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(e ->
		{
			MasterDana masterDana = new MasterDana();
			masterDana.setNameBankAccount(TF_Nama.getText());
			masterDana.setNoBankAccount(TF_noRek.getText());
			masterDana.setNameAccount(TF_AtasNama.getText());
			masterDana.setTotalCash(new BigDecimal(TF_Tunai.getText().replace(",","")));

			try
			{
				dao.DeleteMasterDanaById(masterDana);
				tabelModel.removeRow(row);
				clearDana();
				TF_Nama.setEnabled(true);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});
		btnHapus.setBounds(462, 259, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(e ->
		{
			if(!TF_noRek.getText().matches("^[0-9]+$"))
			{
				JOptionPane.showMessageDialog(null, "No. Rekening hanya bisa disi dengan angka", "Peringatan",
						JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				MasterDana masterDana = new MasterDana();
				masterDana.setNameBankAccount(TF_Nama.getText());
				masterDana.setNoBankAccount(TF_noRek.getText());
				masterDana.setNameAccount(TF_AtasNama.getText());
				masterDana.setTotalCash(new BigDecimal(TF_Tunai.getText().replace(",","")));

				if (!btnHapus.isEnabled())
				{
					try
					{
						dao.addUpdate(masterDana, 0);
						data[0] = TF_Nama.getText();
						data[1] = TF_noRek.getText();
						data[2] = TF_AtasNama.getText();
						data[3] = TF_Tunai.getText().replace(",","");
						tabelModel.insertRow(0, data);
						clearDana();
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
						dao.addUpdate(masterDana, 1);
						data[0] = TF_Nama.getText();
						data[1] = TF_noRek.getText();
						data[2] = TF_AtasNama.getText();
						data[3] = TF_Tunai.getText().replace(",","");
						tabelModel.removeRow(row);
						tabelModel.insertRow(row, data);
						clearDana();
						TF_Nama.setEnabled(true);
					}
					catch (Exception e2)
					{
						e2.printStackTrace();
					}
				}
			}
		});
		btnSimpan.setBounds(336, 259, 117, 25);
		desktopPane.add(btnSimpan);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					showDana();
					btnHapus.setEnabled(true);
					TF_Nama.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 157, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);
	}

	private DefaultTableModel tabelModel = getDefaultTabelModel();
	private void Tabel(JTable tb, int lebar[])
	{
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int kolom = tb.getColumnCount();

		for (int i=0; i<kolom; i++)
		{
			TableColumn tbc= tb.getColumnModel().getColumn(i);
			tbc.setPreferredWidth(lebar[i]);
			tb.setRowHeight(18);
		}
	}

	@SuppressWarnings("serial")
	private DefaultTableModel getDefaultTabelModel()
	{
		return new DefaultTableModel(
				new Object [][] {},
				new String [] {"Nama Bank", "No. Rek", "Atas Nama", "Cash/Tunai"}
		){
			boolean [] canEdit = new boolean[]{
					false,false,false,false
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
			List<MasterDana> masterDanaList = dao.GetAllMasterDana();

			if (masterDanaList.size() > 0)
			{
				for (MasterDana aMasterDanaList : masterDanaList)
				{
					data[0] = aMasterDanaList.getNameBankAccount();
					data[1] = aMasterDanaList.getNoBankAccount();
					data[2] = aMasterDanaList.getNameAccount();
					data[3] = String.valueOf(aMasterDanaList.getTotalCash());

					tabelModel.addRow(data);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showDana()
	{
		row = table.getSelectedRow();
		TF_Nama.setText(tabelModel.getValueAt(row, 0).toString());
		TF_noRek.setText(tabelModel.getValueAt(row, 1).toString());
		TF_AtasNama.setText(tabelModel.getValueAt(row, 2).toString());

		String value = tabelModel.getValueAt(row, 3).toString();
		int dotIndex = value.lastIndexOf(".");
		String name = value.substring(0, dotIndex);
		Long a = Long.valueOf(name);
		TF_Tunai.setValue(a);
	}

	private void clearDana()
	{
		TF_Nama.setText("");
		TF_noRek.setText("");
		TF_AtasNama.setText("");
		TF_Tunai.setText("");
	}

	private void setCurrencyNow()
	{
		//  set banyaknya angka akhir bilangan
		numformat.setMaximumFractionDigits(0);
		numformat.setMinimumFractionDigits(2);

		//  Deklarasikan NumberFormatter
		numformatter = new NumberFormatter(numformat);
		numformatter.setAllowsInvalid(false);
	}
}