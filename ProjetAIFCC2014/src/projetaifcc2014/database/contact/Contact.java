package projetaifcc2014.database.contact;

public class Contact {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	
	public Contact() {}
	public Contact(int id, String nom, String prenom, String mail, String tel) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
	}

	/**
	 * @return l'identifiant du contact
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param le nouvel identifiant du contact
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return le nom du contact
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param le nouveau nom du contact
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return le prenom du contact
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param le nouveau prenom du contact
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return le mail du contact
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param le nouveau mail du contact
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return le tel du contact
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param le nouveau tel du contact
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
}
