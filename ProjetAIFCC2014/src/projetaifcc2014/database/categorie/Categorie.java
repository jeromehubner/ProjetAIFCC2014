package projetaifcc2014.database.categorie;

public class Categorie {
	private int id;
	private String libelle;
	
	public Categorie() {}
	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
