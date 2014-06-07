package projetaifcc2014.database.diplome;
 
import projetaifcc2014.database.Bdd;
import projetaifcc2014.database.categorie.CategorieBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
 
public class DiplomeBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_DIPLOME = "diplome";
	
	// Colonnes table : departement
	public static final String DIP_ID = "id";
	public static final int NUM_DIP_ID = 0;
	public static final String DIP_CATEGORIE = "categorie_id";
	public static final int NUM_DIP_CATEGORIE = 1;
	public static final String DIP_LIBELLE = "libelle";
	public static final int NUM_DIP_LIBELLE = 3;
	public static final String DIP_NIVEAU = "niveau";
	public static final int NUM_DIP_NIVEAU = 4;
	public static final String DIP_METIERS = "metiers";
	public static final int NUM_DIP_METIERS = 5;
	public static final String DIP_OBJECTIFS = "objectifs";
	public static final int NUM_DIP_OBJECTIFS = 6;
	public static final String DIP_ACCES = "acces";
	public static final int NUM_DIP_ACCES = 7;
	public static final String DIP_FINANCEMENT = "financement";
	public static final int NUM_DIP_FINANCEMENT = 8;
	public static final String DIP_VALIDATION = "validation";
	public static final int NUM_DIP_VALIDATION = 9;
	public static final String DIP_LIEU = "lieu";
	public static final int NUM_DIP_LIEU = 10;
	public static final String DIP_POURSUITES = "poursuites";
	public static final int NUM_DIP_POURSUITES = 11;
	
	public DiplomeBdd(Context context){
		super(context);
	}
	
	/**
	 * @brief insere un diplome dans la base de donnees
	 * @param la formation a inserer
	 */
	public long insertFormation(Diplome diplome){
		ContentValues values = new ContentValues();
		values.put(DIP_ID, diplome.getId());
		values.put(DIP_CATEGORIE, diplome.getCategorie().getId());
		values.put(DIP_LIBELLE, diplome.getLibelle());
		values.put(DIP_NIVEAU, diplome.getNiveau());
		values.put(DIP_METIERS, diplome.getMetiers());
		values.put(DIP_OBJECTIFS, diplome.getObjectifs());
		values.put(DIP_ACCES, diplome.getAcces());
		values.put(DIP_FINANCEMENT, diplome.getFinancement());
		values.put(DIP_VALIDATION, diplome.getValidation());
		values.put(DIP_LIEU, diplome.getLieu());
		values.put(DIP_POURSUITES, diplome.getPoursuites());
		return bdd.insert(TABLE_DIPLOME, null, values);
	}

	/**
	 * @brief met a jour un diplome dans la base de donnees
	 * @param le diplome a mettre a jour
	 */
	public int updateFormation(Diplome diplome){
		ContentValues values = new ContentValues();
		values.put(DIP_LIBELLE, diplome.getLibelle());
		values.put(DIP_CATEGORIE, diplome.getCategorie().getId());
		values.put(DIP_NIVEAU, diplome.getNiveau());
		values.put(DIP_METIERS, diplome.getMetiers());
		values.put(DIP_OBJECTIFS, diplome.getObjectifs());
		values.put(DIP_ACCES, diplome.getAcces());
		values.put(DIP_FINANCEMENT, diplome.getFinancement());
		values.put(DIP_VALIDATION, diplome.getValidation());
		values.put(DIP_LIEU, diplome.getLieu());
		values.put(DIP_POURSUITES, diplome.getPoursuites());
		return bdd.update(TABLE_DIPLOME, values, DIP_ID + " = " + diplome.getId(), null);
	}

	/**
	 * @brief supprime un diplome dans la base de donnees
	 * @param le diplome a supprimer
	 */
	public int removeFormation(Diplome diplome){
		return bdd.delete(TABLE_DIPLOME, DIP_ID + " = " + diplome.getId(), null);
	}

	/**
	 * @param l'identifiant du diplome
	 * @return le diplome contenu en base de donnees correspondant a l'identifiant passe en parametre
	 */
	public Diplome getDiplomeById(int id){
		Cursor c = bdd.query( TABLE_DIPLOME, new String[] {DIP_ID, DIP_CATEGORIE, DIP_LIBELLE, DIP_NIVEAU, DIP_METIERS }, DIP_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Diplome diplome = new Diplome();
		diplome.setId(c.getInt(NUM_DIP_ID));
		CategorieBdd categorieBdd = new CategorieBdd(context);
		if (categorieBdd != null){
			categorieBdd.open();
			diplome.setCategorie(categorieBdd.getCategorieById(c.getInt(NUM_DIP_CATEGORIE)));
		}
		diplome.setLibelle(c.getString(NUM_DIP_LIBELLE));
		diplome.setNiveau(c.getString(NUM_DIP_NIVEAU));
		diplome.setMetiers(c.getString(NUM_DIP_METIERS));
		diplome.setObjectifs(c.getString(NUM_DIP_OBJECTIFS));
		diplome.setAcces(c.getString(NUM_DIP_ACCES));
		diplome.setFinancement(c.getString(NUM_DIP_FINANCEMENT));
		diplome.setValidation(c.getString(NUM_DIP_VALIDATION));
		diplome.setLieu(c.getString(NUM_DIP_LIEU));
		diplome.setPoursuites(c.getString(NUM_DIP_POURSUITES));
		c.close();
		return diplome;
	}
}