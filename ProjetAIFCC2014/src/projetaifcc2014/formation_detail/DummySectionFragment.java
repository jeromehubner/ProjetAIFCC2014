package projetaifcc2014.formation_detail;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetaifcc2014.R;

public class DummySectionFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dummy,container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		Resources res = getResources();
		String strings[] = res.getStringArray(R.array.entetes);
		dummyTextView.setText(strings[getArguments().getInt(ARG_SECTION_NUMBER)-1]);
		return rootView;
	}

}
