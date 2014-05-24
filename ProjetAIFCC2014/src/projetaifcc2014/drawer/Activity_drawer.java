package projetaifcc2014.drawer;

import java.util.ArrayList;

import projetaifcc2014.formation_detail.DummySectionFragment;
import com.example.projetaifcc2014.R;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Sebastien on 16/04/14.
 */
public class Activity_drawer extends FragmentActivity {
    private DrawerLayout monDrawerLayout;
    private ListView maListeDrawer;
    private ActionBarDrawerToggle mDrawerToggle;


    // Titre du drawer
    private CharSequence mDrawerTitle;

    //utiliser pour stocker les titre du drawer
    private CharSequence mTitle;

    // Variables des items du drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    // Adapter et definitions des items du drawer
    private ArrayList<NavDrawerItem> navDrawerItems;
    private DrawerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer);

        mTitle = mDrawerTitle = getTitle();

        // Recupere les chaines de caractere des menus du drawer
        navMenuTitles = getResources().getStringArray(R.array.items);

        // Recupere les icones  des menus du drawer
        navMenuIcons = getResources().obtainTypedArray(R.array.icones_drawer);

        monDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        maListeDrawer = (ListView) findViewById(R.id.list_menu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // Ajout des items a la liste
        // AIFCC
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Nos financements
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Catalogues 2014
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Les taxes
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Nous rejoindre
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Portes ouvertes
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        // Contact
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));


        navMenuIcons.recycle();

        maListeDrawer.setOnItemClickListener(new SlideMenuClickListener());

        // on defini l'adapter propre a notre drawer
        adapter = new DrawerAdapter(getApplicationContext(),
                navDrawerItems);
        maListeDrawer.setAdapter(adapter);

        // active l'icone de la action bar et active l'effet de glissement
        getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, monDrawerLayout,
                R.drawable.menu, //nav menu toggle icon
                R.string.app_name, // texte affiché dans l'action bar lorsque le drawer est ouvert
                R.string.app_name // texte affiché dans l'action bar lorsque le drawer est fermé
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // appel onPrepareOptionsMenu() pour montrer  l'icone de l'action bar
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // appel onPrepareOptionsMenu() pour cacher l'icone de l'action bar
                invalidateOptionsMenu();
            }
        };
        monDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // lors du premier appel on montre la premier vue
            displayView(0);
        }
    }


    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // affiche la vue selectionnée lors du clicque sur un item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_paramètres:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

     /* *
     * appellé quand invalidateOptionsMenu() est déclenché
     */
        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            boolean drawerOpen = monDrawerLayout.isDrawerOpen(maListeDrawer);
            menu.findItem(R.id.menu_paramètres).setVisible(!drawerOpen);
            return super.onPrepareOptionsMenu(menu);
        }

    /**
     * Affichage du fragment en fonction de l'item selectionné
     * */
    private void displayView(int position) {
        // mise à jour de la vue
        switch (position) {
            case 0:
                showFragmentDescription();
                break;
            case 1:
                showFragmentAutre();
                break;
            case 2:
            	showFragmentAutre();
                break;
            case 3:
            	showFragmentAutre();
                break;
            case 4:
            	showFragmentAutre();
                break;
            case 5:
            	showFragmentAutre();
                break;
            case 6:
            	showFragmentAutre();
                break;

            default:
                break;
        }

            // mise à jour de l'item selectionné et du titre lors de la fermeture du drawer
            maListeDrawer.setItemChecked(position, true);
            maListeDrawer.setSelection(position);
            setTitle(navMenuTitles[position]);
            monDrawerLayout.closeDrawer(maListeDrawer);
    }
    
    public void showFragmentDescription() {
    	
    	DummySectionFragment fragment ;
    	// Débutez la transaction des fragments
    	FragmentManager fManager = getSupportFragmentManager();
    	FragmentTransaction fTransaction = fManager.beginTransaction();
    	
//    	fragment = (DummySectionFragment) fManager.findFragmentByTag(fragmentTag);
    	
    	fragment = new DummySectionFragment();
    	
    	
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,
    	R.anim.left_out,
    	R.anim.right_in,
    	R.anim.right_out);
    	
    	fTransaction.replace(R.id.frame, fragment);
    	
    	// Faîtes le commit
    	fTransaction.commit();
    }
    
    public void showFragmentAutre() {
    	PagesFragment fragment = new PagesFragment();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,
    	R.anim.left_out,
    	R.anim.right_in,
    	R.anim.right_out);
    	
    	
    	
       	fTransaction.replace(R.id.frame, fragment);
    	
    	// Faîtes le commit
    	fTransaction.commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
