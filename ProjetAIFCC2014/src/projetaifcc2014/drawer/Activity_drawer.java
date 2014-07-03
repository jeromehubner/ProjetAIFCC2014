package projetaifcc2014.drawer;

import java.util.ArrayList;

import projetaifcc2014.drawerFragment.FinancementFragment;
import projetaifcc2014.drawerFragment.FragmentAPropos;
import projetaifcc2014.drawerFragment.FragmentAide;
import projetaifcc2014.drawerFragment.FragmentContact;
import projetaifcc2014.drawerFragment.FragmentParametre;
import projetaifcc2014.drawerFragment.rejoindre.FragmentPageViewer;
import projetaifcc2014.gallerie.Fragment_Gallery;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projetaifcc2014.R;


public abstract class Activity_drawer extends FragmentActivity {
	private DrawerLayout monDrawerLayout;
	protected ListView maListeDrawer;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected int position ;

	// Cette variable est récupérée dans le détail fragment pour effectuer la requête en DB
	protected int idFormation;

	// Titre du drawer
	protected CharSequence mDrawerTitle;

	//utiliser pour stocker les titre du drawer
	protected CharSequence mTitle;

	// Variables des items du drawer
	protected String[] navMenuTitles;
	protected TypedArray navMenuIcons;
	protected TypedArray navMenuIconsFormation;

	// Adapter et definitions des items du drawer
	protected ArrayList<NavDrawerItem> navDrawerItems;
	private DrawerAdapter adapter;

	protected int centre ;
	protected static final int DEPARTEMENT = 0;
	protected static final int AIFCC_ALT = 1;
	protected static final int AIFCC_CNT = 2;
	protected static final int CEL = 3;
	protected static final int IMAD = 4;
	protected static final int IMSS = 5;

	
	public abstract void showFragmentPrincipal();


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
		contruitListeDrawer(navMenuTitles,navMenuIcons);

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
			miseAJourItemSelectionne();
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
		case R.id.menu_parametres:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void contruitListeDrawer(String[] navMenuTitles,TypedArray navMenuIcons)
	{
		for (int nombre = 0 ; nombre<navMenuTitles.length; nombre++) {

			navDrawerItems.add(new NavDrawerItem(navMenuTitles[nombre], navMenuIcons.getResourceId(nombre, -1)));
		}
	}


	/* *
	 * appellé quand invalidateOptionsMenu() est déclenché
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = monDrawerLayout.isDrawerOpen(maListeDrawer);
		menu.findItem(R.id.menu_parametres).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Affichage du fragment en fonction de l'item selectionné
	 * */
	public void displayView(int position) {
		// On récupère l'id de la formation sélectionnée passée depuis la classe Fragment_list_formation
		idFormation = getIntent().getIntExtra(getResources().getString(R.string.extra_id_formation), 0);

		// mise à jour de la vue
		switch (position) {
		case 0:
			showFragmentPrincipal();
			break;
		case 1:
			showFragmentFinancement();
			break;
		case 2:
			showFragmentRejoindre();
			break;
		case 3:
			showFragmentContact();
			break;
		case 4:
			ShowFragmentGallery();
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
	}


	public void miseAJourItemSelectionne(){
		// mise à jour de l'item selectionné et du titre lors de la fermeture du drawer
		maListeDrawer.setItemChecked(position, true);
		maListeDrawer.setSelection(position);
		setTitle(navMenuTitles[position]);
		monDrawerLayout.closeDrawer(maListeDrawer);
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
	
	/**
	 * Item du drawer == 1, FINANCEMENT
	 */
	public void showFragmentFinancement() {
    	FinancementFragment financementFragment= new FinancementFragment();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,
    	R.anim.left_out);
    	
       	fTransaction.replace(R.id.frame, financementFragment);
       	
       	if(getSupportFragmentManager().getBackStackEntryCount() == 0){
       		fTransaction.addToBackStack(null);
       	}
       	
    	// Faîtes le commit
    	fTransaction.commit();
    }
	
	/**
	 * Item du drawer == 2, NOUS REJOINDRE
	 */
	public void showFragmentRejoindre() {
    	FragmentPageViewer fragment = new FragmentPageViewer();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,
    	R.anim.left_out);
    	
       	fTransaction.replace(R.id.frame, fragment);
       	
       	if(getSupportFragmentManager().getBackStackEntryCount() == 0){
       		fTransaction.addToBackStack(null);
       	}
       	
       	
    	// Faîtes le commit
    	fTransaction.commit();
	}
	
	/**
	 * Item du drawer == 3, CONTACT
	 */
	public void showFragmentContact() {
    	FragmentContact fragment = new FragmentContact();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,
    	R.anim.left_out);
    	
       	fTransaction.replace(R.id.frame, fragment);
       	
       	if(getSupportFragmentManager().getBackStackEntryCount() == 0){
       		fTransaction.addToBackStack(null);
       	}
       	
       	
    	// Faîtes le commit
    	fTransaction.commit();
	}
	
	/**
	 * Item du drawer == 4, GALLERIE
	 */
	
	public void ShowFragmentGallery()
	{
		Fragment_Gallery fragment = new Fragment_Gallery();	
		
		// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
       	fTransaction.replace(R.id.frame, fragment);
       	
       	if(getSupportFragmentManager().getBackStackEntryCount() == 0)
       	{
       		fTransaction.addToBackStack(null);
       	}
       	
    	// Faîtes le commit
    	fTransaction.commit();
	}
	/**
	 * Item du drawer == 5, ACTU
	 */
	
	
    public void showFragmentAutre() {
    	FinancementFragment fragment = new FinancementFragment();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,R.anim.left_out);
    	
    	if(getSupportFragmentManager().getBackStackEntryCount() == 0){
       		fTransaction.addToBackStack(null);
       	}
    	
       	fTransaction.replace(R.id.frame, fragment);
    	
    	// Faîtes le commit
    	fTransaction.commit();
    }

    public void showFragmentMenu(Fragment fragment) {
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,R.anim.left_out);
       	fTransaction.addToBackStack(null);

       	fTransaction.replace(R.id.frame, fragment);
    	// Faîtes le commit
    	fTransaction.commit();
    }

	public int getIdFormation() {
		return idFormation;
	}


	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_aide:
			FragmentAide fragment = new FragmentAide() ;
			showFragmentMenu(fragment);
			return true;
		case R.id.menu_apropos:
			FragmentAPropos fragmentAPropos = new FragmentAPropos();
			showFragmentMenu(fragmentAPropos);
			return true;
		case R.id.menu_parametres:
			FragmentParametre fragmentParam = new FragmentParametre();
			showFragmentMenu(fragmentParam);
			return true;
		case R.id.menu_quitter:
			// Comportement du bouton "Quitter"
			moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	
	}


}
