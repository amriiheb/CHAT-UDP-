package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UtilisateurUDP {
public static void main(String[] args) {
		try {
			
			DatagramSocket sc = new DatagramSocket();
			BufferedReader inClavier = new BufferedReader(new InputStreamReader(System.in));
			String msg ="";
			do {
			msg = inClavier.readLine();
			}while(!msg.startsWith("##"));
            InetAddress adr=InetAddress.getLocalHost();//InetAddress.getByName("192.168.2.140") ;
			DatagramPacket pk = new DatagramPacket(msg.getBytes(),msg.length() , adr, 9898);
			sc.send(pk);
			String pseudo = msg.substring(2, msg.length());
			UtilisateurSend e1 = new UtilisateurSend(sc, pseudo);
			UtilisateurReceive e2 = new UtilisateurReceive(sc);
			
			e1.start();
			e2.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

}
