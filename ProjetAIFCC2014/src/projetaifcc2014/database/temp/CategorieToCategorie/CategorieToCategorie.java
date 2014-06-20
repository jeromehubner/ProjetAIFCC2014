package projetaifcc2014.database.temp.CategorieToCategorie;


/** 
 * Cette classe permet de mapper la relation qui sépare 
 * la table categorietoScategorie en 2 tables (table_departement et table_categorie).
 * @author jerome
 *
 */
public class CategorieToCategorie {
	// Correspond à la colonne cat_nivo1_id de la table_categorieToScategorie;
	private int idDepartement;
	
	// Correspond à la colonne cat_nivo2_id de la table_categorieToScategorie;
	private int idCategorie;
	
	
	public CategorieToCategorie() {}
	public CategorieToCategorie(int idDepartement, int idCategorie) {
		this.idDepartement = idDepartement;
		this.idCategorie = idCategorie;
	}
	
	
	public int getIdDepartement() {
		return idDepartement;
	}
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}
	
	
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
}
