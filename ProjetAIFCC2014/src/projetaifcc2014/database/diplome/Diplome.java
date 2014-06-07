package projetaifcc2014.database.diplome;

import projetaifcc2014.database.categorie.Categorie;

public class Diplome {
	private int id;
	private Categorie categorie;
	private String libelle;
	private String niveau;
	private String metiers;
	private String objectifs;
	private String acces;
	private String financement;
	private String validation;
	private String lieu;
	private String poursuites;
	
	public Diplome(){}
	public Diplome(int id, Categorie categorie, String libelle, String niveau, String metiers, String objectifs, String acces, String financement, String validation, String lieu, String poursuites) {
		this.id = id;
		this.categorie = categorie;
		this.libelle = libelle;
		this.niveau = niveau;
		this.metiers = metiers;
		this.objectifs = objectifs;
		this.acces = acces;
		this.financement = financement;
		this.validation = validation;
		this.lieu = lieu;
		this.poursuites = poursuites;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
	
	public String getObjectifs() {
		return objectifs;
	}
	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}
	
	public String getAcces() {
		return acces;
	}
	public void setAcces(String acces) {
		this.acces = acces;
	}
	
	public String getFinancement() {
		return financement;
	}
	public void setFinancement(String financement) {
		this.financement = financement;
	}
	
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	public String getPoursuites() {
		return poursuites;
	}
	public void setPoursuites(String poursuites) {
		this.poursuites = poursuites;
	}
}