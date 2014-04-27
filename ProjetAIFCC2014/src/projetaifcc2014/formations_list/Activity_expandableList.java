package projetaifcc2014.formations_list;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

public class Activity_expandableList extends ExpandableListActivity
{
	// Create ArrayList to hold parent Items and Child Items
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);


		// Create Expandable List and set it's properties
		ExpandableListView expandableList = getExpandableListView(); 
		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		// Set the Items of Parent
		setGroupParents();
		// Set The Child Data
		setChildData();

		// Create the Adapter
		MyExpandableAdapter adapter = new MyExpandableAdapter(parentItems, childItems);

		adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);

		// Set the Adapter to expandableList
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(this);
	}

	// method to add parent Items
	public void setGroupParents() 
	{
		parentItems.add("Commerce - Vente");
		parentItems.add("Comptabilite - Gestion");
		parentItems.add("Communication");
		parentItems.add("Informatique");
		parentItems.add("Tourisme");
		parentItems.add("Secretariat - organisation");
		parentItems.add("Ressources Humaines");
	}

	// method to set child data of each parent
	public void setChildData() 
	{

		// Add Child Items for Commerce - Vente
		ArrayList<String> child = new ArrayList<String>();
		child.add("Responsable Manager de la Distribution");
		child.add("Responsable de développement commercial");
		child.add("BTS Management des Unités Commerciales (MUC)");
		child.add("Gestionnaire des Unités Commerciales");
		child.add("BTS Négociation et Relation Client (NRC)");
		child.add("Vendeur conseiller commercial");
		child.add("Conseiller relation client à distance");
		child.add("CAP Employé de commerce");

		childItems.add(child);

		// Add Child Items for Compatibilit� - Gestion
		child = new ArrayList<String>();
		child.add("Licence Gestion d'Entreprise");
		child.add("BTS Comptabilité et Gestion des Organisations (CGO)");
		child.add("BTS Assistant de Gestion PME-PMI (BTS AG)");
		child.add("Gestionnaire de Paie, option RH");
		child.add("Comptable assistant");
		child.add("Secrétaire comptable");
		child.add("Formation individualisée comptabilité - paie");

		childItems.add(child);

		// Add Child Items for Communication
		child = new ArrayList<String>();
		child.add("BTS Communication");

		childItems.add(child);

		// Add Child Items for Informatique
		child = new ArrayList<String>();
		child.add("Conception et développement de systèmes d'information - Option mobile");
		child.add("BTS Services Informatiques aux Organisations, options Solutions d'infrastructures, systèmes et réseaux & Solutions logicielles et applications métiers");

		childItems.add(child);

		// Add Child Items for Tourisme
		child = new ArrayList<String>();
		child.add("BTS Tourisme");

		childItems.add(child);

		// Add Child Items for Secretariat - organisation
		child = new ArrayList<String>();
		child.add("BTS Assistant de Manager (AM)");
		child.add("BTS Assistant de Gestion PME-PMI");
		child.add("Assistante de Direction Titre professionnel");
		child.add("Secrétaire assistant");

		childItems.add(child);

		// Add Child Items for Ressources Humaines
		child = new ArrayList<String>();
		child.add("Chargé des Ressources Humaines Titre Professionnel");
		child.add("Gestionnaire de Paie, option RH");

		childItems.add(child);

	}

}