package projetaifcc2014.drawerFragment.rejoindre;

import projetaifcc2014.map.MapActivityAIFCC;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.projetaifcc2014.R;

public class FragmentLisieux extends Fragment {
	String centre = "Lisieux";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	// Le drawer n'est utilisable que pour le screen_formation_detail
        View rootView = inflater.inflate(R.layout.fragment_go_lisieux, container, false);

        return rootView;
    }

    

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		 Button map = (Button) view.findViewById(R.id.button_lisieux);
		 
		 map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ouvreMap = new Intent(v.getContext(), MapActivityAIFCC.class);
				ouvreMap.putExtra("Centre", centre);
				startActivity(ouvreMap);
			}
		});
	}
	
}
