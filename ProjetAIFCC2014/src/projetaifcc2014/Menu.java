package projetaifcc2014;

import com.example.projetaifcc2014.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class Menu extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_department);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, (android.view.Menu) menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_aide:
			// Comportement du bouton "Aide"
			return true;
		case R.id.menu_rafraichir:
			// Comportement du bouton "Rafraichir"
			return true;
		case R.id.menu_apropos:
			// Comportement du bouton "A Propos"
			return true;
		case R.id.menu_paramètres:
			// Comportement du bouton "Paramètres"
			return true;
		case R.id.menu_quitter:
			// Comportement du bouton "Quitter"
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}