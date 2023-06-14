import org.clamav4j.*;

public class ClamAVExample {
    public static void main(String[] args) throws Exception {
        String filename = "/path/to/my/file.txt";

        // Create a new ClamScan instance
        ClamScan clamScan = new ClamScan();

        // Set the path to the ClamAV daemon and virus definition database
        clamScan.setHost("localhost");
        clamScan.setPort(3310);
        clamScan.setTimeout(60000);
        clamScan.setMaxChunkSize(262144);
        
        // Scan the file
        ScanResult result = clamScan.scan(filename);

        // Print the results
        System.out.println("File: " + filename);
        System.out.println("Result: " + result.getStatus());
        System.out.println("Message: " + result.getMessage());
        System.out.println("Virus Name: " + result.getVirusName());
    }
}
