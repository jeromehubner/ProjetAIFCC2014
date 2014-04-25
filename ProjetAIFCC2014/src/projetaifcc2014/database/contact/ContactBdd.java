package projetaifcc2014.database.contact;
 
import projetaifcc2014.database.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class ContactBdd {

	// Nom de la table
	public static final String TABLE_CONTACT = "contact";
	
	// Colonnes table : contact
	public static final String CONT_ID = "id";
	public static final int NUM_CONT_ID = 0;
	public static final String CONT_NOM = "nom";
	public static final int NUM_CONT_NOM = 1;
	public static final String CONT_PRENOM = "prenom";
	public static final int NUM_CONT_PRENOM = 2;
	public static final String CONT_MAIL = "mail";
	public static final int NUM_CONT_MAIL = 3;
	public static final String CONT_TEL = "nom";
	public static final int NUM_CONT_TEL = 4;
	
	private SQLiteDatabase bdd;
	private Database maBdd;
	
	public ContactBdd(Context context){
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
	 * @brief ins�re un contact dans la base de donn�es
	 * @param le contact � ins�rer
	 */
	public long insertContact(Contact contact){
		ContentValues values = new ContentValues();
		values.put(CONT_ID, contact.getId());
		values.put(CONT_NOM, contact.getNom());
		values.put(CONT_PRENOM, contact.getPrenom());
		values.put(CONT_MAIL, contact.getMail());
		values.put(CONT_TEL, contact.getTel());
		return bdd.insert(TABLE_CONTACT, null, values);
	}

	/**
	 * @brief met � jour un contact dans la base de donn�es
	 * @param le contact � mettre � jour
	 */
	public int updateContact(Contact contact){
		ContentValues values = new ContentValues();
		values.put(CONT_NOM, contact.getNom());
		values.put(CONT_PRENOM, contact.getPrenom());
		values.put(CONT_MAIL, contact.getMail());
		values.put(CONT_TEL, contact.getTel());
		return bdd.update(TABLE_CONTACT, values, CONT_ID + " = " + contact.getId(), null);
	}

	/**
	 * @brief supprime un contact dans la base de donn�es
	 * @param le contact � supprimer
	 */
	public int removeContact(Contact contact){
		return bdd.delete(TABLE_CONTACT, CONT_ID + " = " + contact.getId(), null);
	}

	/**
	 * @param l'identifiant du contact
	 * @return le contact contenu en base de donn�es correspondant � l'identifiant pass� en param�tre
	 */
	public Contact getContactById(int id){
		Cursor c = bdd.query( TABLE_CONTACT, new String[] {CONT_ID, CONT_NOM, CONT_PRENOM, CONT_MAIL, CONT_TEL}, CONT_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Contact contact = new Contact();
		contact.setId(c.getInt(NUM_CONT_ID));
		contact.setNom(c.getString(NUM_CONT_NOM));
		contact.setPrenom(c.getString(NUM_CONT_PRENOM));
		contact.setMail(c.getString(NUM_CONT_MAIL));
		contact.setTel(c.getString(NUM_CONT_TEL));
		c.close();
		return contact;
	}
}