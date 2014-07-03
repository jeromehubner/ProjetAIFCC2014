package projetaifcc2014.departement;

import projetaifcc2014.MainActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.projetaifcc2014.R;

@SuppressLint("ValidFragment")
public class Fragment_departement extends Fragment {

	private ViewFlipper vfBan;
	private ViewFlipper vfActu;
	private ImageView[] imgs = new ImageView[3];
	private View vueCourante;
	private int[] nbImage = new int[4];


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		vueCourante = inflater.inflate(R.layout.screen_department, container, false);
		
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
		
		vfBan = (ViewFlipper) vueCourante.findViewById(R.id.ViewFlipperImages);
		
		// Affectation des animations
		vfBan.setInAnimation(fadeIn);
		vfBan.setOutAnimation(fadeOut);

		// Paramètrage du ViewFlipper actualités
		vfActu = (ViewFlipper)vueCourante.findViewById(R.id.ViewFlipperActus);
		vfActu.setInAnimation(right_in);
		vfActu.setOutAnimation(left_out);

		vfActu.setAutoStart(true);
		vfActu.setFlipInterval(7500);
		vfActu.startFlipping();
		vfBan.startFlipping();
		return vueCourante;
	}


//	@Override
//	public void onResume() {
//		super.onResume();
//		fillViewFlipper();
//	}

//	public void fillViewFlipper(){
//		imgs = MainActivity.imgs;
//		vfBan = (ViewFlipper) vueCourante.findViewById(R.id.ViewFlipperImages);
//		if(imgs != null){
//			DisplayMetrics metrics = new DisplayMetrics();
//			getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//			for (int i = 1; i < imgs.length+1; i++){
//				if(vfBan.getChildCount() > 1){
//					vfBan.removeViews(2, vfBan.getChildCount());	
//				}
//				else{
//					vfBan.removeViews(1, vfBan.getChildCount());
//				}
//				if(imgs[i-1] != null && nbImage[i-1] != i){
//					imgs[i-1].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//					vfBan.addView(imgs[i-1]);
//					nbImage[i-1] = i;
//				}
//			}
//		}
//		if(!vfBan.isActivated() && vfBan.getChildCount() >= 2){
//			vfBan.startFlipping();
//		}
//	}

}
