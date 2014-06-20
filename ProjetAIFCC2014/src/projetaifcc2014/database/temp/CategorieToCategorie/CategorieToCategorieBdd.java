package projetaifcc2014.database.temp.CategorieToCategorie;

import projetaifcc2014.database.Bdd;
import projetaifcc2014.database.departement.Departement;
import projetaifcc2014.database.departement.DepartementBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class CategorieToCategorieBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_CATEGORIE_TO_CATEGORIE = "categorie_to_categorie";


	// Nom et numéro des colonnes de la TABLE_CATEGORIE_TO_SCATEGORIE
	public static final String CAT_TO_CAT_ID_CATEGORIE = "idCategorie";
	public static final int NUM_CAT_TO_CAT_ID_CATEGORIE = 0;
	public static final String CAT_TO_CAT_ID_DEPARTEMENT = "idDepartement";
	public static final int NUM_CAT_TO_CAT_ID_DEPARTEMENT = 1;


	public CategorieToCategorieBdd(Context context){
		super(context);
	}
	

	/**
	 * @return la base de donn�es SQLite
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}


	public long insertCatToCat(CategorieToCategorie catToCat){
		ContentValues values = new ContentValues();
		values.put(CAT_TO_CAT_ID_CATEGORIE, catToCat.getIdCategorie());
		values.put(CAT_TO_CAT_ID_DEPARTEMENT, catToCat.getIdDepartement());
		return bdd.insert(TABLE_CATEGORIE_TO_CATEGORIE, null, values);
	}


	public int removeCatToCat(CategorieToCategorie catToCat){
		return bdd.delete(TABLE_CATEGORIE_TO_CATEGORIE, 
				CAT_TO_CAT_ID_CATEGORIE+ " = " +catToCat.getIdCategorie(), null);
	}


	public Departement getDepartementByIdCategorie(int idCategorie){
		Cursor c = bdd.query(TABLE_CATEGORIE_TO_CATEGORIE,
				new String[] {CAT_TO_CAT_ID_CATEGORIE, CAT_TO_CAT_ID_DEPARTEMENT},
				CAT_TO_CAT_ID_CATEGORIE + " = " +idCategorie, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();

		Departement departement = new Departement();
		DepartementBdd departementBdd = new DepartementBdd(context);

		departementBdd.open();
		departement = departementBdd.getDepartementById(c.getInt(NUM_CAT_TO_CAT_ID_DEPARTEMENT));
		departementBdd.close();

		c.close();
		return departement;
	}
}