import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 7777);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);
        String msg, reply;

        while (true) {
            System.out.print("Enter message: ");
            msg = sc.nextLine();

            dos.writeUTF(msg);
            reply = dis.readUTF();
            System.out.println("Server: " + reply);

            if (msg.equalsIgnoreCase("exit")) {
                break;
            }
        }

        s.close();
        sc.close();
    }
}
