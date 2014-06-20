package projetaifcc2014.expandable_list_formation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.categorie.CategorieBdd;
import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.drawer.Activity_drawer_detail;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class Fragment_list_formation extends Fragment{

	
	private List<Categorie> groupList;
	private Map<Categorie, List<Formation>> categorieCollections;
	private ExpandableListView expListViewFormations;

    private int idDepartment;
    private int couleurDepartement;
    private View vueCourante = null ;
	
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		vueCourante = inflater.inflate(R.layout.expandable_list_main, container, false);
		
		
		// On récupère les différentes catégories de formation
		idDepartment = (Integer)getActivity().getIntent().getExtras().get(getResources().getString(R.string.extra_id_departement));

		/* 
		 * L'index est utilisé pour aller chercher la couleur du département.
		 * -1 car les idDepartments de la table Departememt commence à 1.
		 */
		int indexArrayColor = idDepartment -1;
		
		// On récupère le tableau des couleurs de départements renseignés dans le fichier color.xml
		int[] colorArray = vueCourante.getResources().getIntArray(R.array.dpt_color);
		
		// On récupère la couleur du département
		couleurDepartement = colorArray[indexArrayColor];
		
		
        createGroupList();
        createCollection();
        
        expListViewFormations = (ExpandableListView) vueCourante.findViewById(R.id.formation_list);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(getActivity(), groupList, categorieCollections, couleurDepartement);

        expListViewFormations.setAdapter(expListAdapter);
        
        
        expListViewFormations.setOnChildClickListener(new OnChildClickListener() {
        	 
            public boolean onChildClick(ExpandableListView parent, View vue, int groupPosition, int childPosition, long id) {
                final Formation selected = (Formation) expListAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(vue.getContext(), selected.getDiplome(), Toast.LENGTH_LONG).show();

    			Intent description = new Intent(vueCourante.getContext(),Activity_drawer_detail.class);
    			
    			// On passe à l'Activity_drawer_AIFCC l'id de la formation sélectionnée
    			description.putExtra(getResources().getString(R.string.extra_id_formation),
    								((Formation)expListAdapter.getChild(groupPosition, childPosition)).getId());
    			
    			startActivity(description);
 
                return true;
            }
        });
        
		return vueCourante;
	}
	
 
	private void createGroupList(){
		/*
		 * On accède à la BD pour récupérer la liste des catégories
		 * par l'id du département (celui passé en paramètre)
		 */
		CategorieBdd categorieBdd = new CategorieBdd(vueCourante.getContext());

		categorieBdd.open();

		groupList = categorieBdd.getListCategoriesByIdDepartement(idDepartment); 

		categorieBdd.close();
	}


	private void createCollection() {
		categorieCollections = new LinkedHashMap<Categorie, List<Formation>>();

		FormationBdd formationBdd = new FormationBdd(vueCourante.getContext());
		formationBdd.open();
		
		for (Categorie categorie : groupList)
		{
			categorieCollections.put(categorie, formationBdd.getListFormationsByIdCategorie(categorie.getId()));
		}
		formationBdd.close();
	}
}
