import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Server {

	public static void main(String args[]) {
		try {
			ServerSocket server_socket = new ServerSocket(2983);
			System.out.println("Waiting for client request");
			int client_client = 0;
			while (true) {
				Socket client = server_socket.accept();
				System.out.println("Accepted connection request = "+client_client);
				RequestHandler obj = new RequestHandler(client);
				obj.start();
				client_client++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// Scanner input = new Scanner(System.in);
	}
}
