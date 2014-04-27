//package projetaifcc2014.formation_detail;
//
//import java.util.Locale;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import com.example.projetaifcc2014.R;
//
//
//// SectionsPagerAdapter
//public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//
//	public SectionsPagerAdapter(FragmentManager fm) {
//		super(fm);
//	}
//
//	@Override
//	public Fragment getItem(int position) {
//		// getItem is called to instantiate the fragment for the given page.
//		// Return a DummySectionFragment (defined as a static inner class
//		// below) with the page number as its lone argument.
//		Fragment fragment = new DummySectionFragment();
//		Bundle args = new Bundle();
//		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//		fragment.setArguments(args);
//		return fragment;
//	}
//
//	@Override
//	public int getCount() {
//		// Shows 4 total pages.
//		return 4;
//	}
//
//	@Override
//	public CharSequence getPageTitle(int position) {
//		Locale l = Locale.getDefault();
//		switch (position) {
//		case 0:
//			return getString(R.string.ong_metier);
//		case 1:
//			return getString(R.string.ong_modalite);
//		case 2:
//			return getString(R.string.ong_programme);
//		case 3:
//			return getString(R.string.ong_profil);
//		default:
//			return null;
//		}
//	}
//}
