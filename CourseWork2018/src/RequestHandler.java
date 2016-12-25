import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class RequestHandler extends Thread{

	Scanner input = new Scanner(System.in);
	Socket client_acp;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	String packet_num;
	static int sequence_num = 0;
	static int previous_header = 1;
	public RequestHandler(Socket client){
		client_acp = client;
	}
	
	@Override
	public void run() {
	
		
		try {
			while (true) {
				// receving
				
				ois = new ObjectInputStream(client_acp.getInputStream());
				String count = ois.readUTF();
				System.out.println(count);
				
				//Gettig header number *counter*
				String[] checksum = count.split(":");
				sequence_num= Integer.valueOf(checksum[1]);
				
				//Generating interrupt: 1 for ACK and 0 for NACk
				Random m = new Random();
				int value = m.nextInt(2);
			
				//delay in message
				delayInMessage();
				
				//sending
				if(sequence_num==previous_header && value==1){
				System.out.print("Server:");
				oos = new ObjectOutputStream(client_acp.getOutputStream());
				oos.writeUTF("ACK:"+ sequence_num );
				oos.flush();
				previous_header++;
				}else{
					System.out.print("Server:");
					oos = new ObjectOutputStream(client_acp.getOutputStream());
					oos.writeUTF("NACK:"+ previous_header);
					oos.flush();
				
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void delayInMessage(){
		if(sequence_num==3){
			for(int a=0;a<2000;a++){
			}
			System.out.println("sleep & header didn't send"+previous_header);
			
			}
	}
}