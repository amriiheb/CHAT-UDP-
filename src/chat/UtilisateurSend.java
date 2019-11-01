package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UtilisateurSend extends Thread {
	DatagramSocket sc ;
	String pseudo;
public UtilisateurSend(DatagramSocket sc, String pseudo)  {
		super();
		this.sc = sc;
		this.pseudo = pseudo;
}
public void run() {
	try {
			while(true) {
				BufferedReader inClavier = new BufferedReader(new InputStreamReader(System.in));
                String msg = inClavier.readLine();
                InetAddress adr=InetAddress.getLocalHost();//InetAddress.getByName("192.168.2.140") ;
				DatagramPacket pk = new DatagramPacket(msg.getBytes(), msg.length(),adr,9898);
				sc.send(pk);  
				
			}
			
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
