package com.main.java.invoice.project.form;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;
import de.wannawork.jcalendar.JCalendarComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class LaporanForm extends JInternalFrame {
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
					LaporanForm frame = new LaporanForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	LaporanForm()
	{
		setTitle("Laporan");
		initializeForm();
	}

	public void initializeForm() {

		setClosable(true);
		setBounds(100, 100, 577, 280);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		
		JLabel lblPoNomor = new JLabel("Laporan");
		lblPoNomor.setBounds(45, 28, 91, 15);
		desktopPane.add(lblPoNomor);
		
		@SuppressWarnings("rawtypes")
		JComboBox CB_Laporan = new JComboBox();
		CB_Laporan.setBounds(130, 23, 370, 24);
		desktopPane.add(CB_Laporan);
		
		//JCalendarComboBox CL_Harian = new JCalendarComboBox();
		JDateChooser CL_Harian = new JDateChooser();
		CL_Harian.setBounds(45, 125, 142, 20);
		CL_Harian.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Harian);
		
		//JCalendarComboBox CL_Bulanan_1 = new JCalendarComboBox();
		JDateChooser CL_Bulanan_1 = new JDateChooser();
		CL_Bulanan_1.setBounds(228, 125, 142, 20);
		CL_Bulanan_1.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_1);
		
		//JCalendarComboBox CL_Bulanan_2 = new JCalendarComboBox();
		JDateChooser CL_Bulanan_2 = new JDateChooser();
		CL_Bulanan_2.setBounds(382, 125, 142, 20);
		CL_Bulanan_2.setDateFormatString("d MMM yyyy");
		desktopPane.add(CL_Bulanan_2);
		
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
