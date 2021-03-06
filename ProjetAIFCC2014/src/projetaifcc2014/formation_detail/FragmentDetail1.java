package projetaifcc2014.formation_detail;

import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.drawer.Activity_drawer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.projetaifcc2014.R;


public class FragmentDetail1 extends FragmentDetail{

	private int idFormation;
	private String contentMetiersVises;
	private final String contentEmpty = "Pas d'informations disponibles pour le moment...";


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
}
