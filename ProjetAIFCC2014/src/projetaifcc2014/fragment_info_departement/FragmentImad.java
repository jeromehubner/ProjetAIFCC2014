package projetaifcc2014.fragment_info_departement;


import com.example.projetaifcc2014.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class FragmentImad extends FragmentActivity {

	// We instantiate just one time fragments during the life of the activity.
	private final fragment_imad frag_imad = new fragment_imad();
	private final fragment_imss frag_imss = new fragment_imss();
	private int m ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_imad_activity);
	}
	public void OnClickIMSS (View view) {
		OnUrl ("http://www.imss-caen.com/");
	}

	public void onClickIMAD (View view) {
		OnUrl ("http://www.imad-caen.com/");
	}

	private void OnUrl (String url) {
		Uri uriUrl = Uri.parse(url);
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}
}
