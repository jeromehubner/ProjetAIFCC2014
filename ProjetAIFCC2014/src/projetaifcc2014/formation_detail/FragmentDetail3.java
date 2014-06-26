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

public class FragmentDetail3 extends FragmentDetail{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_formation_detail_3, container, false);

		idFormation = ((Activity_drawer) getActivity()).getIdFormation();

		/* 
		 * Si le contenu de l'onglet Modalités est null,
		 * on accède à la base pour récupérer la formation correspondante
		 * à l'identifiant passé via un Bundle  
		 */
		if(contentProgramme == null){

			FormationBdd formationBdd = new FormationBdd(getActivity());

			formationBdd.open();			
			Formation formation = formationBdd.getFormationById(idFormation);
			formationBdd.close();

			couleurDepartement = getHTMLCouleurDepartement(formation);
			
			contentProgramme = htmlContentInit(formation);
		}

		WebView metiersVises = (WebView)view.findViewById(R.id.programme);
		metiersVises.loadDataWithBaseURL(null, contentProgramme, "text/html","utf-8", null);

		return view;
	}
}
