package database;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetaifcc2014.Profil;
 
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
	 * @brief ouvre la base de données en mode écriture
	 */
	public void open(){
		bdd = maBdd.getWritableDatabase();
	}

	/**
	 * @brief ferme la base de données
	 */
	public void close(){
		bdd.close();
	}
	
	/**
	 * @return la base de données SQLite
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	/**
	 * @brief insère un profil dans la base de données
	 * @param le profil à insérer
	 */
	public long insertProfil(Profil profil){
		ContentValues values = new ContentValues();
		values.put(PRO_ID, profil.getId());
		values.put(PRO_NOM, profil.getNom());
		return bdd.insert(TABLE_PROFIL, null, values);
	}

	/**
	 * @brief met à jour un profil dans la base de données
	 * @param le profil à mettre à jour
	 */
	public int updateProfil(Profil profil){
		ContentValues values = new ContentValues();
		values.put(PRO_NOM, profil.getNom());
		return bdd.update(TABLE_PROFIL, values, PRO_ID + " = " + profil.getId(), null);
	}

	/**
	 * @brief supprime un profil dans la base de données
	 * @param le profil à supprimer
	 */
	public int removeProfil(Profil profil){
		return bdd.delete(TABLE_PROFIL, PRO_ID + " = " + profil.getId(), null);
	}

	/**
	 * @param l'identifiant du profil
	 * @return le profil contenu en base de données correspondant à l'identifiant passé en paramètre
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