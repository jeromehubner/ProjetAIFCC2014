package projetaifcc2014.expandable_list_formation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import projetaifcc2014.drawer.Activity_drawer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class ExpandableListActivity  extends Activity{
	 
	private String[] domaines;
    List<String> groupList;
    List<String> listFormations;
    Map<String, List<String>> formationCollection;
    ExpandableListView expListViewFormations;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_list_main);
 
     // Recupere les différents domaines de formation
        domaines = getResources().getStringArray(R.array.liste_domaines);
        
        createGroupList(domaines);
 
        createCollection();
 
        expListViewFormations = (ExpandableListView) findViewById(R.id.formation_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, formationCollection);
        expListViewFormations.setAdapter(expListAdapter);
 
        //setGroupIndicatorToRight();
 
        expListViewFormations.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

    			Intent description = new Intent(ExpandableListActivity.this,Activity_drawer.class);
    			startActivity(description);
 
                return true;
            }
        });
    }
 
    private void createGroupList(String[] domaines) {
        groupList = new ArrayList<String>();
        
        for (String domaine : domaines){
        	groupList.add(domaine);
        }
    }
 
    private void createCollection() {
        // on recupere la liste des differentes formations que l'on vas au differents domaines concerné
        String[] formationsCommerceVente = getResources().getStringArray(R.array.commerce_vente);
        String[] formationsComptaGestion = getResources().getStringArray(R.array.compta_gestion);
        String[] formationsCom = getResources().getStringArray(R.array.communication);
        String[] formationsInfo = getResources().getStringArray(R.array.informatique);
        String[] formationsTourisme = getResources().getStringArray(R.array.tourisme);
        String[] formationsSecretariat = getResources().getStringArray(R.array.secretariat_organisation);
        String[] formationsRH = getResources().getStringArray(R.array.ressources_humaines);
 
        formationCollection = new LinkedHashMap<String, List<String>>();
 
        for (String domaines : groupList) {
            if (domaines.equals("Commerce - Vente")) {
                loadChild(formationsCommerceVente);
            } else if (domaines.equals("Comptabilité - Gestion"))
                loadChild(formationsComptaGestion);
            else if (domaines.equals("Communication"))
                loadChild(formationsCom);
            else if (domaines.equals("Informatique"))
                loadChild(formationsInfo);
            else if (domaines.equals("Tourisme"))
                loadChild(formationsTourisme);
            else if (domaines.equals("Secretariat - Organisation"))
                loadChild(formationsSecretariat);
            else if (domaines.equals("Ressources humaines "))
                loadChild(formationsRH);
            
 
            formationCollection.put(domaines, listFormations);
        }
    }
 
    private void loadChild(String[] lesFormations) {
        listFormations = new ArrayList<String>();
        for (String formation : lesFormations)
            listFormations.add(formation);
    }
 
    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
 
        expListViewFormations.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density; 
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}