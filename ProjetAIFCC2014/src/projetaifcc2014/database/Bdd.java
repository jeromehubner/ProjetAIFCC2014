package projetaifcc2014.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Bdd {
	
	protected SQLiteDatabase bdd;
	protected Database maBdd;
	protected Context context;
	
	public Bdd(){}
	public Bdd(Context context){
		this.context = context;
		maBdd = new Database(context, Database.DATABASE, null, Database.DATABASE_VERSION);
	}
	/**
	 * @brief ouvre la base de donnees en mode ecriture
	 */
	public void open(){
		bdd = maBdd.getWritableDatabase();
	}

	/**
	 * @brief ferme la base de donnees
	 */
	public void close(){
		bdd.close();
	}
	
	/**
	 * @return la base de donnees SQLite
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}
}