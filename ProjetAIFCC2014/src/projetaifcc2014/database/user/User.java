package projetaifcc2014.database.user;

import projetaifcc2014.database.profil.Profil;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private Profil profil;

	public User() {}
	public User(int id, String nom, String prenom, String login, String mdp, Profil profil) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.profil = profil;
	}
	
	/**
	 * @return l'identifiant de l'utilisateur
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param le nouvel identifiant de l'utilisateur
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return le nom de l'utilisateur
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param le nouveau nom de l'utilisateur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return le prenom de l'utilisateur
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param le nouveau pr�nom de l'utilisateur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return le login de l'utilisateur
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param le nouveau login de l'utilisateur
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return le mot de passe de l'utilisateur
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param le nouveau mot de passe de l'utilisateur
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * @return le profil de l'utilisateur
	 */
	public Profil getProfil() {
		return profil;
	}
	/**
	 * @param le nouveau profil de l'utilisateur
	 */
	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	/**
	 * @return une cha�ne de caract�res repr�sentative de l'utilisateur
	 */
	public String toString(){
		if (profil == null){
			return (nom + " " + prenom + " : " + login);
		} else {
			return (nom + " " + prenom + " (" + profil.getNom() + ") : " + login);
		}
	}
}
