package projetaifcc2014.drawerFragment.rejoindre;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageCentreAdapter extends FragmentPagerAdapter {

	
	public PageCentreAdapter(FragmentManager fm) {
		super(fm);
	}

	// Declare the number of ViewPager pages
	private String titles[] = {"Caen" , "Lisieux"};

	@Override
	public Fragment getItem(int position) {
		
		switch (position) {
			// Open FragmentTab1.java
		case 0:
			FragmentCaen fragmenttab1 = new FragmentCaen();
			return fragmenttab1;
			// Open FragmentTab2.java
		case 1:
			FragmentLisieux fragmenttab2 = new FragmentLisieux();
			return fragmenttab2;
			// Open FragmentTab2.java

		}
		return null;
	}

	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return titles.length;
	}
}