package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ServeurUDP {
public   static List<Utilisateur> liste;
public static List <Salon> salonList ;

	
	public static void main(String[] args) throws UnknownHostException {
				
				try {
					liste = new ArrayList<Utilisateur>();
					salonList=new ArrayList<Salon>();
					DatagramSocket sc = new DatagramSocket(9898);
					ServeurReceive ur = new ServeurReceive(sc);
					ur.start() ;
					
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

}
}
