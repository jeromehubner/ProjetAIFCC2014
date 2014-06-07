package projetaifcc2014.database.categorie;

import projetaifcc2014.database.departement.Departement;

public class Categorie {
	private int id;
	private Departement departement;
	private String libelle;
	
	public Categorie() {}
	public Categorie(int id, Departement departement, String libelle) {
		this.id = id;
		this.departement = departement;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
