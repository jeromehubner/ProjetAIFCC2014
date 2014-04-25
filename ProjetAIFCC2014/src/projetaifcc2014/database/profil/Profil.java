package projetaifcc2014.database.profil;


public class Profil {
	private int id;
	private String nom;
	
	public Profil() {}
	public Profil(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	/**
	 * @return l'identifiant du profil
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param le nouvel identifiant du profil
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return le libelle du profil
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param le nouveau libelle du profil
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
}
