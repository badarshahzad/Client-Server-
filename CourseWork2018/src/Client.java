import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) throws SocketTimeoutException {

		try {
			Socket client = new Socket("localhost", 2983);
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			Scanner input = new Scanner(System.in);
			
			//Timeout:
			client.setSoTimeout(1000);
			
			String passage = "This is passage. I am sending to client. My name is packet.";
			String [] pass_InPackets = passage.split(" ");
			
			for (int sequence_num=1;sequence_num<=pass_InPackets.length;){

				//Missing packet
				Random m = new Random();
				int value = m.nextInt(13);
				if (value==sequence_num) {
					System.out.println("Skip packet:"+value);
					sequence_num++;
					//packet miss 
				}
				
				// sending
				System.out.print("Client Send packet#: "+sequence_num+"\t");
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeUTF("\tHeader\t:"+String.valueOf(sequence_num)+":\tMessage: "+ pass_InPackets[sequence_num-1]);
				oos.flush();
				
				// receving
				ois = new ObjectInputStream(client.getInputStream());
				String msg = ois.readUTF();
				System.out.println(msg);
				String[] flag = msg.split(":");
				
				if(flag[0].equals("ACK")){
					sequence_num++;
				}
				if(flag[0].equals("NACK")){
					sequence_num = Integer.valueOf(flag[1]);
					System.out.println("\n $@@@@@@@$ Packet Loss $@@@@@@@$ \n");
				}
				
			}
			System.out.println("By to server");
			
			ois.close();
			oos.close();
			client.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}