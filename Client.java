package sockets_lab_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) {
		Socket client ;
		PrintWriter pw ;
		Scanner input;
		try {
			client = new Socket("localhost", 2222);
			pw = new PrintWriter(client.getOutputStream(), true);
			input = new Scanner(System.in);
			boolean a = true;
			while (a) {
				pw.println("Client0: " + input.nextLine());
				System.out.println(pw);
				// System.out.println("Request sent successfully");
				
				//receving
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				System.out.println(br.readLine());
				if (input.equals("q")) {
					a = false;
					client.close();
				}
				
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
