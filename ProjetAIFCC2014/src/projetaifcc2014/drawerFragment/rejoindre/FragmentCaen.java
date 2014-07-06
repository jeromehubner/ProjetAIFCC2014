package projetaifcc2014.drawerFragment.rejoindre;

import projetaifcc2014.map.MapActivityAIFCC;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetaifcc2014.R;

public class FragmentCaen extends Fragment {
	
	String centre = "Caen";
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	
        View rootView = inflater.inflate(R.layout.fragment_go_caen, container, false);
       

        return rootView;
    }


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		 Button map = (Button) view.findViewById(R.id.button_caen);
		 
		 map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent ouvreMap = new Intent(v.getContext(), MapActivityAIFCC.class);
				ouvreMap.putExtra("Centre", centre);
				startActivity(ouvreMap);
			}
		});
	}

    
}