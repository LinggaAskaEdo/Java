package com.main.java;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Laporan extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laporan frame = new Laporan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Laporan() {
		setClosable(true);
		setTitle("Laporan");
		//setBounds(100, 100, 630, 428);
		setBounds(100, 100, 577, 280);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Laporan");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 23, 370, 24);
		desktopPane.add(comboBox);
		
		JCalendarComboBox calendarComboBox = new JCalendarComboBox();
		calendarComboBox.setBounds(45, 125, 142, 20);
		desktopPane.add(calendarComboBox);
		
		JCalendarComboBox calendarComboBox_1 = new JCalendarComboBox();
		calendarComboBox_1.setBounds(228, 125, 142, 20);
		desktopPane.add(calendarComboBox_1);
		
		JCalendarComboBox calendarComboBox_2 = new JCalendarComboBox();
		calendarComboBox_2.setBounds(382, 125, 142, 20);
		desktopPane.add(calendarComboBox_2);
		
		JButton btnCetak = new JButton("Cetak");
		btnCetak.setBounds(217, 181, 117, 25);
		desktopPane.add(btnCetak);
		
		JRadioButton rdbtnHarian = new JRadioButton("Harian");
		rdbtnHarian.setBounds(74, 78, 77, 23);
		desktopPane.add(rdbtnHarian);
		
		JRadioButton rdbtnBulanan = new JRadioButton("Bulanan");
		rdbtnBulanan.setBounds(325, 78, 91, 23);
		desktopPane.add(rdbtnBulanan);

	}
}
