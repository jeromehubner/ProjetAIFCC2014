package projetaifcc2014.map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * classe qui demande la permission au gps
 * 
 * 
 * 
 */
public class PermissionGps extends FragmentActivity {
    private void createGpsDisabledAlert(String message) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder
            .setMessage("Le GPS est inactif, voulez-vous l'activer ?")
            .setCancelable(false)
            .setPositiveButton(message,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        PermissionGps.this.showGpsOptions();
                    }
                }
            );
        localBuilder.setNegativeButton("Ne pas l'activer ",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    paramDialogInterface.cancel();
                    PermissionGps.this.finish();
                }
            }
        );
        localBuilder.create().show();
    }

    private void showGpsOptions() {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        finish();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        String message = null ;
        
        if (getIntent().getBooleanExtra("GPS", true) == true) {
                message = "Désactiver le GPS" ;
            }
        else message = "Activer GPS ";
        createGpsDisabledAlert(message);
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#startActivityForResult(android.content.Intent, int, android.os.Bundle)
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode,
			Bundle options) {
		super.startActivityForResult(intent, requestCode, options);
	}
    
    
}
