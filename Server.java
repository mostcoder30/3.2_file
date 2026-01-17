import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Server started on port 7777");

        Socket s = ss.accept();
        System.out.println("Client connected");

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String msg;

        while (true) {
            msg = dis.readUTF();
            msg = msg.toLowerCase();

            if (msg.equals("date")) {
                dos.writeUTF("Date: " + new Date().toString());

            } else if (msg.equals("time")) {
                dos.writeUTF("Time: " + new Date().toString().split(" ")[3]);

            } else if (msg.equals("ip")) {
                dos.writeUTF("Server IP: " + s.getLocalAddress());

            } else if (msg.equals("hello") || msg.equals("hi")) {
                dos.writeUTF("Hello Client! Nice to meet you! ");

            } else if (msg.equals("exit")) {
                dos.writeUTF("Connection closed. Goodbye!");
                break;

            } else {
                dos.writeUTF("Unknown message");
            }
        }

        s.close();
        ss.close();
    }
}
