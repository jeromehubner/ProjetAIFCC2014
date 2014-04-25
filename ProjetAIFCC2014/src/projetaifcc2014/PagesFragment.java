package projetaifcc2014;

import projetaifcc2014.drawer.Activity_drawer;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetaifcc2014.R;

/**
 * Cette classe est appelée par la classe {@link Activity_drawer} qui permet
 * @author jerome
 *
 */
public class PagesFragment extends Fragment {

    public PagesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    	// Le drawer n'est utilisable que pour le screen_ormation_detail
        View rootView = inflater.inflate(R.layout.screen_formation_detail, container, false);

        return rootView;
    }
}
