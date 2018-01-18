package com.main.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JDesktopPane desktopPane = new JDesktopPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);		
		contentPane = new JPanel();
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
		
		/*JMenu MasterData = new JMenu("Master Data");
		MasterData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				masterDataActionPerformed(e);
			}
		});
		menuBar.add(MasterData);
		
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
		mntmPoMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poMediaActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmPoMedia);
		
		JMenuItem mntmPoProduksi = new JMenuItem("PO. Produksi");
		mntmPoProduksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poProduksiActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmPoProduksi);
		
		JMenuItem mntmPoEvent = new JMenuItem("PO. Event");
		mntmPoEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poEventActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmPoEvent);
		
		JMenuItem mntmFunding = new JMenuItem("Funding");
		mntmFunding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fundingActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmFunding);
		
		JMenuItem mntmTagihanMedia = new JMenuItem("Tagihan Media");
		mntmTagihanMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tagihanMediaActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmTagihanMedia);
		
		JMenuItem mntmTagihanBiayaReimbursement = new JMenuItem("Tagihan Biaya Reimbursement");
		mntmTagihanBiayaReimbursement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tagihanReimbursementActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmTagihanBiayaReimbursement);
		
		JMenuItem mntmCostOperasional = new JMenuItem("Cost Operasional");
		mntmCostOperasional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costOperasionalActionPerformed(e);
			}
		});
		mnPurchaseOrder.add(mntmCostOperasional);
		
		JMenu mnKontrak = new JMenu("Kontrak");
		mnKontrak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				kontrakActionPerformed(e);
			}
		});
		menuBar.add(mnKontrak);
		
		JMenu mnMasterData = new JMenu("Master Data");
		menuBar.add(mnMasterData);
		
		JMenuItem mntmMasterLegalitasPerusahaan = new JMenuItem("Master Legalitas Perusahaan");
		mntmMasterLegalitasPerusahaan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterLegalitasActionPerformed(e);
			}
		});
		mnMasterData.add(mntmMasterLegalitasPerusahaan);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Master Media");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterMediaActionPerformed(e);
			}
		});
		mnMasterData.add(mntmNewMenuItem);
		
		JMenuItem mntmMasterKlien = new JMenuItem("Master Klien");
		mntmMasterKlien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterKlienActionPerformed(e);
			}
		});
		mnMasterData.add(mntmMasterKlien);
		
		JMenuItem mntmMasterEvent = new JMenuItem("Master Event");
		mntmMasterEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterEventActionPerformed(e);
			}
		});
		mnMasterData.add(mntmMasterEvent);
		
		JMenuItem mntmMasterProduksi = new JMenuItem("Master Produksi");
		mntmMasterProduksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterProduksiActionPerformed(e);
			}
		});
		mnMasterData.add(mntmMasterProduksi);
		
		JMenuItem mntmMasterDana = new JMenuItem("Master Dana");
		mntmMasterDana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterDanaActionPerformed(e);
			}
		});
		mnMasterData.add(mntmMasterDana);
		
		JMenu Laporan = new JMenu("Laporan");
		Laporan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				laporanActionPerformed(e);
			}
		});
		menuBar.add(Laporan);
		
		/*menuBar.add(Box.createHorizontalGlue());
		JMenu mnLogout = new JMenu("Logout");
		menuBar.add(mnLogout);*/
		
		//========= action ===========
		
	}
	
	private void poMediaActionPerformed(ActionEvent e) {
		POMedia pom = new POMedia();
		desktopPane.add(pom);
		Dimension screen = this.getSize();
		Dimension frame = pom.getSize();
		pom.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		pom.setVisible(true);
		}
	
	private void poProduksiActionPerformed(ActionEvent e) {
		POProduksi pop = new POProduksi();
		desktopPane.add(pop);
		Dimension screen = this.getSize();
		Dimension frame = pop.getSize();
		pop.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		pop.setVisible(true);
		}
	
	private void poEventActionPerformed(ActionEvent e) {
		POEvent poe = new POEvent();
		desktopPane.add(poe);
		Dimension screen = this.getSize();
		Dimension frame = poe.getSize();
		poe.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		poe.setVisible(true);
		}
	
	private void fundingActionPerformed(ActionEvent e) {
		Funding fun = new Funding();
		desktopPane.add(fun);
		Dimension screen = this.getSize();
		Dimension frame = fun.getSize();
		fun.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		fun.setVisible(true);
		}
	
	private void tagihanMediaActionPerformed(ActionEvent e) {
		TagihanMedia tam = new TagihanMedia();
		desktopPane.add(tam);
		Dimension screen = this.getSize();
		Dimension frame = tam.getSize();
		tam.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		tam.setVisible(true);
		}
	
	private void tagihanReimbursementActionPerformed(ActionEvent e) {
		TagihanReimbursement tar = new TagihanReimbursement();
		desktopPane.add(tar);
		Dimension screen = this.getSize();
		Dimension frame = tar.getSize();
		tar.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		tar.setVisible(true);
		}
	
	private void costOperasionalActionPerformed(ActionEvent e) {
		CostOperasional coo = new CostOperasional();
		desktopPane.add(coo);
		Dimension screen = this.getSize();
		Dimension frame = coo.getSize();
		coo.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		coo.setVisible(true);
		}
	
	private void kontrakActionPerformed(MouseEvent e) {
		Kontrak kon = new Kontrak();
		desktopPane.add(kon);
		Dimension screen = this.getSize();
		Dimension frame = kon.getSize();
		kon.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		kon.setVisible(true);
	}
	
	private void masterLegalitasActionPerformed(ActionEvent e) {
		MasterLegalitas mal = new MasterLegalitas();
		desktopPane.add(mal);
		Dimension screen = this.getSize();
		Dimension frame = mal.getSize();
		mal.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		mal.setVisible(true);
		}
	
	private void masterMediaActionPerformed(ActionEvent e) {
		MasterMedia mam = new MasterMedia();
		desktopPane.add(mam);
		Dimension screen = this.getSize();
		Dimension frame = mam.getSize();
		mam.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		mam.setVisible(true);
		}
	
	private void masterKlienActionPerformed(ActionEvent e) {
		MasterKlien mak = new MasterKlien();
		desktopPane.add(mak);
		Dimension screen = this.getSize();
		Dimension frame = mak.getSize();
		mak.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		mak.setVisible(true);
		}
	
	private void masterEventActionPerformed(ActionEvent e) {
		MasterEvent mae = new MasterEvent();
		desktopPane.add(mae);
		Dimension screen = this.getSize();
		Dimension frame = mae.getSize();
		mae.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		mae.setVisible(true);
		}
	
	private void masterProduksiActionPerformed(ActionEvent e) {
		MasterProduksi map = new MasterProduksi();
		desktopPane.add(map);
		Dimension screen = this.getSize();
		Dimension frame = map.getSize();
		map.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		map.setVisible(true);
		}
	
	private void masterDanaActionPerformed(ActionEvent e) {
		MasterDana mad = new MasterDana();
		desktopPane.add(mad);
		Dimension screen = this.getSize();
		Dimension frame = mad.getSize();
		mad.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		mad.setVisible(true);
		}
	
	private void laporanActionPerformed(MouseEvent e) {
		Laporan lap = new Laporan();
		desktopPane.add(lap);
		Dimension screen = this.getSize();
		Dimension frame = lap.getSize();
		lap.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		lap.setVisible(true);
	}
	/*private void klienActionPerformed(ActionEvent e) {
		MasterKlien kl = new MasterKlien();
		desktopPane.add(kl);
		Dimension screen = this.getSize();
		Dimension frame = kl.getSize();
		kl.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		kl.setVisible(true);
		}
	
	private void orderActionPerformed(MouseEvent arg0) {
		Order_BUKAN or = new Order_BUKAN();
		desktopPane.add(or);
		Dimension screen = this.getSize();
		Dimension frame = or.getSize();
		or.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		or.setVisible(true);
	}
	
	private void masterDataActionPerformed(MouseEvent arg0) {
		MasterData ma = new MasterData();
		desktopPane.add(ma);
		Dimension screen = this.getSize();
		Dimension frame = ma.getSize();
		ma.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/250);
		ma.setVisible(true);
	}
		
	private void legalitasActionPerformed(ActionEvent arg0) {
		MasterLegalitas le = new MasterLegalitas();
		desktopPane.add(le);
		Dimension screen = this.getSize();
		Dimension frame = le.getSize();
		le.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		le.setVisible(true);
	}
	
	private void cetakPerformed(ActionEvent arg0) {
		POMedia ce = new POMedia();
		desktopPane.add(ce);
		Dimension screen = this.getSize();
		Dimension frame = ce.getSize();
		ce.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		ce.setVisible(true);
	}
	
	private void eventPerformed(ActionEvent arg0) {
		POEvent ev = new POEvent();
		desktopPane.add(ev);
		Dimension screen = this.getSize();
		Dimension frame = ev.getSize();
		ev.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		ev.setVisible(true);
	}
	
	private void produksiPerformed(ActionEvent arg0) {
		POProduksi pr = new POProduksi();
		desktopPane.add(pr);
		Dimension screen = this.getSize();
		Dimension frame = pr.getSize();
		pr.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		pr.setVisible(true);
	}
	
	private void buktiTayangPerformed(ActionEvent arg0) {
		BuktiTayang_BUKAN bt = new BuktiTayang_BUKAN();
		desktopPane.add(bt);
		Dimension screen = this.getSize();
		Dimension frame = bt.getSize();
		bt.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		bt.setVisible(true);
	}
	
	private void invoicePerformed(ActionEvent arg0) {
		Invoicing_BUKAN in = new Invoicing_BUKAN();
		desktopPane.add(in);
		Dimension screen = this.getSize();
		Dimension frame = in.getSize();
		in.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		in.setVisible(true);
	}
	
	private void kontrakPerformed(ActionEvent arg0) {
		Kontrak_BUKAN ko = new Kontrak_BUKAN();
		desktopPane.add(ko);
		Dimension screen = this.getSize();
		Dimension frame = ko.getSize();
		ko.setLocation((screen.width - frame.width)/2,
				(screen.height=frame.height)/25);
		ko.setVisible(true);
	}*/
}	

