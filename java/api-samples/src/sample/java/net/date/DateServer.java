package sample.java.net.date;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by alexsch on 5/9/2017.
 */
public class DateServer {
    public static void main(String[] args) throws Exception {

        try (ServerSocket listener = new ServerSocket(9090)) {
            while (true) {
                try (
                        Socket socket = listener.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    out.printf("%s", new Date());
                }
            }
        }
    }
}
