package com.example.projetaifcc2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Intent intentProfil = new Intent(this,ProfilActivity.class);
		startActivity(intentProfil);
		}
			
	Thread logoTimer = new Thread() {
        public void run(){
            try{
                int logoTimer = 0;
               while(logoTimer < 5000){
                    sleep(100);
                    logoTimer = logoTimer +100;
                };
                Intent intentProfil = new Intent();
                startActivity(new Intent(".ProfilActivity.class"));
            } 
             
            catch (InterruptedException e) {
                e.printStackTrace();
            }
             
            finally{
                finish();
            }
        }
    };
     
    //logoTimer.start();
	}

 