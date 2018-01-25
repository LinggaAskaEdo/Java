package com.main.java.invoice.project.form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;

public class CostOperasionalForm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();
	
	@SuppressWarnings("unused")
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                CostOperasionalForm frame = new CostOperasionalForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	CostOperasionalForm() {
		setClosable(true);
		setTitle("Cost Operasional");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 552, 276);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblNamaKantor = new JLabel("Reff Sumber Dana");
		lblNamaKantor.setBounds(45, 28, 153, 15);
		desktopPane.add(lblNamaKantor);
		
		JLabel lblPickontak = new JLabel("PIC/Kontak");
		lblPickontak.setBounds(45, 59, 153, 15);
		desktopPane.add(lblPickontak);
		
		JLabel lblKeperluan = new JLabel("Keperluan");
		lblKeperluan.setBounds(45, 89, 153, 15);
		desktopPane.add(lblKeperluan);
		
		JLabel lblTanggalPembelian = new JLabel("Tanggal Pembelian");
		lblTanggalPembelian.setBounds(45, 153, 153, 15);
		desktopPane.add(lblTanggalPembelian);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 57, 268, 19);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(216, 88, 284, 53);
		desktopPane.add(textArea);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(216, 153, 165, 20);
		desktopPane.add(calendarComboBox);
		
		/*table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Reff Sumber Dana", "PIC/Kontak", "Keperluan", "Tanggal"
			}
		));
		table.setBounds(46, 214, 532, 90);
		//desktopPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 180, 534, 90);
		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);*/
		
		JButton button = new JButton("Simpan");
		button.setBounds(254, 190, 117, 25);
		desktopPane.add(button);
		
		/*JButton button_1 = new JButton("Hapus");
		button_1.setBounds(171, 190, 117, 25);
		desktopPane.add(button_1);*/
		
		JButton button_2 = new JButton("Batal");
		button_2.setBounds(383, 190, 117, 25);
		desktopPane.add(button_2);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(216, 23, 205, 24);
		desktopPane.add(comboBox);

	}
}
