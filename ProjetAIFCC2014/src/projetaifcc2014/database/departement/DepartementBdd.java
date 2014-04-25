package projetaifcc2014.database.departement;
 
import projetaifcc2014.database.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class DepartementBdd {

	// Nom de la table
	public static final String TABLE_DEPARTEMENT = "departement";
	
	// Colonnes table : departement
	public static final String DEP_ID = "id";
	public static final int NUM_DEP_ID = 0;
	public static final String DEP_LIBELLE = "libelle";
	public static final int NUM_DEP_LIBELLE = 1;
	
	private SQLiteDatabase bdd;
	private Database maBdd;
	
	public DepartementBdd(Context context){
		maBdd = new Database(context, Database.DATABASE, null, Database.DATABASE_VERSION);
	}

	/**
	 * @brief ouvre la base de donn�es en mode �criture
	 */
	public void open(){
		bdd = maBdd.getWritableDatabase();
	}

	/**
	 * @brief ferme la base de donn�es
	 */
	public void close(){
		bdd.close();
	}
	
	/**
	 * @return la base de donn�es SQLite
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	/**
	 * @brief ins�re un departement dans la base de donn�es
	 * @param le departement � ins�rer
	 */
	public long insertDepartement(Departement departement){
		ContentValues values = new ContentValues();
		values.put(DEP_ID, departement.getId());
		values.put(DEP_LIBELLE, departement.getLibelle());
		return bdd.insert(TABLE_DEPARTEMENT, null, values);
	}

	/**
	 * @brief met � jour un departement dans la base de donn�es
	 * @param le departement � mettre � jour
	 */
	public int updateDepartement(Departement departement){
		ContentValues values = new ContentValues();
		values.put(DEP_LIBELLE, departement.getLibelle());
		return bdd.update(TABLE_DEPARTEMENT, values, DEP_ID + " = " + departement.getId(), null);
	}

	/**
	 * @brief supprime un departement dans la base de donn�es
	 * @param le departement � supprimer
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