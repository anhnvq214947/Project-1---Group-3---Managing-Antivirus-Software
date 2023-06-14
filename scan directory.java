import java.io.File;
import java.util.List;

import org.clamav4j.*;

public class ClamAVExample {
    public static void main(String[] args) throws Exception {
        String dirPath = "/path/to/my/directory";

        // Create a new ClamScan instance
        ClamScan clamScan = new ClamScan();

        // Set the path to the ClamAV daemon and virus definition database
        clamScan.setHost("localhost");
        clamScan.setPort(3310);
        clamScan.setTimeout(60000);
        clamScan.setMaxChunkSize(262144);

        // Get a list of files
