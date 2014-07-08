package projetaifcc2014.drawerFragment;

import projetaifcc2014.map.PermissionGps;
import wifi.WIFIDetection;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class FragmentParametre extends Fragment {

	private View rootView;
	private CheckBox cbNotifications;
	private CheckBox cbWifi;
	private CheckBox cbGPS;
	private CheckBox cbMiseAJour;
	private LocationManager locManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Le drawer n'est utilisable que pour le screen_formation_detail
		rootView = inflater.inflate(R.layout.fragment_parametres, container,
				false);

		cbNotifications = (CheckBox) rootView
				.findViewById(R.id.checkBoxNotifications);
		cbWifi = (CheckBox) rootView.findViewById(R.id.checkBoxWifi);
		cbGPS = (CheckBox) rootView.findViewById(R.id.checkBoxGps);
		cbMiseAJour = (CheckBox) rootView.findViewById(R.id.checkBoxMaj);

		cbNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cbNotifications.isChecked()) {
					Log.w("message check box", "Notification selectionnée");
				}
			}
		});
		cbWifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.w("message check box", "Wifi selectionnée");
				startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
			}
		});
		cbGPS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/** on lance notre activity (qui est une dialog) */
				Intent localIntent = new Intent(v.getContext(),
						PermissionGps.class);
				if(cbGPS.isChecked()){
					localIntent.putExtra("GPS", false);
				}
				localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(localIntent);
			}
		});
		cbMiseAJour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cbMiseAJour.isChecked()) {
					Log.w("message check box", "Mise à jour selectionnée");
				}
			}
		});

		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		/**
		 * On teste le wifi
		 */
		// Flag sur le status reseau
		Boolean isConnectionExist = false;
		WIFIDetection detecteurReseau;
		// on crée une classe servant a la detection du reseau
		detecteurReseau = new WIFIDetection(view.getContext());
		// Recupere le status internet
		isConnectionExist = detecteurReseau.testConnection();
		cbWifi.setChecked(isConnectionExist);
		
		
		/**
		 * On teste le GPS
		 */
		Boolean isConnecter = false ;
		/** Récupère le locationManager qui gère la localisation */
		locManager = (LocationManager) view.getContext().getSystemService(
				Context.LOCATION_SERVICE);
		
		isConnecter = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		cbGPS.setChecked(isConnecter);
	}

}
