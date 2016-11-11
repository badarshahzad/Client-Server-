
//package sockets_lab_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) {
		String recmsg;
		String sendmsg;
		Socket client;

		Scanner input = new Scanner(System.in);
		try {

			//Making conenction with server
			client = new Socket("localhost", 2225);
			System.out.println("Request sent successfully");
			boolean b = true;
			
			while (b == true) {

				// server msg receive!
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				recmsg = br.readLine();
				System.out.println(recmsg);

				// Client msg sending to server!
				PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
				// pw.println(args[0]);
				System.out.print("Cient: ");
				sendmsg = input.nextLine();
				pw.println("Client: " + sendmsg);

				if (sendmsg.equals("exit")) {
					b = false;
					client.close();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
