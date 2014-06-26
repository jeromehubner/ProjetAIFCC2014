package projetaifcc2014.formation_detail;

import java.lang.reflect.Field;

import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetaifcc2014.R;
import com.example.projetaifcc2014.R.string;

public class FragmentDescription extends Fragment {

	/**
	 * The fragment argument representing the section number for this fragment.
	 */

	private string titre_formation_choisie;
	private Formation formationChoisie;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.fragment_page_viewer, container, false);

		ViewPager mViewPager = (ViewPager) rootView
				.findViewById(R.id.view_pager);

		// On récupère l'id de la formation pour récupérer le titre de celle-ci
		int id_formation_choisie = getArguments().getInt(
				getResources().getString(R.string.extra_id_formation));

		
		if (titre_formation_choisie == null) {
			TextView titre_formation_choisie = (TextView) rootView
					.findViewById(R.id.titre_formation_choisie);
			
			FormationBdd formationBdd = new FormationBdd(getActivity());
			formationBdd.open();
			formationChoisie = formationBdd.getFormationById(id_formation_choisie);
			formationBdd.close();
			
			// On renseigne le titre de la textView
			titre_formation_choisie.setText(formationChoisie.getDiplome());
			
			//On renseigne la couleur du titre de la textView
			int color = Color.parseColor(getHTMLCouleurDepartement(formationChoisie));
			titre_formation_choisie.setTextColor(color);
		}

		mViewPager.setAdapter(new SectionsPagerAdapter(
				getChildFragmentManager(), getResources().getStringArray(
						R.array.titre_onglet_detail_formation)));

		return rootView;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	// TODO : deplacer cette méthode en utilisant l'heritage
	public String getHTMLCouleurDepartement(Formation formation)
	{
		int indexArrayColor = formation.getCategorie().getDepartement().getId() -1;

		// On récupère le tableau des couleurs de départements renseignés dans le fichier color.xml
		int[] colorArray = getResources().getIntArray(R.array.dpt_color);


		String couleurTemp = Integer.toHexString(colorArray[indexArrayColor]);

		return couleurTemp.replace("ff", "#");
	}
}
