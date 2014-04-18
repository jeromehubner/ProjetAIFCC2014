package com.example.projetaifcc2014;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.plan_aifcc:
			try {
				Toast.makeText(this, "Redirection vers google maps", Toast.LENGTH_LONG);
				
				// Ici, dans le ?q, mettez ce que vous voulez trouver sur google map
				Uri urimap = Uri.parse("geo:0,0?q=AIFCC Caen");
				Intent mapIntent = new Intent(Intent.ACTION_VIEW,urimap);
				startActivity(mapIntent); 
			} catch(ActivityNotFoundException e) {
				(Toast.makeText(this.getApplicationContext(), "GoogleMap non trouvé", Toast.LENGTH_LONG)).show();
			}
		
		break;
		}
	}
}