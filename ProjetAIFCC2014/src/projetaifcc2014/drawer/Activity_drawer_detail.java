package projetaifcc2014.drawer;

import projetaifcc2014.formation_detail.FragmentDescription;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.projetaifcc2014.R;

/**
 * Created by Sebastien on 16/04/14.
 */
public class Activity_drawer_detail extends Activity_drawer {

	
	@Override
	public void showFragmentPrincipal() {

		// Débutez la transaction des fragments
		FragmentManager fManager = getSupportFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();

		//    	fragment = (DummySectionFragment) fManager.findFragmentByTag(fragmentTag);

		FragmentDescription fragment = new FragmentDescription();

		// Définissez les animations entrantes et sortantes
		fTransaction.setCustomAnimations(R.anim.left_in,R.anim.left_out);

		fTransaction.replace(R.id.frame, fragment);

		// Faîtes le commit
		fTransaction.commit();
	}
}
