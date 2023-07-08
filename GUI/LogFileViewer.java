package GUI;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import API.ClamAVService;
import API.VirusScanResult;
import API.VirusScanStatus;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;


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
                    ClamAVService clamAVService = new ClamAVService();
                    VirusScanResult scanResult = null;
                    try {
                        if (clamAVService.ping()) {
                            try (InputStream inputStream = new FileInputStream(file)) {
                                scanResult = clamAVService.scan(inputStream);
                            } catch (IOException a) {
                                scanResult = new VirusScanResult(VirusScanStatus.FAILED, a.getMessage());
                            }
                        } else {
                            scanResult = new VirusScanResult(VirusScanStatus.CONNECTION_FAILED,
                                    "ClamAV did not respond to ping request!");
                        }
                    } catch (Exception a) {
                        scanResult =
                                new VirusScanResult(VirusScanStatus.ERROR, "An error occurred while scanning file.");
                    }

                    // Display the scan result in the GUI
                    JOptionPane.showMessageDialog(ClamAVGUI.this, scanResult);

                    // Write the scan result to a file
                    String outputFileName = "scan_result.txt";
                    writeScanResultToFile(scanResult, outputFileName);
                    openWithNotepad(outputFileName);
                }
            }
        });
    }

    private void writeScanResultToFile(VirusScanResult scanResult, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(scanResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWithNotepad(String fileName) {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe", fileName);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClamAVGUI gui = new ClamAVGUI();
        gui.setVisible(true);
    }
}

