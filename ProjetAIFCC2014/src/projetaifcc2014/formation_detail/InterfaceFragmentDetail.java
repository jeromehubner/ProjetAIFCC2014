package projetaifcc2014.formation_detail;

import projetaifcc2014.database.formation.Formation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface InterfaceFragmentDetail {
	
	abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	
	/**
	 * Méthode qui initialise le contenu de la formation à afficher. 
	 * @param htmlString
	 * @return Le texte avec seulement les titres (les balises <h2> sont convertit en <h3>).
	 */
	abstract String htmlContentInit(Formation formation);
	
	abstract String getHTMLCouleurDepartement(Formation formation);

}
