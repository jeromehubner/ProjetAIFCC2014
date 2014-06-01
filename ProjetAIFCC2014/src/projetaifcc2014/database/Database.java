package projetaifcc2014.database;

import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.contact.ContactBdd;
import projetaifcc2014.database.departement.DepartementBdd;
import projetaifcc2014.database.formation.FormationBdd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	// Version base de donnees
	public static final int DATABASE_VERSION = 1;

	// Nom base de donnees
	public static final String DATABASE = "aifcc";

	// Requetes creation base
	private static final String CREATE_TABLE_CATEGORIE = 
		"CREATE TABLE " + CategorieBdd.TABLE_CATEGORIE + " ("
			+ CategorieBdd.CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CategorieBdd.CAT_LIBELLE + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_CONTACT = 
		"CREATE TABLE " + ContactBdd.TABLE_CONTACT + " ("
			+ ContactBdd.CONT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ContactBdd.CONT_NOM + " TEXT NOT NULL, "
			+ ContactBdd.CONT_PRENOM + " TEXT NOT NULL, "
			+ ContactBdd.CONT_MAIL + " TEXT NOT NULL, "
			+ ContactBdd.CONT_TEL + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_DEPARTEMENT = 
		"CREATE TABLE " + DepartementBdd.TABLE_DEPARTEMENT + " ("
			+ DepartementBdd.DEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DepartementBdd.DEP_LIBELLE + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_FORMATION = 
		"CREATE TABLE " + FormationBdd.TABLE_FORMATION + " ("
			+ FormationBdd.FOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ FormationBdd.FOR_CATEGORIE + " INTEGER DEFAULT 0, "
			+ FormationBdd.FOR_DEPARTEMENT + " INTEGER DEFAULT 0, "
			+ FormationBdd.FOR_DIPLOME + " TEXT NOT NULL, "
			+ FormationBdd.FOR_NIVEAU + " TEXT NOT NULL, "
			+ FormationBdd.FOR_METIERS + " TEXT NOT NULL);";
					
	public Database(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_CATEGORIE);
		db.execSQL(CREATE_TABLE_CONTACT);
		db.execSQL(CREATE_TABLE_DEPARTEMENT);
		db.execSQL(CREATE_TABLE_FORMATION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + CategorieBdd.TABLE_CATEGORIE + ";");
		db.execSQL("DROP TABLE " + DepartementBdd.TABLE_DEPARTEMENT + ";");
		db.execSQL("DROP TABLE " + ContactBdd.TABLE_CONTACT + ";");
		db.execSQL("DROP TABLE " + FormationBdd.TABLE_FORMATION + ";");
		onCreate(db);
	}

}