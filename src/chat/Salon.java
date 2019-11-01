package chat;

import java.util.ArrayList;
import java.util.List;

public class Salon {
	public   static List<Utilisateur> listeU=new ArrayList<Utilisateur>();;
	private String NomS ;
	public Salon(String nomS) {
		super();
		this.NomS = nomS;
	}
	public static List<Utilisateur> getListeU() {
		return listeU;
	}
	public static void setListeU(List<Utilisateur> listeU) {
		Salon.listeU = listeU;
	}
	public String getNomS() {
		return NomS;
	}
	public void setNomS(String nomS) {
		NomS = nomS;
	}
	
	

}
