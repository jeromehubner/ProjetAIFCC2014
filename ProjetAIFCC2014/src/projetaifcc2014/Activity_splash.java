package projetaifcc2014;
//package com.example.projetaifcc2014;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MotionEvent;
//
//public class Splashscreen extends Activity 
//{
//   /** Dur�e d'affichage du SplashScreen */
//   protected int _splashTime = 5000; 
//
//   private Thread splashTread;
//
//   /** Chargement de l'Activity */
//   @Override
//   public void onCreate(Bundle savedInstanceState) 
//   {
//      super.onCreate(savedInstanceState);
//      setContentView(R.layout.profil);
//
//      final Splashscreen sPlashScreen = this; 
//
//      /** Thread pour l'affichage du SplashScreen */
//      splashTread = new Thread() 
//      {
//         @Override
//         public void run() 
//         {
//            try 
//            {
//                 synchronized(this)
//                 {
//                    wait(_splashTime);
//                 }
//             } catch(InterruptedException e) {} 
//             finally 
//             {
//                finish();
//                Intent i = new Intent();
//                i.setClass(sPlashScreen, MainActivity.class);
//                startActivity(i);
//             }
//          }
//       };
//
//       splashTread.start();
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) 
//    {
//       /** Si l'utilisateur fait un mouvement de haut en bas on passe � l'Activity principale */
//       if (event.getAction() == MotionEvent.ACTION_DOWN) 
//       {
//	   synchronized(splashTread)
//           {
//                splashTread.notifyAll();
//           }
//       }
//       return true;
//    }	
//}