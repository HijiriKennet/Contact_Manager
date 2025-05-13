import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {
	//commentaire 
	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {
		GestionnaireContacts cont = new GestionnaireContacts();
		
		sc= new Scanner(System.in);
		System.out.println("Bienvenue dans l'application de Gestion de Contacts.");
		chargeFichier(cont);
		int choix = menuPrincipal();
		while (choix>0) {
			switch (choix) {
			case 1 : 	ajouterContactsMain(cont); cont.sauvegarderContact(); break;
			case 2 : 	rechercherContactsMain(cont); break;
			case 3 : 	modifierContactsMain(cont); cont.reecrireContact(); break;
			case 4 : 	supprimerContactsMain(cont); break;
			case 5 : 	cont.chargerContact2(); break;
			default :
						System.out.println("Merci d'avoir utilisé l'application de Gestion de Contacts. ");
						System.exit(0);
			}
			choix = menuPrincipal();
		}
	}
	
	public static int menuPrincipal () {
		System.out.println("Menu : "
				+ "\n1- Ajouter un contact"
				+ "\n2- Rechercher un contact"
				+ "\n3- Modifier un contact"
				+ "\n4- Supprimer un contact"
				+ "\n5- Charger la liste des contacts"
				+ "\n6- Quitter");
		int choix = Integer.parseInt(sc.nextLine());
		return choix;
		
	}
	
	public static GestionnaireContacts ajouterContactsMain (GestionnaireContacts cont) {
		System.out.println("Pour ajouter un nouveau contact, vous devez préciser son nom, son prénom, son mail et son numéro de téléphone."
				+ "\nSi vous avez fini d'entrer de nouveaux contacts, tapez 0.");
		System.out.println("Quel est le nom de votre premier contact ?");
		/*
		String data [][]= new String[2][4];
		data[0][0]="nom";
		data[0][1]="prénom";
		data[0][2]="mail";
		data[0][3]="tel";
		*/
		String data [][]= {
				{"nom","prénom","mail","téléphone"},
				{"","","",""}
		};
		data [1][0]= sc.nextLine();
		int i=1;
		int j=0;
		while (data[1][j].compareTo("0")!=0) {
			j++;
			System.out.println("Son "+data[0][j]+" ?");
			data [1][j]=sc.nextLine();
			if (j==3) {
				j=0;
				cont.ajoutContact(data [1][0],data [1][1],data [1][2],data [1][3]);
				++i;
				System.out.println("Quel est le nom de votre contact N°"+i+" ?");
				data [1][j]=sc.nextLine();
			}
		}
		System.out.println((i-1)+" contacts enregistrés.");
		return cont;
	}
	
	public static void afficherContactsMain (GestionnaireContacts cont) {
		System.out.println("Voici la liste actuelle des contacts : ");
		cont.afficheContacts();
	}
	
	public static void rechercherContactsMain (GestionnaireContacts cont) {
		System.out.println("Quel est le nom du contact que vous recherchez ?");
		String trouve=sc.nextLine();
		trouve=cont.chercheContact2(trouve);
		System.out.println("Votre contact est : \n"+trouve);
	}
	
	public static void modifierContactsMain (GestionnaireContacts cont) {
		System.out.println("Quel est le nom du contact que vous voulez modifier ?");
		String trouve=sc.nextLine();
		System.out.println("Votre contact est : \n"+cont.chercheContact2(trouve));
		String contact []=new String [4];
		System.out.println("Entrez alors les nouveaux nom, prénom, mail et téléphone de ce nouveau contact : ");
		for (int i=0; i<4; i++) {
			contact [i]=sc.nextLine();
		}
		cont.modifierContact(trouve, contact[0], contact[1], contact[2],contact[3]);
		trouve=cont.chercheContact2(contact[0]);
		System.out.println("Votre contact a bien été modifié en : \n"+trouve);
	}
	
	public static void supprimerContactsMain (GestionnaireContacts cont) {
		System.out.println("Quel est le nom du contact que vous voulez supprimer ?");
		String trouve=sc.nextLine();
		if (cont==null) {
		cont.supprimerContact2(trouve);
		}
		else if (cont.supprimerContact(trouve)) {
			cont.reecrireContact();
		}
	}
	
	public static GestionnaireContacts chargeFichier (GestionnaireContacts cont) {
		try {
		      RandomAccessFile monFichier = new RandomAccessFile("contacts.txt", "rwd");
		      String file = monFichier.readLine();
		      String line []= new String [4]; //tableau qui va recueillir chaque ligne du fichier sous forme de contact
		      //si jamais le fichier est vide, on affiche un truc qui nous permet de savoir que le fichier est vide
		      if (file==null) {
		    	  System.out.println("Un fichier a été créé pour récupérer la liste de contacts.");
		      }
		      else {
		    	  while (file!=null) {
			    	  if (file.length()>2) { //comme ça si jamais il y a une suppression de ligne, on peut la sauter.
					      file = file.replace("Contact : ", " ");
					      file = file.replace("; Mail ", "");
					      file = file.replace("; Tel ", "");
					      line = file.split(" : ");
					      cont.ajoutContact(line[0], line[1], line[2], line[3]);
				      }
			    	  file = monFichier.readLine();
			      }
			      monFichier.close();
			      System.out.println("Un fichier avec une liste de "+ cont.nbrContacts + " contacts non vide a été chargé.");
			      afficherContactsMain(cont);  
			      
		      }
	    }
	
	catch (Exception e) {
	      e.printStackTrace();
	    }
		return cont;
	}
	
}
