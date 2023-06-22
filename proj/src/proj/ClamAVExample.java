package proj;

import java.io.FileInputStream;
import java.io.IOException;

public class ClamAVExample {
    public static void main(String[] args) throws IOException {
        ClamAVClient client = new ClamAVClient("localhost", 3310);
        try (FileInputStream inputStream = new FileInputStream("/home/tung/clamav-test/test.txt")) {
            boolean result = client.scanFile(inputStream);
            if (result) {
                System.out.println("File is clean");
            } else {
                System.out.println("File is infected");
            }
        }
    }
}