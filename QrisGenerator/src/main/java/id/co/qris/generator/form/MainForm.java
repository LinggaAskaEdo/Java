package id.co.qris.generator.form;

import id.co.qris.generator.preference.ConfigPreference;
import id.co.qris.generator.preference.ConstantPreference;
import id.co.qris.generator.service.QrisService;
import id.co.qris.generator.util.QrisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Objects;

@Component
public class MainForm extends JFrame
{
    private static final long serialVersionUID = 6903871150795176223L;
    private final String[] type = { ConstantPreference.TYPE_WECHAT, ConstantPreference.TYPE_CIMB };
    private final String[] size = { ConstantPreference.SIZE_A4, ConstantPreference.SIZE_A5, ConstantPreference.SIZE_A6 };

    private final transient QrisService qrisService;
    private final transient ConfigPreference preference;
    private final transient QrisUtil util;

    private final JDesktopPane desktopPane = new JDesktopPane();
    private final JComboBox<String> comboBoxType = new JComboBox<>(type);
    private final JTextField txtSource = new JTextField();
    private final JTextField txtDestination = new JTextField();
    private final JButton btnSource = new JButton("...");
    private final JButton btnDestination = new JButton("...");
    private final JComboBox<String> comboBoxSize = new JComboBox<>(size);
    private final JButton btnGenerate = new JButton("Generate");
    private final JButton btnCancel = new JButton("Cancel");

    @Autowired
    public MainForm(QrisService qrisService, ConfigPreference preference, QrisUtil util)
    {
        this.qrisService = qrisService;
        this.preference = preference;
        this.util = util;

        setResizable(false);
        setTitle("QRIS Generator");
        initializeForm();
        centerForm();
    }

    private void initializeForm()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(450, 250, 651, 300);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(desktopPane);
        desktopPane.setLayout(null);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(22, 36, 49, 15);
        desktopPane.add(lblType);

        comboBoxType.setBounds(113, 31, 138, 27);
        desktopPane.add(comboBoxType);

        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(22, 68, 49, 15);
        desktopPane.add(lblSource);

        txtSource.setBounds(113, 65, 459, 19);
        txtSource.setColumns(10);
        desktopPane.add(txtSource);

        btnSource.setBounds(584, 65, 30, 19);
        desktopPane.add(btnSource);
        btnSource.addActionListener(e ->
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter extensionFilter;

            if (Objects.requireNonNull(comboBoxType.getSelectedItem()).toString().equalsIgnoreCase("WeChat"))
                extensionFilter = new FileNameExtensionFilter("XLS files", "xls", "xlsx");
            else
                extensionFilter = new FileNameExtensionFilter("CSV files", "csv");

            fileChooser.addChoosableFileFilter(extensionFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                txtSource.setText(selectedFile.getAbsolutePath());
            }
        });

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(22, 95, 79, 15);
        desktopPane.add(lblDestination);

        txtDestination.setBounds(113, 92, 459, 19);
        txtDestination.setColumns(10);
        desktopPane.add(txtDestination);

        btnDestination.setBounds(584, 92, 30, 19);
        desktopPane.add(btnDestination);
        btnDestination.addActionListener(e ->
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedDirectory = fileChooser.getSelectedFile();
                txtDestination.setText(selectedDirectory.getAbsolutePath());
            }
        });

        JLabel lblSize = new JLabel("Size");
        lblSize.setBounds(22, 122, 49, 15);
        desktopPane.add(lblSize);

        comboBoxSize.setBounds(113, 117, 138, 27);
        desktopPane.add(comboBoxSize);

        btnGenerate.setBounds(380, 207, 117, 25);
        desktopPane.add(btnGenerate);
        btnGenerate.addActionListener(e ->
        {
            System.out.println(util.decryptKey(preference.appKey));

            if (txtSource.getText().isEmpty())
            {
                txtSource.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Please select source data !!!", "", JOptionPane.WARNING_MESSAGE);
            }
            else if (txtDestination.getText().isEmpty())
            {
                txtDestination.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Please select destination result !!!", "", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                boolean result = qrisService.generateQris(Objects.requireNonNull(comboBoxType.getSelectedItem()).toString(), txtSource.getText(), txtDestination.getText(), Objects.requireNonNull(comboBoxSize.getSelectedItem()).toString());

                if (result)
                    JOptionPane.showMessageDialog(null, "Generate QRIS PDF success !!!", "", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Generate QRIS PDF fail !!!", "", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCancel.setBounds(497, 207, 117, 25);
        desktopPane.add(btnCancel);
        btnCancel.addActionListener(e ->
        {
            txtSource.setText("");
            txtDestination.setText("");
            comboBoxType.setSelectedIndex(0);
            comboBoxSize.setSelectedIndex(0);
            txtSource.requestFocusInWindow();
        });
    }

    private void centerForm()
    {
        this.setLocationRelativeTo(getRootPane());
    }
}