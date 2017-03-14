package itech.form;

import itech.funct.SavePendaftaran;
import itech.funct.TempPendaftaran;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class Pendaftaran2 extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JLabel lblDariManaAnda;
	private JLabel lblNilaiIpk;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 				
				{
					Pendaftaran2 frame = new Pendaftaran2();
					frame.setExtendedState(MAXIMIZED_BOTH);				
					frame.setVisible(true);					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Pendaftaran2()
	{
		setTitle("Form Pendaftaran Mahasiswa Baru");
		initializeForm();
		centerForm();			
	}
	
	private void centerForm()
	{			
		this.setLocationRelativeTo(getRootPane());
	}
	
	private void initializeForm()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 830, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable()
		{
	        private static final long serialVersionUID = 1L;
	        
	        public boolean isCellEditable(int row, int column) 
	        {           	
	        	if (column == 0 )
	        	{
	        		return false;
	        	}
	        	else if (row == 0 && column == 1)
	        	{
	        		return false;
				}
	        	else if (row == 0 && column == 2)
	        	{
	        		return false;
				}
	        	else if (row == 0 && column == 3)
	        	{
	        		return false;
				}
	        	else if (row == 0 && column == 4)
	        	{
	        		return false;
				}
	        	else
	        	{
	        		return true;
	        	}
	           
	        };
	    };
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				//int row = table.getSelectedRow();
				//int col = table.getSelectedColumn();
				//JOptionPane.showMessageDialog(null, String.valueOf(row) + " " + String.valueOf(col));
			}
		});
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] 
			{
				{"Tingkat Pendidikan", "Nama Sekolah", "Jurusan", "Alamat", "Tahun Kelulusan"},
				{"SLTP", null, null, null, null},
				{"SMU", null, null, null, null},
			},
			new String[] 
			{
				"Tingkat Pendidikan", "Nama Sekolah", "Jurusan", "Alamat", "Tahun Kelulusan"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(182);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(258);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.setBounds(26, 45, 725, 48);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("LATAR BELAKANG PENDIDIKAN CALON MAHASISWA");
		lblNewLabel.setBounds(258, 11, 347, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPendidikanSetelahSmusmk = new JLabel("PENDIDIKAN SETELAH SMU/SMK");
		lblPendidikanSetelahSmusmk.setBounds(311, 114, 236, 14);
		contentPane.add(lblPendidikanSetelahSmusmk);
		
		table_1 = new JTable()
		{
	        private static final long serialVersionUID = 1L;
	        
	        public boolean isCellEditable(int row, int column) 
	        {         	
	        	if (row == 0)
	        	{
	        		return false;
	        	}	        	
	        	else
	        	{
	        		return true;
	        	}
	           
	        };
	    };
		table_1.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				//int row = table_1.getSelectedRow();
				//int col = table_1.getSelectedColumn();
				//JOptionPane.showMessageDialog(null, String.valueOf(row) + " " + String.valueOf(col));
			}
		});
	    table_1.setColumnSelectionAllowed(true);
	    table_1.setCellSelectionEnabled(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][]
			{
				{"Tingkat Pendidikan", "Nama Institusi", "Jurusan", "Alamat", "Tahun Kelulusan"}, 
				{null, null, null, null, null}, 
			},
			new String[]
			{
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setBounds(26, 141, 725, 32);
		contentPane.add(table_1);
		
		lblDariManaAnda = new JLabel("DARI MANA ANDA MENGETAHUI INFORMASI TENTANG STTI NIIT I-TECH");
		lblDariManaAnda.setBounds(259, 233, 433, 14);
		contentPane.add(lblDariManaAnda);
		
		lblNilaiIpk = new JLabel("Nilai IPK");
		lblNilaiIpk.setBounds(26, 198, 97, 14);
		contentPane.add(lblNilaiIpk);
		
		textField = new JTextField();
		textField.setBounds(171, 195, 147, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JCheckBox chckbxSurat = new JCheckBox("Surat yang saya terima dari STTI NIIT I-Tech");
		chckbxSurat.setBounds(26, 259, 292, 23);
		chckbxSurat.setActionCommand("Surat yang saya terima dari STTI NIIT I-Tech");
		contentPane.add(chckbxSurat);
		
		final JCheckBox chckbxSelebaranAtauBrosur = new JCheckBox("Selebaran atau brosur STTI NIIT I-Tech di koran dan majalah");
		chckbxSelebaranAtauBrosur.setBounds(25, 285, 446, 23);
		chckbxSelebaranAtauBrosur.setActionCommand("Selebaran atau brosur STTI NIIT I-Tech di koran dan majalah");
		contentPane.add(chckbxSelebaranAtauBrosur);
		
		final JCheckBox chckbxPosterBillboard = new JCheckBox("Poster/Billboard STTI  NIIT I-Tech");
		chckbxPosterBillboard.setBounds(26, 311, 347, 23);
		chckbxPosterBillboard.setActionCommand("Poster/Billboard STTI  NIIT I-Tech");
		contentPane.add(chckbxPosterBillboard);
		
		final JCheckBox chckbxPerwakilanSttiNiit = new JCheckBox("Perwakilan STTI NIIT I-Tech");
		chckbxPerwakilanSttiNiit.setBounds(26, 337, 211, 23);
		chckbxPerwakilanSttiNiit.setActionCommand("Perwakilan STTI NIIT I-Tech");
		contentPane.add(chckbxPerwakilanSttiNiit);
		
		final JCheckBox chckbxPresentasiSttiNiit = new JCheckBox("Presentasi STTI NIIT I-Tech yang pernah saya hadiri");
		chckbxPresentasiSttiNiit.setBounds(26, 363, 445, 23);
		chckbxPresentasiSttiNiit.setActionCommand("Presentasi STTI NIIT I-Tech yang pernah saya hadiri");
		contentPane.add(chckbxPresentasiSttiNiit);
		
		final JCheckBox chckbxIklanSttiNiit = new JCheckBox("Iklan STTI NIIT I-Tech yang saya lihat di koran/majalah");
		chckbxIklanSttiNiit.setBounds(26, 389, 378, 23);
		chckbxIklanSttiNiit.setActionCommand("Iklan STTI NIIT I-Tech yang saya lihat di koran/majalah");
		contentPane.add(chckbxIklanSttiNiit);
		
		final JCheckBox chckbxRekomendasiDariTeman = new JCheckBox("Rekomendasi dari teman/keluarga/seseorang yang berkecimpung dibidang komputer");
		chckbxRekomendasiDariTeman.setBounds(26, 412, 579, 23);
		chckbxRekomendasiDariTeman.setActionCommand("Rekomendasi dari teman/keluarga/seseorang yang berkecimpung dibidang komputer");
		contentPane.add(chckbxRekomendasiDariTeman);
		
		final JCheckBox chckbxPameran = new JCheckBox("Pameran");
		chckbxPameran.setBounds(26, 438, 97, 23);
		chckbxPameran.setActionCommand("Pameran");
		contentPane.add(chckbxPameran);
		
		final JCheckBox chckbxLainLain = new JCheckBox("Lain - Lain :");
		chckbxLainLain.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				if(chckbxLainLain.isSelected())
				{
					textField_1.setEnabled(true);
				}
				else
				{
					textField_1.setEnabled(false);
				}
			}
		});
		chckbxLainLain.setBounds(25, 464, 97, 23);
		chckbxLainLain.setActionCommand("Lainnya");
		contentPane.add(chckbxLainLain);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(128, 465, 249, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPersyaratanDokumen = new JLabel("PERSYARATAN DOKUMEN ");
		lblPersyaratanDokumen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPersyaratanDokumen.setBounds(26, 508, 236, 14);
		contentPane.add(lblPersyaratanDokumen);
		
		JLabel lblFotokopiLegalisir = new JLabel("1. Fotokopi legalisir ijazah SMU /sederajat (1 lembar) *");
		lblFotokopiLegalisir.setBounds(26, 533, 347, 14);
		contentPane.add(lblFotokopiLegalisir);
		
		JLabel lblFotokopiLegalisir_1 = new JLabel("2. Fotokopi Legalisir DANEM/ SKHUN (1 Lembar)*");
		lblFotokopiLegalisir_1.setBounds(26, 558, 347, 14);
		contentPane.add(lblFotokopiLegalisir_1);
		
		JLabel lblFotokopiKtp = new JLabel("3. Fotokopi KTP (1 Lembar)");
		lblFotokopiKtp.setBounds(26, 583, 236, 14);
		contentPane.add(lblFotokopiKtp);
		
		JLabel lblPasPhoto = new JLabel("4. Pas Photo 2x3 (2 Lembar)");
		lblPasPhoto.setBounds(26, 608, 236, 14);
		contentPane.add(lblPasPhoto);
		
		JLabel lblPasPhoto_1 = new JLabel("5. Pas Photo 4x6 (1 embar)");
		lblPasPhoto_1.setBounds(383, 533, 236, 14);
		contentPane.add(lblPasPhoto_1);
		
		JLabel lblFotokopiRaport = new JLabel("6. Fotokopi raport kelas 2 dan kelas 3");
		lblFotokopiRaport.setBounds(383, 558, 236, 14);
		contentPane.add(lblFotokopiRaport);
		
		JLabel lblSuratKeterangan = new JLabel("7. Surat Keterangan Bebas Narkoba");
		lblSuratKeterangan.setBounds(383, 583, 249, 14);
		contentPane.add(lblSuratKeterangan);
		
		JLabel lbldapatDilengkapiSetelah = new JLabel("* Dapat dilengkapi setelah ijazah diterima dari Sekolah");
		lbldapatDilengkapiSetelah.setBounds(383, 608, 352, 14);
		contentPane.add(lbldapatDilengkapiSetelah);
		
		JButton btnBack = new JButton("Back");
		btnBack.setEnabled(false);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Pendaftaran frame = new Pendaftaran();
				frame.setExtendedState(MAXIMIZED_BOTH);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(26, 676, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//check data
				TempPendaftaran tp = new TempPendaftaran();       
			        
				try
				{
					if (table.getModel().getValueAt(1, 1) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama SLTP");
					/*else if (table.getModel().getValueAt(1, 2) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Jurusan SLTP");*/
					else if (table.getModel().getValueAt(1, 3) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Alamat SLTP");
					else if (table.getModel().getValueAt(1, 4) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tahun Kelulusan SLTP");
					else if (table.getModel().getValueAt(2, 1) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama SMU");
					else if (table.getModel().getValueAt(2, 2) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Jurusan SMU");
					else if (table.getModel().getValueAt(2, 3) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Alamat SMU");
					else if (table.getModel().getValueAt(2, 4) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tahun Kelulusan SMU");
					/*else if (table_1.getModel().getValueAt(1, 0) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tingkat Pendidikan");
					else if (table_1.getModel().getValueAt(1, 1) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nama Institusi");
					else if (table_1.getModel().getValueAt(1, 2) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Jurusan");
					else if (table_1.getModel().getValueAt(1, 3) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Alamat");
					else if (table_1.getModel().getValueAt(1, 4) == null)
						JOptionPane.showMessageDialog(null, "Silahkan Isi Tahun Kelulusan");
					else if (textField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Silahkan Isi Nilai IPK");*/
					/*
					 * else if(sumber.getSelection() == null)
					 * JOptionPane.showMessageDialog(null,
					 * "Silahkan Pilih Sumber Informasi");
					 */
					else
					{
						//JOptionPane.showMessageDialog(null, "Lengkap");

						//clear file first				
						tp.emptyFile("p2.dat");
						
						String namaSltp = table.getModel().getValueAt(1, 1).toString();
						tp.appendFile("p2.dat", namaSltp);
						//JOptionPane.showMessageDialog(null, namaSltp);	

						String jurusanSltp = "";
						Object valJurusanSltp = table.getValueAt(1, 2);
						if (valJurusanSltp != null)
							jurusanSltp = table.getModel().getValueAt(1, 2).toString();
						tp.appendFile("p2.dat", jurusanSltp);
						
						String alamatSltp = table.getModel().getValueAt(1, 3).toString();
						tp.appendFile("p2.dat", alamatSltp);
						//JOptionPane.showMessageDialog(null, alamatSltp);	

						String tahunSltp = table.getModel().getValueAt(1, 4).toString();
						tp.appendFile("p2.dat", tahunSltp);
						//JOptionPane.showMessageDialog(null, tahunSltp);	

						String namaSmu = table.getModel().getValueAt(2, 1).toString();
						tp.appendFile("p2.dat", namaSmu);
						//JOptionPane.showMessageDialog(null, namaSmu);	

						String jurusanSmu = table.getModel().getValueAt(2, 2).toString();
						tp.appendFile("p2.dat", jurusanSmu);
						//JOptionPane.showMessageDialog(null, jurusanSmu);	

						String alamatSmu = table.getModel().getValueAt(2, 3).toString();
						tp.appendFile("p2.dat", alamatSmu);
						//JOptionPane.showMessageDialog(null, alamatSmu);	

						String tahunSmu = table.getModel().getValueAt(2, 4).toString();
						tp.appendFile("p2.dat", tahunSmu);
						//JOptionPane.showMessageDialog(null, tahunSmu);							

						/*String tingkatPendidikan = table_1.getModel().getValueAt(1, 0).toString();
						if(tingkatPendidikan.equals(null))
							tingkatPendidikan = "";
						else
							tp.appendFile("p2.dat", tingkatPendidikan);*/
						//JOptionPane.showMessageDialog(null, tingkatPendidikan);	
						
						String tingkatPendidikan = "";
						Object valTingkatPendidikan = table_1.getValueAt(1, 0);
						if (valTingkatPendidikan != null)
							tingkatPendidikan = table_1.getModel().getValueAt(1, 0).toString();
						tp.appendFile("p2.dat", tingkatPendidikan);

						/*String namaInstitusi = table_1.getModel().getValueAt(1, 1).toString();
						if(namaInstitusi.equals(null))
							namaInstitusi = "";
						else
							tp.appendFile("p2.dat", namaInstitusi);*/
						//JOptionPane.showMessageDialog(null, namaInstitusi);	

						String namaInstitusi = "";
						Object valNamaInstitusi = table_1.getValueAt(1,1);
						if (valNamaInstitusi != null)
							namaInstitusi = table_1.getModel().getValueAt(1, 1).toString();
						tp.appendFile("p2.dat", namaInstitusi);
						
						/*String jurusan = table_1.getModel().getValueAt(1, 2).toString();
						if(jurusan.equals(null))
							jurusan = "";
						else
						tp.appendFile("p2.dat", jurusan);*/
						//JOptionPane.showMessageDialog(null, jurusan);	

						String jurusan = "";
						Object valJurusan = table_1.getValueAt(1,2);
						if (valJurusan != null)
							jurusan = table_1.getModel().getValueAt(1, 2).toString();
						tp.appendFile("p2.dat", jurusan);
						
						/*String alamat = table_1.getModel().getValueAt(1, 3).toString();
						if(alamat.equals(null))
							alamat = "";
						else
						tp.appendFile("p2.dat", alamat);*/
						//JOptionPane.showMessageDialog(null, alamat);	

						String alamat = "";
						Object valAlamat = table_1.getValueAt(1,3);
						if (valAlamat != null)
							alamat = table_1.getModel().getValueAt(1, 3).toString();
						tp.appendFile("p2.dat", alamat);
						
						/*String tahunKelulusan = table_1.getModel().getValueAt(1, 4).toString();
						if(tahunKelulusan.equals(null))
							tahunKelulusan = "";
						else
						tp.appendFile("p2.dat", tahunKelulusan);*/
						//JOptionPane.showMessageDialog(null, tahunKelulusan);	
						
						String tahunKelulusan = "";
						Object valTahunKelulusan = table_1.getValueAt(1,4);
						if (valTahunKelulusan != null)
							tahunKelulusan = table_1.getModel().getValueAt(1, 4).toString();
						tp.appendFile("p2.dat", tahunKelulusan);
						
						String ipk = textField.getText();
						if(ipk == "")
							ipk = "";
						tp.appendFile("p2.dat", ipk);
						//JOptionPane.showMessageDialog(null, ipk);

						if (chckbxSurat.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxSurat.getActionCommand());
							tp.appendFile("p2.dat", chckbxSurat.getActionCommand());
						}

						if (chckbxSelebaranAtauBrosur.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxSelebaranAtauBrosur.getActionCommand());
							tp.appendFile("p2.dat", chckbxSelebaranAtauBrosur.getActionCommand());
						}

						if (chckbxPosterBillboard.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxPosterBillboard.getActionCommand());
							tp.appendFile("p2.dat", chckbxPosterBillboard.getActionCommand());
						}

						if (chckbxPerwakilanSttiNiit.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxPerwakilanSttiNiit.getActionCommand());
							tp.appendFile("p2.dat", chckbxPerwakilanSttiNiit.getActionCommand());
						}

						if (chckbxPresentasiSttiNiit.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxPresentasiSttiNiit.getActionCommand());
							tp.appendFile("p2.dat", chckbxPresentasiSttiNiit.getActionCommand());
						}

						if (chckbxIklanSttiNiit.isSelected()) 
						{
							//JOptionPane.showMessageDialog(null, chckbxIklanSttiNiit.getActionCommand());
							tp.appendFile("p2.dat", chckbxIklanSttiNiit.getActionCommand());
						}

						if (chckbxRekomendasiDariTeman.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxRekomendasiDariTeman.getActionCommand());
							tp.appendFile("p2.dat", chckbxRekomendasiDariTeman.getActionCommand());
						}

						if (chckbxPameran.isSelected())
						{
							//JOptionPane.showMessageDialog(null, chckbxPameran.getActionCommand());
							tp.appendFile("p2.dat", chckbxPameran.getActionCommand());
						}

						if (chckbxLainLain.isSelected()) 
						{
							if (textField_1.getText().equals(""))
								JOptionPane.showMessageDialog(null, "Tolong isi lainnya");
							else
							{
								//JOptionPane.showMessageDialog(null, textField_1.getText());
								tp.appendFile("p2.dat", textField_1.getText());
							}
						}
						
						//save data
						SavePendaftaran sp = new SavePendaftaran();
						try 
						{
							sp.saveDataDiri();
							sp.saveDataLain();
							sp.saveDataSekolah();
							sp.saveDataSumber();
							JOptionPane.showMessageDialog(null, "Simpan Berhasil");
						}
						catch (Exception e1) 
						{					
							e1.printStackTrace();
						}
					
						//delete foto
						try
						{
							File file = new File("test.jpg");
				 
							if(file.exists()) 
							{
								if(file.delete())
					    		{
					    			System.out.println(file.getName() + " is deleted!");
					    		}
					    		else
					    		{
					    			System.out.println("Delete operation is failed.");
					    		}
							}					
						}
						catch(Exception ezz)
						{
							ezz.printStackTrace();
						}	
						
						//clean temp data
						tp.emptyFile("p1.dat");
						tp.emptyFile("p2.dat");
						
						Menu frame = new Menu();
						frame.setVisible(true);
						dispose();
					}
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}				
			}
		});
		btnSave.setBounds(121, 676, 89, 23);
		contentPane.add(btnSave);
	}
}