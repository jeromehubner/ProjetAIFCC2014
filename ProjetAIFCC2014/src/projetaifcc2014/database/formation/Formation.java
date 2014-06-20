package projetaifcc2014.database.formation;

import projetaifcc2014.database.categorie.Categorie;

public class Formation{
	
	private int id;
	private Categorie categorie;
	private String diplome;
	private String objectifPro;
	private String objectifPedago;
	private String acces;
	private String financement;
	private String validation;
	private String lieu;
	private String poursuites;
	private String metiers;
	private String programme;
	private String email;
	
	public Formation(){}
	public Formation(int id, Categorie categorie, String libelle, String objectifPro, String objectifPedago, String acces, String financement, String validation, String lieu, String poursuites, String metiers, String programme, String email) {
		this.id = id;
		this.categorie = categorie;
		this.diplome = libelle;
		this.objectifPro = objectifPro;
		this.objectifPedago = objectifPedago;
		this.acces = acces;
		this.financement = financement;
		this.validation = validation;
		this.lieu = lieu;
		this.poursuites = poursuites;
		this.metiers = metiers;
		this.programme = programme;
		this.email = email;
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
	
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String libelle) {
		this.diplome = libelle;
	}
	
	public String getObjectifPro() {
		return objectifPro;
	}
	public void setObjectifPro(String objectifPro) {
		this.objectifPro = objectifPro;
	}
	
	public String getObjectifPedago() {
		return objectifPedago;
	}
	public void setObjectifPedago(String objectifPedago) {
		this.objectifPedago = objectifPedago;
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
	
	public String getMetiers() {
		return metiers;
	}
	public void setMetiers(String metiers) {
		this.metiers = metiers;
	}
	
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}