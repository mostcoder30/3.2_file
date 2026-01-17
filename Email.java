//elfc vgsk ovos ukf
import java.io.*;
import javax.net.ssl.*;
import java.util.*;

class Email {

  private static DataOutputStream dos;
  public static BufferedReader br;

  public static void main(String argv[]) throws Exception {
    String user = "s2112376130@ru.ac.bd";
    String pass = "password";

    String username = new String(Base64.getEncoder().encode(user.getBytes()));
    String password = new String(Base64.getEncoder().encode(pass.getBytes()));
    SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
    dos = new DataOutputStream(s.getOutputStream());
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));

    System.out.println("SERVER: " + br.readLine()); // Read initial greeting

    send("EHLO smtp.gmail.com\r\n");
    String line;
    while ((line = br.readLine()) != null) {
      System.out.println("SERVER: " + line);
      if (line.startsWith("250 "))
        break; // Last line has no hyphen
    }

    send("AUTH LOGIN\r\n");
    System.out.println("SERVER: " + br.readLine());

    send(username + "\r\n");
    System.out.println("SERVER: " + br.readLine());

    send(password + "\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("MAIL FROM:<s2112376130@ru.ac.bd>\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("RCPT TO:<rimacse30.ru@gmail.com>\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("DATA\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("FROM: s2112376130@ru.ac.bd\r\n");
    send("TO: rimacse30.ru@gmail.com\r\n");
    send("Subject: Send Test Email" + "\r\n");
    send("\r\n"); // Blank line required between headers and body
    send("Typing the sending message Here........\r\n");
    send(".\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("QUIT\r\n");
    System.out.println("SERVER: " + br.readLine());

  }

  private static void send(String s) throws Exception {
    dos.writeBytes(s);
    Thread.sleep(1000);
    System.out.println("CLIENT: " + s);
  }
}