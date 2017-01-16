/** This program is client program which connects with the connection to the server by opening socket to allow the user to read, and send his/her messages to server **/


import java.io.*; 
import java.net.*; 
class TCPPacketClient { 
     private static InetAddress host;
	
     private static String[] output;

    public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 
        int i=1;
        String str="";
	 
/** the instance of Socket class uses the address of this computer and the portnumber 1024 **/

        Socket clientSocket = new Socket("*****",1024); 

/** The getInputStream() method obtains a handle to the socket stream **/

	ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
/** The getOutputStream() method send out the data through the socket stream **/

        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

try{

  do  {
 
       System.out.print("Enter the data packet: ");
       
 /** This method reads from the keyboard the data (Packet) that needs to be send to the server **/

       BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
    
  str =(String)br.readLine();
       
                 /**  Create a new instance of Packet class with data's header,the data (packet) and packet number **/

        Packet  myPacket = new Packet(i,str);
        
        outToServer.writeObject(myPacket);
        sentence = (String)inFromServer.readObject(); 

        System.out.println("FROM SERVER:Packet SerialNo#" + sentence+" is recieved"); 
      i++;
  
        } while(!str.equals("CLOSE"));      
  } catch(IOException e)
  {} 
        clientSocket.close();    
    } 
} 

