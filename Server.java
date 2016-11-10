package sockets_lab_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	public static void main(String args[]) {
		
		try {
			
			ServerSocket ss = new ServerSocket(2222);
			System.out.println("Waiting for client request");
			Socket client = ss.accept();
			System.out.println("Accepted connection request");
			Scanner input = new Scanner(System.in);
			while (true) {

				// receving
				InputStreamReader isr = new InputStreamReader(client.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String str = br.readLine();

				// sending
				PrintStream ps = new PrintStream(client.getOutputStream());
				ps.println("Server1: " + input.nextLine());
				System.out.println(ps);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
