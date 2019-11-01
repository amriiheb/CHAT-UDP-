package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UtilisateurReceive extends Thread {
	DatagramSocket sc ;

	public UtilisateurReceive(DatagramSocket sc) {
		super();
		this.sc = sc;
	}
	
	public void run() {
		
		
		try {
			while(true) {
			DatagramPacket pk= new DatagramPacket(new  byte[1024], 1024);
			sc.receive(pk);
			System.out.println(new String(pk.getData(),0,pk.getData().length));}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
