package projetaifcc2014.database.formation;

import java.util.ArrayList;

import projetaifcc2014.database.Bdd;
import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.departement.Departement;
import projetaifcc2014.database.departement.DepartementBdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class FormationBdd extends Bdd{

	// Nom de la table
	public static final String TABLE_FORMATION = "formation";

	// Colonnes table : departement
	//TODO : mettre les même nom que la table de la base sur PHPMYADMIN
	public static final String FOR_ID = "id";
	public static final int NUM_FOR_ID = 0;
	public static final String FOR_CATEGORIE = "categorie";
	public static final int NUM_FOR_CATEGORIE = 1;
	public static final String FOR_DIPLOME = "diplome";
	public static final int NUM_FOR_DIPLOME = 2;
	public static final String FOR_OBJECTIFPRO = "objectif_pro";
	public static final int NUM_FOR_OBJECTIFPRO = 3;
	public static final String FOR_OBJECTIFPEDAGO = "objectif_pedago";
	public static final int NUM_FOR_OBJECTIFPEDAGO = 4;
	public static final String FOR_ACCES = "acces";
	public static final int NUM_FOR_ACCES = 5;
	public static final String FOR_FINANCEMENT = "financement";
	public static final int NUM_FOR_FINANCEMENT = 6;
	public static final String FOR_VALIDATION = "validation";
	public static final int NUM_FOR_VALIDATION = 7;
	public static final String FOR_LIEU = "lieu";
	public static final int NUM_FOR_LIEU = 8;
	public static final String FOR_POURSUITES = "poursuites";
	public static final int NUM_FOR_POURSUITES = 9;
	public static final String FOR_METIERS = "metiers";
	public static final int NUM_FOR_METIERS = 10;
	public static final String FOR_PROGRAMME = "programme";
	public static final int NUM_FOR_PROGRAMME = 11;
	public static final String FOR_EMAIL = "email";
	public static final int NUM_FOR_EMAIL = 12;

	public FormationBdd(Context context){
		super(context);
	}

	/**
	 * @brief insere une formation dans la base de donnees
	 * @param la formation a inserer
	 */
	public long insertFormation(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_ID, formation.getId());
		values.put(FOR_CATEGORIE, formation.getCategorie().getId());
		values.put(FOR_DIPLOME, formation.getDiplome());
		values.put(FOR_OBJECTIFPRO, formation.getObjectifPro());
		values.put(FOR_OBJECTIFPEDAGO, formation.getObjectifPedago());
		values.put(FOR_ACCES, formation.getAcces());
		values.put(FOR_FINANCEMENT, formation.getFinancement());
		values.put(FOR_VALIDATION, formation.getValidation());
		values.put(FOR_LIEU, formation.getLieu());
		values.put(FOR_POURSUITES, formation.getPoursuites());
		values.put(FOR_METIERS, formation.getMetiers());
		values.put(FOR_PROGRAMME, formation.getProgramme());
		values.put(FOR_EMAIL, formation.getEmail());
		return bdd.insert(TABLE_FORMATION, null, values);
	}

	/**
	 * @brief met a jour une formation dans la base de donnees
	 * @param la formation a mettre a jour
	 */
	public int updateFormation(Formation formation){
		ContentValues values = new ContentValues();
		values.put(FOR_DIPLOME, formation.getDiplome());
		values.put(FOR_CATEGORIE, formation.getCategorie().getId());
		values.put(FOR_OBJECTIFPRO, formation.getObjectifPro());
		values.put(FOR_OBJECTIFPEDAGO, formation.getObjectifPedago());
		values.put(FOR_ACCES, formation.getAcces());
		values.put(FOR_FINANCEMENT, formation.getFinancement());
		values.put(FOR_VALIDATION, formation.getValidation());
		values.put(FOR_LIEU, formation.getLieu());
		values.put(FOR_POURSUITES, formation.getPoursuites());
		values.put(FOR_METIERS, formation.getMetiers());
		values.put(FOR_PROGRAMME, formation.getProgramme());
		values.put(FOR_EMAIL, formation.getEmail());
		return bdd.update(TABLE_FORMATION, values, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @brief supprime une formation dans la base de donnees
	 * @param la formation a supprimer
	 */
	public int removeFormation(Formation formation){
		return bdd.delete(TABLE_FORMATION, FOR_ID + " = " + formation.getId(), null);
	}

	/**
	 * @param l'identifiant de la formation
	 * @return la formation contenu en base de donnees correspondant a l'identifiant passe en parametre
	 */
	public Formation getFormationById(int id){
		Cursor c = bdd.query(TABLE_FORMATION, 
						new String[] {FOR_ID, FOR_CATEGORIE, FOR_DIPLOME, FOR_OBJECTIFPRO, FOR_OBJECTIFPEDAGO,
				FOR_ACCES, FOR_FINANCEMENT, FOR_VALIDATION, FOR_LIEU, FOR_POURSUITES, FOR_METIERS, FOR_PROGRAMME, FOR_EMAIL },
										FOR_ID + " = " + id, null, null, null, null);
		if (c.getCount() == 0) return null;
		c.moveToFirst();
		Formation formation = new Formation();
		formation.setId(c.getInt(NUM_FOR_ID));
		CategorieBdd categorieBdd = new CategorieBdd(context);
		if (categorieBdd != null){
			categorieBdd.open();
			formation.setCategorie(categorieBdd.getCategorieById(c.getInt(NUM_FOR_CATEGORIE)));
			categorieBdd.close();
		}
		formation.setDiplome(c.getString(NUM_FOR_DIPLOME));
		formation.setObjectifPro(c.getString(NUM_FOR_OBJECTIFPRO));
		formation.setObjectifPedago(c.getString(NUM_FOR_OBJECTIFPEDAGO));
		formation.setAcces(c.getString(NUM_FOR_ACCES));
		formation.setFinancement(c.getString(NUM_FOR_FINANCEMENT));
		formation.setValidation(c.getString(NUM_FOR_VALIDATION));
		formation.setLieu(c.getString(NUM_FOR_LIEU));
		formation.setPoursuites(c.getString(NUM_FOR_POURSUITES));
		formation.setMetiers(c.getString(NUM_FOR_METIERS));
		formation.setProgramme(c.getString(NUM_FOR_PROGRAMME));
		formation.setEmail(c.getString(NUM_FOR_EMAIL));
		c.close();
		return formation;
	}


	public ArrayList<Formation> getListFormationsByIdCategorie(int idCategorie){

		Cursor c = bdd.query(TABLE_FORMATION, new String[] {FOR_ID, FOR_CATEGORIE, FOR_DIPLOME, FOR_OBJECTIFPRO, FOR_OBJECTIFPEDAGO,
				FOR_ACCES, FOR_FINANCEMENT, FOR_VALIDATION, FOR_LIEU, FOR_POURSUITES, FOR_METIERS, FOR_PROGRAMME, FOR_EMAIL }, FOR_CATEGORIE + " = " + idCategorie, null, null, null, null);
		if (c.getCount() == 0) return null;

		ArrayList<Formation> listFormations = new ArrayList<Formation>(c.getCount());
		CategorieBdd categorieBdd = new CategorieBdd(context);
		
		if (categorieBdd != null)
			categorieBdd.open();

		for(int i = 0; i < c.getCount(); i++)
		{
			c.moveToPosition(i);

			Formation formation = new Formation();
			formation.setId(c.getInt(NUM_FOR_ID));
			formation.setCategorie(categorieBdd.getCategorieById(c.getInt(NUM_FOR_CATEGORIE)));
			formation.setDiplome(c.getString(NUM_FOR_DIPLOME));
			formation.setObjectifPro(c.getString(NUM_FOR_OBJECTIFPRO));
			formation.setObjectifPedago(c.getString(NUM_FOR_OBJECTIFPEDAGO));
			formation.setAcces(c.getString(NUM_FOR_ACCES));
			formation.setFinancement(c.getString(NUM_FOR_FINANCEMENT));
			formation.setValidation(c.getString(NUM_FOR_VALIDATION));
			formation.setLieu(c.getString(NUM_FOR_LIEU));
			formation.setPoursuites(c.getString(NUM_FOR_POURSUITES));
			formation.setMetiers(c.getString(NUM_FOR_METIERS));
			formation.setProgramme(c.getString(NUM_FOR_PROGRAMME));
			formation.setEmail(c.getString(NUM_FOR_EMAIL));

			listFormations.add(formation);
		}
		categorieBdd.close();
		c.close();
		return listFormations;
	}
}