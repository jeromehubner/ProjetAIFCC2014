package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	// Version base de données
	public static final int DATABASE_VERSION = 1;
	
	// Nom base de données
	public static final String DATABASE = "aifcc";
	
	// Requetes creation base
	private static final String CREATE_TABLE_PROFIL = 
		"CREATE TABLE " + ProfilBdd.TABLE_PROFIL + " ("
			+ ProfilBdd.PRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ProfilBdd.PRO_NOM + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_USER = 
		"CREATE TABLE " + UserBdd.TABLE_USER + " ("
			+ UserBdd.USE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ UserBdd.USE_NOM + " TEXT NOT NULL, "
			+ UserBdd.USE_PRENOM + " TEXT NOT NULL, "
			+ UserBdd.USE_LOGIN + " TEXT NOT NULL, "
			+ UserBdd.USE_MDP + " TEXT NOT NULL, "
			+ UserBdd.USE_PROFIL + " INTEGER DEFAULT 0);";
	
	public Database(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PROFIL);
		db.execSQL(CREATE_TABLE_USER);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + UserBdd.TABLE_USER + ";");
		db.execSQL("DROP TABLE " + ProfilBdd.TABLE_PROFIL + ";");
		onCreate(db);
	}
	
}