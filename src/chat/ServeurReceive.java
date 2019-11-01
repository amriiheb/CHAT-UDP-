package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurReceive extends Thread {
	DatagramSocket sc ;
	DatagramSocket sc1 ;
	byte[] data = new byte[1024];
	String msg ;
	String msg1="" ;
	
	public ServeurReceive(DatagramSocket sc) {
		super();
		this.sc = sc;
		
	}


	public void run() {
			
			try {
				while(true) {
				DatagramPacket pk = new DatagramPacket(data, 1024);

				sc.receive(pk);
				String verif = new String(pk.getData(),0,pk.getLength());
				if(verif.startsWith("##")) {
					ServeurUDP.liste.add(new Utilisateur(verif.substring(2,verif.length()), pk.getAddress(), pk.getPort()));
					System.out.println("utilisateur ajoute "+verif.substring(2, verif.length()));
					msg="utilisateur ajoute "+verif.substring(2, verif.length()) ;
					DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), pk.getAddress(), pk.getPort());
					sc.send(pk1);
				}
				else if(verif.startsWith("#LIST")) {
					for(int i=0;i<ServeurUDP.liste.size();i++) {
						msg1=msg1+ServeurUDP.liste.get(i).getPseudo() ;
						
					}
					DatagramPacket pk1 = new DatagramPacket(msg1.getBytes(), msg1.length(), pk.getAddress(), pk.getPort());
					sc.send(pk1);					
				}
				else if(verif.startsWith("#STATUS#")) {
					for(int i=0;i<ServeurUDP.liste.size();i++) {
						
					    if(ServeurUDP.liste.get(i).getAdr().equals(pk.getAddress())) {
                        	ServeurUDP.liste.get(i).setStatut(verif.substring(8,verif.length()));
                        	msg="votre status est "+ServeurUDP.liste.get(i).getStatut();
        					DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), pk.getAddress(), pk.getPort());
        					sc.send(pk1);
                        }
                     
					}
					
				}
               else if(verif.startsWith("#*")) {
            	   for(int i=0;i<ServeurUDP.liste.size();i++) {
						if(ServeurUDP.liste.get(i).getAdr().equals(pk.getAddress())) {
							ServeurUDP.liste.get(i).setPseudo(verif.substring(3,verif.length()));
							msg="votre nouveau  pseudo est"+ServeurUDP.liste.get(i).getPseudo();
        					DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), pk.getAddress(), pk.getPort());
        					sc.send(pk1);
					    }
            	   }
					
				}
               else if(verif.startsWith("#SALONS")) {
            	   for(int i=0;i<ServeurUDP.salonList.size();i++) {
						msg1=msg1+ServeurUDP.salonList.get(i).getNomS();
						
					}
					DatagramPacket pk1 = new DatagramPacket(msg1.getBytes(), msg1.length(), pk.getAddress(), pk.getPort());
					sc.send(pk1);		
            	   
               }
               else if(verif.startsWith("#SALON#")) {
            	   String salonN=verif.substring(7,verif.length()) ;
            	   System.out.println(salonN);
            	   ServeurUDP.salonList.add(new Salon(salonN)) ;
            	   msg="nouvelle salon ajouter" ;
					DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), pk.getAddress(), pk.getPort());
					sc.send(pk1);
            	   
               }
               else if(verif.startsWith("#>")) {
            	   for(int i=0;i<ServeurUDP.salonList.size();i++) {
            		   if(ServeurUDP.salonList.get(i).getNomS().contentEquals(verif.substring(2,verif.length()))) {
       					ServeurUDP.salonList.get(i).getListeU().add(new Utilisateur(verif.substring(2,verif.length()), pk.getAddress(), pk.getPort()));

       					msg="tu est ajouté a cette salon" ;
    					DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), pk.getAddress(), pk.getPort());
    					sc.send(pk1);
            		   }
            	   }

            	   
            	   
               }
               else if(verif.startsWith("#USERS#")) {
            	   for(int i=0;i<ServeurUDP.salonList.size();i++) {
						if(ServeurUDP.salonList.get(i).getNomS().equals(verif.substring(7,verif.length()))) {
							for(int j=0;i<ServeurUDP.salonList.get(i).getListeU().size();j++) {
								msg1=msg1+ServeurUDP.salonList.get(i).getListeU().get(j).getPseudo() ;
								
							}
							DatagramPacket pk1 = new DatagramPacket(msg1.getBytes(), msg1.length(), pk.getAddress(), pk.getPort());
							sc.send(pk1);	
							
						}
							
						}
            	   
            	   
               }
               else if(verif.startsWith("@>")) {
            	   String SEPARATEUR = "@>";
                   String mots[] = verif.substring(2,verif.length()).split(SEPARATEUR);
                   msg=mots[1] ;
                   for(int i=0;i<ServeurUDP.salonList.size();i++) {
                	   if(ServeurUDP.salonList.get(i).getNomS().equals(mots[0])) {
                		   for(int j=0;j<ServeurUDP.salonList.get(i).getListeU().size();j++) {
                			   DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), ServeurUDP.salonList.get(i).getListeU().get(j).getAdr(), ServeurUDP.salonList.get(i).getListeU().get(j).getPort());
								sc.send(pk1);
                		   }
                	   }
                   }
               }
               else if(verif.startsWith("@#")) {
            	   String SEPARATEUR = "@#";
                   String mots[] = verif.substring(2,verif.length()).split(SEPARATEUR);
                   
                   System.out.println(mots[1]);
                   msg=mots[1] ;
                   for(int i=0;i<ServeurUDP.liste.size();i++) {
					    if(ServeurUDP.liste.get(i).getPseudo().equals(mots[0])) {
					    	 DatagramPacket pk1 = new DatagramPacket(msg.getBytes(), msg.length(), ServeurUDP.liste.get(i).getAdr(), ServeurUDP.liste.get(i).getPort());
								sc.send(pk1);
					    	
					    }
                   }
                   
                  
                   
               }
				
				
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
