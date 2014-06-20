package projetaifcc2014.database.handler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import projetaifcc2014.database.temp.CategorieToCategorie.CategorieToCategorie;
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
public class TableCategorieToCategorieHandler extends DefaultHandler {

	private Activity mainActivity;
	private final String fichierXML = "table_categorie_to_categorie.xml";


	/*------- Nom des colonnes de la table CATEGORIE -------*/
	private final String columnIdDepartement = "cat_nivo1_id";
	private final String columnIdCategorie = "cat_nivo2_id";
	/*------------------------------------------------------*/


	/*------- Flag permettant de savoir où en est le parseur dans le flux XML -------*/
	private boolean inTagTable = false;
	private boolean inColumnIdDepartement = false;
	private boolean inColumnIdCategorie = false;
	/*-------------------------------------------------------------------------------*/

	
	private int idDepartement;

	private CategorieToCategorie catToCat;
	private CategorieToCategorieBdd catToCatBdd;


	public TableCategorieToCategorieHandler(Activity mainActivity) {
		this.mainActivity = mainActivity;

		catToCat = new CategorieToCategorie();
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

				// Le nom de colonne == cat_nivo1_id
				if(attributes.getValue("name").equals(columnIdDepartement))
					inColumnIdDepartement = true;
				else
					inColumnIdDepartement = false;

				// Le nom de colonne == cat_nivo2_id
				if(attributes.getValue("name").equals(columnIdCategorie))
					inColumnIdCategorie = true;
				else
					inColumnIdCategorie = false;
			}
		}
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String chars = new String(ch).substring(start, start + length);

		if(inColumnIdDepartement){
			idDepartement = Integer.valueOf(chars);
			inColumnIdDepartement = false;
		}

		if(inColumnIdCategorie){
			catToCat.setIdDepartement(idDepartement);
			catToCat.setIdCategorie(Integer.valueOf(chars));

			catToCatBdd.insertCatToCat(catToCat);

			inColumnIdCategorie = false;	
		}
	}

	@Override
	public void endDocument() throws SAXException {
		catToCatBdd.close();
	}
}
