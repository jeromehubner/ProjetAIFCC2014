package projetaifcc2014.formation_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class FragmentLayout extends Fragment {
	private int idLayout;

	public FragmentLayout(){}
	public FragmentLayout(int idLayout){
		this.idLayout = idLayout;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(idLayout, container, false);
	}
}
