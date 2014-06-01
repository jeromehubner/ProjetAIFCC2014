package projetaifcc2014.database.formation;
 
import projetaifcc2014.database.Bdd;
import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.departement.DepartementBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
 
public class FormationBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_FORMATION = "formation";
	
	// Colonnes table : departement
	public static final String FOR_ID = "id";
	public static final int NUM_FOR_ID = 0;
	public static final String FOR_DEPARTEMENT = "departement_id";
	public static final int NUM_FOR_DEPARTEMENT = 1;
	public static final String FOR_CATEGORIE = "categorie_id";
	public static final int NUM_FOR_CATEGORIE = 2;
	public static final String FOR_DIPLOME = "diplome";
	public static final int NUM_FOR_DIPLOME = 3;
	public static final String FOR_NIVEAU = "niveau";
	public static final int NUM_FOR_NIVEAU = 4;
	public static final String FOR_METIERS = "metiers";
	public static final int NUM_FOR_METIERS = 5;
	
	public FormationBdd(Context context){
		super(context);
	}
	
	/**
	 * @brief insere une formation dans la base de donnees
	 * @param la formation a inserer
	 */
	public long insertFormation(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_ID, formation.getId());
		values.put(FOR_DEPARTEMENT, formation.getDepartement().getId());
		values.put(FOR_CATEGORIE, formation.getCategorie().getId());
		values.put(FOR_DIPLOME, formation.getDiplome());
		values.put(FOR_NIVEAU, formation.getNiveau());
		values.put(FOR_METIERS, formation.getMetiers());
		return bdd.insert(TABLE_FORMATION, null, values);
	}

	/**
	 * @brief met a jour une formation dans la base de donnees
	 * @param la formation a mettre a jour
	 */
	public int updateFormation(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_DIPLOME, formation.getDiplome());
		values.put(FOR_DEPARTEMENT, formation.getDepartement().getId());
		values.put(FOR_CATEGORIE, formation.getCategorie().getId());
		values.put(FOR_DIPLOME, formation.getDiplome());
		values.put(FOR_NIVEAU, formation.getNiveau());
		values.put(FOR_METIERS, formation.getMetiers());
		return bdd.update(TABLE_FORMATION, values, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @brief supprime une formation dans la base de donnees
	 * @param la formation a supprimer
	 */
	public int removeFormation(Formation formation){
		return bdd.delete(TABLE_FORMATION, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @param l'identifiant de la formation
	 * @return la formation contenu en base de donn�es correspondant � l'identifiant pass� en param�tre
	 */
	public Formation getFormationById(int id){
		Cursor c = bdd.query( TABLE_FORMATION, new String[] {FOR_ID, FOR_DIPLOME, FOR_DEPARTEMENT}, FOR_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Formation formation = new Formation();
		formation.setId(c.getInt(NUM_FOR_ID));
		DepartementBdd departementBdd = new DepartementBdd(context);
		if (departementBdd != null){
			departementBdd.open();
			formation.setDepartement(departementBdd.getDepartementById(c.getInt(NUM_FOR_DEPARTEMENT)));
		}
		CategorieBdd categorieBdd = new CategorieBdd(context);
		if (categorieBdd != null){
			categorieBdd.open();
			formation.setCategorie(categorieBdd.getCategorieById(c.getInt(NUM_FOR_CATEGORIE)));
		}
		formation.setDiplome(c.getString(NUM_FOR_DIPLOME));
		formation.setNiveau(c.getString(NUM_FOR_NIVEAU));
		formation.setMetiers(c.getString(NUM_FOR_METIERS));
		c.close();
		return formation;
	}
}