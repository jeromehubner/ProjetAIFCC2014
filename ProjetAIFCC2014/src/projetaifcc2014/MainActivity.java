package projetaifcc2014;

import projetaifcc2014.database.InitDatabase;
import projetaifcc2014.drawer.Activity_drawer_Departement;
import projetaifcc2014.gallerie.RessourceURL;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.projetaifcc2014.R;

public class MainActivity extends Activity {
	private Thread splashTread;
	private Activity mainActivity;
	private ProgressBar progressBar;

	public static ImageView[] imgs = new ImageView[3];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_splash);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		mainActivity = this;

		// Thread pour l'affichage du SplashScreen
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lancerActiviteDrawerDepartement();
				}
				
				int facteur = 4; // facteur utilisé pour le calcul du temps dans
				// les méthodes Thread.sleep(time);
				int vitesseBalayage = 25; // Coefficient inverse de la vitesse
				int nbSecondesBalayage = 60; // Durée de vie du thread en
				// secondes
				if (progressBar.getProgress() <= 99) {
					for (int i = 0; i < (nbSecondesBalayage * facteur); i++) {

						progressBar.setSecondaryProgress(progressBar
								.getSecondaryProgress() + 2);

						//	Log.v("mytag",
						//			"SecondaryProgressBar : "
						//					+ progressBar.getSecondaryProgress());
						//	Log.v("mytag",
						//			"progressBar : " + progressBar.getProgress());

						for (int j = 0; j < progressBar.getSecondaryProgress(); j++) {

							progressBar.setProgress(j);
							try {
								// La vitesse de parcours de la progressBar
								Thread.sleep(vitesseBalayage);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						try {
							// Le temps que la progressBar attends à la fin du
							// chargement
							Thread.sleep(1000 / facteur);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("chargerBdd", true)) {
			splashTread.start();

			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("chargerBdd", false);
			editor.commit();
			new InitDatabase(mainActivity, splashTread, progressBar).execute();
		} else {
			lancerActiviteDrawerDepartement();
		}
	}
	
	public void lancerActiviteDrawerDepartement(){
		new RessourceURL(mainActivity, imgs).execute();
		Intent i = new Intent(getBaseContext(), Activity_drawer_Departement.class);
		startActivity(i);
		finish();
	}
}
