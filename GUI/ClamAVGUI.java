import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import API.*;

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
                int result = fileChooser.showSaveDialog(ClamAVGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ClamAVService clamAVService = new ClamAVService();
                    VirusScanResult scanResult = null;
                    try {
                        if (clamAVService.ping()) {
                            try {
                                scanResult = clamAVService.scan(file);
                            } catch (IOException ex) {
                                scanResult = new VirusScanResult(VirusScanStatus.FAILED, ex.getMessage());
                            }
                        } else {
                            scanResult = new VirusScanResult(VirusScanStatus.CONNECTION_FAILED,
                                    "ClamAV did not respond to ping request!");
                        }
                    } catch (Exception ex) {
                        scanResult = new VirusScanResult(VirusScanStatus.ERROR, "An error occurred while scanning file.");
                    }

                    // Save the scan result to the selected file
                    String saveFilePath = file.getAbsolutePath();
                    saveScanResultToFile(scanResult, saveFilePath);

                    // Open the saved file using the default text editor in Ubuntu
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", saveFilePath);
                        processBuilder.start();
                    } catch (IOException ex) {
                        System.out.println("Error opening file: " + ex.getMessage());
                    }

                    // Display a message dialog with the saved file path
                    JOptionPane.showMessageDialog(ClamAVGUI.this, "Scan result saved to: " + saveFilePath);
                }
            }
        });
    }

    private void saveScanResultToFile(VirusScanResult scanResult, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(scanResult.toString());
        } catch (IOException e) {
            System.out.println("Error saving scan result to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClamAVGUI gui = new ClamAVGUI();
        gui.setVisible(true);
    }
}
