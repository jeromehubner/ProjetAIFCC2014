package projetaifcc2014.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetaifcc2014.R;

/**
 * Cette classe est appelée par la classe {@link Activity_drawer}
 * @author jerome
 *
 */
public class PagesFragment extends Fragment {

    public PagesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	// Le drawer n'est utilisable que pour le screen_formation_detail
        View rootView = inflater.inflate(R.layout.screen_formation_detail, container, false);

        return rootView;
    }
}
