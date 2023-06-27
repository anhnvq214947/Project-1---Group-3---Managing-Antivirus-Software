import java.io.*;
import java.net.*;

public class ClamAVScanner {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3310);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("zINSTREAM\0");
            writer.flush();

            for(File file: File.listRoots()) {
                scanAllFiles(file, socket, reader, writer);
            }

            writer.println("zEND\0");
            writer.flush();

            String response = reader.readLine();

            if(response.equals("stream: OK")) {
                System.out.println("All files are clean");
            } else {
                System.out.println("One or more files are infected");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanAllFiles(File file, Socket socket, BufferedReader reader, PrintWriter writer) throws IOException {
        if(file.isDirectory()) {
            for(File f: file.listFiles()) {
                scanAllFiles(f, socket, reader, writer);
            }
        } else {
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            out.write(new byte[1]);
            out.flush();

            String response = reader.readLine();

            if(response.equals("stream: OK")) {
                System.out.println("File is clean: " + file.getAbsolutePath());
            } else {
                System.out.println("File is infected: " + file.getAbsolutePath());
            }

            in.close();
        }
    }
}