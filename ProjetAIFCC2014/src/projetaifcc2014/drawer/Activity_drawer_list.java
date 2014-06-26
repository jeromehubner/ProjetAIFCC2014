package projetaifcc2014.drawer;

import projetaifcc2014.expandable_list_formation.Fragment_list_formation;
import android.support.v4.app.FragmentTransaction;

import com.example.projetaifcc2014.R;

public class Activity_drawer_list extends Activity_drawer{

	@Override
	public void showFragmentPrincipal(){
		Fragment_list_formation fragment = new Fragment_list_formation();

		// Débutez la transaction des fragments
		FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();

		// Définissez les animations entrantes et sortantes
		fTransaction.setCustomAnimations(R.anim.left_in,R.anim.left_out);

		fTransaction.replace(R.id.frame, fragment);

		// Faîtes le commit
		fTransaction.commit();
	}
}
