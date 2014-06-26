package projetaifcc2014.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetaifcc2014.R;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapActivityAIFCC extends FragmentActivity {

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	GoogleMap map;
	ArrayList<LatLng> pointsParcour;
	TextView textDistance;
	Location myLocation ;
	LocationClient locationClient ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_google_map);
		
		textDistance = (TextView) findViewById(R.id.tv_distance_time);
		
		
		//Position de l'AIFCC de caen
		LatLng aifccCaen = new LatLng(49.2074469, -0.3605196999999407);
		
		// initialisation des points  
		pointsParcour = new ArrayList<LatLng>();		
		pointsParcour.add(aifccCaen);
		
		// Recupere la map
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		// Active ma position
		map.setMyLocationEnabled(true);		
		myLocation = map.getMyLocation();

		
		

		
		double latitude = 49.27643699999999;
		double longitude = -0.7031399999999621;		
		LatLng positionPerso = new LatLng(latitude, longitude);
		pointsParcour.add(positionPerso);
		

		
			for (LatLng point : pointsParcour) {
				// Creating MarkerOptions
				MarkerOptions options = new MarkerOptions();
				
				// affecte les  positions au marker
				options.position(point);
				
				options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
				
				// Ajout d'un marker sur la map
				map.addMarker(options);
			}
							
			LatLng origin = pointsParcour.get(0);
			LatLng dest = pointsParcour.get(1);
					
			// Récuper URL de Google Directions API
			String url = getDirectionsUrl(origin, dest);				
					
			DownloadTask downloadTask = new DownloadTask();
					
			// Télechargement des données JSON Google Directions API
			downloadTask.execute(url);
		
	}
	
	





	private String getDirectionsUrl(LatLng origin,LatLng dest){
					
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;		
		
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		
		return url;
	}
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);
                
                //Création de la connexion http pour communiquer ave l'url
                urlConnection = (HttpURLConnection) url.openConnection();

                // connexion a l'url 
                urlConnection.connect();

                // lis les données de l'url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception lors du téléchargement de l'url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }

	
	
	// Récupère les données de url passée
	private class DownloadTask extends AsyncTask<String, Void, String>{			
				
		// Le téléchargement des données  dans non-ui thread
		@Override
		protected String doInBackground(String... url) {
				
			// Pour stocker les données du service Web
			String data = "";
					
			try{
				// Recupere les données du webService
				data = downloadUrl(url[0]);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}
		
		// execute dans le  UI thread, aprés l'ecxeution dans le 
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			
			
			ParserTask parserTask = new ParserTask();
			
			//Appel du thread pour parser les données JSON
			parserTask.execute(result);
				
		}		
	}
	
	/** Une classe pour analyser les lieux de Google au format JSON */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
    	
    	// Parse les données dans le  non-ui thread    	
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			
			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           
            
            try{
            	jObject = new JSONObject(jsonData[0]);
            	DirectionsJSONParser parser = new DirectionsJSONParser();
            	
            	// Commence a parser les données
            	routes = parser.parse(jObject);    
            }catch(Exception e){
            	e.printStackTrace();
            }
            return routes;
		}
		
		//Exécute le UI thread, après le processus de parsing
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();
			String distance = "";
			String duration = "";
			
			
			
			if(result.size()<1){
				Toast.makeText(getBaseContext(), "Calcul du parcours impossible", Toast.LENGTH_SHORT).show();
				return;
			}
				
			
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();
				
				// obtention de la route
				List<HashMap<String, String>> path = result.get(i);
				
				// obtention de chaque points de la route
				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);	
					
					if(j==0){	// recuper la distance
						distance = (String)point.get("distance");						
						continue;
					}else if(j==1){ // recupere la durée
						duration = (String)point.get("duration");
						continue;
					}
					
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);	
					
					points.add(position);						
				}
				
				// Ajoute tous les points a la route afin de tracer le trajet
				lineOptions.addAll(points);
				lineOptions.width(8);
				lineOptions.color(Color.BLUE);	
				
			}
			
			textDistance.setText("Distance:"+distance + ", Durée:"+duration);
			
			// dessine la route sur la google map
			map.addPolyline(lineOptions);							
		}			
    }   
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}	
}