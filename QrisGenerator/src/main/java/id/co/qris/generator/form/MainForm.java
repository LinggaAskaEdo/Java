package id.co.qris.generator.form;

import id.co.qris.generator.service.QrisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

@Component
public class MainForm extends JFrame
{
    private static final long serialVersionUID = 6903871150795176223L;

    private final transient QrisService qrisService;

    private final JDesktopPane desktopPane = new JDesktopPane();
    private final JTextField txtSource = new JTextField();
    private final JTextField txtDestination = new JTextField();
    private final JButton btnSource = new JButton("...");
    private final JButton btnDestination = new JButton("...");
    private final JButton btnGenerate = new JButton("Generate");
    private final JButton btnCancel = new JButton("Cancel");

    @Autowired
    public MainForm(QrisService qrisService)
    {
        this.qrisService = qrisService;

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
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XLS files", "xls", "xlsx"));
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

        btnGenerate.setBounds(183, 186, 117, 25);
        desktopPane.add(btnGenerate);
        btnGenerate.addActionListener(e ->
        {
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
                boolean result = qrisService.generateQris(txtSource.getText(), txtDestination.getText());

                if (result)
                    JOptionPane.showMessageDialog(null, "Generate QRIS PDF success !!!", "", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Generate QRIS PDF fail !!!", "", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCancel.setBounds(381, 186, 117, 25);
        desktopPane.add(btnCancel);
        btnCancel.addActionListener(e ->
        {
            txtSource.setText("");
            txtDestination.setText("");
            txtSource.requestFocusInWindow();
        });
    }

    private void centerForm()
    {
        this.setLocationRelativeTo(getRootPane());
    }
}