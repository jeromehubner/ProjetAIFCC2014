package projetaifcc2014.database.departement;

public class Departement {
	private int id;
	private String libelle;
	
	public Departement() {}
	public Departement(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	/**
	 * @return l'identifiant du departement
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
	 * @return le libelle du departement
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param le nouveau libelle du departement
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
