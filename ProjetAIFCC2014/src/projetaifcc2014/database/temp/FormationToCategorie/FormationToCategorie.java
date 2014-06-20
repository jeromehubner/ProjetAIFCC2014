package projetaifcc2014.database.temp.FormationToCategorie;

public class FormationToCategorie {

	// Correspond à la colonne idFormation de la table_formationToScategorie;
	private int idFormation;

	// Correspond à la colonne idCategorie de la table_formationToScategorie;
	private int idCategorie;


	public FormationToCategorie() {}
	public FormationToCategorie(int idFormation, int idCategorie) {
		this.idFormation = idFormation;
		this.idCategorie = idCategorie;
	}
	
	
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	
	
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}



}
