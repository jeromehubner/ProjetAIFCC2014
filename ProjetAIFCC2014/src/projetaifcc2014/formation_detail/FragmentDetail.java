package projetaifcc2014.formation_detail;

import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.drawer.Activity_drawer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.projetaifcc2014.R;


public class FragmentDetail extends Fragment implements InterfaceFragmentDetail{

	protected int idFormation;
	protected String emailFormation;
	protected String contentMetiersVises;
	protected String contentModalités;
	protected String contentProgramme;
	protected final String contentEmpty = "Pas d'informations disponibles pour le moment...";
	protected String couleurDepartement;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_formation_detail_1, container, false);

		// On récupère l'id de la formation de l'activité qui a inflaté la fragment (Activity_Drawer) 
 		idFormation = ((Activity_drawer) getActivity()).getIdFormation();
		
		/* 
		 * Si le contenu de l'onglet Métiers visés est null,
		 * on accède à la base pour récupérer la formation correspondante
		 * à l'identifiant passé via un Bundle  
		 */
		if(contentMetiersVises == null){

			FormationBdd formationBdd = new FormationBdd(getActivity());
			
			formationBdd.open();			
			Formation formation = formationBdd.getFormationById(idFormation);
			formationBdd.close();
			
			contentMetiersVises = formation.getMetiers();
			
			if(contentMetiersVises.equals(""))
				contentMetiersVises = contentEmpty;
				
		}

		WebView metiersVises = (WebView)view.findViewById(R.id.metiers_vises);
		metiersVises.loadDataWithBaseURL(null, contentMetiersVises, "text/html","utf-8", null);
		
		return view;
	}
	
	@Override
	public String htmlContentInit(Formation formation)
	{
		String tagDebut = "<font color="+couleurDepartement+">", tagFin = "</font>";
		String htmlString = formation.getProgramme();
		
		String temp = htmlString.replaceAll("<h2>", "<h3>");

		htmlString = temp.replaceAll("<ul>", "<!--");
		temp = htmlString.replaceAll("</ul>", "-->");

		if(temp.equals(""))
			return contentEmpty;
		else 
			return tagDebut+temp+tagFin;
	}


	@Override
	public String getHTMLCouleurDepartement(Formation formation)
	{
		int indexArrayColor = formation.getCategorie().getDepartement().getId() -1;

		// On récupère le tableau des couleurs de départements renseignés dans le fichier color.xml
		int[] colorArray = getResources().getIntArray(R.array.dpt_color);

		String couleurTemp = Integer.toHexString(colorArray[indexArrayColor]);

		return couleurTemp.replace("ff", "#");
	}
}
