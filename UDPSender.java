import java.net.*;
import java.util.Scanner;

public class UDPSender {

    public static void main(String[] args) 
        {
       // Check the arguments
              if( args.length != 2 )
              {
                  System.out.println( "usage: java UDPSender host port" ) ;
    return ;
    }
    DatagramSocket socket = null ;
    try
    {
                  // Convert the arguments first, to ensure that they are valid
                 InetAddress host = InetAddress.getByName( args[0] ) ;
                 int port         = Integer.parseInt( args[1] ) ;
                 socket = new DatagramSocket() ;

                 Scanner in;
                 in = new Scanner (System.in);
                 int Num_messages;
        while (true)
        {
                     System.out.println("Enter number of messages to be sent, ENTER to send ");
                                 Num_messages = Integer.parseInt(in.nextLine());
                                 if (Num_messages==0) break;
                                 byte [] data = null;

                                 for (int n = 0; n < Num_messages; n++) {
                                     data = ("message" + n).getBytes();
                                         DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
                                         socket.send( packet );
                                         DatagramPacket echoPacket = new DatagramPacket( new byte[100], 100 ) ;
                                         socket.receive( echoPacket );
                                         String echoData = new String(echoPacket.getData()).trim();
                                         if (echoData.compareTo("ACK: message" + n) == 0) {
                                             System.out.println("echo recieved.");
                                                 } else {
                                             System.out.println("No echo.");
                                         }
                                 }

                 } 
        System.out.println ("Closing down");
    }
    catch( Exception e )
    {
                  System.out.println( e ) ;
              }
    finally
    {
                  if( socket != null )
                 socket.close() ;
              }
    }
}