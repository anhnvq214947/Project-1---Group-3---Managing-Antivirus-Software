import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClamAVScanner {
    /**
     * This function scans the system using ClamAV antivirus
     *
     * @return String: The result of the scan
     */
    public static String clamAVScanSystem() {
        try {
            // Run the ClamAV scan command
            Process process = Runtime.getRuntime().exec("clamscan -r /");

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitVal = process.waitFor();

            // Return the output of the command
            return output.toString();
        } catch (IOException | InterruptedException e) {
            // Log the error
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }
}
