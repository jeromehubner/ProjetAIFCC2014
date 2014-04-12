package com.example.projetaifcc2014;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity {

	private String profil;
	
	private final String argTitrePageAccueil = "Titre";
	private final String argContDescr = "Description";
	private final String argContFin = "Financement";
	
	private String 	titrePageAccueil,
					contenuDescription,
					contenuFinancement;
	
	private String urlAIFCC = "http://www.aifcc.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		
		// cette ligne indique que lorsque l'on clique sur la notification, nous revenons vers notre application
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ProfilActivity.class), 0);
				
		// on crée une notification
		Notification notification = new Notification.Builder(this)
				    .setSmallIcon(R.drawable.ic_launcher)
				    .setContentTitle("Notification AIFCC")
				    .setContentText("Vous avez reçu une notification AIFCC")
				    .setAutoCancel(true)
				    .setContentIntent(pendingIntent)
				    .build();
		notification.vibrate = new long[] {0,200,100,200,100,200};
				
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    notificationManager.notify(R.string.notification, notification);
	}
	
	

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.profil_1:
			// Etudiant
			profil = getString(R.string.profil_1);

			Toast.makeText(this, R.string.profil_1_desc, Toast.LENGTH_SHORT).show();
			titrePageAccueil = profil;
			contenuDescription += profil;
			contenuFinancement += profil;
			break;

		case R.id.profil_2:
			// Demandeur d'emploi
			profil = getString(R.string.profil_2);

			Toast.makeText(this, R.string.profil_2_desc, Toast.LENGTH_SHORT).show();
			titrePageAccueil = profil;
			contenuDescription += profil;
			contenuFinancement += profil;
			break;

		case R.id.profil_3:
			// Salarie
			profil = getString(R.string.profil_3);

			Toast.makeText(this, R.string.profil_3_desc, Toast.LENGTH_SHORT).show();
			titrePageAccueil = profil;
			contenuDescription += profil;
			contenuFinancement += profil;
			break;

		case R.id.profil_4:
			// Entrepreneur
			profil = getString(R.string.profil_4);

			Toast.makeText(this, R.string.profil_4_desc, Toast.LENGTH_SHORT).show();
			titrePageAccueil = profil;
			contenuDescription += profil;
			contenuFinancement += profil;
			break;

		case R.id.profil_5:
			
			// Formateur

			break;

		case R.id.profil_6:
			
			// Personnel administratif

			break;

			// Bouton de banniÃ¨re qui ouvre le site //
		case R.id.profil_7:
			Intent intent = new Intent(Intent.ACTION_VIEW);

			Uri uri = Uri.parse(urlAIFCC);
			Toast.makeText(this, "Redirection vers" + urlAIFCC, Toast.LENGTH_LONG).show();
			intent.setData(uri);
			startActivity(intent);
			break;
		}

		Intent intentAccueil = new Intent(this, AccueilActivity.class);
		
		intentAccueil.putExtra(argTitrePageAccueil, titrePageAccueil);
		intentAccueil.putExtra(argContDescr, contenuDescription);
		intentAccueil.putExtra(argContFin, contenuFinancement);
		
		startActivity(intentAccueil);
	}



	@Override
	protected void onResume() {
		super.onResume();
		
		contenuDescription = "Description du profil ";
		contenuFinancement = "Description du \nfinancement du profil \n";
		
	}

	// TODO : supprimer le fait de pouvoir revenir sur le splash screen

}
