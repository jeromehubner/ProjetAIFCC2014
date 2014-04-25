package projetaifcc2014.database.formation;

import projetaifcc2014.database.departement.Departement;

public class Formation {
	private int id;
	private String libelle;
	private Departement departement;
	
	public Formation() {}
	public Formation(int id, String libelle, Departement departement) {
		this.id = id;
		this.libelle = libelle;
		this.departement = departement;
	}

	/**
	 * @return l'identifiant de la formation
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param le nouvel identifiant de la formation
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return le libelle de la formation
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param le nouveau libelle de la formation
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return le departement de la formation
	 */
	public Departement getDepartement() {
		return departement;
	}
	/**
	 * @param le nouveau departement de la formation
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
}
