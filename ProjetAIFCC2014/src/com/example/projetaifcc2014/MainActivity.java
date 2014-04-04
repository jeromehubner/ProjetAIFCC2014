package com.example.projetaifcc2014;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int profil = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		

	}

	public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.profil_1 :
			profil = 1;
			TextView textView = (TextView)findViewById(R.layout.accueil);
			
					
			
			break;
		
		case R.id.profil_2 : profil = 2; break;
		
		case R.id.profil_3 : profil = 3; break;
		
		case R.id.profil_4 : profil = 4; break;
		
		case R.id.profil_5 : profil = 5; break;
		
		case R.id.profil_6 : profil = 6; break;
		}
		
		placePermissions();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void placePermissions(){
		/**
		 * Méthode disposant les permissions en fonction du profil sélectionné
		 */
		
		

		setContentView(R.layout.accueil);
	}


}
