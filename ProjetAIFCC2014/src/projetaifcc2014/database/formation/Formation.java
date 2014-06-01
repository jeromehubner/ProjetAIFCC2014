package projetaifcc2014.database.formation;

import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.departement.Departement;

public class Formation {
	private int id;
	private Departement departement;
	private Categorie categorie;
	private String diplome;
	private String niveau;
	private String metiers;
	
	public Formation(){}
	public Formation(int id, Departement departement, Categorie categorie, String diplome, String niveau, String metiers) {
		this.id = id;
		this.departement = departement;
		this.categorie = categorie;
		this.diplome = diplome;
		this.niveau = niveau;
		this.metiers = metiers;
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
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
	public String getMetiers() {
		return metiers;
	}
	public void setMetiers(String metiers) {
		this.metiers = metiers;
	}
}