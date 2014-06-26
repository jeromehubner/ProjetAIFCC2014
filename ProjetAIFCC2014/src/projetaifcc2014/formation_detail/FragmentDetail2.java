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

public class FragmentDetail2 extends FragmentDetail{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_formation_detail_2, container, false);

		idFormation = ((Activity_drawer) getActivity()).getIdFormation();


		/* 
		 * Si le contenu de l'onglet Modalités est null,
		 * on accède à la base pour récupérer la formation correspondante
		 * à l'identifiant passé via un Bundle  
		 */
		if(contentModalités == null){

			FormationBdd formationBdd = new FormationBdd(getActivity());

			formationBdd.open();			
			Formation formation = formationBdd.getFormationById(idFormation);			
			formationBdd.close();

			couleurDepartement = getHTMLCouleurDepartement(formation);

			contentModalités = htmlContentInit(formation);

		}

		WebView metiersVises = (WebView)view.findViewById(R.id.modalites);
		metiersVises.loadDataWithBaseURL(null, contentModalités, "text/html","utf-8", null);



		return view;
	}

	@Override
	public String htmlContentInit(Formation formation){
		String temp = "";
		String tagDebut = "<h3><font color="+couleurDepartement+">", tagFin = "</font></h3>";

		if(!formation.getObjectifPro().equals("")){
			// On ajoute le titre en H3 de la couleur du département 
			temp += tagDebut +"Objectifs professionnels"+ tagFin; 
			temp += formation.getObjectifPro();
		}

		if(!formation.getObjectifPedago().equals("")){
			temp += tagDebut +"Objectifs pédagogiques"+ tagFin;
			temp += formation.getObjectifPedago();
		}

		if(!formation.getAcces().equals("")){
			temp += tagDebut +"Conditions accès"+ tagFin;
			temp += formation.getAcces();
		}

		if(!formation.getFinancement().equals("")){
			temp += tagDebut +"Modalités de financement"+ tagFin;
			temp += formation.getFinancement();
		}

		if(!formation.getValidation().equals("")){
			temp += tagDebut +"Validation du diplôme"+ tagFin;
			temp += formation.getValidation();
		}

		if(!formation.getLieu().equals("")){
			temp += tagDebut +"Lieu de formation"+ tagFin;
			temp += formation.getLieu();
		}

		if(!formation.getPoursuites().equals("")){
			temp += tagDebut +"Poursuite d'études"+ tagFin;
			temp += formation.getPoursuites();
		}

		if(temp.equals(""))
			temp = contentEmpty;

		return temp;
	}
}
