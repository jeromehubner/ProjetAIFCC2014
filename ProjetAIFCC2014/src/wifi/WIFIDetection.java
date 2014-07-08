package wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WIFIDetection {
    private Context context;
    
    public WIFIDetection(Context context) {
        this.context = context;
    }
 
    public boolean testConnection() {
        //Crée un objet connectivityManager renvoyant les info sur la connection 
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //On teste l'objet pour savoir s'il est null
        if (connectivity != null) {
            //On recupere les info sur le reseau
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
 
            if (info != null) {
                //Verifi si le tel est connecté au reseau
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
