package itech.form.admin;

import itech.form.Menu;
import itech.funct.QuizFunct;
import itech.funct.TableFunct;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FormAdmin extends JFrame 
{	
	private static final long serialVersionUID = 1L;
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JPanel panel1, panel2, panel3;
	private JScrollPane scrollPane1, scrollPane2, scrollPane_1, scrollPane_2, scrollPane_3;
	private JTabbedPane tabbedPane;
	private JTable table, table_1, table_2, table_3, table_4, table_5;
	private JLabel lblDataSekolah;
	private JLabel lblDataSumber;
	private JButton btnNewButton;	
		
	TableFunct tf = new TableFunct();
	QuizFunct qf = new QuizFunct();
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try
				{
					FormAdmin frame = new FormAdmin();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormAdmin() 
	{		 
		setResizable(false);
		setTitle("Form Admin");
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
		setBounds(100, 100, 812, 658);
		contentPane = new JPanel();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1321, 630);
		contentPane.add(tabbedPane);
		
		panel1 = new JPanel();
		tabbedPane.addTab("Buku Tamu", null, panel1, null);
		tabbedPane.setEnabledAt(0, true);
		panel1.setLayout(null);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(20, 68, 500, 297);
		panel1.add(scrollPane1);
		
		///////////////////////////////////////////////////
		int colTotal1 = tf.colCountFunct("buku_tamu");
		int rowTotal1 = tf.rowCountFunct("buku_tamu");
		Object[][] data1 = new Object[rowTotal1][colTotal1];
		Object[] columnHeaders1 = new Object[colTotal1];				
		data1 = tf.generateDataFunct(colTotal1, rowTotal1, "buku_tamu");
		columnHeaders1 = loadColumn1(colTotal1);	
		//////////////////////////////////////////////////
		table = new JTable(data1, columnHeaders1)
		{ 
			private static final long serialVersionUID = 6462877000526125889L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane1.setViewportView(table);	
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 568, 89, 23);
		panel1.add(btnNewButton);
		
		JLabel lblDaftarBukuTamu = new JLabel("Daftar Buku Tamu");
		lblDaftarBukuTamu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDaftarBukuTamu.setBounds(20, 43, 130, 14);
		panel1.add(lblDaftarBukuTamu);
		
		panel2 = new JPanel();
		tabbedPane.addTab("Pendaftaran", null, panel2, null);
		panel2.setLayout(null);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 53, 772, 94);
		panel2.add(scrollPane2);
		
		///////////////////////////////////////////////////
		int colTotal2 = tf.colCountFunct("pendaftarandatadiri");
		int rowTotal2 = tf.rowCountFunct("pendaftarandatadiri");
		Object[][] data2 = new Object[rowTotal2][colTotal2];
		Object[] columnHeaders2 = new Object[colTotal2];	      
		data2 = tf.generateDataFunct(colTotal2, rowTotal2, "pendaftarandatadiri");
		columnHeaders2 = loadColumn2(colTotal2);				
		//////////////////////////////////////////////////
		table_1 = new JTable(data2, columnHeaders2)
		{ 
			private static final long serialVersionUID = 6462877000526125889L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
			}
		};
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));	
		scrollPane2.setViewportView(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 770, 94);
		panel2.add(scrollPane);
		
		///////////////////////////////////////////////////
		int colTotal3 = tf.colCountFunct("pendaftarandatalain");
		int rowTotal3 = tf.rowCountFunct("pendaftarandatalain");
		Object[][] data3 = new Object[rowTotal3][colTotal3];
		Object[] columnHeaders3 = new Object[colTotal3];	      
		data3 = tf.generateDataFunct(colTotal3, rowTotal3, "pendaftarandatalain");
		columnHeaders3 = loadColumn3(colTotal3);				
		//////////////////////////////////////////////////		
		table_2 = new JTable(data3, columnHeaders3)
		{ 
			private static final long serialVersionUID = 6462877000526125889L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
			}
		};
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));	
		scrollPane.setViewportView(table_2);	
		
		JLabel lblNewLabel_2 = new JLabel("Data Diri");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 28, 77, 14);
		panel2.add(lblNewLabel_2);
		
		JLabel lblDataLain = new JLabel("Data Lain");
		lblDataLain.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataLain.setBounds(10, 179, 77, 14);
		panel2.add(lblDataLain);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 354, 768, 94);
		panel2.add(scrollPane_1);
		
		///////////////////////////////////////////////////
		int colTotal4 = tf.colCountFunct("pendaftarandatasekolah");
		int rowTotal4 = tf.rowCountFunct("pendaftarandatasekolah");
		Object[][] data4 = new Object[rowTotal4][colTotal4];
		Object[] columnHeaders4 = new Object[colTotal4];	      
		data4 = tf.generateDataFunct(colTotal4, rowTotal4, "pendaftarandatasekolah");
		columnHeaders4 = loadColumn4(colTotal4);			
		//////////////////////////////////////////////////		
		table_3 = new JTable(data4, columnHeaders4)
		{ 
			private static final long serialVersionUID = 6462877000526125889L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
			}
		};
		table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_3.setBorder(new LineBorder(new Color(0, 0, 0)));	
		scrollPane_1.setViewportView(table_3);		
		
		lblDataSekolah = new JLabel("Data Sekolah");
		lblDataSekolah.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataSekolah.setBounds(10, 329, 77, 14);
		panel2.add(lblDataSekolah);
		
		lblDataSumber = new JLabel("Data Sumber");
		lblDataSumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataSumber.setBounds(10, 475, 77, 14);
		panel2.add(lblDataSumber);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 502, 766, 94);
		panel2.add(scrollPane_2);
		
		///////////////////////////////////////////////////
		int colTotal5 = tf.colCountFunct("pendaftarandatasumber");
		int rowTotal5 = tf.rowCountFunct("pendaftarandatasumber");
		Object[][] data5 = new Object[rowTotal5][colTotal5];
		Object[] columnHeaders5 = new Object[colTotal5];	      
		data5 = tf.generateDataFunct(colTotal5, rowTotal5, "pendaftarandatasumber");
		columnHeaders5 = loadColumn5(colTotal5);		
		//////////////////////////////////////////////////	
		table_4 = new JTable(data5, columnHeaders5)		
		{ 
			private static final long serialVersionUID = 6462877000526125889L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
	            int rendererWidth = component.getPreferredSize().width;
	            TableColumn tableColumn = getColumnModel().getColumn(column);
	            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	            return component;
			}
		};
		table_4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table_4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_4.setBorder(new LineBorder(new Color(0, 0, 0)));	
		scrollPane_2.setViewportView(table_4);	
		
		panel3 = new JPanel();
		tabbedPane.addTab("Score Quiz", null, panel3, null);
		tabbedPane.setEnabledAt(0, true);
		panel3.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 53, 303, 94);
		panel3.add(scrollPane_3);
		
		///////////////////////////////////////////////////
		int colTotal6 = tf.colCountFunct("final_score");
		int rowTotal6 = tf.rowCountFunct("final_score");
		Object[][] data6 = new Object[rowTotal1][colTotal6];
		Object[] columnHeaders6 = new Object[colTotal6];				
		data6 = tf.generateDataFunct(colTotal6, rowTotal6, "final_score");
		columnHeaders6 = loadColumn6(colTotal6);	
		//////////////////////////////////////////////////
		table_5 = new JTable(data6, columnHeaders6)
		{ 
			private static final long serialVersionUID = 6462877000526125889L;
			
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		table_5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		table_5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_3.setViewportView(table_5);	
		
		JLabel lblDftarScoreQuiz = new JLabel("Daftar Score Quiz");
		lblDftarScoreQuiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDftarScoreQuiz.setBounds(10, 24, 132, 14);
		panel3.add(lblDftarScoreQuiz);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener()
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent e) 
			{
				int rowSelected = table_5.getSelectedRow();
				if(rowSelected >= 0)
				{
					String tableClick = table_5.getModel().getValueAt(rowSelected, 0).toString();
					//JOptionPane.showMessageDialog(null, tableClick);
										
					String[] dataFinalScore = new String[5];
					String namaPendaftar = "";
					
					try 
					{
						namaPendaftar = qf.getNameScore(tableClick);
					}
					catch (SQLException e3) 
					{
						e3.printStackTrace();
					}
					/*System.out.println(namaPendaftar);*/
					
					try
					{
						dataFinalScore = qf.getDataScore(tableClick);
					} 
					catch (SQLException e2) 
					{
						e2.printStackTrace();
					}				
					/*System.out.println(dataFinalScore[0]);
					System.out.println(dataFinalScore[1]);*/
					
					//generate report
		            JasperPrint jasperPrint = null;
		            TableModelData(tableClick, namaPendaftar, dataFinalScore[0], dataFinalScore[1]);
		            
		            try 
		            {
		                JasperCompileManager.compileReportToFile("reports/ScoreReports.jrxml", "reports/ScoreReports.jasper");
		                jasperPrint = JasperFillManager.fillReport("reports/ScoreReports.jasper", new HashMap(), new JRTableModelDataSource(tableModel));
		                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		                jasperViewer.setVisible(true);
		            } 
		            catch (JRException ex)
		            {
		                ex.printStackTrace();
		            }	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select data before generate report");
				}
			}

			private void TableModelData(String tableClick, String namaPendaftar, String dataFinalScore, String dataFinalScore2)
			{				
				String[] columnNames = {"No.", "Nama", "Nilai Quiz", "Total Biaya"};
				String[][] data =
				{
		            {tableClick, namaPendaftar, dataFinalScore, dataFinalScore2},
				};
		        tableModel = new DefaultTableModel(data, columnNames);					
			}
		});
		btnGenerateReport.setBounds(10, 158, 303, 23);
		panel3.add(btnGenerateReport);
	}		

	private Object[] loadColumn1(int colTotal1) 
	{
		Object[] columnHeaders = new Object[colTotal1];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "Nomor";
		columnHeaders[2] = "Nama";
		columnHeaders[3] = "Alamat";
		columnHeaders[4] = "Telp";
		columnHeaders[5] = "Sekolah";
		
		return columnHeaders;
	}	
	
	private Object[] loadColumn2(int colTotal2) 
	{
		Object[] columnHeaders = new Object[colTotal2];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "No.";
		columnHeaders[2] = "Program Studi";
		columnHeaders[3] = "Foto";
		columnHeaders[4] = "Nama Lengkap";
		columnHeaders[5] = "Nama Panggilan";
		columnHeaders[6] = "Tempat Lahir";
		columnHeaders[7] = "Tanggal Lahir";
		columnHeaders[8] = "Agama";
		columnHeaders[9] = "Jenis Kelamin";
		columnHeaders[10] = "Alamat Tinggal";
		columnHeaders[11] = "No. Telp";
		columnHeaders[12] = "No. HP";
		columnHeaders[13] = "Email";
		columnHeaders[14] = "Status Nikah";
		columnHeaders[15] = "Status Kerja";
		
		return columnHeaders;
	}
	
	private Object[] loadColumn3(int colTotal3) 
	{
		Object[] columnHeaders = new Object[colTotal3];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "No.";
		columnHeaders[2] = "Nama Bapak";
		columnHeaders[3] = "Pekerjaan Bapak";
		columnHeaders[4] = "Nama Ibu";
		columnHeaders[5] = "Pekerjaan Ibu";
		columnHeaders[6] = "Penghasilan";
		columnHeaders[7] = "Sumber Biaya";
		
		return columnHeaders;
	}
	
	private Object[] loadColumn4(int colTotal4)
	{
		Object[] columnHeaders = new Object[colTotal4];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "No.";
		columnHeaders[2] = "Nama SMP";
		columnHeaders[3] = "Jurusan SMP";
		columnHeaders[4] = "Alamat SMP";
		columnHeaders[5] = "Lulus SMP";
		columnHeaders[6] = "Nama SMU";
		columnHeaders[7] = "Jurusan SMU";
		columnHeaders[8] = "Alamat SMU";
		columnHeaders[9] = "Lulus SMU";
		columnHeaders[10] = "Tingkat Pendidikan";
		columnHeaders[11] = "Nama Institusi";
		columnHeaders[12] = "Jurusan Institusi";
		columnHeaders[13] = "Alamat Institusi";
		columnHeaders[14] = "Lulus Institusi";
		columnHeaders[15] = "IPK";
		
		return columnHeaders;
	}
	
	private Object[] loadColumn5(int colTotal5)
	{
		Object[] columnHeaders = new Object[colTotal5];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "No.";
		columnHeaders[2] = "Sumber 1";
		columnHeaders[3] = "Sumber 2";
		columnHeaders[4] = "Sumber 3";
		columnHeaders[5] = "Sumber 4";
		columnHeaders[6] = "Sumber 5";
		columnHeaders[7] = "Sumber 6";
		columnHeaders[8] = "Sumber 7";
		columnHeaders[9] = "Sumber 8";
		columnHeaders[10] = "Sumber 9";
		
		return columnHeaders;
	}
	
	private Object[] loadColumn6(int colTotal6) 
	{
		Object[] columnHeaders = new Object[colTotal6];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "Nomor";
		columnHeaders[2] = "Score";
		columnHeaders[3] = "Price";
				
		return columnHeaders;
	}	
}