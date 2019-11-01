package chat;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Utilisateur {
	 String Pseudo ;
	 InetAddress adr;
	 int port;
	 String statut ;
	public String getPseudo() {
		return Pseudo;
	}
	public Utilisateur(String pseudo, InetAddress adr, int port) {
		super();
		this.Pseudo = pseudo;
		this.adr = adr;
		this.port = port;
		this.statut="connected" ;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
	}
	public InetAddress getAdr() {
		return adr;
	}
	public void setAdr(InetAddress adr) {
		this.adr = adr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
