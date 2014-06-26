package projetaifcc2014.database;

import projetaifcc2014.database.handler.TableCategorieHandler;
import projetaifcc2014.database.handler.TableCategorieToCategorieHandler;
import projetaifcc2014.database.handler.TableFormationHandler;
import projetaifcc2014.database.handler.TableFormationToCategorieHandler;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;

public class InitDatabase extends AsyncTask<Void, Void, Void>{

	private Activity mainActivity;
	private Thread splashThread;
	private ProgressBar progressBar;

	public InitDatabase (Activity mainActivity, Thread splashThread, ProgressBar progressBar){
		this.mainActivity = mainActivity;
		this.splashThread = splashThread;
		this.progressBar = progressBar;
	}
	

	
	@Override
	protected Void doInBackground(Void... params) {

		TableCategorieToCategorieHandler tableCatToCatHandler = new TableCategorieToCategorieHandler(mainActivity);
		tableCatToCatHandler.processFeed();
		progressBar.setSecondaryProgress(20);

		TableCategorieHandler tableCategorieHandler = new TableCategorieHandler(mainActivity);
		tableCategorieHandler.processFeed();
		progressBar.setSecondaryProgress(40);
		
		TableFormationToCategorieHandler tableFormToCatHandler = new TableFormationToCategorieHandler(mainActivity);
		tableFormToCatHandler.processFeed();
		progressBar.setSecondaryProgress(60);
		
		TableFormationHandler tableFormationHandler = new TableFormationHandler(mainActivity);
		tableFormationHandler.processFeed();
		progressBar.setSecondaryProgress(100);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		synchronized (splashThread) {
			splashThread.notifyAll();
		}
	}
}