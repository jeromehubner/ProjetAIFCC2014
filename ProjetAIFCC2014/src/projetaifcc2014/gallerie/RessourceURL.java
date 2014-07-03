package projetaifcc2014.gallerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class RessourceURL extends AsyncTask<Void, Void, Void>{

	// Variables
	private Activity mainActivity;
	private final static String USER_AGENT = "Mozilla/5.0";
	private String[] tab = new String[3];
	private ImageView[] imgs = new ImageView[3];

	// Public constructor
	public RessourceURL (Activity mainActivity, ImageView[] imgs){
		this.mainActivity = mainActivity;
		this.imgs = imgs;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			String url = "http://www.aifcc.com";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Print result
			System.out.println(response.toString());

			// Searching for images
			String varContent = response.toString();
			String[] Tab_img = varContent.split("<img src=" + '"' + "web/img/");
			for(int i = 1; i < 4 ;i++) {
				tab[i-1] = url + "/web/img/" + Tab_img[i].substring(0, Tab_img[i].indexOf('"'));
				System.out.println(tab[i-1]);
			}

			for (int i = 0; i < tab.length; i++){
				URL newurl = new URL(tab[i]);
				Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
				ImageView iv = new ImageView(mainActivity);
				iv.setImageBitmap(mIcon_val);
				imgs[i] = iv;
			}

		}
		catch (Exception e){
			e.getStackTrace();
			System.out.println("Erreur : " + e.toString());
		}
		return null;
	}
}