package itech.sample;

import itech.funct.TableFunct;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TestSpringColoumn
{
	TableFunct tf = new TableFunct();
	
	public static void main(String[] args)
	{
		 SwingUtilities.invokeLater(new Runnable()
	      {          
	         @Override
	         public void run()
	         {
	            new TestSpringColoumn().makeUI();
	         }
	      });
	}

	protected void makeUI()
	{		
		int colTotal = tf.colCountFunct("buku_tamu");
		int rowTotal = tf.rowCountFunct("buku_tamu");
		//System.out.println(colTotal + ", " + rowTotal);		
		
		Object[][] data = new Object[rowTotal][colTotal];
		Object[] columnHeaders = new Object[colTotal];
	      
		columnHeaders = loadColumn(colTotal);
		data = tf.generateDataFunct(colTotal, rowTotal, "buku_tamu");
		
		JTable table = new JTable(data, columnHeaders) 
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
	       
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		frame.setLocationRelativeTo(null);
		frame.add(new JScrollPane(table));
		frame.setVisible(true);
	}

	private Object[] loadColumn(int colTotal) 
	{
		Object[] columnHeaders = new Object[colTotal];
	      
		columnHeaders[0] = "Id";
		columnHeaders[1] = "Nomor";
		columnHeaders[2] = "Nama";
		columnHeaders[3] = "Alamat";
		columnHeaders[4] = "Telp";
		columnHeaders[5] = "Sekolah";
		
		return columnHeaders;
	}
}