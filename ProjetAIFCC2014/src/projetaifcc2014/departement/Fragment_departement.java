package projetaifcc2014.departement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ViewFlipper;

import com.example.projetaifcc2014.R;

public class Fragment_departement extends Fragment {

	private ViewFlipper vfBan;
	private ViewFlipper vfActu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View vueCourante = inflater.inflate(R.layout.screen_department, container, false);

		// Le contenu suivant concerne le ViewFlipper

		// Instanciation des objets
		vfBan = (ViewFlipper)vueCourante.findViewById(R.id.ViewFlipperImages);

		// Définition des animations de slide
		Animation right_in = AnimationUtils.loadAnimation(vueCourante.getContext(),R.anim.right_in);
		Animation left_out = AnimationUtils.loadAnimation(vueCourante.getContext(),R.anim.left_out);

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(1000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
		fadeOut.setStartOffset(1000);
		fadeOut.setDuration(1000);

		Animation fadeIn2 = new AlphaAnimation(0, 1);
		fadeIn2.setInterpolator(new DecelerateInterpolator());
		fadeIn2.setDuration(1000);

		Animation fadeOut2 = new AlphaAnimation(1, 0);
		fadeOut2.setInterpolator(new AccelerateInterpolator()); //and this
		fadeOut2.setStartOffset(1000);
		fadeOut2.setDuration(1000);

		// Affectation des animations
		vfBan.setInAnimation(fadeIn);
		vfBan.setOutAnimation(fadeOut);

		// Paramètrage du comportement du ViewFlipper banniere
		vfBan.setAutoStart(true);
		vfBan.startFlipping();

		// Paramètrage du ViewFlipper actualités
		vfActu = (ViewFlipper)vueCourante.findViewById(R.id.ViewFlipperActus);
		vfActu.setInAnimation(right_in);
		vfActu.setOutAnimation(left_out);

		vfActu.setAutoStart(true);
		vfActu.startFlipping();

		return vueCourante;
	}

}
