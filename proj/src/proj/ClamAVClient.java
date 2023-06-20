package proj;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClamAVClient {

    private String host;
    private int port;

    public ClamAVClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean scanFile(InputStream inputStream) throws IOException {
        try (Socket socket = new Socket(host, port)) {
            byte[] buffer = new byte[2048];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                socket.getOutputStream().write(buffer, 0, read);
            }
            socket.getOutputStream().write(new byte[0]);
            return getResult(socket);
        }
    }

    private boolean getResult(Socket socket) throws IOException {
        byte[] result = new byte[4];
        if (socket.getInputStream().read(result) != -1) {
            int resultCode = ((result[0] & 0xff) << 24) | ((result[1] & 0xff) << 16) | ((result[2] & 0xff) << 8) | (result[3] & 0xff);
            return resultCode == 0;
        } else {
            throw new IOException("Could not read result from ClamAV server");
        }
    }
}