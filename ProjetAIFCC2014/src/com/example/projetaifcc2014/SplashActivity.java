package com.example.projetaifcc2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity { 
	/** Appel� quand l'activit� est cr��e **/ 
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		//setContentView(R.layout.main); 
		
		Thread Timer = new Thread() {
			public void run() {
				try { 
					sleep(2500); 
					
					startActivity (new Intent ("com.toxiclovedesigns.SPLASH")); 
					
				} 
				
				catch (InterruptedException e) {
					e.printStackTrace(); 
					
				} 
				
				finally { 
					finish(); 
				
				} 
			
			} 
		
		} ;
		Timer.start(); 
	}
}
