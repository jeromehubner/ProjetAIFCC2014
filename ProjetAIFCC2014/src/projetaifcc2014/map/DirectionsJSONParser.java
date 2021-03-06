package projetaifcc2014.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

public class DirectionsJSONParser {
	
	/** Recepton d'un objet JSON et renvoi d'une liste de liste contenant les latitude et longitude */
	public List<List<HashMap<String,String>>> parse(JSONObject jObject){
		
		List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String,String>>>() ;
		JSONArray jRoutes = null;
		JSONArray jLegs = null;
		JSONArray jSteps = null;	
		JSONObject jDistance = null;
		JSONObject jDuration = null;
		
		try {			
			
			jRoutes = jObject.getJSONArray("routes");
			
			
			for(int i=0;i<jRoutes.length();i++){			
				jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");				
				
				List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();				
				
				
				for(int j=0;j<jLegs.length();j++){
					
					/** Recupere la distance a partir du fichier JSON */
					jDistance = ((JSONObject) jLegs.get(j)).getJSONObject("distance");
					HashMap<String, String> hmDistance = new HashMap<String, String>();
					hmDistance.put("distance", jDistance.getString("text"));
					
					/** Recupere la durée a partir du fichier JSON */
					jDuration = ((JSONObject) jLegs.get(j)).getJSONObject("duration");
					HashMap<String, String> hmDuration = new HashMap<String, String>();
					hmDuration.put("duration", jDuration.getString("text"));
					
					/** Ajout de la distance au patch */
					path.add(hmDistance);
					
					/** Ajout de la durée au patch */
					path.add(hmDuration);					
					
					
					jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");
					
					/** Les étapes */
					for(int k=0;k<jSteps.length();k++){
						String polyline = "";
						polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
						List<LatLng> list = decodePoly(polyline);
						
						/** Les points */
						for(int l=0;l<list.size();l++){
							HashMap<String, String> hm = new HashMap<String, String>();
							hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
							hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
							path.add(hm);						
						}								
					}					
				}
				routes.add(path);
			}
			
		} catch (JSONException e) {			
			e.printStackTrace();
		}catch (Exception e){			
		}
		
		return routes;
	}	
	
	
	/**
	 * Méthode pour décoder tout le point polylines 
	 **/
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}