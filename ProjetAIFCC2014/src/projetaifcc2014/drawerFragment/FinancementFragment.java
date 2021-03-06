package projetaifcc2014.drawerFragment;

import projetaifcc2014.drawer.Activity_drawer;
import android.content.pm.ActivityInfo;
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
public class FinancementFragment extends Fragment {

    public FinancementFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	// Le drawer n'est utilisable que pour le screen_formation_detail
        View rootView = inflater.inflate(R.layout.fragment_financement, container, false);

        return rootView;
    }
}
