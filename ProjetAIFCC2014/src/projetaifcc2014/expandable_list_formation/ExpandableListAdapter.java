package projetaifcc2014.expandable_list_formation;

import java.util.List;
import java.util.Map;

import projetaifcc2014.database.categorie.Categorie;
import projetaifcc2014.database.formation.Formation;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.projetaifcc2014.R;


public class ExpandableListAdapter extends BaseExpandableListAdapter{

	private Activity context;
	private Map<Categorie, List<Formation>> categorieCollections;
	private List<Categorie> categories;
	private int couleurDepartement;


	public ExpandableListAdapter(Activity context, List<Categorie> categories,
			Map<Categorie, List<Formation>> categorieCollections, int couleurDepartement) {

		this.context = context;
		this.categories = categories;
		this.categorieCollections = categorieCollections;
		this.couleurDepartement = couleurDepartement;
	}
	

	public Object getChild(int groupPosition, int childPosition) {
		return categorieCollections.get(categories.get(groupPosition)).get(childPosition);
	}
	

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}    
	

	public int getChildrenCount(int groupPosition) {
		return categorieCollections.get(categories.get(groupPosition)).size();
	}
	

	public Object getGroup(int groupPosition) {
		return categories.get(groupPosition);
	}
	

	public int getGroupCount() {
		return categories.size();
	}
	

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}


	/**
	 * Renvoie les noms des groupes de la liste (les catégories).
	 */
	 public View getGroupView(int groupPosition, boolean isExpanded,
			 View convertView, ViewGroup parent) {

		 String nomFormation = ((Categorie) getGroup(groupPosition)).getLibelle();
		 
		 if (convertView == null)
		 {
			 LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 convertView = infalInflater.inflate(R.layout.parent_item, null);
		 }
		 
		 TextView item = (TextView) convertView.findViewById(R.id.text_parent);
		 
		 item.setTypeface(null, Typeface.BOLD);
		 item.setText(nomFormation);
		 
		 item.setTextColor(couleurDepartement);
		 
		 return convertView;
	 }
	 

	 /**
	  * Renvoie les noms des enfants de la liste (les formations).
	  */
	 public View getChildView(final int groupPosition, final int childPosition,
			 boolean isLastChild, View convertView, ViewGroup parent) {

		 final String formation = ((Formation) getChild(groupPosition, childPosition)).getDiplome();

		 LayoutInflater inflater = context.getLayoutInflater();

		 if (convertView == null) {
			 convertView = inflater.inflate(R.layout.child_item, null);
		 }

		 TextView item = (TextView) convertView.findViewById(R.id.text_child);

		 item.setText(formation);
		 return convertView;
	 }

	 public boolean hasStableIds() {
		 return true;
	 }

	 public boolean isChildSelectable(int groupPosition, int childPosition) {
		 return true;
	 }
}
