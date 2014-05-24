package projetaifcc2014.drawer;

import projetaifcc2014.fragment_info_departement.fragment_imad;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.projetaifcc2014.R;

public class Activity_drawer_IMAD extends Activity_drawer{


	
	/* (non-Javadoc)
	 * @see projetaifcc2014.drawer.Activity_drawer#changeIcone(int)
	 */
	@Override
	public void changeIcone(int centre) {
		// TODO Auto-generated method stub
		super.changeIcone(IMAD);
	}
	
	/* (non-Javadoc)
	 * @see projetaifcc2014.drawer.Activity_drawer#displayView(int)
	 */
	@Override
	public void displayView(int position) {
		this.position = position ;
        // mise à jour de la vue
        switch (position) {
            case 0:
            	showFragmentIMAD();
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
    
    public void showFragmentIMAD(){
    	fragment_imad fragment = new fragment_imad();
    	
    	
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
    
	public void onClickIMAD (View view) {
		OnUrl ("http://www.imad-caen.com/");
	}

	private void OnUrl (String url) {
		Uri uriUrl = Uri.parse(url);
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}

}
