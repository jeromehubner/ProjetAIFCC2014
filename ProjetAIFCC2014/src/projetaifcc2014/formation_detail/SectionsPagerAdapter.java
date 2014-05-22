package projetaifcc2014.formation_detail;

import android.content.ContextWrapper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


// SectionsPagerAdapter
//public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//
//	public SectionsPagerAdapter(FragmentManager fm) {
//		super(fm);
//	}
//
//	@Override
//	public Fragment getItem(int position) {
//		int idLayout = getResources().getIdentifier("fragment_formation_detail_".concat(String.valueOf(position+1)), "layout", getPackageName());
//		if(idLayout != 0){
//			return new FragmentLayout(idLayout);
//		}
//		return new Fragment();
//	}
//
//	@Override
//	public int getCount() {
//		return 4;
//	}
//
//	@Override
//	public CharSequence getPageTitle(int position) {
//		switch (position) {
//		case 0: return "Métiers visés";
//		case 1: return "Modalités";
//		case 2: return "Programme";
//		case 3: return "Profil requis";
//		}
//		return null;
//	}
//}
