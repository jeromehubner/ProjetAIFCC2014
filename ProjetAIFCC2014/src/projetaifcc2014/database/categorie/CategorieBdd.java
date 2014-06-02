package projetaifcc2014.database.categorie;
 
import projetaifcc2014.database.Bdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
 
public class CategorieBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_CATEGORIE = "categorie";
	
	// Colonnes table : categorie
	public static final String CAT_ID = "id";
	public static final int NUM_CAT_ID = 0;
	public static final String CAT_LIBELLE = "libelle";
	public static final int NUM_CAT_LIBELLE = 1;
	
	public CategorieBdd(Context context){
		super(context);
	}

	/**
	 * @brief insere une categorie dans la base de donnees
	 * @param la categorie a inserer
	 */
	public long insertCSategorie(Categorie categorie){
		ContentValues values = new ContentValues();
		values.put(CAT_ID, categorie.getId());
		values.put(CAT_LIBELLE, categorie.getLibelle());
		return bdd.insert(TABLE_CATEGORIE, null, values);
	}

	/**
	 * @brief met a jour une categorie dans la base de donnees
	 * @param la categorie a mettre a jour
	 */
	public int updateCategorie(Categorie categorie){
		ContentValues values = new ContentValues();
		values.put(CAT_LIBELLE, categorie.getLibelle());
		return bdd.update(TABLE_CATEGORIE, values, CAT_ID + " = " + categorie.getId(), null);
	}

	/**
	 * @brief supprime une categorie dans la base de donnees
	 * @param la categorie a supprimer
	 */
	public int removeCategorie(Categorie categorie){
		return bdd.delete(TABLE_CATEGORIE, CAT_ID + " = " + categorie.getId(), null);
	}

	/**
	 * @param l'identifiant de la categorie
	 * @return la categorie contenu en base de donnees correspondant a l'identifiant passe en parametre
	 */
	public Categorie getCategorieById(int id){
		Cursor c = bdd.query( TABLE_CATEGORIE, new String[] {CAT_ID, CAT_LIBELLE}, CAT_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Categorie categorie = new Categorie();
		categorie.setId(c.getInt(NUM_CAT_ID));
		categorie.setLibelle(c.getString(NUM_CAT_LIBELLE));
		c.close();
		return categorie;
	}
}