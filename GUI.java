import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntivirusGUI {
    private JFrame frame;
    private JLabel statusLabel;
    private JButton scanButton;

    public AntivirusGUI() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Antivirus Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusLabel = new JLabel("Status: Ready");

        scanButton = new JButton("Scan");
        scanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform scanning logic here
                // Update statusLabel accordingly
                statusLabel.setText("Status: Scanning...");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(scanButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AntivirusGUI();
            }
        });
    }
}
