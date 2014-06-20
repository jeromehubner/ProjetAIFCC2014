package projetaifcc2014.database.handler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.database.temp.FormationToCategorie.FormationToCategorieBdd;
import android.app.Activity;
import android.content.res.AssetManager;
import android.text.Html;


/**
 * Cette classe a pour but de parser le fichier table_formation.xml.
 * Elle permet d'initialiser certaines tables de la DataBase SQLite.
 * @author jerome
 *
 */
public class TableFormationHandler extends DefaultHandler{

	private Activity mainActivity;
	private String fichierXML = "table_formation.xml";

	/*------- Les tags du fichiers XML -------*/
	private final String tagTable = "table";
	private final String tagColumn = "column";
	private final String tagName = "name";


	/*------- Nom des colonnes de la table FORMATION -------*/
	private final String columnId = "id";
	private final String columnDiplome = "diplome";
	private final String columnObjectifPro = "objectif_pro";
	private final String columnObjectifPedago = "objectif_pedago";
	private final String columnAcces = "acces";
	private final String columnFinancement = "financement";
	private final String columnValidation = "validation";
	private final String columnLieu = "lieu_formation";
	private final String columnPoursuites = "poursuite_etudes";
	private final String columnMetiers = "metiers";
	private final String columnProgramme = "programme";
	private final String columnEmail = "email";
	private final String columnActif = "actif";
	/*------------------------------------------------------*/


	/*------- Flag permettant de savoir où en est le parseur dans le flux XML -------*/
	private boolean inTagTable = false;
	private boolean inColumnId = false;
	private boolean inColumnDiplome = false;
	private boolean inColumnObjectifPro = false;
	private boolean inColumnObjectifPedago = false;
	private boolean inColumnAcces = false;
	private boolean inColumnFinancement = false;
	private boolean inColumnValidation = false;
	private boolean inColumnLieu = false;
	private boolean inColumnPoursuites = false;
	private boolean inColumnMetiers = false;
	private boolean inColumnProgramme = false;
	private boolean inColumnEmail = false;
	private boolean inColumnActif = false;
	/*-------------------------------------------------------------------------------*/


	/*------- Variables de la classe Formation -------*/
	private int id;
	private String diplome;
	private String objectifPro;
	private String objectifPedago;
	private String acces;
	private String financement;
	private String validation;
	private String lieu;
	private String poursuites;
	private String metiers;
	private String programme;
	private String email;
	private String actif;
	/*------------------------------------------------*/


	private Formation formation;
	private FormationBdd formationBdd;
	private FormationToCategorieBdd formToCatBdd;


	public TableFormationHandler(Activity mainActivity) {
		this.mainActivity = mainActivity;

		formation = new Formation();
		formationBdd = new FormationBdd(mainActivity);

		formToCatBdd = new FormationToCategorieBdd(mainActivity);

		initChamps();	
	}

	private void initChamps(){
		id = 0;
		diplome = "";
		objectifPro = "";
		objectifPedago = "";
		acces ="";
		financement = "";
		validation = "";
		lieu = "";
		poursuites = "";
		metiers = "";	
		programme = "";	
		email = "";
		actif = "";
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
		formationBdd.open();
		formToCatBdd.open();
	}	


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// Le tag == table
		if(qName.equals(tagTable))
			inTagTable = true;

		if(inTagTable){
			// Le tag == column
			if(qName.equals(tagColumn)){

				if(attributes.getValue(tagName).equals(columnId))
					inColumnId = true;
				else
					inColumnId = false;

				if(attributes.getValue(tagName).equals(columnDiplome))
					inColumnDiplome = true;
				else
					inColumnDiplome = false;

				if(attributes.getValue(tagName).equals(columnObjectifPro))
					inColumnObjectifPro = true;
				else
					inColumnObjectifPro = false;

				if(attributes.getValue(tagName).equals(columnObjectifPedago))
					inColumnObjectifPedago = true;
				else
					inColumnObjectifPedago = false;

				if(attributes.getValue(tagName).equals(columnAcces))
					inColumnAcces = true;
				else
					inColumnAcces = false;

				if(attributes.getValue(tagName).equals(columnFinancement))
					inColumnFinancement = true;
				else
					inColumnFinancement = false;

				if(attributes.getValue(tagName).equals(columnValidation))
					inColumnValidation = true;
				else
					inColumnValidation = false;

				if(attributes.getValue(tagName).equals(columnLieu))
					inColumnLieu = true;
				else
					inColumnLieu = false;

				if(attributes.getValue(tagName).equals(columnPoursuites))
					inColumnPoursuites = true;
				else
					inColumnPoursuites = false;

				if(attributes.getValue(tagName).equals(columnMetiers))
					inColumnMetiers = true;
				else
					inColumnMetiers = false;

				if(attributes.getValue(tagName).equals(columnProgramme))
					inColumnProgramme = true;
				else
					inColumnProgramme = false;

				if(attributes.getValue(tagName).equals(columnEmail))
					inColumnEmail = true;
				else
					inColumnEmail = false;

				if(attributes.getValue(tagName).equals(columnActif))
					inColumnActif = true;
				else
					inColumnActif = false;
			}
		}
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {


		/*
		 * Cette ligne permet de convertir les données récupérées en de html en String
		 * Supprime les /n inutiles
		 */
		String chars = Html.fromHtml(new String(ch).substring(start, start + length)).toString();


		if(inColumnId){
			id = Integer.valueOf(chars);
			inColumnId = false;
		}

		if(inColumnDiplome){
			diplome += chars;
			//			inColumnDiplome = false;
		}

		if(inColumnObjectifPro){
			objectifPro += chars;
			//			inColumnObjectifPro = false;
		}

		if(inColumnObjectifPedago){
			objectifPedago += chars;
			//			inColumnObjectifPedago = false;
		}

		if(inColumnAcces){
			acces += chars;
			//			inColumnAcces = false;
		}

		if(inColumnFinancement){
			financement += chars;
			//			inColumnFinancement = false;
		}

		if(inColumnValidation){
			validation += chars;
			//			inColumnValidation = false;
		}

		if(inColumnLieu){
			lieu += chars;
			//			inColumnLieu = false;
		}

		if(inColumnPoursuites){
			poursuites += chars;
			//			inColumnPoursuites = false;
		}

		if(inColumnMetiers){
			metiers += chars;
			//			inColumnMetiers = false;
		}

		if(inColumnProgramme){
			programme += chars;
			//			inColumnProgramme = false;
		}

		if(inColumnEmail){
			email += chars;
			//			inColumnEmail = false;
		}

		if(inColumnActif){
			actif += chars;
			//			inColumnActif = false;
			//
			//			if(actif.equals("en ligne"))
			//			{
			//				formation.setId(id);
			//				formation.setDiplome(diplome);
			//				formation.setCategorie(formToCatBdd.getCategorieByIdFormation(id));
			//				formation.setObjectifPro(objectifPro);
			//				formation.setObjectifPedago(objectifPedago);
			//				formation.setAcces(acces);
			//				formation.setFinancement(financement);
			//				formation.setValidation(validation);
			//				formation.setLieu(lieu);
			//				formation.setPoursuites(poursuites);
			//				formation.setMetiers(metiers);
			//				formation.setProgramme(programme);
			//				formation.setEmail(email);
			//
			//				formationBdd.insertFormation(formation);
			//
			//				initChamps();
			//			}
		}

	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(qName.equals(tagTable)){
			if(actif.equalsIgnoreCase("en ligne")){

				formation.setId(id);
				formation.setDiplome(diplome);
				formation.setCategorie(formToCatBdd.getCategorieByIdFormation(id));
				formation.setObjectifPro(objectifPro);
				formation.setObjectifPedago(objectifPedago);
				formation.setAcces(acces);
				formation.setFinancement(financement);
				formation.setValidation(validation);
				formation.setLieu(lieu);
				formation.setPoursuites(poursuites);
				formation.setMetiers(metiers);
				formation.setProgramme(programme);
				formation.setEmail(email);

				formationBdd.insertFormation(formation);
			}

			initChamps();
		}
	}


	@Override
	public void endDocument() throws SAXException {
		formationBdd.close();
		formToCatBdd.close();
	}
}
