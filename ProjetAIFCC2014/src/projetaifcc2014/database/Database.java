package projetaifcc2014.database;

import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.contact.ContactBdd;
import projetaifcc2014.database.departement.DepartementBdd;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.database.temp.CategorieToCategorie.CategorieToCategorieBdd;
import projetaifcc2014.database.temp.FormationToCategorie.FormationToCategorieBdd;
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
					+ CategorieBdd.CAT_DEPARTEMENT + " INTEGER DEFAULT 0, "
					+ CategorieBdd.CAT_LIBELLE + " TEXT);";

	private static final String CREATE_TABLE_CONTACT = 
			"CREATE TABLE " + ContactBdd.TABLE_CONTACT + " ("
					+ ContactBdd.CONT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ ContactBdd.CONT_NOM + " TEXT, "
					+ ContactBdd.CONT_PRENOM + " TEXT, "
					+ ContactBdd.CONT_MAIL + " TEXT, "
					+ ContactBdd.CONT_TEL + " TEXT);";

	private static final String CREATE_TABLE_DEPARTEMENT = 
			"CREATE TABLE " + DepartementBdd.TABLE_DEPARTEMENT + " ("
					+ DepartementBdd.DEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ DepartementBdd.DEP_LIBELLE + ");";

	private static final String CREATE_TABLE_FORMATION = 
			"CREATE TABLE " + FormationBdd.TABLE_FORMATION + " ("
					+ FormationBdd.FOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ FormationBdd.FOR_CATEGORIE + " INTEGER, "
					+ FormationBdd.FOR_DIPLOME + " TEXT, "
					+ FormationBdd.FOR_OBJECTIFPRO + " TEXT, "
					+ FormationBdd.FOR_OBJECTIFPEDAGO + " TEXT, "
					+ FormationBdd.FOR_ACCES + " TEXT, "
					+ FormationBdd.FOR_FINANCEMENT + " TEXT, "
					+ FormationBdd.FOR_VALIDATION + " TEXT, "
					+ FormationBdd.FOR_LIEU + " TEXT, "
					+ FormationBdd.FOR_POURSUITES + " TEXT, "
					+ FormationBdd.FOR_METIERS + " TEXT, "
					+ FormationBdd.FOR_PROGRAMME + " TEXT, "
					+ FormationBdd.FOR_EMAIL + " TEXT);";


	// Tables temporaires
	private static final String CREATE_TABLE_CATEGORIE_TO_CATEGORIE = 
			"CREATE TABLE " + CategorieToCategorieBdd.TABLE_CATEGORIE_TO_CATEGORIE + " ("
					+ CategorieToCategorieBdd.CAT_TO_CAT_ID_CATEGORIE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ CategorieToCategorieBdd.CAT_TO_CAT_ID_DEPARTEMENT + " INTEGER);";

	private static final String CREATE_TABLE_FORMATION_TO_CATEGORIE = 
			"CREATE TABLE " + FormationToCategorieBdd.TABLE_FORMATION_TO_CATEGORIE + " ("
					+ FormationToCategorieBdd.FORM_TO_CAT_ID_FORMATION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ FormationToCategorieBdd.FORM_TO_CAT_ID_CATEGORIE + " INTEGER);";



	public Database(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_CATEGORIE_TO_CATEGORIE);
		db.execSQL(CREATE_TABLE_CATEGORIE);
		db.execSQL(CREATE_TABLE_FORMATION_TO_CATEGORIE);
		db.execSQL(CREATE_TABLE_FORMATION);
		db.execSQL(CREATE_TABLE_CONTACT);
		db.execSQL(CREATE_TABLE_DEPARTEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + CategorieToCategorieBdd.TABLE_CATEGORIE_TO_CATEGORIE + ";");
		db.execSQL("DROP TABLE " + CategorieBdd.TABLE_CATEGORIE + ";");
		db.execSQL("DROP TABLE " + FormationToCategorieBdd.TABLE_FORMATION_TO_CATEGORIE + ";");
		db.execSQL("DROP TABLE " + FormationBdd.TABLE_FORMATION + ";");		
		db.execSQL("DROP TABLE " + DepartementBdd.TABLE_DEPARTEMENT + ";");
		db.execSQL("DROP TABLE " + ContactBdd.TABLE_CONTACT + ";");
		onCreate(db);
	}
}