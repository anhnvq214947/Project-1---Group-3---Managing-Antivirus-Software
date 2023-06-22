package proj;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClamAVGUI extends JFrame {
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton scanButton;

    public ClamAVGUI() {
        setTitle("ClamAV GUI");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        statusLabel = new JLabel("Select a file to scan");
        progressBar = new JProgressBar();
        scanButton = new JButton("Scan");

        // Add components to GUI
        JPanel topPanel = new JPanel();
        topPanel.add(statusLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(scanButton);
        bottomPanel.add(progressBar);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener to scan button
        scanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display file chooser dialog
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int result = fileChooser.showOpenDialog(ClamAVGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                	File file = fileChooser.getSelectedFile();
                    String strPath = file.getAbsolutePath();
                    Path path = Paths.get(strPath);

                    // Scan the file with ClamAV
                    ClamavClient scanner = new ClamavClient("localhost",3310);
                    ScanResult scResult = scanner.scan(path);
                    
                    // Display the scan result in the GUI
//                    statusLabel.setText(scResult);
                    if (scResult instanceof ScanResult.VirusFound) {
                        JOptionPane.showMessageDialog(ClamAVGUI.this, "The file is infected!");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        ClamAVGUI gui = new ClamAVGUI();
        gui.setVisible(true);
    }
}