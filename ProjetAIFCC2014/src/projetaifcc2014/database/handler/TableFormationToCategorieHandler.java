package projetaifcc2014.database.handler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import projetaifcc2014.database.temp.FormationToCategorie.FormationToCategorie;
import projetaifcc2014.database.temp.FormationToCategorie.FormationToCategorieBdd;
import android.app.Activity;
import android.content.res.AssetManager;

/**
 * Cette classe a pour but de parser le fichier table_formation_to_categorie.xml.
 * Elle permet d'initialiser certaines tables de la DataBase SQLite.
 * @author jerome
 *
 */
public class TableFormationToCategorieHandler extends DefaultHandler {

	private Activity mainActivity;
	private String fichierXML = "table_formation_to_categorie.xml";


	/*------- Nom des colonnes de la table FORMATION_TO_CATEGORIE -------*/
	private final String columnIdCategorie = "id_categorie";
	private final String columnIdFormation = "id_formation";
	/*-------------------------------------------------------------------*/


	/*------- Flag permettant de savoir où en est le parseur dans le flux XML -------*/
	private boolean inTagTable = false;
	private boolean inColumnIdCategorie = false;
	private boolean inColumnIdFormation = false;	
	/*-------------------------------------------------------------------------------*/

	
	private int idCategorie;

	private FormationToCategorie formToCat;
	private FormationToCategorieBdd formToCatBdd;


	public TableFormationToCategorieHandler(Activity mainActivity) {
		this.mainActivity = mainActivity;

		formToCat= new FormationToCategorie();
		formToCatBdd = new FormationToCategorieBdd(mainActivity);
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
		formToCatBdd.open();
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
						if(attributes.getValue("name").equals(columnIdCategorie))
							inColumnIdCategorie = true;
						else
							inColumnIdCategorie = false;

						// Le nom de colonne == cat_nivo2_id
						if(attributes.getValue("name").equals(columnIdFormation))
							inColumnIdFormation = true;
						else
							inColumnIdFormation = false;
					}
				}
	}
	

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		String chars = new String(ch).substring(start, start + length);

		if(inColumnIdCategorie){
			idCategorie = Integer.valueOf(chars);
			inColumnIdCategorie = false;
		}

		if(inColumnIdFormation){
			formToCat.setIdFormation(Integer.valueOf(chars));
			formToCat.setIdCategorie(Integer.valueOf(idCategorie));

			formToCatBdd.insertFormToCat(formToCat);

			inColumnIdFormation = false;	
		}
	}


	@Override
	public void endDocument() throws SAXException {
		formToCatBdd.close();
	}
}