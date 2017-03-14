package itech.form;

import itech.funct.SavePendaftaran;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class Sample extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup grpSatu = new ButtonGroup();
	private JTextField textField;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Sample frame = new Sample();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Sample()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox<?> comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Islam", "Kristen", "Dll"}));
		comboBox.setBounds(10, 11, 149, 20);
		contentPane.add(comboBox);
		
		final JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(10, 118, 648, 318);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		lblFoto.setBorder(border);
		contentPane.add(lblFoto);
		
		JButton btnNewButton = new JButton("Get Text");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String agama = (String) comboBox.getSelectedItem();
				JOptionPane.showMessageDialog(null, agama);			
				
				ImageIcon tinyPicture = new ImageIcon("bbb.jpg");
				Image image = tinyPicture.getImage();
				image = image.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_FAST);
				tinyPicture.setImage(image);
				lblFoto.setIcon(tinyPicture);
							
				/*Component[] components = contentPane.getComponents(); 
				Component component = null; 
				for (int i = 0; i < components.length; i++) 
				{ 
					component = components[i]; 
					if (component instanceof JLabel) 
					{ 
						//your code for changing color here 
						JOptionPane.showMessageDialog(null, "Ada");	           	
					} 
				} */
			}
		});
		btnNewButton.setBounds(169, 10, 89, 23);
		contentPane.add(btnNewButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("a");
		grpSatu.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 51, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("b");
		grpSatu.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(6, 77, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);		
		
		textField = new JTextField();
		textField.setBounds(291, 11, 149, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cek Email");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				boolean result = textField.getText().matches(emailreg);			
				if(result)
					JOptionPane.showMessageDialog(null, "Benar");	  
				else
					JOptionPane.showMessageDialog(null, "Salah");	  
			}
		});
		btnNewButton_1.setBounds(450, 10, 208, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save Pendaftaran");
		btnNewButton_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				SavePendaftaran sp = new SavePendaftaran();
				try 
				{
					//sp.saveDataDiri();
					//sp.saveDataLain();
					sp.saveDataSekolah();
					sp.saveDataSumber();
				}
				catch (Exception e1) 
				{					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(450, 38, 208, 23);
		contentPane.add(btnNewButton_2);
	}
}
