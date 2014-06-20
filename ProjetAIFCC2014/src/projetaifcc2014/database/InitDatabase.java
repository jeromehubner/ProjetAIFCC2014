package projetaifcc2014.database;

import projetaifcc2014.database.handler.TableCategorieHandler;
import projetaifcc2014.database.handler.TableCategorieToCategorieHandler;
import projetaifcc2014.database.handler.TableFormationHandler;
import projetaifcc2014.database.handler.TableFormationToCategorieHandler;
import android.app.Activity;
import android.os.AsyncTask;

public class InitDatabase extends AsyncTask<Void, Void, Void>{

	private Activity mainActivity;
	private Thread splashThread;

	public InitDatabase (Activity mainActivity, Thread splashThread){
		this.mainActivity = mainActivity;
		this.splashThread = splashThread;
	}
	

	@Override
	protected Void doInBackground(Void... params) {

		TableCategorieToCategorieHandler tableCatToCatHandler = new TableCategorieToCategorieHandler(mainActivity);
		tableCatToCatHandler.processFeed();

		TableCategorieHandler tableCategorieHandler = new TableCategorieHandler(mainActivity);
		tableCategorieHandler.processFeed();

		TableFormationToCategorieHandler tableFormToCatHandler = new TableFormationToCategorieHandler(mainActivity);
		tableFormToCatHandler.processFeed();

		TableFormationHandler tableFormationHandler = new TableFormationHandler(mainActivity);
		tableFormationHandler.processFeed();

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		synchronized (splashThread) {
			splashThread.notifyAll();
		}
	}
}