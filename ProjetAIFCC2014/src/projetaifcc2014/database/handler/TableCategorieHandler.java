package projetaifcc2014.database.handler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.departement.Departement;
import projetaifcc2014.database.departement.DepartementBdd;
import projetaifcc2014.database.temp.CategorieToCategorie.CategorieToCategorieBdd;
import android.app.Activity;
import android.content.res.AssetManager;


/**
 * Cette classe a pour but de parser le fichier table_categorie_and_formation.xml
 * Ce fichier contient le nom des départements et catégories.
 * L'idCategorie contenu dans cette table sera une clé étrangère de
 * la table_formation_to_categorie.
 * Elle permet d'initialiser certaines tables de la DataBase SQLite.
 * @author jerome
 *
 */
public class TableCategorieHandler extends DefaultHandler {

	private Activity mainActivity;
	private final String fichierXML = "table_categorie.xml";
	

	/*------- Nom des colonnes de la table CATEGORIE -------*/
	private final String columnIdCategorie = "id_categorie";
	private final String columnNiveau = "niveau";
	private final String columnNom = "nom";
	/*------------------------------------------------------*/


	/*------- Flag permettant de savoir où en est le parseur dans le flux XML -------*/
	private boolean inTagTable = false;

	private boolean inColumnIdCategorie = false;
	private boolean inColumnNiveau = false;
	private boolean inColumnNom = false;
	/*-------------------------------------------------------------------------------*/

	private int id;
	private int niveau;

	private Departement departement;
	private Categorie categorie;

	private DepartementBdd departementBdd;
	private CategorieBdd categorieBdd;
	private CategorieToCategorieBdd catToCatBdd;


	public TableCategorieHandler(Activity mainActivity) {
		this.mainActivity = mainActivity;

		departement = new Departement();
		categorie = new Categorie();

		departementBdd = new DepartementBdd(mainActivity);
		categorieBdd = new CategorieBdd(mainActivity);
		catToCatBdd = new CategorieToCategorieBdd(mainActivity);
	}


	public void processFeed(){
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setContentHandler(this);

			AssetManager assetManager = mainActivity.getBaseContext().getAssets();
			InputStream inputStream = assetManager.open(fichierXML);

			reader.parse(new InputSource(inputStream));			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		departementBdd.open();
		categorieBdd.open();
		catToCatBdd.open();
	}
	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// Le tag == table
		if(localName.equals("table"))
			inTagTable = true;

		if(inTagTable){

			// Le tag == column
			if(localName.equals("column")){

				// Le nom de colonne == id_categorie
				if(attributes.getValue("name").equals(columnIdCategorie))
					inColumnIdCategorie = true;
				else
					inColumnIdCategorie = false;

				if(attributes.getValue("name").equals(columnNiveau))
					inColumnNiveau = true;
				else
					inColumnNiveau = false;

				if(attributes.getValue("name").equals(columnNom))
					inColumnNom = true;
				else
					inColumnNom = false;
			}
		}
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String chars = new String(ch).substring(start, start + length);

		if(inColumnIdCategorie){
			id = Integer.valueOf(chars);
			inColumnIdCategorie = false;
		}

		if(inColumnNiveau){
			niveau = Integer.valueOf(chars);			
			inColumnNiveau = false;
		}

		if(inColumnNom){
			// Si le niveau de catégorie == 1 => c'est un département
			if(niveau == 1){
				departement.setId(id);
				departement.setLibelle(chars);
				departementBdd.insertDepartement(departement);
			}
			// Si le niveau de catégorie == 2 => c'est une catégorie
			else if (niveau == 2){
				categorie.setId(id);
				categorie.setLibelle(chars);
				
				categorie.setDepartement(catToCatBdd.getDepartementByIdCategorie(id));
				
				categorieBdd.insertCategorie(categorie);
			}
			niveau = 0;
		}
	}


	@Override
	public void endDocument() throws SAXException {
		departementBdd.close();
		categorieBdd.close();
		catToCatBdd.close();
	}
}