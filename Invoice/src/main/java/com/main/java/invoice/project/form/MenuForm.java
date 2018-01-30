package com.main.java.invoice.project.form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuForm extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane = new JDesktopPane();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
            try
            {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                MenuForm frame = new MenuForm();
				frame.setExtendedState(MAXIMIZED_BOTH);
				frame.setSize(screenSize);
				frame.setResizable(false);
				frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
	}

	private MenuForm()
	{
		setResizable(false);
		setTitle("Menu");
		initializeForm();
		centerForm();
	}

	private void centerForm()
	{
		this.setLocationRelativeTo(getRootPane());
	}

	private void initializeForm()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

//		desktopPane.setBounds(0, 0, 450, 300);
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem mntmKeluar = new JMenuItem("Keluar");
		file.add(mntmKeluar);

		/*JMenu Order = new JMenu("Order");
		Order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderActionPerformed(e);
			}
		});
		menuBar.add(Order);*/

		/*JMenu MasterDataForm = new JMenu("Master Data");
		MasterDataForm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				masterDataActionPerformed(e);
			}
		});
		menuBar.add(MasterDataForm);

		JMenu Menu = new JMenu("Menu");
		menuBar.add(Menu);*/


		/*JMenu mnMedia = new JMenu("Media");
		Menu.add(mnMedia);

		JMenuItem mntmCetak = new JMenuItem("Cetak");
		mntmCetak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cetakPerformed(e);
			}
		});
		mnMedia.add(mntmCetak);

		JMenuItem mntmEvent = new JMenuItem("Event");
		mntmEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventPerformed(e);
			}
		});
		mnMedia.add(mntmEvent);

		JMenuItem mntmProduksi = new JMenuItem("Produksi");
		mntmProduksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produksiPerformed(e);
			}
		});
		mnMedia.add(mntmProduksi);

		JMenu mnKeuangan = new JMenu("Keuangan");
		Menu.add(mnKeuangan);

		JMenuItem mntmBuktiTayang = new JMenuItem("Bukti Tayang");
		mntmBuktiTayang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buktiTayangPerformed(e);
			}
		});
		mnKeuangan.add(mntmBuktiTayang);

		JMenuItem mntmInvoice = new JMenuItem("Invoice");
		mntmInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoicePerformed(e);
			}
		});
		mnKeuangan.add(mntmInvoice);

		JMenuItem mntmKontrak = new JMenuItem("Kontrak");
		mntmKontrak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontrakPerformed(e);
			}
		});
		mnKeuangan.add(mntmKontrak);*/

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

		JMenuItem mntmTagihanMedia = new JMenuItem("Tagihan Media");
		mntmTagihanMedia.addActionListener(this::tagihanMediaActionPerformed);
		mnPurchaseOrder.add(mntmTagihanMedia);

		JMenuItem mntmTagihanBiayaReimbursement = new JMenuItem("Tagihan Biaya Reimbursement");
		mntmTagihanBiayaReimbursement.addActionListener(this::tagihanReimbursementActionPerformed);
		mnPurchaseOrder.add(mntmTagihanBiayaReimbursement);

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
	
	private void tagihanMediaActionPerformed(ActionEvent e)
	{
		TagihanMediaForm tam = new TagihanMediaForm();
		desktopPane.add(tam);
		Dimension screen = this.getSize();
		Dimension frame = tam.getSize();
		tam.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		tam.setVisible(true);
	}
	
	private void tagihanReimbursementActionPerformed(ActionEvent e)
	{
		TagihanReimbursementForm tar = new TagihanReimbursementForm();
		desktopPane.add(tar);
		Dimension screen = this.getSize();
		Dimension frame = tar.getSize();
		tar.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		tar.setVisible(true);
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

	/*private void klienActionPerformed(ActionEvent e)
	{
		MasterKlienForm kl = new MasterKlienForm();
		desktopPane.add(kl);
		Dimension screen = this.getSize();
		Dimension frame = kl.getSize();
		kl.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		kl.setVisible(true);
	}
	
	private void orderActionPerformed(MouseEvent arg0)
	{
		Order_BUKAN or = new Order_BUKAN();
		desktopPane.add(or);
		Dimension screen = this.getSize();
		Dimension frame = or.getSize();
		or.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		or.setVisible(true);
	}
	
	private void masterDataActionPerformed(MouseEvent arg0)
	{
		MasterDataForm ma = new MasterDataForm();
		desktopPane.add(ma);
		Dimension screen = this.getSize();
		Dimension frame = ma.getSize();
		ma.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/250);
		ma.setVisible(true);
	}
		
	private void legalitasActionPerformed(ActionEvent arg0)
	{
		MasterLegalitasForm le = new MasterLegalitasForm();
		desktopPane.add(le);
		Dimension screen = this.getSize();
		Dimension frame = le.getSize();
		le.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		le.setVisible(true);
	}
	
	private void cetakPerformed(ActionEvent arg0)
	{
		POMediaForm ce = new POMediaForm();
		desktopPane.add(ce);
		Dimension screen = this.getSize();
		Dimension frame = ce.getSize();
		ce.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		ce.setVisible(true);
	}
	
	private void eventPerformed(ActionEvent arg0)
	{
		POEventForm ev = new POEventForm();
		desktopPane.add(ev);
		Dimension screen = this.getSize();
		Dimension frame = ev.getSize();
		ev.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		ev.setVisible(true);
	}
	
	private void produksiPerformed(ActionEvent arg0)
	{
		POProduksiForm pr = new POProduksiForm();
		desktopPane.add(pr);
		Dimension screen = this.getSize();
		Dimension frame = pr.getSize();
		pr.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		pr.setVisible(true);
	}
	
	private void buktiTayangPerformed(ActionEvent arg0)
	 {
		BuktiTayang_BUKAN bt = new BuktiTayang_BUKAN();
		desktopPane.add(bt);
		Dimension screen = this.getSize();
		Dimension frame = bt.getSize();
		bt.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		bt.setVisible(true);
	}
	
	private void invoicePerformed(ActionEvent arg0)
	{
		Invoicing_BUKAN in = new Invoicing_BUKAN();
		desktopPane.add(in);
		Dimension screen = this.getSize();
		Dimension frame = in.getSize();
		in.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		in.setVisible(true);
	}
	
	private void kontrakPerformed(ActionEvent arg0)
	{
		Kontrak_BUKAN ko = new Kontrak_BUKAN();
		desktopPane.add(ko);
		Dimension screen = this.getSize();
		Dimension frame = ko.getSize();
		ko.setLocation((screen.width - frame.width)/2, (screen.height=frame.height)/25);
		ko.setVisible(true);
	}*/
}