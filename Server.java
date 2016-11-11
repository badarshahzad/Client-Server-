package sockets_lab_1;

//package sockets_lab_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MTServer {

	public static void main(String args[]) {
		ServerSocket client;
		Socket socket;
		// CommThread ct;
		Scanner input = new Scanner(System.in);
		String sendmsg;
		String recmsg;
		int requests = 0;
		try {
			
			//Waiting for client
			System.out.println("Waiting for client request");
			client = new ServerSocket(2225);
			socket = client.accept();
			//accept and Now connected with client!
			System.out.println("New client is pop up!");
			
			//True bcz server always remain open for clients
			while (true) {
				
				//Server msg Send to client
				System.out.print("Server: ");
				sendmsg = input.nextLine();
				PrintStream ps = new PrintStream(socket.getOutputStream());
				ps.println("Server: " + sendmsg);

				// client msg receive that he/she send!
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				recmsg = br.readLine();
				System.out.println(recmsg);

				// System.out.println("Accepted Request# "+requests);
				// ct=new CommThread(client);
				// ct.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
