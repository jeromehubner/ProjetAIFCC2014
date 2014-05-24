package projetaifcc2014.fragment_info_departement;


import com.example.projetaifcc2014.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class FragmentActivityIMAD extends FragmentActivity {

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
