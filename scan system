import org.clamav4j.ClamScan;
import org.clamav4j.ClamScanOptions;

import java.io.File;

public class ClamAVExample {
    public static void main(String[] args) throws Exception {
        // Khởi tạo ClamScan object
        ClamScan clam = new ClamScan();

        // Tạo đối tượng ClamScanOptions để thiết lập các tùy chọn quét virus
        ClamScanOptions options = new ClamScanOptions();
        options.setFilename("/path/to/clamd.sock");  // Đặt đường dẫn đến socket của ClamAV

        // Điều hướng đến thư mục gốc của hệ thống
        File rootDirectory = File.listRoots()[0];

        // Gọi phương thức quét virus cho từng file trên hệ thống
        scan(rootDirectory, clam, options);
    }

    private static void scan(File file, ClamScan clam, ClamScanOptions options) throws Exception {
        if (file.isDirectory()) {
            // Nếu là thư mục, duyệt các file con bên trong
            File[] files = file.listFiles();
            for (File child : files) {
                scan(child, clam, options);
            }
        } else {
            // Nếu là file, quét virus và hiển thị kết quả nếu có virus
            boolean isInfected = clam.scan(file, options);
            if (isInfected) {
                System.out.println("Virus detected in file: " + file.getAbsolutePath());
            }
        }
    }
}
