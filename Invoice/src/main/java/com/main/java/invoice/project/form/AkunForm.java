package com.main.java.invoice.project.form;

import com.main.java.invoice.project.dao.UserDAO;
import com.main.java.invoice.project.pojo.User;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.table.TableColumn;

public class AkunForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTable table;
	private JCheckBox chckbxAdmin;
	UserDAO dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AkunForm frame = new AkunForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	AkunForm()
	{
		setTitle("Akun");
		initializeForm();
		table.setModel(tabelModel);
		Tabel(table, new int[]{120, 120, 200});
		setDefaultTable();
	}

	int row = 0;
	String data[]=new String[3];

	public void initializeForm()
	{
		setClosable(true);
		setBounds(100, 100, 630, 360);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(46, 43, 139, 15);
		desktopPane.add(lblNama);
		
		JLabel lblNamaPengguna = new JLabel("Nama Pengguna");
		lblNamaPengguna.setBounds(46, 70, 139, 15);
		desktopPane.add(lblNamaPengguna);
		
		JLabel lblKataSandi = new JLabel("Kata Sandi");
		lblKataSandi.setBounds(46, 97, 139, 15);
		desktopPane.add(lblKataSandi);
		
		JLabel lblUlangiKataSandi = new JLabel("Ulangi Kata Sandi");
		lblUlangiKataSandi.setBounds(46, 124, 126, 15);
		desktopPane.add(lblUlangiKataSandi);
		
		textField = new JTextField();
		textField.setBounds(182, 41, 250, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 68, 250, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 95, 250, 19);
		desktopPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 122, 250, 19);
		desktopPane.add(passwordField_1);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearUser();
				textField.setEnabled(true);
			}
		});
		btnBatal.setBounds(463, 266, 117, 25);
		desktopPane.add(btnBatal);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = null;
				user.setUsers(textField.getText());

				try {
					dao.DeleteUserById(user);
					tabelModel.removeRow(row);
					clearUser();
					textField.setEnabled(true);
				} catch (Exception e3) {
					System.out.println(e3);
				}
			}
		});
		btnHapus.setBounds(172, 266, 117, 25);
		desktopPane.add(btnHapus);

		btnHapus.setEnabled(false);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUserName(textField.getText());
				user.setUserPassword(String.valueOf(passwordField.getPassword()));

				if (chckbxAdmin.isSelected()){
					user.setIsAdmin(1);
				} else {
					user.setIsAdmin(0);
				}

				if(!btnHapus.isEnabled() == false) {
					try{
						dao.addUpdate(user, 0);
						data[0] = textField.getText();
						data[1] = textField_1.getText();

						if (chckbxAdmin.isSelected()){
							data[2] = String.valueOf(1);
						} else {
							data[2] = String.valueOf(0);
						}
						tabelModel.insertRow(0, data);
						clearUser();
						textField.setEnabled(true);
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} else {
					try {
						dao.addUpdate(user, 1);
						data[0] = textField.getText();
						data[1] = textField_1.getText();

						if (chckbxAdmin.isSelected()){
							data[2] = String.valueOf(1);
						} else {
							data[2] = String.valueOf(0);
						}
						tabelModel.removeRow(row);
						tabelModel.insertRow(row, data);
						clearUser();
						textField.setEnabled(true);
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});
		btnSimpan.setBounds(46, 266, 117, 25);
		desktopPane.add(btnSimpan);
		
		chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setBounds(451, 39, 129, 23);
		desktopPane.add(chckbxAdmin);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==1) {
					showDana();
					btnHapus.setEnabled(true);
					textField.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 151, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

	}

	private DefaultTableModel tabelModel = getDefaultTabelModel();
	private void Tabel(JTable tb, int lebar[]){
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int kolom = tb.getColumnCount();
		for (int i=0; i<kolom; i++){
			TableColumn tbc= tb.getColumnModel().getColumn(i);
			tbc.setPreferredWidth(lebar[i]);
			tb.setRowHeight(18);
		}
	}

	private DefaultTableModel getDefaultTabelModel(){
		return new DefaultTableModel(
				new Object [][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null}
				},
				new String [] {"Nama", "Nama Pengguna", "Role"}
		){
			boolean [] canEdit = new boolean[]{
					false,false
			};
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return canEdit[columnIndex];
			}
		};
	}

	public void setDefaultTable()
	{
		try {
			List<User> userList;
			userList = dao.GetAllUser();

			for(int i = 0; i < userList.size(); i++) {
				data[0] = userList.get(i).getUsers();
				data[1] = userList.get(i).getUserName();
				data[2] = String.valueOf(userList.get(i).getIsAdmin());

				tabelModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void showDana()
	{
		row = table.getSelectedRow();
		textField.setText(tabelModel.getValueAt(row, 0).toString());
		textField_1.setText(tabelModel.getValueAt(row, 1).toString());

		if (tabelModel.getValueAt(row, 2).toString().equalsIgnoreCase("1")){
			chckbxAdmin.setEnabled(true);
		} else {
			chckbxAdmin.setEnabled(false);
		}
		passwordField.setText("");
		passwordField_1.setText("");
	}

	public void clearUser()
	{
		textField.setText("");
		textField_1.setText("");
		passwordField.setText("");
		passwordField_1.setText("");
		chckbxAdmin.setSelected(false);
	}
}
