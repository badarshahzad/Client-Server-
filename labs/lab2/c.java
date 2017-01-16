/** This program is Server program which connects with the connection to the Client by opening Serversocket to allow the server to read, and send the messages to Client **/

import java.io.*; 
import java.net.*; 



class TCPPacketServer { 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 
      String capitalizedSentence; 
      Packet  packet;

     System.out.println("Opening port...\n");

      /** the instance of ServerSocket class uses the portnumber 1024 **/

    ServerSocket welcomeSocket = new ServerSocket(1024); 
  
    Socket connectionSocket = welcomeSocket.accept(); 

/** The getOutputStream() method send out the data through the socket stream **/

           ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

/** The getInputStream() method obtains a handle to the socket stream **/

           ObjectInputStream inFromClient =  new ObjectInputStream(connectionSocket.getInputStream());

try{

     
  while(( packet  = (Packet)inFromClient.readObject())!=null) { 

	 
 
          int x = packet.getSerialNo();
          
	     Integer myx = new Integer(x);
          String str = myx.toString();
           System.out.println("Recieving From Client Packet's serialNo#" + str +" \n and packet's Data is " + packet.getData());
           outToClient.writeObject(str); 

           if (packet.getData().equals("CLOSE"))
               break;
        } 
  } catch(IOException e)
  {} 
        connectionSocket.close();
    } 
} 

