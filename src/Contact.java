
public class Contact {
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	
	//Constructeur
	Contact (String nom, String prenom,String mail,String tel){
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.tel=tel;
	}
	
	//getter 
	String getNom () {
		return nom;
	}
	
	String getPrenom () {
		return prenom;
	}
	
	String getMail () {
		return mail;
	}
	
	String getTel () {
		return tel;
	}
	
	//setters
	void setNom (String nom) {
		this.nom = nom;
	}
	
	void setPrenom (String prenom) {
		this.prenom = prenom;
	}
	
	void setMail (String mail) {
		this.mail = mail;
	}
	
	void setTel (String tel) {
		this.tel = tel;
	}
	
	//tostring
	public String toString () {
		String contact="Contact : "+nom+" : "+prenom+" ; Mail : "+mail+" ; Tel : "+tel;
		return contact;
	}
	
}
