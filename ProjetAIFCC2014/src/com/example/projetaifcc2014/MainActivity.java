package com.example.projetaifcc2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends Activity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.splash);
//		
//		Intent intentProfil = new Intent(this,ProfilActivity.class);
//		startActivity(intentProfil);
//		}
//			
//	Thread logoTimer = new Thread() {
//        public void run(){
//            try{
//                int logoTimer = 0;
//               while(logoTimer < 5000){
//                    sleep(100);
//                    logoTimer = logoTimer +100;
//                };
//                Intent intentProfil = new Intent();
//                startActivity(new Intent(".ProfilActivity.class"));
//            } 
//             
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//             
//            finally{
//                finish();
//            }
//        }
//    };
//     
//    //logoTimer.start();
//	}
	 /** Durée d'affichage du SplashScreen */
	   protected int _splashTime = 5000; 

	   private Thread splashTread;

	   /** Chargement de l'Activity */
	   @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.splash);

	      final MainActivity sPlashScreen = this; 

	      /** Thread pour l'affichage du SplashScreen */
	      splashTread = new Thread() 
	      {
	         @Override
	         public void run() 
	         {
	            try 
	            {
	                 synchronized(this)
	                 {
	                    wait(_splashTime);
	                 }
	             } catch(InterruptedException e) {} 
	             finally 
	             {
	                finish();
	                Intent i = new Intent();
	                i.setClass(sPlashScreen, ProfilActivity.class);
	                startActivity(i);
	             }
	          }
	       };

	       splashTread.start();
	    }
	    @Override
	    public boolean onTouchEvent(MotionEvent event) 
	    {
	       /** Si l'utilisateur fait un mouvement de haut en bas on passe à l'Activity principale */
	       if (event.getAction() == MotionEvent.ACTION_DOWN) 
	       {
		   synchronized(splashTread)
	           {
	                splashTread.notifyAll();
	           }
	       }
	       return true;
	    }	
	}
 