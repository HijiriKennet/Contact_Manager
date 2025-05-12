
import java.io.File;
import java.io.RandomAccessFile;
import java.util.*;

public class GestionnaireContacts {
	int nbrContacts=0;
	int totalCCree=0;
	List<Contact> listeContacts =new ArrayList<Contact>();
	
	public void ajoutContact (String nom, String prenom,String mail,String tel) {
		listeContacts.add(new Contact (nom,prenom,mail,tel)) ;
		nbrContacts++;
		totalCCree=nbrContacts;
	}
	
	public void afficheContacts() {
		int id=0;
		for (Contact con : listeContacts) {
			System.out.println("Client N°"+(++id)+" : "+con.toString());
		}	
	}
	
	public Contact chercheContact (String trouve) {
		for (Contact t : listeContacts) {
			if (t.getNom().contains(trouve)){
				return t;
			}
		}
		
		System.out.println("Contact "+trouve+" non trouvé...");
		return null;
	}
	
	public String chercheContact2 (String trouve) {
		try {
			      RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
			      String file = "Voici la liste des contacts : \n";
			      boolean trouver=false;
			      //boucle while pour passer ligne par ligne
			      while(file!=null){
			    	  	file = monFichier.readLine(); //passer à la ligne suivante
			    	  	trouver=file.contains(trouve);//un boolean qui récupère le true or false
			    	  	if (trouver==true) { //Si file.contains trouve trouve le texte, ceci passe en true 
	return file;
			    	  	}
			      }
			      
			      if (file==null && trouver==false) {
		    	  		System.out.println("Contact "+trouve+" non trouvé...");
		    	  }
			      monFichier.close();
		    }
		
		catch (Exception e) {
		      e.printStackTrace();
		    }
		
		
		
	return null;
		
	}

	public String rechercheContact (String trouve) {
		for (Contact t : listeContacts) {
			if (t.getNom().equals(trouve)){
				return t.toString();
			}
		}
		
		System.out.println("Contact "+trouve+" non trouvé...");
		return null;
	}

	
	public void modifierContact (String nom, String nouveau_nom, String prenom,String mail,String tel) {
		Contact t = chercheContact(nom);
		if(t!=null) {
			t.setNom(nouveau_nom);
			t.setPrenom(prenom);
			t.setMail(mail);
			t.setTel(tel);
		}
	}
	
	public boolean supprimerContact (String nom) {
		Contact t = chercheContact(nom);
		if (t!=null) {
		listeContacts.remove(listeContacts.indexOf(t));
		nbrContacts--;
		return true;
		} else {
			return false;
		}
	}
	
	// J'ai grave souffert sur cette fonction ...
	public void supprimerContact2 (String nom) {
		String trouve = chercheContact2(nom);
		nbrContacts--;
		try {
		      RandomAccessFile monFile = new RandomAccessFile("contacts.txt", "rwd");
		      String rpls= "";
		      if (trouve!=null) {
		    	  String line= monFile.readLine();
		    	  while(line!=null){
		    		  if(!line.contains(trouve)) {
		    			  rpls+=line;
		    			  
		    		  }
		    		  rpls+='\n';
		    		  line=monFile.readLine();
		    		  System.out.println(rpls+"\n");
		    		  System.out.println("ligne suivante " + line + "\n");
			      }
		    	  System.out.println("Contact "+ nom + " supprimé avec succès.");
		      }
		      else {
		    	  System.out.println("Contact "+nom+" non supprimé...");
		      }
		      monFile.close();
		      
		      //remplir le fichier avec le replacement
		      monFile = new RandomAccessFile("contacts.txt", "rwd");
		      monFile.writeBytes(rpls);
		      monFile.close();
		 } catch (Exception e) {
		      e.printStackTrace();
		 }
	}
	// voici un bout de code qui va pas beaucoup servir...
	public void reecrireContact0 () {
		 try {
			RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
			for (Contact t : listeContacts) {
		        monFichier.writeBytes(t.toString());
		        monFichier.writeBytes("\n");
		      }
			monFichier.close();
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	public void reecrireContact () {
		 try {
			RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
			for (int i=0; i<totalCCree;i++) {
				for (Contact t : listeContacts) {
			        monFichier.writeBytes(t.toString());
			        monFichier.writeBytes("\n");
			        i++;
			      }
				monFichier.writeBytes("\n");
		    }
			monFichier.writeBytes("\n");
			monFichier.close();
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	
	public void sauvegarderContact () {
	 try {
	      RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
	      String file = monFichier.readLine();
	      while(file != null){
	    	  	file = monFichier.readLine();
	      }
	      monFichier.readLine();
	      reecrireContact();
	      monFichier.writeBytes("");
	      monFichier.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public void chargerContact () {
		try {
		      RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
		      for (Contact t : listeContacts) {
			        System.out.println(monFichier.readLine());
		      }
		      monFichier.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	public void chargerContact2 () {
		try {
		      RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
		      if (monFichier.length()>15) {
		    	  String file = "Voici la liste des contacts : ";
			      while(file!=null){
			    	  	System.out.println(file);
			    	  	file = monFichier.readLine();
			      }  
		      }
		      else {
		    	  System.out.println("Le fichier est vide.");
		      }
		      monFichier.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	
}
