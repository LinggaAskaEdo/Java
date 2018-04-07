package com.main.java.invoice.project.form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

import static java.awt.SystemColor.desktop;

public class MenuForm extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
            try
            {
                MenuForm frame = new MenuForm();
				frame.setResizable(false);
				frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
	}

	MenuForm()
	{
		setTitle("Menu");
		initializeForm();
		centerForm();
		setBackgroundImage();
	}

	private void centerForm()
	{
		this.setLocationRelativeTo(getRootPane());
	}

	private void initializeForm()
	{
		this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/*Setting Panel*/
		desktopPane = new JDesktopPane();

		//JPanel contentPane = new JPanel();
		//contentPane.setBackground(new Image());
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(null);

		//JLabel background = new JLabel(new ImageIcon("C:\\Program Files\\Invoice\\image\\background.png"), SwingConstants.CENTER);
		//add(background);
		//background.setLayout(new FlowLayout());
		//contentPane.add(background);

		//ImagePanel panel = new ImagePanel(new ImageIcon("C:\\\\Program Files\\\\Invoice\\\\image\\\\background.png").getImage());
		//getContentPane().add(panel);
		//pack();

		//getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		//getContentPane().add(desktopPane);
		//desktopPane.setLayout(null);

		//contentPane.add(desktopPane, BorderLayout.CENTER);

		setContentPane(desktopPane);

		/*Setting MenuBar*/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem mntmKeluar = new JMenuItem("Keluar");
		mntmKeluar.addActionListener(this::keluarActionPerformed);
		file.add(mntmKeluar);

		JMenu mnPurchaseOrder = new JMenu("Purchase Order");
		menuBar.add(mnPurchaseOrder);

		JMenuItem mntmPoMedia = new JMenuItem("PO. Media");
		mntmPoMedia.addActionListener(this::poMediaActionPerformed);
		mnPurchaseOrder.add(mntmPoMedia);

		JMenuItem mntmPoProduksi = new JMenuItem("PO. Produksi");
		mntmPoProduksi.addActionListener(this::poProduksiActionPerformed);
		mnPurchaseOrder.add(mntmPoProduksi);

		JMenuItem mntmPoEvent = new JMenuItem("PO. Event");
		mntmPoEvent.addActionListener(this::poEventActionPerformed);
		mnPurchaseOrder.add(mntmPoEvent);

		JMenuItem mntmFunding = new JMenuItem("Funding");
		mntmFunding.addActionListener(this::fundingActionPerformed);
		mnPurchaseOrder.add(mntmFunding);

		JMenuItem mntmCostOperasional = new JMenuItem("Cost Operasional");
		mntmCostOperasional.addActionListener(this::costOperasionalActionPerformed);
		mnPurchaseOrder.add(mntmCostOperasional);

		JMenu mnKontrak = new JMenu("Kontrak");
		mnKontrak.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				kontrakActionPerformed(e);
			}
		});
		menuBar.add(mnKontrak);

		JMenu mnMasterData = new JMenu("Master Data");
		menuBar.add(mnMasterData);

		JMenuItem mntmMasterLegalitasPerusahaan = new JMenuItem("Master Legalitas Perusahaan");
		mntmMasterLegalitasPerusahaan.addActionListener(this::masterLegalitasActionPerformed);
		mnMasterData.add(mntmMasterLegalitasPerusahaan);

		JMenuItem mntmNewMenuItem = new JMenuItem("Master Media");
		mntmNewMenuItem.addActionListener(this::masterMediaActionPerformed);
		mnMasterData.add(mntmNewMenuItem);

		JMenuItem mntmMasterKlien = new JMenuItem("Master Klien");
		mntmMasterKlien.addActionListener(this::masterKlienActionPerformed);
		mnMasterData.add(mntmMasterKlien);

		JMenuItem mntmMasterEvent = new JMenuItem("Master Event");
		mntmMasterEvent.addActionListener(this::masterEventActionPerformed);
		mnMasterData.add(mntmMasterEvent);

		JMenuItem mntmMasterProduksi = new JMenuItem("Master Produksi");
		mntmMasterProduksi.addActionListener(this::masterProduksiActionPerformed);
		mnMasterData.add(mntmMasterProduksi);

		JMenuItem mntmMasterDana = new JMenuItem("Master Dana");
		mntmMasterDana.addActionListener(this::masterDanaActionPerformed);
		mnMasterData.add(mntmMasterDana);

		JMenu mnAkun = new JMenu("Akun");
		mnAkun.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				akunActionPerformed(e);
			}
		});
		menuBar.add(mnAkun);

		JMenu Laporan = new JMenu("Laporan");
		Laporan.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				laporanActionPerformed(e);
			}
		});
		menuBar.add(Laporan);

		/*menuBar.add(Box.createHorizontalGlue());
		JMenu mnLogout = new JMenu("Logout");
		menuBar.add(mnLogout);*/
	}

	private void keluarActionPerformed(ActionEvent actionEvent)
	{
		dispose();
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
	}

	private void poMediaActionPerformed(ActionEvent e)
	{
		POMediaForm pom = new POMediaForm();
		desktopPane.add(pom);
		Dimension screen = this.getSize();
		Dimension frame = pom.getSize();
		pom.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		pom.setVisible(true);
	}
	
	private void poProduksiActionPerformed(ActionEvent e)
	{
		POProduksiForm pop = new POProduksiForm();
		desktopPane.add(pop);
		Dimension screen = this.getSize();
		Dimension frame = pop.getSize();
		pop.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		pop.setVisible(true);
	}
	
	private void poEventActionPerformed(ActionEvent e)
	{
		POEventForm poe = new POEventForm();
		desktopPane.add(poe);
		Dimension screen = this.getSize();
		Dimension frame = poe.getSize();
		poe.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		poe.setVisible(true);
	}
	
	private void fundingActionPerformed(ActionEvent e)
	{
		FundingForm fun = new FundingForm();
		desktopPane.add(fun);
		Dimension screen = this.getSize();
		Dimension frame = fun.getSize();
		fun.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		fun.setVisible(true);
	}
	
	private void costOperasionalActionPerformed(ActionEvent e)
	{
		CostOperasionalForm coo = new CostOperasionalForm();
		desktopPane.add(coo);
		Dimension screen = this.getSize();
		Dimension frame = coo.getSize();
		coo.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		coo.setVisible(true);
	}
	
	private void kontrakActionPerformed(MouseEvent e)
	{
		KontrakForm kon = new KontrakForm();
		desktopPane.add(kon);
		Dimension screen = this.getSize();
		Dimension frame = kon.getSize();
		kon.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		kon.setVisible(true);
	}

	private void akunActionPerformed(MouseEvent e)
	{
		AkunForm akunForm = new AkunForm();
		desktopPane.add(akunForm);
		Dimension screen = this.getSize();
		Dimension frame = akunForm.getSize();
		akunForm.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		akunForm.setVisible(true);
	}
	
	private void masterLegalitasActionPerformed(ActionEvent e)
	{
		MasterLegalitasForm mal = new MasterLegalitasForm();
		desktopPane.add(mal);
		Dimension screen = this.getSize();
		Dimension frame = mal.getSize();
		mal.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		mal.setVisible(true);
	}
	
	private void masterMediaActionPerformed(ActionEvent e)
	{
		MasterMediaForm mam = new MasterMediaForm();
		desktopPane.add(mam);
		Dimension screen = this.getSize();
		Dimension frame = mam.getSize();
		mam.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		mam.setVisible(true);
	}
	
	private void masterKlienActionPerformed(ActionEvent e)
	{
		MasterKlienForm mak = new MasterKlienForm();
		desktopPane.add(mak);
		Dimension screen = this.getSize();
		Dimension frame = mak.getSize();
		mak.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		mak.setVisible(true);
	}
	
	private void masterEventActionPerformed(ActionEvent e)
	{
		MasterEventForm mae = new MasterEventForm();
		desktopPane.add(mae);
		Dimension screen = this.getSize();
		Dimension frame = mae.getSize();
		mae.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		mae.setVisible(true);
	}
	
	private void masterProduksiActionPerformed(ActionEvent e)
	{
		MasterProduksiForm map = new MasterProduksiForm();
		desktopPane.add(map);
		Dimension screen = this.getSize();
		Dimension frame = map.getSize();
		map.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		map.setVisible(true);
	}
	
	private void masterDanaActionPerformed(ActionEvent e)
	{
		MasterDanaForm mad = new MasterDanaForm();
		desktopPane.add(mad);
		Dimension screen = this.getSize();
		Dimension frame = mad.getSize();
		mad.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		mad.setVisible(true);
	}
	
	private void laporanActionPerformed(MouseEvent e)
	{
		LaporanForm lap = new LaporanForm();
		desktopPane.add(lap);
		Dimension screen = this.getSize();
		Dimension frame = lap.getSize();
		lap.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		lap.setVisible(true);
	}

	protected void setBackgroundImage() {
		try {
			ImageIcon icon = new ImageIcon(new ImageIcon("/home/dery/workspace/Java/Invoice/image/aft.jpg").getImage());
			JLabel label = new JLabel(icon);
			label.setBounds(350, 35, icon.getIconWidth(), icon.getIconHeight());

			desktopPane.add(label, new Integer(Integer.MIN_VALUE));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}