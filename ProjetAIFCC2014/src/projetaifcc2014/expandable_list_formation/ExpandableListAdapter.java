package projetaifcc2014.expandable_list_formation;

import java.util.List;
import java.util.Map;

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
    private Map<String, List<String>> formationCollections;
    private List<String> lesFormations;
 
    public ExpandableListAdapter(Activity context, List<String> lesFormations,
            Map<String, List<String>> formationCollections) {
        this.context = context;
        this.formationCollections = formationCollections;
        this.lesFormations = lesFormations;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return formationCollections.get(lesFormations.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
     
     
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String formation = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
         
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
         
        TextView item = (TextView) convertView.findViewById(R.id.text_child);
         
        
         
        item.setText(formation);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return formationCollections.get(lesFormations.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return lesFormations.get(groupPosition);
    }
 
    public int getGroupCount() {
        return lesFormations.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String nomFormation = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.parent_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.text_parent);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(nomFormation);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
