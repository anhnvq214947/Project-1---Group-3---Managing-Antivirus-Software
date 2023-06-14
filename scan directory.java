import org.clamav4j.ClamScan;
import org.clamav4j.ClamScanOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClamAVExample {
    public static void main(String[] args) throws Exception {
        // Khởi tạo ClamScan object
        ClamScan clam = new ClamScan();

        // Tạo đối tượng ClamScanOptions để thiết lập các tùy chọn quét virus
        ClamScanOptions options = new ClamScanOptions();
        options.setFilename("/path/to/clamd.sock");  // Đặt đường dẫn đến socket của ClamAV

        // Thiết lập thư mục cần quét
        Path directoryToScan = Paths.get("/path/to/directory");

        // Lấy danh sách các file trong thư mục
        Files.walk(directoryToScan)
                .filter(path -> !Files.isDirectory(path))  // Loại bỏ các thư mục con
                .forEach(path -> {
                    // Quét từng file và hiển thị kết quả
                    boolean isInfected = clam.scan(path.toFile(), options);
                    if (isInfected) {
                        System.out.println("Virus detected in file: " + path.toString());
                    }
                });
    }
}
