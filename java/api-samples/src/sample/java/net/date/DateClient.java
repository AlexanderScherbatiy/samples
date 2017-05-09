package sample.java.net.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by alexsch on 5/9/2017.
 */
public class DateClient {
    public static void main(String[] args) throws IOException {

        String serverAddress = args.length > 0 ? args[0] : "localhost";

        try (Socket socket = new Socket(serverAddress, 9090);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ) {
            String date = input.readLine();
            System.out.printf("Date: %s\n", date);
        }
    }
}