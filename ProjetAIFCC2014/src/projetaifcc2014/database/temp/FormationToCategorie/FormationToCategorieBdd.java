package projetaifcc2014.database.temp.FormationToCategorie;

import projetaifcc2014.database.Bdd;
import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.categorie.CategorieBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FormationToCategorieBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_FORMATION_TO_CATEGORIE = "formation_to_categorie";


	// Nom et numéro des colonnes de la TABLE_FORMATION_TO_CATEGORIE
	public static final String FORM_TO_CAT_ID_FORMATION = "idFormation";
	public static final int NUM_FORM_TO_CAT_ID_FORMATION = 0;
	public static final String FORM_TO_CAT_ID_CATEGORIE = "idCategorie";
	public static final int NUM_FORM_TO_CAT_ID_CATEGORIE = 1;


	public FormationToCategorieBdd(Context context){
		super(context);
	}
	
	
	/**
	 * @return la base de donn�es SQLite
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}


	public long insertFormToCat(FormationToCategorie formToCat){
		ContentValues values = new ContentValues();
		values.put(FORM_TO_CAT_ID_FORMATION, formToCat.getIdFormation());
		values.put(FORM_TO_CAT_ID_CATEGORIE, formToCat.getIdCategorie());
		return bdd.insert(TABLE_FORMATION_TO_CATEGORIE, null, values);
	}


	public int removeRelation(FormationToCategorie formToCat){
		return bdd.delete(TABLE_FORMATION_TO_CATEGORIE, 
				FORM_TO_CAT_ID_CATEGORIE+ " = " +formToCat.getIdCategorie(), null);
	}


	public Categorie getCategorieByIdFormation(int idFormation){
		Cursor c = bdd.query(TABLE_FORMATION_TO_CATEGORIE,
				new String[] {FORM_TO_CAT_ID_FORMATION, FORM_TO_CAT_ID_CATEGORIE},
				FORM_TO_CAT_ID_FORMATION + " = " +idFormation, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();

		Categorie categorie = new Categorie();
		CategorieBdd categorieBdd = new CategorieBdd(context);
		
		categorieBdd.open();
		categorie = categorieBdd.getCategorieById(c.getInt(NUM_FORM_TO_CAT_ID_CATEGORIE));
		categorieBdd.close();

		c.close();
		return categorie;
	}
}