package projetaifcc2014.drawerFragment;

import com.example.projetaifcc2014.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	// Le drawer n'est utilisable que pour le screen_formation_detail
        View rootView = inflater.inflate(R.layout.fragment_aide, container, false);

        return rootView;
    }

}
