package com.example.projetaifcc2014;

import android.app.Activity;
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
					contenuDescription = "Description du profil" ,
					contenuFinancement = "Description de la partie financement du profil";
	
	private String urlAIFCC = "http://www.aifcc.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
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

			// Bouton de bannière qui ouvre le site //
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

	// TODO : supprimer le fait de pouvoir revenir sur le splash screen

}
