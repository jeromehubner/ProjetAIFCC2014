package projetaifcc2014.departement;

import projetaifcc2014.drawer.Activity_drawer;
import projetaifcc2014.formations_list.Activity_expandableList;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class Activity_department extends Activity {

	private String departement;
	
	private final String argTitrePageAccueil = "Titre";
	private final String argContDescr = "Description";
	private final String argContFin = "Financement";
	
	private String 	titrePageAccueil,
					contenuDescription,
					contenuFinancement;
	
	private String urlAIFCC = "http://www.aifcc.com/";
	private String urlimss = "http://www.imss-caen.com/";
	private String urlIMAD = "http://www.imad-caen.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_department);
		
		
//		// cette ligne indique que lorsque l'on clique sur la notification, nous revenons vers notre application
//		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, orgActivity.class), 0);
//				
//		// on cr�e une notification
//		Notification notification = new Notification.Builder(this)
//				    .setSmallIcon(R.drawable.ic_launcher)
//				    .setContentTitle("Notification AIFCC")
//				    .setContentText("Vous avez re�u une notification AIFCC")
//				    .setAutoCancel(true)
//				    .setContentIntent(pendingIntent)
//				    .build();
//		//notification.vibrate = new long[] {0,200,100,200,100,200};
//				
//		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//	    notificationManager.notify(R.string.notification, notification);
	}
	
	

	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		switch (v.getId()) {

		case R.id.aifcc_alt:
			// AIFCC ALTERNANCE
			departement = getString(R.string.aifcc_alt);
			Toast.makeText(this, R.string.aifcc_alt, Toast.LENGTH_SHORT).show();
			titrePageAccueil = departement;
			contenuDescription += departement;
			contenuFinancement += departement;
			break;

		case R.id.aifcc_cnt:
			// AIFCC CONTINU
			departement = getString(R.string.aifcc_cnt);
			Toast.makeText(this, R.string.aifcc_cnt, Toast.LENGTH_SHORT).show();
			titrePageAccueil = departement;
			contenuDescription += departement;
			contenuFinancement += departement;
			break;

		case R.id.imss:
			// IMSS
			departement = getString(R.string.imss);
			Toast.makeText(this, R.string.imss, Toast.LENGTH_SHORT).show();
			titrePageAccueil = departement;
			contenuDescription += departement;
			contenuFinancement += departement;
			
			// Redirection vers site imss
			Uri uriimss = Uri.parse(urlimss);
			Toast.makeText(this, "Redirection vers" + urlimss, Toast.LENGTH_LONG).show();
			intent.setData(uriimss);
			startActivity(intent);
			break;

		case R.id.cel:
			// CEL
			departement = getString(R.string.cel);
			Toast.makeText(this, R.string.cel, Toast.LENGTH_SHORT).show();
			titrePageAccueil = departement;
			contenuDescription += departement;
			contenuFinancement += departement;
			break;

		case R.id.imad:
			// IMAD
			departement = getString(R.string.imad);
			Toast.makeText(this, R.string.imad, Toast.LENGTH_SHORT).show();
			titrePageAccueil = departement;
			contenuDescription += departement;
			contenuFinancement += departement;
			
			// Redirection vers site IMAD
			Uri uriAIMD = Uri.parse(urlIMAD);
			Toast.makeText(this, "Redirection vers" + urlIMAD, Toast.LENGTH_LONG).show();
			intent.setData(uriAIMD);
			startActivity(intent);
			break;

			// Bouton de banniere qui ouvre le site //
		case R.id.banniere:

			Uri uriAIFCC = Uri.parse(urlAIFCC);
			Toast.makeText(this, "Redirection vers" + urlAIFCC, Toast.LENGTH_LONG).show();
			intent.setData(uriAIFCC);
			startActivity(intent);
			break;
		}

		Intent intentAccueil = new Intent(this, Activity_expandableList.class);
		
//		intentAccueil.putExtra(argTitrePageAccueil, titrePageAccueil);
//		intentAccueil.putExtra(argContDescr, contenuDescription);
//		intentAccueil.putExtra(argContFin, contenuFinancement);
		
		startActivity(intentAccueil);
	}



	@Override
	protected void onResume() {
		super.onResume();
		
		contenuDescription = "Description de l'organisation ";
		contenuFinancement = "Description du \nfinancement de l'organisation \n";
		
	}

	// TODO : supprimer le fait de pouvoir revenir sur le splashscreen

}
