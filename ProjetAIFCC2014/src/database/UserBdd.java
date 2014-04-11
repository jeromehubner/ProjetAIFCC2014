package database;
 
import com.example.projetaifcc2014.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class UserBdd {

	// Nom de la table
	public static final String TABLE_USER = "user";
	
	// Colonnes table : user
	public static final String USE_ID = "id";
	public static final int NUM_USE_ID = 0;
	public static final String USE_NOM = "nom";
	public static final int NUM_USE_NOM = 1;
	public static final String USE_PRENOM = "prenom";
	public static final int NUM_USE_PRENOM = 2;
	public static final String USE_LOGIN = "login";
	public static final int NUM_USE_LOGIN = 3;
	public static final String USE_MDP = "mdp";
	public static final int NUM_USE_MDP = 4;
	public static final String USE_PROFIL = "profil";
	public static final int NUM_USE_PROFIL = 5;
	
	private SQLiteDatabase bdd;
	private Database maBdd;
	private Context context;
	
	public UserBdd(Context context){
		this.context = context;
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
	 * @brief insère un utilisateur dans la base de données
	 * @param l'utilisateur à insérer
	 */
	public long insertUser(User user){
		ContentValues values = new ContentValues();
		values.put(USE_NOM, user.getNom());
		values.put(USE_PRENOM, user.getPrenom());
		values.put(USE_LOGIN, user.getLogin());
		values.put(USE_MDP, user.getMdp());
		values.put(USE_PROFIL, user.getProfil().getId());
		return bdd.insert(TABLE_USER, null, values);
	}
	
	/**
	 * @brief met à jour un utilisateur dans la base de données
	 * @param l'utilisateur à mettre à jour
	 */
	public int updateUser(User user){
		ContentValues values = new ContentValues();
		values.put(USE_NOM, user.getNom());
		values.put(USE_PRENOM, user.getPrenom());
		values.put(USE_LOGIN, user.getLogin());
		values.put(USE_MDP, user.getMdp());
		return bdd.update(TABLE_USER, values, USE_ID + " = " + user.getId(), null);
	}
	
	/**
	 * @brief supprime un utilisateur dans la base de données
	 * @param l'utilisateur à supprimer
	 */
	public int removeUser(User user){
		return bdd.delete(TABLE_USER, USE_ID + " = " + user.getId(), null);
	}

	/**
	 * @param l'identifiant de l'utilisateur
	 * @return l'utilisateur contenu en base de données correspondant à l'identifiant passé en paramètre
	 */
	public User getUserById(int id){
		Cursor c = bdd.query( TABLE_USER, new String[] {USE_ID, USE_NOM, USE_PRENOM, USE_LOGIN, USE_MDP, USE_PROFIL}, USE_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		User user = new User();
		user.setId(c.getInt(NUM_USE_ID));
		user.setNom(c.getString(NUM_USE_NOM));
		user.setPrenom(c.getString(NUM_USE_PRENOM));
		user.setLogin(c.getString(NUM_USE_LOGIN));
		user.setMdp(c.getString(NUM_USE_MDP));
		ProfilBdd profilBdd = new ProfilBdd(context);
		if (profilBdd != null){
			profilBdd.open();
			user.setProfil(profilBdd.getProfilById(c.getInt(NUM_USE_PROFIL)));
		}
		c.close();
		return user;
	}
}