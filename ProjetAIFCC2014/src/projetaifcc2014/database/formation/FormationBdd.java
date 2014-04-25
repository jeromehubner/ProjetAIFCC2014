package projetaifcc2014.database.formation;
 
import projetaifcc2014.database.Database;
import projetaifcc2014.database.departement.DepartementBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class FormationBdd {

	// Nom de la table
	public static final String TABLE_FORMATION = "formation";
	
	// Colonnes table : departement
	public static final String FOR_ID = "id";
	public static final int NUM_FOR_ID = 0;
	public static final String FOR_LIBELLE = "libelle";
	public static final int NUM_FOR_LIBELLE = 1;
	public static final String FOR_DEPARTEMENT = "departement_id";
	public static final int NUM_FOR_DEPARTEMENT = 2;
	
	private SQLiteDatabase bdd;
	private Database maBdd;
	private Context context;
	
	public FormationBdd(Context context){
		this.context = context;
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
	 * @brief ins�re une formation dans la base de donn�es
	 * @param la formation � ins�rer
	 */
	public long insertFormation(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_ID, formation.getId());
		values.put(FOR_LIBELLE, formation.getLibelle());
		values.put(FOR_DEPARTEMENT, formation.getDepartement().getId());
		return bdd.insert(TABLE_FORMATION, null, values);
	}

	/**
	 * @brief met � jour une formation dans la base de donn�es
	 * @param la formation � mettre � jour
	 */
	public int updateDepartement(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_LIBELLE, formation.getLibelle());
		values.put(FOR_DEPARTEMENT, formation.getDepartement().getId());
		return bdd.update(TABLE_FORMATION, values, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @brief supprime une formation dans la base de donn�es
	 * @param la formation � supprimer
	 */
	public int removeDepartement(Formation formation){
		return bdd.delete(TABLE_FORMATION, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @param l'identifiant de la formation
	 * @return la formation contenu en base de donn�es correspondant � l'identifiant pass� en param�tre
	 */
	public Formation getFormationById(int id){
		Cursor c = bdd.query( TABLE_FORMATION, new String[] {FOR_ID, FOR_LIBELLE, FOR_DEPARTEMENT}, FOR_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Formation formation = new Formation();
		formation.setId(c.getInt(NUM_FOR_ID));
		formation.setLibelle(c.getString(NUM_FOR_LIBELLE));
		DepartementBdd departementBdd = new DepartementBdd(context);
		if (departementBdd != null){
			departementBdd.open();
			formation.setDepartement(departementBdd.getDepartementById(c.getInt(NUM_FOR_DEPARTEMENT)));
		}
		c.close();
		return formation;
	}
}