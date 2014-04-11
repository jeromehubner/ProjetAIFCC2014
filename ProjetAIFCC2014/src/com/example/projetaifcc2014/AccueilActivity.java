package com.example.projetaifcc2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AccueilActivity extends Activity {

	final String argTitrePageAccueil = "Titre";
	final String argContDescr = "Description";
	final String argContFin = "Financement";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
		
		Intent intent = getIntent();
		
		if(intent != null){
			((TextView)findViewById(R.id.titrePageAccueil)).setText(intent.getStringExtra(argTitrePageAccueil));
			((TextView)findViewById(R.id.descriptionPageAccueil)).setText(intent.getStringExtra(argContDescr));
			((TextView)findViewById(R.id.financementContenu)).setText(intent.getStringExtra(argContFin));
		}
	}
}