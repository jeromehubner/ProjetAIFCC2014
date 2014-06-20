package projetaifcc2014;

import projetaifcc2014.database.InitDatabase;
import projetaifcc2014.drawer.Activity_drawer_Departement;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.projetaifcc2014.R;

public class MainActivity extends Activity {
	private Thread splashTread;
	private Activity mainActivity;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_splash);
		mainActivity = this;

		// Thread pour l'affichage du SplashScreen
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized(this){
						wait();					
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
					Intent i = new Intent(getBaseContext(), Activity_drawer_Departement.class);
					startActivity(i);
				}
			}
		};
		splashTread.start();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (!prefs.getBoolean("firstTime", false)) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("firstTime", true);
			editor.commit();
			new InitDatabase(mainActivity, splashTread).execute();
		} else {
			synchronized(splashTread){
				splashTread.notifyAll();					
			}
		}
	}
}
