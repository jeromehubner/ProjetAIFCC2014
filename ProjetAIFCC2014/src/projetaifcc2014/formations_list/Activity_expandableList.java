package projetaifcc2014.formations_list;

import java.util.ArrayList;

import projetaifcc2014.drawer.Activity_drawer;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
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
		child.add("Responsable de d�veloppement commercial");
		child.add("BTS Management des Unit�s Commerciales (MUC)");
		child.add("Gestionnaire des Unit�s Commerciales");
		child.add("BTS N�gociation et Relation Client (NRC)");
		child.add("Vendeur conseiller commercial");
		child.add("Conseiller relation client � distance");
		child.add("CAP Employ� de commerce");

		childItems.add(child);

		// Add Child Items for Compatibilit� - Gestion
		child = new ArrayList<String>();
		child.add("Licence Gestion d'Entreprise");
		child.add("BTS Comptabilit� et Gestion des Organisations (CGO)");
		child.add("BTS Assistant de Gestion PME-PMI (BTS AG)");
		child.add("Gestionnaire de Paie, option RH");
		child.add("Comptable assistant");
		child.add("Secr�taire comptable");
		child.add("Formation individualis�e comptabilit� - paie");

		childItems.add(child);

		// Add Child Items for Communication
		child = new ArrayList<String>();
		child.add("BTS Communication");

		childItems.add(child);

		// Add Child Items for Informatique
		child = new ArrayList<String>();
		child.add("Conception et d�veloppement de syst�mes d'information - Option mobile");
		child.add("BTS Services Informatiques aux Organisations, options Solutions d�infrastructures, syst�mes et r�seaux & Solutions logicielles et applications m�tiers");

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
		child.add("Secr�taire assistant");

		childItems.add(child);

		// Add Child Items for Ressources Humaines
		child = new ArrayList<String>();
		child.add("Charg� des Ressources Humaines Titre Professionnel");
		child.add("Gestionnaire de Paie, option RH");

		childItems.add(child);

	}

}