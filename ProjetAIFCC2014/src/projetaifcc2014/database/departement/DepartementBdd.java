package projetaifcc2014.database.departement;
 
import projetaifcc2014.database.Bdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
 
public class DepartementBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_DEPARTEMENT = "departement";
	
	// Colonnes table : departement
	public static final String DEP_ID = "id";
	public static final int NUM_DEP_ID = 0;
	public static final String DEP_LIBELLE = "libelle";
	public static final int NUM_DEP_LIBELLE = 1;
	
	public DepartementBdd(Context context){
		super(context);
	}

	/**
	 * @brief insere un departement dans la base de donnees
	 * @param le departement a inserer
	 */
	public long insertDepartement(Departement departement){
		ContentValues values = new ContentValues();
		values.put(DEP_ID, departement.getId());
		values.put(DEP_LIBELLE, departement.getLibelle());
		return bdd.insert(TABLE_DEPARTEMENT, null, values);
	}

	/**
	 * @brief met a jour un departement dans la base de donnees
	 * @param le departement a mettre a jour
	 */
	public int updateDepartement(Departement departement){
		ContentValues values = new ContentValues();
		values.put(DEP_LIBELLE, departement.getLibelle());
		return bdd.update(TABLE_DEPARTEMENT, values, DEP_ID + " = " + departement.getId(), null);
	}

	/**
	 * @brief supprime un departement dans la base de donnees
	 * @param le departement a supprimer
	 */
	public int removeDepartement(Departement departement){
		return bdd.delete(TABLE_DEPARTEMENT, DEP_ID + " = " + departement.getId(), null);
	}

	/**
	 * @param l'identifiant du departement
	 * @return le departement contenu en base de donn�es correspondant � l'identifiant pass� en param�tre
	 */
	public Departement getDepartementById(int id){
		Cursor c = bdd.query( TABLE_DEPARTEMENT, new String[] {DEP_ID, DEP_LIBELLE}, DEP_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Departement departement = new Departement();
		departement.setId(c.getInt(NUM_DEP_ID));
		departement.setLibelle(c.getString(NUM_DEP_LIBELLE));
		c.close();
		return departement;
	}
}