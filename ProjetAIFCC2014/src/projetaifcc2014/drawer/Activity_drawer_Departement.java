package projetaifcc2014.drawer;

import projetaifcc2014.database.departement.Departement;
import projetaifcc2014.database.departement.DepartementBdd;
import projetaifcc2014.departement.Fragment_departement;
import projetaifcc2014.drawerFragment.FinancementFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class Activity_drawer_Departement extends Activity_drawer{

	
	private Departement departement;
	private DepartementBdd departementBdd;

	public Activity_drawer_Departement() {
		departementBdd = new DepartementBdd(this);
	}

	
	@Override
    public void showFragmentPrincipal(){
    	Fragment_departement fragment = new Fragment_departement();
    	
    	
    	// Débutez la transaction des fragments
    	FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
    	

       	fTransaction.replace(R.id.frame, fragment);
    	
    	// Faîtes le commit
    	fTransaction.commit();
    }
	
    
    public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		switch (v.getId()) {

		case R.id.aifcc_alt:
			// AIFCC ALTERNANCE
			Toast.makeText(this, R.string.aifcc_alt, Toast.LENGTH_SHORT).show();
			
			departementBdd.open();
			departement = departementBdd.getDepartementById(Integer.valueOf(getResources().getString(R.string.alt_dpt_id))); 
			departementBdd.close();
			
			/* On passe l'id du département concerné à l'activité
			 * pour que celle ci puisse récupérer les la liste des formations
			 */
			Intent intentAifcc_alt = new Intent(this, Activity_drawer_list.class);
			intentAifcc_alt.putExtra(getResources().getString(R.string.extra_id_departement), departement.getId());
			startActivity(intentAifcc_alt);
			break;

		case R.id.aifcc_cnt:
			Toast.makeText(this, R.string.aifcc_cnt, Toast.LENGTH_SHORT).show();
			
			// AIFCC CONTINU
			departementBdd.open();
			departement = departementBdd.getDepartementById(Integer.valueOf(getResources().getString(R.string.con_dpt_id))); 
			departementBdd.close();
			

			Intent intentAifcc_cnt = new Intent(this, Activity_drawer_list.class);
			intentAifcc_cnt.putExtra(getResources().getString(R.string.extra_id_departement), departement.getId());
			startActivity(intentAifcc_cnt);
			break;
			
			
		case R.id.cel:
			// CEL
			Toast.makeText(this, R.string.cel, Toast.LENGTH_SHORT).show();

			departementBdd.open();
			departement = departementBdd.getDepartementById(Integer.valueOf(getResources().getString(R.string.cel_dpt_id))); 
			departementBdd.close();
			
			Intent intentAifcc_Cel = new Intent(this, Activity_drawer_list.class);
			intentAifcc_Cel.putExtra(getResources().getString(R.string.extra_id_departement), departement.getId());
			startActivity(intentAifcc_Cel);
			break;

		case R.id.imss:
			// IMSS
			Toast.makeText(this, R.string.imss, Toast.LENGTH_SHORT).show();

			Intent intentImss = new Intent(this, Activity_drawer_IMSS.class);
			startActivity(intentImss);
			break;

		case R.id.imad:
			// IMAD
			Toast.makeText(this, R.string.imad, Toast.LENGTH_SHORT).show();


			Intent intentImad = new Intent(this, Activity_drawer_IMAD.class);
			startActivity(intentImad);
			break;


			// Bouton de banniere qui ouvre le site //
		case R.id.banniere_element1:

			Uri uriAIFCC = Uri.parse(getResources().getString(R.string.url_AIFCC));
			Toast.makeText(this, "Redirection vers" + uriAIFCC.toString(), Toast.LENGTH_LONG).show();
			intent.setData(uriAIFCC);
			startActivity(intent);
			break;
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
	}
}
