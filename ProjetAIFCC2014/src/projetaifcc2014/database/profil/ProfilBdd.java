package projetaifcc2014.database.profil;
 
import projetaifcc2014.database.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class ProfilBdd {

	// Nom de la table
	public static final String TABLE_PROFIL = "profil";
	
	// Colonnes table : profil
	public static final String PRO_ID = "id";
	public static final int NUM_PRO_ID = 0;
	public static final String PRO_NOM = "nom";
	public static final int NUM_PRO_NOM = 1;
	
	private SQLiteDatabase bdd;
	private Database maBdd;
	
	public ProfilBdd(Context context){
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
	 * @brief ins�re un profil dans la base de donn�es
	 * @param le profil � ins�rer
	 */
	public long insertProfil(Profil profil){
		ContentValues values = new ContentValues();
		values.put(PRO_ID, profil.getId());
		values.put(PRO_NOM, profil.getNom());
		return bdd.insert(TABLE_PROFIL, null, values);
	}

	/**
	 * @brief met � jour un profil dans la base de donn�es
	 * @param le profil � mettre � jour
	 */
	public int updateProfil(Profil profil){
		ContentValues values = new ContentValues();
		values.put(PRO_NOM, profil.getNom());
		return bdd.update(TABLE_PROFIL, values, PRO_ID + " = " + profil.getId(), null);
	}

	/**
	 * @brief supprime un profil dans la base de donn�es
	 * @param le profil � supprimer
	 */
	public int removeProfil(Profil profil){
		return bdd.delete(TABLE_PROFIL, PRO_ID + " = " + profil.getId(), null);
	}

	/**
	 * @param l'identifiant du profil
	 * @return le profil contenu en base de donn�es correspondant � l'identifiant pass� en param�tre
	 */
	public Profil getProfilById(int id){
		Cursor c = bdd.query( TABLE_PROFIL, new String[] {PRO_ID, PRO_NOM}, PRO_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Profil profil = new Profil();
		profil.setId(c.getInt(NUM_PRO_ID));
		profil.setNom(c.getString(NUM_PRO_NOM));
		c.close();
		return profil;
	}
}