package projetaifcc2014.drawer;

import projetaifcc2014.fragment_info_departement.Fragment_imad;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.projetaifcc2014.R;

public class Activity_drawer_IMAD extends Activity_drawer{

    @Override
    public void showFragmentPrincipal(){
    	Fragment_imad fragment = new Fragment_imad();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	
    	
    	
    	// Définissez les animations entrantes et sortantes
    	fTransaction.setCustomAnimations(R.anim.left_in,R.anim.left_out);
    	
    	
    	
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
